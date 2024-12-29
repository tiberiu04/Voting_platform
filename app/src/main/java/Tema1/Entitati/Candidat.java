package Tema1.Entitati;
public class Candidat extends Persoana{
	private String cnp;
	private int numarVoturi = 0;

	public Candidat(int varsta, String nume, String cnp) {
		super(varsta, nume);
		this.cnp = cnp;
	}

	public String getCnp() {
		return this.cnp;
	}

	public void setCnp(String cnp) {
		this.cnp = cnp;
	}

	public boolean esteCnpValid(String cnp) {
		return cnp.length() == 13;
	}

	public boolean esteVarstaValida(int varsta) {
		return varsta >= 35;
	}

	public int getNumarVoturi() {
		return this.numarVoturi;
	}

	public void setNumarVoturi(int numarVoturi) {
		this.numarVoturi = numarVoturi;
	}

	/**
	 * Metoda pentru afisarea unui candidat
	 * @return
	 */
	@Override
	public String toString() {
		return this.getNume() + " " + this.cnp + " " + this.getVarsta();
	}
}
