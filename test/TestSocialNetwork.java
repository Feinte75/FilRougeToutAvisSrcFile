package test;

public class TestSocialNetwork {
	
	public static void main(String[] args){
		
		TestsInitialisation.main(null);
		
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
	}
}
