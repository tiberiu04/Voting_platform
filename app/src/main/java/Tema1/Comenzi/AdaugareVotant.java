package Tema1.Comenzi;
import Tema1.Entitati.Alegere;
import Tema1.Entitati.Votant;

import java.util.HashMap;
import java.util.Scanner;

public class AdaugareVotant implements Comanda {
	private Scanner scanner;

	public AdaugareVotant(Scanner scanner) {
		this.scanner = scanner;
	}

	public void executa() {
		String id = this.scanner.next();
		String numeCircum = this.scanner.next();
		String cnp = this.scanner.next();
		int varsta = this.scanner.nextInt();
		String neind = this.scanner.next();
		boolean neindemanatic = !neind.equals("nu"); // Daca valoarea e "nu", neindemanatic devine false

		String nume = this.scanner.nextLine().trim();

		CreareAlegeri creareAlegeri = new CreareAlegeri(this.scanner);
		HashMap<String, Alegere> alegeriMap = creareAlegeri.getAlegeriMap();

		// Verific daca datele sunt valide si daca votantul poate fi adaugat
		if (!dateVotantValide(alegeriMap, id, numeCircum, cnp, varsta, nume)) return;

		// Daca datele sunt valide, adaug votantul in circumscriptie si in lista de votanti a alegerilor
		Votant votant = new Votant(varsta, nume, neindemanatic, cnp);
		alegeriMap.get(id).getCircumscriptie(numeCircum).adaugaVotant(votant);
		System.out.println("S-a adaugat votantul " + nume);
		alegeriMap.get(id).addVotant(votant);
	}

	private boolean dateVotantValide(HashMap<String, Alegere> alegeriMap, String id, String numeCircum, String cnp, int varsta, String nume) {
		if (cnp.length() != 13) {
			System.out.println("EROARE: CNP invalid");
			return false;
		} else if (varsta < 18) {
			System.out.println("EROARE: Varsta invalida");
			return false;
		} else if (!alegeriMap.containsKey(id)) {
			System.out.println("EROARE: Nu exista alegeri cu acest id");
			return false;
		} else if (!alegeriMap.get(id).existaCircumscriptie(numeCircum)) {
			System.out.println("EROARE: Nu exista o circumscriptie cu numele " + numeCircum);
			return false;
		} else if (!alegeriMap.get(id).getEPornita()) {
			System.out.println("EROARE: Nu este perioada de votare");
			return false;
		} else if (alegeriMap.get(id).getCircumscriptie(numeCircum).existaVotantCnp(cnp)) {
			System.out.println("EROARE: Votantul " + nume + " are deja acelasi CNP");
			return false;
		}
		return true;
	}
}
