package Tema1.Comenzi;

import Tema1.Entitati.Alegere;

import java.util.HashMap;
import java.util.Scanner;

public class OprireAlegeri implements Comanda {
	private Scanner scanner;

	public OprireAlegeri(Scanner scanner) {
		this.scanner = scanner;
	}

	public void executa() {
		String idAlegeri = this.scanner.nextLine().trim();

		CreareAlegeri creareAlegeri = new CreareAlegeri(this.scanner);
		HashMap<String, Alegere> alegeriMap = creareAlegeri.getAlegeriMap();

		// Verific daca alegerile sunt valide (exista si perioada de votare a inceput)
		if (!verificaAlegeriValide(alegeriMap, idAlegeri)) {
			return;
		}

		// Oprim alegerile
		oprireAlegeri(alegeriMap, idAlegeri);
	}

	private boolean verificaAlegeriValide(HashMap<String, Alegere> alegeriMap, String idAlegeri) {
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

	// Opresc alegerile (seteaza starea la false)
	private void oprireAlegeri(HashMap<String, Alegere> alegeriMap, String idAlegeri) {
		Alegere alegere = alegeriMap.get(idAlegeri);
		alegere.setEPornita(false);
		System.out.println("S-au terminat alegerile " + alegere.getNume());
	}
}
