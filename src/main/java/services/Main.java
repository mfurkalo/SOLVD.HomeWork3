/*
 * Copyright (c) 2022
 * The main program service class
 * runs a farm simulator
 * use it for free
 */

package services;

import instances.*;
import instances.exeptions.NoCustomNameException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Random;
import java.util.Scanner;

public class Main {
    private static final Logger log = LogManager.getLogger(Main.class.getName());
    public static int maxTurnTime = 20;
    public static int maxAnimalBuy = 5;
    static Random random = new Random();
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        Farm myFarm = new Farm();
        myFarm.plantField(myFarm.getWheatField(), myFarm.getCornField().getPlantedArea());
        myFarm.plantField(myFarm.getCornField(), myFarm.getWheatField().getPlantedArea());
        start(myFarm);
    }

    /**
     *  game loop
     * @param farm takes ta farm into the game loop
     */
    static void start(Farm farm) {
        int totalTime = 0;
        int turnTime;
        boolean game = true;
        Animal animal;
        while (game) {
            String gameInput;
            turnTime = 2 + random.nextInt(maxTurnTime);
            totalTime = totalTime + turnTime;
            farm.getWheatField().toConsumption(turnTime, farm);
            farm.getWheatField().grow(turnTime);
            farm.getWheatField().produceHarvest(totalTime, farm);
            farm.getCornField().toConsumption(turnTime, farm);
            farm.getCornField().grow(turnTime);
            farm.getCornField().produceHarvest(totalTime, farm);
            for (int i = 0; i < farm.getBarn().size(); i++) {
                animal = farm.getBarn().get(i);
                if (animal instanceof Cow) {
                    ((Cow) animal).produceMilk(turnTime, farm);
                    animal.grow(turnTime);
                    animal.becomeNewAnimal(totalTime, farm);
                    ((Cow) animal).toConsumption(turnTime, farm);
                    animal.butcher(farm);
                } else if (animal instanceof Bull) {
                    animal.grow(turnTime);
                    ((Bull) animal).toConsumption(turnTime, farm);
                    animal.butcher(farm);
                }
                if (animal.getName().endsWith("New")) {
                    try {
                        throw new NoCustomNameException(animal, " needs a new custom name ");
                    } catch (NoCustomNameException e) {
                        System.out.println(e);
                        System.out.print("Give a new name for the animal: ");
                        gameInput = input.nextLine();
                        animal.setName("animal " + gameInput);
                    }
                }
            }
            System.out.println("\n\t\t ##### TOTAL time: '" + totalTime + "\t ##### TURN time: '" + turnTime + "' ");
            System.out.println(farm);
            marketOperation(farm);
            System.out.println(farm);
            System.out.println("\t\t######  Would you like to finish your Farm ?  (type 'YES')");
            gameInput = input.nextLine();
            if (gameInput.trim().equalsIgnoreCase("yes"))
                game = false;
        }
    }

    /**
     * Making business operations: Buy, Sell, Plant, Continue
     * @param farm an instance of the Farm.clas - our farm
     */
    static void marketOperation(Farm farm) {
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
                        System.out.println("What would you like to buy: \n\t1: Animal\n\t2: Gras feed\n\t3: Grain feed"
                                + "\n\t4: CANCEL");
                        marketInput = input.nextLine();
                        switch (marketInput) {
                            case "1":
                                farm.animalBuy();
                                break;
                            case "2":
                                price = 1 + random.nextInt(3);
                                System.out.print("How much Gras feed are you buying (price " + price + ") ? ");
                                marketInput = input.nextLine();
                                try {
                                    number = Integer.parseInt(marketInput);
                                    farm.addMoney(-number * price);
                                    farm.addGrassFeed(number);
                                } catch (NumberFormatException e) {
                                    System.out.print("Wrong input!");
                                    log.error("Error Input happened! " + e.getMessage());
                                }
                                break;
                            case "3":
                                price = 1 + random.nextInt(3);
                                System.out.print("How much Grain feed are you buying (price " + price + ") ? ");
                                marketInput = input.nextLine();
                                try {
                                    number = Integer.parseInt(marketInput);
                                    farm.addMoney(-number * price);
                                    farm.addGrainFeed(number);
                                } catch (NumberFormatException e) {
                                    System.out.print("Wrong input!");
                                    log.error("Error Input happened! " + e.getMessage());
                                }
                                break;
                            default:
                                isBuying = false;
                        }
                    }
                    break;
                case "2":
                case "sell":
                    boolean isSelling = true;
                    while (isSelling) {
                        System.out.println("What would you like to sell: \n\t1: Wheat\n\t2: Corn\n\t3: Milk\n\t4: Meat"
                                + "\n\t5: CANCEL");
                        marketInput = input.nextLine();
                        switch (marketInput) {
                            case "1":
                                price = farm.getWheatField().getPrice() - 2 + random.nextInt(3);
                                System.out.print("How much Wheat are you selling (price " + price + ") ? ");
                                while (true) {
                                    marketInput = input.nextLine();
                                    try {
                                        number = Integer.parseInt(marketInput);
                                        if (number <= farm.getWheat()) {
                                            farm.addWheat(-number);
                                            farm.addMoney(number * price);
                                            break;
                                        }
                                    } catch (NumberFormatException e) {
                                        System.out.print("Wrong input!");
                                        log.error("Error Input happened! " + e.getMessage());
                                    }
                                    System.out.println(" Available only: " + farm.getWheat() + " wheat");
                                }
                                break;
                            case "2":
                                price = farm.getCornField().getPrice() - 1 + random.nextInt(3);
                                System.out.print("How much Corn are you selling (price " + price + ") ? ");
                                while (true) {
                                    marketInput = input.nextLine();
                                    try {
                                        number = Integer.parseInt(marketInput);
                                        if (number <= farm.getCorn()) {
                                            farm.addCorn(-number);
                                            farm.addMoney(number * price);
                                            break;
                                        }
                                    } catch (NumberFormatException e) {
                                        System.out.print("Wrong input!");
                                        log.error("Error Input happened! " + e.getMessage());
                                    }
                                    System.out.println(" Available only: " + farm.getCorn() + " corn");
                                }
                                break;
                            case "3":
                                System.out.print("How much Milk are you selling (price " + Farm.milkPrice + ") ? ");
                                while (true) {
                                    marketInput = input.nextLine();
                                    try {
                                        number = Integer.parseInt(marketInput);
                                        if (number <= farm.getMilk()) {
                                            farm.addMilk(-number);
                                            farm.addMoney(number * Farm.milkPrice);
                                            break;
                                        }
                                    } catch (NumberFormatException e) {
                                        System.out.print("Wrong input!");
                                        log.error("Error Input happened! " + e.getMessage());
                                    }
                                    System.out.println(" Available only: " + farm.getMilk() + " milk");
                                }
                                break;
                            case "4":
                                System.out.print("How much Meat are you selling (price " + Farm.meatPrice + ") ? ");
                                while (true) {
                                    marketInput = input.nextLine();
                                    try {
                                        number = Integer.parseInt(marketInput);
                                        if (number <= farm.getMeat()) {
                                            farm.addMeat(-number);
                                            farm.addMoney(number * Farm.meatPrice);
                                            break;
                                        }
                                    } catch (NumberFormatException e) {
                                        System.out.print("Wrong input!");
                                        log.error("Error Input happened! " + e.getMessage());
                                    }
                                    System.out.println(" Available only: " + farm.getMeat() + " meat");
                                }
                                break;
                            default:
                                isSelling = false;
                        }
                    }
                    break;
                case "3":
                case "plant":
                    float freeLand = Farm.land - farm.getCornField().getPlantedArea()
                            - farm.getWheatField().getPlantedArea();
                    boolean isFreeLand = (freeLand) > 0;
                    while (isFreeLand) {
                        System.out.println("What would you like to plant  on '" + (Farm.land
                                - farm.getCornField().getPlantedArea() - farm.getWheatField().getPlantedArea())
                                + "' : \n\t1: Wheat\n\t2: Corn\n\t3: CANCEL");
                        marketInput = input.nextLine();
                        if (marketInput.equals("1"))
                            farm.plantField(farm.getWheatField(), farm.getCornField().getPlantedArea());
                        else if (marketInput.equals("2")) {
                            farm.plantField(farm.getCornField(), farm.getWheatField().getPlantedArea());
                        } else {
                            break;
                        }
                    }
                    break;
                case "4":
                case "continue":
                    isTrading = false;
                    System.out.println("\t\t\t##### Enjoy the Farm #####");
                    break;
            }
        } while (isTrading);
    }
}
