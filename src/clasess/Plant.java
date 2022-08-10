/**
 * The base ancestor class for plants
 */

package clasess;

import interfaces.FarmAble;
import interfaces.Grow;

abstract class Plant implements Grow {

    private boolean isPlanted;
    private String name;
    protected int price;
    protected int harvestTime;
    protected int growingTime;
    protected float productivity;
    private float plantedArea;

    public Plant() {
        this.name = "plant";
    }

    public boolean isPlanted() {
        return isPlanted;
    }

    public String getName() {
        return name;
    }

    public void setGrowingTime(int growingTime) {
        this.growingTime = growingTime;
    }

    public float getPlantedArea() {
        return plantedArea;
    }

    protected void setPlanted(boolean planted) {
        isPlanted = planted;
    }

    @Override
    public final void grow(int time) {
        growingTime =growingTime+time;
    }

    protected void setPlantedArea(float area) {
        plantedArea = area;
    }

    public final void setName(String name) {
        this.name += (' ' + name);
    }

    public abstract void produceHarvest(int time, FarmAble farm);

    public abstract void plantField(float plantedArea, FarmAble farm);
}