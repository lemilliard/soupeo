# Soupeo

## Back

Start:

	cd back
	sh run_dev.sh


## Mongo
Start :

	cd database/
	sh startMongo.sh
    

Stop : 

	cd database/
	sh stopMongo.sh
	
### Morphia (liens utiles) :
- https://dzone.com/articles/using-morphia-map-java-objects
- https://dzone.com/articles/mongodb-30-minutes-draft

### Collection user_session : 
    [
        {
            "id_terminal" : String, // id de la borne PILA 
            "location": String, // Lieu de la borne PILA (ville / batiment) 
            "start_sessoin": Date, // Date de début d'utilisation (lorsque l'utilisateur ouvre le navigateur)
            "end_session": Date, // Date de fin d'utilisation (lorsque l'utilisateur ferme le navigateur)
            "parcours": [ // Toutes les redirections de l'utilisateur lors de son utilisation 
                {
                    "start": Date, // Date de début de navigation sur le lien
                    "url": String, // Url de la page 
                    "help_used": Boolean, // L'utilisateur a t-il eu besoin d'aide (assistant virtuel)
                    "end": Date // Donnée ajoutée lorsque l'utilisateur a changé de page ou fermé le navigateur
                },
                ...
            ],
            "indice_pb": [
                {
                    "level": Number, // Sévérité de la galère de l'utilisateur 
                    "date": Date,  
                    "cause": String, // Cause de l'augmentation de l'indice définit par le BACK (analyse)
                },
                ...
            ],
            "mood": [
                {
                    "date": Date, 
                    "faceAttributes": { } // Mood returned by the API 
                },
                ...
            ]
        },
        ...
    ]
    
Exemple  :

    /* 1 */
    {
        "_id" : ObjectId("5afae0c5051cd61c824f3f24"),
        "id_terminal" : "adresse mac",
        "location" : "Montpellier",
        "start_sessoin" : ISODate("2013-11-20T23:32:18.000Z"),
        "end_session" : ISODate("2013-11-20T23:37:18.000Z"),
        "parcours" : [ 
            {
                "start" : ISODate("2013-11-20T23:32:18.000Z"),
                "url" : "https://pole-emploi.fr",
                "help_used" : false,
                "end" : ISODate("2013-11-20T23:33:18.000Z")
            }
        ],
        "indice_pb" : [ 
            {
                "level" : 0,
                "date" : ISODate("2013-11-20T23:32:18.000Z"),
                "cause" : "Nothing wrong"
            }
        ],
        "mood" : [ 
            {
                "date" : ISODate("2013-11-20T23:32:18.000Z"),
                "emotion" : {
                    "anger" : 0,
                    "contempt" : 0.01,
                    "disgust" : 0,
                    "fear" : 0,
                    "happiness" : 0.115,
                    "neutral" : 0.874,
                    "sadness" : 0,
                    "surprise" : 0
                }
            }
        ]
    }
    
### Collection popup_content
    [
        {
            "id_popup": Number,
            "title": String,
            "message": String
        },
        ...
    ]
