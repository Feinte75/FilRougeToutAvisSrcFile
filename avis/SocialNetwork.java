package avis;

import java.util.LinkedList;

import exception.BadEntry;
import exception.ItemBookAlreadyExists;
import exception.ItemFilmAlreadyExists;
import exception.MemberAlreadyExists;
import exception.NotItem;
import exception.NotMember;
import exception.NotReview;

/** 
 * @author A. Beugnard, 
 * @author G. Ouvradou
 * @author B. Prou
 * @author G. Feunteun
 * @author J. Bili-Prodhomme
 * @date mai - juin 2014 
 * @version V 1.0
 */


/** 
 * <p>
 * <b> Opinions mutualisation system about cinema and literature.  </b>
 * </p>
 * <p>
 * <b> Items and opinions consultation is free for all. Item creation and 
 * the ability to give opinions are restricted to members only </b>
 * </p>
 * <p>
 * <b> Registration recquires a pseudo, a password and a sentence describing the member </b>
 * </p>
 * <p>
 * <b> The system is in version 1.0 and includes a karma system allowing members to rate each others reviews
 * leading to a smoother item rating. </b>
 * </p> 
 */

public class SocialNetwork {

	// Holds the social network members
	private LinkedList<Member> members;
	
	// Holds the social network items
	private LinkedList<Item> items;
	
	/**
	 * constructor of <i>SocialNetwok</i> 
	 * 
	 */

	/**
	 * Constructor
	 */
	public SocialNetwork() {
		
		members = new LinkedList<Member>();
		items = new LinkedList<Item>();
	}

	/**
	 * Return the number of members from the <i>SocialNetwork</i>
	 * 
	 * @return number of Members
	 */
	public int nbMembers() {
		
		return members.size();
	}

	/**
	 * Return the number of Films from <i>SocialNetwork</i>
	 * 
	 * @return number of Films
	 */
	public int nbFilms() {
		
		int nbFilms = 0;
		
		// Iterate over items and only count the instances of Film
		for(Item i : items){
			if(i instanceof Film) nbFilms++;
		}
		return nbFilms;
	}

	/**
	 * Return the number of Books <i>SocialNetwork</i>
	 * 
	 * @return number of Books
	 */
	public int nbBooks() {

		int nbBooks = 0;
		
		// Iterate over items and only count the instances of Book
		for(Item i : items){
			if(i instanceof Book) nbBooks++;
		}
		return nbBooks;
	}


	/**
	 * Add a new member to the <i>SocialNetwork</i>
	 * 
	 * @param pseudo pseudo of the new member
	 * @param password password
	 * @param profile A sentence chosen by the member to describe himself
	 * 
	 * @throws BadEntry :
	 * <ul>
	 *  <li>  If the pseudo is not instantiated or less than 1 character.  </li>
	 *  <li>  If the password is not instantiated or of less than 4 characters excluding leadings or trailing blanks. </li>
	 *  <li>  If the profile is not instantiated.  </li>
	 * </ul><br>       
	 * 
	 * @throws MemberAlreadyExists Member with same pseudo already exists in <i>SocialNetwork</i> (Same pseudo : Without leadings and trailings blanks and not case sensible)
	 * 
	 */
	public void addMember(String pseudo, String password, String profile) throws BadEntry, MemberAlreadyExists  {
		
		// Bad entry exception checking
		if(badPseudoEntry(pseudo)) throw new BadEntry("Bad pseudo entry");
		if(badPasswordEntry(password)) throw new BadEntry("Bad password entry");
		if(badProfileEntry(profile)) throw new BadEntry("Bad profile entry");
		
		// Check if the pseudo is already used
		Member member = userExists(pseudo);
		if (member != null) throw new MemberAlreadyExists();
		
		members.add(new Member(pseudo.trim(),password.trim(),profile));
	}


