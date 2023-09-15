package peko.memory;

import peko.exceptions.InvalidTaskException;
import peko.tasks.Deadline;
import peko.tasks.Event;
import peko.tasks.Task;
import peko.tasks.ToDos;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class ArchiveHandler {

    private static File file = new File("src/main/Archive.txt");
    private static PrintWriter printWriter;
    private static ArrayList<Task> list = new ArrayList<>();
    private static HashMap<Task, Boolean> map = new HashMap<>();
    public static void archive(Task task) {
        if (!map.get(task)) {

            fileManager();
            String toStore = task.toStore() + "\n";
            try {
                Writer temp;
                temp = new BufferedWriter(new FileWriter("src/main/Archive.txt", true));
                temp.append(toStore);
                temp.close();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    private static void fileManager() {
        if (!file.exists()) {

            File temp = new File(file.getParentFile(), "List.txt");
            file = temp;
        } else {
            try {
                Scanner scanner = new Scanner(file.getAbsoluteFile());
                while (scanner.hasNextLine()) {
                    String s = scanner.nextLine();
                    String[] arr = s.split(" \\| ");
                    Task t = stringToTask(arr);
                    if (t != null) {
                        map.put(t, true);
                        list.add(t);
                    }

                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

    }
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

}
