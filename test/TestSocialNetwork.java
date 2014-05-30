package test;

public class TestSocialNetwork {
	
	public static void main(String[] args){
		
		TestsInitialisation.main(null);
		
		System.out.println("Veuillez patienter, generation de 500 membres et 5000 items en cours");
		TestRendement.main(null);
		
		TestsAddMember.main(null);
		System.out.println("La classe TestsAddMember retourne : "+TestsAddMember.nbTestOk+" tests ok et : "+TestsAddMember.nbTestFail+ " Tests rat�s \n");
		
		TestsAddItemFilm.main(null);
		System.out.println("La classe TestsAddItemFilm retourne : "+TestsAddItemFilm.nbTestOk+" tests ok et : "+TestsAddItemFilm.nbTestFail+ " Tests rat�s \n");
		
		TestsAddItemBook.main(null);
		System.out.println("La classe TestsAddItemBook retourne : "+TestsAddItemBook.nbTestOk+" tests ok et : "+TestsAddItemBook.nbTestFail+ " Tests rat�s \n");
		
		TestsReviewItemFilm.main(null);
		System.out.println("La classe TestsReviewItemFilm retourne : "+TestsReviewItemFilm.nbTestOk+" tests ok et : "+TestsReviewItemFilm.nbTestFail+ " Tests rat�s \n");
		
		TestsReviewItemBook.main(null);
		System.out.println("La classe TestsReviewItemBook retourne : "+TestsReviewItemBook.nbTestOk+" tests ok et : "+TestsReviewItemBook.nbTestFail+ " Tests rat�s \n");
		
		TestsReviewOpinion.main(null);
		System.out.println("La classe TestsReviewOpinion retourne : "+TestsReviewOpinion.nbTestOk+" tests ok et : "+TestsReviewOpinion.nbTestFail+ " Tests rat�s \n");
		
		int totalFail = TestsReviewItemBook.nbTestFail + TestsAddItemBook.nbTestFail + TestsReviewItemFilm.nbTestFail + TestsAddItemFilm.nbTestFail + TestsAddMember.nbTestFail
				+ TestsReviewOpinion.nbTestFail;
		int totalOk = TestsReviewItemBook.nbTestOk + TestsAddItemBook.nbTestOk + TestsReviewItemFilm.nbTestOk + TestsAddItemFilm.nbTestOk + TestsAddMember.nbTestOk
				+ TestsReviewOpinion.nbTestOk;
		
		System.out.println("Un total de : "+ totalFail +" Fails et : "+ totalOk +" Reussites");
	}
}
