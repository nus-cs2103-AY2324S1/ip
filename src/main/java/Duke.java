import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
public class Duke {
    public static ArrayList<Task> taskList = new ArrayList<>();
    public static String currentDirPath = System.getProperty("user.dir");
    public static Path outputPath = Paths.get(currentDirPath, "output.txt");
    public static void addTask(Task newTask) throws IOException {
        taskList.add(newTask);
        System.out.println("    Got it... I've added this task...");
        System.out.println("      " + newTask.getStatus());
        System.out.println("    Now you have " + (taskList.size()) + " tasks in the list...");
        System.out.println("--------------------------------");
        saveFile();
    }
    public static void deleteTask(int index) throws DukeIndexOutOfBoundsException, IOException {
        if (taskList.size() < index) {
            throw new DukeIndexOutOfBoundsException();
        }
        Task temp = taskList.get(index - 1);
        taskList.remove(index - 1);
        System.out.println("    Noted. I've removed this task...");
        System.out.println("      " + temp.getStatus());
        System.out.println("    Now you have " + (taskList.size()) + " tasks in the list...");
        System.out.println("--------------------------------");
        saveFile();
    }
    public static void markAsDone(int index) throws DukeIndexOutOfBoundsException, IOException {
        if (taskList.size() < index) {
            throw new DukeIndexOutOfBoundsException();
        }
        Task target = taskList.get(index - 1);
        target.setDone(true);
        System.out.println("    I've marked this as done...");
        System.out.println("    " + target.getStatus());
        System.out.println("--------------------------------");
        saveFile();
    }
    /**
     * Unmarks task in the taskList ArrayList.
     * If the index is bigger than the size of the taskList, DukeIndexOutOfBoundsException is thrown.
     * @param index Index of the task in taskList to unmark.
     * @throws DukeIndexOutOfBoundsException If index > taskList.size()
     */
    public static void unmark(int index) throws DukeIndexOutOfBoundsException, IOException {
        if (taskList.size() < index) {
            throw new DukeIndexOutOfBoundsException();
        }
        Task target = taskList.get(index - 1);
        target.setDone(false);
        System.out.println("    I've marked this task as not done yet...");
        System.out.println("    " + target.getStatus());
        System.out.println("--------------------------------");
        saveFile();
    }

    public static void listTask() {
        for (int i = 0; i < taskList.size(); i++) {
            Task target = taskList.get(i);
            System.out.println("    " + (i + 1) + ". " + target.getStatus());
        }
        System.out.println("--------------------------------");
    }
    /**
     *
     * @throws IOException
     */
    public static void saveFile() throws IOException {
        try {
            if (!Files.exists(outputPath)) {
                Files.createFile(outputPath);
            }
            String data = "";
            for (Task task : taskList) {
                data += task.getStatus() + "\n";
            }
            Files.write(outputPath, data.getBytes());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public static boolean continueOrNot(String[] input) {
        if (input[0].equals("bye")) {
            return false;
        }
        return true;
    }
    public static String input(Scanner sc) {
        String reply = sc.nextLine();
        System.out.println("--------------------------------");
        return reply;
    }

    public static String[] splitBy(String input, String regex) {
        String [] parts = input.split(regex, 2);
        if (parts.length <= 1) {
            parts = new String[]{parts[0], ""};
        }
        return parts;
    }

    public static void greeting() {
        System.out.println("Hello.. I'm ekuD..");
        System.out.println("I probably won't be much of a help.. But ask me something..");
        System.out.println("--------------------------------");
        Scanner sc = new Scanner(System.in);
        String [] input = splitBy(input(sc), " ");

        while (continueOrNot(input)) {
            try {
                if (input[0].equals("list")) {
                    listTask();
                } else if (input[0].equals("mark")) {
                    markAsDone(Integer.parseInt(input[1]));
                } else if (input[0].equals("unmark")) {
                    unmark(Integer.parseInt(input[1]));
                } else if (input[0].equals("delete")) {
                    deleteTask(Integer.parseInt(input[1]));
                } else if (input[0].equals("todo")) {
                    addTask(new Todo(input[1]));
                } else if (input[0].equals("deadline")) {
                    if (input[1].isBlank()) {
                        addTask(new Deadline("", ""));
                    }
                    String newString = input[1];
                    String[] parts = splitBy(newString, "/by");
                    addTask(new Deadline(parts[0].strip(), parts[1].strip()));
                } else if (input[0].equals("event")) {
                    if (input[1].isBlank()) {
                        addTask(new Event("", "", ""));
                    }
                    String newString = input[1].strip();
                    String[] splitFrom = splitBy(newString, "/from");
                    String[] splitTo = splitBy(splitFrom[1], "/to");;
                    String startDate = splitTo[0].strip();
                    String endDate = splitTo[1].strip();
                    addTask(new Event(splitFrom[0].strip(), startDate, endDate));
                } else {
                    throw new DukeUnknownCommandException();
                }
            }
            catch (DukeException e) {
                System.out.println(e.getMessage());
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
            input = splitBy(input(sc), " ");
        }
        System.out.println("    bye...");
        sc.close();
    }
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        greeting();
    }
}

