import java.util.Scanner;

public class miniGame {

    static class Personaggio {
        String nome;
        String classeNome;
        int forza;
        int vite;
        int livello;
        
        String[] inventario;
        int numeroOggetti;
        final int MAX_OGGETTI = 10;

        public Personaggio(String nome) {
            this.nome = nome;
            this.livello = 1;
            this.inventario = new String[MAX_OGGETTI];
            this.numeroOggetti = 0;
            this.classeNome = "Avventuriero"; 
        }

        public void aggiungiOggetto(String oggetto) {
            if (numeroOggetti < MAX_OGGETTI) {
                inventario[numeroOggetti] = oggetto;
                numeroOggetti++;
                System.out.println(">> " + nome + " ha ottenuto: " + oggetto);
            } else {
                System.out.println(">> Inventario pieno!");
            }
        }

        public void mostraInventario() {
            System.out.println("\n--- ZAINO DI " + nome.toUpperCase() + " ---");
            if (numeroOggetti == 0) {
                System.out.println("(Vuoto)");
            } else {
                for (int i = 0; i < numeroOggetti; i++) {
                    System.out.println((i + 1) + ") " + inventario[i]);
                }
            }
            System.out.println("-----------------------");
        }

        public void mostraStatistiche() {
            System.out.println("\n*** STATISTICHE ***");
            System.out.println("Nome: " + nome);
            System.out.println("Classe: " + classeNome);
            System.out.println("Livello: " + livello);
            System.out.println("Vite: " + vite + " | Forza: " + forza);
            System.out.println("*******************");
        }
    }

    static Scanner input = new Scanner(System.in);
    static Personaggio player;
    static Personaggio Mikasa; 
    static Personaggio Fujino;
    static Personaggio Orco;
    static boolean giocoIniziato = false;

    public static void main(String[] args) {
        System.out.print("Nome personaggio: ");
        String nomeTemp = input.nextLine();
        player = new Personaggio(nomeTemp);

        System.out.println("***************************");
        System.out.println("Benvenuto " + player.nome);
        System.out.println("***************************");

        boolean esegui = true;
        while (esegui) {
            mostra_menu();
            int scelta = leggiIntero();

            switch (scelta) {
                case 1:
                    gestisciAvventura();
                    break;
                case 2:
                    System.out.println("\n-- TRAMA --");
                    System.out.println("Uccidi il Re dei demoni con l'aiuto degli aspiranti eroi!");
                    break;
                case 3:
                    if (giocoIniziato) {
                        player.mostraInventario();
                        if (Mikasa != null) Mikasa.mostraInventario();
                        if (Fujino != null) Fujino.mostraInventario();
                    } else {
                        System.out.println("Uscita dal gioco...");
                        esegui = false;
                    }
                    break;
                case 4:
                    System.out.println("Uscita dal gioco...");
                    esegui = false;
                    break;
                default:
                    System.out.println("Scelta non valida.");
            }
        }
        input.close();
    }

    static void mostra_menu() {
        System.out.println("\n--- MENU PRINCIPALE ---");
        if (!giocoIniziato) {
            System.out.println("1) Inizia il gioco");
            System.out.println("2) Trama");
            System.out.println("3) Esci");
        } else {
            System.out.println("1) Continua avventura");
            System.out.println("2) Trama");
            System.out.println("3) Inventario");
            System.out.println("4) Esci");
        }
        System.out.print("Scelta: ");
    }

    static int leggiIntero() {
        if (input.hasNextInt()) {          
            int numero = input.nextInt();  
            input.nextLine();              
            return numero;
        } else {
            input.nextLine();              
            return -1;                     
        }
    }

    static String leggiStringa() {
        return input.nextLine().toUpperCase();
    }

    static void gestisciAvventura() {
        if (!giocoIniziato) {
            scegliClasse();
            giocoIniziato = true;
        }
        
        if (player.classeNome.equals("Mago")) {
            storiaMago();
        } else {
            System.out.println("\nLa storia per " + player.classeNome + " e' ancora in sviluppo...");
        }
    }

