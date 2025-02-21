//la funcion carga una imagen en la pagina
function readURL(input) {
    if (input.files && input.files[0]) {
        //nos pasaron un archivo
        var lector = new FileReader();
        
        lector.onload = function(e) {
          $('#blah').attr('src',e.target.result) 
                  .heght(200);
        };
        
        lector.readAsDataURL(input.files[0]);
        
    }
}

