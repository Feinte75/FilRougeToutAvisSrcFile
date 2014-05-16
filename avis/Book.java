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

	@Override
	public LinkedList<String> consultItem(LinkedList<String> ll) {
		
		ll.add("Book");
		ll.add(title);
		ll.add(genre);
		ll.add(author);
		ll.add(Integer.toString(nbPages));
		ll.add(Float.toString(super.average()));
		ll.add("");
		
		return ll;
	}

	public static void main(String[] args){

	}
}
