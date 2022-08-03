package clasess;

import interfaces.*;

import java.util.Random;

public final class Cow extends Animal implements Consumption, ProduceMeat, ProduceMilk {

    private int price;
    private int lifeTime = 450;

    public Cow(String name, int age, float weight, int price) {
        super(name, age, weight);
        this.price = price;
    }

    @Override
    public void becomeNewAnimal(int time, Farm farm) {
        if (isAlive() && time % lifeTime / 9 == 0) {
            Random rand = new Random();
            Animal newAnimal;
            if (rand.nextBoolean()) {
                newAnimal = new Cow("New", 0, 10.5f, 1);
            } else
                newAnimal = new Bull("New", 0, 10.5f, 1);
            farm.getBarn().add(newAnimal);
        }
    }

    @Override
    public void butcher(int time, AliveCreature animal, Farm farm) {
        if (isAlive() && time % lifeTime == 0) {
            int meat = (int) (getWeight() * 0.5);
            setAlive(false);
            farm.setMeat(meat);
            farm.getBarn().remove(animal);
        }
    }

    @Override
    public void toConsumption(int time, Object farm) {
        if (farm instanceof Farm) {
            Farm farm1 = (Farm) farm;
            int grasFeed = (int) (time * 2);
            int grain = (int) (time * 1);
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
        return (o instanceof Cow)&& (getAge() ==((Cow) o).getAge())&&(getWeight()==((Cow) o).getWeight());
    }

    @Override
    public String toString() {
        return "Cow {"
                + "fullName='" + getName() + '\''
                + ", age=" + getAge()
                + ", weight=" + getWeight()
                + ", price=" + price
                + '}';
    }

}
