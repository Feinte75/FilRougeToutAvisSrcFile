package test;

public class TestSocialNetwork {
	
	public static void main(String[] args){
			
		TestsAddItemFilm.main(null);
		System.out.println("La classe TestsAddItemFilm retourne : "+TestsAddItemFilm.nbTestOk+" tests ok et : "+TestsAddItemFilm.nbTestFail+ " Tests ratés");
		
		TestsAddMember.main(null);
		System.out.println("La classe TestsAddMember retourne : "+TestsAddMember.nbTestOk+" tests ok et : "+TestsAddMember.nbTestFail+ " Tests ratés");
		
		TestsReviewItemFilm.main(null);
		System.out.println("La classe TestsReviewItemFilm retourne : "+TestsReviewItemFilm.nbTestOk+" tests ok et : "+TestsReviewItemFilm.nbTestFail+ " Tests ratés");
	}
}
