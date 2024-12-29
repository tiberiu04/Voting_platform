package Tema1.Comenzi;
import Tema1.Entitati.*;
import Tema1.Utilitati.Stiva;
import java.util.HashMap;
import java.util.Scanner;

public class Votare implements Comanda {
	private Scanner scanner;

	public Votare(Scanner scanner) {
		this.scanner = scanner;
	}

	public void executa() {
		CreareAlegeri creareAlegeri = new CreareAlegeri(this.scanner);
		HashMap<String, Alegere> map = creareAlegeri.getAlegeriMap();
		String id = this.scanner.next();
		String numeCircum = this.scanner.next();
		String cnpVotant = this.scanner.next();
		String cnpCandidat = this.scanner.nextLine().trim();
		Stiva stiva = new Stiva();

		// Verific daca alegerile sunt valide
		if (!verificaAlegeri(id, numeCircum, cnpVotant, cnpCandidat, map)) {
			return;
		}

		// Verific daca votantul a comis o frauda si adaug frauda in stiva daca este cazul
		if (verificaFrauda(id, numeCircum, cnpVotant, map, stiva)) {
			return;
		}

		// Procesarea votului daca nu exista frauda
		proceseazaVot(id, numeCircum, cnpVotant, cnpCandidat, map);
	}

	private boolean verificaAlegeri(String id, String numeCircum, String cnpVotant, String cnpCandidat, HashMap<String, Alegere> map) {
		if (!map.containsKey(id)) {
			System.out.println("EROARE: Nu exista alegeri cu acest id");
			return false;
		} else if (!map.get(id).getEPornita()) {
			System.out.println("EROARE: Nu este perioada de votare");
			return false;
		} else if (map.get(id).getCircumscriptie(numeCircum) == null) {
			System.out.println("EROARE: Nu exista o circumscriptie cu numele " + numeCircum);
			return false;
		} else if (!map.get(id).existaVotant(cnpVotant)) {
			System.out.println("EROARE: Nu exista niciun votant cu CNP-ul " + cnpVotant);
			return false;
		} else if (!map.get(id).existaCandidat(cnpCandidat)) {
			System.out.println("EROARE: Nu exista un candidat cu CNP-ul " + cnpCandidat);
			return false;
		}
		return true;
	}

	private boolean verificaFrauda(String id, String numeCircum, String cnpVotant, HashMap<String, Alegere> map, Stiva stiva) {
		if (map.get(id).getCircumscriptie(numeCircum).getVotant(cnpVotant) == null ||
				map.get(id).getCircumscriptie(numeCircum).getVotant(cnpVotant).isaVotat()) {
			System.out.println("FRAUDa: Votantul cu CNP-ul " + cnpVotant + " a incercat sa comita o frauda. Votul a fost anulat");
			Frauda frauda = new Frauda(numeCircum, cnpVotant, map.get(id).getVotant(cnpVotant).getNume());
			stiva.adaugaFrauda(frauda);
			return true;
		}
		return false;
	}

	private void proceseazaVot(String id, String numeCircum, String cnpVotant, String cnpCandidat, HashMap<String, Alegere> map) {
		// Votantul a votat pentru candidatul cu cnp-ul dat
		Votant votant = map.get(id).getCircumscriptie(numeCircum).getVotant(cnpVotant);
		Candidat candidat = map.get(id).getCandidat(cnpCandidat);
		String numeCandidat = candidat.getNume();

		// Marcheaza ca a votat
		votant.setAVotat(true);
		Circumscriptie circumscriptie = map.get(id).getCircumscriptie(numeCircum);

		System.out.println(votant.getNume() + " a votat pentru " + numeCandidat);

		// Incrementez numarul de voturi al candidatului
		candidat.setNumarVoturi(candidat.getNumarVoturi() + 1);

		// Daca candidatul nu este deja in circumscriptie, il adaug
		if (circumscriptie.getCandidat(candidat) == null)
			circumscriptie.addCandidat(candidat);

		// Adaug votul candidatului in circumscriptie
		circumscriptie.adaugaVot(cnpCandidat);
	}
}
