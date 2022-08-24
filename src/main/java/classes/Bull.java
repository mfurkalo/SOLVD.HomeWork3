/*
 * Copyright (c) 2022
 * A bull is an animal which consume food and produces meat
 * use it for free
 */

package classes;

import interfaces.Consumption;
import interfaces.FarmAble;

public final class Bull extends Animal implements Consumption {

    private final String balls = "I have 2 balls";

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
    public void butcher(FarmAble farm) {
        boolean isOld = (getAge() / getLifeTime() >= 1);
        if (isAlive() && isOld) {
            int meat = (int) (getWeight() * 0.6);
            setAlive(false);
            farm.addMeat(meat);
            farm.getBarn().remove(this);
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
        for (char ch : chars) {
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
        return "Bull {" + "'" + getName()
                + '\'' + ", age = " + getAge()
                + ", weight = " + getWeight()
                + ", price = " + getPrice()
                + ", " + balls + '}';
    }
}
