package Tema1;

import Tema1.Comenzi.*;

import java.io.*;
import java.util.*;

public class App {
    private Scanner scanner;

    public App(InputStream input) {
        this.scanner = new Scanner(input);
    }
    // Functia run citeste comanda de la tastatura si apeleaza clasa corespunzatoare comenzii
    public void run() {
        boolean running = true;
        while (running) {
            // Citesc de la tastatura numarul comenzii
            int comanda = this.scanner.nextInt();
            // Citesc endline-ul si in functie de comanda apelez clasa corespunzatoare
            this.scanner.nextLine();
            switch(comanda) {
                case 0:
                    CreareAlegeri creareAlegeri = new CreareAlegeri(this.scanner);
                    creareAlegeri.executa();
                    break;
                case 1:
                    PornireAlegeri pornireAlegeri = new PornireAlegeri(this.scanner);
                    pornireAlegeri.executa();
                    break;
                case 2:
                    AdaugareCircumscriptie adaugareCircumscriptie = new AdaugareCircumscriptie(this.scanner);
                    adaugareCircumscriptie.executa();
                    break;
                case 3:
                    EliminareCircumscriptie eliminareCircumscriptie = new EliminareCircumscriptie(this.scanner);
                    eliminareCircumscriptie.executa();
                    break;
                case 4:
                    AdaugareCandidat adaugareCandidat = new AdaugareCandidat(this.scanner);
                    adaugareCandidat.executa();
                    break;
                case 5:
                    EliminareCandidat eliminareCandidat = new EliminareCandidat(this.scanner);
                    eliminareCandidat.executa();
                    break;
                case 6:
                    AdaugareVotant adaugareVotant = new AdaugareVotant(this.scanner);
                    adaugareVotant.executa();
                    break;
                case 7:
                    ListareCandidati listareCandidati = new ListareCandidati(this.scanner);
                    listareCandidati.executa();
                    break;
                case 8:
                    ListareVotanti listareVotanti = new ListareVotanti(this.scanner);
                    listareVotanti.executa();
                    break;
                case 9:
                    Votare votare = new Votare(this.scanner);
                    votare.executa();
                    break;
                case 10:
                    OprireAlegeri oprireAlegeri = new OprireAlegeri(this.scanner);
                    oprireAlegeri.executa();
                    break;
                case 11:
                    RaportCircum raportcircum = new RaportCircum(this.scanner);
                    raportcircum.executa();
                    break;
                case 12:
                    RaportNational raportNational = new RaportNational(this.scanner);
                    raportNational.executa();
                    break;
                case 13:
                    AnalizaCircumscriptie analizaCircumscriptie = new AnalizaCircumscriptie(this.scanner);
                    analizaCircumscriptie.executa();
                    break;
                case 14:
                    AnalizaNationala analizaNationala = new AnalizaNationala(this.scanner);
                    analizaNationala.executa();
                    break;
                case 15:
                    RaportFraude raportFraude = new RaportFraude(this.scanner);
                    raportFraude.executa();
                    break;
                case 16:
                    StergeAlegeri stergeAlegeri = new StergeAlegeri(this.scanner);
                    stergeAlegeri.executa();
                    break;
                case 17:
                    ListareAlegeri listareAlegeri = new ListareAlegeri(this.scanner);
                    listareAlegeri.executa();
                    break;
                case 18:
                    running = false;
                    break;
                default:
                    break;
            }

        }
        scanner.close();
    }

    public static void main(String[] args) {
        App app = new App(System.in);
        app.run();
    }
}