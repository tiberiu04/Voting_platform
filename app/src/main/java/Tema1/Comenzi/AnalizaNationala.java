package Tema1.Comenzi;
import Tema1.Entitati.Alegere;
import Tema1.Entitati.Candidat;
import Tema1.Entitati.Circumscriptie;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.TreeMap;

public class AnalizaNationala implements Comanda {
	private Scanner scanner;

	public AnalizaNationala(Scanner scanner) {
		this.scanner = scanner;
	}

	public void executa() {
		String id = this.scanner.nextLine().trim();
		CreareAlegeri creareAlegeri = new CreareAlegeri(this.scanner);
		HashMap<String, Alegere> alegeriMap = creareAlegeri.getAlegeriMap();
		Alegere alegere = alegeriMap.get(id);

		// Verific daca alegerea exista si daca votarea a fost finalizata
		if (!verificaAlegere(alegere)) return;

		// Verific daca exista voturi
		if (alegere.getTotalVoturi() == 0) {
			System.out.println("GOL: Lumea nu isi exercita dreptul de vot in Romania");
			return;
		}

		// Afisam numarul total de voturi din Romania
		int voturiNational = alegere.getTotalVoturi();
		System.out.println("in Romania au fost " + voturiNational + " voturi.");

		// Grupam circumscriptiile pe regiuni si afisam rezultatele
		afiseazaRezultatePeRegiuni(alegere, voturiNational);
	}

	private boolean verificaAlegere(Alegere alegere) {
		if (alegere == null) {
			System.out.println("EROARE: Nu exista alegeri cu acest id");
			return false;
		}

		if (alegere.getEPornita()) {
			System.out.println("EROARE: Inca nu s-a terminat votarea");
			return false;
		}

		return true;
	}

	private void afiseazaRezultatePeRegiuni(Alegere alegere, int voturiNational) {
		// Grupam circumscriptiile pe regiuni si afisam pentru fiecare
		alegere.getCircumscriptii().stream()
				.collect(Collectors.groupingBy(Circumscriptie::getRegiune,
						() -> new TreeMap<>(Comparator.reverseOrder()),
						Collectors.toList()))
				.forEach((regiune, circumscriptii) -> {
					// Calculam voturile pentru regiune
					int voturiRegiune = circumscriptii.stream()
							.mapToInt(c -> c.getCandidati().stream()
									.mapToInt(candidat -> c.getVoturiCandidat(candidat.getCnp()))
									.sum())
							.sum();
					int procentRegiune = (voturiRegiune * 100) / voturiNational;

					// Afisam candidatul cu cele mai multe voturi din regiune
					Candidat candidatTop = gasesteCandidatulTop(circumscriptii);

					if (candidatTop != null) {
						// Calculam procentul pentru candidatul cu cele mai multe voturi
						int procentTopRegiune = (circumscriptii.stream()
								.mapToInt(c -> c.getVoturiCandidat(candidatTop.getCnp()))
								.sum() * 100) / voturiRegiune;

						// Afisam rezultatele
						System.out.println("in " + regiune + " au fost " + voturiRegiune + " voturi din " + voturiNational
								+ ". Adica " + procentRegiune + "%. Cele mai multe voturi au fost stranse de "
								+ candidatTop.getCnp() + " " + candidatTop.getNume().trim() + ". Acestea constituie "
								+ procentTopRegiune + "% din voturile regiunii.");
					}
				});
	}

	private Candidat gasesteCandidatulTop(java.util.List<Circumscriptie> circumscriptii) {
		return circumscriptii.stream()
				.flatMap(c -> c.getCandidati().stream())
				.max(Comparator.comparingInt((Candidat candidat) -> circumscriptii.stream()
								.mapToInt(c -> c.getVoturiCandidat(candidat.getCnp()))
								.sum())
						.thenComparing(Candidat::getCnp, Comparator.naturalOrder()))
				.orElse(null);
	}
}
