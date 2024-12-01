package game;

import game.MyDroids.Droid;
import game.Battle.OneToOne;
import game.Battle.TeamBattle;

import java.io.IOException;
import java.util.Scanner;
import java.io.File;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Main {
    private static Droid[] droids = new Droid[6];
    private static int droidCount = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;
        String FilePath;
        System.out.println("\n--------------------~WELCOME TO THE~-----------------------");
        printLogo();
        do {
            System.out.println("\n1. View game characters");
            System.out.println("2. Choose heroes");
            System.out.println("3. Show selected heroes list");
            System.out.println("4. One-on-one battle");
            System.out.println("5. Team battle");
            System.out.println("6. Replay battle from file");
            System.out.println("7. Exit");
            do {
                System.out.print("Enter a number: ");
                choice = scanner.nextInt();

            } while (choice <= 0 || choice > 7);

            switch (choice) {
                case 1: // Передивитися героїв гри
                    openImage();
                    break;
                case 2: {
                    int number;
                    do {
                        System.out.print("Enter the number of heroes to choose: ");
                        number = scanner.nextInt();
                    } while (number <= 0 || number > 6);

                    for (int i = 0; i < number; i++) {
                        if (droidCount < droids.length) {
                            droids[droidCount] = Droid.selectDroid();
                            droidCount++;
                        } else {
                            System.out.println("Maximum number of heroes reached");
                            break;
                        }
                    }
                    break;
                }
                case 3: { // Показати список обраних героїв
                    if (droidCount == 0) {
                        System.out.println("No selected heroes");
                    } else {
                        System.out.println("Selected heroes:");
                        for (int i = 0; i < droidCount; i++) {
                            System.out.println((i + 1) + ". " + droids[i]);
                        }
                    }
                    break;
                }
                case 4: { // Бій 1 на 1
                    if (droidCount < 2) {
                        System.out.println("Two heroes are needed");
                    } else {
                        System.out.println("Select heroes for the one-on-one battle from the created list.\n");

                        for (int i = 0; i < droidCount; i++) {
                            System.out.println((i + 1) + ". " + droids[i]);
                        }

                        System.out.println("\nEnter the number of the first hero (it will attack first): ");
                        int droid1Numb = scanner.nextInt();
                        droid1Numb -= 1;
                        System.out.println("Enter the number of the second hero: ");
                        int droid2Numb = scanner.nextInt();
                        droid2Numb -= 1;

                        if (droid1Numb >= 0 && droid1Numb < droidCount &&
                                droid2Numb >= 0 && droid2Numb < droidCount && droid1Numb != droid2Numb) {

                            Droid droid1 = droids[droid1Numb];
                            Droid droid2 = droids[droid2Numb];

                            OneToOne battle = new OneToOne(droid1, droid2);
                            battle.startBattle(); // Запускаємо бій
                            System.out.println("END OF THE BATTLE");
                        } else {
                            System.out.println("Invalid selection, try again");
                        }
                    }
                    break;
                }
                case 5: { // Командний бій
                    if (droidCount < 4) {
                        System.out.println("Four or more heroes are needed for a team battle");
                    } else {
                        int number = 0;
                        do {
                            System.out.println("Enter the number of heroes for the team battle:");
                            number = scanner.nextInt();
                        } while (number != 4 && number != 6 || (number == 6 && droidCount < 6));

                        Droid[] team1 = new Droid[number / 2];
                        Droid[] team2 = new Droid[number / 2];

                        System.out.println("Select heroes for Team Iron Man:");
                        for (int i = 0; i < droidCount; i++) {
                            System.out.println((i + 1) + ") " + droids[i]);
                        }

                        for (int i = 0; i < team1.length; i++) {
                            System.out.println("\nEnter the number of the hero for Team Iron Man:");
                            int droidIndex = scanner.nextInt();
                            team1[i] = droids[droidIndex - 1];
                        }

                        System.out.println("\nSelect droids for Team Cap:");
                        for (int i = 0; i < team2.length; i++) {
                            System.out.println("Enter the number of the hero for Team Cap:");
                            int droidIndex = scanner.nextInt();
                            team2[i] = droids[droidIndex - 1];
                        }

                        // Відображення обраних команд
                        System.out.println("\nTeam Iron Man:");
                        for (int i = 0; i < team1.length; i++) {
                            System.out.println((i + 1) + ") " + team1[i]);
                        }

                        System.out.println("\nTeam Cap:");
                        for (int i = 0; i < team2.length; i++) {
                            System.out.println((i + 1) + ") " + team2[i]);
                        }

                        TeamBattle teamBattle = new TeamBattle(team1, team2);
                        teamBattle.startTeamBattle(); // Запускаємо бій
                        System.out.println("END OF THE TEAM BATTLE");
                    }
                    break;
                }
                case 6: { // Відтворення бою з файлу
                    System.out.print("Enter the file path: ");
                    FilePath = scanner.next();

                    try {
                        File file = new File(FilePath);
                        Scanner fileScanner = new Scanner(file);

                        while (fileScanner.hasNextLine()) {
                            String line = fileScanner.nextLine();
                            System.out.println(line);
                        }

                        fileScanner.close();
                    } catch (IOException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                }
                case 7:
                    System.out.println("END");
                    return ;
                default: {
                    System.out.println("Invalid choice, try again");
                }
            }
        } while (choice != 7);
        scanner.close();
    }

    private static void printLogo() {
        System.out.println("  ______     _                    ______ _       _     _    ");
        System.out.println(" |  ____|   | |                  |  ____(_)     | |   | |   ");
        System.out.println(" | |__ _   _| |_ _   _ _ __ ___  | |__   _  __ _| |__ | |_  ");
        System.out.println(" |  __| | | | __| | | | '__/ _ \\ |  __| | |/ _` | '_ \\| __| ");
        System.out.println(" | |  | |_| | |_| |_| | | |  __/ | |    | | (_| | | | | |_  ");
        System.out.println(" |_|   \\__,_|\\__|\\__,_|_|  \\___| |_|    |_|\\__, |_| |_|\\__| ");
        System.out.println("                                           __/ |           ");
        System.out.println("                                          |___/            ");
    }

    private static void openImage() {

        String imagePath = "C:\\Java_AN\\marvelteams.jpg";

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Створюємо панель для відображення зображення
        JPanel imagePanel = new JPanel() {
            private BufferedImage image;

            {
                try {
                    image = ImageIO.read(new File(imagePath));
                } catch (IOException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (image != null) {
                    int width = getWidth();
                    int height = getHeight();
                    g.drawImage(image, 0, 0, width, height, null);
                }
            }
        };
        frame.add(imagePanel);
        frame.setVisible(true);
    }
}
