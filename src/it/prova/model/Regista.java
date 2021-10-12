package it.prova.model;

public class Regista {

	private Long id;
	private String nome;
	private String cognome;
	private int numeroOscarVinti;

	public Regista() {

	}

	public Regista(String nome, String cognome, int numeroOscarVinti) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.numeroOscarVinti = numeroOscarVinti;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public int getNumeroOscarVinti() {
		return numeroOscarVinti;
	}

	public void setNumeroOscarVinti(int numeroOscarVinti) {
		this.numeroOscarVinti = numeroOscarVinti;
	}
	
	public String toString() {
		return "id= " + id + "\nnome = " + nome + "\ncognome = " + cognome + "\nnumero Oscar Vinti = " + numeroOscarVinti;
	}

}
