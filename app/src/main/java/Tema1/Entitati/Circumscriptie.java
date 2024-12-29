package Tema1.Entitati;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class Circumscriptie {
	private String nume;
	private String regiune;
	private ArrayList<Votant> votanti;
	private ArrayList<Candidat> candidati;
	private HashMap<String, Integer> voturiPerCandidat = new HashMap<>();
	private HashMap<String, Votant> votantiMap = new HashMap<>();
	private Stack<Votant> fraudosi = new Stack<>();

	/**
	 * Constructor pentru circumscriptie
	 * @param nume
	 * @param regiune
	 */
	public Circumscriptie(String nume, String regiune) {
		this.nume = nume;
		this.regiune = regiune;
		this.votanti = new ArrayList<>();
		this.candidati = new ArrayList<>();
	}

	public String getNume() {
		return nume;
	}

	/**
	 * Adauga un votant in lista de votanti
	 * @param votant
	 */
	public void adaugaVotant(Votant votant) {
		votanti.add(votant);
	}

	/**
	 * Verifica daca exista un votant cu acelasi CNP
	 * @param cnp
	 * @return
	 */
	public boolean existaVotantCnp(String cnp) {
		for (Votant votant : votanti) {
			if (votant.getCnp().equals(cnp)) {
				return true;
			}
		}

		return false;
	}

	public ArrayList<Votant> getVotanti() {
		return votanti;
	}

	/**
	 * @param cnp
	 * @return un votant dupa CNP
	 */
	public Votant getVotant(String cnp) {
		for (Votant votant : votanti) {
			if (votant.getCnp().equals(cnp)) {
				return votant;
			}
		}

		return null;
	}

	/**
	 * Adauga un candidat in lista de candidati
	 * @param c
	 */
	public void addCandidat(Candidat c) {
		this.candidati.add(c);
	}

	/**
	 *
	 * @return vectorul de candidati din circumscriptie
	 */
	public ArrayList<Candidat> getCandidati() {
		return this.candidati;
	}

	/**
	 *
	 * @param c
	 * @return cauta un candidat anume si il returneaza
	 */
	public Candidat getCandidat(Candidat c) {
		for (Candidat candidat : candidati) {
			if (candidat.equals(c))
				return candidat;
		}

		return null;
	}

	/**
	 * Adauga un vot pentru un candidat
	 * @param cnpCandidat
	 */
	public void adaugaVot(String cnpCandidat) {
		voturiPerCandidat.put(cnpCandidat, voturiPerCandidat.getOrDefault(cnpCandidat, 0) + 1);
	}

	/**
	 *
	 * @param cnpCandidat
	 * @return numarul de voturi pentru un candidat
	 */
	public int getVoturiCandidat(String cnpCandidat) {
		return voturiPerCandidat.getOrDefault(cnpCandidat, 0);
	}

	/**
	 *
	 * @return hashmap cu voturile pentru fiecare candidat
	 */
	public HashMap<String, Integer> getVoturiPerCandidat() {
		return voturiPerCandidat;
	}

	/**
	 *
	 * @return numarul total de voturi din circumscriptie
	 */
	public int getTotalVoturi() {
		int totalVoturi = 0;
		for (int voturi : voturiPerCandidat.values()) {
			totalVoturi += voturi;
		}
		return totalVoturi;
	}

	public String getRegiune() {
		return this.regiune;
	}

	public void setRegiune(String regiune) {
		this.regiune = regiune;
	}

	public HashMap<String, Votant> getVotantMapa() {
		return this.votantiMap;
	}

	/**
	 *
	 * @return votantul dupa cnp
	 */
	public Votant getNumeVotantDupaCnp(String cnp) {
		return this.votantiMap.get(cnp);
	}


	public Stack<Votant> getFraudosi() {
		return this.fraudosi;
	}

	/**
	 * Adauga un votant in stiva de fraudosi
	 * @param votant
	 */
	public void addFraudos(Votant votant) {
		this.fraudosi.push(votant);
	}
}
