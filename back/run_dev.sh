#!/bin/bash
git clone https://github.com/LeMilliard/DecisionTree.git;
cd DecisionTree/;
mvn clean install;
# ls;
cd ..;
# ls;
rm -rf DecisionTree/;
cd SoupeoServer;
# ls;
mvn clean package;
java -jar target/soupeoserver-0.0.1-SNAPSHOT.jar;