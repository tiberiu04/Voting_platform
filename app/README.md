*** GUTANU TIBERIU-MIHNEA 321CA ***

*** Clasa App ***
Reprezinta punctul central al aplicatiei si gestioneaza comenzile 
introduse de utilizator prin consola. Fiecare comanda, reprezentata de un numar, 
reprezinta o actiune corespunzatoare in cadrul sistemului de alegeri.

Metoda run functioneaza în mod continuu pana cand utilizatorul introduce comanda de oprire
(comanda 18). In cadrul acestei metode, se citeste o comanda numerotata de la utilizator, 
se identifica tipul comenzii si se  executa clasa corespunzatoare acesteia.

PACHET ENTITATI 

*** Clasa Alegere ***
gestioneaza toate entitatile si operatiunile legate de o alegere electorala, 
inclusiv candidatii, votantii si circumscriptiile asociate. Aceasta permite adaugarea, 
modificarea și verificarea entitatilor asociate și gestioneaza operatiuni importante, 
cum ar fi pornirea alegerilor si calculul numarului total de voturi sau fraudelor.

*** Clasa Persoana ***
reprezinta o entitate generala pentru o persoana, utilizata pentru a stoca informatii de baza 
cum ar fi numele si varsta. Aceasta este o clasa parinte pentru alte entitati: Candidat și Votant.

*** Clasa Candidat ***
extinde clasa Persoana si reprezinta o entitate de candidat intr-un sistem de alegeri. 
Un Candidat are, pe langa atributele mostenite de la Persoana (varsta si nume), 
si un cnp si un numar de voturi obtinut in alegeri. In cadrul clasei regasim metode pentru
validarea CNP-ului si a varstei, precum si pentru gestionarea numarului de voturi.

*** Clasa Votant ***
extinde clasa Persoana si reprezinta o entitate specifica a unui votant. Aceasta contine
informatii aditionale despre votant.

*** Clasa Circumscriptie ***
reprezinta o circumscriptie electorala in care are loc votul. Aceasta pastreaza o lista 
de votanti, candidati, si numarul de voturi per candidat. Clasa permite adaugarea de 
votanti si candidati, urmarirea voturilor si gestionarea votanților suspectati de frauda.

*** Clasa Frauda ***
reprezinta o entitate care descrie un caz de frauda intr-o anumita circumscriptie. 
Aceasta contine informatii despre circumscriptia in care a avut loc frauda, CNP-ul persoanei 
implicate si numele acesteia. Clasa include metode pentru setarea si obținerea acestor informatii.
Clasa contribuie la afisarea informatiilor legate de cazurile de frauda, permitand monitorizarea
persoanelor si circumscriptiilor implicate.

PACHET COMENZI 

*** Interfața Comanda ***
defineste o structura de baza pentru toate comenzile din aplicatie. Aceasta contine o singura 
metoda abstracta, care va fi implementata de toate clasele ce doresc sa execute
o actiune specifica in cadrul aplicatiei.

*** Clasa AdaugareCandidat ***
implementeaza interfata Comanda, adaugand candidati la alegeri in functie de datele 
furnizate de la tastatura. Proceseaza datele  introduse si se asigura ca fiecare
candidat respecta anumite criterii de eligibilitate înainte de a fi adaugat la alegere.

*** Clasa AdaugareCircumscriptie *** 
este utilizata pentru a adauga circumscriptii. Implementand interfata Comanda, 
clasa defineste operatiile necesare pentru a introduce o circumscriptie noua,
verificand daca aceasta este valida inainte de a o adauga la alegerile existente.

*** Clasa AdaugareVotant *** 
permite adaugarea de votanti intr-o anumita circumscriptie. Implementand interfata Comanda, 
aceasta clasa citeste datele votantului de la tastatura si verifica informatiile inainte de a le 
inregistra in sistem.

*** Clasa OprireAlegeri ***
obtine la inceput un hashmap cu toate alegerile active urmand apoi sa valideze datele
citite la tastatura. In caz de conditiile nu sunt indeplinite se afiseaza mesaj de eroare.
In celalalt caz, se va afisa la ecran un mesaj de confirmare ce indica succesul operatiunii.

*** Clasa ListareAlegeri, ListareVotanti si ListareCandidati ***
au aceeasi functionalitate, la inceput valideaza datele urmand apoi sa verifice condtiile.
Daca acestea sunt indeplinite, se va afisa la ecran o lista de alegeri cu numele si id ul
acestora si cea de votanti cu nume cnp si varsta.

*** Clasele EliminareCandidat si EliminareCircumscriptie ***
gestioneaza eliminarea entitatilor din cadrul unei alegeri.
Ambele clase incep prin citirea datelor de intrare si verifica daca alegerile
exista si daca perioada de votare este activa. In cazul eliminarii
unui candidat, se verifica daca acesta este inregistrat, iar apoi este sters din lista
globala de candidati si din toate circumscriptiile unde are voturi. 
In cazul eliminarii unei circumscriptii, se verifica existenta acesteia,
iar apoi este stearsa din lista de circumscriptii a alegerii. La final, ambele clase
afiseaza mesaje care confirma cu succes operatiunea efectuata.

