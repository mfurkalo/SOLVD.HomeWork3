package clasess;

import interfaces.*;

public final class Bull extends Animal implements Consumption, ProduceMeat {

    private String balls = "I have 2 balls";

    public String getBalls() {
        return balls;
    }

    public Bull(String name, int age, float weight, int price) {
        super(name, age, weight, price);
        setLifetime(180);
    }

    @Override
    public void becomeNewAnimal(int time, Farm farm) {
    }

    @Override
    public void butcher(int time, AliveCreature animal, FarmAble farm) {
        if (isAlive() && time / getLifeTime() >= 1) {
            int meat = (int) (getWeight() * 0.6);
            setAlive(false);
            farm.addMeat(meat);
            farm.getBarn().remove(animal);
        }
    }

    @Override
    public void toConsumption(int time, FarmAble farm) {

        int grasFeed = (int) (time * 1.5);
        int grain = (int) (time * 1.2);
        farm.addGrassFeed(-grasFeed);
        farm.addGrainFeed(-grain);

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
        return (o instanceof Bull) && (getAge() == ((Bull) o).getAge()) && (getWeight() == ((Bull) o).getWeight());
    }

    @Override
    public String toString() {
        return "Bull {"
                + "'" + getName() + '\''
                + ", age = " + getAge()
                + ", weight = " + getWeight()
                + ", price = " + getPrice()
                + ", " + balls
                + '}';
    }
}