printf "A continuacion se Eliminara al odontologo: \n"

cat ./odontologo-jorge.json | jq .
sleep 3

printf "Eliminando Odontologo1...\n"
sleep 1
curl -X DELETE -H "Content-Type: application/json" -d @odontologo1.json -i 127.0.0.1:8080/odontologo/eliminar/3
