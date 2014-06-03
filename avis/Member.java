package avis;

import java.util.LinkedList;

public class Member {

	private String pseudo;
	private String password;
	private String profile;
	
	private LinkedList<Review> reviews;
		
	/**
	 * Constructor
	 * @param pseudo
	 * @param password
	 * @param profile
	 */
	public Member(String pseudo, String password, String profile) {
	
		this.pseudo = pseudo;
		this.password = password;
		this.profile = profile;
		this.reviews = new LinkedList<Review>();
	}

	/**
	 * Compares the pseudo and password parameters to the attributes
	 * @param pseudo 
	 * @param password
	 * @return Member if the pseudo/password matches, null either 
	 */
	public Member authenticate(String pseudo, String password){

		if(this.pseudo.equalsIgnoreCase(pseudo.trim()) && this.password.equals(password)) return this;
		else return null;
	}
	
	/**
	 * Only compares the pseudo
	 * @param pseudo
	 * @return Member if the pseudo matches, null either 
	 */
	public Member userExists(String pseudo){
		
		if(this.pseudo.equalsIgnoreCase(pseudo.trim())) return this;
		else return null;		
	}
	
	/**
	 * Add a review to the given Item, if the member already reviewed the Item, the review is updated
	 * @param item item to review
	 * @param commentary comment to add to the review
	 * @param rating rating of the review
	 * @return the new review or the modified review
	 */
	public Review addReview(Item item, String commentary, float rating){
		
		// Test if the member already reviewed the item
		Review review = reviewAlreadyExists(item);
		
		// If he has reviewed before than update his review
		if (review != null) return review.modifyReview(commentary, rating);
		
		// If not then create a new review
		else{
			review = new Review(this, item, commentary, rating);
			reviews.add(review);
			item.addReview(review);
			return review;	
		}
	}
	
	/**
	 * Computes the member's karma
	 * @return float the karma of the user
	 */
	public float getKarma(){
		
		float karma = 0;
		
		// Get every opinions from every reviews of the member and sum the ratings
		for(Review r : reviews){
			karma += r.getAverageOpinionRating();
		}
		// Divide the sum by the number of reviews
		karma /= reviews.size();
		
		// Normalize by 5 to get a karma between 0 and 1 
		karma /= 5;
		return karma;
	}
	
	/**
	 * Test if a review about the item has already been done by the member
	 * @param item 
	 * @return
	 */
	public Review reviewAlreadyExists(Item item){
		
		for(Review r : reviews){
			if(r.getItem() == item) return r;
		}
		return null;
	}
	
	/**
	 * @return The String representation of the member without his password for privacy reasons.
	 */
	public String toString(){
		return "My pseudo is : " +pseudo+ " and my profile is : " +profile;
	}
	
	public static void main(String[] args){
		
	}
}
