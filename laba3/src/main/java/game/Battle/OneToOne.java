package game.Battle;

import game.MyDroids.Droid;
import java.io.FileWriter;
import java.io.IOException;

public class OneToOne {
    private Droid droid1;
    private Droid droid2;
    private String filePath = "C:\\Java_AN\\OTOBattle.txt";

    public OneToOne(Droid d1, Droid d2) {
        this.droid1 = d1;
        this.droid2 = d2;
    }

    // Метод для запису в файл
    private void print(String str) {
        try (FileWriter writer = new FileWriter(filePath, true)) {
            writer.write(str + System.lineSeparator()); // Записуємо рядок з новим рядком
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Метод для запуску бою
    public void startBattle() {
        System.out.println("\n" + droid1.getName() + " VS " + droid2.getName() + " \n");
        print(" " + droid1.getName() + " VS " + droid2.getName() + " "); // Запис у файл

        while (droid1.isAlive() && droid2.isAlive()) {
            // Атака першого героя
            attack(droid1, droid2);
            if (!droid2.isAlive()) {
                String defeatMessage = droid2.getName() + " has been defeated \n";
                System.out.println(defeatMessage);
                print(defeatMessage);
                break;
            }

            // Атака другого героя
            attack(droid2, droid1);
            if (!droid1.isAlive()) {
                String defeatMessage = droid1.getName() + " has been defeated \n";
                System.out.println(defeatMessage);
                print(defeatMessage);
                break;
            }
        }

        // Оголошення переможця
        String winner = droid1.isAlive() ? droid1.getName() : droid2.getName();
        String winnerMessage = winner + " WON\n";
        System.out.println(winnerMessage);
        print(winnerMessage);
    }

    // Метод для атаки одного дроїда на іншого
    private void attack(Droid attacker, Droid defender) {
        String attackMessage = attacker.getName() + " attacks " + defender.getName() + " with damage LVL " + attacker.getDamage();
        System.out.println(attackMessage);
        print(attackMessage);

        defender.takeDamage(attacker.getDamage());
        String healthMessage = defender.getName() + "'s health level: " + defender.getHealth() + "\n";
        System.out.println(healthMessage);
        print(healthMessage);
    }

}
