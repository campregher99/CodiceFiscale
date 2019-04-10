package nofantasy.codicefiscale2;

import java.util.Scanner;

public class InterfacciaUtente {

	private GestioneXML gestore;
	private Scanner lettore = new Scanner(System.in);

	public InterfacciaUtente() {
		
	}
	
	public void esecuzioneAnalisi() {
		setPaths();
		gestore.letturaCF();
		gestore.letturaPersona();
		gestore.generaOutput();
		scrittura("L'operazione di controllo e generazione del nuovo file è avvenuta con successo.");
	}

	// acquisizione e controllo correttezza del percorso verificando che sia un file
	// .xml
	private void setPaths() {

		String path = letturaString("inserisci il percorso del file .xml dei codici fiscali: ");
		while (!path.substring(path.length() - 4).equals(".xml")) {
			scrittura("Il percorso del file inserito non è valido.");
			path = letturaString("inserisci il percorso del file .xml dei codici fiscali: ");
		}
		gestore.sethPathCF(path);

		path = letturaString("Inserisci il percorso del file .xml dei comuni: ");
		while (!path.substring(path.length() - 4).equals(".xml")) {
			scrittura("Il percorso del file inserito non è valido.");
			path = letturaString("Inserisci il percorso del file .xml dei comuni: ");
		}
		gestore.sethPathComuni(path);

		path = letturaString("Inserisci il percorso del file .xml delle persone: ");
		while (!path.substring(path.length() - 4).equals(".xml")) {
			scrittura("Il percorso del file inserito non è valido.");
			path = letturaString("Inserisci il percorso del file .xml delle persone: ");
		}
		gestore.sethPathInputPersona(path);

	}

	private String letturaString(String msg) {
		System.out.print(msg);
		return lettore.next();
	}

	private void scrittura(String ms) {
		System.out.println(ms);
	}
}
