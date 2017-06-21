function validarLetras(e){
    tecla = (document.all) ? e.keyCode : e.which;
    if (tecla==8) return true; //baackspace
    if (tecla==32) return true; //espacio
    if (e.ctrlkey && tecla==86) {return true;} //ctrl v
    if (e.ctrlkey && tecla==67) {return true;} //ctrl c
    if (e.ctrlkey && tecla==88) {return true;} //ctrl x
    
    patron = /[a-zA-Z]/; //patron
    
    te = String.fromCharCode(tecla);
    return patron.test(te); //prueba de patron
}