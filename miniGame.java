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
        }

        public void aggiungiOggetto(String oggetto) {
            if (numeroOggetti < MAX_OGGETTI) {
                inventario[numeroOggetti] = oggetto;
                numeroOggetti++;
                System.out.println(">> Hai ottenuto: " + oggetto);
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
            System.out.println("Classe: " + classeNome);
            System.out.println("Livello: " + livello);
            System.out.println("Vite: " + vite + " | Forza: " + forza);
            System.out.println("*******************");
        }
    }

    static Scanner input = new Scanner(System.in);
    static Personaggio player;
    static Personaggio Mikasa;
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
                    System.out.println("Uccidi il Re dei demoni con l'aiuto degli aspiranti eroi che incontreai nella storia!");
                    break;
                case 3:
                    if (giocoIniziato) {
                        player.mostraInventario();
                    } else {
                        System.out.println("Uscita dal gioco...");
                        esegui = false;
                    }
                    break;
                case 4:
                    if (giocoIniziato) {
                        System.out.println("Uscita dal gioco...");
                        esegui = false;
                    } else {
                        System.out.println("Opzione non valida.");
                    }
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

        switch (scelta) {
            case 1:
                player.classeNome = "Mago";
                player.forza = 10;
                player.vite = 5;
                player.aggiungiOggetto("Bastone Magico");
                break;
            case 2:
                player.classeNome = "Assassino";
                player.forza = 15;
                player.vite = 10;
                player.aggiungiOggetto("Pugnale");
                break;
            case 3:
                player.classeNome = "Monaco";
                player.forza = 8;
                player.vite = 7;
                player.aggiungiOggetto("Bastone");
                break;
            case 4:
                player.classeNome = "Healer";
                player.forza = 5;
                player.vite = 8;
                player.aggiungiOggetto("Pozione");
                break;
            default:
                System.out.println("Classe non valida, diventi un Monaco.");
                player.classeNome = "Monaco";
                player.forza = 8;
                player.vite = 7;
                player.aggiungiOggetto("Bastone");
        }
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
            player.livello += 2;
            
            System.out.println("Uno studente ti sfida a duello.");
            System.out.println("1) Accetti");
            System.out.println("2) Rifiuti");
            System.out.print("-> ");
            
            int duello = leggiIntero();
            
            if (duello == 1) {
                System.out.println("Vinci il duello! Guadagni esperienza.");
                player.livello++;
                incontraYui();
            } else {
                System.out.println("Rifiuti e vieni deriso.");
            }
        } else {
            System.out.println("Studi a casa. Impari molto ma non ti fai amici.");
        }
    }

    static void incontraYui() {
        System.out.println("\nUna ragazza di nome Yui si avvicina.");
        System.out.println("<<Hey " + player.nome + ", vuoi mangiare con noi?>>");
        System.out.println("1) Accetta");
        System.out.println("2) Rifiuta");
        System.out.print("-> ");
        
        int risp = leggiIntero();
        if(risp == 1) {
            System.out.println("Accetti. Yui ti sorride un pò imbarazzata e ti accompagna dalle sue amiche.\n");
            incontro();
        } else {
            System.out.println("Te ne vai da solo.");
        }
    }
        static void Mikasa() {
            Mikasa.forza = 8;
            Mikasa.vite = 7;
            Mikasa.aggiungiOggetto("Arco");

        }
        static void incontro() {
            System.out.println("Mikasa: Hey" + player.nome + ", mi chiamo Mikasa! Fujino: Invece io mi chiamo Fujino, piacere di consocerti!\n");
            System.out.println("Mikasa: Volevamo chiederti se ti possiamo accompagnare nel viaggio contro il re dei demoni, siamo delle comagne forti!");
            Mikasa.Mikasa();

        }
}
