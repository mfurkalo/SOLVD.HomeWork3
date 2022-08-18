/*
 * Copyright (c) 2022
 * describes a farm
 * use it for free
 */

package interfaces;

import java.util.List;

public interface FarmAble {


    void addMoney(int money);

    void addWheat(int wheat);

    void addCorn(int corn);

     void addMilk(int milk);

     void addMeat(int meat);

     void addGrassFeed(int grassFeed);

     void addGrainFeed(int grainFeed);

     List<?> getBarn();

}
