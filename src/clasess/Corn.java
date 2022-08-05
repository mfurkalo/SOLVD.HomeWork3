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

        if (time % harvestTime == 0) {
            int harvest = (int) (productivity * plantedArea);
            setPlanted(false);
            farm.addCorn(harvest);
        }
    }


    @Override
    public void plantField(float plantedArea, FarmAble farm) {
        this.plantedArea = plantedArea;
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
                + "fullName='" + getName() + '\''
                + ", productivity=" + productivity
                + ", harvest time=" + harvestTime
                + ", price=" + price
                + '}';
    }
}