    static void scegliClasse() {
        System.out.println("\nScegli la classe:");
        System.out.println("1) Mago");
        System.out.println("2) Assassino");
        System.out.println("3) Monaco");
        System.out.println("4) Healer");
        System.out.print("-> ");
        
        int scelta = leggiIntero();
        player.classeNome = "Mago"; 
        player.forza = 10; player.vite = 5; player.aggiungiOggetto("Bastone Magico");
        player.mostraStatistiche();
    }

    static void storiaMago() {
        System.out.println("\n--- CAPITOLO 1: L'ACCADEMIA ---");
        System.out.println("Ti svegli come figlio del Re.");
        System.out.println("1) Vai all'accademia");
        System.out.println("2) Studia a casa");
        System.out.print("-> ");

        int azione = leggiIntero();

        if (azione == 1) {
            System.out.println("\nVai all'accademia e sali di livello!");
            player.livello += 2; player.forza += 3; player.vite += 2;
            player.mostraStatistiche();
            
            System.out.println("Uno studente ti sfida a duello. \n1) Accetti\n2) Rifiuti");
            if (leggiIntero() == 1) {
                System.out.println("Vinci il duello! Guadagni esperienza.");
                player.livello++;
                incontraYui();
            } else {
                System.out.println("Rifiuti e vieni deriso.");
                rifarsi(); studia();
            }
        } else {
            System.out.println("Studi a casa.");
        }
    }

    static void rifarsi() {
        System.out.println("\nRifatti. Ti metti a studiare.");
        player.livello += 5;
    }

    static void studia() {
        System.out.println("Studia molto.");
        player.livello += 7;
    }

    static void incontraYui() {
        System.out.println("\nYui ti invita a mangiare. \n1) Accetta \n2) Rifiuta");
        if(leggiIntero() == 1) {
            System.out.println("Accetti e vai con lei.\n");
            incontro();
        } else {
            System.out.println("Te ne vai da solo.");
        }
    }

    static void Mikasa() {
        Mikasa = new Personaggio("Mikasa"); 
        Mikasa.classeNome = "Cacciatrice";
        Mikasa.forza = 8; Mikasa.vite = 7; Mikasa.aggiungiOggetto("Arco");
    }

    static void Fujino() {
        Fujino = new Personaggio("Fujino"); 
        Fujino.classeNome = "Healer";
        Fujino.forza = 8; Fujino.vite = 7; Fujino.aggiungiOggetto("Incantesimi");
    }

    static void incontro() {
        System.out.println("Mikasa e Fujino vogliono unirsi a te.");
        Mikasa(); 
        Fujino();
        
        Mikasa.livello += 5; Fujino.livello += 4;
        Mikasa.forza += 15; Fujino.forza += 13;
        Mikasa.vite += 10; Fujino.vite += 10;
        Mikasa.mostraStatistiche();
        Mikasa.aggiungiOggetto("Armatura");
        Fujino.aggiungiOggetto("Guanti magici");

        sceltaCompagni(); 
    } 

    static void sceltaCompagni() {
        System.out.println("1) Accetta");
        System.out.println("2) Rifiuta");
        System.out.print("-> ");
        
        int risp = leggiIntero();
        if(risp == 1) {
            System.out.println("Accetti. Viaggi verso il villaggio.\n");
            villaggio();
        } else {
            System.out.println("Rifiuti e vai da solo.\n");
        }
    }

    static void villaggio() {
        System.out.println("Arrivi al villaggio e ottieni un Mantello!\n");
        player.aggiungiOggetto("Mantello");
        player.livello += 2;
        player.mostraStatistiche();
        
        viaggio();
    }

    static void viaggio() {
        System.out.println("\n--- CAPITOLO 2: IL VIAGGIO ---");
        System.out.println("Inizi il tuo viaggio con Mikasa e Fujino.");
        System.out.println("Incontri un gruppo di mostri. Ti chiedono se vuoi combatterli.\n");
        System.out.println("1) Accetti");
        System.out.println("2) Rifiuti");
        System.out.print("-> ");

        int risp = leggiIntero(); 

        if(risp == 1) {
            System.out.println("Accetti. Ti imbatti in mostri deboli e vinci facile.\n");
            grotta();
        } else {
            System.out.println("Vi nascondete ma venite colpiti, perdendo vita!");
            Mikasa.vite -= 3;
            Fujino.vite -= 3;
            player.vite -= 3;
            
            System.out.println("Vite attuali -> Player: " + player.vite + ", Mikasa: " + Mikasa.vite + ", Fujino: " + Fujino.vite);
            grotta();
        }
    } 

