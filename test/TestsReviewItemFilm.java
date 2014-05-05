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
			System.out.println("Test " + idTest + " : " + messErreur);
			nbTestOk++;
		}
		catch (Exception e) {
			System.out.println ("Test " + idTest + " : exception non prévue. " + e);
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
			System.out.println("Test " + idTest + " : " + messErreur);
			nbTestOk++;
		}
		catch (Exception e){
			System.out.println ("Test " + idTest + " : exception non prévue. " + e);
			e.printStackTrace();
			nbTestFail++;
		}
		
	}

	public static void reviewItemFilmOkTest (SocialNetwork sn, String pseudo, String password, String titre, 
			float note, String commentaire, String idTest){
		try{
			sn.reviewItemFilm(pseudo, password, titre, note, commentaire);
			nbTestOk++;
		}
		catch (Exception e) {
			System.out.println ("Test " + idTest + " : exception non prévue. " + e);
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
			System.out.println("Test " + idTest + " ; " + messErreur );
			nbTestOk++;
		}
		catch (Exception e) {
			System.out.println ("Test " + idTest + " : exception non prévue. " + e); 
			e.printStackTrace();
			nbTestFail++;
		}
	}

	public static void main(String[] args){

		SocialNetwork sn = new SocialNetwork();

		try {
			sn.addMember("Jordan", "abcd", "Surfeur sexy");
			sn.addItemFilm("Jordan", "abcd", "test", "drame", "john", "do", 120);

		} catch (Exception e) {	
			System.out.println("Test reviewItemFilm échoué : Exception non prevue : Revoir methodes dans le bloc try");
		}
		
		reviewItemFilmBadEntryTest(sn, null, "abcd", "test", 4, "Pourri", "5.1", "L'ajout d'un commentaire par un membre dont le pseudo n'est pas instancié est accepté" );
		reviewItemFilmBadEntryTest(sn, " ",  "abcd", "test", 4, "Pourri", "5.2", "L'ajout d'un commentaire par un membre dont le pseudo n'est composé que d'espaces est accepté" );
		reviewItemFilmBadEntryTest(sn, "Jordan", null, "test", 4, "Pourri", "5.3", "L'ajout d'un commentaire par un membre dont le password n'est pas instancié est accepté" );
		reviewItemFilmBadEntryTest(sn, "Jordan", "abc", "test", 4, "Pourri", "5.4", "L'ajout d'un commentaire par un membre dont le password fait moins de 4 caractères est accepté" );
		reviewItemFilmBadEntryTest(sn, "Jordan", "abcd", null, 4, "Pourri", "5.5", "L'ajout d'un commentaire dont le titre n'est pas instancié est accepté" );
		reviewItemFilmBadEntryTest(sn, "Jordan", "abcd", "   ", 4, "Pourri", "5.6", "L'ajout d'un commentaire dont le titre n'est constitué que d'espaces est accepté");
		reviewItemFilmBadEntryTest(sn, "Jordan", "abcd", "test", -5, "Pourri", "5.7", "L'ajout d'un commentaire dont la note n'est pas comprise entre 0 et 5 est accepté");
		reviewItemFilmBadEntryTest(sn, "Jordan", "abcd", "test", 4, null, "5.8", "L'ajout d'un commentaire non instancié est accepté");
		
		reviewItemFilmOkTest (sn, "Jordan","abcd","test", 4, "Pourri", "5.9");

		reviewItemFilmNotItemTest (sn, "Jordan","abcd","test2", 4, "Pourri", "5.10", "L'ajout d'un commentaire pour un film inexistant est accepté");
		
		reviewItemFilmNotMemberTest(sn, "George", "abcd","test2", 4, "Pourri", "5.11", "L'ajout d'un commentaire par un non membre est accepté");
		reviewItemFilmNotMemberTest(sn, "Jordan", "efgh","test2", 4, "Pourri", "5.12", "L'ajout d'un commentaire avec mauvais login/pwd est accepté");

	}
}
