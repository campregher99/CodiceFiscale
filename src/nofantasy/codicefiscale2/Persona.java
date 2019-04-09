package nofantasy.codicefiscale2;

public class Persona {

	private String nome;
	private String cognome;
	private String sesso;
	private String comune;
	private String dataDiNascita; // AAAA/MM/GG
	private String cF;

	public Persona(Persona newPersona) {
		this.nome = newPersona.getNome();
		this.cognome = newPersona.getCognome();
		this.sesso = newPersona.getSesso();
		this.comune = newPersona.getComune();
		this.dataDiNascita = newPersona.getDataDiNascita();
		this.cF = calcoloCF();
	}

	public Persona(String nome, String cognome, String sesso, String comune, String dataDiNascita) {
		this.nome = nome;
		this.cognome = cognome;
		this.sesso = sesso;
		this.comune = comune;
		this.dataDiNascita = dataDiNascita;
		this.cF = calcoloCF();
	}

	public void setCF(String cF) {
		this.cF = cF;
	}

	public String getNome() {
		return nome;
	}

	public String getCognome() {
		return cognome;
	}

	public String getSesso() {
		return sesso;
	}

	public String getComune() {
		return comune;
	}

	public String getDataDiNascita() {
		return dataDiNascita;
	}

	public String getcF() {
		return cF;
	}

	private String calcoloCF() {
		String newCF = "";
		int i = 0, c = 0;

		// Creazione seconde tre caratteri del codice fiscale (cognome)
		while (i < 3) {
			if (c < this.cognome.length()) {
				if (this.cognome.charAt(c) != 'A' && this.cognome.charAt(c) != 'E' && this.cognome.charAt(c) != 'I'
						&& this.cognome.charAt(c) != 'O' && this.cognome.charAt(c) != 'U') {
					i++;
					newCF += this.cognome.charAt(c);
				}

			} else {
				if (c - this.cognome.length() < this.cognome.length()) {
					if (this.cognome.charAt(c - this.cognome.length()) == 'A'
							|| this.cognome.charAt(c - this.cognome.length()) == 'E'
							|| this.cognome.charAt(c - this.cognome.length()) == 'I'
							|| this.cognome.charAt(c - this.cognome.length()) == 'O'
							|| this.cognome.charAt(c - this.cognome.length()) == 'U') {
						i++;
						newCF += this.cognome.charAt(c);
					}
				}
			}
			c++;
		}

		c = 0;
		i = 0;

		// Creazione primi tre caratteri del codice fiscale (nome)
		while (i < 3) {
			if (c < this.nome.length()) {
				if (this.nome.charAt(c) != 'A' && this.nome.charAt(c) != 'E' && this.nome.charAt(c) != 'I'
						&& this.nome.charAt(c) != 'O' && this.nome.charAt(c) != 'U') {
					i++;
					newCF += this.nome.charAt(c);
				}

			} else {
				if (c - this.nome.length() < this.nome.length()) {
					if (this.nome.charAt(c - this.nome.length()) == 'A'
							|| this.nome.charAt(c - this.nome.length()) == 'E'
							|| this.nome.charAt(c - this.nome.length()) == 'I'
							|| this.nome.charAt(c - this.nome.length()) == 'O'
							|| this.nome.charAt(c - this.nome.length()) == 'U') {
						i++;
						newCF += this.nome.charAt(c);
					}
				}
			}
			c++;
		}

		// calcolo anno di nascita
		newCF += this.dataDiNascita.substring(2, 3);

		// calcolo mese e conversione in lettera e calcolo giorno con controllo
		switch (Integer.valueOf(this.dataDiNascita.substring(5, 6))) {
		case 1:
			newCF += 'A';
			if (Integer.valueOf(this.dataDiNascita.substring(8, 9)) <= 31
					&& Integer.valueOf(this.dataDiNascita.substring(8, 9)) > 0) {
				newCF += this.dataDiNascita.substring(8, 9);
			}
			break;
		case 2:
			newCF += 'B';
			break;
		case 3:
			newCF += 'C';
			if (Integer.valueOf(this.dataDiNascita.substring(8, 9)) <= 31
					&& Integer.valueOf(this.dataDiNascita.substring(8, 9)) > 0) {
				newCF += this.dataDiNascita.substring(8, 9);
			}
			break;
		case 4:
			newCF += 'D';
			
			break;
		case 5:
			newCF += 'E';
			if (Integer.valueOf(this.dataDiNascita.substring(8, 9)) <= 31
					&& Integer.valueOf(this.dataDiNascita.substring(8, 9)) > 0) {
				newCF += this.dataDiNascita.substring(8, 9);
			}
			break;
		case 6:
			newCF += 'F';
			break;
		case 7:
			newCF += 'G';
			if (Integer.valueOf(this.dataDiNascita.substring(8, 9)) <= 31
					&& Integer.valueOf(this.dataDiNascita.substring(8, 9)) > 0) {
				newCF += this.dataDiNascita.substring(8, 9);
			}
			break;
		case 8:
			newCF += 'H';
			if (Integer.valueOf(this.dataDiNascita.substring(8, 9)) <= 31
					&& Integer.valueOf(this.dataDiNascita.substring(8, 9)) > 0) {
				newCF += this.dataDiNascita.substring(8, 9);
			}
			break;
		case 9:
			newCF += 'I';
			break;
		case 10:
			newCF += 'L';
			if (Integer.valueOf(this.dataDiNascita.substring(8, 9)) <= 31
					&& Integer.valueOf(this.dataDiNascita.substring(8, 9)) > 0) {
				newCF += this.dataDiNascita.substring(8, 9);
			}
			break;
		case 11:
			newCF += 'M';
			break;
		case 12:
			newCF += 'N';
			if (Integer.valueOf(this.dataDiNascita.substring(8, 9)) <= 31
					&& Integer.valueOf(this.dataDiNascita.substring(8, 9)) > 0) {
				newCF += this.dataDiNascita.substring(8, 9);
			}
			break;
		default:

			break;
		}

		newCF = "";
		return newCF;
	}

}
