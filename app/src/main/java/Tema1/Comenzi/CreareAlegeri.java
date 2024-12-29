package Tema1.Comenzi;
import Tema1.Entitati.Alegere;

import java.util.HashMap;
import java.util.Scanner;

public class CreareAlegeri implements Comanda {
	private String idAlegeri;
	private String numeAlegeri;
	private static HashMap<String, Alegere> alegeriMap = new HashMap<>();
	private Scanner scanner;

	public CreareAlegeri(Scanner scanner) {
		this.scanner = scanner;
	}

	public void executa() {
		// Citim id-ul si numele alegerilor
		idAlegeri = this.scanner.next();
		numeAlegeri = this.scanner.nextLine().trim();

		// Verificam daca alegerile cu acest id exista deja
		if (existaAlegeriCuId(idAlegeri)) {
			System.out.println("EROARE: Deja exista alegeri cu id " + idAlegeri);
		} else {
			creazaAlegeri();
		}
	}

	private boolean existaAlegeriCuId(String idAlegeri) {
		return alegeriMap.containsKey(idAlegeri);
	}

	private void creazaAlegeri() {
		Alegere alegere = new Alegere();
		alegere.setid(idAlegeri);
		alegere.setNume(numeAlegeri);
		alegere.setEPornita(false); // Perioada de votare nu a fost pornita initial
		alegeriMap.put(idAlegeri, alegere);
		System.out.println("S-au creat alegerile " + numeAlegeri);
	}

	public HashMap<String, Alegere> getAlegeriMap() {
		return this.alegeriMap;
	}
}
