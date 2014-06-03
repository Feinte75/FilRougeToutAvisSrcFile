
package test;


import avis.SocialNetwork;
import exception.BadEntry;
import exception.NotItem;
import exception.NotMember;
import exception.NotReview;

public class TestsReviewOpinion {

	public static int nbTestOk=0;
	public static int nbTestFail=0;


	public static void reviewOpinionBadEntryTest(SocialNetwork sn, String pseudo, String password, String commentaryAuthor, String title, String commentary, float rating, String idTest, String messErreur){

		// verifie la levee de l'exception NotMember
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
			nbTestOk++;
		}
		catch (Exception e){
			System.out.println ("Test " + idTest + " : exception non prï¿½vue. " + e);
			e.printStackTrace();
			nbTestFail++;
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
			System.out.println ("Test " + idTest + " : exception non prévue. " + e);
			e.printStackTrace();
			nbTestFail++;
		}
	}
	
	public static void reviewOpinionNotReviewTest(SocialNetwork sn, String pseudo, String password, String commentaryAuthor, String title, String commentary, float rating, String idTest, String messErreur){
		
		try{
			sn.reviewOpinionFilm(pseudo, password, commentaryAuthor, title, rating, commentary);
			System.out.println ("Test " + idTest+ ": " + messErreur);
			nbTestFail++;
		}
		catch (NotReview e){
			nbTestOk++;
		}
		catch (Exception e){
			System.out.println ("Test " + idTest + " : exception non prï¿½vue. " + e);
			e.printStackTrace();
			nbTestFail++;
		}

		try {
			sn.reviewOpinionBook(pseudo, password, commentaryAuthor, title, rating, commentary);
			System.out.println ("Test " + idTest + " : " + messErreur);
			nbTestFail++;
		}
		catch (NotReview e) {
			nbTestOk++;
		}
		catch (Exception e) {
			System.out.println ("Test " + idTest + " : exception non prévue. " + e);
			e.printStackTrace();
			nbTestFail++;
		}
	}

	public static void reviewOpinionOkTest(SocialNetwork sn, String pseudo, String password, String commentaryAuthor, String title, String commentary, float rating, String idTest, String messErreur){

		// verifie que l'ajout d'une opinion (pseudo ,password, rating, string type, titre, pseudo) est refusée (levï¿½e de l'exception ou tout est OK et pas de modification du sn)
		// si c'est bien le cas, ne fait rien mais incrï¿½mente la variable nbTestFail
		// sinon, affiche le message d'erreur passï¿½ en paramï¿½tre

		try{
			sn.reviewOpinionFilm(pseudo, password, commentaryAuthor, title, rating, commentary);
			
			nbTestOk++;
		}
		catch (Exception e) {
			System.out.println ("Test " + idTest + " : exception non prévue. " + e);
			e.printStackTrace();
			nbTestFail++;
		}

		try{
			sn.reviewOpinionBook(pseudo, password, commentaryAuthor, title, rating, commentary);
			nbTestOk++;
		}
		catch (Exception e) {
			System.out.println ("Test " + idTest + " : exception non prévue. " + e);
			e.printStackTrace();
			nbTestFail++;
		}
	}

	public static void reviewOpinionNotItemTest(SocialNetwork sn, String pseudo, String password, String commentaryAuthor, String title, String commentary, float rating, String idTest, String messErreur){

		try {
			sn.reviewOpinionFilm(pseudo, password, commentaryAuthor, title, rating, commentary);
			System.out.println ("Test " + idTest + " : " + messErreur);
			nbTestFail++;
		}
		catch (NotItem e) {
			nbTestOk++;
		}
		catch (Exception e) {
			System.out.println ("Test " + idTest + " : exception non prï¿½vue. " + e); 
			e.printStackTrace();
			nbTestFail++;
		}

		try {
			sn.reviewOpinionBook(pseudo, password, commentaryAuthor, title, rating, commentary);
			System.out.println ("Test " + idTest + " : " + messErreur);
			nbTestFail++;
		}
		catch (NotItem e) {
			nbTestOk++;
		}
		catch (Exception e) {
			System.out.println ("Test " + idTest + " : exception non prï¿½vue. " + e); 
			e.printStackTrace();
			nbTestFail++;
		}
	}


	public static void main(String[] args){

		SocialNetwork sn = new SocialNetwork();
		int nbMembres = 0;
		int nbLivres = 0;
		int nbFilms = 0;

		System.out.println("Tests  ajouter des opinions aux reviews du reseau social  ");
		
		// tests de TestsReviewOpinion
		nbFilms = sn.nbFilms();
		nbMembres = sn.nbMembers();
		nbLivres = sn.nbBooks();
		
		//ajout des membres
		try {
			sn.addMember("test", "1234", "bebe");
			sn.addMember("inconnu", "1234", "baba");
		} catch (Exception e) {
			
			e.printStackTrace();
			System.out.println("Test ReviewOpinion échoué : Exception non prevue : Revoir methodes dans le bloc try");
		}

		// Utilisation de reviewOpinion avec paramètres d'entrée incorrect
		reviewOpinionBadEntryTest(sn, null, "1234", "Glenn", "titre", "Cool", 2.2f, "7.1", "L'ajout d'une opinion dont le pseudo n'est pas instancie est accepte" );
		reviewOpinionBadEntryTest(sn, " ", "1234", "Glenn", "titre", "Cool", 2.2f, "7.2", "L'ajout d'une opinion dont le pseudo n'est compose que d'espaces est accepte" );
		reviewOpinionBadEntryTest(sn, "test", null,"Glenn", "titre", "Cool", 2.2f, "7.3", "L'ajout d'une opinion par un membre (pseudo) dont le password n'est pas instancie est accepte" );
		reviewOpinionBadEntryTest(sn, "test", "bbb", "Glenn", "titre", "Cool", 2.2f, "7.4", "L'ajout d'une opinion par un membre (pseudo) dont le password fait moins de 4 caracteres est accepte" );
		reviewOpinionBadEntryTest(sn, "test", "1234", "Glenn", "titre", "Cool", -2.2f, "7.5", "L'ajout d'une opinion dont la rating est nÃ©gative est accepte" );
		reviewOpinionBadEntryTest(sn, "test", "1234", "Glenn", "titre", "Cool", 5.2f, "7.6", "L'ajout d'une opinion dont la rating est supÃ©rieur a 5 est accepte" );
		reviewOpinionBadEntryTest(sn, "test", "1234", " ", "titre", "Cool", 2.2f, "7.7", "L'ajout d'une opinion sur un pseudo qui n'est pas instancie est accepte" );
		reviewOpinionBadEntryTest(sn, "test", "1234", null , "titre", "Cool", 2.2f, "7.8", "L'ajout d'une opinion sur un membre dont le pseudo n'est compose que d'espaces est accepte" );
		reviewOpinionBadEntryTest(sn, "test", "1234","Glenn", null, "Cool", 2.2f, "7.10", "L'ajout d'une opinion dont le titre n'est pas instancie est accepte" );
		reviewOpinionBadEntryTest(sn, "test", "1234", "Glenn", " ", "Cool", 2.2f, "7.11", "L'ajout d'une opinion dont le titre n'est compose que d'espaces est accepte" );
		reviewOpinionBadEntryTest(sn, "test", "1234", "Glenn", "titre", null, 2.2f, "7.12", "L'ajout d'une opinion dont le commentaire n'est pas instancie est accepte" );
		
		// Utilisation de reviewOpinion avec en paramètre membre "inexistant"
		reviewOpinionNotMemberTest(sn, "ABCDEFGHIJ","AZERTY", "Glenn", "Titre", "Cool", 2.2f, "7.13", "L'ajout d'une opinion alors que le membre n'existe pas est accepte");
		reviewOpinionNotMemberTest(sn, "test","aaaa", "Glenn", "Titre", "Cool", 2.2f, "7.14", "L'ajout d'un commentaire avec mauvais login/pwd est accepté");
		reviewOpinionNotMemberTest(sn, "ABCDEFGHIJ","AZERTY", "Glenn", "Titre", "Cool", 2.2f, "7.15", "L'ajout d'une opinion alors que le membre n'existe pas est accepte");

		// Utilisation de reviewOpinion avec en paramètre un item "inexistant"
		reviewOpinionNotItemTest (sn, "test","1234", "inconnu", "film", "Cool", 2.2f, "7.16", "L'ajout d'une opinion pour un film inexistant est accepte");

		//ajout d'un film et d'un livre de même titre pour tester les deux méthodes
		try {
			sn.addItemFilm("test", "1234", "Le seigneur des anneaux", "Fantastique", "Peter Jackson", "Peter", 120);
			sn.addItemBook("test", "1234", "Le seigneur des anneaux", "Fantastique", "Peter", 120);
		} catch (Exception e) {
			
			e.printStackTrace();
			System.out.println("Test ReviewOpinion échoué : Exception non prevue : Revoir methodes dans le bloc try");
		}
		
		// Utilisation de reviewOpinion avec en paramètre une review "inexistante"
		reviewOpinionNotReviewTest (sn, "test","1234", "inconnu", "Le seigneur des anneaux", "Cool", 2.2f, "7.17", "L'ajout d'une opinion sur une review inexistante est accepte");
		
		//ajout d'une review film et livre
		try{
			// 2 reviews a 4
			sn.reviewItemFilm("test", "1234", "Le seigneur des anneaux", 4, "Good");
			sn.reviewItemBook("test", "1234", "Le seigneur des anneaux", 4, "Good");
		
		} catch (Exception e){
			
			e.printStackTrace();
			System.out.println("Test ReviewOpinion échoué : Exception non prevue : Revoir methodes dans le bloc try");
		}

		nbFilms=sn.nbFilms();
		nbMembres=sn.nbMembers();
		nbLivres=sn.nbBooks();

		// Utilisation de reviewOpinion avec paramètres d'entrée incorrects
		// Test avec ajout d'une opinion sur un film et un book
		reviewOpinionOkTest (sn, "inconnu","1234","test", "Le seigneur des anneaux", "D'accord", 4, "7.18", "L'ajout d'une opinion sur un film est OK");

		if (nbMembres != sn.nbMembers()) {
			System.out.println("Erreur 7.19 :  le nombre de membres apres utilisation de reviewOpinion a ete modifie");
		}
		if (nbFilms != sn.nbFilms()) {
			System.out.println("Erreur 7.20 :  le nombre de films apres utilisation de reviewOpinion a ete modifie");
		}
		if (nbLivres != sn.nbBooks()) {
			System.out.println("Erreur 7.21 :  le nombre de livres apres utilisation de reviewOpinion a ete modifie");   			 
		}
	}
}
