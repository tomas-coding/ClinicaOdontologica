
window.addEventListener('load', function () {

      //Al cargar la pagina buscamos y obtenemos el formulario donde estarán
      //los datos que el usuario cargará de el nuevo odontologo
      const formulario = document.querySelector('#add_new_odontologo');


      //Ante un submit del formulario se ejecutará la siguiente funcion
      formulario.addEventListener('submit', function (event) {
            event.preventDefault();

            //creamos un JSON que tendrá los datos de la nueva película
            const formData = {
                  matricula: document.querySelector('#matricula').value,
                  nombre: document.querySelector('#nombre').value,
                  apellido: document.querySelector('#apellido').value,

            };
            //invocamos utilizando la función fetch la API odontologos con el método POST que guardará
            //la película que enviaremos en formato JSON
            const url = '/odontologo';
            const settings = {
                  method: 'POST',
                  headers: {
                        'Content-Type': 'application/json',
                  },
                  body: JSON.stringify(formData)
            }

            fetch(url, settings)
                  .then(response => response.json())
                  .then(data => {

                        //Si no hay ningun error se muestra un mensaje diciendo que el odontologo
                        //se agrego bien
                        // let successAlert = '<div class="alert alert-success alert-dismissible">' +
                              //     '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                              //     '<strong></strong> Odontologo agregado </div>'
                        //
                              // document.querySelector('#response').innerHTML = successAlert;
                        // document.querySelector('#response').style.display = "block";

                        resetUploadForm();

                        // Toast Notification: ADDED!
                        const toastLiveExample = document.getElementById('liveToast')
                        const toastBootstrap = bootstrap.Toast.getOrCreateInstance(toastLiveExample)
                        
                        toastBootstrap.show()               

                  })
                  .catch(error => {

                        //Si hay algun error se muestra un mensaje diciendo que el odontologo
                        //no se pudo guardar y se intente nuevamente
                        // let errorAlert = '<div class="alert alert-danger alert-dismissible">' +
                              //                  '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                              //                  '<strong> Error intente nuevamente</strong> </div>'
                        //
                              //   document.querySelector('#response').innerHTML = errorAlert;
                        //   document.querySelector('#response').style.display = "block";

                        //se dejan todos los campos vacíos por si se quiere ingresar otro odontologo

                        // Toast Notification: ERROR!
                                     const toastErr = document.getElementById('liveToastErr')
                               const toastBootstrapErr = bootstrap.Toast.getOrCreateInstance(toastErr)
                               toastBootstrapErr.show()               



                        resetUploadForm();})
      });



      function resetUploadForm(){
            document.querySelector('#matricula').value = "";
            document.querySelector('#nombre').value = "";
            document.querySelector('#apellido').value = "";

      }

      (function(){
            let pathname = window.location.pathname;
            if(pathname === "/"){
                  document.querySelector(".nav .nav-item a:first").addClass("active");
            } else if (pathname == "/odontologoList.html") {
                  document.querySelector(".nav .nav-item a:last").addClass("active");
            }
      })();
});

