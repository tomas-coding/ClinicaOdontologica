printf "A continuacion se buscara a un odontologo por String...\n"
sleep 2

string=""
printf "Ingrese el string buscado: \n"
read string

curl -X GET 127.0.0.1:8080/odontologo/buscar/atributo?str="${string}" | jq .
