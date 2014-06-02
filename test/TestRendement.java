package test;

import java.util.UUID;

import avis.SocialNetwork;

public class TestRendement {
	
	private static int nbMembers=500;
	private static int nbItems=5000;
	
	public static void generateMembers(SocialNetwork sn){
		
		for(int i = 0; i < nbMembers-1; i++){
		
			try {
				sn.addMember(Integer.toString(i), "1234", "No profile");
			} catch (Exception e) {
				
				e.printStackTrace();
			} 
		}
	}
	
	public static void generateItems(SocialNetwork sn){
		
		// generate one member to generate the items
		try {
			sn.addMember("Glenn", "1456", "Cool");
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		for(int i = 0; i < nbItems; i++){
			try {
				sn.addItemBook("Glenn", "1456", Integer.toString(i), "Drame", "Max", 15);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args){
		SocialNetwork sn = new SocialNetwork();
		
		long begin = System.nanoTime();
		
		generateMembers(sn);
		generateItems(sn);
		System.out.println("Il y a "+ sn.nbMembers() +" membres et "+ sn.nbBooks() +" Livres \n");
		System.out.println("G�n�r� en :"+((System.nanoTime()-begin))+" nanosecondes soit � peu pr�s :"+((System.nanoTime()-begin)/1000000)+" millisecondes \n");
		
		
	}
}
