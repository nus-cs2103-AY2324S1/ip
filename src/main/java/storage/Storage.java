package storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import command.Commands;
import dukeexceptions.DukeException;
import dukeexceptions.DukeSaveException;
import parser.Parser;
import task.ListOfTask;
import task.Task;

/**
 * This class handles all the reads and writes of the program.
 * It will save the list and load the list.
 */
public class Storage {

    /**
     * Saves the list to a specific file.
     *
     * @param listOfTask The task list to save.
     */
    public static void save(ArrayList<Task> listOfTask) throws DukeSaveException {
        File writeData = new File("./duke/data/duke.txt");
        try {
            writeData.createNewFile();
            FileWriter writer = new FileWriter(writeData);

            // Created a boolean to watch the consumer
            boolean[] isThereException = new boolean[1];
            isThereException[0] = false;
            listOfTask.forEach(x-> {
                try {
                    writer.write(x.write());
                    if (x.getIsDone()) {
                        writer.write("mark " + (listOfTask.indexOf(x) + 1) + "\n");
                    }
                } catch (IOException e) {
                    // If the consumer has an error it becomes true
                    isThereException[0] = true;
                }
            });
            if (isThereException[0]) {
                throw new DukeSaveException("Nothing was saved as you do not have write access");
            }

            writer.close();

        } catch (IOException e) {
            throw new DukeSaveException("You do not have access to save your file");
        }
    }

    /**
     * Loads the task list from a specific line in the save file onwards.
     *
     * @param taskList The task list that tasks are loaded into.
     * @return Returns a string of null or errors that occur in the loading of the file.
     */
    public static String load(ListOfTask taskList) {
        return load(taskList, 1, "");
    }

    /**
     * Loads the task list from a specific line in the save file onwards.
     *
     * @param taskList The task list that tasks are loaded into.
     * @param startLine The line number to start loading from.
     * @param errorCarryForward The string with the accumulated errors of the load.
     * @return Returns a string of null or errors that occur in the loading of the file.
     */
    private static String load(ListOfTask taskList, int startLine, String errorCarryForward) {
        assert(startLine > 0);
        assert(errorCarryForward != null);

        File holderDirectory = new File("./duke");
        File saveDirectory = new File("./duke/data");
        File saveData = new File("./duke/data/duke.txt");
        String error = null;

        try {
            holderDirectory.mkdir();
            saveDirectory.mkdir();
            saveData.createNewFile();
            Scanner readData = new Scanner(saveData);

            // Continue from startLine
            for (int i = 0; i < startLine - 1; i++) {
                if (readData.hasNextLine()) {
                    readData.nextLine();
                }
            }

            // Begin loading
            while (readData.hasNextLine()) {
                String command = readData.nextLine();
                error = command;
                // If next line is empty, it is the end of file
                if (command.equals("\n")) {
                    break;
                }

                // Loads each line in the save file into the taskList
                Parser cmd = new Parser(command);
                Commands action = cmd.parse();
                action.execute(taskList, false);

                startLine++;
            }
            readData.close();

        } catch (DukeException e) {
            startLine++;
            // Carries the error to be shown at the end of the load
            return load(taskList, startLine, errorCarryForward
                    + "line " + startLine + " corrupted: " + e.getMessage() + "\n" + error + "\n");

        } catch (IOException f) {
            return "You do not have access to create a save file";
        }

        return errorCarryForward;
    }
}
