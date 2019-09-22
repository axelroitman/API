package test;

import java.util.ArrayList;
import java.util.List;

import controlador.Controlador;
import exceptions.EdificioException;
import exceptions.PersonaException;
import exceptions.ReclamoException;
import exceptions.UnidadException;
import views.Estado;
import views.PersonaView;
import views.ReclamoView;

public class Test {

	public static void main(String[] args) {
		
		/*try {
			Controlador.getInstancia().cambiarEstado(1, Estado.enProceso);
		} catch (ReclamoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		/*try {
			Controlador.getInstancia().agregarImagenAReclamo(1, "escalera2", "jpg");
		} catch (ReclamoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("OK - Imagen");
*/
		
		try {
			Controlador.getInstancia().agregarReclamo(1, "2", "4", "DNI30829463", "Humedad", "Hay humedad en el techo del baño.");
			System.out.println("OK - Reclamo");
			

		} catch (EdificioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnidadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PersonaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		
		/*try {
			Controlador.getInstancia().liberarUnidad(1, "10", "6");
			System.out.println("OK");
		} catch (UnidadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		/*List<PersonaView> personas;
		try {
			personas = Controlador.getInstancia().habitantesPorEdificio(1);
			for(PersonaView p : personas) 
			{
				System.out.println(p.toString());
			}

		} catch (EdificioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		
		/*try {
			Controlador.getInstancia().agregarPersona("DNI30600888", "PEREZ, JUAN");
		} catch (PersonaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		/*try {
			Controlador.getInstancia().habitarUnidad(1, "10", "4");
		} catch (UnidadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/

		/*
		try {
			Controlador.getInstancia().agregarInquilinoUnidad(1, "10", "3", "DNI30600888");
			System.out.println("OK");
		} catch (UnidadException | PersonaException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/

		/*try {
			Controlador.getInstancia().alquilarUnidad(1, "10", "5", "DNI30600888");
		} catch (UnidadException | PersonaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		/*try {
			Controlador.getInstancia().eliminarPersona("CI 13230978");
		} catch (PersonaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		/*try {
			List<PersonaView> dueniosActuales = Controlador.getInstancia().dueniosPorUnidad(1, "2", "4");
			for(PersonaView p : dueniosActuales)
			{
				System.out.println(p.toString());
			}
			try {
				//Controlador.getInstancia().transferirUnidad(1, "2", "4", "DNI30829463");
				Controlador.getInstancia().agregarDuenioUnidad(1, "2", "4", "DNI30829463");

				System.out.println("OK.");
			} catch (PersonaException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (UnidadException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/
		
		
		
		
		/*List<ReclamoView> rec= Controlador.getInstancia().reclamosPorUnidad(1, "2","4");
		for(ReclamoView r : rec) {
			System.out.println(r.getDescripcion());
		}*/
		
		/*List<ReclamoView> res = Controlador.getInstancia().reclamosPorPersona("DNI30829463");
		for(ReclamoView r: res){
			System.out.println(r.getDescripcion());
		}
		*/
		
		/*ReclamoView res = Controlador.getInstancia().reclamosPorNumero(3);
			System.out.println(res.getDescripcion());
		*/
		
		/*Controlador.getInstancia().agregarPersona("DNI78908978", "MIGUEL, LUIS");
		*/
		
		
		
		/*List<PersonaView> habilitados = null;
		try {
			habilitados= Controlador.getInstancia().habilitadosPorEdificio(1);
		} catch (EdificioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int cant=0;
		for(PersonaView pers : habilitados) {
		System.out.println(pers.getNombre());
		cant++;}
		System.out.println(cant);*/
		
		
		
	/*	
		List<PersonaView> duenios;
		try {
			//duenios = Controlador.getInstancia().dueniosPorEdificio(1);
			//duenios = Controlador.getInstancia().dueniosPorUnidad(1, "2", "4");
			
			duenios = Controlador.getInstancia().inquilinosPorUnidad(1, "2", "4"); //- PRUEBA DE INQUILINOS.
			for(PersonaView p : duenios) 
			{
				System.out.println(p.getNombre() + " - " + p.getDocumento() + ".");
			}
			
		}/* catch (EdificioException e) {
			// TODO Auto-generated catch block
			System.out.println("Error.");
		}*//* catch (UnidadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		/*
List<EdificioView> edificios = Controlador.getInstancia().getEdificios();
		
		for(EdificioView e : edificios)
		{
			//System.out.println(e.getNombre() + " - " + e.getDireccion());
			
			try {
				List<UnidadView> unidades = Controlador.getInstancia().getUnidadesPorEdificio(e.getCodigo());
				for(UnidadView u : unidades){
					System.out.println(u.getEdificio().getNombre() + " - " + u.toString());
				}
			} catch (EdificioException e1) {
				System.out.println("Error.");

			} catch (UnidadException e1) {
				System.out.println("Error.");
			}
		}*/
		
		
		/*
		for(EdificioView e : edificios)
			System.out.println(e.toString());
		*/
		//List<EdificioView> edificios = Controlador.getInstancia().getEdificios();
		
		/*
		List<PersonaView> personas= Controlador.getInstancia().getPersonas();
		List<Persona> inquilinos= Controlador.getInstancia().getInquilinos();
		int count=0;
		for(Persona i : inquilinos){
			System.out.println(i.getNombre());
			count++;
		}
		System.out.println(count);
		count=0;
		for(PersonaView p : personas){
			count++;
		}
		System.out.println(count);
		*/

		/*List<ReclamoView> reclamosPorEdificio = new ArrayList<ReclamoView>();
		reclamosPorEdificio= Controlador.getInstancia().reclamosPorEdificio(1);
		for(ReclamoView r : reclamosPorEdificio) {
				System.out.println(r.getNumero());
		}*/
	}

}