	/**
	 * Add a Film item to the <i>SocialNetwork</i> 
	 * 
	 * @param pseudo pseudo of the member
	 * @param password password of the member 
	 * @param titre title of the Film
	 * @param genre genre (action, thriller, etc.)
	 * @param filmMaker the film maker
	 * @param scriptWriter the script writer
	 * @param length length in minutes
	 * 
	 * @throws BadEntry :
	 * <ul>
	 *  <li>  If the pseudo is not instantiated or is less than 1 character.  </li>
	 *  <li>  If the password is not instantiated or less than 4 characters excluding leadings or trailing blanks. </li>
	 *  <li>  If the title is not instantiated or has fewer than a character (other than spaces) </li>
	 *  <li>  If the type is not instantiated </li>
	 *  <li>  If the realisator is not instantiated</li>
	 *  <li>  If the scriptwriter is not instantiated </li>
	 *  <li>  If the duration is not positive  </li>
	 * </ul><br>       
	 * @throws NotMember : If the pseudo doesn't belong to a member or if the pseudo and the password don't match
	 * @throws ItemFilmAlreadyExists : Film's title already exists (same title : case-insensitive,  leadings and trailings blanks insensitive)
	 * 
	 */
	public void addItemFilm(String pseudo, String password, String title, String genre, String filmMaker, String scriptWriter, int length) throws BadEntry, NotMember, ItemFilmAlreadyExists {

		// Bad entry exception checking
		if(badPseudoEntry(pseudo)) throw new BadEntry("Bad pseudo entry");
		if(badPasswordEntry(password)) throw new BadEntry("Bad password entry");
		if (badTitleEntry(title)) throw new BadEntry("Bad title entry");
		if (badGenreEntry(genre)) throw new BadEntry("Bad genre entry");
		if (badFilmMakerEntry(filmMaker)) throw new BadEntry("Bad film maker entry");
		if (badScriptWriterEntry(scriptWriter)) throw new BadEntry("Bad script writer entry");
		if (length <= 0) throw new BadEntry("Bad length entry");
		
		Member member = authenticate(pseudo, password);
		if (member == null) throw new NotMember("");
		
		Item item = findItemFilm(title);
		if (item != null) throw new ItemFilmAlreadyExists();
		
		items.add(new Film(title, genre, filmMaker, scriptWriter, length));
	}

	/**
	 * Add a new book item <i>SocialNetwork</i> 
	 * 
	 * @param pseudo pseudo of the member
	 * @param password password of the member
	 * @param titre title of the book
	 * @param genre type of the book (novel, essai, etc.)
	 * @param auteur author of the book
	 * @param nbPages number of pages
	 * 
	 * @throws BadEntry :
	 * <ul>
	 *  <li>  If the pseudo is not instantiated or is less than 1 character.  </li>
	 *  <li>  If the password is not instantiated or of less than 4 characters excluding leadings or trailing blanks. </li>
	 *  <li>  If the title is not instantiated or has fewer than a character (other than spaces).  </li>
	 *  <li>  If the type is not instantiated. </li>
	 *  <li>  If the author is not instantiated. </li>
	 *  <li>  If the number of pages is not positive.  </li>
	 * </ul><br>       
	 * @throws NotMember : If the pseudo doesn't belong to a member or if the pseudo and the password don't match.
	 * @throws ItemBookAlreadyExists : Film's title already exists (same title : case-insensitive,  leadings and trailings blanks insensitive)
	 * 
	 * 
	 */
	public void addItemBook(String pseudo, String password, String title, String genre, String author, int nbPages) throws  BadEntry, NotMember, ItemBookAlreadyExists{

		// Bad entry exception checking
		if(badPseudoEntry(pseudo)) throw new BadEntry("Bad pseudo entry");
		if(badPasswordEntry(password)) throw new BadEntry("Bad password entry");
		if (badTitleEntry(title)) throw new BadEntry("Bad title entry");
		if (badGenreEntry(genre)) throw new BadEntry("Bad genre entry");
		if (badAuthorEntry(author)) throw new BadEntry("");
		if (nbPages <= 0) throw new BadEntry("Bad nbPages entry");
		
		Member member = authenticate(pseudo, password);
		if (member == null) throw new NotMember("");
		
		Item item = findItemBook(title);
		if (item != null) throw new ItemBookAlreadyExists();
		
		items.add(new Book(title, genre, author, nbPages));
	}