    public static void grotta() {
        System.out.println("\n--- LA GROTTA ---");
        System.out.println("Trovate una grotta dove riposare e mangiate.");
        System.out.println("Poi arrivate al villaggio dei nani e parli con Takumi.");
        System.out.println("Takumi ti offre una spada in regalo.");
        System.out.println("1) Accetti");
        System.out.println("2) Rifiuti");
        System.out.print("-> ");

        int risp = leggiIntero();

        if(risp == 1) {
            System.out.println("Accetti. E' una spada in Mithril!\n");
            player.aggiungiOggetto("Spada in Mithril");
            dungeon();
        } else {
            System.out.println("Rifiuti l'offerta cordialmente.");
            dungeon();
        }
    }
       
    static void Orco() {
        Orco = new Personaggio("Orco"); 
        Orco.classeNome = "Mostro";
        Orco.forza = 8; Orco.vite = 20; Orco.aggiungiOggetto("Mazza di legno");
    }

    public static void dungeon() {
        System.out.println("\nVai nella gilda degli avventurieri e ti danno una quest obbligatoria: esplorare il dungeon per uccidere il re dei demoni.");
        System.out.println("Usa i tasti W, A, S, D per muoverti e premi Invio. 'P' sei tu.");
        
        char[][] mappaDungeon = {
            {'E', '-', '-', 'O'},
            {'X', 'X', '-', 'X'},
            {'R', '-', '-', 'X'}
        };
        
        int rigaGiocatore = 0;
        int colonnaGiocatore = 0;
        boolean inEsplorazione = true;

        while (inEsplorazione) {
            System.out.println("\n--- MAPPA DEL DUNGEON ---");
            for (int r = 0; r < mappaDungeon.length; r++) {
                for (int c = 0; c < mappaDungeon[r].length; c++) {
                    if (r == rigaGiocatore && c == colonnaGiocatore) {
                        System.out.print("P ");
                    } else {
                        System.out.print(mappaDungeon[r][c] + " ");
                    }
                }
                System.out.println();
            }
            System.out.println("-------------------------");
            
            if (mappaDungeon[rigaGiocatore][colonnaGiocatore] == 'O') {
                System.out.println("\nATTENZIONE! Ti sei imbattuto nell'Orco!");
                Orco(); 
                Orco.mostraStatistiche();
                mappaDungeon[rigaGiocatore][colonnaGiocatore] = '-';
                inEsplorazione = false;
                break;
            } else if (mappaDungeon[rigaGiocatore][colonnaGiocatore] == 'R') {
                System.out.println("\nATTENZIONE! Hai trovato la stanza del Re dei Demoni!");
                inEsplorazione = false;
                break;
            }

            System.out.print("Dove vuoi andare? (W=Su, S=Giu, A=Sinistra, D=Destra): ");
            String mossa = leggiStringa();

            int nuovaRiga = rigaGiocatore;
            int nuovaColonna = colonnaGiocatore;

            if (mossa.equals("W")) {
                nuovaRiga--;
            } else if (mossa.equals("S")) {
                nuovaRiga++;
            } else if (mossa.equals("A")) {
                nuovaColonna--;
            } else if (mossa.equals("D")) {
                nuovaColonna++;
            } else {
                System.out.println("Comando non valido.");
                continue;
            }

            if (nuovaRiga >= 0 && nuovaRiga < mappaDungeon.length && nuovaColonna >= 0 && nuovaColonna < mappaDungeon[0].length) {
                if (mappaDungeon[nuovaRiga][nuovaColonna] == 'X') {
                    System.out.println("Non puoi passare, c'e' un muro!");
                } else {
                    rigaGiocatore = nuovaRiga;
                    colonnaGiocatore = nuovaColonna;
                }
            } else {
                System.out.println("Non puoi uscire dai confini del dungeon!");
            }
        }
        
        giocoIniziato = false; 
    }
}
