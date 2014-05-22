package avis;

import java.util.LinkedList;


public class Member {

	/**
	 * @uml.property  name="pseudo"
	 */
	private String pseudo;
	/**
	 * @uml.property  name="password"
	 */
	private String password;
	/**
	 * @uml.property  name="profile"
	 */
	private String profile;
	/** 
	 * @uml.property name="socialNetwork"
	 * @uml.associationEnd multiplicity="(1 1)" inverse="members:avis.SocialNetwork"
	 */
	private SocialNetwork socialNetwork = null;
	/**
	 * @uml.property  name="reviews"
	 * @uml.associationEnd  multiplicity="(0 -1)" inverse="member:avis.Review"
	 */
	private LinkedList<Review> reviews;
		
	public Member(String pseudo, String password, String profile) {
	
		this.pseudo = pseudo;
		this.password = password;
		this.profile = profile;
		this.reviews = new LinkedList<Review>();
	}

	/**
	*/
	public Member authenticate(String pseudo, String password){

		if(this.pseudo.equalsIgnoreCase(pseudo.trim()) && this.password.equals(password)) return this;
		else return null;
	}
	
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
		
		Review review = reviewAlreadyExists(item);
		if (review != null) return review.modifyReview(commentary, rating);
		else{
			review = new Review(this, item, commentary, rating);
			reviews.add(review);
			item.addReview(review);
			return review;	
		}
	}
	
	public float getKarma(){
		
		float karma = 0;
		
		for(Review r : reviews){
			karma += r.getAverageOpinionRating();
		}
		karma /= reviews.size();
		
		System.out.println("Karma = "+karma);
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
	
	public String toString(){
		return "My pseudo is :" +pseudo+ " my password is :" +password+ " and my profile is :" +profile;
	}
	
	public static void main(String[] args){
		
	}

}
