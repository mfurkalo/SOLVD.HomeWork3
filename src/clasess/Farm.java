package clasess;

import org.apache.logging.log4j.*;

import java.util.ArrayList;

public class Farm {

    private static final Logger log = LogManager.getLogger(Farm.class.getName());
    public static final String CURRENCY = "USD";
    public static final float LAND;
    public static final int MILK_PRICE;
    public static final int MEAT_PRICE;

    private static ArrayList Barn = new ArrayList<Animal>();

    private static int money;
    private static int wheat;
    private static int corn;
    private static int milk;
    private static int meat;
    private static int grassFeed;
    private static int grainFeed;

    static {
        money = 100000;
        LAND = 456.5f;
        MEAT_PRICE = 7;
        MILK_PRICE = 1;
        System.out.println("Let's start your farm! You have: \n money " + money + " " + CURRENCY + '\n' + "land " + LAND
                + "\n milk price: " + MILK_PRICE + "\n meat price: " + MEAT_PRICE);
    }

    public static int getMoney() {
        return money;
    }

    public static int getWheat() {
        return wheat;
    }

    public static int getCorn() {
        return corn;
    }

    public static int getMilk() {
        return milk;
    }

    public static int getMeat() {
        return meat;
    }

    public static int getGrassFeed() {
        return grassFeed;
    }

    public static int getGrainFeed() {
        return grainFeed;
    }

    public static ArrayList getBarn() {
        return Barn;
    }

    public static void addMoney(int money) {
        Farm.money += money;
    }

    public static void addWheat(int wheat) {
        Farm.wheat += wheat;
    }

    public static void addCorn(int corn) {
        Farm.corn += corn;
    }

    public static void addMilk(int milk) {
        Farm.milk += milk;
    }

    public static void setMeat(int meat) {
        Farm.meat += meat;
    }

    public static void addGrassFeed(int grassFeed) {
        Farm.grassFeed += grassFeed;
    }

    public static void addGrainFeed(int grainFeed) {
        Farm.grainFeed += grainFeed;
    }

    public static void main(String[] args) {
        Farm myFarm = new Farm();
        log.error("Error happened!");
    }
}
