package clasess;

import interfaces.AliveCreature;
import interfaces.Grow;

abstract class Animal implements AliveCreature, Grow {

    private final int price;
    private int lifeTime;
    private boolean isAlive;
    private String name = "animal";
    private int age;
    private float weight;

    public Animal(String name, int age, float weight, int price) {
        this.name += (' ' + name);
        this.age = age;
        this.weight = weight;
        this.price = price;
        isAlive = true;
    }

    protected void setLifetime(int time) {
        this.lifeTime = time;
    }

    public int getLifeTime() {
        return lifeTime;
    }

    public int getPrice() {
        return price;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public float getWeight() {
        return weight;
    }

    public final void setAlive(boolean alive) {
        isAlive = alive;
    }

    public void setName(String name) {
        this.name = name;
    }

    protected void setWeight(float weight) {
        this.weight = weight;
    }

    abstract public void becomeNewAnimal(int time, Farm farm);

    public void grow(int time) {
        age = age + time;
        weight= weight+(int)(time*1.5);
    }
}