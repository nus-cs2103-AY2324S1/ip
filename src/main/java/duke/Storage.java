package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filePath;
    private File file;

    /**
     * Constructor for the duke.Storage class.
     *
     * @param filePath The string representing the path of the file used.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        this.file = new File(filePath);
    }

    /**
     * Retrieves data from the file and stores it in an array list.
     *
     * @return The array list containing the tasks.
     * @throws DukeException
     */
    public ArrayList<Task> load() throws DukeException {
        try {
            Scanner s = new Scanner(this.file);
            ArrayList<Task> tempList = new ArrayList<>(100);
            while (s.hasNext()) {
                String currentLine = s.nextLine();
                String eventType = currentLine.substring(4, 5);
                boolean eventDone = currentLine.charAt(7) == 'X';
                if (eventType.equals("T")) {
                    String eventName = currentLine.substring(10);
                    Todo todo = new Todo(eventName, eventDone);
                    tempList.add(todo);
                } else if (eventType.equals("D")) {
                    String[] strSegments = currentLine.substring(10).split(" By: ");
                    String eventName = strSegments[0].trim();
                    String date = strSegments[1];
                    Deadline deadline = new Deadline(eventName, date, eventDone);
                    tempList.add(deadline);
                } else {
                    String[] strSegments = currentLine.substring(10).split(" From: ");
                    String eventName = strSegments[0].trim();
                    String[] strSegments2 = strSegments[1].split(" To: ");
                    String startDate = strSegments2[0];
                    int length = strSegments2[1].length();
                    String endDate = strSegments2[1].substring(0, length);
                    Event event = new Event(eventName, startDate, endDate, eventDone);
                    tempList.add(event);
                }
            }
            return tempList;
        } catch (FileNotFoundException e) {
            throw new DukeException("File not found :(");
        }
    };

    /**
     * Rewrites the contents of the task list to the file.
     *
     * @param list The array list of the task list.
     * @throws IOException
     */
    public void rewriteToFile(ArrayList<Task> list) throws IOException {
        writeToFile("");

        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                appendToFile((i + 1) + ". " + list.get(i) + "\n");
            }
        }
    }

    /**
     * Writes a string to the specified text file.
     *
     * @param textToAdd The string to be written.
     * @throws IOException
     */
    public void writeToFile(String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    /**
     * Adds a string to the end of the specified text file.
     *
     * @param textToAppend The string to be added.
     * @throws IOException
     */
    public void appendToFile(String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }
}
