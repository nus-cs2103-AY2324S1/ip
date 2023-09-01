package duke;

import java.io.File;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;

/**
 * Represents a storage handler for managing reading from and writing to a file.
 * This class handles IO operations with the file to store and retrieve user data.
 */
public class Storage {
    /** The file name of the user data file. */
    protected String fileName = "userData.txt";

    /** The instance of the user data file. */
    protected File file;

    /**
     * Initializes a new Storage with a specified file path.
     * If the directory does not exist, it will be created.
     *
     * @param filePath The directory path where the user data file is to be stored.
     */
    public Storage(String filePath) {
        this.file = new File(filePath, fileName);
        //Making a new dir if the specified one does not exit
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
    }

    /**
     * Writes the given inputs to the storage file.
     * If an exception occurs, the stack trace will be printed.
     *
     * @param inputs The string to be written to the file.
     */
    public void write(String inputs) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(inputs);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads a line from the storage file that contains the specified key.
     * Returns a string representation of the line, or an error message if the key is not found.
     *
     * @param key The keyword to search for within the file.
     * @return The line from the file containing the key, or an error message.
     */
    public ArrayList<String> read(String key) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            ArrayList<String> lines = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains(key)) {
                    lines.add(line);
                }
            }
            if (lines.isEmpty()) {
                System.out.println("Keyword not found, please try again!");
                return null;
            }
            return convertToDisplayFormat(lines);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private ArrayList<String> convertToDisplayFormat(ArrayList<String> lines) {
        ArrayList<String> ans = new ArrayList<>();
        for (String line : lines) {
            String[] split = line.split("|");
            if (split[0].equals("[T]")) {
                ans.add("[T]" + split[2] + " " + split[4]);
            } else if (split[0].equals("[D]")) {
                ans.add("[D]" + split[2] + " " + split[4]
                        + " (by: " + split[6] + ")");
            } else {
                ans.add("[D]" + split[2] + " " + split[4]
                        + " (from: " + split[6] + ", to: " + split[8] + ")");
            }
        }
        return ans;
    }
}
