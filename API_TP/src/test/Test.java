package test;

import java.util.List;

import controlador.Controlador;
import exceptions.EdificioException;
import views.EdificioView;

public class Test {

	public static void main(String[] args) {
		List<EdificioView> edificios = Controlador.getInstancia().getEdificios();
		
		for(EdificioView e : edificios)
			System.out.println(e.toString());
	
		/*ClubView club = null;
		try {
			club = new Controlador().obtenerClub(2);
			System.out.println(club.getNombre());
		} catch (ClubException e) {
			System.out.println(e.getMessage());
		}*/
		

	}

}
