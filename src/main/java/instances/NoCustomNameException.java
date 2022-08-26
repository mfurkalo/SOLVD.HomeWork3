/*
 * Copyright (c) 2022
 * The custom exception which happens with new animals without a custom name
 * se it for free
 */

package instances;

public class NoCustomNameException extends Exception {

    private Animal animal;

    public NoCustomNameException(Animal animal, String message) {
        super(message);
        this.animal = animal;
    }

    @Override
    public String toString() {
        return "MyException{"
                + "animal=" + animal.toString()
                + "\n" + getMessage()
                + "} ";
    }
}
