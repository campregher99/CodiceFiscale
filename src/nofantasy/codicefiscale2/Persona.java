package nofantasy.codicefiscale2;

public class Persona {
	
	private String nome;
	private String cognome;
	private String sesso;
	private String comune;
	private String dataDiNascita;
	private String cF;
	
	public Persona(Persona newPersona) {
		
	}
	
	public Persona(String nome, String cognome, String sesso, String comune, String dataDiNascita) {
		
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
		return "";
	}

}
