package avis;

import java.util.LinkedList;

public class Review {

	private float rating;

	private String commentary;
	
	private Member member = null;
	
	private Item item = null;
	
	private LinkedList<Review> opinionsReview;
	
	/**
	 * Constructor
	 * @param member
	 * @param item
	 * @param commentary
	 * @param rating
	 */
	public Review(Member member, Item item, String commentary, float rating){
		
		this.rating = rating;
		this.member = member;
		this.commentary = commentary;
		this.item = item;
	}
	
	/**
	 * Modify an existing review
	 * @param commentary New commentary
	 * @param rating New rating
	 * @return The modified review
	 */
	public Review modifyReview(String commentary, float rating){
		this.commentary = commentary;
		this.rating = rating;
		return this;
	}
	
	/**
	 * Add a new opinion to this review
	 * @param r the opinion to add
	 */
	public void addOpinion(Review r){

		if(opinionsReview == null) opinionsReview = new LinkedList<Review>();
		
		opinionsReview.add(r);
	}
	
	/**
	 * Computes the average opinion rating on this review
	 * @return the average opinion rating
	 */
	public float getAverageOpinionRating(){
		
		float average = 0;
		
		// Return best rating if no opinion given on the review yet
		if(opinionsReview == null) return 5;
		
		for(Review r : opinionsReview){
			average += r.rating;
		}
		// Divide by the number of reviews
		average /= opinionsReview.size();
	
		return average;
	}

	public float getRating(){
		
		return rating;
	}
	
	public Item getItem(){
		
		return item;
	}
	
	public Member getMember(){
		
		return member;
	}
	
	public static void main(String[] args){

	}
	
	
}
