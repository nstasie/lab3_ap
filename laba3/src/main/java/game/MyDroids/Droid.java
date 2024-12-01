package game.MyDroids;

import java.util.Scanner;

public class Droid {
    private final String name;
    private final int damage;
    private int health;

    public Droid(String name, int damage, int health) {
        this.name = name;
        this.damage = damage;
        this.health = health;
    }

    public String getName() {
        return name;
    }

    public int getDamage() {
        return damage;
    }

    public int getHealth() {
        return health;
    }

    public boolean isAlive() {
        return health > 0;
    }

    public void takeDamage(int damage) {
        health -= damage;
        if (health < 0) {
            health = 0;
        }
    }

    @Override
    public String toString() {
        return "Name: " + name  + ", Health(HP): " + health +  ", Damage LVL: " + damage;
    }

    public static class IronMan extends Droid {
        public IronMan() {
            super("Iron Man", 38, 250);
        }
    }

    public static class BlackWidow extends Droid {
        public BlackWidow() {
            super("Black Widow", 26, 210);
        }
    }

    public static class SpiderMan extends Droid {
        public SpiderMan() {
            super("Spider Man", 15, 200);
        }
    }

    public static class CaptainAmerica extends Droid {
        public CaptainAmerica() {
            super("Captain America", 33, 260);
        }
    }

    public static class WinterSoldier extends Droid {
        public WinterSoldier() {
            super("Winter Soldier", 28, 205);
        }
    }

    public static class AntMan extends Droid {
        public AntMan() {
            super("Ant Man", 30, 200);
        }
    }

    public static Droid selectDroid() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose a character:");
        System.out.println("1. Iron Man");
        System.out.println("2. Black Widow");
        System.out.println("3. Spider-Man");
        System.out.println("4. Captain America");
        System.out.println("5. Winter Soldier");
        System.out.println("6. Ant-Man");

        System.out.print("Enter number of a character: ");

        int choice = scanner.nextInt();
        switch (choice) {
            case 1: {
                return new IronMan();
            }
            case 2: {
                return new BlackWidow();
            }
            case 3: {
                return new SpiderMan();
            }
            case 4: {
                return new CaptainAmerica();
            }
            case 5: {
                return new WinterSoldier();
            }
            case 6: {
                return new AntMan();
            }
            default:
                System.out.println("Invalid choice. Captain America is choosen by default");
                return new CaptainAmerica();
        }
    }
}
