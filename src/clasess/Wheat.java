package clasess;

import interfaces.Consumption;

public final class Wheat extends Plant implements Consumption {

    private static int price = 3;
    private float productivity;
    private float plantedArea;
    private int harvestTime;

    public Wheat(String name, float productivity, int harvestTime) {
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

        if (isPlanted()&&time % harvestTime == 0) {
            int harvest = (int) (productivity * plantedArea);
            setPlanted(false);
            farm.addWheat(harvest);
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
    public boolean equals(Object o){
        return (o instanceof Wheat)&& (getName() ==((Wheat) o).getName())&&(productivity==((Wheat) o).productivity);
    }

    @Override
    public String toString() {
        return "Wheat {"
                + "fullName='" + getName() + '\''
                + ", productivity=" + productivity
                + ", harvest time=" + harvestTime
                + ", price=" + price
                + '}';
    }
}