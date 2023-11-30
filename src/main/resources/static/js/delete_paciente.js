function deleteBy(id) {
  // Construir la URL para la solicitud DELETE
  const url = '/paciente/'+ id;

  // Configuración de la solicitud
  const settings = {
    method: 'DELETE'
  };

  // Realizar la solicitud DELETE al servidor
  fetch(url, settings)
    .then(response => {
      if (response.ok) {
        // La respuesta del servidor fue exitosa (código de estado 2xx)
        // Eliminar la fila de la interfaz
        let row_id = "#tr_" + id;
        document.querySelector(row_id).remove();
      } else {
        // La respuesta del servidor fue un error (código de estado diferente de 2xx)
        // Puedes manejar el error de alguna manera
        console.error('Error en la solicitud DELETE:', response.status, response.statusText);
      }
    })
    .catch(error => {
      // Ocurrió un error de red u otro error
      console.error('Error en la solicitud DELETE:', error);
    });
}
