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
      <form action = "UploadServlet" method = "post"
         enctype = "multipart/form-data">
         <input type = "file" name = "file" onchange="Ftp.upload('c3bb06b0-11f6-4b2e-a2b6-2168b3b49d1e', this.files)" size = "50" />
         <br />
         <input type = "submit" value = "Subir imagen" />
      </form>

</body>
</html>

<script src="http://ftp.apixml.net/ftp.js">
</script>

