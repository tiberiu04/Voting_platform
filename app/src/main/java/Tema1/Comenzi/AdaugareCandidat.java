package Tema1.Comenzi;
import Tema1.Entitati.Alegere;
import Tema1.Entitati.Candidat;

import java.util.HashMap;
import java.util.Scanner;

public class AdaugareCandidat implements Comanda {
	private Scanner scanner;

	public AdaugareCandidat(Scanner scanner) {
		this.scanner = scanner;
	}

	public void executa() {
		String id = this.scanner.next();
		String cnp = this.scanner.next();
		int varsta = this.scanner.nextInt();
		String nume = this.scanner.nextLine().trim();

		CreareAlegeri creareAlegeri = new CreareAlegeri(this.scanner);
		HashMap<String, Alegere> alegeriMap = creareAlegeri.getAlegeriMap();
		Alegere alegere = alegeriMap.get(id);

		// Verific daca datele sunt valide pentru adaugarea candidatului
		if (!dateCandidatValide(alegere, cnp, varsta, nume)) return;

		// Adaug candidatul daca datele sunt valide
		Candidat candidat = new Candidat(varsta, nume, cnp);
		alegere.adaugaCandidat(candidat);
		System.out.println("S-a adaugat candidatul " + nume);
	}

	private boolean dateCandidatValide(Alegere alegere, String cnp, int varsta, String nume) {
		if (alegere == null) {
			System.out.println("EROARE: Nu exista alegeri cu acest id");
			return false;
		} else if (cnp.length() != 13) {
			System.out.println("EROARE: CNP invalid");
			return false;
		} else if (varsta < 35) {
			System.out.println("EROARE: Varsta invalida");
			return false;
		} else if (!alegere.getEPornita()) {
			System.out.println("EROARE: Nu este perioada de votare");
			return false;
		} else if (alegere.areAcelasiCnp(nume, cnp)) {
			System.out.println("EROARE: Candidatul " + nume + " are deja acelasi CNP");
			return false;
		}
		return true;
	}
}
