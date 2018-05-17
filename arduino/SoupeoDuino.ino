#include <SPI.h>
#include <Ethernet.h>
/**
   Variable permettant de choisir entre une assignation
   fixe ou dynamique du niveau 3 OSI
   '0' --> configuration statique
   '1' --> configuration dynamique
*/
#define DHCP 0
/**
   Adresse MAC du module, doit être unique sur le réseau !
   Ici 00:01:02:03:04:05
*/
byte mac[] = { 0x50, 0x72, 0x7D, 0x3D, 0x2D, 0x90 };

// Serveur écoutant sur le port 80
EthernetServer server(80);

#if DHCP == 0
// Adresse IP
IPAddress ip = { 192, 168, 5, 2 };
// Masque de sous-réseau
IPAddress mask = { 255, 255, 255, 0 };
// Passerelle
IPAddress gateway = { 192, 168, 5, 1 };
// DNS
IPAddress server_dns = { 192, 168, 5, 1 };
#endif

// Méthode démarrant le début de l'URL
static const char *methods[4];

/**
  Arbitrairement on décide que le chemin
  contiendra 5 partie de 16 caractères
*/
static const uint8_t URL_MAX_PART = 5;
static const uint8_t URL_PART_SIZE = 16;
/**
  Arbitrairement on décide qu'il y aura
  maximum 5 paramètres de 15 caractères
*/
static const uint8_t PARAM_MAX_NUMBER = 5;
static const uint8_t PARAM_SIZE = 15;
// Tableau permettant de stocker les parties du chemin
char url_parts[URL_MAX_PART][URL_PART_SIZE];
// Tableau permettant de stocker les paramètres
char param_names[PARAM_MAX_NUMBER][PARAM_SIZE];
char param_values[PARAM_MAX_NUMBER][PARAM_SIZE];

// Variables utilisée à l'exécution
uint16_t http_status_code = 0;
uint16_t http_error_code = 200;
// Permet de savoir si l'URL est trouvée
bool isUrl = false;
// Nombre de parties du chemin
uint8_t nbUrlPart = 0;
// Nombre de paramètres de l'URL
uint8_t nbParam = 0;
// URL complète pour accéder à un fichier
char url[URL_MAX_PART * URL_PART_SIZE + URL_MAX_PART];

char request[100];

int ledPin = 3;

uint64_t currentMillis;
uint64_t previousMillis;
uint16_t timer = 15000;

void setup() {
  methods[0] = "GET";
  methods[1] = "POST";
  methods[2] = "PUT";
  methods[3] = "DELETE";

  // Démarrage du port série
  Serial.begin(9600);
  /**
     Démarrage du shield Ethernet sans spécifier d'adresse IP
     Cela oblige le contrôleur Ethernet à demander une configuration OSI 3
  */
#if DHCP == 0
  Serial.println(F("Setting ip..."));
  Ethernet.begin(mac, ip, server_dns, gateway, mask);
#else
  Serial.println(F("Requesting ip..."));
  if (Ethernet.begin(mac) == 0) {
    // Si c'est un échec, pas la peine de pousuivre...
    Serial.println(F("DHCP failure !"));
    while (true);
  }
#endif

  // Affichage des informations obtenues
  Serial.print(F("IP : "));
  Serial.println(Ethernet.localIP());
  Serial.print(F("Mask : "));
  Serial.println(Ethernet.subnetMask());
  Serial.print(F("Gateway : "));
  Serial.println(Ethernet.gatewayIP());
  Serial.print(F("DNS : "));
  Serial.println(Ethernet.dnsServerIP());

  Serial.println(F("Demarrage du serveur..."));
  server.begin();

  Serial.println(F("Préparation de la led..."));
  pinMode(ledPin, OUTPUT);
  previousMillis = millis();
}

void loop() {
  // On écoute les connections entrantes
  EthernetClient client = server.available();
  // Si la connexion est établie (SYN / SYN+ACK / ACK)...
  if (client) {
    while (client.connected()) {
      if (client.available()) {
        char c = client.read();
        // Optionnel, permet d'afficher le contenu de la requête HTTP
        //Serial.print(c);
        if (!isUrl) {
          if (digestURL(c)) {
            isUrl = true;
            // Optionnel, permet de voir ce qui à été récupéré
            //displayUrlVariables();
          }
        }
      } else {
        // Réponse au client
        sendResponse(client);

        // Fin de la requête
        break;
      }
    }
    // On coupe la connexion
    client.stop();
    resetVariables();


    if (strcmp(url_parts[0], "alert") == 0) {
      Serial.println(F("Allumage de la LED..."));
      digitalWrite(ledPin, HIGH);
      previousMillis = millis();
    }
  }
  if (digitalRead(ledPin) == HIGH) {
    currentMillis = millis();
    if (currentMillis - previousMillis > timer) {
      Serial.println(F("Eteignage de la LED..."));
      digitalWrite(ledPin, LOW);
      previousMillis = millis();
    }
  }
}

