package nofantasy.codicefiscale2;

import java.util.ArrayList;

public class GestioneCodici {

	private ArrayList<Persona> persone = new ArrayList<Persona>();
	private GestioneXML gestoreXML = new GestioneXML();
	private ArrayList<String> codiciFiscaliInvalidi = new ArrayList<String>();
	private ArrayList<String> codiciFiscaliSpaiati = new ArrayList<String>();
	
	public GestioneCodici() {
		codiciFiscaliSpaiati = gestoreXML.letturaCF();
		for (int i = 0; i < codiciFiscaliSpaiati.size(); i++) {
			if (!(convalidaCF(codiciFiscaliSpaiati.get(i)))) {
				codiciFiscaliInvalidi.add(codiciFiscaliSpaiati.get(i));
				codiciFiscaliSpaiati.remove(i);
			}
		}
	}
	
	public void aggiungiPersona(Persona persona) {
		if(!(controlloCF(persona.getcF()))) {
			persona.setCF("ASSENTE");
		}else {
			codiciFiscaliSpaiati.remove(persona.getcF());
		}
		persone.add(persona);		
	}
	
	private boolean controlloCF(String cF) {
		//serve per controllare che il cf della persona sia all'interno di codici fiscali spaiati
		for (int i = 0; i < codiciFiscaliSpaiati.size(); i++) {!!ma siamno sicuri non serva un <=? perchè non capisco se facendo solo < non controlla l'ultimo
			if (cF.equals(codiciFiscaliSpaiati.get(i))) {
				return true;
			}
		}
		return false;
	}
	
	private boolean convalidaCF(String cF) {
		//serve a controllare che i codici inseriti all'inzio in codiciSpaiati siano validi
		return true;
	}

}
