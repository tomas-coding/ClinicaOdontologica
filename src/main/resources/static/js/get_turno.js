window.addEventListener('load', function () {
    (function(){

      const url = '/turnos';
      const settings = {
        method: 'GET'
      }

      fetch(url,settings)
      .then(response => response.json())
      .then(async function( data ){
         for(turno of data){
            var table = document.getElementById("turnoTable");
            var turnoRow =table.insertRow();
            let tr_id = 'tr_' + turno.id;
            turnoRow.id = tr_id;


            let nombrePacienteId;
            let nombreOdontologoId;
            nombrePacienteId = await fetch(`/paciente/${turno.pacienteId}`,settings)
            .then(response => response.json())
            .then(data => {
                  return data.apellido;
            });
            nombreOdontologoId = await fetch(`/odontologo/${turno.odontologoId}`,settings)
            .then(response => response.json())
            .then(data => {
                  return data.apellido;
            });
            let deleteButton = '<button' +
                                      ' id=' + '\"' + 'btn_delete_' + turno.id + '\"' +
                                      ' type="button" onclick="deleteBy('+turno.id+')" class="btn btn-danger btn_delete">' +
                                      '&times' +
                                      '</button>';

            let updateButton = '<button' +
                                      ' id=' + '\"' + 'btn_id_' + turno.id + '\"' +
                                      ' type="button" onclick="findBy('+turno.id+')" class="btn btn-info btn_id">' +
                                      turno.id +
                                      '</button>';

            turnoRow.innerHTML = '<td>' + updateButton + '</td>' +
                    '<td class=\"td_matricula\">' + turno.fechaTurno + '</td>' +
                    '<td class=\"td_nombre\">' + nombrePacienteId + '</td>' +
                    '<td class=\"td_apellido\">' + nombreOdontologoId + '</td>' +

                    '<td>' + deleteButton + '</td>';

        };

    })
    })

    (function(){
      let pathname = window.location.pathname;
      if (pathname == "/get_turnos.html") {
          document.querySelector(".nav .nav-item a:last").addClass("active");
      }
    })


    })
