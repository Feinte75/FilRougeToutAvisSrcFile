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
	
	
	public Item(String title, String genre){
		
		this.title = title;
		this.genre = genre;
		reviews = new LinkedList<Review>();
	}

	public void addReview(Review review){
		reviews.add(review);
	}
	
	/**
	 * 
	 * @param title the title of the item searched
	 * @return the item if it is found, null either
	 */
	public Item itemExists(String title){
		if(this.title.equalsIgnoreCase(title.trim())) return this;
		else return null;
	}
	
	/**
	 * calculate the average rating
	 * @return the average rating
	 */
	public float average(){
		
		float av = 0;
		float karmaWeight = 0;
		
		for(Review sum : reviews){
			// Compute the rating of each reviews with the karma of their authors
			av += (sum.getRating() * sum.getMember().getKarma());
			// Save the total amount of karma of all members
			karmaWeight += sum.getMember().getKarma();
		}
		// Divide by the total karma to make people with good karma more influent on the rating
		av /= karmaWeight;
		
		return av;
	}
}
