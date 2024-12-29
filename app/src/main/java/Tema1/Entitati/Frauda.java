package Tema1.Entitati;

public class Frauda {
	private String numeCircum;
	private String cnp;
	private String nume;

	public Frauda(String numeCircum, String cnp, String nume) {
		this.numeCircum = numeCircum;
		this.cnp = cnp;
		this.nume = nume;
	}

	public void setNumeCircum(String numeCircum) {
		this.numeCircum = numeCircum;
	}

	public String getNumeCircum() {
		return this.numeCircum;
	}

	public void setCnp(String cnp) {
		this.cnp = cnp;
	}

	public String getCnp() {
		return cnp;
	}

	public String getNume() {
		return nume;
	}

	public void setNume(String nume) {
		this.nume = nume;
	}

	public String toString(String numeCircum, String cnp, String nume) {
		return "in " + numeCircum + ": " + cnp + " " + nume;
	}
}
