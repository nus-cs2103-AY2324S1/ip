package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Manages all loading and saving of data to external storage.
 */

public class Storage {

    private String dataFile;

    /**
     * Constructor for Storage class, establishes whether external storage
     * location exists, and if not, creates it.
     *
     * @param dataFolder relative pathname to the folder of external storage
     */
    public Storage(String dataFolder) {
        File g = new File(dataFolder);
        if (!g.exists()) {
            g.mkdir();
        }
        this.dataFile = dataFolder + "data.txt";
    }

    /**
     * Loads items into storage from a given data source.
     *
     * @param f Data source from which to read
     * @param items Storage to load data into
     */
    private void loadItemsFromFile(File f, ArrayList<String> items) throws FileNotFoundException {
        Scanner reader = new Scanner(f);
        while (reader.hasNextLine()) {
            items.add(reader.nextLine());
        }
        reader.close();
    }

    /**
     * Attempts to create a new file, throws an IOException if unsuccessul.
     *
     * @param f File that will be attempted to be created
     */
    private void createNewFile(File f) {
        try {
            f.createNewFile();
            System.out.println("Data file successfully created.");
        } catch (IOException e) {
            System.out.println("Data storage failed, items added to the app will be deleted after program exit.");
            e.printStackTrace();
        }
    }

    /**
     * Loads data from external storage into the application, creating an
     * ArrayList of items as strings to be further processed.
     *
     * @returns ArrayList of strings
     */
    public ArrayList<String> load() {
        File f = new File(dataFile);
        ArrayList<String> items = new ArrayList<>();
        try {
            loadItemsFromFile(f, items);
        } catch (FileNotFoundException fe) {
            System.out.println("Data file not found, attempting to create one...");
            createNewFile(f);
        }
        return items;
    }

    /**
     * Saves data from application into external storage.
     *
     * @param items list of TaskTypes to be saved to storage
     */

    public void save(ArrayList<TaskType> items) {
        try {
            FileWriter fileWriter = new FileWriter(dataFile);
            for (int i = 0; i < items.size(); i++) {
                fileWriter.write(items.get(i).saveStringRep() + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Something went wrong while saving your list items.");
        }

    }

}

