package duke.assets.storage;

import java.io.File;
import java.io.IOException;
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
     * Reads data from a file and adds it to the task list
     *
     * @return true if the file was read successfully, false otherwise
     * @throws IOException if there was an issue with I/O, which cannot be handled by the program
     * @throws CorruptDataException if data file is corrupted
     */
    public boolean readFromFile() throws IOException, CorruptDataException {
        try {
            File myFile = new File("./duke.txt");
            myFile.createNewFile();
            Scanner sc = new Scanner(myFile);
            Parser dataParser = new Parser();
            while (sc.hasNextLine()) {
                String nextLine = sc.nextLine();
                dataParser.passDataCommand(nextLine, tasklist);
            }
            return true;
        } catch (IOException inputOutputExcept) {
            throw inputOutputExcept;
        } catch (CorruptDataException corruptDataExcept) {
            tasklist.clearList();
            throw corruptDataExcept;
        }
    }

    /**
     * Passes a user command to the parser to be executed
     *
     * @param nextLine the user command to be executed
     * @return appropriate chatbot response to user query
     */
    public String passUserCommand(String nextLine) {
        return this.parser.passUserCommand(nextLine, tasklist);
    }
}
