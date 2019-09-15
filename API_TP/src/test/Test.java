package test;

import java.util.ArrayList;
import java.util.List;

import controlador.Controlador;
import exceptions.EdificioException;
import exceptions.PersonaException;
import exceptions.UnidadException;
import views.PersonaView;
import views.ReclamoView;

public class Test {

	public static void main(String[] args) {
		
		
		try {
			Controlador.getInstancia().agregarReclamo(1, "9", "5", "CI 13230978", "Escaleras", "Patinan mucho"); //tira error en PersonaDAO
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