/**
   Digère l'URL en séparant le chemin et les paramètres
*/
bool digestURL(char c) {
  static int8_t algoPart = 0, readBytes = 0, urlSize = 0;
  if (algoPart == 0) {
    // On vérifie que le caractère lue correspond à un caractère de la méthode
    if (c == methods[0][readBytes++]) {
      if (readBytes == strlen(methods[0])) {
        // On vient de lire 'GET', on passe à la suite
        algoPart++;
        readBytes = 0;
      }
    } else {
      //On remet le compteur à zéro
      readBytes = 0;
    }
  } else if (algoPart == 1) {
    // On doit ignorer les 2 caractères qui suivent ' /'
    if (readBytes < 1) {
      readBytes++;
    } else {
      // Les deux caractères sont passés, on passe à la suite
      algoPart++;
      readBytes = 0;
    }
  } else if (c == ' ' || c == '\n') {
    // On a terminé la lecture de l'URL, RAZ des variables
    if (algoPart == 2) {
      // On termine la chaîne
      url_parts[nbUrlPart][readBytes] = '\0';
      url[urlSize] = '\0';
      nbUrlPart++;
    } else if (algoPart == 4) {
      // On termine la chaîne
      param_values[nbParam][readBytes] = '\0';
      nbParam++;
    }
    algoPart = -1;
  } else if (algoPart == 2) {
    // Lecture des parties du chemin
    if (c == '/') {
      // On termine la chaîne
      url_parts[nbUrlPart][readBytes] = '\0';
      url[urlSize++] = '/';
      /**
         On passe à la lecture de la partie suivante
         si le nombre max de parties n'est pas atteint
      */
      if (nbUrlPart == URL_MAX_PART - 1) {
        // Erreur 414 (Request-URI Too Long)
        http_status_code = 414;
        algoPart = -1;
      } else {
        nbUrlPart++;
        readBytes = 0;
      }
    } else if (c == '?') {
      // On termine la chaîne
      url_parts[nbUrlPart][readBytes] = '\0';
      url[urlSize] = '\0';
      // On a terminé la lecture du chemin et il y a des paramètres
      nbUrlPart++;
      algoPart++;
      readBytes = 0;
    } else {
      /**
         On ajoute le caractère à la partie si on
         a pas atteint le nombre max de caractères
      */
      if (readBytes ==  URL_PART_SIZE - 1) {
        // Erreur 413 (Request Entity Too Large)
        http_status_code = 413;
        algoPart = -1;
      } else {
        url_parts[nbUrlPart][readBytes++] = c;
        url[urlSize++] = c;
      }
    }
  } else if (algoPart == 3) {
    // Lecture des noms de paramètres
    if (c == '=') {
      // On termine la chaîne
      param_names[nbParam][readBytes] = '\0';
      // On passe à la lecture de la valeur
      algoPart++;
      readBytes = 0;
    } else {
      /**
         On ajoute le caractère au nom si on
         a pas atteint le nombre max de caractères
      */
      if (readBytes == PARAM_SIZE - 1) {
        // Erreur 413 (Request Entity Too Large)
        http_status_code = 413;
        algoPart = -1;
      } else {
        param_names[nbParam][readBytes++] = c;
      }
    }
  } else if (algoPart == 4) {
    // Lecture des valeurs de paramètres
    if (c == '&') {
      // On termine la chaîne
      param_values[nbParam][readBytes] = '\0';
      /**
         On passe à la lecture du nom du paramètre suivant
         si le nombre max de paramètres n'est pas atteint
      */
      if (nbParam == PARAM_MAX_NUMBER - 1) {
        // Erreur 414 (Request-URI Too Long)
        http_status_code = 414;
        algoPart = -1;
      } else {
        nbParam++;
        algoPart--;
        readBytes = 0;
      }
    } else {
      /**
         On ajoute le caractère à la valeur si on
         a pas atteint le nombre max de caractères
      */
      if (readBytes == PARAM_SIZE - 1) {
        // Erreur 413 (Request Entity Too Large)
        http_status_code = 413;
        algoPart = -1;
      } else {
        param_values[nbParam][readBytes++] = c;
      }
    }
  }
  if (algoPart == -1) {
    // RAZ des variables et fin !
    algoPart = 0;
    readBytes = 0;
    urlSize = 0;
    return true;
  }
  return false;
}

/**
   Remet à zéro les variables pour la requête suivante
*/
void resetVariables() {
  isUrl = false;
  http_error_code = 200;
  nbUrlPart = 0;
  nbParam = 0;
}

/**
   Envoie l'entête HTTP
*/
void sendResponse(EthernetClient client) {
  // Envoie de l'entête HTTP
  client.print(F("HTTP/1.1 "));
  client.println(http_status_code);
  // Ajouter ici les codes HTTP
  if (http_status_code == 200) {
    client.println(F(" OK"));
  } else if (http_status_code == 201) {
    client.println(F(" Created"));
  } else if (http_status_code == 204) {
    client.println(F(" No Content"));
  } else if (http_status_code == 400) {
    client.println(F(" Bad Request"));
  } else if (http_status_code == 404) {
    client.println(F(" Not Found"));
  } else if (http_status_code == 414) {
    client.println(F(" Request-URI Too Long"));
  } else if (http_status_code == 415) {
    client.println(F(" Request Entity Too Large"));
  } else if (http_status_code == 500) {
    client.println(F(" Internal Server Error"));
  }
  // Format de la réponse
  client.println(F("Content-Type: text/html"));
  // On prévient le client que la connexion est fermée
  client.println(F("Connection: close"));
  // Saut de ligne qui sépare le header du body
  client.println();

  // Affichage du body au client
  if (strcmp(url_parts[0], "alert") == 0) {
    client.println("OK");
  }

  // On attend que le client reçoive la réponse
  delay(1);
}

