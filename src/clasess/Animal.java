package clasess;

import interfaces.AliveCreature;

abstract class Animal  implements  AliveCreature{

    private boolean isAlive;
    private String name= "animal";
    private int age;
    private float weight;

    public Animal(String name, int age, float weight) {
        this.name +=name;
        this.age = age;
        this.weight =weight;
        isAlive =true;
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

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public void setName(String name) {
        this.name = name;
    }

    protected void setWeight(float weight) {
        this.weight = weight;
    }

    abstract public void becomeNewAnimal(int time, Farm farm);
}
