package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * A class to handle loading data from and saving data to a file.
 */
public class Storage {
    /** Relative path of the location of the file that stores the data **/
    private String filePath;

    /**
     * Constructor for the Storage class.
     *
     * @param filePath Relative path of the file that stores the data.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves data to the file.
     *
     * @param data ArrayList of the string representation of the data to be stored.
     * @throws IOException If the named file exists but is a directory rather than a regular file, does not exist
     *      but cannot be created, or cannot be opened for any other reason
     */
    public void saveData(ArrayList<String> data) throws StorageException {
        try {
            String[] splitFilePath = filePath.split("/");
            String directories = String.join("/", Arrays.copyOfRange(splitFilePath, 0,
                    splitFilePath.length - 1));
            File dir = new File(directories);
            if (!dir.exists()) {
                if (!dir.mkdirs()) {
                    throw new StorageException("Error Saving data");
                }
            }


            FileWriter fw = new FileWriter(filePath);
            for (int i = 0; i < data.size(); i++) {
                fw.write(data.get(i) + "\n");
            }
            fw.close();
        } catch (IOException e) {
            throw new StorageException("Error Saving data");
        }

    }

    /**
     * Returns the data stored in the file.
     *
     * @returns Stored Data
     * @throws FileNotFoundException If the named file does not exist
     */
    public ArrayList<String> loadData() throws FileNotFoundException {
        ArrayList<String> data = new ArrayList<>();
        File f = new File(filePath);
        Scanner s = new Scanner(f);

        while (s.hasNext()) {
            String line = s.nextLine();
            data.add(line);
        }

        return data;
    }
}
