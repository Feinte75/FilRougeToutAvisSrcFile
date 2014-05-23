package avis;

public class Film extends Item {

	private String filmMaker;

	private String scriptWriter;

	private int length;
	
	/**
	 * Constructor
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
	
	public String toString(){
		
		return "Film :" +title+ " genre :" +genre+ " film maker :" +filmMaker+ " script writer :" +scriptWriter+ " length :" +length+ " average rating :" +average();
	}
	
	public static void main(String[] main){
		
	}
}