	/**
	 * Give your opinion on this film
	 * Add the opinion of this member on this film on the <i>SocialNetwork</i> 
	 * If an opinion of this member on this film already exists, it's updated with the new values
	 * 
	 * @param pseudo pseudo of the member
	 * @param password password of the member
	 * @param titre title of the film
	 * @param note rate given to the film
	 * @param commentaire comments of the member
	 * 
	 * @throws BadEntry :
	 * <ul>
	 *  <li>  If the pseudo is not instantiated or is less than 1 character .  </li>
	 *  <li>  If the password is not instantiated or of less than 4 characters excluding leadings or trailing blanks. </li>
	 *  <li>  If the title is not instantiated or has fewer than a character (other than spaces).  </li>
	 *  <li>  If the rate is not between 0.0 and 5.0. </li>
	 *  <li>  If the comment is not instantiated. </li>
	 * </ul><br>       
	 * @throws NotMember : If the pseudo doesn't belong to a member or if the pseudo and the password don't match.
	 * @throws NotItem : If the title is not the title of a film.
	 * 
	 * @return The average of the rating on this film
	 */
	public float reviewItemFilm(String pseudo, String password, String title, float rating, String commentary) throws BadEntry, NotMember, NotItem {
		
		// Bad entry exception checking
		if(badPseudoEntry(pseudo)) throw new BadEntry("Bad pseudo entry");
		if(badPasswordEntry(password)) throw new BadEntry("Bad password entry");
		if (badTitleEntry(title)) throw new BadEntry("Bad title entry");
		if (badCommentaryEntry(commentary)) throw new BadEntry("Bad commentary entry");
		if (badRatingEntry(rating)) throw new BadEntry("Bad rating entry");
		
		Member member = authenticate(pseudo, password);
		if (member == null) throw new NotMember("");
		
		Item item = findItemFilm(title);
		if(item == null) throw new NotItem("");
		
		member.addReview(item, commentary, rating);	
		
		return item.average();
	}


	/**
	 * Give your opinion on this film.
	 * Add the opinion of this member on this film on the <i>SocialNetwork</i> 
	 * If an opinion of this member on this film already exists, it's updated with the new values
	 * 
	 * @param pseudo pseudo of the member
	 * @param password password of the member
	 * @param titre title of the book
	 * @param note rate given on the film
	 * @param commentaire comments of the member
	 * 
	 * @throws BadEntry :
	 * <ul>
	 *  <li>  If the pseudo is not instantiated or is less than 1 character .  </li>
	 *  <li>  If the password is not instantiated or of less than 4 characters excluding leadings or trailing blanks. </li>
	 *  <li>  If the title is not instantiated or has fewer than a character (other than spaces).  </li>
	 *  <li>  If the rate is not between 0.0 and 5.0. </li>
	 *  <li>  If the comment is not instantiated. </li>
	 * </ul><br>       
	 * @throws NotMember : If the pseudo doesn't belong to a member or if the pseudo and the password don't match.
	 * @throws NotItem : If the title is not the title of a book.
	 * 
	 * @return The average of the rating on this book
	 */
	
	public float reviewItemBook(String pseudo, String password, String title, float rating, String commentary) throws BadEntry, NotMember, NotItem {
		
		// Bad entry exception checking
		if(badPseudoEntry(pseudo)) throw new BadEntry("Bad pseudo entry");
		if(badPasswordEntry(password)) throw new BadEntry("Bad password entry");
		if (badTitleEntry(title)) throw new BadEntry("Bad title entry");
		if (badCommentaryEntry(commentary)) throw new BadEntry("Bad commentary entry");
		if (badRatingEntry(rating)) throw new BadEntry("Bad rating entry");
		
		Member member = authenticate(pseudo, password);
		if (member == null) throw new NotMember("");
		
		Item item = findItemBook(title);
		if(item == null) throw new NotItem("");
		
		member.addReview(item, commentary, rating);	
		
		return item.average();
	}

	/**
	 * Allow users to add opinions over members reviews. This opinions ratings influences the weight of the author of the review during
	 * the computation of the item's average rating. If a review has no opinions, it is considered as trustfull (karma = 1). The karma of a member
	 * is defined by an average of all the opinions overs the member's review and is between 0 and 1.
	 * 
	 * @param pseudo Opinion's author pseudo
	 * @param password Opinion's author password
	 * @param reviewAuthorPseudo Pseudo of the author of the review which will be commented
	 * @param title title of the book
	 * @param rating 
	 * @param commentary
	 * 
	 * @throws BadEntry
	 * @throws NotMember
	 * <ul>
	 *  <li> If there is no member with the pseudo or if the couple pseudo / password doesn't match</li>
	 *  <li> If there is no member with the pseudo of commentary author 
	 * </ul>
	 * @throws NotItem
	 * @throws NotReview when the review doesn't exists
	 * 
	 * @return the item average rating taking into account the new karma 
	 */
	public float reviewOpinionBook(String pseudo, String password, String reviewAuthorPseudo, String title, float rating, String commentary) throws BadEntry, NotMember, NotItem, NotReview{
		
		// Bad entry exception checking
		if(badPseudoEntry(pseudo)) throw new BadEntry("Bad pseudo entry");
		if(badPasswordEntry(password)) throw new BadEntry("Bad password entry");
		if (badPseudoEntry(reviewAuthorPseudo)) throw new BadEntry("Bad review author pseudo entry");
		if (badTitleEntry(title)) throw new BadEntry("Bad title entry");
		if (badCommentaryEntry(commentary)) throw new BadEntry("Bad commentary entry");
		if (badRatingEntry(rating)) throw new BadEntry("Bad rating entry");
		
		// Authenticate the author of the opinion about the review
		Member member = authenticate(pseudo, password);
		if (member == null) throw new NotMember("Member");
		
		// Find the author of the review
		Member memberCriticated = userExists(reviewAuthorPseudo);
		if (memberCriticated == null) throw new NotMember("Commentary Author");
		
		// Check if the author of the review tries to review himself
		if(member == memberCriticated) throw new NotReview("Can't give an opinion about your own reviews");
		
		// Find the item book
		Item item = findItemBook(title);
		if(item == null) throw new NotItem("Item doesn't exists");
		
		// Find if the review exists
		Review review = memberCriticated.reviewAlreadyExists(item);
		if (review == null) throw new NotReview("Review doesn't exists");
		
		// Add the opinion to the review
		review.addOpinion(new Review(member, item, commentary, rating));
		
		return item.average();
	}

