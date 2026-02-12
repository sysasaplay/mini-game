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

        public class Main {

            public static void main(String[] args) {
            
            /* LOGICA VISTA IN LABORATORIO PER L'ORDINAMENTO:
        
        String[] inventario = {"pozione", "spada", "arco", "scudo", null};
        
        for (int i=0; i < inventario.length -1; i++) {
            for (int j = 0; j < inventario.length -1 - i; j++) {
                
                // GESTIONE ELEMENTI VUOTI (null):
                // Se l'elemento attuale è null, lo spostiamo in avanti (verso la fine)
                if (inventario[j] == null && inventario[j + 1] != null) {
                    String temp = inventario[j];
                    inventario[j] = inventario[j + 1];
                    inventario[j + 1] = temp;
                } 
                // Altrimenti, se entrambi non sono null, facciamo il confronto alfabetico
                else if (inventario[j] != null && inventario[j + 1] != null) {
                    if (inventario[j].compareToIgnoreCase(inventario[j + 1]) > 0) {
                        String temp = inventario[j];
                        inventario[j] = inventario[j + 1];
                        inventario[j + 1] = temp;
                    }
                }
            }
        }
        
        System.out.println("Elenco ordinato alfabeticamente (vuoti in fondo): ");
        for (int i = 0; i < inventario.length; i++) {
            System.out.println((i + 1) + ") " + (inventario[i] == null ? "[Vuoto]" : inventario[i]));
        }
        */
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
        try {
            return Integer.parseInt(input.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
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
            
            System.out.println("Uno studente ti sfida a duello. 1) Accetti 2) Rifiuti");
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
        System.out.println("\nYui ti invita a mangiare. 1) Accetta 2) Rifiuta");
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
            continua();
        } else {
            System.out.println("Rifiuti l'offerta cordialmente.");
            continua();
        }
    }

    public static void continua() {
        System.out.println("\nIl viaggio continua... (Fine della demo per ora)");
        giocoIniziato = false; 
    }
}
