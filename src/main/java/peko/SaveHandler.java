package peko;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class SaveHandler {
    private static int listSize = 0;
    private static Task[] tasks = new Task[100];
    private static File file = new File("src/main/List.txt");

    //System.out.println("Gomen peko, something broke...");
    /**
     * Saves tasks to a text file.
     * This method clears the existing file content, then iterates through the task list,
     * converting each task to a line of tex and appending it to the file.
     * If the file is not found, a new file is created.
     */
    public static void saveTo() {

        System.out.println(file.getAbsolutePath());
        PrintWriter printWriter;

        try {

            printWriter = new PrintWriter(file.getAbsoluteFile());
            printWriter.write("");
            printWriter.close();
        } catch (FileNotFoundException e) {
            File temp = new File(file.getParentFile(), "List.txt");
            file = temp;

        } finally {
            for (Task t : tasks) {
                if (t == null) {
                    break;
                }
                String toStore = t.toStore() + "\n";


                //System.out.println("toStore: " + toStore);


                try {
                    Writer temp;
                    temp = new BufferedWriter(new FileWriter("src/main/List.txt", true));
                    temp.append(toStore);
                    temp.close();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

            }
        }
    }

    /**
     * Loads tasks from a text file.
     * This method reads the content of the file, parses each line to create tasks,
     * and populates the tasks array with the loaded tasks.
     *
     * @return An array of tasks loaded from the file.
     */
    public static Task[] loadFrom() {

        System.out.println(file.getAbsolutePath());
        try {
            int pos = 0;
            Scanner scanner = new Scanner(file.getAbsoluteFile());
            while (scanner.hasNextLine()) {
                String s = scanner.nextLine();
                String[] arr = s.split(" \\| ");
                Task t = stringToTask(arr);
                if (t != null) {
                    tasks[pos] = t;
                    pos++;
                }

            }
            listSize = pos;
        } catch (Exception e){

        }
        return tasks;
    }

    /**
     * Converts a string array to a Task object.
     * This method takes a string array containing task details and converts it into a Task object,
     * handling various task types (ToDos, Deadlines, and Events) and marking done as needed.
     *
     * @param arr The string array containing task details.
     * @return A Task object created from the provided string array, or null if parsing fails.
     */
    private static Task stringToTask(String[] arr) {
        Task t;
        try {

            switch (arr[0]) {
                case "T":
                    t = new ToDos( arr[2]);
                    if (arr[1] == "1") {
                        t.setMark();
                    }
                    return t;
                case "D":
                    t = new Deadline(arr[2] + " /by " + arr[3]);
                    if (arr[1] == "1") {
                        t.setMark();
                    }
                    return t;
                case "E":
                    t = new Event(arr[2] + " /from " + arr[3] + " /to " + arr[4]);
                    if (arr[1] == "1") {
                        t.setMark();
                    }
                    return t;
                default:
                    throw new InvalidTaskException();
            }
        } catch (InvalidTaskException e) {
            System.out.println(e);
            System.out.println("There's an error in the list, Pain peko, I'll delete it");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Incomplete task an error in the list, Pain peko, I'll delete it");
        }
        return  null;

    }

    /**
     * Retrieves the number of tasks in the task list.
     *
     * @return The number of tasks in the list.
     */
    public static int size() {
        return listSize;
    }


}
