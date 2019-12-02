import React, {Component} from 'react';
import './Reclamos.css';
import { Link } from 'react-router-dom'

class ReclamoForm extends Component {
   constructor(props) {
      super(props);
      this.state  = {
        reclamo: {},
        edificios: [],
        unidadesListadas: [],
        isLoaded:false
      }
   }



  // getEdificiosParaReclamosUsuario
   componentDidMount() {
      fetch('http://localhost:8080/apitp/getEdificiosParaReclamosUsuario?documento=' + sessionStorage.getItem("documento"))
      .then((res) => res.json()).then((json) => {
         var listadoEdificios = [];
         json.forEach(function(unidad){
            var aparecio = false;
            listadoEdificios.forEach(function(edificio){
               if(edificio.nombre == unidad.edificio.nombre)
               {
                  aparecio = true;
               }
            });

            if(aparecio == false)
            {
               listadoEdificios.push(unidad.edificio);
            }
         });

         this.setState({
            isLoaded: true,
            unidades: json,
            edificios: listadoEdificios
            });
      }).catch((error) =>{
        alert("Error en API" + error);
      });
 }
   handleChange = (event) => {
      var valorSel = event.target.value;
      if(event.target.id == "listaEdificios")
      {
         if(valorSel == -1)
         {
            document.getElementById("espacioComun").style.display = "none";
         }
         else
         {
            document.getElementById("espacioComun").style.display = "block";

         }

         var unidadesMostrar = [];
         this.state.unidades.forEach(function(unidad){
            if(unidad.edificio.codigo == valorSel)
            {
               unidadesMostrar.push(unidad);
            }
         });
         this.setState({
            unidadesListadas: unidadesMostrar
            });   
      }
      else if(event.target.id == "listaUnidades")
      {
         if(valorSel == 0)
         {
            document.getElementById("ubicacionMostrar").style.display = "inline";           
         }
         else
         {
            document.getElementById("ubicacionMostrar").style.display = "none";          
         }
      }
      

      //Busco las unidades y las muestro.
      //this.setState({ value: event.target.value });
   };

