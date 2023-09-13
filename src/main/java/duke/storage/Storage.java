package duke.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import duke.exception.FileNotFoundException;

/**
 * Represents a text-file based storage.
 */
public class Storage {
    private final File file;
    private final BufferedWriter bufferedWriter;

    /**
     * Constructor for a Storage instance.
     * @param filePath The file path to the text file.
     * @throws FileNotFoundException If no file exists at the filePath.
     */
    public Storage(String filePath) throws FileNotFoundException {
        file = new File(filePath);
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(file, true));
        } catch (IOException e) {
            throw new FileNotFoundException(filePath);
        }
    }

    /**
     * Write a line into the text file.
     * @param line The line to add.
     */
    public void addLine(String line) {
        try {
            bufferedWriter.write(line);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get a line from the text file.
     * @param index The index of the line.
     * @return The line at the given index.
     */
    public String getLine(int index) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line;
            int currentLine = 1;
            while ((line = bufferedReader.readLine()) != null) {
                if (index == currentLine) {
                    return line;
                }
                currentLine++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * Clear the file of all text.
     */
    public void clear() {
        // Create a FileWriter with the given file path
        try {
            FileWriter fileWriter = new FileWriter(file);
            // Overwrite the file content with an empty string
            fileWriter.write("");
            // Close the FileWriter
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
