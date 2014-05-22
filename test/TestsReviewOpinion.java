
package test;


import avis.SocialNetwork;

import exception.BadEntry;
import exception.ItemFilmAlreadyExists;
import exception.ItemBookAlreadyExists;
import exception.MemberAlreadyExists;
import exception.NotItem;
import exception.NotMember;

public class TestsReviewOpinion {
	
	public static int nbTestOk=0;
	public static int nbTestFail=0;
	
		
 public static void reviewOpinionBadEntryTest(SocialNetwork sn, String pseudo, String password, float rating, String type, String title, String pseudo, String idTest, String messErreur){
   	 
	 // verifie la levee de l'exception BadEntry 
   	 // si la levee est effective, incremente la variable nbsuccess
   	 // sinon, affiche un message d'erreur 
	 
		try {
			sn.reviewOpinionFilm(pseudo, password, commentaryAuthor, title, rating, commentary);
			System.out.println ("Test " + idTest + " : " + messErreur);
			nbTestFail++;
		}
		catch (BadEntry e) {
			nbTestOk++;
		}
		catch (Exception e) {
			System.out.println ("Test " + idTest + " : exception non prévue. " + e);
			e.printStackTrace();
			nbTestFail++;
		}
		
		try {
			sn.reviewOpinionBook(pseudo, password, commentaryAuthor, title, rating, commentary);
			System.out.println ("Test " + idTest + " : " + messErreur);
			nbTestFail++;
		}
		catch (BadEntry e) {
			nbTestOk++;
		}
		catch (Exception e) {
			System.out.println ("Test " + idTest + " : exception non prévue. " + e);
			e.printStackTrace();
			nbTestFail++;
		}
	}
	
 public static void reviewOpinionNotMemberTest(SocialNetwork sn, String pseudo, String password, float rating, String type, String title, String pseudo, String idTest, String messErreur){
			
	 // verifie la levee de l'exception NotMember
   	 // si la levee est effective, incremente la variable nbsuccess
   	 // sinon, affiche un message d'erreur 
   	 
		try{
			sn.reviewOpinionFilm(pseudo, password, commentaryAuthor, title, rating, commentary);
			System.out.println ("Test " + idTest+ ": " + messErreur);
			nbTestFail++;
		}
		catch (NotMember e){
			 nbTestFail++;
		}
		catch (Exception e){
			System.out.println ("Test " + idTest + " : exception non prï¿½vue. " + e);
			e.printStackTrace();
			nbTestOk++;
		}
		
		try {
			sn.reviewOpinionBook(pseudo, password, commentaryAuthor, title, rating, commentary);
			System.out.println ("Test " + idTest + " : " + messErreur);
			nbTestFail++;
		}
		catch (BadEntry e) {
			nbTestOk++;
		}
		catch (Exception e) {
			System.out.println ("Test " + idTest + " : exception non prévue. " + e);
			e.printStackTrace();
			nbTestFail++;
		}
		
	}

	public static void reviewOpinionOkTest(SocialNetwork sn, String pseudo, String password, float rating, String type, String titre, String pseudo, String idTest, String messErreur){
	
	// verifie que l'ajout d'une opinion (pseudo ,password, rating, string type, titre, pseudo) est refusï¿½e (levï¿½e de l'exception ou tout est OK et pas de modification du sn)
   	// si c'est bien le cas, ne fait rien mais incrï¿½mente la variable nbTestFail
   	// sinon, affiche le message d'erreur passï¿½ en paramï¿½tre
		
		try{
			sn.reviewOpinionFilm(pseudo, password, commentaryAuthor, title, rating, commentary);
			nbTestFail++;
		}
		catch (Exception e) {
			System.out.println ("Test " + idTest + " : exception non prï¿½vue. " + e);
			e.printStackTrace();
			nbTestOk++;
		}
		
		try{
			sn.reviewOpinionBook(pseudo, password, commentaryAuthor, title, rating, commentary);
			nbTestFail++;
		}
		catch (Exception e) {
			System.out.println ("Test " + idTest + " : exception non prï¿½vue. " + e);
			e.printStackTrace();
			nbTestOk++;
		}
	}

