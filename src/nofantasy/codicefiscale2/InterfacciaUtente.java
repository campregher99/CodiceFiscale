package nofantasy.codicefiscale2;

import java.util.Scanner;

public class InterfacciaUtente {
	
	private GestioneCodici codici;
	private Scanner lettore = new Scanner(System.in);
	
	public InterfacciaUtente() {
		
	}
	
	private void setPaths() {
		
	}
	
	private String letturaString(String msg) {
		System.out.print(msg);
		return lettore.next();
	}
	
	private void scrittura(String ms) {
		System.out.println(ms);
	}
}
