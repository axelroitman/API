package test;

import java.util.ArrayList;
import java.util.List;

import controlador.Controlador;
import exceptions.EdificioException;
import exceptions.PersonaException;
import exceptions.UnidadException;
import modelo.Persona;
import views.PersonaView;
import views.ReclamoView;

public class Test {

	public static void main(String[] args) {
		
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
		
		
		/*try {
			Controlador.getInstancia().agregarReclamo(1, "2", "4", "DNI30829463", "Escaleras", "Patinan mucho");
			System.out.println("OK");
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
		*/
		
		List<ReclamoView> rec= Controlador.getInstancia().reclamosPorUnidad(1, "2","4");
		for(ReclamoView r : rec) {
			System.out.println(r.getDescripcion());
		}
		
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
