package nofantasy.codicefiscale2;

import java.util.*;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import java.io.FileInputStream;
import java.io.FileOutputStream;


public class GestioneXML {

	private String pathInputPersone;
	private String pathComuni;
	private String pathCF;
	private GestioneCodici codici;	
	private XMLInputFactory xmlifComuni = null;
	private XMLStreamReader xmlrComuni = null;
	private XMLInputFactory xmlif = null;
	private XMLStreamReader xmlr = null;
	private XMLOutputFactory xmlof = null;
	private XMLStreamWriter xmlw = null;

	public void letturaCF() {
		ArrayList<String> codiciLetti = new ArrayList<String>();
		String lastTag = "";
		try {
			xmlif = XMLInputFactory.newInstance();
			xmlr = xmlif.createXMLStreamReader(pathCF, new FileInputStream(pathCF));
		} catch (Exception e) {
			System.out.println("Errore nell'inizializzazione del reader: " + pathCF);
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
							lastTag = "";//resetto la variabile d'appoggio
						}
						break;
					}
					xmlr.next();
				}
		}catch (Exception e){
			System.out.println("Errore durante la lettura del file: " + pathCF);
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
			System.out.println("Errore nell'inizializzazione del reader:" + pathInputPersone);
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
			System.out.println("Errore durante la lettura del file: " + pathInputPersone);
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
			System.out.println("Errore nell'inizializzazione del reader: " + pathComuni);
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
			System.out.println("Errore durante la lettura del file: " + pathComuni);
			System.out.println(e.getMessage());
		}
		return "Comune non trovato";
	}

	public void setPathInputPersona(String path) {
		pathInputPersone = path;
	}

	public void setPathComuni(String path) {
		pathComuni = path;
	}

	public void setPathCF(String path) {
		pathCF = path;
	}

	public void generaOutput() {//eliminati perchè li può prendere da solo da codici
		try {
			xmlof = XMLOutputFactory.newInstance();
			xmlw = xmlof.createXMLStreamWriter(new FileOutputStream("codiciPersone.xml"), "utf-8");
			xmlw.writeStartDocument("utf-8", "1.0");
		} catch (Exception e) {
			System.out.println("Errore nell'inizializzazione del writer:");
			System.out.println(e.getMessage());
		}

		try { 
			xmlw.writeStartElement("output"); // apertura tag radice
			xmlw.writeStartElement("persone");//apertura persone
			xmlw.writeAttribute("numero", Integer.toString(codici.getPersone().size()));
			for (int i = 0; i < codici.getPersone().size(); i++) {
				xmlw.writeStartElement("persona");
				xmlw.writeAttribute("id", Integer.toString(i));
				xmlw.writeStartElement("nome");
				xmlw.writeCharacters(codici.getPersone().get(i).getNome());
				xmlw.writeEndElement();
				xmlw.writeStartElement("cognome");
				xmlw.writeCharacters(codici.getPersone().get(i).getCognome());
				xmlw.writeEndElement();
				xmlw.writeStartElement("sesso");
				xmlw.writeCharacters(codici.getPersone().get(i).getSesso());
				xmlw.writeEndElement();
				xmlw.writeStartElement("comune_nascita");
				xmlw.writeCharacters(codici.getPersone().get(i).getComune());
				xmlw.writeEndElement();
				xmlw.writeStartElement("data_nascita");
				xmlw.writeCharacters(codici.getPersone().get(i).getDataDiNascita());
				xmlw.writeEndElement();
				xmlw.writeStartElement("codice_fiscale");
				xmlw.writeCharacters(codici.getPersone().get(i).getCF());
				xmlw.writeEndElement();
				xmlw.writeEndElement();
			}
			xmlw.writeEndElement(); //chiudo persone
			
			xmlw.writeStartElement("codici");//apro codici
			xmlw.writeStartElement("invalidi");//apro invalidi
			xmlw.writeAttribute("numero", Integer.toString(codici.getCodiciFiscaliInvalidi().size()));
			for (int i = 0; i < codici.getCodiciFiscaliInvalidi().size(); i++) {
				xmlw.writeStartElement("codice");
				xmlw.writeCharacters(codici.getCodiciFiscaliInvalidi().get(i));
				xmlw.writeEndElement();
			}
			xmlw.writeEndElement();//chiudo invalidi
			
			xmlw.writeStartElement("spaiati");//apro spaiati
			xmlw.writeAttribute("numero", Integer.toString(codici.getCodiciFiscaliSpaiati().size()));
			for (int i = 0; i < codici.getCodiciFiscaliSpaiati().size(); i++) {
				xmlw.writeStartElement("codice");
				xmlw.writeCharacters(codici.getCodiciFiscaliSpaiati().get(i));
				xmlw.writeEndElement();
			}
			xmlw.writeEndElement();//chiudo spaiati
			xmlw.writeEndElement();//chiudo codici
			
			xmlw.writeEndElement();//chiudo output tag radice
			xmlw.writeEndDocument(); // scrittura della fine del documento
			xmlw.flush(); // svuota il buffer e procede alla scrittura
			xmlw.close(); // chiusura
		} catch (Exception e) { 
			System.out.println("Errore nella scrittura");
			System.out.println(e.getMessage());
		}
	}

}
