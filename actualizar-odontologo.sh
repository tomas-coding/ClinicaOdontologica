printf "A continuacion se actualizara el odontologo: \n"
cat ./odontologo-jorge.json | jq .
sleep 3

printf "\n Por el odontologo: \n"
cat ./odontologo-jorgito.json | jq .

sleep 3

printf "Actualizando odontologo...\n"
sleep 1
curl -X PUT -H "Content-Type: application/json" -d @odontologo-jorgito.json 127.0.0.1:8080/odontologo/actualizar | jq .

printf "Lo que sucede cuando quiero actualizar un odontologo inexistente: \n"
sleep 3
curl -X PUT -H "Content-Type: application/json" -d @odontologo-inexistente.json -i 127.0.0.1:8080/odontologo/actualizar
