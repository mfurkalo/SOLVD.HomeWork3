package clasess;

import interfaces.FarmAble;
import org.apache.logging.log4j.*;

import java.util.ArrayList;

public class Farm implements FarmAble {

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

    public int getMoney() {
        return money;
    }

    public int getWheat() {
        return wheat;
    }

    public int getCorn() {
        return corn;
    }

    public int getMilk() {
        return milk;
    }

    public int getMeat() {
        return meat;
    }

    public int getGrassFeed() {
        return grassFeed;
    }

    public int getGrainFeed() {
        return grainFeed;
    }

    public ArrayList getBarn() {
        return Barn;
    }

    public void addMoney(int money) {
        Farm.money += money;
    }

    public void addWheat(int wheat) {
        Farm.wheat += wheat;
    }

    public void addCorn(int corn) {
        Farm.corn += corn;
    }

    public void addMilk(int milk) {
        Farm.milk += milk;
    }

    public void addMeat(int meat) {
        Farm.meat += meat;
    }

    public void addGrassFeed(int grassFeed) {
        Farm.grassFeed += grassFeed;
    }

    public void addGrainFeed(int grainFeed) {
        Farm.grainFeed += grainFeed;
    }

    public static void main(String[] args) {
        Farm myFarm = new Farm();
        log.error("Error happened!");
    }
}