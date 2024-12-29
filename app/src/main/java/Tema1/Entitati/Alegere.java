package Tema1.Entitati;
import Tema1.Comenzi.CreareAlegeri;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Alegere {
	private String id;
	private String nume;
	private boolean ePornita;
	private ArrayList<Circumscriptie> circumscriptii =  new ArrayList<>();
	private ArrayList<Candidat> candidati = new ArrayList<>();
	private ArrayList<Votant> votanti = new ArrayList<>();
	private Scanner scanner;

	public String getId() {
		return this.id;
	}

	public void setid(final String id) {
		this.id = id;
	}

	public String getNume() {
		return this.nume;
	}

	public void setNume(final String nume) {
		this.nume = nume;
	}

	public boolean getEPornita() {
		return this.ePornita;
	}

	public void setEPornita(final boolean ePornita) {
		this.ePornita = ePornita;
	}

	/**
	 * Adaug o circumscriptie in alegere
	 * @param c
	 */
	public void adaugaCircumscriptie(Circumscriptie c) {
		circumscriptii.add(c);
	}

	/**
	 * Verifica daca exista circumscriptia cu numele dat ca parametru
	 * @param nume
	 * @return
	 */
	public boolean existaCircumscriptie(String nume) {
		for (Circumscriptie c : circumscriptii) {
			if (c.getNume().equalsIgnoreCase(nume)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @return circumscriptiile din alegere
	 */
	public ArrayList<Circumscriptie> getCircumscriptii() {
		return this.circumscriptii;
	}

	/**
	 *
	 * @param nume
	 * @return circumscriptia cu numele dat ca parametru
	 */
	public Circumscriptie getCircumscriptie(String nume) {
		for (Circumscriptie c : circumscriptii) {
			if (c.getNume().equalsIgnoreCase(nume)) {
				return c;
			}
		}
		return null;
	}

	/**
	 * Elimina circumscriptia cu numele dat ca parametru
	 * @param nume
	 * @return
	 */
	public boolean eliminaCircumscriptie(String nume) {
		for (Circumscriptie c : circumscriptii) {
			if (c.getNume().equalsIgnoreCase(nume)) {
				ArrayList<Candidat> candidati = c.getCandidati();
				for (Candidat candidat : candidati) {
					candidat.setNumarVoturi(candidat.getNumarVoturi() - c.getVoturiCandidat(candidat.getCnp()));
				}
				circumscriptii.remove(c);
				return true;
			}
		}
		return false;
	}

	/**
	 * Adauga un candidat in alegere
	 * @param c
	 */
	public void adaugaCandidat(Candidat c) {
		candidati.add(c);
	}

	/**
	 * Verifica daca exista candidatul cu numele si cnp-ul date ca parametri
	 * @param nume
	 * @return
	 */
	public boolean areAcelasiCnp(String nume, String cnp) {
		for (Candidat c : candidati) {
			if (c.getCnp().equalsIgnoreCase(cnp)) {
				return true;
			}
		}

		return false;
	}

	/**
	 * @param cnp
	 * @return numele candidatului cu cnp-ul dat ca parametru
	 */
	public String getNumeCandidat(String cnp) {
		for (Candidat c : candidati) {
			if (c.getCnp().equalsIgnoreCase(cnp)) {
				return c.getNume();
			}
		}

		return null;
	}

	/**
	 * @param cnp
	 * @return candidatul cu cnp-ul dat ca parametru
	 */
	public Candidat getCandidat(String cnp) {
		for (Candidat c : candidati) {
			if (c.getCnp().equalsIgnoreCase(cnp)) {
				return c;
			}
		}

		return null;
	}

	/**
	 * Verifica daca exista candidatul cu cnp-ul dat ca parametru
	 * @param cnp
	 * @return
	 */
	public boolean existaCandidat(String cnp) {
		for (Candidat c : candidati) {
			if (c.getCnp().equalsIgnoreCase(cnp)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Verifica daca exista votantul cu cnp-ul dat ca parametru
	 * @param cnp
	 * @return
	 */
	public boolean existaVotant(String cnp) {
		for (Votant v : votanti) {
			if (v.getCnp().equalsIgnoreCase(cnp)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Elimina candidatul cu cnp-ul dat ca parametru
	 * @param cnp
	 */
	public void eliminaCandidat(String cnp) {
		for (Candidat c : candidati) {
			if (c.getNume().equalsIgnoreCase(cnp)) {
				candidati.remove(c);
			}
		}
	}

	/**
	 *
	 * @return numarul de candidati
	 */
	public int getCandidatiDimensiune() {
		return this.candidati.size();
	}

	/**
	 *
	 * @return candidatii la alegere
	 */
	public ArrayList<Candidat> getCandidati() {
		return this.candidati;
	}

	/**
	 * Sterge alegerea cu id ul dat ca parametru
	 * @param id
	 */
	public void stergeAlegeri(String id) {
		CreareAlegeri a = new CreareAlegeri(scanner);
		HashMap<String, Alegere> alegeriMap = a.getAlegeriMap();

		alegeriMap.remove(id);
	}

	/**
	 * @return numarul total de voturi
	 */
	public int getTotalVoturi() {
		int total = 0;
		for (Candidat candidat : candidati) {
			total += candidat.getNumarVoturi();
		}

		return total;
	}

	/**
	 * Adauga un votant in alegere
	 * @param votant
	 */
	public void addVotant(Votant votant) {
		this.votanti.add(votant);
	}

	/**
	 * @return votantii din alegere
	 */
	public ArrayList<Votant> getVotanti() {
		return this.votanti;
	}

	/**
	 * @param cnp
	 * @return votantul cu cnp-ul dat ca parametru
	 */
	public Votant getVotant(String cnp) {
		for (Votant votant : votanti) {
			if (votant.cnp.equalsIgnoreCase(cnp))
				return votant;
		}

		return null;
	}

	/**
	 * @return numarul de votanti fraudosi
	 */
	public int numarFraudosi() {
		int total = 0;
		for (Circumscriptie circumscriptie : this.circumscriptii) {
			total += circumscriptie.getFraudosi().size();
		}

		return total;
	}
}
