package avis;

import java.util.LinkedList;


public class Review {

	/**
	 * @uml.property  name="rating"
	 */
	private float rating;
	/**
	 * @uml.property  name="commentary"
	 */
	private String commentary;
	/**
	 * @uml.property  name="member"
	 * @uml.associationEnd  multiplicity="(1 1)" inverse="reviews:avis.Member"
	 */
	private Member member = null;
	/** 
	 * @uml.property name="item"
	 * @uml.associationEnd multiplicity="(1 1)" inverse="reviews:avis.Item"
	 */
	private Item item = null;
	
	/** 
	 * @uml.property name="review2"
	 * @uml.associationEnd multiplicity="(0 -1)" dimension="1" ordering="true" inverse="review1:avis.Review"
	 */
	private LinkedList<Review> opinionsReview;
		
	/**
	 */
		
	public Review(Member member, Item item, String commentary, float rating){
		
		this.rating = rating;
		this.member = member;
		this.commentary = commentary;
		this.item = item;
	}
	
	/**
	 * Modify and existing review
	 * @param commentary New commentary
	 * @param rating New rating
	 * @return 
	 */
	public Review modifyReview(String commentary, float rating){
		this.commentary = commentary;
		this.rating = rating;
		return this;
	}
	
	public void addOpinion(Review r){
		
		if(opinionsReview == null) opinionsReview = new LinkedList<Review>();
		
		opinionsReview.add(r);
	}
	
	public float getAverageOpinionRating(){
		
		float average = 0;
		
		for(Review r : opinionsReview){
			average += r.getRating();
		}
		return (average /= opinionsReview.size());
	}

	public float getRating(){
		
		return rating;
	}
	
	public Item getItem(){
		
		return item;
	}
	
	public static void main(String[] args){

	}
	
	
}
