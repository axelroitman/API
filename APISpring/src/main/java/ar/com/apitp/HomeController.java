package ar.com.apitp;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import controlador.Controlador;
import exceptions.EdificioException;
import exceptions.PersonaException;
import exceptions.ReclamoException;
import exceptions.UnidadException;
import views.EdificioView;
import views.Estado;
import views.PersonaView;
import views.ReclamoView;
import views.UnidadView;
/*import exceptions.MateriaException;
import view.MateriaView;*/

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	//reclamosPorPersona
	@RequestMapping(value = "/getEdificiosParaReclamosUsuario", method = RequestMethod.GET, produces = {"application/json"})
	public @ResponseBody<json> String getEdificiosParaReclamosUsuario(@RequestParam(value="documento", required=true) String documento) throws JsonProcessingException {
		/*List<EdificioView> edificios = Controlador.getInstancia().getEdificios();
		List<EdificioView> listado = new ArrayList<EdificioView>();
		for(EdificioView ed : edificios)
		{
			List<PersonaView> hab;
			try {
				hab = Controlador.getInstancia().habilitadosPorEdificio(ed.getCodigo());
				boolean aparecio = false;
				for(PersonaView p : hab)
				{
					if(p.getDocumento().equals(documento) && aparecio == false) 
					{
						aparecio = true;
						listado.add(ed);
					}
				}

			} catch (EdificioException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}*/
		List<UnidadView> listado = new ArrayList<UnidadView>();
		listado = Controlador.getInstancia().getUnidadesParaReclamosUsuario(documento);
		
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(listado);
	}


	@RequestMapping(value = "/getPersonaPorUsuario", method = RequestMethod.GET, produces = {"application/json"})
	public @ResponseBody<json> String getPersonaPorUsuario(@RequestParam(value="usuario", required=true) String usuario, @RequestParam(value="password", required=true) String password) throws JsonProcessingException {
		//ResponseBody<json>: Aclara que el String guarda un JSON
		//ObjectMapper: Es una clase de Jackson que permite convertir una colección a un JSON usando el método writeValueAsString
			PersonaView persona =  Controlador.getInstancia().getPersonaPorUsuario(usuario, password);
			ObjectMapper mapper = new ObjectMapper();
			return mapper.writeValueAsString(persona);

	}
	
	@RequestMapping(value = "/getPersonaPorDocumento", method = RequestMethod.GET, produces = {"application/json"})
	public @ResponseBody<json> String getPersonaPorDocumento(@RequestParam(value="documento", required=true) String documento) throws JsonProcessingException {
			PersonaView persona =  Controlador.getInstancia().getPersonaPorDocumento(documento);
			ObjectMapper mapper = new ObjectMapper();
			return mapper.writeValueAsString(persona);

	}

	
	@RequestMapping(value = "/getEdificios", method = RequestMethod.GET, produces = {"application/json"})
	public @ResponseBody<json> String getEdificios() throws JsonProcessingException {
		List<EdificioView> edificios = Controlador.getInstancia().getEdificios();
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(edificios);
	}
	
	@RequestMapping(value = "/getAllReclamos", method = RequestMethod.GET, produces = {"application/json"})
	public @ResponseBody<json> String getAllReclamos() throws JsonProcessingException {
		List<ReclamoView> reclamos = Controlador.getInstancia().getAllReclamos();
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(reclamos);
	}
	
	@RequestMapping(value = "/getPersonas", method = RequestMethod.GET, produces = {"application/json"})
	public @ResponseBody<json> String getPersonas() throws JsonProcessingException {
		List<PersonaView> personas = Controlador.getInstancia().getPersonas();
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(personas);
	}
	
	@RequestMapping(value = "/getInquilinos", method = RequestMethod.GET, produces = {"application/json"})
	public @ResponseBody<json> String getInquilinos() throws JsonProcessingException {
		List<PersonaView> inquilinos = Controlador.getInstancia().getInquilinos();
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(inquilinos);
	}
	
	//getUnidadesPorEdificio
	@RequestMapping(value = "/getUnidadesPorEdificio", method = RequestMethod.GET, produces = {"application/json"})
	public @ResponseBody<json> String getUnidadesPorEdificio(@RequestParam(value="codigo", required=true) int codigo) throws JsonProcessingException {
		//ResponseBody<json>: Aclara que el String guarda un JSON
		//ObjectMapper: Es una clase de Jackson que permite convertir una colección a un JSON usando el método writeValueAsString
		try {
			List<UnidadView> unidades =  Controlador.getInstancia().getUnidadesPorEdificio(codigo);
			ObjectMapper mapper = new ObjectMapper();
			return mapper.writeValueAsString(unidades);

		} catch (UnidadException e) {
			return e.getMessage();
		} catch (EdificioException e) {
			return e.getMessage();
		}
	}
	
	//getHabilitadosPorEdificio
		@RequestMapping(value = "/getHabilitadosPorEdificio", method = RequestMethod.GET, produces = {"application/json"})
		public @ResponseBody<json> String getHabilitadosPorEdificio(@RequestParam(value="codigo", required=true) int codigo) throws JsonProcessingException {
			//ResponseBody<json>: Aclara que el String guarda un JSON
			//ObjectMapper: Es una clase de Jackson que permite convertir una colección a un JSON usando el método writeValueAsString
			try {
				List<PersonaView> personas =  Controlador.getInstancia().habilitadosPorEdificio(codigo);
				ObjectMapper mapper = new ObjectMapper();
				return mapper.writeValueAsString(personas);

			} catch (EdificioException e) {
				return e.getMessage();
			}
		}
		
		//dueniosPorEdificio
		@RequestMapping(value = "/getDueniosPorEdificio", method = RequestMethod.GET, produces = {"application/json"})
		public @ResponseBody<json> String getDueniosPorEdificio(@RequestParam(value="codigo", required=true) int codigo) throws JsonProcessingException {
			//ResponseBody<json>: Aclara que el String guarda un JSON
			//ObjectMapper: Es una clase de Jackson que permite convertir una colección a un JSON usando el método writeValueAsString
			try {
				List<PersonaView> personas =  Controlador.getInstancia().dueniosPorEdificio(codigo);
				ObjectMapper mapper = new ObjectMapper();
				return mapper.writeValueAsString(personas);

			} catch (EdificioException e) {
				return e.getMessage();
			}
		}
		//habitantesPorEdificio
				@RequestMapping(value = "/getHabitantesPorEdificio", method = RequestMethod.GET, produces = {"application/json"})
				public @ResponseBody<json> String getHabitantesPorEdificio(@RequestParam(value="codigo", required=true) int codigo) throws JsonProcessingException {
					//ResponseBody<json>: Aclara que el String guarda un JSON
					//ObjectMapper: Es una clase de Jackson que permite convertir una colección a un JSON usando el método writeValueAsString
					try {
						List<PersonaView> personas =  Controlador.getInstancia().habitantesPorEdificio(codigo);
						ObjectMapper mapper = new ObjectMapper();
						return mapper.writeValueAsString(personas);

					} catch (EdificioException e) {
						return e.getMessage();
					}
				}
				
		//dueniosPorUnidad
				@RequestMapping(value = "/getDueniosPorUnidad", method = RequestMethod.GET, produces = {"application/json"})
				public @ResponseBody<json> String getDueniosPorUnidad(@RequestParam(value="codigo", required=true) int codigo, @RequestParam(value="piso", required=true) String piso, @RequestParam(value="numero", required=true) String numero) throws JsonProcessingException {
					//ResponseBody<json>: Aclara que el String guarda un JSON
					//ObjectMapper: Es una clase de Jackson que permite convertir una colección a un JSON usando el método writeValueAsString
					try {
						List<PersonaView> personas =  Controlador.getInstancia().dueniosPorUnidad(codigo, piso, numero);
						ObjectMapper mapper = new ObjectMapper();
						return mapper.writeValueAsString(personas);

					} catch (UnidadException e) {
						return e.getMessage();
					}
				}
				
		//inquilinosPorUnidad
				@RequestMapping(value = "/getInquilinosPorUnidad", method = RequestMethod.GET, produces = {"application/json"})
				public @ResponseBody<json> String getInquilinosPorUnidad(@RequestParam(value="codigo", required=true) int codigo, @RequestParam(value="piso", required=true) String piso, @RequestParam(value="numero", required=true) String numero) throws JsonProcessingException {
					//ResponseBody<json>: Aclara que el String guarda un JSON
					//ObjectMapper: Es una clase de Jackson que permite convertir una colección a un JSON usando el método writeValueAsString
					try {
						List<PersonaView> personas =  Controlador.getInstancia().inquilinosPorUnidad(codigo, piso, numero);
						ObjectMapper mapper = new ObjectMapper();
						return mapper.writeValueAsString(personas);

					} catch (UnidadException e) {
						return e.getMessage();
					}
				}
				
		//transferirUnidad
				@RequestMapping(value = "/transferirUnidad", method = RequestMethod.PUT)
				public ResponseEntity<Void> transferirUnidad(@RequestParam(value="codigo", required=true) int codigo, @RequestParam(value="piso", required=true) String piso, @RequestParam(value="numero", required=true) String numero, @RequestParam(value="documento", required=true) String documento) {
					//ResponseBody<json>: Aclara que el String guarda un JSON
					//ObjectMapper: Es una clase de Jackson que permite convertir una colección a un JSON usando el método writeValueAsString
						try {
							Controlador.getInstancia().transferirUnidad(codigo, piso, numero, documento);
							return new ResponseEntity<Void>(HttpStatus.OK);																										
							
						} catch (UnidadException e) {
							// TODO Auto-generated catch block
							//e.getMessage();
							return new ResponseEntity<Void>(HttpStatus.CONFLICT);																										
							
						} catch (PersonaException e) {
							// TODO Auto-generated catch block
							//e.getMessage();
							return new ResponseEntity<Void>(HttpStatus.CONFLICT);																										

						}
					
				}
	  //agregarDuenioUnidad
				@RequestMapping(value = "/agregarDuenioUnidad", method = RequestMethod.POST)
				public ResponseEntity<Void> agregarDuenioUnidad(@RequestParam(value="codigo", required=true) int codigo, @RequestParam(value="piso", required=true) String piso, @RequestParam(value="numero", required=true) String numero, @RequestParam(value="documento", required=true) String documento) {
					//ResponseBody<json>: Aclara que el String guarda un JSON
					//ObjectMapper: Es una clase de Jackson que permite convertir una colección a un JSON usando el método writeValueAsString
						try {
							Controlador.getInstancia().agregarDuenioUnidad(codigo, piso, numero, documento);
							return new ResponseEntity<Void>(HttpStatus.OK);																										
						
						} catch (UnidadException e) {
							// TODO Auto-generated catch block
							//e.getMessage();
							return new ResponseEntity<Void>(HttpStatus.CONFLICT);																										

						} catch (PersonaException e) {
							// TODO Auto-generated catch block
							//e.getMessage();
							return new ResponseEntity<Void>(HttpStatus.CONFLICT);																										

						}
					
				}
		//alquilarUnidad
				@RequestMapping(value = "/alquilarUnidad", method = RequestMethod.PUT)
				public ResponseEntity<Void> alquilarUnidad(@RequestParam(value="codigo", required=true) int codigo, @RequestParam(value="piso", required=true) String piso, @RequestParam(value="numero", required=true) String numero, @RequestParam(value="documento", required=true) String documento) {
					//ResponseBody<json>: Aclara que el String guarda un JSON
					//ObjectMapper: Es una clase de Jackson que permite convertir una colección a un JSON usando el método writeValueAsString
						try {
							Controlador.getInstancia().alquilarUnidad(codigo, piso, numero, documento);
							return new ResponseEntity<Void>(HttpStatus.OK);																										
						} catch (UnidadException e) {
							// TODO Auto-generated catch block
							//e.getMessage();
							return new ResponseEntity<Void>(HttpStatus.CONFLICT);																										
							
						} catch (PersonaException e) {
							// TODO Auto-generated catch block
							//e.getMessage();
							return new ResponseEntity<Void>(HttpStatus.CONFLICT);																										

						}
					
				}
		//agregarInquilinoUnidad
				@RequestMapping(value = "/agregarInquilinoUnidad", method = RequestMethod.POST)
				public ResponseEntity<Void> agregarInquilinoUnidad(@RequestParam(value="codigo", required=true) int codigo, @RequestParam(value="piso", required=true) String piso, @RequestParam(value="numero", required=true) String numero, @RequestParam(value="documento", required=true) String documento) {
					//ResponseBody<json>: Aclara que el String guarda un JSON
					//ObjectMapper: Es una clase de Jackson que permite convertir una colección a un JSON usando el método writeValueAsString
						try {
							Controlador.getInstancia().agregarInquilinoUnidad(codigo, piso, numero, documento);
							return new ResponseEntity<Void>(HttpStatus.CREATED);																			
							
						} catch (UnidadException e) {
							// TODO Auto-generated catch block
							//e.getMessage();
							return new ResponseEntity<Void>(HttpStatus.CONFLICT);																			
							
						} catch (PersonaException e) {
							// TODO Auto-generated catch block
							//e.getMessage();
							return new ResponseEntity<Void>(HttpStatus.CONFLICT);																			

						}
					
				}
		//liberarUnidad
				@RequestMapping(value = "/liberarUnidad", method = RequestMethod.PUT)
				public ResponseEntity<Void> liberarUnidad(@RequestParam(value="codigo", required=true) int codigo, @RequestParam(value="piso", required=true) String piso, @RequestParam(value="numero", required=true) String numero) {
					//ResponseBody<json>: Aclara que el String guarda un JSON
					//ObjectMapper: Es una clase de Jackson que permite convertir una colección a un JSON usando el método writeValueAsString
						try {
							Controlador.getInstancia().liberarUnidad(codigo, piso, numero);
							return new ResponseEntity<Void>(HttpStatus.OK);																			
							
						} catch (UnidadException e) {
							// TODO Auto-generated catch block
							//e.getMessage();
							return new ResponseEntity<Void>(HttpStatus.CONFLICT);																			
							
						} 
					
				}
		//habitarUnidad
				@RequestMapping(value = "/habitarUnidad", method = RequestMethod.PUT)
				public ResponseEntity<Void> habitarUnidad(@RequestParam(value="codigo", required=true) int codigo, @RequestParam(value="piso", required=true) String piso, @RequestParam(value="numero", required=true) String numero) {
					//ResponseBody<json>: Aclara que el String guarda un JSON
					//ObjectMapper: Es una clase de Jackson que permite convertir una colección a un JSON usando el método writeValueAsString
						try {
							Controlador.getInstancia().habitarUnidad(codigo, piso, numero);
							return new ResponseEntity<Void>(HttpStatus.OK);																			
						} catch (UnidadException e) {
							// TODO Auto-generated catch block
							//e.getMessage();
							return new ResponseEntity<Void>(HttpStatus.CONFLICT);																			
						} 
					
				}
		//agregarPersona
				@RequestMapping(value = "/agregarPersona", method = RequestMethod.POST)
				public ResponseEntity<Void> agregarPersona(@RequestParam(value="documento", required=true) String documento, @RequestParam(value="nombre", required=true) String nombre, @RequestParam(value="apellido", required=true) String apellido, @RequestParam(value="usuario", required=true) String usuario, @RequestParam(value="password", required=true) String password) {
					//ResponseBody<json>: Aclara que el String guarda un JSON
					//ObjectMapper: Es una clase de Jackson que permite convertir una colección a un JSON usando el método writeValueAsString
					try {
						
						nombre = apellido + ", " + nombre;
						Controlador.getInstancia().agregarPersona(documento, nombre, usuario, password);
						return new ResponseEntity<Void>(HttpStatus.CREATED);												
					} catch (PersonaException e) {
						return new ResponseEntity<Void>(HttpStatus.CONFLICT);												
						
					}
					
				}
				
		//eliminarPersona
				@RequestMapping(value = "/eliminarPersona", method = RequestMethod.DELETE)
				public ResponseEntity<Void> eliminarPersona(@RequestParam(value="documento", required=true) String documento) {
					//ResponseBody<json>: Aclara que el String guarda un JSON
					//ObjectMapper: Es una clase de Jackson que permite convertir una colección a un JSON usando el método writeValueAsString
					try {
						Controlador.getInstancia().eliminarPersona(documento);
						return new ResponseEntity<Void>(HttpStatus.OK);						
					} catch (PersonaException e) {
						return new ResponseEntity<Void>(HttpStatus.CONFLICT);												
						// TODO Auto-generated catch block
						//e.getMessage();
					}
					
				}
		//reclamosPorEdificio
				@RequestMapping(value = "/getReclamosPorEdificio", method = RequestMethod.GET, produces = {"application/json"})
				public @ResponseBody<json> String getReclamosPorEdificio(@RequestParam(value="codigo", required=true) int codigo) throws JsonProcessingException {
					List<ReclamoView> reclamos = Controlador.getInstancia().reclamosPorEdificio(codigo);
					ObjectMapper mapper = new ObjectMapper();
					return mapper.writeValueAsString(reclamos);
				}
		//reclamosPorUnidad
				@RequestMapping(value = "/getReclamosPorUnidad", method = RequestMethod.GET, produces = {"application/json"})
				public @ResponseBody<json> String getReclamosPorUnidad(@RequestParam(value="codigo", required=true) int codigo, @RequestParam(value="piso", required=true) String piso, @RequestParam(value="numero", required=true) String numero) throws JsonProcessingException {
					//ResponseBody<json>: Aclara que el String guarda un JSON
					//ObjectMapper: Es una clase de Jackson que permite convertir una colección a un JSON usando el método writeValueAsString
						List<ReclamoView> reclamos =  Controlador.getInstancia().reclamosPorUnidad(codigo, piso, numero);
						ObjectMapper mapper = new ObjectMapper();
						return mapper.writeValueAsString(reclamos);

				}
		//reclamosPorNumero
				@RequestMapping(value = "/getReclamosPorNumero", method = RequestMethod.GET, produces = {"application/json"})
				public @ResponseBody<json> String getReclamosPorNumero(@RequestParam(value="numero", required=true) int numero) throws JsonProcessingException {
					ReclamoView reclamo = Controlador.getInstancia().reclamosPorNumero(numero);
					ObjectMapper mapper = new ObjectMapper();
					return mapper.writeValueAsString(reclamo);
				}
		//reclamosPorPersona
				@RequestMapping(value = "/getReclamosPorPersona", method = RequestMethod.GET, produces = {"application/json"})
				public @ResponseBody<json> String getReclamosPorPersona(@RequestParam(value="documento", required=true) String documento) throws JsonProcessingException {
					List<ReclamoView> reclamos = Controlador.getInstancia().reclamosPorPersona(documento);
					ObjectMapper mapper = new ObjectMapper();
					return mapper.writeValueAsString(reclamos);
				}
				
		//agregarReclamo
				@RequestMapping(value = "/agregarReclamo", method = RequestMethod.POST)
				public ResponseEntity <Void> agregarReclamo(@RequestParam(value="codigo", required=true) int codigo, @RequestParam(value="piso", required=true) String piso, @RequestParam(value="numero", required=true) String numero, @RequestParam(value="documento", required=true) String documento, @RequestParam(value="ubicacion", required=true) String ubicacion, @RequestParam(value="descripcion", required=true) String descripcion) {
					try {
						Controlador.getInstancia().agregarReclamo(codigo, piso, numero, documento, ubicacion, descripcion);
						return new ResponseEntity<Void>(HttpStatus.CREATED);
					} catch (EdificioException e) {
						return new ResponseEntity<Void>(HttpStatus.CONFLICT);
						//e.getMessage();
					} catch (UnidadException e) {
						return new ResponseEntity<Void>(HttpStatus.CONFLICT);						
						//e.getMessage();
					} catch (PersonaException e) {
						return new ResponseEntity<Void>(HttpStatus.CONFLICT);						
						//e.getMessage();
					}
				}
		//cambiarEstado
				@RequestMapping(value = "/cambiarEstado", method = RequestMethod.PUT)
				public ResponseEntity <Void> cambiarEstado(@RequestParam(value="numero", required=true) int numero, @RequestParam(value="estado", required=true) int estado) {
					try {
						Estado est = null;
						if(estado == 1) {
							est = Estado.nuevo;
						}
						else if (estado == 2) {
							est= Estado.abierto;
						}
						else if(estado == 3) {
							est= Estado.enProceso;
						}
						else if(estado == 4) {
							est= Estado.desestimado;
						}
						else if(estado == 5) {
							est= Estado.anulado;
						}
						else if(estado == 6) {
							est= Estado.terminado;
						}
						Controlador.getInstancia().cambiarEstado(numero, est);
						return new ResponseEntity<Void>(HttpStatus.OK);
						
					} catch (ReclamoException e) {
						return new ResponseEntity<Void>(HttpStatus.CONFLICT);
						
					}

				}
				
		//agregarImagenAReclamo
				@RequestMapping(value = "/agregarImagenAReclamo", method = RequestMethod.POST)
				public ResponseEntity <Void> agregarImagenAReclamo(@RequestParam(value="numero", required=true) int numero, @RequestParam(value="direccion", required=true) String direccion, @RequestParam(value="tipo", required=true) String tipo) {
					try {
						Controlador.getInstancia().agregarImagenAReclamo(numero, direccion, tipo);
						return new ResponseEntity<Void>(HttpStatus.OK);
						
					} catch (ReclamoException e) {
						return new ResponseEntity<Void>(HttpStatus.CONFLICT);
						
					}

				}				
				
}
