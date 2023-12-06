window.addEventListener('load', function () {


      const formulario = document.querySelector('#update_turno_form');

      formulario.addEventListener('submit', function (event) {
            event.preventDefault();
            let turnoId = document.querySelector('#turno_id').value;

            const formData = {
                  id: document.querySelector('#turno_id').value,
                  fechaTurno: document.querySelector('#fecha-turno').value,
                  pacienteId: document.querySelector('#paciente-id').value,
                  odontologoId: document.querySelector('#odontologo-id').value,

            };

            const url = '/turnos';
            const settings = {
                  method: 'PUT',
                  headers: {
                        'Content-Type': 'application/json',
                  },
                  body: JSON.stringify(formData)
            }
            fetch(url,settings)
                  .then(response => {response.json();

                        if( response.status > 400 ){
                              // Toast Notification: ERROR!
                                    const toastErr = document.getElementById('liveToastErr')
                              const toastBootstrapErrModif = bootstrap.Toast.getOrCreateInstance(toastErr)
                              toastBootstrapErrModif.show()               
                              // setTimeout(
                                            //            ()=>{location.reload();},
                                            //            2000
                                            // );
                        }
                        else {

                              const toastLiveExample = document.getElementById('liveToastModificar');
                              const toastBootstrapModif = bootstrap.Toast.getOrCreateInstance(toastLiveExample);
                              toastBootstrapModif.show();
                              setTimeout(
                                         ()=>{location.reload();},
                                         2000
                              );
                        }

                  })
                  .catch(
                         ()=>
                         {
                               // Toast Notification: ERROR!
                                     const toastErr = document.getElementById('liveToastErr')
                               const toastBootstrapErrModif = bootstrap.Toast.getOrCreateInstance(toastErr)
                               toastBootstrapErrModif.show()               
                         }
                  )

      })
})

function findBy(id) {
      const url = '/turnos'+"/"+id;
      const settings = {
            method: 'GET'
      }
      fetch(url,settings)
            .then(response => response.json())
            .then(data => {
                  let turno = data;
                  document.querySelector('#turno_id').value = turno.id;
                  document.querySelector('#fecha-turno').value = turno.fechaTurno;
                  document.querySelector('#paciente-id').value = turno.pacienteId;
                  document.querySelector('#odontologo-id').value = turno.odontologoId;
                  document.querySelector('#div_turno_updating').style.display = "block";
            }).catch(error => {
                  alert("Error: " + error);
            })
}
