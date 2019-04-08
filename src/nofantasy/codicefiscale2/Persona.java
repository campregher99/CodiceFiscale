package nofantasy.codicefiscale2;

public class Persona {

	private String nome;
	private String cognome;
	private String sesso;
	private String comune;
	private String dataDiNascita;
	private String cF;

	public Persona(Persona newPersona) {
		this.nome = newPersona.getNome();
		this.cognome = newPersona.getCognome();
		this.sesso = newPersona.getSesso();
		this.comune = newPersona.getComune();
		this.dataDiNascita = newPersona.getDataDiNascita();
		this.cF=calcoloCF();
	}

	public Persona(String nome, String cognome, String sesso, String comune, String dataDiNascita) {
		this.nome = nome;
		this.cognome = cognome;
		this.sesso = sesso;
		this.comune = comune;
		this.dataDiNascita = dataDiNascita;
		this.cF=calcoloCF();
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
