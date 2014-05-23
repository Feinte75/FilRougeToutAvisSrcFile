package test;

import exception.BadEntry;
import exception.ItemFilmAlreadyExists;
import exception.MemberAlreadyExists;
import exception.NotMember;
import avis.SocialNetwork;

public class TestsAddItemFilm {

	public static int nbTestOk=0;
	public static int nbTestFail=0;

	public static void addItemFilmBadEntryTest(SocialNetwork sn, String pseudo, String password, String titre, 
			String genre, String realisateur, String scenariste, int duree, String idTest, String messErreur){

		int nbFilms = sn.nbFilms();
		try {
			sn.addItemFilm(pseudo, password, titre, genre, realisateur, scenariste, duree);
			System.out.println ("Test " + idTest + " : " + messErreur);
			nbTestFail++;
		}
		catch (BadEntry e) {
			if (sn.nbFilms() != nbFilms) {
				System.out.println("Test " + idTest + " : l'exception BadEntry a bien été levée mais le nombre de films a été modifié");
				nbTestFail++;
				return;
			}
			nbTestOk++;
		}
		catch (Exception e) {
			System.out.println ("Test " + idTest + " : exception non prÃ©vue. " + e);
			e.printStackTrace();
			nbTestFail++;
		}

	}
	
	public static void addItemFilmOKTest (SocialNetwork sn, String pseudo, String password, String titre, String genre, String realisateur, String scenariste, int duree, String idTest){
		int nbFilms = sn.nbFilms();
		try{
			sn.addItemFilm(pseudo, password, titre, genre, realisateur, scenariste, duree);
			if (sn.nbFilms() != nbFilms+1){
				System.out.println("Test " + idTest + " :  le nombre de films n'a pas été correctement incrémenté");
				nbTestFail++;
				return;
			}
			nbTestOk++;
		}
		catch (Exception e) {
			System.out.println ("Test " + idTest + " : exception non prévue. " + e);
			e.printStackTrace();
			nbTestFail++;
		}
	}
	
	public static void addItemFilmAlreadyExistsTest (SocialNetwork sn, String pseudo, String password, String titre, String genre, String realisateur, String scenariste, int duree, String idTest, String messErreur){
		int nbFilms = sn.nbFilms();
		try {
			sn.addItemFilm(pseudo, password, titre, genre, realisateur, scenariste, duree);
			System.out.println ("Test " + idTest + " : " + messErreur);
			nbTestFail++;
		}
		catch (ItemFilmAlreadyExists e) {
			if (sn.nbFilms() != nbFilms){
				System.out.println("Test " + idTest + " : l'exception ItemFilmAlreadyExists a bien été levée mais le nombre de films a été modifié");
				nbTestFail++;
				return;
			}
			nbTestOk++;
		}
		catch (Exception e) {
			System.out.println ("Test " + idTest + " : exception non prévue. " + e); 
			e.printStackTrace();
			nbTestFail++;
		}
	}

	public static void addItemFilmNotMember (SocialNetwork sn, String pseudo, String password, String titre, String genre, String realisateur, String scenariste, int duree, String idTest, String messErreur){
		int nbFilms = sn.nbFilms();
		try{
			sn.addItemFilm(pseudo, password, titre, genre, realisateur, scenariste, duree);
			nbTestFail++;
		}
		catch (NotMember e){
			if (sn.nbFilms() != nbFilms){
				System.out.println("Test " + idTest + " : l'exception NotMember a bien été levée mais le nombre de membres a été modifié");
				nbTestFail++;
				return;
			}
			nbTestOk++;
		}
		catch (Exception e){
			System.out.println ("Test " + idTest + " : exception non prévue. " + e); 
			e.printStackTrace();
			nbTestFail++;
		}
	}

