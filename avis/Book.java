package avis;

import java.util.LinkedList;


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

	public String toString(){

		return "Book :" +title+ " genre :" +genre+ " author:" +author+ " number pages :" +nbPages+ " average rating :" +average();
	}

	public static void main(String[] args){

	}
}
