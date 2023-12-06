window.addEventListener('load', function () {


      //Buscamos y obtenemos el formulario donde estan
      //los datos que el usuario pudo haber modificado de el odontolog
      const formulario = document.querySelector('#update_odontologo_form');

      formulario.addEventListener('submit', function (event) {
            event.preventDefault();
            let odontologoId = document.querySelector('#odontologo_id').value;

            //creamos un JSON que tendrá los datos de la película
            //a diferencia de un odontologo nueva en este caso enviamos el id
            //para poder identificarla y modificarla para no cargarla como nueva
            const formData = {
                  id: document.querySelector('#odontologo_id').value,
                  matricula: document.querySelector('#matricula').value,
                  nombre: document.querySelector('#nombre').value,
                  apellido: document.querySelector('#apellido').value,

            };

            //invocamos utilizando la función fetch la API odontologos con el método PUT que modificará
            //la película que enviaremos en formato JSON
            const url = '/odontologo';
            const settings = {
                  method: 'PUT',
                  headers: {
                        'Content-Type': 'application/json',
                  },
                  body: JSON.stringify(formData)
            }
            fetch(url,settings)
                  .then(response => {response.json();
                        const toastLiveExample = document.getElementById('liveToastModificar');
                        const toastBootstrapModif = bootstrap.Toast.getOrCreateInstance(toastLiveExample);
                        toastBootstrapModif.show();
                       setTimeout(
                                   ()=>{location.reload();},
                                   2000
                        );

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

//Es la funcion que se invoca cuando se hace click sobre el id de un odontologo del listado
//se encarga de llenar el formulario con los datos de el odontologo
//que se desea modificar
function findBy(id) {
      const url = '/odontologo'+"/"+id;
      const settings = {
            method: 'GET'
      }
      fetch(url,settings)
            .then(response => response.json())
            .then(data => {
                  let odontologo = data;
                  document.querySelector('#odontologo_id').value = odontologo.id;
                  document.querySelector('#matricula').value = odontologo.matricula;
                  document.querySelector('#nombre').value = odontologo.nombre;
                  document.querySelector('#apellido').value = odontologo.apellido;
                  //el formulario por default esta oculto y al editar se habilita
                  document.querySelector('#div_odontologo_updating').style.display = "block";
            }).catch(error => {
                  alert("Error: " + error);
            })
}
