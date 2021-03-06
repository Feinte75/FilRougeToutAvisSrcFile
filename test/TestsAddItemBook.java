package test;

import avis.SocialNetwork;
import exception.BadEntry;
import exception.ItemBookAlreadyExists;
import exception.NotMember;

public class TestsAddItemBook {
	
	public static int nbTestOk=0;
	public static int nbTestFail=0;

	public static void addItemBookBadEntryTest(SocialNetwork sn, String pseudo, String password, String titre, 
			String genre, String auteur, int nbPages, String idTest, String messErreur){

		int nbBooks = sn.nbBooks();
		try {
			sn.addItemBook(pseudo, password, titre, genre, auteur, nbPages);
			System.out.println ("Test " + idTest + " : " + messErreur);
			nbTestFail++;
		}
		catch (BadEntry e) {
			if (sn.nbBooks() != nbBooks) {
				System.out.println("Test " + idTest + " : BadEntry exception has been fired but nbBooks has changed");
				nbTestFail++;
				return;
			}
			nbTestOk++;
		}
		catch (Exception e) {
			System.out.println ("Test " + idTest + " : exception non pr�vue. " + e);
			e.printStackTrace();
			nbTestFail++;
		}
	}

	public static void addItemBookOKTest (SocialNetwork sn, String pseudo, String password, String titre, String genre, String auteur, int nbPages, String idTest){
		int nbBooks = sn.nbBooks();
		try{
			sn.addItemBook(pseudo, password, titre, genre, auteur, nbPages);
			if (sn.nbBooks() != nbBooks+1){
				System.out.println("Test " + idTest + " :  le nombre de livres n'a pas �t� correctement incr�ment�");
				nbTestFail++;
				return;
			}
			nbTestOk++;
		}
		catch (Exception e) {
			System.out.println ("Test " + idTest + " : exception non pr�vue. " + e);
			e.printStackTrace();
			nbTestFail++;
		}
	}

	public static void addItemBookAlreadyExistsTest (SocialNetwork sn, String pseudo, String password, String titre, String genre, String auteur, int nbPages, String idTest, String messErreur){
		int nbBooks = sn.nbBooks();
		try {
			sn.addItemBook(pseudo, password, titre, genre, auteur, nbPages);
			System.out.println ("Test " + idTest + " : " + messErreur);
			nbTestFail++;
		}
		catch (ItemBookAlreadyExists e) {
			if (sn.nbBooks() != nbBooks){
				System.out.println("Test " + idTest + " : l'exception ItemBookAlreadyExists a bien �t� lev�e mais le nombre de livres a �t� modifi�");
				nbTestFail++;
				return;
			}
			nbTestOk++;
		}
		catch (Exception e) {
			System.out.println ("Test " + idTest + " : exception non pr�vue. " + e); 
			e.printStackTrace();
			nbTestFail++;
		}
	}
	
	public static void addItemBookNotMember (SocialNetwork sn, String pseudo, String password, String titre, String genre, String auteur, int nbPages, String idTest, String messErreur){
		int nbBooks = sn.nbBooks();
		try{
			sn.addItemBook(pseudo, password, titre, genre, auteur, nbPages);
			nbTestFail++;
		}
		catch (NotMember e){
			if (sn.nbBooks() != nbBooks){
				System.out.println("Test " + idTest + " : l'exception NotMember a bien �t� lev�e mais le nombre de membres a �t� modifi�");
				nbTestFail++;
				return;
			}
			nbTestOk++;
		}
		catch (Exception e){
			System.out.println ("Test " + idTest + " : exception non pr�vue. " + e); 
			e.printStackTrace();
			nbTestFail++;
		}
	}

