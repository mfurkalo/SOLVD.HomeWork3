package clasess;

import interfaces.*;

public final class Bull extends Animal implements Consumption, ProduceMeat {

    private int price;
    private int lifeTime = 180;

    public Bull(String name, int age, float weight, int price) {
        super(name, age, weight);
        this.price = price;
    }

    @Override
    public void becomeNewAnimal(int time, Farm farm) {
    }

    @Override
    public void butcher(int time, AliveCreature animal, Farm farm) {
        if (isAlive() && time % lifeTime == 0) {
            int meat = (int) (getWeight() * 0.6);
            setAlive(false);
            farm.setMeat(meat);
            farm.getBarn().remove(animal);
        }
    }

    @Override
    public void toConsumption(int time, Object farm) {
        if (farm instanceof Farm) {
            Farm farm1 = (Farm) farm;
            int grasFeed = (int) (time * 1.5);
            int grain = (int) (time * 1.2);
            farm1.addGrassFeed(-grasFeed);
            farm1.addGrainFeed(-grain);
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
        return (o instanceof Bull)&& (getAge() ==((Bull) o).getAge())&&(getWeight()==((Bull) o).getWeight());
    }

    @Override
    public String toString() {
        return "Bull {"
                + "fullName='" + getName() + '\''
                + ", age=" + getAge()
                + ", weight=" + getWeight()
                + ", price=" + price
                + '}';
    }
}