package nofantasy.codicefiscale2;

import java.util.ArrayList;

/**
 * 
 * @author dchiaf
 * @author fcampregher
 *
 */
public class GestioneCodici {

	private static final String ASSENTE = "ASSENTE";
	private ArrayList<Persona> persone = new ArrayList<Persona>();
	private ArrayList<String> codiciFiscaliInvalidi = new ArrayList<String>();
	private ArrayList<String> codiciFiscaliSpaiati = new ArrayList<String>();
	private static final char[] SCHELETRO_CODICE = new char[] { 'c', 'c', 'c', 'c', 'c', 'c', 'n', 'n', 'c', 'n', 'n',
			'c', 'n', 'n', 'n', 'c' };

	/**
	 * Costruttore che controlla la validità dei codici inseriti e li separa tra
	 * spaiati e invalidi
	 * 
	 * @param newCodici
	 */
	public GestioneCodici(ArrayList<String> newCodici) {
		codiciFiscaliSpaiati = newCodici;
		// scompongo l'array e controllo codice per codice la sua validità
		for (int i = 0; i < codiciFiscaliSpaiati.size(); i++) {
			if (!(convalidaCF(codiciFiscaliSpaiati.get(i)))) {
				codiciFiscaliInvalidi.add(codiciFiscaliSpaiati.get(i));
				codiciFiscaliSpaiati.remove(i);
			}
		}
	}

	/**
	 * ritorna l'araylist dei codici fiscali invalidi
	 * 
	 * @return ArrayList<String>
	 */
	public ArrayList<String> getCodiciFiscaliInvalidi() {
		return codiciFiscaliInvalidi;
	}

	/**
	 * ritorna l'arraylist delle persone inserite
	 * 
	 * @return ArrayList<Persona>
	 */
	public ArrayList<Persona> getPersone() {
		return persone;
	}

	/**
	 * ritorna l'arraylist dei codici fiscali spaiati
	 * 
	 * @return ArrayList<String>
	 */
	public ArrayList<String> getCodiciFiscaliSpaiati() {
		return codiciFiscaliSpaiati;
	}

	/**
	 * controlla che il codice fiscale autogenerato della persona sia presente nella
	 * lista dei codici spaiati, se è presente lo rimuove dalla lista se non è
	 * presente lo sostituisce con ASSENTE. Dopodiché aggiunge la persona
	 * all'arraylist delle persone
	 * 
	 * @param newPersona
	 */
	public void aggiungiPersona(Persona newPersona) {
		if (!(controlloCF(newPersona.getCF()))) {
			newPersona.setCF(ASSENTE);
		} else {
			codiciFiscaliSpaiati.remove(newPersona.getCF());
		}
		persone.add(newPersona);
	}
	
