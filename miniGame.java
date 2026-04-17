import java.util.Random;
import java.util.Scanner;

public class miniGame {

    // ========== SISTEMA MAPPA ==========
    static int RIGHE = 10;
    static int COLONNE = 10;
    static int playerY = 1, playerX = 1; 
    static char[][] mappa = new char[RIGHE][COLONNE];
    static int NUM_NEMICI = 10;
    static int NUM_OGGETTI = 6;
    static Random rand = new Random();
    // ===================================

    static class Personaggio {
        String nome;
        String classeNome;
        int forza;
        int vite;
        int livello;
        int fortuna; 
        
        String[] inventario;
        int numeroOggetti;
        int MAX_OGGETTI = 10;

        public Personaggio(String nome) {
            this.nome = nome;
            this.livello = 1;
            this.fortuna = 10; 
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
            System.out.println("Vite: " + vite + " | Forza: " + forza + " | Fortuna: " + fortuna + "%");
            System.out.println("*******************");
        }
    }

    static Scanner input = new Scanner(System.in);
    static Personaggio player;
    static Personaggio mikasa;
    static Personaggio fujino;
    static boolean giocoIniziato = false;
    static boolean inEsplorazioneMappa = false;

    public static void main(String[] args) {
        System.out.print("Nome personaggio: ");
        String nomeTemp = input.nextLine();
        player = new Personaggio(nomeTemp);

        System.out.println("***************************");
        System.out.println("Benvenuto " + player.nome);
        System.out.println("***************************");

        boolean esegui = true;
        while (esegui) {
            if (inEsplorazioneMappa) {
                mostraMenuMappa();
                gestisciInputMappa();
            } else {
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
                            if (mikasa != null) mikasa.mostraInventario();
                            if (fujino != null) fujino.mostraInventario();
                        } else {
                            System.out.println("Inizia una partita per vedere l'inventario!");
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
        }
        input.close();
    }

    // ========== MENU E INPUT ==========
    static void mostra_menu() {
        System.out.println("\n--- MENU PRINCIPALE ---");
        if (!giocoIniziato) {
            System.out.println("1) Inizia il gioco");
            System.out.println("2) Trama");
            System.out.println("3) Esci");
        } else {
            System.out.println("1) Continua avventura (Storia)");
            System.out.println("2) Trama");
            System.out.println("3) Inventario");
            System.out.println("4) Esci");
        }
        System.out.print("Scelta: ");
    }

    static void mostraMenuMappa() {
        System.out.println("\n--- 🗺️ ESPLORAZIONE MAPPA ---");
        stampaMappa();
        System.out.println("\nComandi: W=Su, S=Giù, A=Sinistra, D=Destra");
        System.out.println("         I=Inventario, E=Esci dalla mappa");
        System.out.print("-> ");
    }

    static int leggiIntero() {
        try {
            return Integer.parseInt(input.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    // ========== SISTEMA MAPPA ==========
    static void inizializzaMappa() {
        for (int i = 0; i < RIGHE; i++) {
            for (int j = 0; j < COLONNE; j++) {
                if (i == 0 || i == RIGHE - 1 || j == 0 || j == COLONNE - 1) {
                    mappa[i][j] = '#';
                } else {
                    mappa[i][j] = '.';
                }
            }
        }
        playerY = 1; 
        playerX = 1;
        mappa[playerY][playerX] = 'P';
    }

    static void posizionaElementi(int quantita, char simbolo) {
        int i = 0;
        while (i < quantita) {
            int x = 1 + rand.nextInt(COLONNE - 2);
            int y = 1 + rand.nextInt(RIGHE - 2);
            if (mappa[y][x] == '.' && !(y == playerY && x == playerX)) {
                mappa[y][x] = simbolo;
                i++;
            }
        }
    }

    static void stampaMappa() {
        System.out.print("   ");
        for (int j = 0; j < COLONNE; j++) System.out.printf("%2d ", j);
        System.out.println("\n   " + "-".repeat(COLONNE * 3));
        
        for (int i = 0; i < RIGHE; i++) {
            System.out.printf("%2d | ", i);
            for (int j = 0; j < COLONNE; j++) {
                System.out.print(mappa[i][j] + "  ");
            }
            System.out.println();
        }
        System.out.println("   " + "-".repeat(COLONNE * 3));
        System.out.println("Legenda: #=Muro, P=Tu, N=Nemico, O=Oggetto, .=Vuoto");
        System.out.println("Posizione attuale: (" + playerY + ", " + playerX + ")");
    }

    static void gestisciInputMappa() {
        String comando = input.nextLine().trim().toUpperCase();
        int newY = playerY, newX = playerX;
        
        switch (comando) {
            case "W": case "8": newY--; break;
            case "S": case "2": newY++; break;
            case "A": case "4": newX--; break;
            case "D": case "6": newX++; break;
            case "I": 
                player.mostraInventario(); 
                return;
            case "E": case "M": case "Q":
                System.out.println(">> Torni al menu principale.");
                inEsplorazioneMappa = false;
                return;
            default:
                System.out.println("Comando non valido! Usa WASD, I o E.");
                return;
        }
        
        char cellaDestinazione = mappa[newY][newX];

        if (cellaDestinazione == '#') {
            System.out.println(">> 🧱 C'è un muro! Non puoi passare.");
            return;
        }
        
        // Risolto il Bug di sovrascrittura mappa: controllo preventivo della cella futura
        if (cellaDestinazione == 'N') {
            System.out.println("\n⚔️ Un nemico ti attacca!");
            boolean vittoria = combattiNemicoMappa();
            if (!vittoria) {
                return; // Se fuggi o muori, non ti muovi nella casella del nemico
            }
        } else if (cellaDestinazione == 'O') {
            System.out.println("\n✨ Hai trovato un oggetto!");
            raccogliOggettoMappa();
        }
        
        // Se arrivi qui, il movimento è autorizzato (casella vuota, oggetto raccolto o nemico ucciso)
        mappa[playerY][playerX] = '.';
        playerY = newY;
        playerX = newX;
        mappa[playerY][playerX] = 'P';
    }

    // Modificato per restituire un boolean (true se vinci, false se fuggi/muori)
    static boolean combattiNemicoMappa() {
        Personaggio nemico = generaNemico();
        nemico.nome = "Nemico Mappa";
        
        System.out.println("Affronti un " + nemico.nome + " (Liv." + nemico.livello + ")!");
        
        while (player.vite > 0 && nemico.vite > 0) {
            System.out.println("\nTu: " + player.vite + " HP | Nemico: " + nemico.vite + " HP");
            System.out.println("1) Attacca  2) Scappa");
            int scelta = leggiIntero();
            
            if (scelta == 1) {
                int danno = player.forza + (rand.nextInt(100) < player.fortuna ? player.forza : 0);
                nemico.vite -= danno;
                System.out.println("Infliggi " + danno + " danni!");
                
                if (nemico.vite <= 0) {
                    System.out.println("🏆 Hai sconfitto il nemico! +2 Livello, +3 Forza");
                    player.livello += 2;
                    player.forza += 3;
                    return true;
                }
                
                player.vite -= nemico.forza;
                System.out.println("Il nemico ti infligge " + nemico.forza + " danni!");
                
                if (player.vite <= 0) {
                    System.out.println("💀 SEI MORTO! Game Over.");
                    giocoIniziato = false;
                    inEsplorazioneMappa = false;
                    return false;
                }
            } else {
                System.out.println("Sei fuggito!");
                return false;
            }
        }
        return false;
    }

    static void raccogliOggettoMappa() {
        String[] oggettiPossibili = {"Pozione", "Erba Curativa", "Pergamena", "Cristallo", "Chiave"};
        String oggettoTrovato = oggettiPossibili[rand.nextInt(oggettiPossibili.length)];
        
        player.aggiungiOggetto(oggettoTrovato);
        
        if (oggettoTrovato.equals("Pozione") || oggettoTrovato.equals("Erba Curativa")) {
            player.vite += 3;
            System.out.println(">> Hai recuperato 3 vite!");
        } else if (oggettoTrovato.equals("Pergamena")) {
            player.livello++;
            System.out.println(">> Hai guadagnato esperienza! Livello +1");
        }
    }
    // ===================================

    static void gestisciAvventura() {
        if (!giocoIniziato) {
            scegliClasse();
            inizializzaMappa();
            posizionaElementi(NUM_NEMICI, 'N');
            posizionaElementi(NUM_OGGETTI, 'O');
            giocoIniziato = true;
        }
        
        if (player.classeNome.equals("Mago")) {
            storiaMago();
        } else {
            System.out.println("\nLa storia per " + player.classeNome + " è ancora in sviluppo...");
            System.out.println("Nel frattempo, esplora la mappa per allenarti! 🗡️");
            inEsplorazioneMappa = true;
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
                player.forza = 10; player.vite = 5; player.fortuna = 25; 
                player.aggiungiOggetto("Bastone Magico");
                break;
            case 2:
                player.classeNome = "Assassino";
                player.forza = 15; player.vite = 4; player.fortuna = 40;
                player.aggiungiOggetto("Pugnale");
                break;
            case 3:
                player.classeNome = "Monaco";
                player.forza = 12; player.vite = 8; player.fortuna = 20;
                player.aggiungiOggetto("Bastone da Monaco");
                break;
            case 4:
                player.classeNome = "Healer";
                player.forza = 8; player.vite = 6; player.fortuna = 35;
                player.aggiungiOggetto("Bacchetta Curativa");
                break;
            default:
                System.out.println("Scelta non valida, impostato come Mago.");
                player.classeNome = "Mago"; 
                player.forza = 10; player.vite = 5; player.fortuna = 25; 
                player.aggiungiOggetto("Bastone Magico");
        }
        player.mostraStatistiche();
    }

    // ========== STORIA ORIGINALE ==========
    static void storiaMago() {
        System.out.println("\n--- CAPITOLO 1: L'ACCADEMIA ---");
        System.out.println("Ti svegli come figlio del Re.");
        System.out.println("1) Vai all'accademia");
        System.out.println("2) Studia a casa");
        System.out.print("-> ");

        int azione = leggiIntero();

        if (azione == 1) {
            System.out.println("\nVai all'accademia e sali di livello!");
            player.livello += 2; player.forza += 3; player.vite += 2; player.fortuna += 5;
            player.mostraStatistiche();
            
            System.out.println("Uno studente ti sfida a duello. 1) Accetti 2) Rifiuti");
            if (leggiIntero() == 1) {
                System.out.println("Vinci il duello! Guadagni esperienza.");
                player.livello++;
                incontraYui();
            } else {
                System.out.println("Rifiuti e vieni deriso.");
                rifarsi(); 
                studia();
                incontraYui();
            }
        } else {
            System.out.println("Studi a casa.");
            incontraYui();
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

    static void creaMikasa() {
        mikasa = new Personaggio("Mikasa"); 
        mikasa.classeNome = "Cacciatrice";
        mikasa.forza = 8; mikasa.vite = 7; mikasa.fortuna = 30; 
        mikasa.aggiungiOggetto("Arco");
    }

    static void creaFujino() {
        fujino = new Personaggio("Fujino"); 
        fujino.classeNome = "Healer";
        fujino.forza = 8; fujino.vite = 7; fujino.fortuna = 15; 
        fujino.aggiungiOggetto("Incantesimi");
    }

    static void incontro() {
        System.out.println("Mikasa e Fujino vogliono unirsi a te.");
        creaMikasa(); 
        creaFujino();
        
        mikasa.livello += 5; fujino.livello += 4;
        mikasa.forza += 15; fujino.forza += 13;
        mikasa.vite += 10; fujino.vite += 10;
        mikasa.mostraStatistiche();
        mikasa.aggiungiOggetto("Armatura");
        fujino.aggiungiOggetto("Guanti magici");

        sceltaCompagni(); 
    } 

    static void sceltaCompagni() {
        System.out.println("1) Accetta");
        System.out.println("2) Rifiuta");
        System.out.print("-> ");
        
        int risp = leggiIntero();
        if(risp == 1) {
            System.out.println("Accetti. Viaggi verso il villaggio.\n");
        } else {
            System.out.println("Rifiuti e vai da solo.\n");
        }
        villaggio();
    }

    static void villaggio() {
        System.out.println("Arrivi al villaggio e ottieni un Mantello!\n");
        player.aggiungiOggetto("Mantello");
        player.livello += 2; player.fortuna += 5;
        player.mostraStatistiche();
        viaggio();
    }

    static void viaggio() {
        System.out.println("\n--- CAPITOLO 2: IL VIAGGIO ---");
        System.out.println("Inizi il tuo viaggio.");
        if (mikasa != null && fujino != null) {
            System.out.println("In compagnia di Mikasa e Fujino.");
        }
        System.out.println("Incontri un gruppo di mostri. 1) Accetti 2) Rifiuti");
        System.out.print("-> ");

        int risp = leggiIntero(); 
        if(risp == 1) {
            System.out.println("Accetti. Vinci facile!\n");
            grotta();
        } else {
            System.out.println("Vi nascondete ma venite colpiti!");
            if (mikasa != null) mikasa.vite -= 3;
            if (fujino != null) fujino.vite -= 3;
            player.vite -= 3;
            System.out.println("Vite: P=" + player.vite + 
                               (mikasa != null ? " M=" + mikasa.vite : "") + 
                               (fujino != null ? " F=" + fujino.vite : ""));
            grotta();
        }
    } 

    public static void grotta() {
        System.out.println("\n--- LA GROTTA ---");
        System.out.println("Trovate una grotta, mangiate e incontrate Takumi.");
        System.out.println("Takumi ti offre una spada in Mithril. 1) Accetti 2) Rifiuti");
        System.out.print("-> ");

        if(leggiIntero() == 1) {
            System.out.println("Accetti. Ottieni la Spada in Mithril!\n");
            player.aggiungiOggetto("Spada in Mithril");
        } else {
            System.out.println("Rifiuti cordialmente.");
        }
        continua();
    }

    public static Personaggio generaNemico() {
        int tipo = rand.nextInt(3);
        Personaggio nemico = new Personaggio("Mostro");
        nemico.classeNome = "Mostro";
        nemico.livello = player.livello;

        switch (tipo) {
            case 0:
                nemico.nome = "Slime";
                nemico.forza = 2 + nemico.livello;
                nemico.vite = 3 + nemico.livello;
                nemico.aggiungiOggetto("Gelatina appiccicosa");
                break;
            case 1:
                nemico.nome = "Goblin";
                nemico.forza = 4 + nemico.livello;
                nemico.vite = 4 + nemico.livello;
                nemico.aggiungiOggetto("Pugnale arrugginito");
                break;
            case 2:
                nemico.nome = "Lupo Randagio";
                nemico.forza = 6 + nemico.livello;
                nemico.vite = 3 + nemico.livello;
                nemico.aggiungiOggetto("Zanna di lupo");
                break;
        }
        return nemico;
    }

    public static void continua() {
        System.out.println("\nLa tua vera battaglia inizia! Incontri un mostro...");
        Personaggio nemico = generaNemico();
        System.out.println("È apparso un " + nemico.nome + "!");
        nemico.mostraStatistiche();

        System.out.println("1) Combatti  2) Scappa");
        if(leggiIntero() == 1) {
            boolean haSpada = false;
            for(String item : player.inventario) if(item != null && item.contains("Spada")) haSpada = true;

            while (player.vite > 0 && nemico.vite > 0) {
                System.out.println("\nTu: " + player.vite + " HP | Nemico: " + nemico.vite + " HP");
                System.out.println("1) Mani nude  " + (haSpada ? "2) Spada (+5 dmg)  " : "") + "3) Scappa");
                int scelta = leggiIntero();
                int danno = player.forza;

                if (scelta == 2 && haSpada) {
                    System.out.println("⚔️ Attacco con la Spada in Mithril!");
                    danno += 5;
                } else if (scelta == 3) {
                    System.out.println("Sei fuggito!");
                    gilda();
                    return;
                } else {
                    System.out.println("👊 Attacco a mani nude!");
                }

                if (rand.nextInt(100) < player.fortuna) {
                    System.out.println("💥 COLPO CRITICO!");
                    danno *= 2;
                }
                nemico.vite -= danno;
                System.out.println("Infliggi " + danno + " danni!");

                if (nemico.vite <= 0) {
                    System.out.println("🏆 VITTORIA! Hai sconfitto il " + nemico.nome);
                    if(nemico.numeroOggetti > 0) player.aggiungiOggetto(nemico.inventario[0]);
                    break;
                }

                player.vite -= nemico.forza;
                System.out.println("Il nemico ti infligge " + nemico.forza + " danni!");
                if (player.vite <= 0) {
                    System.out.println("💀 GAME OVER");
                    giocoIniziato = false;
                    return;
                }
            }
        } else {
            System.out.println("Sei scappato come un codardo!");
        }
        if (player.vite > 0) gilda();
    }

    static void gilda() {
        System.out.println("\nArrivi alla Gilda. Nuova missione disponibile!");
        System.out.println("1) Accetta  2) Rifiuta");
        if(leggiIntero() == 1) {
            System.out.println("Accetti! Torni all'esplorazione...");
            inEsplorazioneMappa = true;
        } else {
            System.out.println("Rifiuti. Riposi un po'.");
            System.out.println("\nRitorno al menu...");
            giocoIniziato = false;
        }
    }
}
