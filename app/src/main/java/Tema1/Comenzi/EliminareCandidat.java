package Tema1.Comenzi;

import Tema1.Entitati.Alegere;
import Tema1.Entitati.Candidat;
import Tema1.Entitati.Circumscriptie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class EliminareCandidat implements Comanda {
	private Scanner scanner;

	public EliminareCandidat(Scanner scanner) {
		this.scanner = scanner;
	}

	public void executa() {
		String idAlegeri = this.scanner.next();
		String cnpCandidat = this.scanner.nextLine().trim();

		CreareAlegeri creareAlegeri = new CreareAlegeri(this.scanner);
		HashMap<String, Alegere> alegeriMap = creareAlegeri.getAlegeriMap();

		// Verificam daca alegerea exista si daca perioada de votare este pornita
		if (!existaAlegeriValide(alegeriMap, idAlegeri)) {
			return;
		}

		Alegere alegere = alegeriMap.get(idAlegeri);

		// Verificam daca CNP-ul candidatului este valid
		if (!existaCandidatValid(alegere, cnpCandidat)) {
			return;
		}

		// Stergerea candidatului
		eliminaCandidat(alegere, cnpCandidat);
	}

	private boolean existaAlegeriValide(HashMap<String, Alegere> alegeriMap, String idAlegeri) {
		if (!alegeriMap.containsKey(idAlegeri)) {
			System.out.println("EROARE: Nu exista alegeri cu acest id");
			return false;
		}

		if (!alegeriMap.get(idAlegeri).getEPornita()) {
			System.out.println("EROARE: Nu este perioada de votare");
			return false;
		}

		return true;
	}

	// Verifica daca exista un candidat cu CNP-ul dat
	private boolean existaCandidatValid(Alegere alegere, String cnp) {
		if (!alegere.existaCandidat(cnp)) {
			System.out.println("EROARE: Nu exista un candidat cu CNP-ul " + cnp);
			return false;
		}

		return true;
	}

	// Elimina candidatul din circumscriptii si din lista de candidati
	private void eliminaCandidat(Alegere alegere, String cnp) {
		String numeCandidat = alegere.getNumeCandidat(cnp);
		ArrayList<Circumscriptie> circumscriptii = alegere.getCircumscriptii();
		Candidat candidat = alegere.getCandidat(cnp);

		// Eliminarea candidatul din fiecare circumscriptie
		for (Circumscriptie circumscriptie : circumscriptii) {
			if (circumscriptie.getCandidat(candidat) != null) {
				candidat.setNumarVoturi(candidat.getNumarVoturi() - circumscriptie.getVoturiCandidat(cnp));
				circumscriptie.getVoturiPerCandidat().remove(cnp);
			}
		}

		// Eliminarea candidatului din lista de candidati a alegerii
		alegere.eliminaCandidat(cnp);
		System.out.println("S-a sters candidatul " + numeCandidat);
	}
}
