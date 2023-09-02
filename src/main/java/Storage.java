import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filePath;
    Storage(String filePath) {
        this.filePath =filePath;
        try {
            printTasks();
        } catch (FileNotFoundException e) {
            System.out.println("Error loading file");
        }
    }

    /**
     * Prints the contents of the file stored at filePath
     * @throws FileNotFoundException It is thrown in a situation where the file
     * does not exist at the given filePath
     */
    public void printTasks() throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            System.out.println(s.nextLine());
        }
    }

    /**
     * Writes any changes in the tasks to the hard disk
     * @param filePath Stores the address to the file with the stored tasks
     * @param tasks The ArrayList containing all the tasks
     */
    public void writeUp(TaskList t) {
        try {
            FileWriter fw = new FileWriter(filePath);
            fw.write(t.getTasks());
            fw.close();
            this.printTasks();
        } catch (IOException e) {
            System.out.println("An exception occurred: " + e.getMessage());
        }
    }

    /**
     * Converts the input text into its equivalent ToDo Task
     * @param text String to be converted to a ToDo task
     * @return The ToDo task converted from text
     */
    public ToDo taskToDo(String text) {
        ToDo t = new ToDo(text.substring(10).trim());
        if (text.charAt(7) == 'X') {
            t.markTask();
        }
        return t;
    }

    /**
     * Converts the input text into its equivalent Event Task
     * @param text String to be converted to Event Task
     * @return The Event task converted from text
     */
    public Event taskEvent(String text) {
        boolean isMarked = text.charAt(7) == 'X';
        String[] parts = text.split("[()]");
        String taskDescription = parts[0].trim().substring(10);
        String timeInfo = parts[1].trim();
        String[] timeParts = timeInfo.split("from:|to:");
        String startTime = timeParts[1].trim();
        String endTime = timeParts[2].trim();
        String[] extractedValues = { taskDescription, startTime, endTime };
        Event e = new Event(extractedValues[0], extractedValues[1], extractedValues[2]);
        if (isMarked) {
            e.markTask();
        }
        return e;
    }

    /**
     * Converts the input text into its equivalent Deadline Task
     * @param text The String to be converted to a Deadline Task
     * @return The Deadline Task from the converted String
     */
    public Deadline taskDeadline(String text) {
        boolean isMarked = text.charAt(7) == 'X';
        text = text.substring(10);
        String wrd = "";
        String str = "";
        int i;
        for (i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (ch == ' ') {
                if (wrd.equals("(by:")) {
                    break;
                }
                str += wrd + " ";
                wrd = "";
            } else {
                wrd += ch;
            }
        }
        String deadArray[] = new String[2];
        deadArray[0] = str.trim();
        deadArray[1] = text.substring(i + 1, text.length() - 1);
        Deadline d = new Deadline(deadArray[0], deadArray[1]);
        if (isMarked) {
            d.markTask();
        }
        return d;
    }

    /**
     * This method reads a file and converts its text into an ArrayList of Task objects
     * @param filePath The path to the destination where the Tasks are stored
     * @return ArrayList of Tasks
     */
    public ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            File f = new File(filePath);
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String prompt = s.nextLine();
                char ch = prompt.charAt(4);
                if (ch == 'T') {
                    tasks.add(taskToDo(prompt));
                } else if (ch == 'E') {
                    tasks.add(taskEvent(prompt));
                } else {
                    tasks.add(taskDeadline(prompt));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error! Could not find file!");
        }
        return tasks;
    }
}
