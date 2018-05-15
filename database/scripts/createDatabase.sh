echo "##################################"
echo "Creating MongoDB soupeo Database"
echo "##################################"

mongoimport --host=127.0.0.1 --db 'soupeo' --collection 'user_session' --file $PWD'/scripts/examples/user_session.json' --jsonArray;

mongoimport --host=127.0.0.1 --db 'soupeo' --collection 'popup_content' --file $PWD'/scripts/examples/popup_content.json' --jsonArray;
