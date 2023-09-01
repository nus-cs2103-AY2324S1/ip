package duke.util;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a storage for the list of Tasks.
 */
public class Storage {
    private File file;

    public Storage(String path) {
        this.file = new File(path);
        if (!this.file.getParentFile().exists()) {
            this.file.getParentFile().mkdirs();
        }
        if (!this.file.exists()) {
            try {
                this.file.createNewFile();
            } catch (IOException e) {
                System.out.println("Error: Unable to create local file");
            }
        }
    }

    /**
     * Returns an ArrayList of Tasks from local data file.
     *
     * @return ArrayList consisting of Task read from the data file.
     * @throws FileNotFoundException
     */
    public ArrayList<Task> load() throws FileNotFoundException {
        ArrayList<Task> list = new ArrayList<>();
        Scanner sc = new Scanner(this.file);

        while (sc.hasNextLine()) {
            String input = sc.nextLine();

            if (input.charAt(0) == 'D') {
                Deadline deadline = Parser.parseLoadDeadline(input);
                list.add(deadline);
            } else if (input.charAt(0) == 'E') {
                Event event = Parser.parseLoadEvent(input);
                list.add(event);
            } else if (input.charAt(0) == 'T') {
                ToDo todo = Parser.parseLoadToDo(input);
                list.add(todo);
            }
        }

        return list;
    }

    /**
     * Save the given TaskList into a local data file.
     *
     * @param taskList The given TaskList to be saved locally.
     */
    public void save(TaskList taskList) {
        String input = taskList.toString();
        String newData = "";

        for (int i = 0; i < taskList.size(); i++) {
            int startIndex = input.indexOf((i + 1) + ".");
            int endIndex = input.indexOf("\n", startIndex);

            String subInput = input.substring(startIndex + 2, endIndex);
            String taskType = String.valueOf(subInput.charAt(1));
            String status = subInput.charAt(4) == 'X' ? "1" : "0";

            newData += taskType + " | " + status + " | ";
            if (taskType.equals("D")) {
                int byIndex = subInput.indexOf("(by:");
                int subEndIndex = subInput.indexOf(")");

                String description = subInput.substring(7, byIndex - 1);
                String by = subInput.substring(byIndex + 5, subEndIndex);

                newData += description + " | " + by;
            } else if (taskType.equals("E")) {
                int fromIndex = subInput.indexOf("(from:");
                int toIndex = subInput.indexOf("to:", fromIndex);
                int subEndIndex = subInput.indexOf(")");

                String description = subInput.substring(7, fromIndex - 1);
                String from = subInput.substring(fromIndex + 7, toIndex - 1);
                String to = subInput.substring(toIndex + 4, subEndIndex);

                newData += description + " | " + from + " | " + to;
            } else if (taskType.equals("T")) {
                String description = subInput.substring(7);

                newData += description;
            }
            newData += System.lineSeparator();
        }
        try {
            FileWriter fw = new FileWriter(file);
            fw.write(newData);
            fw.close();
        } catch (IOException e) {
            System.out.println("Error: Unable to save list");
        }
    }

}
