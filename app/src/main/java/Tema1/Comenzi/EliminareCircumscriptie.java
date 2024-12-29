package Tema1.Comenzi;

import Tema1.Entitati.Alegere;

import java.util.HashMap;
import java.util.Scanner;

public class EliminareCircumscriptie implements Comanda {
	private Scanner scanner;

	public EliminareCircumscriptie(Scanner scanner) {
		this.scanner = scanner;
	}

	public void executa() {
		String idAlegeri = this.scanner.next();
		String numeCircumscriptie = this.scanner.nextLine().trim();

		CreareAlegeri creareAlegeri = new CreareAlegeri(this.scanner);
		HashMap<String, Alegere> alegeriMap = creareAlegeri.getAlegeriMap();

		// Verificam daca alegerile cu id-ul dat exista si daca perioada de votare este pornita
		if (!existaAlegeriValide(alegeriMap, idAlegeri)) {
			return;
		}

		Alegere alegere = alegeriMap.get(idAlegeri);

		// Verificam daca circumscriptia cu numele dat exista
		if (!existaCircumscriptieValida(alegere, numeCircumscriptie)) {
			return;
		}

		// Stergerea circumscriptiei
		eliminaCircumscriptie(alegere, numeCircumscriptie);
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

	// Verific daca circumscriptia cu numele dat exista in alegeri
	private boolean existaCircumscriptieValida(Alegere alegere, String numeCircumscriptie) {
		if (!alegere.existaCircumscriptie(numeCircumscriptie)) {
			System.out.println("EROARE: Nu exista o circumscriptie cu numele " + numeCircumscriptie);
			return false;
		}

		return true;
	}

	// Sterg circumscriptia din lista de circumscriptii
	private void eliminaCircumscriptie(Alegere alegere, String numeCircumscriptie) {
		alegere.eliminaCircumscriptie(numeCircumscriptie);
		System.out.println("S-a sters circumscriptia " + numeCircumscriptie);
	}
}
