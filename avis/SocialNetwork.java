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
 * @date février - mars 2011
 * @version V0.6
 */


/** 
 * <p>
 * <b>Système de mutualisation d'opinions portant sur des domaines
 * variés (littérature, cinéma, art, gastronomie, etc.) et non limités.</b>
 * </p>
 * <p>
 * L'accès aux items et aux opinions qui leurs sont associées
 * est public. La création d'item et le dépôt d'opinion nécessite en revanche 
 * que l'utilisateur crée son profil au préalable.
 * </p>
 * <p>
 * Lorsqu'une méthode peut lever deux types d'exception, et que les conditions sont réunies 
 * pour lever l'une et l'autre, rien ne permet de dire laquelle des deux sera effectivement levée.
 * </p>
 * <p>
 * Dans une version avancée (version 2), une opinion peut également
 * être évaluée. Chaque membre se voit dans cette version décerner un "karma" qui mesure
 * la moyenne des notes portant sur les opinions qu'il a émises.
 * L'impact des opinions entrant dans le calcul de la note moyenne attribuée à un item
 * est pondéré par le karma des membres qui les émettent.
 * </p>
 */

public class SocialNetwork {

	// Holds the social network members
	private LinkedList<Member> members;
	
	// Holds the social network items
	private LinkedList<Item> items;
	
	/**
	 * constructeur de <i>SocialNetwok</i> 
	 * 
	 */

