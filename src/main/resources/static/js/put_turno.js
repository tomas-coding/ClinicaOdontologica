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
          .then(response => {response.json(); location.reload();})

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