package puke.managers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import puke.task.Deadline;
import puke.task.Event;
import puke.task.Task;
import puke.task.ToDo;


/**
 * A class that handles the storage of the task list into ListData.txt.
 */
public class DataHandler {

    /**
     * Interprets a line from the ListData.txt file used to store events.
     *
     * @param input a line from the file
     * @return a corresponding task.
     * @throws PukeException If an invalid task is detected.
     */
    public static Task translate(String input) throws PukeException {
        String[] splitInput = input.split("/");
        Task output;
        if (splitInput[0].equals("[T]")) {
            output = translateToDo(splitInput);
        } else if (splitInput[0].equals("[D]")) {
            output = translateDeadline(splitInput);
        } else if (splitInput[0].equals("[E]")) {
            output = translateEvent(splitInput);
        } else {
            throw new PukeException();
        }

        if (splitInput[1].equals("1")) {
            output.mark();
        } else if (!splitInput[1].equals("0")) {
            throw new PukeException();
        }

        return output;
    }
    private static Task translateToDo(String[] splitInput) throws PukeException {
        if (splitInput.length > 3) {
            return ToDo.construct(splitInput[2],
                    Arrays.copyOfRange(splitInput, 3, splitInput.length));
        } else {
            return ToDo.construct(splitInput[2], new String[0]);
        }
    }
    private static Task translateDeadline(String[] splitInput) throws PukeException {
        if (splitInput.length > 4) {
            return Deadline.construct(splitInput[2], splitInput[3],
                    Arrays.copyOfRange(splitInput, 4, splitInput.length));
        } else {
            return Deadline.construct(splitInput[2], splitInput[3], new String[0]);
        }
    }
    private static Task translateEvent(String[] splitInput) throws PukeException {
        if (splitInput.length > 5) {
            return Event.construct(splitInput[2], splitInput[3], splitInput[4],
                    Arrays.copyOfRange(splitInput, 5, splitInput.length));
        } else {
            return Event.construct(splitInput[2], splitInput[3], splitInput[4], new String[0]);
        }
    }
    /**
     * Updates the ListData.txt file with the latest list of tasks.
     *
     * @param taskList The task list.
     * @throws IOException If an error occurs with the file writer.
     */
    public static void writeToDatabase(TaskList taskList) throws IOException {
        FileWriter fw = new FileWriter("ListData.txt");
        StringBuilder output = new StringBuilder();
        for (Task item:taskList.getList()) {
            output.append(item.write()).append("\n");
        }
        fw.write(output.toString());
        fw.close();
    }

    /**
     * Loads the events stored in the ListData.txt file when the program is run.
     * If an invalid line is detected, it is skipped.
     *
     * @return The Task List
     * @throws PukeException If the file is not found.
     */
    public static ArrayList<Task> loadDatabase() throws PukeException {
        Scanner sc;
        try {
            sc = new Scanner(new File("ListData.txt"));
        } catch (Exception e) {
            throw new PukeException();
        }
        ArrayList<Task> output = new ArrayList<Task>();
        while (sc.hasNext()) {
            try {
                output.add(DataHandler.translate(sc.nextLine()));
            } catch (Exception e) {
                continue;
            }
        }
        return output;
    }

    /**
     * Clears all stored tasks.
     *
     * @throws IOException If an error occurs with the FileWriter.
     */
    public static void clearAll() throws IOException {
        FileWriter fw = new FileWriter("ListData.txt");
        fw.write("");
        fw.close();
    }
}
