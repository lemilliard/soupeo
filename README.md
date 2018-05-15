# Soupeo


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
    
### Collection popup_content
    [
        {
            "id_popup": Number,
            "title": String,
            "message": String
        },
        ...
    ]