	/**
	 * controlla che il codice fiscale autogenerato della persona sia presente nella
	 * lista dei codici spaiati
	 * 
	 * @param cF
	 * @return
	 */
	private boolean controlloCF(String cF) {
		// serve per controllare che il cf della persona sia all'interno di codici
		// fiscali spaiati
		for (int i = 0; i < codiciFiscaliSpaiati.size(); i++) {
			if (cF.equals(codiciFiscaliSpaiati.get(i))) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * controlla che il codice in ingresso sia valido, se è valido ritorna true se è invalido ritorna false
	 * 
	 * @param codice
	 * @return
	 */
	private boolean convalidaCF(String codice) {
		// serve a controllare che i codici inseriti all'inzio in codiciSpaiati siano
		// validi
		// controllo lunghezza
		if (codice.length() > 16) {
			return false;
		}
		// controllo che nelle varie posizioni ci sia la tipologia di carattere ceh ci
		// aspettiamo
		for (int i = 0; i < codice.length(); i++) {
			if (SCHELETRO_CODICE[i] == 'n') {
				if (!(codice.charAt(i) >= 48 && codice.charAt(i) <= 57)) {
					return false;
				}
			} else if (SCHELETRO_CODICE[i] == 'c') {
				if (!(codice.charAt(i) >= 65 && codice.charAt(i) <= 90)) {
					return false;
				}
			}
		}
		// controllo che il giorno sia corretto rispetto al mese e che il carattere per
		// definire il mese sia corretto
		switch (codice.charAt(8)) {
		case 'A':// gennaio 31
			if (!((Integer.valueOf(codice.substring(9, 11)) > 0 && Integer.valueOf(codice.substring(9, 11)) < 32)
					|| (Integer.valueOf(codice.substring(9, 11)) > 40
							&& Integer.valueOf(codice.substring(9, 11)) < 72))) {
				return false;
			}
			break;
		case 'B':// febbraio 28/29
			if (Integer.valueOf(codice.substring(6, 8)) % 4 == 0) {
				if (!((Integer.valueOf(codice.substring(9, 11)) > 0 && Integer.valueOf(codice.substring(9, 11)) < 30)
						|| (Integer.valueOf(codice.substring(9, 11)) > 40
								&& Integer.valueOf(codice.substring(9, 11)) < 70))) {
					return false;
				}
			} else {
				if (!((Integer.valueOf(codice.substring(9, 11)) > 0 && Integer.valueOf(codice.substring(9, 11)) < 29)
						|| (Integer.valueOf(codice.substring(9, 11)) > 40
								&& Integer.valueOf(codice.substring(9, 11)) < 69))) {
					return false;
				}
			}
			break;
		case 'C':// marzo 31
			if (!((Integer.valueOf(codice.substring(9, 11)) > 0 && Integer.valueOf(codice.substring(9, 11)) < 32)
					|| (Integer.valueOf(codice.substring(9, 11)) > 40
							&& Integer.valueOf(codice.substring(9, 11)) < 72))) {
				return false;
			}
			break;
		case 'D':// aprile 30
			if (!((Integer.valueOf(codice.substring(9, 11)) > 0 && Integer.valueOf(codice.substring(9, 11)) < 31)
					|| (Integer.valueOf(codice.substring(9, 11)) > 40
							&& Integer.valueOf(codice.substring(9, 11)) < 71))) {
				return false;
			}
			break;
		case 'E':// maggio 31
			if (!((Integer.valueOf(codice.substring(9, 11)) > 0 && Integer.valueOf(codice.substring(9, 11)) < 32)
					|| (Integer.valueOf(codice.substring(9, 11)) > 40
							&& Integer.valueOf(codice.substring(9, 11)) < 72))) {
				return false;
			}
			break;
		case 'H':// giugno 30
			if (!((Integer.valueOf(codice.substring(9, 11)) > 0 && Integer.valueOf(codice.substring(9, 11)) < 31)
					|| (Integer.valueOf(codice.substring(9, 11)) > 40
							&& Integer.valueOf(codice.substring(9, 11)) < 71))) {
				return false;
			}
			break;
		case 'L':// luglio 31
			if (!((Integer.valueOf(codice.substring(9, 11)) > 0 && Integer.valueOf(codice.substring(9, 11)) < 32)
					|| (Integer.valueOf(codice.substring(9, 11)) > 40
							&& Integer.valueOf(codice.substring(9, 11)) < 72))) {
				return false;
			}
			break;
		case 'M':// agosto 31
			if (!((Integer.valueOf(codice.substring(9, 11)) > 0 && Integer.valueOf(codice.substring(9, 11)) < 32)
					|| (Integer.valueOf(codice.substring(9, 11)) > 40
							&& Integer.valueOf(codice.substring(9, 11)) < 72))) {
				return false;
			}
			break;
		case 'P':// settembre 30
			if (!((Integer.valueOf(codice.substring(9, 11)) > 0 && Integer.valueOf(codice.substring(9, 11)) < 31)
					|| (Integer.valueOf(codice.substring(9, 11)) > 40
							&& Integer.valueOf(codice.substring(9, 11)) < 71))) {
				return false;
			}
			break;
		case 'R':// ottobre 31
			if (!((Integer.valueOf(codice.substring(9, 11)) > 0 && Integer.valueOf(codice.substring(9, 11)) < 32)
					|| (Integer.valueOf(codice.substring(9, 11)) > 40
							&& Integer.valueOf(codice.substring(9, 11)) < 72))) {
				return false;
			}
			break;
		case 'S':// novembre 30
			if (!((Integer.valueOf(codice.substring(9, 11)) > 0 && Integer.valueOf(codice.substring(9, 11)) < 31)
					|| (Integer.valueOf(codice.substring(9, 11)) > 40
							&& Integer.valueOf(codice.substring(9, 11)) < 71))) {
				return false;
			}
			break;
		case 'T':// dicembre 31
			if (!((Integer.valueOf(codice.substring(9, 11)) > 0 && Integer.valueOf(codice.substring(9, 11)) < 32)
					|| (Integer.valueOf(codice.substring(9, 11)) > 40
							&& Integer.valueOf(codice.substring(9, 11)) < 72))) {
				return false;
			}
			break;
		default:// nel caso in cui la lettera in posizione per il mese non sia corretta ritorna
				// falso
			return false;
		}
		// controllo del comune

		// controllo carattere di controllo
		// calcolo carattere di controllo
		int i, dispari = 0, pari = 0;
		for (i = 0; i < codice.length() - 1; i++) {
			if (i % 2 == 0) {
				switch (codice.charAt(i)) {
				case '0':
					dispari += 1;
					break;
				case '1':
					dispari += 0;
					break;
				case '2':
					dispari += 5;
					break;
				case '3':
					dispari += 7;
					break;
				case '4':
					dispari += 9;
					break;
				case '5':
					dispari += 13;
					break;
				case '6':
					dispari += 15;
					break;
				case '7':
					dispari += 17;
					break;
				case '8':
					dispari += 19;
					break;
				case '9':
					dispari += 21;
					break;
				case 'A':
					dispari += 1;
					break;
				case 'B':
					dispari += 0;
					break;
				case 'C':
					dispari += 5;
					break;
				case 'D':
					dispari += 7;
					break;
				case 'E':
					dispari += 9;
					break;
				case 'F':
					dispari += 13;
					break;
				case 'G':
					dispari += 15;
					break;
				case 'H':
					dispari += 17;
					break;
				case 'I':
					dispari += 19;
					break;
				case 'J':
					dispari += 21;
					break;
				case 'K':
					dispari += 2;
					break;
				case 'L':
					dispari += 4;
					break;
				case 'M':
					dispari += 18;
					break;
				case 'N':
					dispari += 20;
					break;
				case 'O':
					dispari += 11;
					break;
				case 'P':
					dispari += 3;
					break;
				case 'Q':
					dispari += 6;
					break;
				case 'R':
					dispari += 8;
					break;
				case 'S':
					dispari += 12;
					break;
				case 'T':
					dispari += 14;
					break;
				case 'U':
					dispari += 16;
					break;
				case 'V':
					dispari += 10;
					break;
				case 'W':
					dispari += 22;
					break;
				case 'X':
					dispari += 25;
					break;
				case 'Y':
					dispari += 24;
					break;
				case 'Z':
					dispari += 23;
					break;
				}
			} else {
				if (codice.charAt(i) < 60) {
					pari += codice.charAt(i) - 48;
				} else {
					pari += codice.charAt(i) - 65;
				}
			}
		}
		if (!(codice.charAt(15) == (((pari + dispari) % 26) + 65))) {
			return false;
		}

		// se nessun controllo interviene il codice è corretto e ritorno true
		return true;
	}

}
