package Tema1.Comenzi;

import Tema1.Entitati.Alegere;
import Tema1.Entitati.Candidat;

import java.util.*;

public class ListareCandidati implements Comanda {
	private Scanner scanner;

	public ListareCandidati(Scanner scanner) {
		this.scanner = scanner;
	}

	public void executa() {
		String idAlegeri = this.scanner.nextLine().trim();
		CreareAlegeri creareAlegeri = new CreareAlegeri(this.scanner);
		HashMap<String, Alegere> alegeriMap = creareAlegeri.getAlegeriMap();

		// Verificam daca alegerile sunt valide si daca perioada de votare a inceput
		if (!existaAlegeriValide(alegeriMap, idAlegeri)) {
			return;
		}

		Alegere alegere = alegeriMap.get(idAlegeri);

		// Verificam daca exista candidati in alegeri
		if (!existaCandidati(alegere)) {
			return;
		}

		// Afisam candidatii sortati dupa CNP
		listareCandidati(alegere);
	}

	private boolean existaAlegeriValide(HashMap<String, Alegere> alegeriMap, String idAlegeri) {
		if (!alegeriMap.containsKey(idAlegeri)) {
			System.out.println("EROARE: Nu exista alegeri cu acest id");
			return false;
		}

		if (!alegeriMap.get(idAlegeri).getEPornita()) {
			System.out.println("EROARE: Inca nu au inceput");
			return false;
		}

		return true;
	}

	// Verific daca exista candidati in alegeri
	private boolean existaCandidati(Alegere alegere) {
		if (alegere.getCandidatiDimensiune() == 0) {
			System.out.println("GOL: Nu sunt candidati");
			return false;
		}
		return true;
	}

	// Afisez candidatii sortati dupa CNP in ordine descrescatoare
	private void listareCandidati(Alegere alegere) {
		System.out.println("Candidatii:");
		ArrayList<Candidat> candidati = alegere.getCandidati();

		Collections.sort(candidati, Comparator.comparing(Candidat::getCnp).reversed());
		for (Candidat candidat : candidati) {
			System.out.println(candidat.toString());
		}
	}
}
