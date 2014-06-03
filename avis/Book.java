package avis;

public class Book extends Item {

	private String author;
	private int nbPages;
	
	/**
	 * Constructor
	 * @param title
	 * @param genre
	 * @param author
	 * @param nbPages
	 */
	public Book(String title, String genre, String author, int nbPages) {
		
		super(title, genre);
		this.author = author;
		this.nbPages = nbPages;
	}

	public String toString(){

		return "Book :" +title+ " genre :" +genre+ " author:" +author+ " number pages :" +nbPages+ " average rating :" +average();
	}

	public static void main(String[] args){

	}
}
