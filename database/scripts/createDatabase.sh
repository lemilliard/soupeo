echo "##################################"
echo "Creating MongoDB soupeo Database"
echo "##################################"

mongoimport --host=127.0.0.1 --db 'soupeo' --collection 'connexion_analysis' --file $PWD'/scripts/examples/connexion_analysis.json' --jsonArray;
mongoimport --host=127.0.0.1 --db 'soupeo' --collection 'connexion_analysis2' --file $PWD'/scripts/examples/connexion_analysis.json' --jsonArray;