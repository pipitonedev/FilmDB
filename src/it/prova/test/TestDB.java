package it.prova.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import it.prova.dao.FilmDAO;
import it.prova.dao.RegistaDAO;
import it.prova.model.Film;
import it.prova.model.Regista;

public class TestDB {

	public static void main(String[] args) {

		FilmDAO filmDAOInstance = new FilmDAO();
		RegistaDAO registaDAOInstance = new RegistaDAO();
		
		// PROVA ALCUNI INSERT //
		
//		registaDAOInstance.insert(new Regista("Quentin", "Tarantino", 10));
//
//		Film film = new Film("Batman", "Thriller", 90);
//		film.setRegista(new Regista("Cristopher", "Nolan", 3));
//		filmDAOInstance.insertCompleto(film);
//		film = new Film("Big Fish", "Avventura", 120);
//		film.setRegista(new Regista("Tim", "Burton", 7));
//		filmDAOInstance.insertCompleto(film);
//		
//		List<Film> listaFilm = filmDAOInstance.list();
//		for (Film elencoFilm : listaFilm) {
////			System.out.println(elencoFilm);
//		}
//
//		// UPDATE TABELLA//
//
//		if (!listaFilm.isEmpty()) {
//			Film filmDaModificare = listaFilm.get(3);
//			System.out.println("------>Stai modificando: " + "\n" + filmDaModificare.getTitolo());
//			filmDaModificare.setTitolo("Interstellar");
//			filmDaModificare.setGenere("scifi");
//			filmDaModificare.setDurata(120);
//			filmDAOInstance.update(filmDaModificare);
//			System.out.println("------->Il nuovo film aggiunto: " + "\n" + filmDaModificare.getTitolo());
//
//		}
//		//DELETE//
//		
//		if (!listaFilm.isEmpty()) {
//			Film filmDaEliminare = listaFilm.get(0);
//			filmDAOInstance.delete(filmDaEliminare);
//			System.out.println("Hai eliminato il seguente film: " + "\n" + filmDaEliminare.getTitolo());
//		} else {
//			System.out.println("Impossibile eliminare il film!");
//		}
		
		List<Regista> cercaRegista = new ArrayList<Regista>();
		cercaRegista = registaDAOInstance.findAllRegistaConPiuOscar();
		for (Regista regista : cercaRegista) {
			System.out.println(regista.toString());
		}



	}
}
