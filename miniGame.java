import java.util.Scanner;

public class miniGame {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        String nome;
        int classe = 0;
        int forza = 1;
        int vite = 10;
        int livello = 0;
        int next_level = 100;
        int scelta;
        boolean giocoIniziato = false;

        int INVENTARIO = 10;
        String[] inventario = new String[INVENTARIO];
        int numOggetti = 0;

        String scelta1 = "Inizia il gioco";
        String scelta2 = "Trama";
        String scelta3 = "Esci";

        System.out.print("nome personaggio: ");
        nome = input.nextLine();

        System.out.println("***************************");
        System.out.println("Benvenuto " + nome);
        System.out.println("***************************");

        do {
            
            if (!giocoIniziato) {
                System.out.println("\n1) " + scelta1);
                System.out.println("2) " + scelta2);
                System.out.println("3) " + scelta3);
                System.out.print("Inserisci la tua scelta: ");
                try {
                    scelta = Integer.parseInt(input.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Input non valido, riprova.");
                    scelta = 0;
                }
            } else {
                System.out.println("\n1) Continua avventura");
                System.out.println("2) " + scelta2);
                System.out.println("3) Inventario");
                System.out.println("4) " + scelta3);
                System.out.print("Inserisci la tua scelta: ");
                try {
                    scelta = Integer.parseInt(input.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Input non valido, riprova.");
                    scelta = 0;
                }
            }

            switch (scelta) {

                case 1:
                    // --- INIZIO GIOCO O CONTINUAZIONE ---
                    if (!giocoIniziato) {
                        System.out.println("\n-- L'AVVENTURA DI " + nome.toUpperCase() + " HA INIZIO ---");
                        System.out.println("Scegli la classe del tuo personaggio: ");
                        System.out.println("1) Mago");
                        System.out.println("2) Assassino");
                        System.out.println("3) Monaco");
                        System.out.println("4) Healer");
                        System.out.println("5) Info");
                        System.out.print("Inserisci la tua scelta: ");
                        try {
                            classe = Integer.parseInt(input.nextLine());
                        } catch (NumberFormatException e) {
                            System.out.println("Input non valido, seleziono Monaco.");
                            classe = 3;
                        }

                        switch (classe) {
                            case 1:
                                System.out.println("\nHai scelto il Mago");
                                forza = 10;
                                if (numOggetti < INVENTARIO) { 
                                    inventario[numOggetti] = "Bacchetta"; numOggetti++; }
                                break;
                            case 2:
                                System.out.println("\nHai scelto l'Assassino");
                                forza = 15;
                                if (numOggetti < INVENTARIO) { inventario[numOggetti] = "Pugnale"; numOggetti++; }
                                break;
                            case 3:
                                System.out.println("\nHai scelto il Monaco");
                                forza = 8;
                                if (numOggetti < INVENTARIO) { inventario[numOggetti] = "Bastone"; numOggetti++; }
                                break;
                            case 4:
                                System.out.println("\nHai scelto l'Healer");
                                forza = 5;
                                if (numOggetti < INVENTARIO) { inventario[numOggetti] = "Pozione"; numOggetti++; }
                                break;
                            case 5:
                                System.out.println("\nINFO: Mago (Attacco), Assassino (Furtività)...");
                                continue; 
                            default:
                                System.out.println("Scelta non valida, seleziono Monaco.");
                                classe = 3;
                                forza = 8;
                                if (numOggetti < INVENTARIO) { inventario[numOggetti] = "Bastone"; numOggetti++; }
                                break;
                        }
                        giocoIniziato = true;
                    }
                    
                    // STORIA SPECIFICA PER IL MAGO
                    if (classe == 1) { 
                        System.out.println("\n--- STORIA MAGO: IL PRODIGIO ---");
                        System.out.println("Ti svegli come figlio del Re. Inizi la tua vita a 7 anni.");
                        System.out.println("1) Vai nell'accademia di famiglia");
                        System.out.println("2) Studia nella biblioteca di casa");
                        System.out.print("Scelta: ");
                        
                        int azioneMago; 
                        try {
                            azioneMago = Integer.parseInt(input.nextLine());
                        } catch (NumberFormatException e) {
                            azioneMago = 2;
                        }

                        if (azioneMago == 1) {
                            System.out.println("Congratulazioni, sei salito di 2 livelli!");
                            livello += 2;
                            System.out.println("Uno studente ti sfida a duello.");
                            System.out.println("1) Accetti");
                            System.out.println("2) Rifiuti");
                            System.out.print("Scelta: ");
                            int duello;
                            try {
                                duello = Integer.parseInt(input.nextLine());
                            } catch (NumberFormatException e) {
                                duello = 2;
                            }
                            if (duello == 1) {
                                System.out.println("Accetti. Vinci il duello! Livello attuale: " + (livello + 1) + ".");
                                livello++;
                            } else {
                                System.out.println("Rifiuti. Ti allontani senza combattere.");
                            }
                        } else {
                            System.out.println("Studi in biblioteca. La tua conoscenza aumenta.");
                        }
                    }
                    if (classe == 2) {
                        System.out.println("\n--- STORIA ASSASSINO ---");
                        System.out.println("L'ombra ti chiama. La storia continua...");
                    }
                    break;

                case 2:
                    System.out.println("\n-- TRAMA --");
                    System.out.println("Devi recuperare i frammenti...");
                    break;

                case 3:
                    if (giocoIniziato) {
                        System.out.println("\n-- INVENTARIO --");
                        if (numOggetti == 0) {
                            System.out.println("(vuoto)");
                        } else {
                            for (int i = 0; i < numOggetti; i++) {
                                System.out.println((i + 1) + ") " + inventario[i]);
                            }
                        }
                    } else {
                        System.out.println("Uscita...");
                    }
                    break;

                case 4:
                    if (giocoIniziato) {
                        System.out.println("Uscita...");
                    } else {
                        System.out.println("Scelta non valida.");
                    }
                    break;

                default:
                    System.out.println("Scelta non valida.");
            }

        } while ( (!giocoIniziato && scelta != 3) || (giocoIniziato && scelta != 4) );

        input.close();
    }
}
