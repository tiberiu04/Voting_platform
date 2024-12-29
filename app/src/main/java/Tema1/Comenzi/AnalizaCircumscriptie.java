package Tema1.Comenzi;
import Tema1.Entitati.Alegere;
import Tema1.Entitati.Candidat;
import Tema1.Entitati.Circumscriptie;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Scanner;

public class AnalizaCircumscriptie implements Comanda {
	private Scanner scanner;

	public AnalizaCircumscriptie(Scanner scanner) {
		this.scanner = scanner;
	}

	public void executa() {
		String id = this.scanner.next();
		String numeCircum = this.scanner.nextLine().trim();

		// Accesez alegerile
		CreareAlegeri creareAlegeri = new CreareAlegeri(this.scanner);
		HashMap<String, Alegere> alegeriMap = creareAlegeri.getAlegeriMap();

		// Verific daca alegerile sunt valide pentru analiza
		if (!validareAlegeri(alegeriMap, id, numeCircum)) return;

		Circumscriptie circumscriptie = alegeriMap.get(id).getCircumscriptie(numeCircum);
		if (circumscriptie.getCandidati().isEmpty()) {
			// Circumscriptia nu are candidati
			System.out.println("GOL: Lumea nu isi exercita dreptul de vot in " + numeCircum);
		} else {
			// Afisez candidatii sortati daca circumscriptia are voturi
			afiseazaCandidatiSortati(circumscriptie, alegeriMap.get(id));
		}
	}

	private boolean validareAlegeri(HashMap<String, Alegere> alegeriMap, String id, String numeCircum) {
		if (!alegeriMap.containsKey(id)) {
			System.out.println("EROARE: Nu exista alegeri cu acest id");
			return false;
		} else if (alegeriMap.get(id).getEPornita()) {
			System.out.println("EROARE: Inca nu s-a terminat votarea");
			return false;
		} else if (!alegeriMap.get(id).existaCircumscriptie(numeCircum)) {
			System.out.println("EROARE: Nu exista o circumscriptie cu numele " + numeCircum);
			return false;
		}
		return true;
	}

	private void afiseazaCandidatiSortati(Circumscriptie circumscriptie, Alegere alegere) {
		ArrayList<Candidat> candidati = circumscriptie.getCandidati();
		// Sortare candidati in functie de numarul de voturi, apoi dupa CNP
		candidati.sort(Comparator.comparingInt((Candidat c) -> circumscriptie.getVoturiCandidat(c.getCnp()))
				.reversed()
				.thenComparing(Candidat::getCnp).reversed());

		// Afisare statistici pentru candidatul cu cele mai multe voturi
		afiseazaStatisticiAlegeri(circumscriptie, alegere, candidati.get(0));
	}

	private void afiseazaStatisticiAlegeri(Circumscriptie circumscriptie, Alegere alegere, Candidat topCandidat) {
		int totalVoturiCircumscriptie = circumscriptie.getTotalVoturi();
		int totalVoturiAlegeri = alegere.getTotalVoturi();
		int voturiTopCandidat = circumscriptie.getVoturiCandidat(topCandidat.getCnp());

		// Calcul si afisare procente si voturi obtinute
		System.out.println("in " + circumscriptie.getNume() + " au fost " + totalVoturiCircumscriptie +
				" voturi din " + totalVoturiAlegeri + ". Adica " +
				(totalVoturiCircumscriptie * 100 / totalVoturiAlegeri) + "%. Cele mai multe voturi au fost stranse de " +
				topCandidat.getCnp() + " " + topCandidat.getNume() + ". Acestea constituie " +
				(voturiTopCandidat * 100 / totalVoturiCircumscriptie) + "% din voturile circumscriptiei.");
	}
}
