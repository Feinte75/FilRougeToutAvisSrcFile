package avis;

import java.util.Collection;


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
	private Collection<Review> reviews;
		
	/**
	*/
	public static void main(String[] args){
		
	}

}
