package Tema1.Comenzi;
import Tema1.Entitati.Alegere;

import java.util.HashMap;
import java.util.Scanner;

public class StergeAlegeri implements Comanda {
	private Scanner scanner;

	public StergeAlegeri(Scanner scanner) {
		this.scanner = scanner;
	}

	public void executa() {
		CreareAlegeri a = new CreareAlegeri(this.scanner);
		HashMap<String, Alegere> alegeriMap = a.getAlegeriMap();

		String id = this.scanner.nextLine().trim();

		/*
		 * Verific daca exista alegeri cu id-ul dat.
		 */
		if (!alegeriMap.containsKey(id)) {
			System.out.println("EROARE: Nu exista alegeri cu acest id");
		} else {
			/*
			 * Sterg alegerile cu id-ul dat.
			 */
			System.out.println("S-au sters alegerile " + alegeriMap.get(id).getNume());
			alegeriMap.get(id).stergeAlegeri(id);
		}
	}
}
