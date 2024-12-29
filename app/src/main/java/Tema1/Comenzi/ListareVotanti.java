package Tema1.Comenzi;

import Tema1.Entitati.Alegere;
import Tema1.Entitati.Votant;

import java.util.*;

public class ListareVotanti implements Comanda {
	private Scanner scanner;

	public ListareVotanti(Scanner scanner) {
		this.scanner = scanner;
	}

	public void executa() {
		String idAlegeri = this.scanner.next().trim();
		String numeCircumscriptie = this.scanner.nextLine().trim();

		CreareAlegeri creareAlegeri = new CreareAlegeri(this.scanner);
		HashMap<String, Alegere> alegeriMap = creareAlegeri.getAlegeriMap();

		// Verificam daca alegerile sunt valide si perioada de votare a inceput
		if (!existaAlegeriValide(alegeriMap, idAlegeri)) {
			return;
		}

		Alegere alegere = alegeriMap.get(idAlegeri);

		// Verificam daca circumscriptia este valida
		if (!existaCircumscriptieValida(alegere, numeCircumscriptie)) {
			return;
		}

		// Afisam votantii din circumscriptie
		listareVotanti(alegere, numeCircumscriptie);
	}

	private boolean existaAlegeriValide(HashMap<String, Alegere> alegeriMap, String idAlegeri) {
		if (!alegeriMap.containsKey(idAlegeri)) {
			System.out.println("EROARE: Nu exista alegeri cu acest id");
			return false;
		}

		if (!alegeriMap.get(idAlegeri).getEPornita()) {
			System.out.println("EROARE: Inca nu au inceput alegerile");
			return false;
		}

		return true;
	}

	private boolean existaCircumscriptieValida(Alegere alegere, String numeCircumscriptie) {
		if (!alegere.existaCircumscriptie(numeCircumscriptie)) {
			System.out.println("EROARE: Nu exista o circumscriptie cu numele " + numeCircumscriptie);
			return false;
		}

		if (alegere.getCircumscriptie(numeCircumscriptie).getVotanti().isEmpty()) {
			System.out.println("GOL: Nu sunt votanti in " + numeCircumscriptie);
			return false;
		}

		return true;
	}

	// Afiseaz votantii din circumscriptie sortati dupa CNP
	private void listareVotanti(Alegere alegere, String numeCircumscriptie) {
		System.out.println("Votantii din " + numeCircumscriptie + ":");
		ArrayList<Votant> votanti = alegere.getCircumscriptie(numeCircumscriptie).getVotanti();

		Collections.sort(votanti, Comparator.comparing(Votant::getCnp));
		for (Votant votant : votanti) {
			System.out.println(votant.toString());
		}
	}
}
