package game.Battle;

import game.MyDroids.Droid;
import java.io.FileWriter;
import java.io.IOException;

public class TeamBattle {
    private Droid[] team1;
    private Droid[] team2;
    private String  filePath = "C:\\Java_AN\\TeamBattle.txt"; // Шлях до файлу

    public TeamBattle(Droid[] team1, Droid[] team2) {
        this.team1 = team1;
        this.team2 = team2;

        System.out.println("Team battle between teams:\n");
        printTeam("Team Iron Man", team1);
        printTeam("Team Captain America", team2);
    }

    private void print(String str) {
        try (FileWriter writer = new FileWriter(filePath, true)) { // true для дозапису
            writer.write(str + System.lineSeparator()); // Записуємо рядок з новим рядком
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void startTeamBattle() {
        while (areTeamsAlive()) {
            // Атака команди 1
            for (Droid attacker : team1) {
                if (attacker.isAlive()) {
                    Droid defender = selectRandomDefender(team2);
                    if (defender != null) {
                        attack(attacker, defender);
                        if (!defender.isAlive()) {
                            String defeatMessage = defender.getName() + " from Team Cap has been defeated\n";
                            System.out.println(defeatMessage);
                            print(defeatMessage);
                            break;
                        }
                    }
                }
            }

            // АтакА команди 2
            for (Droid attacker : team2) {
                if (attacker.isAlive()) { // Перевірка, чи живий атакуючий
                    Droid defender = selectRandomDefender(team1);
                    if (defender != null) {
                        attack(attacker, defender);
                        if (!defender.isAlive()) {
                            String defeatMessage = defender.getName() + " from Team Iron Man has been defeated\n";
                            System.out.println(defeatMessage);
                            print(defeatMessage);
                            break;
                        }
                    }
                }
            }
        }

        // Оголошення переможця
        if (areTeamAlive(team1)) {
            String winnerMessage = "Team Iron Man won the battle\n";
            System.out.println(winnerMessage);
            print(winnerMessage);
        } else {
            String winnerMessage = "Team Cap won the battle\n";
            System.out.println(winnerMessage);
            print(winnerMessage);
        }
    }

    private boolean areTeamsAlive() {
        return areTeamAlive(team1) && areTeamAlive(team2);
    }

    private boolean areTeamAlive(Droid[] team) {
        for (Droid droid : team) {
            if (droid.isAlive()) {
                return true;
            }
        }
        return false;
    }

    private Droid selectRandomDefender(Droid[] team) {
        for (Droid droid : team) {
            if (droid.isAlive()) {
                return droid;
            }
        }
        return null;
    }

    private void attack(Droid attacker, Droid defender) {
        if (!defender.isAlive()) {
            String defenderMessage = defender.getName() + " has already been defeated! Attack cannot be performed";
            System.out.println(defenderMessage);
            print(defenderMessage);
            return; // Зупиняємо атаку, якщо захисник поражений
        }

        String attacks = attacker.getName() + " attacks " + defender.getName() + " with damage LVL " + attacker.getDamage();
        System.out.println(attacks);
        print(attacks);
        defender.takeDamage(attacker.getDamage());
        String healthlvl = defender.getName() + "'s health level: " + defender.getHealth() + "\n";
        System.out.println(healthlvl);
        print(healthlvl);

        if (!defender.isAlive()) {
            String defeated = defender.getName() + " has been defeated \n";
            System.out.println(defeated);
            print(defeated);
        }
    }

    private void printTeam(String teamName, Droid[] team) {
        System.out.println(teamName + ": ");
        StringBuilder teamMessage = new StringBuilder(teamName + ": ");
        for (int i = 0; i < team.length; i++) {
            teamMessage.append(team[i].getName()).append(i < team.length - 1 ? ", " : "");
        }
        System.out.println(teamMessage);
        print(teamMessage.toString());
    }
}
