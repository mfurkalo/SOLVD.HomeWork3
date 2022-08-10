package clasess;

import interfaces.*;

import java.util.Random;

public final class Cow extends Animal implements Consumption, ProduceMeat, ProduceMilk {

    private String udder = "I have a big udder";
    private int calf;

    public Cow(String name, int age, float weight, int price) {
        super(name, age, weight, price);
        setLifetime(450);
    }

    @Override
    public void produceMilk(int time,Farm farm) {
        farm.addMilk(time*10);
    }

    @Override
    public void becomeNewAnimal(int time, Farm farm) {
        if (isAlive() && time / (getLifeTime() / 9) >= 1 && time / (getLifeTime() / 9) > calf) {
            Random rand = new Random();
            Animal newAnimal;
            if (rand.nextBoolean()) {
                newAnimal = new Cow("New", 0, 10.5f, 1);
            } else
                newAnimal = new Bull("New", 0, 10.5f, 1);
            calf++;
            farm.getBarn().add(newAnimal);
        }
    }

    @Override
    public void butcher(FarmAble farm) {
        if (isAlive() && getAge() / getLifeTime() >= 1) {
            int meat = (int) (getWeight() * 0.5);
            setAlive(false);
            farm.addMeat(meat);
            farm.getBarn().remove(this);
        }
    }

    @Override
    public void toConsumption(int time, FarmAble farm) {
        if (farm instanceof Farm) {
            Farm farm1 = (Farm) farm;
            int grasFeed = time * 2;
            int grain = time * 1;
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
    public boolean equals(Object o) {
        return (o instanceof Cow) && (getAge() == ((Cow) o).getAge()) && (getWeight() == ((Cow) o).getWeight());
    }

    @Override
    public String toString() {
        return "Cow {"
                + "'" + getName() + '\''
                + ", age = " + getAge()
                + ", weight = " + getWeight()
                + ", price = " + getPrice()
                + ", calf= " + calf
                + ", " + udder
                + '}';
    }
}