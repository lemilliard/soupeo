git clone https://github.com/LeMilliard/DecisionTree.git
cd DecisionTree
mvn clean install
cd ..
rm -rf DecisionTree

cd SoupeoServer && mvn clean package
java -jar target/soupeoserver-0.0.1-SNAPSHOT.jar