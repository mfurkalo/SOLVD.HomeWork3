/**
 * a plant with its unique characteristics,
 */

package clasess;

import interfaces.*;

public final class Corn extends Plant implements Consumption {

    public Corn(String name, float productivity, int harvestTime) {
        super();
        setName("Corn " + name);
        this.productivity = productivity;
        this.harvestTime = harvestTime;
        this.price = 2;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public void produceHarvest(int time, FarmAble farm) {

        if (isPlanted() && growingTime>=harvestTime) {
            int harvest = (int) (productivity * getPlantedArea());
            setPlanted(false);
            setGrowingTime(0);
            setPlantedArea(0);
            farm.addCorn(harvest);
        }
    }

    @Override
    public void plantField(float plantedArea, FarmAble farm) {
        setPlantedArea(plantedArea);
        int money = (int) (plantedArea * price);
        setPlanted(true);
        farm.addMoney(-money);
    }

    @Override
    public void toConsumption(int time, FarmAble farm) {
        int money = (int) (time * price * 0.02);
        farm.addMoney(-money);
    }

    @Override
    public String toString() {
        return "Corn {"
                + "'" + getName() + '\''
                + ", productivity=" + productivity
                + ", harvest time=" + harvestTime
                + ", price=" + price
                + '}';
    }
}