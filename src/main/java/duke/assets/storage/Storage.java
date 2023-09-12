package duke.assets.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.Scanner;

import duke.assets.parser.Parser;
import duke.dukeexceptions.CorruptDataException;

/**
 * Represents a storage class that handles reading and writing data to a file
 */
public class Storage {
    private TaskList tasklist;
    private Parser parser;

    /**
     * Constructs a Storage object with a new TaskList and Parser
     */
    public Storage() {
        this.tasklist = new TaskList();
        this.parser = new Parser();
    }

    /**
     * Writes the current task list to a file
     */
    public void writeToFile() {
        try {
            File myFile = new File("./src/main/java/duke/data/duke.txt");
            FileWriter fw = new FileWriter(myFile);
            PrintWriter pw = new PrintWriter(fw);
            pw.print(tasklist.saveToFileFormat());
            pw.close();
        } catch (IOException IOExcept) {
            System.out.println("ChadGPT: Unfortunately there was an unexpected error when reading your data file.");
            IOExcept.printStackTrace();
        }
    }

    /**
     * Reads data from a file and adds it to the task list
     *
     * @return true if the file was read successfully, false otherwise
     */
    public boolean readFromFile() {
        try {
            File myFile = new File("./src/main/java/duke/data/duke.txt");
            myFile.createNewFile();
            Scanner sc = new Scanner(myFile);
            Parser dataParser = new Parser();
            while (sc.hasNextLine()) {
                String nextLine = sc.nextLine();
                dataParser.passDataCommand(nextLine, tasklist);
            }
            return true;
        } catch (IOException IOExcept) {
            System.out.println("ChadGPT: Unfortunately there was an unexpected error when reading your data file.");
            IOExcept.printStackTrace();
        } catch (CorruptDataException corruptDataExcept) {
            System.out.println("ChadGPT: Data is corrupt at: \"" + corruptDataExcept.getCorruptLine() +
                    "\". Please fix and press enter to proceed, or type the command \"exit\" to quit program");
            tasklist.clearList();
        }
        return false;
    }

    /**
     * Passes a user command to the parser to be executed
     *
     * @param nextLine the user command to be executed
     */
    public void passUserCommand(String nextLine) {
        this.parser.passUserCommand(nextLine, tasklist);
    }
}