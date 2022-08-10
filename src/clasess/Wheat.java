package clasess;

import interfaces.*;

public final class Wheat extends Plant implements Consumption {

    public Wheat(String name, float productivity, int harvestTime) {
        super();
        setName("Wheat " + name);
        this.productivity = productivity;
        this.harvestTime = harvestTime;
        this.price = 3;
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
            farm.addWheat(harvest);
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
    public int hashCode() {
        int result = 0;
        char[] chars = getName().toCharArray();
        for (char ch : chars
        ) {
            result += ch;
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        return (o instanceof Wheat) && (getName() == ((Wheat) o).getName()) && (productivity == ((Wheat) o).productivity);
    }

    @Override
    public String toString() {
        return "Wheat {"
                + "'" + getName() + '\''
                + ", productivity=" + productivity
                + ", harvest time=" + harvestTime
                + ", price=" + price
                + '}';
    }

    @Override
    public void toConsumption(int time, FarmAble farm) {
        int money = (int) (time * price * 0.01);
        farm.addMoney(-money);
    }
}