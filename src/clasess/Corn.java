package clasess;

import interfaces.Consumption;

public final class Corn extends Plant implements Consumption {

    private static int price = 2;
    private float productivity;
    private float plantedArea;
    private int harvestTime;

    public Corn(String name, float productivity, int harvestTime) {
        super();
        setName(name);
        this.productivity = productivity;
        this.harvestTime = harvestTime;
    }

    public static int getPrice() {
        return price;
    }

    @Override
    public void produceHarvest(int time, Farm farm) {

        if (time % harvestTime == 0) {
            int harvest = (int) (productivity * plantedArea);
            setPlanted(false);
            farm.addCorn(harvest);
        }

    }

    @Override
    public void plantField(float plantedArea, Farm farm) {
        this.plantedArea = plantedArea;
        int money = (int) (plantedArea * price);
        setPlanted(true);
        farm.addMoney(-money);

    }

    @Override
    public void toConsumption(int time, Object farm) {
        if (farm instanceof Farm){
            Farm farm1 =(Farm) farm;
            int money = (int)(time*price*0.02);
            farm1.addMoney(-money);
        }
    }
}