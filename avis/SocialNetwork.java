package avis;

import java.util.LinkedList;

import exception.*;

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

	/** 
	 * @uml.property name="members"
	 * @uml.associationEnd multiplicity="(0 -1)" inverse="socialNetwork:avis.Member"
	 */
	private LinkedList<Member> members;
	/** 
	 * @uml.property name="items"
	 * @uml.associationEnd multiplicity="(0 -1)" aggregation="composite" inverse="socialNetwork:avis.Item"
	 */
	
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
	 * Obtenir le nombre de membres du <i>SocialNetwork</i>
	 * 
	 * @return le nombre de membres
	 */
	public int nbMembers() {
		
		return members.size();
	}

	/**
	 * Obtenir le nombre de films du <i>SocialNetwork</i>
	 * 
	 * @return le nombre de films
	 */
	public int nbFilms() {
		
		int nbFilms = 0;
		
		for(Item i : items){
			if(i instanceof Film) nbFilms++;
		}
		return nbFilms;
	}

	/**
	 * Obtenir le nombre de livres du <i>SocialNetwork</i>
	 * 
	 * @return le nombre de livres
	 */
	public int nbBooks() {

		int nbBooks = 0;

		for(Item i : items){
			if(i instanceof Book) nbBooks++;
		}
		return nbBooks;
	}


	/**
	 * Ajouter un nouveau membre au <i>SocialNetwork</i>
	 * 
	 * @param pseudo son pseudo
	 * @param password son mot de passe 
	 * @param profil un slogan choisi par le membre pour se définir
	 * 
	 * @throws BadEntry :
	 * <ul>
	 *  <li>  si le pseudo n'est pas instancié ou a moins de 1 caractère autre que des espaces .  </li>
	 *  <li>  si le password n'est pas instancié ou a moins de 4 caractères autres que des leadings or trailing blanks. </li>
	 *  <li>  si le profil n'est pas instancié.  </li>
	 * </ul><br>       
	 * 
	 * @throws MemberAlreadyExists membre de même pseudo déjà présent dans le <i>SocialNetwork</i> (même pseudo : indifférent à  la casse  et aux leadings et trailings blanks)
	 * 
	 */
	public void addMember(String pseudo, String password, String profile) throws BadEntry, MemberAlreadyExists  {
		
		if(badPseudoEntry(pseudo)) throw new BadEntry("");
		if(badPasswordEntry(password)) throw new BadEntry("");
		if(badProfileEntry(profile)) throw new BadEntry("");
		
		Member member = authenticate(pseudo, password);
		if (member != null) throw new MemberAlreadyExists();
		
		members.add(new Member(pseudo,password,profile));
	}


	/**
	 * Ajouter un nouvel item de film au <i>SocialNetwork</i> 
	 * 
	 * @param pseudo le pseudo du membre
	 * @param password le password du membre 
	 * @param titre le titre du film
	 * @param genre son genre (aventure, policier, etc.)
	 * @param realisateur le réalisateur
	 * @param scenariste le scénariste
	 * @param duree sa durée en minutes
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

		if (badPseudoEntry(pseudo)) throw new BadEntry("");
		if (badPasswordEntry(password)) throw new BadEntry("");
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

		if (badPseudoEntry(pseudo)) throw new BadEntry("");
		if (badPasswordEntry(password)) throw new BadEntry("");
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
		
		if (badPseudoEntry(pseudo)) throw new BadEntry("");
		if (badPasswordEntry(password)) throw new BadEntry("");
		if (badTitleEntry(title)) throw new BadEntry("");
		if (badCommentaryEntry(commentary)) throw new BadEntry("");
		if (badRatingEntry(rating)) throw new BadEntry("");
		
		Member member = authenticate(pseudo, password);
		if (member == null) throw new NotMember("");
		
		Item item = findItemFilm(title);
		if(item == null) throw new NotItem("");
		
		Review review = member.addReview(item, commentary, rating);	
		
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
		
		if (badPseudoEntry(pseudo)) throw new BadEntry("");
		if (badPasswordEntry(password)) throw new BadEntry("");
		if (badTitleEntry(title)) throw new BadEntry("");
		if (badCommentaryEntry(commentary)) throw new BadEntry("");
		if (badRatingEntry(rating)) throw new BadEntry("");
		
		Member member = authenticate(pseudo, password);
		if (member == null) throw new NotMember("");
		
		Item item = findItemBook(title);
		if(item == null) throw new NotItem("");
		
		Review review = member.addReview(item, commentary, rating);	
		return item.average();
	}

	/**
	 * 
	 * @param pseudo
	 * @param password
	 * @param commentaryAuthor
	 * @param title
	 * @param rating
	 * @param commentary
	 * @throws BadEntry
	 * @throws NotMember
	 * @throws NotItem
	 * @throws NotReview
	 */
	public void reviewOpinionBook(String pseudo, String password, String commentaryAuthor, String title, float rating, String commentary) throws BadEntry, NotMember, NotItem, NotReview{
		
		if (badPseudoEntry(pseudo)) throw new BadEntry("");
		if (badPasswordEntry(password)) throw new BadEntry("");
		if (badPseudoEntry(commentaryAuthor)) throw new BadEntry("");
		if (badTitleEntry(title)) throw new BadEntry("");
		if (badCommentaryEntry(commentary)) throw new BadEntry("");
		if (badRatingEntry(rating)) throw new BadEntry("");
		
		// Authenticate the author of the opinion about the review
		Member member = authenticate(pseudo, password);
		if (member == null) throw new NotMember("Member");
		
		// Find the autor of the review
		Member memberCriticated = userExists(commentaryAuthor);
		if (memberCriticated == null) throw new NotMember("Commentary Author");
		
		if(member == memberCriticated) throw new NotReview("Can't give an opinion about your own reviews");
		
		// Find the item book
		Item item = findItemBook(title);
		if(item == null) throw new NotItem("");
		
		// Find if the review exists
		Review review = memberCriticated.reviewAlreadyExists(item);
		if (review == null) throw new NotReview("Review doesn't exists");
		
		// Add the opinion
		review.addOpinion(new Review(member, item, commentary, rating));		
	}

	/**
	 * 
	 * @param pseudo
	 * @param password
	 * @param commentaryAuthor
	 * @param title
	 * @param rating
	 * @param commentary
	 * @throws BadEntry
	 * @throws NotMember
	 * @throws NotItem
	 * @throws NotReview
	 */
	public void reviewOpinionFilm(String pseudo, String password, String commentaryAuthor, String title, float rating, String commentary) throws BadEntry, NotMember, NotItem, NotReview{
		
		if (badPseudoEntry(pseudo)) throw new BadEntry("");
		if (badPasswordEntry(password)) throw new BadEntry("");
		if (badPseudoEntry(commentaryAuthor)) throw new BadEntry("");
		if (badTitleEntry(title)) throw new BadEntry("");
		if (badCommentaryEntry(commentary)) throw new BadEntry("");
		if (badRatingEntry(rating)) throw new BadEntry("");
		
		// Authenticate the author of the opinion about the review
		Member member = authenticate(pseudo, password);
		if (member == null) throw new NotMember("Member");
		
		// Find the autor of the review
		Member memberCriticated = userExists(commentaryAuthor);
		if (memberCriticated == null) throw new NotMember("Commentary Author");
		
		if(member == memberCriticated) throw new NotReview("Can't give an opinion about your own reviews");
		
		// Find the item book
		Item item = findItemFilm(title);
		if(item == null) throw new NotItem("");
		
		// Find if the review exists
		Review review = memberCriticated.reviewAlreadyExists(item);
		if (review == null) throw new NotReview("Review doesn't exists");
		
		// Add the opinion
		review.addOpinion(new Review(member, item, commentary, rating));
	}
	/**
	 * Obtenir une représentation textuelle du <i>SocialNetwork</i>.
	 * 
	 * @return la chaîne de caractères représentation textuelle du <i>SocialNetwork</i> 
	 */
	public String toString() {
		return "";
	}

	
	/**
	 * Permet de v�rifier que le pseudo est bien instanci� et d'une longueur sup�rieure � 1 
	 * @param pseudo pseudo du membre
	 * 
	 * @return boolean renvoie true si erreur, false si bonne syntaxe et instanci�
	 */
	
	public boolean badPseudoEntry(String pseudo){
		
		if(pseudo == null)return true;

		if(pseudo.trim().length() < 1)return true;
		else return false;
	}

	/**
	 * 
	 * @param password
	 * @return
	 */
	
	public boolean badPasswordEntry(String password){
		
		if(password == null)return true;
		
		if(password.trim().length() < 4)return true;
		else return false;	
	}
	
	/**
	 * Check if the profile has been instanciated and insure that he is more than 0 character length
	 * @param profile the member profile
	 * @return true when the profile is not instanciated or of 0 characters
	 */
	
	public boolean badProfileEntry(String profile){

		if(profile == null) return true;
		else return false;
	}
	
	/**
	 * 
	 * @param title 
	 * @return 
	 */
	
	public boolean badTitleEntry(String title){
		
		if(title == null)return true;
		 
		if(title.trim().length() < 1)return true;
		else return false;
	}
	
	public boolean badFilmMakerEntry(String filmMaker){

		if(filmMaker == null) return true;
		else return false;
	}
	
	public boolean badScriptWriterEntry(String scriptWriter){
		
		if(scriptWriter == null) return true;
		else return false;
	}
	
	public boolean badAuthorEntry(String author){
		
		if(author == null) return true;
		else return false;
	}
	
	public boolean badGenreEntry(String genre){

		if(genre == null) return true;
		else return false;
	}
	
	/**
	 * Permet de v�rifier que le commentaire est bien instanci�
	 */
	
	public boolean badCommentaryEntry(String commentary){
		
		if(commentary == null)return true;
		else return false;
	}
	
	/**
	 */
	public boolean badRatingEntry(float rating){
		
		if(rating < 0 || rating > 5) return true;
		else return false;	
	}

	/** 
	 * Permet d'authentifier un utilisateur lorsque celui ci rentre son pseudo/motdepasse.
	 * 
	 * @param pseudo pseudo du membre
	 * @param password son mot de passe
	 * 
	 * @return Renvoie le membre si il a �t� trouv�, renvoie null sinon
	 * 
	 */
	
	public Member authenticate(String pseudo, String password){
		
		Member memberFound = null;
		
		for (Member m : members){
			memberFound = m.authenticate(pseudo, password);
			if(memberFound != null) break;
		}
		
		return memberFound;
	}
	
	public Member userExists(String pseudo){

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
	 * @return The item with the title searchItem or null if not found
	 */
	public Item findItemBook(String searchItem){
		
		Item itemFound = null;

		for(Item search : items){
			itemFound = search.itemExists(searchItem);
			if(itemFound != null && itemFound instanceof Book)break;
			itemFound = null;
		}
		
		return itemFound;
	}
	
	/**
	 * 
	 * @param searchItem
	 * @return
	 */
	public Item findItemFilm(String searchItem){

		Item itemFound = null;

		for(Item search : items){
			itemFound = search.itemExists(searchItem);
			if(itemFound != null && itemFound instanceof Film)break;
			itemFound = null;
		}

		return itemFound;
	}
}
