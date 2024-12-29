package Tema1.Utilitati;
import Tema1.Entitati.Frauda;

import java.util.Stack;

public class Stiva {
	/**
	 * Stiva de fraude
	 */
	private static Stack<Frauda> stiva = new Stack<>();

	/**
	 * Getter pentru stiva
	 * @return stiva
	 */
	public static Stack<Frauda> getStiva() {
		return stiva;
	}

	/**
	 * Adauga o frauda in stiva
	 * @param frauda frauda de adaugat
	 */
	public void adaugaFrauda(Frauda frauda) {
		if (!contineFrauda(frauda)) {
			stiva.push(frauda);
		}
	}

	/**
	 * Verifica daca o frauda exista in stiva
	 * @param frauda frauda de verificat
	 * @return true daca frauda exista, false altfel
	 */
	public boolean contineFrauda(Frauda frauda) {
		for (Frauda f : stiva) {
			if (f.getNumeCircum().equals(frauda.getNumeCircum()) && f.getCnp().equals(frauda.getCnp())) {
				return true;
			}
		}
		return false;
	}
}