	/**
	 * Allow users to add opinions over member reviews. This opinions ratings influences the weight of the author of the review during
	 * the computation of the item's average rating. If a review has no opinions, it is considered as trustfull (karma = 1). The karma of a member
	 * is defined by an average of all the opinions overs the member's review and is between 0 and 1.
	 * 
	 * @param pseudo Opinion's author pseudo
	 * @param password Opinion's author password
	 * @param reviewAuthorPseudo Pseudo of the author of the review which will be commented
	 * @param title title of the Film
	 * @param rating 
	 * @param commentary
	 * 
	 * @throws BadEntry
	 * @throws NotMember
	 * <ul>
	 *  <li> If there is no member with the pseudo or if the couple pseudo / password doesn't match</li>
	 *  <li> If there is no member with the pseudo of commentary author <\li>
	 * </ul>
	 * @throws NotItem
	 * @throws NotReview when the review doesn't exists
	 * 
	 * @return the item average rating taking into account the new karma 
	 */
	public float reviewOpinionFilm(String pseudo, String password, String reviewAuthorPseudo, String title, float rating, String commentary) throws BadEntry, NotMember, NotItem, NotReview{
		
		// Bad entry exception checking
		if(badPseudoEntry(pseudo)) throw new BadEntry("Bad pseudo entry");
		if(badPasswordEntry(password)) throw new BadEntry("Bad password entry");
		if (badPseudoEntry(reviewAuthorPseudo)) throw new BadEntry("Bad review author pseudo entry");
		if (badTitleEntry(title)) throw new BadEntry("Bad title entry");
		if (badCommentaryEntry(commentary)) throw new BadEntry("Bad commentary entry");
		if (badRatingEntry(rating)) throw new BadEntry("Bad rating entry");
		
		// Authenticate the author of the opinion about the review
		Member member = authenticate(pseudo, password);
		if (member == null) throw new NotMember("Member");
		
		// Find the author of the review
		Member memberCriticated = userExists(reviewAuthorPseudo);
		if (memberCriticated == null) throw new NotMember("Commentary Author");
		
		if(member == memberCriticated) throw new NotReview("Can't give an opinion about your own reviews");
		
		// Find the item book
		Item item = findItemFilm(title);
		if(item == null) throw new NotItem("Item doesn't exists");
		
		// Find if the review exists
		Review review = memberCriticated.reviewAlreadyExists(item);
		if (review == null) throw new NotReview("Review doesn't exists");
		
		// Add the opinion to the review
		review.addOpinion(new Review(member, item, commentary, rating));
		
		return item.average();
	}
	
	/**
	 * Gives a textual representation of the <i>SocialNetwork</i>.
	 * 
	 * @return A String with every members and items included <i>SocialNetwork</i> 
	 */
	public String toString() {
		
		String rpz = "";
		
		for(Member m : members){
			rpz += m + "\n";
		}
		
		for(Item m : items){
			rpz += m + "\n";
		}
		return rpz;
	}

	/**
	 * Consult <i>SocialNetwork</i> items by name
	 * 
	 * @param title : The title of the item (Film, Book)
	 * 
	 * @throws BadEntry : If the name is not instantiated or is less than 1 character.  </li>
	 * 
	 * @return LinkedList <String> :  A list with every representations of the items with the same title
	 * The representation includes the rating.
	 * The list is empty if no items were found
	 */
	public LinkedList <String> consultItems(String title) throws BadEntry {
		
		LinkedList<String> ll = new LinkedList<String>();
		
		if(badTitleEntry(title)) throw new BadEntry("");
		
		Item item = findItemBook(title);
		if(item != null){
			ll.add(item.toString());
		}
		
		item = findItemFilm(title);
		if(item != null){
			ll.add(item.toString());
		}
		
		return ll;
	}
	
