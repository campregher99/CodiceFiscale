package nofantasy.codicefiscale2;

import java.util.*;

public class GestioneXML {

	private String pathInputPersone;
	private String pathComuni;
	private String pathCF;
	private GestioneCodici codici;

	public void letturaCF() {
		
		codici = new GestioneCodici(newCodici);
	}

	public String ricercaComune(String comune) {
		return "";
	}

	public void letturaPersona() {
		codici.aggiungiPersona(persona);
	}

	public boolean sethPathInputPersona(String Path) {
		return true;
	}

	public boolean sethPathComuni(String path) {
		return true;
	}

	public boolean sethPathCF(String path) {
		return true;
	}

	public void generaOutput(ArrayList<String> persone, ArrayList<String> cFInvalidi, ArrayList<String> cFSpaiati) {

	}

}
