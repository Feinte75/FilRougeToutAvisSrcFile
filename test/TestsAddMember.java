package test;

import avis.SocialNetwork;
import exception.BadEntry;
import exception.MemberAlreadyExists;

/** 
 * @author B. Prou, E. Cousin
 * @date mars 2014
 * @version V0.9
 */

public class TestsAddMember {

	public static int nbTestOk=0;
	public static int nbTestFail=0;

	public static void addMemberBadEntryTest (SocialNetwork sn, String pseudo, String pwd, String profil, String idTest, String messErreur){
		
		int nbMembres = sn.nbMembers();
		try {
			sn.addMember (pseudo, pwd, profil);
			System.out.println ("Test " + idTest + " : " + messErreur);
			nbTestFail++;
		}
		catch (BadEntry e) {
			if (sn.nbMembers() != nbMembres) System.out.println("Test " + idTest + " : l'exception BadEntry a bien été levée mais le nombre de membres a été modifié");
			nbTestOk++;
		}
		catch (Exception e) {System.out.println ("Test " + idTest + " : exception non pr�vue. " + e); e.printStackTrace();}
	}
	
	public static void addMemberOKTest (SocialNetwork sn, String pseudo, String pwd, String profil, String idTest){
		int nbMembres = sn.nbMembers();
		try{
			sn.addMember (pseudo, pwd, profil);
			if (sn.nbMembers() != nbMembres+1){
				System.out.println("Test " + idTest + " :  le nombre de membres n'a pas été correctement incrémenté");
				nbTestFail++;
				return;
			}
			nbTestOk++;
		}
		catch (Exception e) {System.out.println ("Test " + idTest + " : exception non prévue. " + e); e.printStackTrace();}
	}

	public static void addMemberAlreadyExistsTest (SocialNetwork sn, String pseudo, String pwd, String profil, String idTest, String messErreur){
		int nbMembres = sn.nbMembers();
		try {
			sn.addMember (pseudo, pwd, profil);
			System.out.println ("Test " + idTest + " : " + messErreur);
			nbTestFail++;
		}
		catch (MemberAlreadyExists e) {
			if (sn.nbMembers() != nbMembres) System.out.println("Test " + idTest + " : l'exception MemberAlreadyExists a bien été levée mais le nombre de membres a été modifié");
			nbTestOk++;
		}
		catch (Exception e) {System.out.println ("Test " + idTest + " : exception non prévue. " + e); e.printStackTrace();}
	}

	public static void main(String[] args) {

		int nbLivres = 0;
		int nbFilms = 0;

		System.out.println("Tests  ajouter des membres au r�seau social  ");


		SocialNetwork sn = new SocialNetwork();

		// tests de addMember
		nbFilms = sn.nbFilms();
		nbLivres = sn.nbBooks();

		// <=> fiche num�ro 1

		// Utilisation de addMember avec param�tres d'entr�e incorrects
		addMemberBadEntryTest ( sn, null, "qsdfgh", "", "3.1", "L'ajout d'un membre dont le pseudo n'est pas instancié est accepté");
		addMemberBadEntryTest ( sn, " ", "qsdfgh", "", "3.2", "L'ajout d'un membre dont le pseudo ne contient pas un caracteres, autre que des espaces, est accepté");
		addMemberBadEntryTest ( sn, "B", null, "", "3.3", "L'ajout d'un membre dont le password n'est pas instancié est accepté");
		addMemberBadEntryTest ( sn, "B", "   qwd ", "", "3.4", "L'ajout d'un membre dont le password ne contient pas au moins 4 caracteres, autre que des espaces de début ou de fin, est accepté");
		addMemberBadEntryTest ( sn, "BBBB", "bbbb", null, "3.5", "L'ajout d'un membre dont le profil n'est pas instancié est accepté");
		
		// <=> fiche num�ro 2
		
		// Utilisation de addMember avec param�tres d'entr�e corrects
		addMemberOKTest (sn, "Paul", "paul", "lecteur impulsif","3.6,1");
		addMemberOKTest (sn, "Antoine", "antoine", "grand amoureux de la littérature","3.6,2");
		addMemberOKTest (sn, "Alice", "alice", "20 ans, sexy","3.6,3");

		// Utilisation de addMember avec en param�tre un livre d�j� existant
		addMemberAlreadyExistsTest(sn, "Paul", "dfsf", "", "3.7", "L'ajout d'un membre avec le pseudo du premier membre ajouté est accepté");
		addMemberAlreadyExistsTest(sn, "Alice", "alice", "", "3.8", "L'ajout d'un membre avec le pseudo du dernier membre ajouté est accepté");
		addMemberAlreadyExistsTest(sn, "anToine", "antoine", "", "3.9", "L'ajout d'un membre avec un pseudo existant (avec casse différente) est accepté");
		addMemberAlreadyExistsTest(sn, " Antoine ", "antoine", "", "3.10", "L'ajout d'un membre avec un pseudo existant (avec leading et trailing blanks) est accepté");		


		if (nbFilms != sn.nbFilms()) {
			System.out.println("Erreur 3.11 :  le nombre de films après utilisation de addMember a été modifié");
		}
		if (nbLivres != sn.nbBooks()) {
			System.out.println("Erreur 3.12 :  le nombre de livres après utilisation de addMember a été modifié");				
		}
	}
}
