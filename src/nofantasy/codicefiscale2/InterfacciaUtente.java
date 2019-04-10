package nofantasy.codicefiscale2;

import java.io.FileInputStream;
import java.util.Scanner;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;

public class InterfacciaUtente {

	private GestioneXML gestore = new GestioneXML();
	private Scanner lettore = new Scanner(System.in);
	private XMLInputFactory xmlif = null;
	private XMLStreamReader xmlr = null;

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
		
		boolean isCorretto = false;
		
		String path = letturaString("inserisci il percorso del file .xml dei codici fiscali: ");
		
		while (!isCorretto) {
			try {
				xmlif = XMLInputFactory.newInstance();
				xmlr = xmlif.createXMLStreamReader(path, new FileInputStream(path));
				isCorretto = true;
			} catch (Exception e) {
				scrittura("Il percorso del file inserito non è valido: ");
				scrittura(e.getMessage());				
				path = letturaString("inserisci il percorso del file .xml dei codici fiscali: ");
				isCorretto = false;
			}
		}
		gestore.setPathCF(path);

		path = letturaString("Inserisci il percorso del file .xml dei comuni: ");
		isCorretto = false;
		
		while (!isCorretto) {
			try {
				xmlif = XMLInputFactory.newInstance();
				xmlr = xmlif.createXMLStreamReader(path, new FileInputStream(path));
				isCorretto = true;
			} catch (Exception e) {
				scrittura("Il percorso del file inserito non è valido: ");
				scrittura(e.getMessage());				
				path = letturaString("inserisci il percorso del file .xml dei comuni: ");
				isCorretto = false;
			}
		}
		gestore.setPathComuni(path);

		path = letturaString("Inserisci il percorso del file .xml delle persone: ");
		isCorretto = false;
		
		while (!isCorretto) {
			try {
				xmlif = XMLInputFactory.newInstance();
				xmlr = xmlif.createXMLStreamReader(path, new FileInputStream(path));
				isCorretto = true;
			} catch (Exception e) {
				scrittura("Il percorso del file inserito non è valido: ");
				scrittura(e.getMessage());				
				path = letturaString("inserisci il percorso del file .xml delle persone: ");
				isCorretto = false;
			}
		}
		gestore.setPathInputPersona(path);

	}

	private String letturaString(String msg) {
		System.out.print(msg);
		return lettore.next();
	}

	private void scrittura(String ms) {
		System.out.println(ms);
	}
}
