package Tema1.Comenzi;
import Tema1.Entitati.Alegere;

import java.util.HashMap;
import java.util.Scanner;

public class PornireAlegeri implements Comanda {
	private Scanner scanner;

	public PornireAlegeri(Scanner scanner) {
		this.scanner = scanner;
	}

	public void executa() {
		String idAlegeri = this.scanner.nextLine().trim();
		CreareAlegeri creareAlegeri = new CreareAlegeri(this.scanner);
		HashMap<String, Alegere> mapaVoturi = creareAlegeri.getAlegeriMap();

		if (!verificaAlegeriValide(mapaVoturi, idAlegeri)) {
			return;
		}

		pornireAlegeri(mapaVoturi, idAlegeri);
	}

	private boolean verificaAlegeriValide(HashMap<String, Alegere> mapaVoturi, String idAlegeri) {
		if (!mapaVoturi.containsKey(idAlegeri)) {
			System.out.println("EROARE: Nu exista alegeri cu acest id");
			return false;
		}

		if (mapaVoturi.get(idAlegeri).getEPornita()) {
			System.out.println("EROARE: Alegerile deja au inceput");
			return false;
		}

		return true;
	}

	private void pornireAlegeri(HashMap<String, Alegere> mapaVoturi, String idAlegeri) {
		Alegere alegere = mapaVoturi.get(idAlegeri);
		alegere.setEPornita(true);
		System.out.println("Au pornit alegerile " + alegere.getNume());
	}
}
