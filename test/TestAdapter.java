package test;

import exception.BadEntry;
import exception.NotItem;
import exception.NotMember;
import exception.NotReview;
import avis.SocialNetwork;

public class TestAdapter extends SocialNetwork {

	@Override
	public float reviewOpinionBook(String pseudo, String password,
			String commentaryAuthor, String title, float rating,
			String commentary) throws BadEntry, NotMember, NotItem, NotReview {
	
		return super.reviewOpinionBook(pseudo, password, commentaryAuthor, title,
				rating, commentary);
	}

	@Override
	public float reviewOpinionFilm(String pseudo, String password,
			String commentaryAuthor, String title, float rating,
			String commentary) throws BadEntry, NotMember, NotItem, NotReview {
		
		return super.reviewOpinionFilm(pseudo, password, commentaryAuthor, title,
				rating, commentary);
	}	
}
