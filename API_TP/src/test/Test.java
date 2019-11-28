package test;

import java.util.ArrayList;
import java.util.List;

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

public class Test {

	public static void main(String[] args) {
		
		
		/*
		 * Método getEdificios.
		 */
		/*List<EdificioView> edificios = Controlador.getInstancia().getEdificios();
				
				for(EdificioView e : edificios)
				{
					System.out.println(e.getNombre() + " - " + e.getDireccion());		
				}
		*/
		
		/*
		 * Método getAllReclamos
		 */
		List<ReclamoView> reclamos = Controlador.getInstancia().getAllReclamos();
		for(ReclamoView r : reclamos)
		{
			System.out.println(r.getNumero() + " - " + r.getDescripcion());		
		}		
		/*
		 * Método getPersonas.
		 */
		/*List<PersonaView> personas = Controlador.getInstancia().getPersonas();
		for(PersonaView p : personas) {
			System.out.println(p.getNombre());
		}
		*/
		
		/*
		 * Método getInquilinos.
		 */
		/*List<PersonaView> inquilinos= Controlador.getInstancia().getInquilinos();
		for(PersonaView i : inquilinos){
			System.out.println(i.getNombre());
		}
		*/
		
		
		/*
		 * Método getUnidadesPorEdificio.
		 */
		/*List<EdificioView> edificios = Controlador.getInstancia().getEdificios();
				
				for(EdificioView e : edificios)
				{		
					try {
						List<UnidadView> unidades = Controlador.getInstancia().getUnidadesPorEdificio(e.getCodigo());
						for(UnidadView u : unidades){
							System.out.println(u.getEdificio().getNombre() + " - " + u.toString());
						}
					} catch (EdificioException e1) {
						e1.getMessage();

					} catch (UnidadException e1) {
						e1.getMessage();
					}
				}
		*/
		
		
		
		/*
		 * Método habilitadosPorEdificio.
		 */
		/*List<PersonaView> habilitados = null;
		try {
			habilitados= Controlador.getInstancia().habilitadosPorEdificio(1);
		} catch (EdificioException e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}
		int cant=0;
		for(PersonaView pers : habilitados) {
		System.out.println(pers.getNombre());
		cant++;
		}
		System.out.println(cant);
		*/
		
		
		/*
		 * Método dueniosPorEdificio.
		 */
		/*List<PersonaView> duenios = null;
		try {
			duenios = Controlador.getInstancia().dueniosPorEdificio(1);
		} catch (EdificioException e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}
		for(PersonaView p : duenios) {
			System.out.println(p.getNombre());
		}*/
		
		
		/*
		 * Método habitantesPorEdificio.
		 */
		/*List<PersonaView> personas;
		try {
			personas = Controlador.getInstancia().habitantesPorEdificio(2);
			for(PersonaView p : personas) 
			{
				System.out.println(p.toString());
			}

		} catch (EdificioException e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}
		*/
		
		/*
		 * Método dueniosPorUnidad.
		 */
		/*List<PersonaView> duenios = null;
		try {
			duenios = Controlador.getInstancia().dueniosPorUnidad(1, "2", "4");
		} catch (UnidadException e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}
		for(PersonaView p : duenios) {
			System.out.println(p.getNombre());
		}
		*/

		
		/*
		 * Método inquilinosPorUnidad.
		 */
		/*List<PersonaView> inquilinos = null;
		try {
			inquilinos = Controlador.getInstancia().inquilinosPorUnidad(1, "2", "4");
		} catch (UnidadException e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}
		for(PersonaView p : inquilinos) {
			System.out.println(p.getNombre());
		}
		*/
		
		
		/*
		 * Método transferirUnidad.
		 */
		/*try {
			Controlador.getInstancia().transferirUnidad(1, "2", "4", "DNI30829463");
		} catch (UnidadException | PersonaException e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}*/
		
		
		
		/*
		 * Método agregarDuenioUnidad
		 */
		/*try {
			Controlador.getInstancia().agregarDuenioUnidad(1, "2", "4", "DNI30829463");
		} catch (UnidadException | PersonaException e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}*/
		
		
		/*
		 * Método alquilarUnidad.
		 */
		/*try {
		Controlador.getInstancia().alquilarUnidad(1, "10", "5", "DNI30600888");
		} catch (UnidadException | PersonaException e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}
		 */
		
		/*
		 * Método agregarInquilinoUnidad.
		 */
		/*try {
			Controlador.getInstancia().agregarInquilinoUnidad(1, "10", "3", "DNI30600888");
			System.out.println("OK");
		} catch (UnidadException | PersonaException e1) {
			// TODO Auto-generated catch block
			e1.getMessage();
		}*/
		
		
		/*
		 * Método liberarUnidad
		 */
			/*try {
			Controlador.getInstancia().liberarUnidad(1, "10", "6");
			System.out.println("OK");
		} catch (UnidadException e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}*/
		
		
		/*
		 * Método habitarUnidad.
		 */
			/*try {
			Controlador.getInstancia().habitarUnidad(1, "10", "4");
		} catch (UnidadException e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}*/
		
		
		/*
		 * Método agregarPersona.
		 */
		/*try {
			Controlador.getInstancia().agregarPersona("DNI30600123", "PEREZ, JUAN", "jperez", "1234");
		} catch (PersonaException e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}
		*/
		/*
		 * Método eliminarPersona.
		 */
			/*try {
			Controlador.getInstancia().eliminarPersona("DNI30600123");
		} catch (PersonaException e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}*/
		
		
		/*
		 * Método reclamosPorEdificio.
		 */
		/*List<ReclamoView> reclamosPorEdificio = new ArrayList<ReclamoView>();
		reclamosPorEdificio= Controlador.getInstancia().reclamosPorEdificio(1);
		for(ReclamoView r : reclamosPorEdificio) {
				System.out.println(r.getNumero());
		}*/
		
		/*
		 * Método reclamosPorUnidad.
		 */
		/*List<ReclamoView> rec= Controlador.getInstancia().reclamosPorUnidad(1, "2","4");
		for(ReclamoView r : rec) {
			System.out.println(r.getDescripcion());
		}*/
		
		/*
		 * Método reclamosPorNumero
		 */
		/*ReclamoView res = Controlador.getInstancia().reclamosPorNumero(1);
		System.out.println(res.getDescripcion());
		 */
		
		/*
		 * Método reclamosPorPersona.
		 */
		/*List<ReclamoView> res = Controlador.getInstancia().reclamosPorPersona("DNI30829463");
		for(ReclamoView r: res){
			System.out.println(r.getDescripcion());
		}
		*/
		
		/*
		 * Método agregarReclamo.
		 */
		/*try {
		Controlador.getInstancia().agregarReclamo(1, "2", "4", "DNI30829463", "Humedad", "Hay humedad en el techo del baño.");
		System.out.println("OK - Reclamo");
			
		} catch (EdificioException e) {
			// TODO Auto-generated catch block
			e.getMessage();
		} catch (UnidadException e) {
			// TODO Auto-generated catch block
			e.getMessage();
		} catch (PersonaException e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}		
		*/
		
		/*
		 * Método agregarImagenAReclamo.
		 */
		/*try {
		Controlador.getInstancia().agregarImagenAReclamo(1, "escalera2", "jpg");
		} catch (ReclamoException e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}
		System.out.println("OK - Imagen");
		 */
		
		/*
		 * Método cambiarEstado.
		 */
		
		/*try {
			Controlador.getInstancia().cambiarEstado(1, Estado.enProceso);
		} catch (ReclamoException e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}*/
		
	}

}
