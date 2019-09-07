package test;

import java.util.List;

import controlador.Controlador;
import exceptions.EdificioException;
import exceptions.UnidadException;
import modelo.Persona;
import views.EdificioView;
import views.PersonaView;
import views.UnidadView;

public class Test {

	public static void main(String[] args) {
		
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
		}
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

	}

}
