printf "A continuacion se listaran todos los odontologos almacenados en la Base de datos\n\n"
sleep 3

curl -X GET 127.0.0.1:8080/odontologo/listar | jq .
