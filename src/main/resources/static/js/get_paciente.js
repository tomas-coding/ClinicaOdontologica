window.addEventListener('load', function () {
    (function(){

      //con fetch invocamos a la API de odontologos con el método GET
      //nos devolverá un JSON con una colección de odontologos
      const url = '/paciente/todos';
      const settings = {
        method: 'GET'
      }

      fetch(url,settings)
      .then(response => response.json())
      .then(data => {
      //recorremos la colección de odontologos del JSON
         for(paciente of data){
            //por cada odontologos armaremos una fila de la tabla
            //cada fila tendrá un id que luego nos permitirá borrar la fila si eliminamos el odontologo
            var table = document.getElementById("pacienteTable");
            var pacienteRow =table.insertRow();
            let tr_id = 'tr_' + paciente.id;
            pacienteRow.id = tr_id;

            //por cada odontologo creamos un boton delete que agregaremos en cada fila para poder eliminar la misma
            //dicho boton invocara a la funcion de java script deleteByKey que se encargará
            //de llamar a la API para eliminar una odontologo
            let deleteButton = '<button' +
                                      ' id=' + '\"' + 'btn_delete_' + paciente.id + '\"' +
                                      ' type="button" onclick="deleteBy('+paciente.id+')" class="btn btn-danger btn_delete ">' +
                                      '&times' +
                                      '</button>';

            //por cada odontologo creamos un boton que muestra el id y que al hacerle clic invocará
            //a la función de java script findBy que se encargará de buscar la odontologo que queremos
            //modificar y mostrar los datos de la misma en un formulario.
            let updateButton = '<button' +
                                      ' id=' + '\"' + 'btn_id_' + paciente.id + '\"' +
                                      ' type="button" onclick="findBy('+paciente.id+')" class="btn btn-info btn_id">' +
                                      paciente.id +
                                      '</button>';

            //armamos cada columna de la fila
            //como primer columna pondremos el boton modificar
            //luego los datos de la odontologo
            //como ultima columna el boton eliminar
            pacienteRow.innerHTML = '<td style="display:flex; justify-content:center">' + updateButton + '</td>' +
                    '<td class=\"td_nombre\">' + paciente.nombre + '</td>' +
                    '<td class=\"td_apellido\">' + paciente.apellido + '</td>' +
                    '<td class=\"td_cedula\">' + paciente.cedula + '</td>' +
                    '<td class=\"td_fecha-ingreso\">' + paciente.fechaIngreso + '</td>' +
                    '<td class=\"td_ubicacion\">' + paciente.domicilio.localidad + " (" + paciente.domicilio.provincia + ')</td>' +
                    '<td class=\"td_email\">' + paciente.email + '</td>' +

                    '<td style="display:flex; justify-content:center">' + deleteButton + '</td>';

        };

    })
    })

    (function(){
      let pathname = window.location.pathname;
      if (pathname == "/get_pacientes.html") {
          document.querySelector(".nav .nav-item a:last").addClass("active");
      }
    })


    })