	public static void reviewOpinionNotItemTest(SocialNetwork sn, String pseudo, String password, float rating, String type, String titre, String pseudo, String idTest, String messErreur){
	
		try {
			sn.reviewOpinionFilm(pseudo, password, commentaryAuthor, title, rating, commentary);
			System.out.println ("Test " + idTest + " : " + messErreur);
			nbTestOk++;
		}
		catch (NotItem e) {
			nbTestFail++;
		}
		catch (Exception e) {
			System.out.println ("Test " + idTest + " : exception non prï¿½vue. " + e); 
			e.printStackTrace();
			nbTestOk++;
		}
		
		try {
			sn.reviewOpinionBook(pseudo, password, commentaryAuthor, title, rating, commentary);
			System.out.println ("Test " + idTest + " : " + messErreur);
			nbTestOk++;
		}
		catch (NotItem e) {
			nbTestFail++;
		}
		catch (Exception e) {
			System.out.println ("Test " + idTest + " : exception non prï¿½vue. " + e); 
			e.printStackTrace();
			nbTestOk++;
		}
	}

	
	public static void main(String[] args){

   	 SocialNetwork sn = new SocialNetwork();
		int nbMembres = 0;
		int nbLivres = 0;
		int nbFilms = 0;
	
	// tests de TestsReviewOpinion
	nbFilms = sn.nbFilms();
	nbMembres = sn.nbMembers();
	nbLivres = sn.nbBooks();
	
	
   	// tentative d'ajout d'une opinion avec entrees "incorrectes"
		reviewOpinionBadEntryTest(sn, null, "1234", 2.2f, "film", "titre", "pseudo", "7.1", "L'ajout d'une opinion dont le pseudo n'est pas instancie est accepte" );
		reviewOpinionBadEntryTest(sn, " ", "1234", 2.2f, "film", "titre", "pseudo", "7.2", "L'ajout d'une opinion dont le pseudo n'est compose que d'espaces est accepte" );
		reviewOpinionBadEntryTest(sn, "test", null, 2.2f, "film", "titre", "pseudo", "7.3", "L'ajout d'une opinion par un membre (pseudo) dont le password n'est pas instancie est accepte" );
		reviewOpinionBadEntryTest(sn, "test", "bbb", 2.2f, "film", "titre", "pseudo", "7.4", "L'ajout d'une opinion par un membre (pseudo) dont le password fait moins de 4 caracteres est accepte" );
		reviewOpinionBadEntryTest(sn, "test", "1234", -2.2f, "film", "titre", "pseudo", "7.5", "L'ajout d'une opinion dont la rating est nÃ©gative est accepte" );
		reviewOpinionBadEntryTest(sn, "test", "1234", 5.2f, "film", "titre", "pseudo", "7.6", "L'ajout d'une opinion dont la rating est supÃ©rieur a 5 est accepte" );
		reviewOpinionBadEntryTest(sn, "test", "1234", 2.2f, " ", "titre", "pseudo", "7.7", "L'ajout d'une opinion dont le type n'est pas instancie est accepte" );
		reviewOpinionBadEntryTest(sn, "test", "1234", 2.2f, null , "titre", "pseudo", "7.8", "L'ajout d'une opinion dont le type n'est compose que d'espaces est accepte" );
		reviewOpinionBadEntryTest(sn, "test", "1234", 2.2f, "film", null, "pseudo", "7.10", "L'ajout d'une opinion dont le titre n'est pas instancie est accepte" );
		reviewOpinionBadEntryTest(sn, "test", "1234", 2.2f, "film", " ", "pseudo", "7.11", "L'ajout d'une opinion dont le titre n'est compose que d'espaces est accepte" );
		reviewOpinionBadEntryTest(sn, "test", "1234", 2.2f, "film", "titre", null, "7.12", "L'ajout d'une opinion dont le pseudo du notÃ© n'est pas instancie est accepte" );
		reviewOpinionBadEntryTest(sn, "test", "1234", 2.2f, "film", "titre", " ", "7.13", "L'ajout d'une opinion dont le pseudo du notÃ© n'est compose que d'espaces est accepte" );
		
		
	// tentative d'ajout d'une opinion alors que le membre est "inexistant"
		reviewOpinionNotMemberTest(sn, "ABCDEFGHIJ","AZERTY", 2.2f, "film", "titre", "pseudo", "7.14", "L'ajout d'une opinion alors que le membre n'existe pas est accepte");

	
			
	//ajout des membres
			try {
			sn.addMember("test", "1234", "bebe");
		} catch (BadEntry e) {
			// TODO Auto-generatingd catch block
			e.printStackTrace();
		} catch (MemberAlreadyExists e) {
			// TODO Auto-generatingd catch block
			e.printStackTrace();
		}
			try {
			sn.addMember("inconnu", "1234", "baba");
		} catch (BadEntry e) {
			// TODO Auto-generatingd catch block
			e.printStackTrace();
		} catch (MemberAlreadyExists e) {
			// TODO Auto-generatingd catch block
			e.printStackTrace();
		}
		
	//tentative d'ajout d'une opinion alors que le film est "inexistant"
	reviewOpinionNotItemTest (sn, "test","1234", 2.2f, "film", "titre", "inconnu", "7.15", "L'ajout d'une opinion pour un film inexistant est accepte");
		
	//tentative d'ajout d'une opinion alors que le livre est "inexistant"
	reviewOpinionNotItemTest (sn, "test","1234", 2.2f, "book", "titre", "inconnu", "7.16", "L'ajout d'une opinion pour un livre inexistant est accepte");
				
	//ajout d'un film
			try {
				sn.addItemFilm("test", "1234", "Le seigneur des anneaux", "Fantastique", "Peter Jackson", "Peter", 120);
			} catch (BadEntry e) {
				// TODO Auto-generatingd catch block
				e.printStackTrace();
			} catch (NotMember e) {
				// TODO Auto-generatingd catch block
				e.printStackTrace();
			} catch (ItemFilmAlreadyExists e) {
				// TODO Auto-generatingd catch block
				e.printStackTrace();
			}
			
			
	//ajout d'un livre (pseudo,password,titre,genre,auteur,nbPages)
			try {
				sn.addItemBook("test", "1234", "blague", "Fantastique", "Peter", 120);
			} catch (BadEntry e) {
				// TODO Auto-generatingd catch block
				e.printStackTrace();
			} catch (NotMember e) {
				// TODO Auto-generatingd catch block
				e.printStackTrace();
			} catch (ItemBookAlreadyExists e) {
				// TODO Auto-generatingd catch block
				e.printStackTrace();
			}
		
			nbFilms=sn.nbFilms();
			nbMembres=sn.nbMembers();
			nbLivres=sn.nbBooks();
	
	// tentative d'ajout d'avis sur un film avec entrees "correctes"	
	//Test avec ajout d'une opinion sur un film
		reviewOpinionOkTest (sn, "inconnu","1234",4.5f,"film","Le seigneur des anneaux","test", "7.17", "L'ajout d'une opinion sur un film est OK");
	
	//Test avec ajout d'une rating sur l' opinion sur un film
		reviewOpinionOkTest (sn, "inconnu","1234",3.5f,"book","blague","test", "7.18", "L'ajout d'une opinion sur un livre est OK");
	



	if (nbMembres != sn.nbMembers()) {
   		 System.out.println("Erreur 7.19 :  le nombre de membres apres utilisation de reviewOpinion a ete modifie");
   	 }
	if (nbFilms != sn.nbFilms()) {
   		 System.out.println("Erreur 7.20 :  le nombre de films apres utilisation de reviewOpinion a ete modifie");
   	 }
   	if (nbLivres != sn.nbBooks()) {
   		 System.out.println("Erreur 7.21 :  le nombre de livres apres utilisation de reviewOpinion a ete modifie");   			 
   	 }
	
	// Permet d'afficher le nombre d'echec et reussite.
   	System.out.println("Nombre d'echec :  "+ nbTestOk +"\nNombre de reussite :  " + nbTestFail);
    }
}
