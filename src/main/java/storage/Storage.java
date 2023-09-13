package storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import command.Commands;
import dukeExceptions.DukeException;
import dukeExceptions.DukeLoadException;
import dukeExceptions.DukeSaveException;
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
        File writeData = new File("./src/data/duke.txt");
        try {
            writeData.createNewFile();
            FileWriter writer = new FileWriter(writeData);

            boolean isThereException[] = new boolean[1];  // Created a boolean to watch the consumer
            isThereException[0] = false;
            listOfTask.forEach(x-> {
                try {
                    writer.write(x.write());
                    if (x.getIsDone()) {
                        writer.write("mark " + (listOfTask.indexOf(x) + 1) + "\n");
                    }
                } catch (IOException e) {
                    isThereException[0] = true;  // If the consumer has an error it becomes true
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

        File saveData = new File("./src/data/duke.txt");
        String error = null;

        try {
            saveData.createNewFile();
            Scanner readData = new Scanner(saveData);

            for (int i = 0; i < startLine - 1; i++) {  // Continue from startLine
                if (readData.hasNextLine()) {
                    readData.nextLine();
                }
            }

            while (readData.hasNextLine()) {  // Begin loading
                String command = readData.nextLine();
                error = command;
                if (command.equals("\n")) {  // If next line is empty, it is the end of file
                    break;
                }

                Parser cmd = new Parser(command);  // Loads each line in the save file into the taskList
                Commands action = cmd.parse();
                action.execute(taskList, false);

                startLine++;
            }
            readData.close();

        } catch (DukeException e) {
            startLine++;
            return load(taskList, startLine, errorCarryForward // Carries the error to be shown at the end
                    + "line " + startLine + " corrupted: " + error + "\n");  // of the load

        } catch (IOException f) {
            return "You do not have access to create a save file";
        }

        return errorCarryForward;
    }
}
