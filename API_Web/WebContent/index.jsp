<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Subir imagenes</title>
</head>
<body>
<h1>Subir imagenes</h1>
      Seleccione una imagen a subir: <br />
      <form action = "javascript:subirImagen()" enctype = "multipart/form-data">
         <!--<input type = "file" name = "file" onchange="Ftp.upload('c3bb06b0-11f6-4b2e-a2b6-2168b3b49d1e', this.files)" size = "50" />-->
         <input type = "file" name = "file" id="imagen" size = "50" />
        
         <br />
         <input type = "submit" value = "Subir imagen" />
      </form>

</body>
<script type="text/javascript">

var id_reclamo = 1;
var extension = '';
var nombre = '';

var Ftp = {
	    createCORSRequest: function (method, url) {
	        var xhr = new XMLHttpRequest();
	        if ("withCredentials" in xhr) {
	            // Check if the XMLHttpRequest object has a "withCredentials" property.
	            // "withCredentials" only exists on XMLHTTPRequest2 objects.
	            xhr.open(method, url, true);
	        } else if (typeof XDomainRequest != "undefined") {
	            // Otherwise, check if XDomainRequest.
	            // XDomainRequest only exists in IE, and is IE's way of making CORS requests.
	            xhr = new XDomainRequest();
	            xhr.open(method, url);
	        } else {
	            // Otherwise, CORS is not supported by the browser.
	            xhr = null;
	        }
	        return xhr;
	    },
	    upload: function(token, files) {
	        var file = files[0];
	        var reader = new FileReader();
	        reader.readAsDataURL(file);
	        reader.addEventListener("load",
	            function() {
	                var base64 = this.result;               
	                var xhr = Ftp.createCORSRequest('POST', "http://ftp.apixml.net/upload.aspx");
	                if (!xhr) {
	                    throw new Error('CORS not supported');
	                }
					xhr.onreadystatechange = function() {
						if (xhr.readyState == 4 && xhr.status == 200) {
							Ftp.callback(file);
						}
					};
	                xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	                xhr.send("token=" + token + "&data=" + encodeURIComponent(base64) + "&file=" + file.name);
	            },
	            false);
	    },
		callback: function()
		{
			actualizarBase();
		}
	};



function subirImagen()
{
	var imagen = document.getElementById('imagen');
	imagen = imagen.files;
	if(imagen.length > 0)
	{
		var imagenes = imagen;
		imagen = imagen[0];
		var nombreYExtension = imagen.name;
		var datos = nombreYExtension.split('.');
		
		nombre = datos[0];
		extension = datos[1];
		
		if(extension != 'jpg' && extension != 'png' && extension != 'gif')
		{
			alert("El archivo subido debe ser una imagen jpg, png o gif.");
		}
		else
		{
	      	Ftp.upload('c3bb06b0-11f6-4b2e-a2b6-2168b3b49d1e', imagenes);		      
			
		}
	}
	else
	{
		alert("Debe seleccionar una imagen.");
	}
	
}

function actualizarBase()
{	
	const form = document.createElement('form');
	form.method = 'post';
	form.action = '/apitp/agregarImagenAReclamo';

    const numeroReclamo = document.createElement('input');
    numeroReclamo.type = 'hidden';
    numeroReclamo.name = 'numero';
 	numeroReclamo.value = id_reclamo;

    form.appendChild(numeroReclamo);

    const direccion = document.createElement('input');
    direccion.type = 'hidden';
    direccion.name = 'direccion';
    direccion.value = nombre;

    form.appendChild(direccion);

    const tipo = document.createElement('input');
    tipo.type = 'hidden';
    tipo.name = 'tipo';
    tipo.value = extension;

    form.appendChild(tipo);

    document.body.appendChild(form);
    
    form.submit();
	
	
}
</script>

</html>