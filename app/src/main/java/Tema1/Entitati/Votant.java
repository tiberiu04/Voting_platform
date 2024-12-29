package Tema1.Entitati;
public class Votant extends Persoana{
	boolean neindemanatic;
	String cnp;
	boolean aVotat = false;
	boolean frauda = false;

	public Votant(int varsta, String nume, boolean neindemanatic, String cnp) {
		super(varsta, nume);
		this.neindemanatic = neindemanatic;
		this.cnp = cnp;
	}

	public void setCnp(String cnp) {
		this.cnp = cnp;
	}

	public String getCnp() {
		return this.cnp;
	}

	public void setNeindemanatic(boolean neindemanatic) {
		this.neindemanatic = neindemanatic;
	}

	public boolean isNeindemanatic() {
		return neindemanatic;
	}

	public void setAVotat(boolean aVotat) {
		this.aVotat = aVotat;
	}

	public boolean isaVotat() {
		return aVotat;
	}

	public boolean isFrauda() {
		return frauda;
	}

	public void setFrauda(boolean frauda) {
		this.frauda = frauda;
	}

	public String toString() {
		return this.getNume() + " " + this.cnp + " " + this.getVarsta();
	}
}
