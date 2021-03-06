package test;

import avis.SocialNetwork;
import exception.BadEntry;
import exception.NotItem;
import exception.NotMember;

public class TestsReviewItemFilm {
	
	public static int nbTestOk=0;
	public static int nbTestFail=0;

	public static void reviewItemFilmBadEntryTest(SocialNetwork sn, String pseudo, String password, String titre, 
			float note, String commentaire, String idTest, String messErreur){
		try {
			sn.reviewItemFilm(pseudo, password, titre, note, commentaire);
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
	
	public static void reviewItemFilmNotMemberTest(SocialNetwork sn, String pseudo, String password, String titre, 
			float note, String commentaire, String idTest, String messErreur){
		try{
			sn.reviewItemFilm(pseudo, password, titre, note, commentaire);
			nbTestFail++;
		}
		catch (NotMember e){
			nbTestOk++;
		}
		catch (Exception e){
			System.out.println ("Test " + idTest + " : exception non pr�vue. " + e);
			e.printStackTrace();
			nbTestFail++;
		}
		
	}

	public static void reviewItemFilmOkTest (SocialNetwork sn, String pseudo, String password, String titre, 
			float note, String commentaire, String idTest){
		try{
			float average = sn.reviewItemFilm(pseudo, password, titre, note, commentaire);
			if(average != 0)nbTestOk++;
			else{
				System.out.println("Test " + idTest + " :  la moyenne n'a pas �t� correctement recalcul�e");
				nbTestFail++;
			}
		}
		catch (Exception e) {
			System.out.println ("Test " + idTest + " : exception non pr�vue. " + e);
			e.printStackTrace();
			nbTestFail++;
		}
	}

	public static void reviewItemFilmNotItemTest (SocialNetwork sn, String pseudo, String password, String titre, 
			float note, String commentaire, String idTest, String messErreur){
	
		try {
			sn.reviewItemFilm(pseudo, password, titre, note, commentaire);
			System.out.println ("Test " + idTest + " : " + messErreur);
			nbTestFail++;
		}
		catch (NotItem e) {
			nbTestOk++;
		}
		catch (Exception e) {
			System.out.println ("Test " + idTest + " : exception non pr�vue. " + e); 
			e.printStackTrace();
			nbTestFail++;
		}
	}

	public static void main(String[] args){

		System.out.println("Tests  ajouter des reviews au films du reseau social  ");
		
		SocialNetwork sn = new SocialNetwork();

		try {
			sn.addMember("Jordan", "abcd", "Surfeur sexy");
			sn.addItemFilm("Jordan", "abcd", "test", "drame", "john", "do", 120);

		} catch (Exception e) {	
			System.out.println("Test reviewItemFilm �chou� : Exception non prevue : Revoir methodes dans le bloc try");
		}
		
		// Utilisation de reviewItemFilm avec param�tres d'entr�e incorrects
		reviewItemFilmBadEntryTest(sn, null, "abcd", "test", 4, "Pourri", "5.1", "L'ajout d'un commentaire par un membre dont le pseudo n'est pas instanci� est accept�" );
		reviewItemFilmBadEntryTest(sn, " ",  "abcd", "test", 4, "Pourri", "5.2", "L'ajout d'un commentaire par un membre dont le pseudo n'est compos� que d'espaces est accept�" );
		reviewItemFilmBadEntryTest(sn, "Jordan", null, "test", 4, "Pourri", "5.3", "L'ajout d'un commentaire par un membre dont le password n'est pas instanci� est accept�" );
		reviewItemFilmBadEntryTest(sn, "Jordan", "abc", "test", 4, "Pourri", "5.4", "L'ajout d'un commentaire par un membre dont le password fait moins de 4 caract�res est accept�" );
		reviewItemFilmBadEntryTest(sn, "Jordan", "abcd", null, 4, "Pourri", "5.5", "L'ajout d'un commentaire dont le titre n'est pas instanci� est accept�" );
		reviewItemFilmBadEntryTest(sn, "Jordan", "abcd", "   ", 4, "Pourri", "5.6", "L'ajout d'un commentaire dont le titre n'est constitu� que d'espaces est accept�");
		reviewItemFilmBadEntryTest(sn, "Jordan", "abcd", "test", -5, "Pourri", "5.7", "L'ajout d'un commentaire dont la note n'est pas comprise entre 0 et 5 est accept�");
		reviewItemFilmBadEntryTest(sn, "Jordan", "abcd", "test", 4, null, "5.8", "L'ajout d'un commentaire non instanci� est accept�");
		
		// Utilisation de reviewItemFilm avec en param�tre pseudo/mdp incorrects
		reviewItemFilmNotMemberTest(sn, "George", "abcd","test2", 4, "Pourri", "5.9", "L'ajout d'un commentaire par un non membre est accept�");
		reviewItemFilmNotMemberTest(sn, "Jordan", "efgh","test2", 4, "Pourri", "5.10", "L'ajout d'un commentaire avec mauvais login/pwd est accept�");
		
		// Utilisation de reviewItemFilm avec en param�tre un film inexistant
		reviewItemFilmNotItemTest (sn, "Jordan","abcd","test2", 4, "Pourri", "5.11", "L'ajout d'un commentaire pour un film inexistant est accept�");
		
		// Utilisation de reviewItemFilm avec param�tres d'entr�e corrects et v�rification de la mise � jour d'une review
		reviewItemFilmOkTest (sn, "Jordan","abcd","test", 4, "Pourri", "5.12");
		reviewItemFilmOkTest (sn, "Jordan","abcd","test", 5, "Bien", "5.13");
	}
}
