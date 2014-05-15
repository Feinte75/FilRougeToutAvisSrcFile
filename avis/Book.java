package avis;


public class Book extends Item {

	/**
	 * @uml.property  name="author"
	 */
	private String author;
	/**
	 * @uml.property  name="nbPages"
	 */
	private int nbPages;
	
	public Book(String title, String genre, String author, int nbPages) {
		
		super(title, genre);
		this.author = author;
		this.nbPages = nbPages;
	}

	/**
	 *
	*/
	public static void main(String[] args){
		
	}

}
