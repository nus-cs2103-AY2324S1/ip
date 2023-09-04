package puke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
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
        String[] split = input.split("/");
        Task output;
        if (split[0].equals("[T]")) {
            output = new ToDo(split[2]);
        } else if (split[0].equals("[D]")) {
            output = Deadline.construct(split[2], split[3]);
        } else if (split[0].equals("[E]")) {
            output = Event.construct(split[2], split[3], split[4]);
        } else {
            throw new PukeException();
        }

        if (split[1].equals("0")) {
            output.unmark();
        } else if (split[1].equals("1")) {
            output.mark();
        } else {
            throw new PukeException();
        }

        return output;
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