   handleSubmit = (event) => {

      event.preventDefault();

      var edificio = document.getElementById("listaEdificios").value;
      var ubicacion = null;
      var piso = null;
      var numero = null;

      if(edificio != -1)
      {
         var unidad = document.getElementById("listaUnidades").value;
         if(unidad == 0)
         {
            ubicacion = document.getElementById("ubicacion").value;
            unidad = null;
            if(ubicacion == '')
            {
               alert("Debe escribir una ubicación");
            }
         }

         if(unidad == -1)
         {
            alert("Debe seleccionar una unidad");
         }
         else
         {
            if(unidad != 0)
            {
               this.state.unidades.forEach(function(un){
                  if(un.piso + "° " + un.numero == unidad)
                  {
                     piso = un.piso;
                     numero = un.numero;
                  }
               });
      
            }

            var descripcion = document.getElementById("descripcion").value;
            if(descripcion == '')
            {
               alert("Debe escribir una descripcion de su problema");
            }
            else
            {
               var error = false;
               var imagenes = document.getElementById('imagenes');
               imagenes = imagenes.files;
               if(imagenes.length > 0)
               {
                  var i = 0;
                  while(i < imagenes.length)
                  {
                     var imagen = imagenes[i];
                     var nombreYExtension = imagen.name;
                     var datos = nombreYExtension.split('.');
                     
                     var extension = datos[1];
                     
                     if(extension != 'jpg' && extension != 'png' && extension != 'gif' && extension != 'jpeg')
                     {
                        error = true;
                     }
                     i++;

                  }
         
               }

               if(error == false)
               {
                  var cantImagenes = 0;
                  var id_reclamo = 0;
                  //FETCH
                  fetch('http://localhost:8080/apitp/agregarReclamo?codigo=' + edificio + '&piso=' + piso + '&numero=' + numero + '&documento=' + sessionStorage.getItem("documento") + '&ubicacion=' + ubicacion + '&descripcion=' + descripcion, {
                     method: 'POST'
                  }).then((res) => res.json()).then((json) => {

                     if (json != null && json > 0) 
                     {
                        id_reclamo = json;
                        var i = 0;
                        var imagenes = document.getElementById('imagenes');
                        imagenes = imagenes.files;
                        while(i < imagenes.length)
                        {
                           var imagen = imagenes[i];
                           console.log(imagen);
                           var blob = imagen.slice(0, imagen.size, 'image/png'); 
                           imagen = new File([blob], id_reclamo + '_' + imagen.name , {type: 'image/png'});
                              var Ftp = {
                                 createCORSRequest: function (method, url) {
                                    var xhr = new XMLHttpRequest();
                                    if ("withCredentials" in xhr) {
                                       // Check if the XMLHttpRequest object has a "withCredentials" property.
                                       // "withCredentials" only exists on XMLHTTPRequest2 objects.
                                       xhr.open(method, url, true);
                                    } else {
                                       // Otherwise, CORS is not supported by the browser.
                                       xhr = null;
                                    }
                                    return xhr;
                                 },
                                 upload: function(token, file) {
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
                              callback: function(file)
                              {      
                                 var nombreArchivo = file.name;
                                 var data = nombreArchivo.split('_');
                                 var numeroReclamo = data.shift();
                                 var nombreYExtension = data.join('_');
                           
                                 nombreYExtension = nombreYExtension.split('.');
                           
                                 fetch('http://localhost:8080/apitp/agregarImagenAReclamo?numero=' + numeroReclamo + "&direccion=" + nombreYExtension[0] + "&tipo=" + nombreYExtension[1],
                                 {method: 'POST'})
                                 .then(response => {
                                    if (response.status != 200) 
                                    {
                                       alert("Error al cargar imagen");
                                    } 
                                    else
                                    { 
                                        cantImagenes++;
                                        if (cantImagenes == imagenes.length)
                                        {
                                           alert("Reclamo creado.");
                                           window.location = '/reclamo/' + id_reclamo;
                                        }
                                     }
                                 }).catch((error) =>{
                                 alert("Error en API" + error);
                                 });
                              }
                              };                     
      
      
                              Ftp.upload('fae789eb-544f-451d-8601-361249ff8f0a', imagen);		      



                              i++;
                        }
                        
                        if(i == 0)
                        {
                           alert("Reclamo creado.");
                           window.location = '/reclamo/' + id_reclamo;

                        }
                     }
                     else
                     {
                        alert("Error al crear el reclamo.");
                     }
                     

                  })
                  .catch(error => {
                     alert("ERROR");
                     console.log("Error.", error);
                  });

               }
               else
               {
                  alert("El archivo subido debe ser una imagen jpg, jpeg, png o gif.");
               }
            }
         }
      }
      else
      {
         alert("Debe seleccionar un edificio");
      }
     
   }

   handleChangeInput = event => {
      const { value, maxLength } = event.target;
      const message = value.slice(0, maxLength);
  
      this.setState({
        form: {
          message
        }
      });
    };
  
  render() {
      var  {isLoaded, edificios, unidadesListadas} =this.state;

      if(!isLoaded) {
         return <div className="container">Cargando...</div>
      }
      else
      {
         return (
            <div className="container">
               <form id="frm-reclamo" onSubmit={this.handleSubmit}>
                  <h2>Crear Reclamo</h2>
                  <div className="row">
                     
                     <div className="col-md-2">
                        <label>Edificio</label>
                     </div>
                     <div className="col-md-10">
                        <select id="listaEdificios" onChange={this.handleChange}>
                           <option value="-1">Seleccione un edificio</option>

                           {
                              edificios.map(item => (
                                 <option value={item.codigo}>{item.nombre}</option>

                              ))
                           }
                        </select>
                     </div> 

                     <div className="col-md-2">
                        <label>Unidad/Ubicación</label>
                     </div>
                     <div className="col-md-10">
                        <select id="listaUnidades" onChange={this.handleChange}>
                           <option value="-1">Seleccione una unidad</option>
                           <option value="0" id="espacioComun" style={{display:'none'}}>Espacio común</option> 
                           {
                              unidadesListadas.map(item => (
                              <option value={item.identificador}> {item.piso}° {item.numero}</option>

                              ))
                           } 
                        </select>
                     </div> 
                     <div id="ubicacionMostrar" style={{display: 'none'}}>
                        <div className="col-md-2">
                           <label>Nombre de la ubicación</label>
                        </div>
                        <div className="col-md-10">
                        <input type="text" id="ubicacion" name="ubicacion" placeholder="Ubicacion"/>
                        </div> 
                     </div>
                     <div className="col-md-2">
                        <label>Descripción</label>
                     </div>
                     <div className="col-md-10">
                        <textarea style={{resize: "none"}}  onChange={this.handleChangeInput} name="descripcion" id="descripcion" maxLength="800" rows="10" cols="50" placeholder="Descripcion"></textarea>
                     </div> 
                     <div className="col-md-2">
                        <label>Imagenes</label>
                     </div>
                     <div className="col-md-10">
                        <input type = "file" name = "file" id="imagenes" size = "50" multiple/>
                     </div> 

                  </div>
                  <div className="row divBotones">
                     <button type="submit">Crear reclamo</button>
                  </div>


               </form>
            </div>
         );

      }
   }
}
export default ReclamoForm;


