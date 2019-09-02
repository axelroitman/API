package test;

import java.util.List;

import controlador.Controlador;
import exceptions.EdificioException;
import modelo.Persona;
import views.EdificioView;
import views.PersonaView;

public class Test {

	public static void main(String[] args) {
		List<EdificioView> edificios = Controlador.getInstancia().getEdificios();
		List<PersonaView> personas= Controlador.getInstancia().getPersonas();
		List<Persona> inquilinos= Controlador.getInstancia().getInquilinos();
		int count=0;
		for(Persona i : inquilinos){
			System.out.println(i.toString());
			count++;
		}
		System.out.println(count);
		count=0;
		for(PersonaView p : personas){
			count++;
		}
		System.out.println(count);
		/*for(EdificioView e : edificios)
			System.out.println(e.toString());
		for (PersonaView p : personas)
				System.out.println(p.toString());
	*/
		/*ClubView club = null;
		try {
			club = new Controlador().obtenerClub(2);
			System.out.println(club.getNombre());
		} catch (ClubException e) {
			System.out.println(e.getMessage());
		}*/
		

	}

}
