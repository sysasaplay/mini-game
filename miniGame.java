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
                        if (Mikasa != null) { 
                            Mikasa.mostraInventario();
                        }
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
            player.forza += 3;
            player.vite += 2;
            player.mostraStatistiche();
            
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
                rifarsi();
                studia();
            }
        } else {
            System.out.println("Studi a casa. Impari molto ma non ti fai amici.\n");
        }
    }

    static void rifarsi() {
        System.out.println("\nRifatti. Ti metti a studiare e ti diventa un mago molto forte.\n");
        player.livello += 5;
        player.forza += 10;
        player.vite += 10;
    }

    static void studia() {
        System.out.println("Studia. Ti metti a studiare e ti diventa un mago molto forte.\n");
        player.livello += 7;
        player.forza += 10;
        player.vite += 10;
        System.out.println("Congratulazioni! Sei salito di livello " + player.livello + "!\n");

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
        Mikasa = new Personaggio("Mikasa"); 
        Mikasa.classeNome = "Cacciatrice";
        Mikasa.forza = 8;
        Mikasa.vite = 7;
        Mikasa.aggiungiOggetto("Arco");
    }
    static void Fujino() {
        Fujino = new Personaggio("Fujino"); 
        Fujino.classeNome = "Healer";
        Fujino.forza = 8;
        Fujino.vite = 7;
        Fujino.aggiungiOggetto("incantesimi di cura, ghiaccio e fuoco");
        Fujino.mostraStatistiche();
    }

    static void incontro() {
        System.out.println("Mikasa: Hey " + player.nome + ", mi chiamo Mikasa! Sono una ragazza semiumana.");
        System.out.println("Fujino: Invece io mi chiamo Fujino, piacere di consocerti!\n");
        System.out.println("Mikasa: Volevamo chiederti se ti possiamo accompagnare nel viaggio contro il re dei demoni, siamo delle compagne forti!\n");
        Mikasa(); 
        Fujino();
        
        Mikasa.livello += 5;
        Fujino.livello += 4;
        Mikasa.forza += 15;
        Fujino.forza += 13;
        Mikasa.vite += 10;
        Fujino.vite += 10;
        Mikasa.mostraStatistiche();
        Mikasa.aggiungiOggetto("armatura");
        Fujino.aggiungiOggetto("Lancia incantesimi con le mani\n");

        scelta(); 
    } 

    static void scelta() {
        System.out.println("1) Accetta");
        System.out.println("2) Rifiuta");
        System.out.print("-> ");
        
        int risp = leggiIntero();
        if(risp == 1) {
            System.out.println("Accetti. Ti accompagna al viaggio natio e ti fa conoscere il capovillaggio.\n");
            villaggio();
        } else {
            System.out.println("Rifiuti. Ti lasci andare e vai da solo e ti rechi in un villaggio poco distante da lì.\n");
        }
    }

    static void villaggio() {
        System.out.println("Congratulazioni! Hai aggiunto Mantello al tuo inventario!\n");
        player.aggiungiOggetto("Mantello");
        player.livello += 2;
        player.forza += 3;
        player.vite += 2;
        player.mostraStatistiche();
    }
    static void viaggio() {
        System.out.println("Inizi il tio viaggio. Ti accompagna Mikasa e Fujino nel viaggio contro il re dei demoni.\n");
        System.out.println("Incontri un gruppo di mostri. Ti chiedono se vuoi combatterli.\n");
        System.out.println("1) accetti");
        System.out.println("2) rifiuti");
        System.out.println("->");

        scelta();

        if(risp == 1) {
            System.out.println("Accetti. Ti imbatti in dei mostri poco pericolosi e ve la cavate.\n");
            grotta();
        } else {
            System.out.println("Vi nascondete in un cespuglio e venite colpiti da dei mostri tutti e 3 togliendovi ");
            Mikasa.vite - 3;
            Fujino.vite - 3;
            player.vite -3;
            grotta();
        }

    public static void grotta() {
        System.out.println("Trovate una grotta dove riposare prima di riprendere, Accendi un falò per mangiare qualcosa con le ragazze\n");
        System.out.println("Reiniziate il vostro viaggio e vi imbattete in un'altro villaggio, dove vivono i nani, parli con Takumi");
        System.out.println("Takumi ti porta al suo negozio e ti regala una spada");
        System.out.println("1) accetti");
        System.out.println("2) rifiuti");


        scelta();

        if(risp == 1) {
            System.out.println("Accetti. Ti dà questa spada e scopri che è fatta di Mithril, utile per sconfiggere il re dei demoni.\n");
            player.aggiungiOggetto("Spada in Mithril");
            continua();
        } else {
            System.out.println("Rifiuti in modo codiale l'offerta ma scopri dopo che avresti fatto grandi progressi con quella spada");
            continua();
        }
    }

    public static void continua() {

    }
}
