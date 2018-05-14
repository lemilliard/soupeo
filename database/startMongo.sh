#!/bin/bash
echo "######################################"
echo "Starting Mongo for Soupeo Application"
echo "######################################"
echo ""

if [ "$(docker ps | grep soupeo-mongo)" ]
then 
    echo "Docker mongo already running"
    echo "Please run 'sh stopMongo.sh' before running this script"
else
    echo "Docker build : "
    echo ""
    docker build . --tag soupeo/mongo

    echo ""
    echo "Docker run : "
    echo ""
    docker run --name soupeo-mongo --rm -p 27017:27017 -d soupeo/mongo

    docker exec -ti soupeo-mongo sh ./scripts/createDatabase.sh
fi
