package avis;


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
	
	public Film(String title, String genre, String filmMaker, String scriptWriter, int length) {
		
		super(title, genre);
		this.filmMaker = filmMaker;
		this.scriptWriter = scriptWriter;
		this.length = length;
	}
	/**
	*/
	public static void main(String[] main){
		
	}

}
