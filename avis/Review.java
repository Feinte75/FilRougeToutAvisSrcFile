package avis;


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
	 */
		
	public Review(Member member, Item item, String commentary, float rating){
		
		this.rating = rating;
		this.member = member;
		this.commentary = commentary;
		this.item = item;
	}

	public float getRating(){
		
		return rating;
	}
		
	public static void main(String[] args){

	}
}
