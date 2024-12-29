package Tema1.Comenzi;
import Tema1.Entitati.Alegere;
import Tema1.Entitati.Candidat;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Scanner;

public class RaportNational implements Comanda {
	private Scanner scanner;

	public RaportNational(Scanner scanner) {
		this.scanner = scanner;
	}

	public void executa() {
		String id = this.scanner.nextLine().trim();

		CreareAlegeri a = new CreareAlegeri(this.scanner);
		HashMap<String, Alegere> alegeriMap = a.getAlegeriMap();

		/*
		 * Verific daca exista alegeri cu id-ul dat si daca votarea este pornita
		 * si daca s-a votat.
		 */
		if (!alegeriMap.containsKey(id)) {
			System.out.println("EROARE: Nu exista alegeri cu acest id");
		} else if (alegeriMap.get(id).getEPornita()) {
			System.out.println("EROARE: Inca nu s-a terminat votarea");
		} else if (alegeriMap.containsKey(id)) {
			ArrayList<Candidat> candidati = alegeriMap.get(id).getCandidati();
			if (alegeriMap.get(id).getTotalVoturi() == 0) {
				System.out.println("GOL: Lumea nu isi exercita dreptul de vot in Romania");
			} else {
				System.out.println("Raport voturi Romania:");
				/*
				 * Sortez candidatii dupa numarul de voturi primit, iar daca au acelasi numar de
				 * voturi ii sortez dupa cnp-ul lor.
				 */
				candidati.sort(Comparator
						.comparingInt(Candidat::getNumarVoturi).reversed()
						.thenComparing(Candidat::getCnp).reversed());
				for (Candidat candidat : candidati) {
					System.out.println(candidat.getNume().trim() + " " + candidat.getCnp() + " - " + candidat.getNumarVoturi());
				}
			}
		}
	}
}