	public static void main(String[] args){

		SocialNetwork sn = new SocialNetwork();

		int nbMembres = 0;
		int nbFilms= 0;

		System.out.println("Tests  ajouter des livres au r�seau social  ");

		// tests de addItemBook
		
		nbFilms = sn.nbFilms();
		
		try {
			sn.addMember("Jordan", "abcd", "Surfeur sexy");
			sn.addMember("Glenn", "bcde", "ok");
			sn.addMember("Alice", "cdef", "bref");
			nbMembres = sn.nbMembers();
			
		} catch (Exception e) {	
			System.out.println("Test AddItemBook �chou� : Exception non prevue : Revoir methodes dans le bloc try");
		}
		
		// Utilisation de addItemBook avec param�tres d'entr�e incorrects
		addItemBookBadEntryTest(sn, null, "abcd", "test", "drame", "john", 120, "4.1", "L'ajout d'un livre par un membre dont le pseudo n'est pas instanci� est accept�" );
		addItemBookBadEntryTest(sn, " ", "abcd", "test", "drame", "john", 120, "4.2", "L'ajout d'un livre par un membre dont le pseudo n'est compos� que d'espaces est accept�" );
		addItemBookBadEntryTest(sn, "Jordan", null, "test", "drame", "john", 120, "4.3", "L'ajout d'un livre par un membre dont le password n'est pas instanci� est accept�" );
		addItemBookBadEntryTest(sn, "Jordan", "abc", "test", "drame", "john", 120, "4.4", "L'ajout d'un livre par un membre dont le password fait moins de 4 caract�res est accept�" );
		addItemBookBadEntryTest(sn, "Jordan", "abcd", null, "drame", "john", 120, "4.5", "L'ajout d'un livre dont le titre n'est pas instanci� est accept�" );
		addItemBookBadEntryTest(sn, "Jordan", "abcd", "   ", "drame", "john", 120, "4.6", "L'ajout d'un livre dont le titre n'est constitu� que d'espaces est accept�");
		addItemBookBadEntryTest(sn, "Jordan", "abcd", "test", null, "john", 120, "4.7", "L'ajout d'un livre dont le genre n'est pas instanci� est accept�");
		addItemBookBadEntryTest(sn, "Jordan", "abcd", "test", "drame", null, 120, "4.8", "L'ajout d'un livre dont l'auteur n'est pas instanci� est accept�");
		addItemBookBadEntryTest(sn, "Jordan", "abcd", "test4.9", "drame", "john", 0, "4.9", "L'ajout d'un livre de 0 pages est accept�");
		
		// Utilisation de addItemBook avec en param�tre un livre d�j� existant
		addItemBookAlreadyExistsTest(sn, "Jordan","abcd","test", "drame","john", 125, "4.10", "L'ajout d'un livre avec le titre du premier livre ajout� est accept�");
		addItemBookAlreadyExistsTest(sn, "Alice", "cdef", "test3","humour", "john", 100, "4.11", "L'ajout d'un livre avec le titre du dernier livre ajout� est accept�");
		addItemBookAlreadyExistsTest(sn, "Glenn", "bcde", "TEST2","action", "john", 90, "4.12", "L'ajout d'un livre avec le titre d'un livre existant (avec casse diff�rente) est accept�");
		addItemBookAlreadyExistsTest(sn, "Glenn", "bcde", "  test2  ","action", "john", 90, "4.13","L'ajout d'un livre avec le titre d'un livre existant (avec leadings et trailings blanks) est accept�");       
		
		// Utilisation de addItemBook avec en param�tre pseudo/mdp incorrects
		addItemBookNotMember(sn, "George", "abcd","test2", "drame","john", 125, "4.14", "L'ajout d'un livre par un non membre est accept�");
		addItemBookNotMember(sn, "Jordan", "efgh","test2", "drame","john", 125, "4.15", "L'ajout d'un livre avec mauvais login/pwd est accept�");
		
		// Utilisation de addItemBook avec param�tres d'entr�e corrects
		addItemBookOKTest (sn, "Jordan","abcd","test", "drame","john", 125, "4.16");
		addItemBookOKTest (sn, "Glenn", "bcde", "test2","action", "john", 90, "4.17");
		addItemBookOKTest (sn, "Alice", "cdef", "test3","humour", "john", 100, "4.18");
		
		if (nbMembres != sn.nbMembers()) {
			System.out.println("Erreur 4.29 :  le nombre de membres apr�s utilisation de addItemBooks a �t� modifi�");
		}
		if (nbFilms != sn.nbFilms()) {
			System.out.println("Erreur 4.30 :  le nombre de films apr�s utilisation de addItemBooks a �t� modifi�");               
		}

	}
}
