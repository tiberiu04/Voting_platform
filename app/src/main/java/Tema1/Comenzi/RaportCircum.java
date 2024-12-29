package Tema1.Comenzi;
import Tema1.Entitati.Alegere;
import Tema1.Entitati.Candidat;
import Tema1.Entitati.Circumscriptie;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Scanner;

public class RaportCircum implements Comanda {
	private Scanner scanner;

	public RaportCircum(Scanner scanner) {
		this.scanner = scanner;
	}

	public void executa() {
		String id = this.scanner.next();
		String numeCircum = this.scanner.nextLine().trim();

		CreareAlegeri creareAlegeri = new CreareAlegeri(this.scanner);
		HashMap<String, Alegere> alegeriMap = creareAlegeri.getAlegeriMap();

		if (!verificaAlegeriValide(alegeriMap, id, numeCircum)) {
			return;
		}

		genereazaRaport(alegeriMap.get(id).getCircumscriptie(numeCircum));
	}

	private boolean verificaAlegeriValide(HashMap<String, Alegere> alegeriMap, String id, String numeCircum) {
		if (!alegeriMap.containsKey(id)) {
			System.out.println("EROARE: Nu exista alegeri cu acest id");
			return false;
		}

		if (alegeriMap.get(id).getEPornita()) {
			System.out.println("EROARE: Inca nu s-a terminat votarea");
			return false;
		}

		if (!alegeriMap.get(id).existaCircumscriptie(numeCircum)) {
			System.out.println("EROARE: Nu exista o circumscriptie cu numele " + numeCircum);
			return false;
		}

		return true;
	}

	private void genereazaRaport(Circumscriptie circumscriptie) {
		if (circumscriptie.getCandidati().isEmpty()) {
			System.out.println("GOL: Lumea nu isi exercita dreptul de vot in " + circumscriptie.getNume());
		} else {
			ArrayList<Candidat> candidati = circumscriptie.getCandidati();

			// Sortez candidatii dupa numarul de voturi, iar daca au acelasi numar de voturi ii sortez dupa CNP
			candidati.sort(Comparator.comparingInt((Candidat c) -> circumscriptie.getVoturiCandidat(c.getCnp()))
					.reversed()
					.thenComparing(Candidat::getCnp, Comparator.reverseOrder()));

			afiseazaRaport(candidati, circumscriptie);
		}
	}

	private void afiseazaRaport(ArrayList<Candidat> candidati, Circumscriptie circumscriptie) {
		System.out.println("Raport voturi " + circumscriptie.getNume() + ":");
		for (Candidat candidat : candidati) {
			System.out.print(candidat.getNume().trim() + " " + candidat.getCnp() + " - ");
			System.out.println(circumscriptie.getVoturiCandidat(candidat.getCnp()));
		}
	}
}
