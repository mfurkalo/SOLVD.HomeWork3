package clasess;

abstract class Plant {

    private boolean isPlanted;
    private String name;

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
        this.name +=(' '+ name);
    }

    abstract public void produceHarvest(int time, Farm farm);

    abstract public void plantField(float plantedArea, Farm farm);
}
