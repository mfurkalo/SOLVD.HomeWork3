package clasess;

import interfaces.FarmAble;

abstract class Plant {

    private boolean isPlanted;
    private String name;
    protected int price;
    protected int harvestTime;
    protected float productivity;
    protected float plantedArea;

    public Plant() {
        this.name = "plant";
    }

    public boolean isPlanted() {
        return isPlanted;
    }

    public String getName() {
        return name;
    }

    protected void setPlanted(boolean planted) {
        isPlanted = planted;
    }

    public final void setName(String name) {
        this.name += (' ' + name);
    }

    public abstract void produceHarvest(int time, FarmAble farm);

    public abstract void plantField(float plantedArea, FarmAble farm);
}