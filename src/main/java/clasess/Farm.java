/*
 * Copyright (c) 2022
 *  Farm entity class
 * use it for free
 */

package clasess;

import interfaces.FarmAble;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Farm implements FarmAble {

    public static final String CURRENCY = "USD";
    public static final float LAND;
    public static final int MILK_PRICE;
    public static final int MEAT_PRICE;
    private static final Logger log = LogManager.getLogger(Farm.class.getName());
    private static int money;
    private static int wheat;
    private static int corn;
    private static int milk;
    private static int meat;
    private static int grassFeed;
    private static int grainFeed;

    enum AnimalType {
        COW,
        BULL
    }

    Random random = new Random();
    Scanner input = new Scanner(System.in);
    private Wheat wheatField = new Wheat("Hard Red Winter wheat", 32, 240);
    private Corn cornField = new Corn("Ambrosia Hybrid corn", 756, 60);
    private ArrayList<Animal> barn = new ArrayList<>();

    static {
        money = 10000;
        LAND = 456.5f;
        MEAT_PRICE = 7;
        MILK_PRICE = 1;
        System.out.println("Let's start your farm!      You have: \n \t money " + money + " " + CURRENCY + '\n' +
                "\tfree land " + LAND + "; Empty barn for animals " + "\n\t milk price: " + MILK_PRICE +
                "\n\t meat price: " + MEAT_PRICE);
    }

    public Wheat getWheatField() {
        return wheatField;
    }

    public Corn getCornField() {
        return cornField;
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

    public List<Animal> getBarn() {
        return barn;
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

    /* Adding the animals to the farm's barn */
    private boolean addAnimal(String animalInput, AnimalType animalType, int age, int weight, int maxAnimalBuy) {
        int price, number;
        try {
            number = Integer.parseInt(animalInput);
            if (number <= maxAnimalBuy) {
                for (int i = 0; i < number; i++) {
                    weight = weight + random.nextInt(50);
                    price = (int) (weight * 1.2);
                    age = age + random.nextInt(10);
                    System.out.print("Please enter name for your " + animalType + " " + (i + 1) + ": ");
                    String name;
                    do {
                        name = input.nextLine();
                        if (name.isBlank())
                            System.out.println("Wrong name!");
                        else
                            break;
                    } while (true);
                    switch (animalType) {
                        case COW:
                            Cow cow = new Cow(name, age, weight, price);
                            barn.add(cow);
                            addMoney(-cow.getPrice());
                            break;
                        case BULL:
                            Bull bull = new Bull(name, age, weight, price);
                            barn.add(bull);
                            addMoney(-bull.getPrice());
                    }
                }
                return true;
            } else {
                System.out.println("Wrong number, not more than " + maxAnimalBuy + " animal per once");
            }
        } catch (NumberFormatException e) {
            System.out.println("Wrong input!");
            log.error("Error Input happened! " + e.getMessage());
        }
        return false;
    }

    /* Planting land with a plant */
    void plantField(Plant plant, float plantedArea) {
        if (plant.getPlantedArea() == 0) {
            System.out.println("How much land would you like to plant with " + plant);
            while (true) {
                String plantInput = input.nextLine();
                try {
                    float area;
                    area = Float.parseFloat(plantInput);
                    if (area > 0 && area <= (LAND - plantedArea)) {
                        plant.plantField(area, this);
                        break;
                    }
                } catch (NumberFormatException e) {
                    System.out.print("Wrong input!");
                    log.error("Error Input happened! " + e.getMessage());
                }
                System.out.println(" Available only " + (LAND - plantedArea) + " land");
            }
        } else
            System.out.println(" Sorry, not possible, your " + plant.getName() + " is planted. Waite for harvest");
    }

    /* Buying animals for the farm */
    void animalBuy() {
        boolean isBullBought = false, isCowBought = false;
        int age = 45;
        int weight = 600;
        do {
            System.out.println("What animal would you like to by:\n \t1: cow\n\t2: bull");
            String animalInput = input.nextLine();
            if (!animalInput.isBlank() && !isCowBought && (animalInput.equalsIgnoreCase("1: cow")
                    || animalInput.equalsIgnoreCase("cow") || animalInput.equals("1"))) {
                System.out.print("How much cows? ");
                animalInput = input.nextLine();
                isCowBought = addAnimal(animalInput, AnimalType.COW, age, weight, Main.maxAnimalBuy);
            } else if (!animalInput.isBlank() && !isBullBought && (animalInput.equalsIgnoreCase("2: bull")
                    || animalInput.equalsIgnoreCase("bull") || animalInput.equals("2"))) {
                age = 35;
                weight = 700;
                System.out.print("How much bulls? ");
                animalInput = input.nextLine();
                isBullBought = addAnimal(animalInput, AnimalType.BULL, age, weight, Main.maxAnimalBuy);
            } else
                System.out.println("Wrong input! Cows are bought: " + isCowBought + "; Bulls are bought: "
                        + isBullBought);
        } while (!isBullBought || !isCowBought);
    }

    /* Getting the farm state in text */
    @Override
    public String toString() {
        return ("\t\t\t##########  Farm  ########## \n"
                + "money '" + money + "',\t"
                + "free land '" + (LAND - cornField.getPlantedArea() - wheatField.getPlantedArea()) + "',\t"
                + "wheat '" + wheat + "',\t"
                + "corn '" + corn + "',\t"
                + "milk '" + milk + "',\t"
                + "meat '" + meat + "',\t"
                + "grassFeed '" + grassFeed + "',\t"
                + "grainFeed '" + grainFeed + "',\t\n"
                + "there are " + barn.size() + " animals :\n")
                + barnContent();
    }

    /* Getting text content of the barn list*/
    String barnContent() {
        StringBuilder builder = new StringBuilder();
        for (Animal animal : barn
        ) {
            builder.append('\t').append(animal).append('\n');
        }
        return builder.toString();
    }
}