	/**
	 * Constructeur
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
	 *  <li>  If the pseudo is not instanciated of less than 1 character.  </li>
	 *  <li>  If the password is not instanciated or of less than 4 characters excluding leadings or trailing blanks. </li>
	 *  <li>  If the profile is not instanciated.  </li>
	 * </ul><br>       
	 * 
	 * @throws MemberAlreadyExists Member with same pseudo already exists in <i>SocialNetwork</i> (Same pseudo : Without leadings and trailings blanks and not case sensible)
	 * 
	 */
	public void addMember(String pseudo, String password, String profile) throws BadEntry, MemberAlreadyExists  {
		
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
	 *  <li>  si le pseudo n'est pas instancié ou a moins de 1 caractère autre que des espaces .  </li>
	 *  <li>  si le password n'est pas instancié ou a moins de 4 caractères autres que des leadings or trailing blanks. </li>
	 *  <li>  si le titre n'est pas instancié ou a moins de 1 caractère autre que des espaces.  </li>
	 *  <li>  si le genre n'est pas instancié. </li>
	 *  <li>  si le réalisateur n'est pas instancié. </li>
	 *  <li>  si le scénariste n'est pas instancié. </li>
	 *  <li>  si la durée n'est pas positive.  </li>
	 * </ul><br>       
	 * @throws NotMember : si le pseudo n'est pas celui d'un membre ou si le pseudo et le password ne correspondent pas.
	 * @throws ItemFilmAlreadyExists : item film de même titre  déjà présent (même titre : indifférent à  la casse  et aux leadings et trailings blanks)
	 * 
	 */
	public void addItemFilm(String pseudo, String password, String title, String genre, String filmMaker, String scriptWriter, int length) throws BadEntry, NotMember, ItemFilmAlreadyExists {

		if(badPseudoEntry(pseudo)) throw new BadEntry("Bad pseudo entry");
		if(badPasswordEntry(password)) throw new BadEntry("Bad password entry");
		if (badTitleEntry(title)) throw new BadEntry("");
		if (badGenreEntry(genre)) throw new BadEntry("");
		if (badFilmMakerEntry(filmMaker)) throw new BadEntry("");
		if (badScriptWriterEntry(scriptWriter)) throw new BadEntry("");
		if (length <= 0) throw new BadEntry("");
		
		Member member = authenticate(pseudo, password);
		if (member == null) throw new NotMember("");
		
		Item item = findItemFilm(title);
		if (item != null) throw new ItemFilmAlreadyExists();
		
		items.add(new Film(title, genre, filmMaker, scriptWriter, length));
	}

	/**
	 * Ajouter un nouvel item de livre au <i>SocialNetwork</i> 
	 * 
	 * @param pseudo le pseudo du membre
	 * @param password le password du membre 
	 * @param titre le titre du livre
	 * @param genre son genre (roman, essai, etc.)
	 * @param auteur l'auteur
	 * @param nbPages le nombre de pages
	 * 
	 * @throws BadEntry :
	 * <ul>
	 *  <li>  si le pseudo n'est pas instancié ou a moins de 1 caractère autre que des espaces .  </li>
	 *  <li>  si le password n'est pas instancié ou a moins de 4 caractères autres que des leadings or trailing blanks. </li>
	 *  <li>  si le titre n'est pas instancié ou a moins de 1 caractère autre que des espaces.  </li>
	 *  <li>  si le genre n'est pas instancié. </li>
	 *  <li>  si l'auteur n'est pas instancié. </li>
	 *  <li>  si le nombre de pages n'est pas positif.  </li>
	 * </ul><br>       
	 * @throws NotMember : si le pseudo n'est pas celui d'un membre ou si le pseudo et le password ne correspondent pas.
	 * @throws ItemBookAlreadyExists item livre de même titre  déjà présent (même titre : indifférent à la casse  et aux leadings et trailings blanks)
	 * 
	 * 
	 */
	public void addItemBook(String pseudo, String password, String title, String genre, String author, int nbPages) throws  BadEntry, NotMember, ItemBookAlreadyExists{

		if(badPseudoEntry(pseudo)) throw new BadEntry("Bad pseudo entry");
		if(badPasswordEntry(password)) throw new BadEntry("Bad password entry");
		if (badTitleEntry(title)) throw new BadEntry("");
		if (badGenreEntry(genre)) throw new BadEntry("");
		if (badAuthorEntry(author)) throw new BadEntry("");
		if (nbPages <= 0) throw new BadEntry("");
		
		Member member = authenticate(pseudo, password);
		if (member == null) throw new NotMember("");
		
		Item item = findItemBook(title);
		if (item != null) throw new ItemBookAlreadyExists();
		
		items.add(new Book(title, genre, author, nbPages));
	}

	/**
	 * Consulter les items du <i>SocialNetwork</i> par nom
	 * 
	 * @param nom son nom (eg. titre d'un film, d'un livre, etc.)
	 * 
	 * @throws BadEntry : si le nom n'est pas instancié ou a moins de 1 caractère autre que des espaces.  </li>
	 * 
	 * @return LinkedList <String> : la liste des représentations de tous les items ayant ce nom 
	 * Cette représentation contiendra la note de l'item s'il a été noté.
	 * (une liste vide si aucun item ne correspond) 
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
	 * Donner son opinion sur un item film.
	 * Ajoute l'opinion de ce membre sur ce film au <i>SocialNetwork</i> 
	 * Si une opinion de ce membre sur ce film  préexiste, elle est mise à jour avec ces nouvelles valeurs.
	 * 
	 * @param pseudo pseudo du membre émettant l'opinion
	 * @param password son mot de passe
	 * @param titre titre du film  concerné
	 * @param note la note qu'il donne au film 
	 * @param commentaire ses commentaires
	 * 
	 * @throws BadEntry :
	 * <ul>
	 *  <li>  si le pseudo n'est pas instancié ou a moins de 1 caractère autre que des espaces .  </li>
	 *  <li>  si le password n'est pas instancié ou a moins de 4 caractères autres que des leadings or trailing blanks. </li>
	 *  <li>  si le titre n'est pas instancié ou a moins de 1 caractère autre que des espaces.  </li>
	 *  <li>  si la note n'est pas comprise entre 0.0 et 5.0. </li>
	 *  <li>  si le commentaire n'est pas instancié. </li>
	 * </ul><br>       
	 * @throws NotMember : si le pseudo n'est pas celui d'un membre ou si le pseudo et le password ne correspondent pas.
	 * @throws NotItem : si le titre n'est pas le titre d'un film.
	 * 
	 * @return la note moyenne des notes sur ce film  
	 */
	public float reviewItemFilm(String pseudo, String password, String title, float rating, String commentary) throws BadEntry, NotMember, NotItem {
		
		if(badPseudoEntry(pseudo)) throw new BadEntry("Bad pseudo entry");
		if(badPasswordEntry(password)) throw new BadEntry("Bad password entry");
		if (badTitleEntry(title)) throw new BadEntry("");
		if (badCommentaryEntry(commentary)) throw new BadEntry("");
		if (badRatingEntry(rating)) throw new BadEntry("");
		
		Member member = authenticate(pseudo, password);
		if (member == null) throw new NotMember("");
		
		Item item = findItemFilm(title);
		if(item == null) throw new NotItem("");
		
		member.addReview(item, commentary, rating);	
		
		return item.average();
	}


	/**
	 * Donner son opinion sur un item livre.
	 * Ajoute l'opinion de ce membre sur ce livre au <i>SocialNetwork</i> 
	 * Si une opinion de ce membre sur ce livre  préexiste, elle est mise à jour avec ces nouvelles valeurs.
	 * 
	 * @param pseudo pseudo du membre émettant l'opinion
	 * @param password son mot de passe
	 * @param titre titre du livre  concerné
	 * @param note la note qu'il donne au livre 
	 * @param commentaire ses commentaires
	 * 
	 * @throws BadEntry :
	 * <ul>
	 *  <li>  si le pseudo n'est pas instancié ou a moins de 1 caractère autre que des espaces .  </li>
	 *  <li>  si le password n'est pas instancié ou a moins de 4 caractères autres que des leadings or trailing blanks. </li>
	 *  <li>  si le titre n'est pas instancié ou a moins de 1 caractère autre que des espaces.  </li>
	 *  <li>  si la note n'est pas comprise entre 0.0 et 5.0. </li>
	 *  <li>  si le commentaire n'est pas instancié. </li>
	 * </ul><br>       
	 * @throws NotMember : si le pseudo n'est pas celui d'un membre ou si le pseudo et le password ne correspondent pas.
	 * @throws NotItem : si le titre n'est pas le titre d'un livre.
	 * 
	 * @return la note moyenne des notes sur ce livre
	 */
	
	public float reviewItemBook(String pseudo, String password, String title, float rating, String commentary) throws BadEntry, NotMember, NotItem {
		
		if(badPseudoEntry(pseudo)) throw new BadEntry("Bad pseudo entry");
		if(badPasswordEntry(password)) throw new BadEntry("Bad password entry");
		if (badTitleEntry(title)) throw new BadEntry("");
		if (badCommentaryEntry(commentary)) throw new BadEntry("");
		if (badRatingEntry(rating)) throw new BadEntry("");
		
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
	 * @param commentaryAuthor Pseudo of the author of the review which will be commented
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
	 */
	public float reviewOpinionBook(String pseudo, String password, String commentaryAuthor, String title, float rating, String commentary) throws BadEntry, NotMember, NotItem, NotReview{
		
		if(badPseudoEntry(pseudo)) throw new BadEntry("Bad pseudo entry");
		if(badPasswordEntry(password)) throw new BadEntry("Bad password entry");
		if (badPseudoEntry(commentaryAuthor)) throw new BadEntry("");
		if (badTitleEntry(title)) throw new BadEntry("");
		if (badCommentaryEntry(commentary)) throw new BadEntry("");
		if (badRatingEntry(rating)) throw new BadEntry("");
		
		// Authenticate the author of the opinion about the review
		Member member = authenticate(pseudo, password);
		if (member == null) throw new NotMember("Member");
		
		// Find the author of the review
		Member memberCriticated = userExists(commentaryAuthor);
		if (memberCriticated == null) throw new NotMember("Commentary Author");
		
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
	 * Allow users to add opinions over members reviews. This opinions ratings influences the weight of the author of the review during
	 * the computation of the item's average rating. If a review has no opinions, it is considered as trustfull (karma = 1). The karma of a member
	 * is defined by an average of all the opinions overs the member's review and is between 0 and 1.
	 * 
	 * @param pseudo Opinion's author pseudo
	 * @param password Opinion's author password
	 * @param commentaryAuthor Pseudo of the author of the review which will be commented
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
	 */
	public float reviewOpinionFilm(String pseudo, String password, String commentaryAuthor, String title, float rating, String commentary) throws BadEntry, NotMember, NotItem, NotReview{
		
		if(badPseudoEntry(pseudo)) throw new BadEntry("Bad pseudo entry");
		if(badPasswordEntry(password)) throw new BadEntry("Bad password entry");
		if (badPseudoEntry(commentaryAuthor)) throw new BadEntry("");
		if (badTitleEntry(title)) throw new BadEntry("");
		if (badCommentaryEntry(commentary)) throw new BadEntry("");
		if (badRatingEntry(rating)) throw new BadEntry("");
		
		// Authenticate the author of the opinion about the review
		Member member = authenticate(pseudo, password);
		if (member == null) throw new NotMember("Member");
		
		// Find the author of the review
		Member memberCriticated = userExists(commentaryAuthor);
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
	 * Obtenir une représentation textuelle du <i>SocialNetwork</i>.
	 * 
	 * @return la chaîne de caractères représentation textuelle du <i>SocialNetwork</i> 
	 */
	public String toString() {
		
		String rpz = "";
		
		for(Member m : members){
			rpz += m;
		}
		
		for(Item m : items){
			rpz += m;
		}
		return rpz;
	}

	/**
	 * Check if the pseudo has been instanciated and insure that it is more than 1 character length
	 * @param pseudo Member's pseudo
	 * @return boolean True when the pseudo is not instanciated or of less than 1 character, false either
	 */
	
	private boolean badPseudoEntry(String pseudo){
		
		if(pseudo == null)return true;

		if(pseudo.trim().length() < 1)return true;
		else return false;
	}

	/**
	 *
	 * @param password
	 * @return boolean True when the password is not instanciated or of less than 4 characters, false either
	 */
	
	private boolean badPasswordEntry(String password){
		
		if(password == null)return true;
		
		if(password.trim().length() < 4)return true;
		else return false;	
	}
	
	/**
	 * Check if the profile has been instanciated and insure that it is more than 0 character length
	 * @param profile The member profile
	 * @return boolean True when the profile is not instanciated or of 0 characters, false either
	 */
	
	private boolean badProfileEntry(String profile){

		if(profile == null) return true;
		else return false;
	}
	
	private boolean badTitleEntry(String title){
		
		if(title == null)return true;
		 
		if(title.trim().length() < 1)return true;
		else return false;
	}
	
	private boolean badFilmMakerEntry(String filmMaker){

		if(filmMaker == null) return true;
		else return false;
	}
	
	private boolean badScriptWriterEntry(String scriptWriter){
		
		if(scriptWriter == null) return true;
		else return false;
	}
	
	private boolean badAuthorEntry(String author){
		
		if(author == null) return true;
		else return false;
	}
	
	private boolean badGenreEntry(String genre){

		if(genre == null) return true;
		else return false;
	}
	
	private boolean badCommentaryEntry(String commentary){
		
		if(commentary == null)return true;
		else return false;
	}

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
	 * Look for a pseudo in the members Linked List
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
	
	// Tests unitaires
	public static void main(String[] args){
		SocialNetwork sn = new SocialNetwork();
		//ok
	}
}
