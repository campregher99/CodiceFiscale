package nofantasy.codicefiscale2;

import java.util.*;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;

import java.io.FileInputStream;


public class GestioneXML {

	private String pathInputPersone;
	private String pathComuni;
	private String pathCF;
	private GestioneCodici codici;	
	private XMLInputFactory xmlifComuni = null;
	private XMLStreamReader xmlrComuni = null;
	private XMLInputFactory xmlif = null;
	private XMLStreamReader xmlr = null;

	public void letturaCF() {
		ArrayList<String> codiciLetti = new ArrayList<String>();
		String lastTag = "";
		try {
			xmlif = XMLInputFactory.newInstance();
			xmlr = xmlif.createXMLStreamReader(pathCF, new FileInputStream(pathCF));
		} catch (Exception e) {
			System.out.println("Errore nell'inizializzazione del reader:");
			System.out.println(e.getMessage());
		}
		
		try {
			while (xmlr.hasNext()) { 
				switch (xmlr.getEventType()) { 
					case XMLStreamConstants.START_DOCUMENT:  
						
						break;
					case XMLStreamConstants.START_ELEMENT:
						
						lastTag = xmlr.getLocalName();//passo ad una variabile d'appoggio l'ultima tag che ho letto
						
						break;
					case XMLStreamConstants.END_ELEMENT:  
						
						break;
					case XMLStreamConstants.COMMENT:
						
						break;  
					case XMLStreamConstants.CHARACTERS: 
						
						if (lastTag.equals("codice")) {//se l'ultima tag che ho letto è "codice" prendo il testo
							codiciLetti.add(xmlr.getText());
						}
						break;
					}
					xmlr.next();
				}
		}catch (Exception e){
			System.out.println("Errore durante la lettura del file: ");
			System.out.println(e.getMessage());
		}
		codici = new GestioneCodici(codiciLetti);
	}
	
	public void letturaPersona() {
		String nome = "";
		String cognome = "";
		String sesso = "";
		String comune = "";
		String data = "";
		String lastTag = "";
		
		try {
			xmlif = XMLInputFactory.newInstance();
			xmlr = xmlif.createXMLStreamReader(pathInputPersone, new FileInputStream(pathInputPersone));
		} catch (Exception e) {
			System.out.println("Errore nell'inizializzazione del reader:");
			System.out.println(e.getMessage());
		}
		
		try {
			while (xmlr.hasNext()) { // continua a leggere finché ha eventi a disposizione
				switch (xmlr.getEventType()) { 
					case XMLStreamConstants.START_DOCUMENT: 
						
						break;
					case XMLStreamConstants.START_ELEMENT:
						
						lastTag = xmlr.getLocalName();//passo ad una variabile d'appoggio l'ultima tag che ho letto

						break;
					case XMLStreamConstants.END_ELEMENT:
						if (xmlr.getLocalName().equals("persona")) {
							Persona newPersona = new Persona(nome, cognome, sesso, comune, ricercaComune(comune), data);
							codici.aggiungiPersona(newPersona);
						}
						break;
					case XMLStreamConstants.COMMENT:
						
						break;  
					case XMLStreamConstants.CHARACTERS:
						
						if (lastTag.equals("nome")) {//se l'ultima tag che ho letto è "nome" prendo il testo
							nome = xmlr.getText();
							lastTag = "";//resetto la variabile d'appoggio
						}
						if (lastTag.equals("cognome")) {//se l'ultima tag che ho letto è "cognome" prendo il testo
							cognome = xmlr.getText();
							lastTag = "";//resetto la variabile d'appoggio
						}	
						if (lastTag.equals("sesso")) {//se l'ultima tag che ho letto è "sesso" prendo il testo
							sesso = xmlr.getText();
							lastTag = "";//resetto la variabile d'appoggio
						}
						if (lastTag.equals("comune_nascita")) {//se l'ultima tag che ho letto è "comune_nascita" prendo il testo
							comune = xmlr.getText();
							lastTag = "";//resetto la variabile d'appoggio
						}
						if (lastTag.equals("data_nascita")) {//se l'ultima tag che ho letto è "data_nascita" prendo il testo
							data = xmlr.getText();
							lastTag = "";//resetto la variabile d'appoggio
						}
						break;
					}
				
				
					xmlr.next();
				}
		}catch (Exception e){
			System.out.println("Errore durante la lettura del file: ");
			System.out.println(e.getMessage());
		}
	}

	private String ricercaComune(String comune) {
		String nomeComune = "";
		String lastTag = "";
		try {
			xmlifComuni = XMLInputFactory.newInstance();
			xmlrComuni = xmlifComuni.createXMLStreamReader(pathComuni, new FileInputStream(pathComuni));
		} catch (Exception e) {
			System.out.println("Errore nell'inizializzazione del reader:");
			System.out.println(e.getMessage());
		}
		
		try {
			while (xmlrComuni.hasNext()) { // continua a leggere finché ha eventi a disposizione
				switch (xmlrComuni.getEventType()) { 
					case XMLStreamConstants.START_DOCUMENT: 
						
						break;
					case XMLStreamConstants.START_ELEMENT:
						
						lastTag = xmlrComuni.getLocalName();//passo ad una variabile d'appoggio l'ultima tag che ho letto

						break;
					case XMLStreamConstants.END_ELEMENT:
						if (xmlrComuni.getLocalName().equals("persona")) {
							
						}
						break;
					case XMLStreamConstants.COMMENT:
						
						break;  
					case XMLStreamConstants.CHARACTERS:
						
						if (lastTag.equals("nome")) {//se l'ultima tag che ho letto è "nome" prendo il testo
							nomeComune = xmlrComuni.getText();
							lastTag = "";//resetto la variabile d'appoggio
						}
						if (lastTag.equals("codice") && nomeComune.equals(comune)) {//se l'ultima tag che ho letto è "nome" prendo il testo
							return xmlrComuni.getText();
						}
						break;
					}
					xmlrComuni.next();
				}
		}catch (Exception e){
			System.out.println("Errore durante la lettura del file: ");
			System.out.println(e.getMessage());
		}
		return "Comune non trovato";
	}

	public boolean sethPathInputPersona(String path) {
		try {
			xmlif = XMLInputFactory.newInstance();
			xmlr = xmlif.createXMLStreamReader(path, new FileInputStream(path));
			pathInputPersone = path;
		} catch (Exception e) {
			System.out.println("Non è stato possibile trovare il file");
			System.out.println(e.getMessage());
		}
		return true;
	}

	public boolean sethPathComuni(String path) {
		try {
			xmlif = XMLInputFactory.newInstance();
			xmlr = xmlif.createXMLStreamReader(path, new FileInputStream(path));
			pathComuni = path;
		} catch (Exception e) {
			System.out.println("Non è stato possibile trovare il file");
			System.out.println(e.getMessage());
		}
		return true;
	}

	public boolean sethPathCF(String path) {
		try {
			xmlif = XMLInputFactory.newInstance();
			xmlr = xmlif.createXMLStreamReader(path, new FileInputStream(path));
			pathCF = path;
		} catch (Exception e) {
			System.out.println("Non è stato possibile trovare il file");
			System.out.println(e.getMessage());
		}
		return true;
	}

	public void generaOutput() {//eliminati perchè li può prendere da solo da codici
			
	}

}
