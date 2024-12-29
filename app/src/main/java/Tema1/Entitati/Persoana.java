package Tema1.Entitati;
public class Persoana {
	private int varsta;
	private String nume;

	public Persoana(int varsta, String nume) {
		this.varsta =  varsta;
		this.nume = nume;
	}

	public int getVarsta() {
		return this.varsta;
	}

	public void setVarsta(int varsta) {
		this.varsta = varsta;
	}

	public String getNume() {
		return this.nume;
	}

	public void setNume(String nume) {
		this.nume = nume;
	}
}
