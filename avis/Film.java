package avis;

import java.util.LinkedList;


public class Film extends Item {

	/**
	 * @uml.property  name="filmMaker"
	 */
	private String filmMaker;
	/**
	 * @uml.property  name="scriptWriter"
	 */
	private String scriptWriter;
	/**
	 * @uml.property  name="length"
	 */
	private int length;
	
	/**
	 * 
	 * @param title
	 * @param genre
	 * @param filmMaker
	 * @param scriptWriter
	 * @param length
	 */
	public Film(String title, String genre, String filmMaker, String scriptWriter, int length) {
		
		super(title, genre);
		this.filmMaker = filmMaker;
		this.scriptWriter = scriptWriter;
		this.length = length;
	}
	
	@Override
	public LinkedList<String> consultItem(LinkedList<String> ll) {
		
		ll.add(title);
		ll.add(genre);
		ll.add(filmMaker);
		ll.add(scriptWriter);
		ll.add(Integer.toString(length));
		ll.add(Float.toString(super.average()));
		
		return ll;
	}
	
	/**
	*/
	public static void main(String[] main){
		
	}
	
}
