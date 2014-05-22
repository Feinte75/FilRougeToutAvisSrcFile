
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


	public static void reviewOpinionBadEntryTest(SocialNetwork sn, String pseudo, String password, String commentaryAuthor, String title, String commentary, float rating, String idTest, String messErreur){

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
			System.out.println ("Test " + idTest + " : exception non pr�vue. " + e);
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
			System.out.println ("Test " + idTest + " : exception non pr�vue. " + e);
			e.printStackTrace();
			nbTestFail++;
		}
	}

	public static void reviewOpinionNotMemberTest(SocialNetwork sn, String pseudo, String password, String commentaryAuthor, String title, String commentary, float rating, String idTest, String messErreur){

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
			System.out.println ("Test " + idTest + " : exception non pr�vue. " + e);
			e.printStackTrace();
			nbTestOk++;
		}

		try {
			sn.reviewOpinionBook(pseudo, password, commentaryAuthor, title, rating, commentary);
			System.out.println ("Test " + idTest + " : " + messErreur);
			nbTestFail++;
		}
		catch (NotMember e) {
			nbTestOk++;
		}
		catch (Exception e) {
			System.out.println ("Test " + idTest + " : exception non pr�vue. " + e);
			e.printStackTrace();
			nbTestFail++;
		}

	}

	public static void reviewOpinionOkTest(SocialNetwork sn, String pseudo, String password, String commentaryAuthor, String title, String commentary, float rating, String idTest, String messErreur){

		// verifie que l'ajout d'une opinion (pseudo ,password, rating, string type, titre, pseudo) est refus�e (lev�e de l'exception ou tout est OK et pas de modification du sn)
		// si c'est bien le cas, ne fait rien mais incr�mente la variable nbTestFail
		// sinon, affiche le message d'erreur pass� en param�tre

		try{
			sn.reviewOpinionFilm(pseudo, password, commentaryAuthor, title, rating, commentary);
			nbTestFail++;
		}
		catch (Exception e) {
			System.out.println ("Test " + idTest + " : exception non pr�vue. " + e);
			e.printStackTrace();
			nbTestOk++;
		}

		try{
			sn.reviewOpinionBook(pseudo, password, commentaryAuthor, title, rating, commentary);
			nbTestFail++;
		}
		catch (Exception e) {
			System.out.println ("Test " + idTest + " : exception non pr�vue. " + e);
			e.printStackTrace();
			nbTestOk++;
		}
	}

	public static void reviewOpinionNotItemTest(SocialNetwork sn, String pseudo, String password, String commentaryAuthor, String title, String commentary, float rating, String idTest, String messErreur){

		try {
			sn.reviewOpinionFilm(pseudo, password, commentaryAuthor, title, rating, commentary);
			System.out.println ("Test " + idTest + " : " + messErreur);
			nbTestOk++;
		}
		catch (NotItem e) {
			nbTestFail++;
		}
		catch (Exception e) {
			System.out.println ("Test " + idTest + " : exception non pr�vue. " + e); 
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
			System.out.println ("Test " + idTest + " : exception non pr�vue. " + e); 
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
		reviewOpinionBadEntryTest(sn, null, "1234", "Glenn", "titre", "Cool", 2.2f, "7.1", "L'ajout d'une opinion dont le pseudo n'est pas instancie est accepte" );
		reviewOpinionBadEntryTest(sn, " ", "1234", "Glenn", "titre", "Cool", 2.2f, "7.2", "L'ajout d'une opinion dont le pseudo n'est compose que d'espaces est accepte" );
		reviewOpinionBadEntryTest(sn, "test", null,"Glenn", "titre", "Cool", 2.2f, "7.3", "L'ajout d'une opinion par un membre (pseudo) dont le password n'est pas instancie est accepte" );
		reviewOpinionBadEntryTest(sn, "test", "bbb", "Glenn", "titre", "Cool", 2.2f, "7.4", "L'ajout d'une opinion par un membre (pseudo) dont le password fait moins de 4 caracteres est accepte" );
		reviewOpinionBadEntryTest(sn, "test", "1234", "Glenn", "titre", "Cool", -2.2f, "7.5", "L'ajout d'une opinion dont la rating est négative est accepte" );
		reviewOpinionBadEntryTest(sn, "test", "1234", "Glenn", "titre", "Cool", 5.2f, "7.6", "L'ajout d'une opinion dont la rating est supérieur a 5 est accepte" );
		reviewOpinionBadEntryTest(sn, "test", "1234", " ", "titre", "Cool", 2.2f, "7.7", "L'ajout d'une opinion sur un pseudo qui n'est pas instancie est accepte" );
		reviewOpinionBadEntryTest(sn, "test", "1234", null , "titre", "Cool", 2.2f, "7.8", "L'ajout d'une opinion sur un membre dont le pseudo n'est compose que d'espaces est accepte" );
		reviewOpinionBadEntryTest(sn, "test", "1234","Glenn", null, "Cool", 2.2f, "7.10", "L'ajout d'une opinion dont le titre n'est pas instancie est accepte" );
		reviewOpinionBadEntryTest(sn, "test", "1234", "Glenn", " ", "Cool", 2.2f, "7.11", "L'ajout d'une opinion dont le titre n'est compose que d'espaces est accepte" );
		reviewOpinionBadEntryTest(sn, "test", "1234", "Glenn", "titre", null, 2.2f, "7.12", "L'ajout d'une opinion dont le commentaire n'est pas instancie est accepte" );
		


		// tentative d'ajout d'une opinion alors que le membre est "inexistant"
		reviewOpinionNotMemberTest(sn, "ABCDEFGHIJ","AZERTY", "Glenn", "Titre", "Cool", 2.2f, "7.14", "L'ajout d'une opinion alors que le membre n'existe pas est accepte");

		//ajout des membres
		try {
			sn.addMember("test", "1234", "bebe");
			sn.addMember("inconnu", "1234", "baba");
		} catch (Exception e) {
			
			e.printStackTrace();
			System.out.println("Test ReviewOpinion �chou� : Exception non prevue : Revoir methodes dans le bloc try");
		}

		//tentative d'ajout d'une opinion alors que le film est "inexistant"
		reviewOpinionNotItemTest (sn, "test","1234", "inconnu", "film", "Cool", 2.2f, "7.15", "L'ajout d'une opinion pour un film inexistant est accepte");

		//tentative d'ajout d'une opinion alors que le livre est "inexistant"
		reviewOpinionNotItemTest (sn, "test","1234", "inconnu", "book", "Cool", 2.2f, "7.16", "L'ajout d'une opinion pour un livre inexistant est accepte");

		//ajout d'un film et d'un livre de m�me titre pour tester les deux m�thodes
		try {
			sn.addItemFilm("test", "1234", "Le seigneur des anneaux", "Fantastique", "Peter Jackson", "Peter", 120);
			sn.addItemBook("test", "1234", "Le seigneur des anneaux", "Fantastique", "Peter", 120);
		} catch (Exception e) {
			
			e.printStackTrace();
			System.out.println("Test ReviewOpinion �chou� : Exception non prevue : Revoir methodes dans le bloc try");
		}
		
		//ajout d'une review film et livre
		try{
			sn.reviewItemFilm("test", "1234", "Le seigneur des anneaux", 5, "Good");
			sn.reviewItemBook("test", "1234", "Le seigneur des anneaux", 5, "Good");
		} catch (Exception e){
			
			e.printStackTrace();
			System.out.println("Test ReviewOpinion �chou� : Exception non prevue : Revoir methodes dans le bloc try");
		}

		nbFilms=sn.nbFilms();
		nbMembres=sn.nbMembers();
		nbLivres=sn.nbBooks();

		// tentative d'ajout d'avis sur un film avec entrees "correctes"	
		//Test avec ajout d'une opinion sur un film
		reviewOpinionOkTest (sn, "inconnu","1234","test", "Le seigneur des anneaux", "Pas d'accord", 0, "7.17", "L'ajout d'une opinion sur un film est OK");

		//Test avec ajout d'une rating sur l' opinion sur un film
		reviewOpinionOkTest (sn, "inconnu","1234","test","Le seigneur des anneaux", "Pas d'accord", 0, "7.18", "L'ajout d'une opinion sur un livre est OK");


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