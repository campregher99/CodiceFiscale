package nofantasy.codicefiscale2;

import java.util.ArrayList;

public class GestioneCodici {

	private ArrayList<Persona> persone = new ArrayList<Persona>();
	private GestioneXML gestoreXML = new GestioneXML();
	private ArrayList<String> codiciFiscaliInvalidi = new ArrayList<String>();
	private ArrayList<String> codiciFiscaliSpaiati = new ArrayList<String>();
	
	public GestioneCodici() {
		codiciFiscaliSpaiati = gestoreXML.letturaCF();
		
	}
	
	public void aggiungiPersona(Persona persona) {
		persone.add(persona);
		convalida()
	}
	
	private boolean controlloCF(String cF) {
		//serve per controllare che il cf della persona sia all'interno di codici fiscali spaiati
		return true;
	}
	
	private boolean convalidaCF() {
		//serve a controllare che i codici inseriti all'inzio in codiciSpaiati siano validi
		return true;
	}

}
