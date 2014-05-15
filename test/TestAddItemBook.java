package test;

import avis.SocialNetwork;
import exception.BadEntry;
import exception.ItemBookAlreadyExists;
import exception.NotMember;

public class TestAddItemBook {
	
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
				System.out.println("Test " + idTest + " : l'exception BadEntry a bien �t� lev�e mais le nombre de livres a �t� modifi�");
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
		int nbLivres = 0;

		System.out.println("Tests  ajouter des livres au r�seau social  ");

		// tests de addItemBook
		
		nbLivres = sn.nbBooks();
		
		try {
			sn.addMember("Jordan", "abcd", "Surfeur sexy");
			sn.addMember("Glenn", "bcde", "ok");
			sn.addMember("Alice", "cdef", "bref");
			nbMembres = sn.nbMembers();
			
		} catch (Exception e) {	
			System.out.println("Test AddItemBook �chou� : Exception non prevue : Revoir methodes dans le bloc try");
		}
		
		addItemBookBadEntryTest(sn, null, "abcd", "test", "drame", "john", 120, "4.1", "L'ajout d'un livre par un membre dont le pseudo n'est pas instanci� est accept�" );
		addItemBookBadEntryTest(sn, " ", "abcd", "test", "drame", "john", 120, "4.2", "L'ajout d'un livre par un membre dont le pseudo n'est compos� que d'espaces est accept�" );
		addItemBookBadEntryTest(sn, "Jordan", null, "test", "drame", "john", 120, "4.3", "L'ajout d'un livre par un membre dont le password n'est pas instanci� est accept�" );
		addItemBookBadEntryTest(sn, "Jordan", "abc", "test", "drame", "john", 120, "4.4", "L'ajout d'un livre par un membre dont le password fait moins de 4 caract�res est accept�" );
		addItemBookBadEntryTest(sn, "Jordan", "abcd", null, "drame", "john", 120, "4.5", "L'ajout d'un livre dont le titre n'est pas instanci� est accept�" );
		addItemBookBadEntryTest(sn, "Jordan", "abcd", "   ", "drame", "john", 120, "4.6", "L'ajout d'un livre dont le titre n'est constitu� que d'espaces est accept�");
		addItemBookBadEntryTest(sn, "Jordan", "abcd", "test", null, "john", 120, "4.7", "L'ajout d'un livre dont le genre n'est pas instanci� est accept�");
		addItemBookBadEntryTest(sn, "Jordan", "abcd", "test", "drame", null, 120, "4.8", "L'ajout d'un livre dont l'auteur n'est pas instanci� est accept�");
		addItemBookBadEntryTest(sn, "Jordan", "abcd", "test", "drame", "john", null, "4.9", "L'ajout d'un livre dont le nombre de pages n'est pas instanci� est accept�");
		
		addItemBookOKTest (sn, "Jordan","abcd","test", "drame","john", 125, "4.20");
		addItemBookOKTest (sn, "Glenn", "bcde", "test2","action", "john", 90, "4.21");
		addItemBookOKTest (sn, "Alice", "cdef", "test3","humour", "john", 100, "4.22");

		// tentative d'ajout d'un livre "existant"

		addItemBookAlreadyExistsTest(sn, "Jordan","abcd","test", "drame","john", 125, "4.23", "L'ajout d'un livre avec le titre du premier livre ajout� est accept�");
		addItemBookAlreadyExistsTest(sn, "Alice", "cdef", "test3","humour", "john", 100, "4.24", "L'ajout d'un livre avec le titre du dernier livre ajout� est accept�");
		addItemBookAlreadyExistsTest(sn, "Glenn", "bcde", "TEST2","action", "john", 90, "4.25", "L'ajout d'un livre avec le titre d'un livre existant (avec casse diff�rente) est accept�");
		addItemBookAlreadyExistsTest(sn, "Glenn", "bcde", "  test2  ","action", "john", 90, "4.26","L'ajout d'un livre avec le titre d'un livre existant (avec leadings et trailings blanks) est accept�");       

		addItemBookNotMember(sn, "George", "abcd","test2", "drame","john", 125, "4.27", "L'ajout d'un livre par un non membre est accept�");
		addItemBookNotMember(sn, "Jordan", "efgh","test2", "drame","john", 125, "4.28", "L'ajout d'un livre avec mauvais login/pwd est accept�");
		
		if (nbMembres != sn.nbMembers()) {
			System.out.println("Erreur 4.29 :  le nombre de membres apr�s utilisation de addItemBooks a �t� modifi�");
		}
		if (nbLivres != sn.nbBooks()) {
			System.out.println("Erreur 4.30 :  le nombre de livres apr�s utilisation de addItemBooks a �t� modifi�");               
		}

	}
}