	/**
	 * Check if the pseudo has been instantiated and insure that it is more than 1 character length
	 * @param pseudo Member's pseudo
	 * @return boolean True when the pseudo is not instantiated or of less than 1 character, false either
	 */
	private boolean badPseudoEntry(String pseudo){
		
		if(pseudo == null)return true;

		if(pseudo.trim().length() < 1)return true;
		else return false;
	}

	/**
	 *
	 * @param password
	 * @return boolean True when the password is not instantiated or of less than 4 characters, false either
	 */
	private boolean badPasswordEntry(String password){
		
		if(password == null)return true;
		
		if(password.trim().length() < 4)return true;
		else return false;	
	}
	
	/**
	 * Check if the profile has been instantiated and insure that it is more than 0 character length
	 * @param profile The member profile
	 * @return boolean True when the profile is not instantiated or of 0 characters, false either
	 */
	private boolean badProfileEntry(String profile){

		if(profile == null) return true;
		else return false;
	}
	
	/**
	 * 
	 * @param title
	 * @return
	 */
	private boolean badTitleEntry(String title){
		
		if(title == null)return true;
		 
		if(title.trim().length() < 1)return true;
		else return false;
	}
	
	/**
	 * 
	 * @param filmMaker
	 * @return
	 */
	private boolean badFilmMakerEntry(String filmMaker){

		if(filmMaker == null) return true;
		else return false;
	}
	
	/**
	 * 
	 * @param scriptWriter
	 * @return
	 */
	private boolean badScriptWriterEntry(String scriptWriter){
		
		if(scriptWriter == null) return true;
		else return false;
	}
	
	/**
	 * 
	 * @param author
	 * @return
	 */
	private boolean badAuthorEntry(String author){
		
		if(author == null) return true;
		else return false;
	}
	
	/**
	 * 
	 * @param genre
	 * @return
	 */
	private boolean badGenreEntry(String genre){

		if(genre == null) return true;
		else return false;
	}
	
	/**
	 * 
	 * @param commentary
	 * @return
	 */
	private boolean badCommentaryEntry(String commentary){
		
		if(commentary == null)return true;
		else return false;
	}

	/**
	 * 
	 * @param rating
	 * @return
	 */
	private boolean badRatingEntry(float rating){
		
		if(rating < 0 || rating > 5) return true;
		else return false;	
	}

	/** 
	 * Allow a user to authenticate when the couple pseudo / password is correct
	 * @param pseudo Member's pseudo
	 * @param password Member's password
	 * 
	 * @return Member The member corresponding to the pseudo / password or null if not found
	 * 
	 */
	
	private Member authenticate(String pseudo, String password){
		
		Member memberFound = null;
		
		for (Member m : members){
			memberFound = m.authenticate(pseudo, password);
			// When the member is found, break the while and return the member
			if(memberFound != null) break;
		}
		return memberFound;
	}
	
	/**
	 * Look for a pseudo in the member's Linked List
	 * @param pseudo the pseudo to find in the linked list
	 * @return Member The member with the pseudo or null if not found
	 */
	private Member userExists(String pseudo){

		Member memberFound = null;

		for (Member m : members){
			memberFound = m.userExists(pseudo);
			if(memberFound != null) break;
		}

		return memberFound;
		
	}

	/**
	 * 
	 * @param searchItem
	 * @return Item The book with the title searchBook or null if not found
	 */
	private Item findItemBook(String searchBook){
		
		Item itemFound = null;

		for(Item search : items){
			// Look for the item entitled searchBook
			itemFound = search.itemExists(searchBook);
			// Check if the item found is an instance of book
			if(itemFound != null && itemFound instanceof Book)break;
			itemFound = null;
		}
		
		return itemFound;
	}
	
	/**
	 * 
	 * @param searchFilm The name of the film searched
	 * @return Item The film with the title searchFilm or null if not found
	 */
	private Item findItemFilm(String searchFilm){

		Item itemFound = null;

		for(Item search : items){
			itemFound = search.itemExists(searchFilm);
			if(itemFound != null && itemFound instanceof Film)break;
			itemFound = null;
		}

		return itemFound;
	}
	
	
	public static void main(String[] args){
	}
}
