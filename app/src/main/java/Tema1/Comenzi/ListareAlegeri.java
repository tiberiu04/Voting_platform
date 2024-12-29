package Tema1.Comenzi;
import Tema1.Entitati.Alegere;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ListareAlegeri implements Comanda {
	private Scanner scanner;

	public ListareAlegeri(Scanner scanner) {
		this.scanner = scanner;
	}

	public void executa() {
		CreareAlegeri a = new CreareAlegeri(this.scanner);
		HashMap<String, Alegere> map = a.getAlegeriMap();

		/*
		 * Daca nu exista alegeri afisez un mesaj corespunzator, altfel afisez alegerile
		 */
		if (map.isEmpty()) {
			System.out.println("GOL: Nu sunt alegeri");
		} else {
			System.out.println("Alegeri:");
			for (Map.Entry<String, Alegere> entry : map.entrySet()) {
				String id = entry.getKey();
				String nume = entry.getValue().getNume();
				System.out.println(id + " " + nume);
			}
		}
	}
}