	public static void main(String[] args){

		SocialNetwork sn = new SocialNetwork();

		int nbMembres = 0;
		int nbBooks= 0;

		System.out.println("Tests  ajouter des films au réseau social  ");

		// tests de addItemFilms
		
		nbBooks = sn.nbBooks();
		
		try {
			sn.addMember("Jordan", "abcd", "Surfeur sexy");
			sn.addMember("Glenn", "bcde", "ok");
			sn.addMember("Alice", "cdef", "bref");
			nbMembres = sn.nbMembers();
			
		} catch (Exception e) {	
			System.out.println("Test AddItemFilm échoué : Exception non prevue : Revoir methodes dans le bloc try");
		}
		
		addItemFilmBadEntryTest(sn, null, "abcd", "test", "drame", "john", "do", 120, "4.1", "L'ajout d'un film par un membre dont le pseudo n'est pas instancié est accepté" );
		addItemFilmBadEntryTest(sn, " ", "abcd", "test", "drame", "john", "do", 120, "4.2", "L'ajout d'un film par un membre dont le pseudo n'est composé que d'espaces est accepté" );
		addItemFilmBadEntryTest(sn, "Jordan", null, "test", "drame", "john", "do", 120, "4.3", "L'ajout d'un film par un membre dont le password n'est pas instancié est accepté" );
		addItemFilmBadEntryTest(sn, "Jordan", "abc", "test", "drame", "john", "do", 120, "4.4", "L'ajout d'un film par un membre dont le password fait moins de 4 caractères est accepté" );
		addItemFilmBadEntryTest(sn, "Jordan", "abcd", null, "drame", "john", "do", 120, "4.5", "L'ajout d'un film dont le titre n'est pas instancié est accepté" );
		addItemFilmBadEntryTest(sn, "Jordan", "abcd", "   ", "drame", "john", "do", 120, "4.6", "L'ajout d'un film dont le titre n'est constitué que d'espaces est accepté");
		addItemFilmBadEntryTest(sn, "Jordan", "abcd", "test", null, "john", "do", 120, "4.7", "L'ajout d'un film dont le genre n'est pas instancié est accepté");
		addItemFilmBadEntryTest(sn, "Jordan", "abcd", "test", "drame", null, "do", 120, "4.8", "L'ajout d'un film dont le realisateur n'est pas instancié est accepté");
		addItemFilmBadEntryTest(sn, "Jordan", "abcd", "test", "drame", "john", null, 120, "4.9", "L'ajout d'un film dont le scenariste n'est pas instancié est accepté");
		addItemFilmBadEntryTest(sn, "Jordan", "abcd", "test", "drame", "john", "do", 0, "4.12", "L'ajout d'un film dont la duree est nulle est accepté");
		
		addItemFilmOKTest (sn, "Jordan","abcd","test", "drame","john","do", 125, "4.20");
		addItemFilmOKTest (sn, "Glenn", "bcde", "test2","action", "john", "didier", 90, "4.21");
		addItemFilmOKTest (sn, "Alice", "cdef", "test3","humour", "john", "jean", 100, "4.22");

		// tentative d'ajout d'un film "existant"

		addItemFilmAlreadyExistsTest(sn, "Jordan","abcd","test", "drame","john","do", 125, "4.23", "L'ajout d'un film avec le titre du premier film ajouté est accepté");
		addItemFilmAlreadyExistsTest(sn, "Alice", "cdef", "test3","humour", "john", "jean", 100, "4.24", "L'ajout d'un film avec le titre du dernier film ajouté est accepté");
		addItemFilmAlreadyExistsTest(sn, "Glenn", "bcde", "TEST2","action", "john", "didier", 90, "4.25", "L'ajout d'un film avec le titre d'un film existant (avec casse différente) est accepté");
		addItemFilmAlreadyExistsTest(sn, "Glenn", "bcde", "  test2  ","action", "john", "didier", 90, "4.26","L'ajout d'un film avec le titre d'un film existant (avec leadings et trailings blanks) est accepté");       

		addItemFilmNotMember(sn, "George", "abcd","test2", "drame","john","do", 125, "4.27", "L'ajout d'un film par un non membre est accepté");
		addItemFilmNotMember(sn, "Jordan", "efgh","test2", "drame","john","do", 125, "4.28", "L'ajout d'un film avec mauvais login/pwd est accepté");
		
		if (nbMembres != sn.nbMembers()) {
			System.out.println("Erreur 4.29 :  le nombre de membres après utilisation de addItemFilms a été modifié");
		}
		if (nbBooks != sn.nbBooks()) {
			System.out.println("Erreur 4.30 :  le nombre de films après utilisation de addItemFilms a été modifié");               
		}
	}
}
