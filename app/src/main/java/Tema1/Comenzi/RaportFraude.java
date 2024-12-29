package Tema1.Comenzi;
import Tema1.Entitati.Alegere;
import Tema1.Entitati.Frauda;
import Tema1.Utilitati.Stiva;

import java.util.HashMap;
import java.util.Scanner;

public class RaportFraude implements Comanda {
	private Scanner scanner;
	private Stiva stiva;
	private CreareAlegeri creareAlegeri;

	public RaportFraude(Scanner scanner) {
		this.scanner = scanner;
		this.stiva = new Stiva();
		this.creareAlegeri = new CreareAlegeri(this.scanner);
	}

	public void executa() {
		String id = this.scanner.next();
		HashMap<String, Alegere> alegeriMap = creareAlegeri.getAlegeriMap();

		// Verific daca alegerile exista si sunt valide pentru raportarea fraudei
		if (!verificaAlegeri(id, alegeriMap)) {
			return;
		}

		raporteazaFraude();
	}

	private boolean verificaAlegeri(String id, HashMap<String, Alegere> alegeriMap) {
		// Verific daca alegerile exista in harta
		if (!alegeriMap.containsKey(id)) {
			System.out.println("EROARE: Nu exista alegeri cu acest id");
			return false;
		}

		// Verific daca perioada de votare a fost deja incheiata
		if (alegeriMap.get(id).getEPornita()) {
			System.out.println("EROARE: Inca nu s-a terminat votarea.");
			return false;
		}

		return true;
	}

	private void raporteazaFraude() {
		// Verific daca exista fraude in stiva
		if (stiva.getStiva().isEmpty()) {
			System.out.println("GOL: Romanii sunt cinstiti");
		} else {
			System.out.println("Fraude comise:");
			// Parcurg fiecare frauda din stiva si o afisez
			for (Frauda frauda : stiva.getStiva()) {
				System.out.println(frauda.toString(frauda.getNumeCircum(), frauda.getCnp(), frauda.getNume()));
			}

			// Sterg toate fraudele din stiva dupa raportare
			while (!stiva.getStiva().isEmpty()) {
				stiva.getStiva().pop();
			}
		}
	}
}
