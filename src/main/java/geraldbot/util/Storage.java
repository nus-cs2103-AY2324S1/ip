package geraldbot.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles reading and writing data to a storage file.
 *
 * @param <T> The type of data to be stored and read.
 */
public class Storage<T> {
    protected File file;

    /**
     * Constructor for Storage.
     *
     * @param path The path to the storage file.
     */
    public Storage(String path) {
        this.file = new File(path);

        if (!file.exists()) {
            createFile();
        }
    }

    /**
     * Creates a new file if it does not exist and also creates parent directories if needed.
     */
    protected void createFile() {
        File parentFolder = this.file.getParentFile();

        if (!parentFolder.exists()) {
            parentFolder.mkdirs();
        }

        try {
            if (this.file.createNewFile()) {
                System.out.println("File has been created successfully: " + this.file.getPath());
            }
        } catch (IOException e) {
            System.out.println(e);
            System.out.println("There is an error creating the file.");
        }
    }

    /**
     * Reads data from the storage file.
     *
     * @return An ArrayList of data objects.
     */
    public ArrayList<T> read() {
        ArrayList<T> dataList = new ArrayList<>();
        try {
            Scanner sc = new Scanner(this.file);

            while (sc.hasNextLine()) {
                String[] line = sc.nextLine().split("\\|");
                String[] data = new String[line.length];
                for (int i = 0; i < line.length; i++) {
                    data[i] = line[i].trim();
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println(e);
            createFile();
        }

        return dataList;
    }
}