*** Clasa PornireAlegeri ***
implementeaza logica pentru a porni o alegere, validand in prealabil daca alegerile 
pot fi activate. Incepe prin a citi id ul alegerii. Apoi, verifica daca alegerile cu ID-ul 
respectiv exista si daca acestea nu au fost deja pornite. 
Daca alegerile nu sunt valide, procesul se opreste si se afiseaza un mesaj de eroare. 
Daca alegerile sunt valide, se seteaza starea acestora ca fiind pornita si afiseaza un mesaj
de confirmare.

*** Clasele RaportCircum, RaportFraude, RaportNational ***
genereaza rapoarte despre alegeri pe baza diferitelor criterii. Fiecare clasa urmeaza un 
proces similar de validare a datelor, dar raporteaza informatii diferite.

*** Clasa Votare ***
gestioneaza procesul de vot intr-un sistem electoral. Intai verifica daca datele introduse
sunt valude, detecteaza tentativele de frauda si proceseaza voturile valide.

*** Clasa AnalizaCircumscriptie ***
analizeaza rezultatele votului intr-o anumita circumscriptie. Valideaza la inceput datele
de intrare, verifica daca votarea s-a incheiat si daca circumscriptia este una valida. Daca avem
candidati ii sorteaza dupa numarul de voturi si afiseaza numarul total de voturi,
procentul voturilor din total de alegeri si candidatul cu cele mai multe voturi.
Daca nu exista voturi, se afiseaza un mesaj corespunzator.

*** Clasa AnalizaNationala ***
analizeaza rezultatele voturilor la nivel national. Aceasta verifica daca alegerile exista
si daca votarea s-a incheiat. Daca sunt voturi, afiseaza numarul total de voturi si diverse
statistici pe regiuni precum: procentul voturilor si candidatul cu cele mai multe voturi din
fiecare regiune.

PACHET UTILITATI

*** Clasa Stiva ***
este responsabila pentru gestionarea unei stive de obiecte de tip Frauda. 
Aceasta contine metode pentru adaugarea unei fraude în stiva si pentru verificarea 
existentei unei fraude specifice, asigurand ca stiva nu contine duplicate.


CAZURI LIMITA

1. Cod invalid al comenzii: ar trebui tratat cazul cand utilizatorul introduce o 
valoare diferita de numerele de la 0 la 18 pentru a evita complicatii la nivelul programului
2. Nume goale sau formatate gresit: daca nume, numeCircum, sau regiune sunt goale sau contin doar 
spatii, ar trebui gestionate aceste cazuri, astfel incat sa nu se adauge obiecte cu denumiri 
invalide. In metodele executa, as adauga verificari pentru a ne asigura ca aceste variabile 
nu sunt null.
3. Anulare vot la eliminare circumscriptie: atunci cand elimin o circumscriptie sa anulez si 
voturile candidatilor care au fost votati din acea circumscriptie.
4. Anulare vot neindemanatic: atunci cand am un votant neindemanatic nu ar trebui sa se numere votul
acestuia, doar sa fie setat acesta ca a votat pentru aa tine cont daca comite o frauda.
5. Raport fraude: dupa ce s-a facut un raport al fraudelor acestea ar trebui eliminate intrucat au 
fost raportate autoritatilor.
6. Stergere alegeri: dupa ce este stearsa o alegere ar trebui sa se anuleze toate voturile din acea
alegere si sa se verifice intai daca ar fi in curs de desfasurare.

REFACTORIZARI

1. As crea o clasa dedicata pentru validare, precum ValidatorComanda, unde fiecare tip de validare 
specifica fiecarei comenzi ar fi organizat separat.
2. Pentru a unifica mesajele de eroare si succes intr-o singură structura, as crea o clasa RaspunsComanda. 
Aceasta poate contine tipuri de raspuns predefinite si metode pentru a compune mesaje detaliate.
3. As restructura metoda executa din fiecare comanda astfel incat sa contina doar logica principala si 
apeluri catre metode de validare si mesaje de rsspuns. Acest lucru ar imbunatati lizibilitatea si ar 
permite identificarea rapida a pasilor esentiali din comenzi.
4. As crea o clasa ManagerComenzi pentru a gestiona toate comenzile si pentru a facilita apelarea 
acestora. Aceasta ar putea stoca o mapare intre numele comenzilor si clasele corespunzatoare, 
executand comenzile intr-un mod centralizat.
5. Pentru a evita codul duplicat in metodele de adaugare (candidat, votant, circumscriptie), 
as crea o metoda comuna în ManagerComenzi care verifica existenta unei alegeri, perioadei 
de vot si alte validari similare.
6. In loc de a folosi HashMap pentru toate colectiile de date, ar putea fi mai eficient
sa se foloseasca alte structuri de date. De exemplu, HashSet ar  putea fi utilizat pentru 
a evita duplicarile atunci cand vrem sa tinem evidenta unui set de valori unice.
