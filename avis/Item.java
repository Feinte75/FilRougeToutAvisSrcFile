package avis;

import java.util.LinkedList;

public abstract class Item {

	protected String title;
	
	protected String genre;
	
	private LinkedList<Review> reviews;
	
	/**
	 * Constructor
	 * @param title
	 * @param genre
	 */
	public Item(String title, String genre){
		
		this.title = title;
		this.genre = genre;
		reviews = new LinkedList<Review>();
	}
	
	/**
	 * Add a review to the item
	 * @param review
	 */
	public void addReview(Review review){
		reviews.add(review);
	}
	
	/**
	 * Compare parameters title to attribute title
	 * @param title the title of the item searched
	 * @return the item if it is found, null either
	 */
	public Item itemExists(String title){
		if(this.title.equalsIgnoreCase(title.trim())) return this;
		else return null;
	}
	
	/**
	 * calculate the average rating taking into account the karma of the reviews authors
	 * @return the average rating of the item
	 */
	public float average(){
		
		float av = 0;
		float karmaWeight = 0;
		float karmaMember = 0;
		
		for(Review sum : reviews){
			karmaMember = sum.getMember().getKarma();
			// Compute the rating of each reviews with the karma of their authors
			av += (sum.getRating() * karmaMember);
			// Save the total amount of karma of the members who reviewed the item
			karmaWeight += karmaMember;
		}
		// Divide by the total karma to make people with good karma more influent on the rating
		av /= karmaWeight;
		
		return av;
	}
}
