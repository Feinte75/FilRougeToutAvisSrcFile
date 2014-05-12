package avis;

import java.util.LinkedList;


public abstract class Item {

	/**
	 * @uml.property  name="title"
	 */
	protected String title;
	/**
	 * @uml.property  name="genre"
	 */
	protected String genre;
	/** 
	 * @uml.property name="reviews"
	 * @uml.associationEnd multiplicity="(0 -1)" inverse="item:avis.Review"
	 */
	private LinkedList<Review> reviews;
	
	public float addReview(Review review){
		reviews.add(review);
		return average();
	}

	public float average(){
		float av = 0;
		
		for(Review sum : reviews){
			av += sum.getRating();
		}
		
		av /= reviews.size();
		
		return av;
	}

}
