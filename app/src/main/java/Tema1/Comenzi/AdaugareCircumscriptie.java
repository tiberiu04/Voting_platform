package Tema1.Comenzi;
import Tema1.Entitati.Alegere;
import Tema1.Entitati.Circumscriptie;

import java.util.HashMap;
import java.util.Scanner;

public class AdaugareCircumscriptie implements Comanda {
	private Scanner scanner;

	public AdaugareCircumscriptie(Scanner scanner) {
		this.scanner = scanner;
	}

	public void executa() {
		String id = this.scanner.next();
		String nume = this.scanner.next();
		String regiune = this.scanner.nextLine().trim();

		CreareAlegeri creareAlegeri = new CreareAlegeri(this.scanner);
		HashMap<String, Alegere> alegeriMap = creareAlegeri.getAlegeriMap();
		Alegere alegere = alegeriMap.get(id);

		// Verific daca datele sunt valide pentru adaugarea circumscriptiei
		if (!dateCircumscriptieValide(alegere, nume)) return;

		// Adaug circumscriptia
		Circumscriptie circumscriptie = new Circumscriptie(nume, regiune);
		alegere.adaugaCircumscriptie(circumscriptie);
		System.out.println("S-a adaugat circumscriptia " + nume);
	}

	private boolean dateCircumscriptieValide(Alegere alegere, String nume) {
		if (alegere == null) {
			System.out.println("EROARE: Nu exista alegeri cu acest id");
			return false;
		} else if (!alegere.getEPornita()) {
			System.out.println("EROARE: Nu este perioada de votare");
			return false;
		} else if (alegere.existaCircumscriptie(nume)) {
			System.out.println("EROARE: Deja exista o circumscriptie cu numele " + nume);
			return false;
		}
		return true;
	}
}
