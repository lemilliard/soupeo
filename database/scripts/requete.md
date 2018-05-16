# Requêtes

## popup_content

get popup_content by 'id_popup' :

    db.getCollection('popup_content').find({ "id_popup": NUMBER })

## user_session

get user_session by '_id' :

    db.getCollection('user_session').find({ "_id" : ObjectId("5afbfcf696a26a51d4067ffb") })

update end date :

    db.getCollection('user_session').update({ "_id" : ObjectId("5afbfcf696a26a51d4067ffb") }, {$set :{ end_session: 'DATE' }})