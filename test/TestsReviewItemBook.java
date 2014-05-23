package test;

import avis.SocialNetwork;
import exception.BadEntry;
import exception.NotItem;
import exception.NotMember;

public class TestsReviewItemBook {
	
	public static int nbTestOk=0;
	public static int nbTestFail=0;

	public static void reviewItemBookBadEntryTest(SocialNetwork sn, String pseudo, String password, String titre, 
			float note, String commentaire, String idTest, String messErreur){
		try {
			sn.reviewItemBook(pseudo, password, titre, note, commentaire);
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
	
	public static void reviewItemBookNotMemberTest(SocialNetwork sn, String pseudo, String password, String titre, 
			float note, String commentaire, String idTest, String messErreur){
		try{
			sn.reviewItemBook(pseudo, password, titre, note, commentaire);
			nbTestFail++;
		}
		catch (NotMember e){
			nbTestOk++;
		}
		catch (Exception e){
			System.out.println ("Test " + idTest + " : exception non prévue. " + e);
			e.printStackTrace();
			nbTestFail++;
		}
	}

	public static void reviewItemBookOkTest (SocialNetwork sn, String pseudo, String password, String titre, 
			float note, String commentaire, String idTest){
		try{
			float average = sn.reviewItemBook(pseudo, password, titre, note, commentaire);
			if(average != 0)nbTestOk++;
			else{
				System.out.println("Test " + idTest + " :  la moyenne n'a pas été correctement recalculée");
				nbTestFail++;
			}
		}
		catch (Exception e) {
			System.out.println ("Test " + idTest + " : exception non prévue. " + e);
			e.printStackTrace();
			nbTestFail++;
		}
	}

	public static void reviewItemBookNotItemTest (SocialNetwork sn, String pseudo, String password, String titre, 
			float note, String commentaire, String idTest, String messErreur){
	
		try {
			sn.reviewItemBook(pseudo, password, titre, note, commentaire);
			System.out.println ("Test " + idTest + " : " + messErreur);
			nbTestFail++;
		}
		catch (NotItem e) {
			nbTestOk++;
		}
		catch (Exception e) {
			System.out.println ("Test " + idTest + " : exception non prévue. " + e); 
			e.printStackTrace();
			nbTestFail++;
		}
	}

	public static void main(String[] args){

		System.out.println("Tests  ajouter des reviews aux livres du reseau social  ");
		
		SocialNetwork sn = new SocialNetwork();

		try {
			sn.addMember("Jordan", "abcd", "Surfeur sexy");
			sn.addItemBook("Jordan", "abcd", "test", "drame", "john", 120);

		} catch (Exception e) {	
			System.out.println("Test reviewItemBook échoué : Exception non prevue : Revoir methodes dans le bloc try");
		}
		
		reviewItemBookBadEntryTest(sn, null, "abcd", "test", 4, "Pourri", "5.1", "L'ajout d'un commentaire par un membre dont le pseudo n'est pas instancié est accepté" );
		reviewItemBookBadEntryTest(sn, " ",  "abcd", "test", 4, "Pourri", "5.2", "L'ajout d'un commentaire par un membre dont le pseudo n'est composé que d'espaces est accepté" );
		reviewItemBookBadEntryTest(sn, "Jordan", null, "test", 4, "Pourri", "5.3", "L'ajout d'un commentaire par un membre dont le password n'est pas instancié est accepté" );
		reviewItemBookBadEntryTest(sn, "Jordan", "abc", "test", 4, "Pourri", "5.4", "L'ajout d'un commentaire par un membre dont le password fait moins de 4 caractères est accepté" );
		reviewItemBookBadEntryTest(sn, "Jordan", "abcd", null, 4, "Pourri", "5.5", "L'ajout d'un commentaire dont le titre n'est pas instancié est accepté" );
		reviewItemBookBadEntryTest(sn, "Jordan", "abcd", "   ", 4, "Pourri", "5.6", "L'ajout d'un commentaire dont le titre n'est constitué que d'espaces est accepté");
		reviewItemBookBadEntryTest(sn, "Jordan", "abcd", "test", -5, "Pourri", "5.7", "L'ajout d'un commentaire dont la note n'est pas comprise entre 0 et 5 est accepté");
		reviewItemBookBadEntryTest(sn, "Jordan", "abcd", "test", 4, null, "5.8", "L'ajout d'un commentaire non instancié est accepté");
		
		reviewItemBookOkTest (sn, "Jordan","abcd","test", 5, "Pourri", "5.9");

		reviewItemBookNotItemTest (sn, "Jordan","abcd","test2", 4, "Pourri", "5.10", "L'ajout d'un commentaire pour un livre inexistant est accepté");
		
		reviewItemBookNotMemberTest(sn, "George", "abcd","test2", 4, "Pourri", "5.11", "L'ajout d'un commentaire par un non membre est accepté");
		reviewItemBookNotMemberTest(sn, "Jordan", "efgh","test2", 4, "Pourri", "5.12", "L'ajout d'un commentaire avec mauvais login/pwd est accepté");

	}
}
