/*
 * Copyright (c) 2022
 * The main program class
 * runs a farm simulator
 * use it for free
 */

package clasess;

import interfaces.FarmAble;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Farm implements FarmAble {

    enum AnimalType {
        COW,
        BULL
    }

    private static final Logger log = LogManager.getLogger(Farm.class.getName());
    public static final String CURRENCY = "USD";
    public static final float LAND;
    public static final int MILK_PRICE;
    public static final int MEAT_PRICE;

    private ArrayList<Animal> barn = new ArrayList<>();

    Wheat wheatField = new Wheat("Hard Red Winter wheat", 32, 240);
    Corn cornField = new Corn("Ambrosia Hybrid corn", 756, 60);

    Random random = new Random();
    Scanner input = new Scanner(System.in);
    int maxTurnTime = 20;
    int maxAnimalBuy = 5;

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
        System.out.println("Let's start your farm!      You have: \n \t money " + money + " " + CURRENCY + '\n' +
                "\tfree land " + LAND + "; Empty barn for animals " + "\n\t milk price: " + MILK_PRICE +
                "\n\t meat price: " + MEAT_PRICE);
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

    public static void main(String[] args) {
        int totalTime = 0;
        int turnTime = 2;
        Farm myFarm = new Farm();
        boolean game = true;
        Animal animal;
        myFarm.plantField(myFarm.wheatField, myFarm.cornField.getPlantedArea());
        myFarm.plantField(myFarm.cornField, myFarm.wheatField.getPlantedArea());
        myFarm.animalBuy();
        while (game) {
            String mainInput;
            turnTime = turnTime + myFarm.random.nextInt(myFarm.maxTurnTime);
            totalTime = totalTime + turnTime;
            myFarm.wheatField.toConsumption(turnTime, myFarm);
            myFarm.wheatField.grow(turnTime);
            myFarm.wheatField.produceHarvest(totalTime, myFarm);
            myFarm.cornField.toConsumption(turnTime, myFarm);
            myFarm.cornField.grow(turnTime);
            myFarm.cornField.produceHarvest(totalTime, myFarm);
            for (int i = 0; i < myFarm.barn.size(); i++) {
                animal = myFarm.barn.get(i);
                if (animal instanceof Cow) {
                    ((Cow) animal).produceMilk(turnTime, myFarm);
                    animal.grow(turnTime);
                    animal.becomeNewAnimal(totalTime, myFarm);
                    ((Cow) animal).toConsumption(turnTime, myFarm);
                    animal.butcher(myFarm);
                } else if (animal instanceof Bull) {
                    animal.grow(turnTime);
                    ((Bull) animal).toConsumption(turnTime, myFarm);
                    animal.butcher(myFarm);
                }
                if (animal.getName().equals("New")) {
                    try {
                        throw new NoCustomNameException(animal, " needs a new custom name ");
                    } catch (NoCustomNameException e) {
                        System.out.println(e);
                        mainInput = myFarm.input.nextLine();
                        animal.setName(mainInput);
                    }
                }
            }

            System.out.println("\n\t\t ##### TOTAL time: '" + totalTime + "\t ##### TURN time: '" + turnTime + "' ");
            System.out.println(myFarm);
            myFarm.marketOperation();
            System.out.println(myFarm);
            System.out.println("\t\t######  Would you like to finish your Farm ?  (type 'YES')");
            mainInput = myFarm.input.nextLine();
            if (mainInput.trim().equalsIgnoreCase("yes"))
                game = false;
        }
    }

    private boolean addAnimal(String animalInput, AnimalType animalType, int age, int weight, boolean isInput) {
        int price, number;
        while (!isInput) {
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
                                break;
                        }
                    }
                    isInput = true;
                    break;
                } else {
                    System.out.println("Wrong number, not more than " + maxAnimalBuy + " animal per once");
                }
            } catch (NumberFormatException e) {
                System.out.println("Wrong input!");
                log.error("Error Input happened! " + e.getMessage());
            }
            break;
        }
        return isInput;
    }

    void plantField(Plant plant, float plantedArea) {
        System.out.println("How much land would you like to plant with " + plant);
        String plantInput = input.nextLine();
        while (true) {
            try {
                float area;
                area = Float.parseFloat(plantInput);
                if (area <= (LAND - plantedArea)) {
                    plant.plantField(area, this);
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.print("Wrong input!");
                log.error("Error Input happened! " + e.getMessage());
            }
            System.out.println(" Available only " + (LAND - plantedArea) + " land");
            plantInput = input.nextLine();
        }
    }

    void animalBuy() {
        boolean isBullBought = false, isCowBought = false;
        int age = 45;
        int weight = 600;
        do {
            System.out.println("What animal would you like to by:\n \t1: cow\n\t2: bull");
            String animalInput = input.nextLine();
            if (!animalInput.isBlank() && !isCowBought && (animalInput.equalsIgnoreCase("1: cow") ||
                    animalInput.equalsIgnoreCase("cow") || animalInput.equals("1"))) {
                System.out.print("How much cows? ");
                animalInput = input.nextLine();
                isCowBought = addAnimal(animalInput, AnimalType.COW, age, weight, false);
            } else if (!animalInput.isBlank() && !isBullBought && (animalInput.equalsIgnoreCase("2: bull") ||
                    animalInput.equalsIgnoreCase("bull") || animalInput.equals("2"))) {
                age = 35;
                weight = 700;
                System.out.print("How much bulls? ");
                animalInput = input.nextLine();
                isBullBought = addAnimal(animalInput, AnimalType.BULL, age, weight, false);
            } else
                System.out.println("Wrong input! Cows are bought: " + isCowBought + "; Bulls are bought: " +
                        isBullBought);
        } while (!isBullBought || !isCowBought);
    }

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

    void marketOperation() {
        int price, number;
        boolean isTrading = true;
        do {
            System.out.println("What would you like to do: \n\t1: BUY\n\t2: SELL\n\t3: PLANT\n\t4: CONTINUE");
            String marketInput = input.nextLine();
            switch (marketInput.toLowerCase().trim()) {
                case "1":
                case "buy":
                    boolean isBuying = true;
                    while (isBuying) {
                        System.out.println("What would you like to buy: \n\t1: Animal\n\t2: Gras feed\n\t3: Grain feed" +
                                "\n\t4: CANCEL");
                        marketInput = input.nextLine();
                        if (marketInput.equals("1")) {
                            this.animalBuy();

                        } else if (marketInput.equals("2")) {
                            price = 1 + random.nextInt(3);
                            System.out.print("How much Gras feed are you buying (price " + price + ") ? ");
                            marketInput = input.nextLine();
                            try {
                                number = Integer.parseInt(marketInput);
                                this.addMoney(-number * price);
                                this.addGrassFeed(number);
                            } catch (NumberFormatException e) {
                                System.out.print("Wrong input!");
                                log.error("Error Input happened! " + e.getMessage());
                            }
                        } else if (marketInput.equals("3")) {
                            price = 1 + random.nextInt(3);
                            System.out.print("How much Grain feed are you buying (price " + price + ") ? ");
                            marketInput = input.nextLine();
                            try {
                                number = Integer.parseInt(marketInput);
                                this.addMoney(-number * price);
                                this.addGrainFeed(number);
                            } catch (NumberFormatException e) {
                                System.out.print("Wrong input!");
                                log.error("Error Input happened! " + e.getMessage());
                            }
                        } else
                            isBuying = false;
                    }
                    break;
                case "2":
                case "sell":
                    boolean isSelling = true;
                    while (isSelling) {
                        System.out.println("What would you like to sell: \n\t1: Wheat\n\t2: Corn\n\t3: Milk\n\t4: Meat" +
                                "\n\t5: CANCEL");
                        marketInput = input.nextLine();
                        if (marketInput.equals("1")) {
                            price = wheatField.getPrice() - 2 + random.nextInt(3);
                            System.out.print("How much Wheat are you selling (price " + price + ") ? ");
                            marketInput = input.nextLine();
                            try {
                                number = Integer.parseInt(marketInput);
                                addWheat(-number);
                                addMoney(number * price);
                            } catch (NumberFormatException e) {
                                System.out.print("Wrong input!");
                                log.error("Error Input happened! " + e.getMessage());
                            }
                        } else if (marketInput.equals("2")) {
                            price = cornField.getPrice() - 1 + random.nextInt(3);
                            System.out.print("How much Corn are you selling (price " + price + ") ? ");
                            marketInput = input.nextLine();
                            try {
                                number = Integer.parseInt(marketInput);
                                addCorn(-number);
                                addMoney(number * price);
                            } catch (NumberFormatException e) {
                                System.out.print("Wrong input!");
                                log.error("Error Input happened! " + e.getMessage());
                            }
                        } else if (marketInput.equals("3")) {
                            System.out.print("How much Milk are you selling (price " + MILK_PRICE + ") ? ");
                            marketInput = input.nextLine();
                            try {
                                number = Integer.parseInt(marketInput);
                                addMilk(-number);
                                addMoney(number * MILK_PRICE);
                            } catch (NumberFormatException e) {
                                System.out.print("Wrong input!");
                                log.error("Error Input happened! " + e.getMessage());
                            }
                        } else if (marketInput.equals("4")) {
                            System.out.print("How much Meat are you selling (price " + MEAT_PRICE + ") ? ");
                            marketInput = input.nextLine();
                            try {
                                number = Integer.parseInt(marketInput);
                                addMeat(-number);
                                addMoney(number * MEAT_PRICE);
                            } catch (NumberFormatException e) {
                                System.out.print("Wrong input!");
                                log.error("Error Input happened! " + e.getMessage());
                            }
                        } else
                            isSelling = false;
                    }
                    break;
                case "3":
                case "plant":
                    while (true) {
                        System.out.println("What would you like to plant  on '" + (LAND - cornField.getPlantedArea() -
                                wheatField.getPlantedArea()) + "' : \n\t1: Wheat\n\t2: Corn\n\t3: CANCEL");
                        marketInput = input.nextLine();
                        if (marketInput.equals("1"))
                            plantField(wheatField, cornField.getPlantedArea());
                        else if (marketInput.equals("2")) {
                            plantField(cornField, wheatField.getPlantedArea());
                        } else {
                            break;
                        }
                    }
                    break;
                case "4":
                case "continue":
                    isTrading = false;
                    break;
            }
            if (marketInput.equals("4"))
                System.out.println("\t\t##### Enjoy the Farm #####");
        } while (isTrading);
    }

    String barnContent() {
        StringBuilder builder = new StringBuilder();
        for (Animal animal : barn
        ) {
            builder.append('\t').append(animal).append('\n');
        }
        return builder.toString();
    }
}
