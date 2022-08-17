/*
 * Copyright (c) 2022
 * The main program service class
 * runs a farm simulator
 * use it for free
 */

package clasess;

public class Main {
    static int maxTurnTime = 20;
    static int maxAnimalBuy = 5;

    public static void main(String[] args) {
        int totalTime = 0;
        int turnTime;
        Farm myFarm = new Farm();
        boolean game = true;
        Animal animal;
        myFarm.plantField(myFarm.getWheatField(), myFarm.getCornField().getPlantedArea());
        myFarm.plantField(myFarm.getCornField(), myFarm.getWheatField().getPlantedArea());
        /* Farm loop */
        while (game) {
            String gameInput;
            turnTime = 2 + myFarm.random.nextInt(maxTurnTime);
            totalTime = totalTime + turnTime;
            myFarm.getWheatField().toConsumption(turnTime, myFarm);
            myFarm.getWheatField().grow(turnTime);
            myFarm.getWheatField().produceHarvest(totalTime, myFarm);
            myFarm.getCornField().toConsumption(turnTime, myFarm);
            myFarm.getCornField().grow(turnTime);
            myFarm.getCornField().produceHarvest(totalTime, myFarm);
            for (int i = 0; i < myFarm.getBarn().size(); i++) {
                animal = myFarm.getBarn().get(i);
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
                if (animal.getName().endsWith("New")) {
                    try {
                        throw new NoCustomNameException(animal, " needs a new custom name ");
                    } catch (NoCustomNameException e) {
                        System.out.println(e);
                        System.out.print("Give a new name for the animal: ");
                        gameInput = myFarm.input.nextLine();
                        animal.setName("animal " + gameInput);
                    }
                }
            }
            System.out.println("\n\t\t ##### TOTAL time: '" + totalTime + "\t ##### TURN time: '" + turnTime + "' ");
            System.out.println(myFarm);
            myFarm.marketOperation();
            System.out.println(myFarm);
            System.out.println("\t\t######  Would you like to finish your Farm ?  (type 'YES')");
            gameInput = myFarm.input.nextLine();
            if (gameInput.trim().equalsIgnoreCase("yes"))
                game = false;
        }
    }
}
