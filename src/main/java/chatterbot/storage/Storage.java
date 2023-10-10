package chatterbot.storage;

import chatterbot.data.Task;
import chatterbot.data.TaskList;
import chatterbot.data.Deadline;
import chatterbot.data.Event;
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

    private static String filePath;

    public Storage() {
        this.filePath = "Chatterbot.txt";
        try {
            File file = new File(this.filePath);
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Storage(String filePath) {
        this.filePath = filePath;
        try {
            File file = new File(this.filePath);
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Initiates the list with entered user inputs that have been stored in the .txt file.
     * @throws FileNotFoundException If the file cannot be found.
     */
    public static ArrayList<Task> copyFileContents() throws FileNotFoundException {
        ArrayList<Task> taskArrayList = new ArrayList<>();
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String line = s.nextLine();
            if (line.contains("[T]")) {
                String[] todoSplit = line.split(" \\| ");
                String mark = todoSplit[1];
                String description = todoSplit[2];
                Todo todo = new Todo(description);
                if (mark.equals("1")) {
                    todo.setDone();
                }
                taskArrayList.add(todo);
            } else if (line.contains("[D]")) {
                String[] deadlineSplit = line.split(" \\| ");
                String mark = deadlineSplit[1];
                String deadlineDescription = deadlineSplit[2];
                String deadlineBy = deadlineSplit[3];
                Deadline deadline = new Deadline(deadlineDescription, deadlineBy);
                if (mark.equals("1")) {
                    deadline.setDone();
                }
                taskArrayList.add(deadline);
            } else if (line.contains("[E]")) {
                String[] eventSplit = line.split(" \\| ");
                String mark = eventSplit[1];
                String eventDescription = eventSplit[2];
                String eventTo = eventSplit[3];
                String eventFrom = eventSplit[4];
                Event event = new Event(eventDescription, eventTo, eventFrom);
                if (mark.equals("1")) {
                    event.setDone();
                }
                taskArrayList.add(event);
            }
        }
        s.close();
        return taskArrayList;
    }

    /**
     * Removes the current .txt file content by overwriting it.
     * @param textToWrite This is what will be written in the .txt file.
     * @throws IOException If there is an error reading or writing to the file.
     */
    public static void writeToFile(String textToWrite) throws IOException {
        try {
            FileWriter fw = new FileWriter(filePath);
            fw.write(textToWrite);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds to the current .txt file content.
     * @param textToAppend This is what will be added to the .txt file.
     * @throws IOException If there is an error reading or writing to the file.
     */
    public static void appendToFile(String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAppend + System.lineSeparator());
        fw.close();
    }
}