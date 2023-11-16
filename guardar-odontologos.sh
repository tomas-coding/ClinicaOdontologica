printf "A continuacion se guardaran Odontologo1 y Odontologo2 a la base de datos\n"
sleep 3

printf "Guardando Odontologo1..."
sleep 1
curl -X POST -H "Content-Type: application/json" -d @odontologo1.json 127.0.0.1:8080/odontologo/guardar | jq .

printf "Guardando Odontologo2..."
sleep 1
curl -X POST -H "Content-Type: application/json" -d @odontologo2.json 127.0.0.1:8080/odontologo/guardar | jq .
