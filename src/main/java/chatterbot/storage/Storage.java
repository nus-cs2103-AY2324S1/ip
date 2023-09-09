package chatterbot.storage;

import chatterbot.data.Deadline;
import chatterbot.data.Event;
import chatterbot.data.Task;
import chatterbot.data.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents where the file and list contents are edited.
 */
public class Storage {

    private String filePath;
    private static ArrayList<Task> list;

    public Storage(String filePath, ArrayList<Task> list) {
        this.filePath = filePath;
        this.list = list;
    }

    /**
     * Initiates the list with entered user inputs that have been stored in the .txt file.
     * @param filePath This is the file path to the .txt file
     * @throws FileNotFoundException If the file cannot be found.
     */
    public static void copyFileContents(String filePath) throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String line = s.nextLine();
            if (line.contains("todo")) {
                Todo todo = new Todo(line.substring(5));
                list.add(todo);
            } else if (line.contains("deadline")) {
                int slashDeadline = line.indexOf("/");
                String deadlineDescription = line.substring(9, slashDeadline).trim();
                String deadlineBy = line.substring(slashDeadline + 3).trim();
                Deadline deadline = new Deadline(deadlineDescription, deadlineBy);
                list.add(deadline);
            } else if (line.contains("event")) {
                String[] eventSplit = line.split("/");
                String eventDescription = eventSplit[0].substring(6);
                String eventTo = eventSplit[1].substring(5);
                String eventFrom = eventSplit[2].substring(3);
                Event event = new Event(eventDescription, eventTo, eventFrom);
                list.add(event);
            }
        }
        s.close();
    }

    /**
     * Removes the current .txt file content by overwriting it.
     * @param filePath This is the file path to the .txt file.
     * @param textToWrite This is what will be written in the .txt file.
     * @throws IOException If there is an error reading or writing to the file.
     */
    public static void writeToFile(String filePath, String textToWrite) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToWrite);
        fw.close();
    }

    /**
     * Adds to the current .txt file content.
     * @param filePath This is the file path to the .txt file.
     * @param textToAppend This is what will be added to the .txt file.
     * @throws IOException If there is an error reading or writing to the file.
     */
    public static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAppend + System.lineSeparator());
        fw.close();
    }

}
