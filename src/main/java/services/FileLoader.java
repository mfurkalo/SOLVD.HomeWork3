/*
 * Copyright (c) 2022
 * Reads the file of initial values
 * use it for free
 */

package services;

import instances.Farm;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileLoader {

    public static final char DIVIDER_CHAR = ':';
    public static final String FILE_PATH = "src/main/resources/farm_resources.txt";
    private static final List<String> list = new ArrayList<>();
    private static final Logger log = LogManager.getLogger(Farm.class.getName());

    /**
     *  Names of variables for reading from the file
     */
    enum InitialResources {
        MONEY("money"),
        LAND("land"),
        MEAT_PRICE("meat_price"),
        MILK_PRICE("milk_price");
        final String value;

        InitialResources(String value) {
            this.value = value;
        }
    }

    /**
     *  Reads the file line by line into the list of strings
     */
    private static void readFile() {
        File file = FileUtils.getFile(FILE_PATH);
        try {
            LineIterator lineIterator = FileUtils.lineIterator(file);
            while (lineIterator.hasNext()) {
                list.add(lineIterator.next());
            }
        } catch (IOException e) {
            System.out.println("Problem " + e.getMessage());
            log.error("Error reading file happened! " + e.getMessage());
        }
    }

    /**
     *  Assigns value of starting variables from the strings using Lambda function
     */
    public static void loadValues() {
        readFile();
        list.stream().filter((s) -> s.contains("" + DIVIDER_CHAR)).forEach(s -> {
            var divider = s.indexOf(DIVIDER_CHAR);
            var parameter = s.substring(0, divider).trim();
            if (parameter.equalsIgnoreCase(FileLoader.InitialResources.MONEY.value))
                Farm.setMoney(Integer.parseInt(s.substring(divider + 1).trim()));
            else if (parameter.equalsIgnoreCase(FileLoader.InitialResources.LAND.value))
                Farm.setLand(Float.parseFloat(s.substring(divider + 1).trim()));
            else if (parameter.equalsIgnoreCase(FileLoader.InitialResources.MEAT_PRICE.value))
                Farm.setMeatPrice(Integer.parseInt(s.substring(divider + 1).trim()));
            else if (parameter.equalsIgnoreCase(FileLoader.InitialResources.MILK_PRICE.value))
                Farm.setMilkPrice(Integer.parseInt(s.substring(divider + 1).trim()));
        });
    }
}
