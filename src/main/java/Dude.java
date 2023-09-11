import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Dude {

    static ArrayList<Task> taskList = new ArrayList<Task>();
    static int nTasks = 0;

    private static final String FILE_PATH = "./data/dude.txt";

    public static void addTodo(String task) {
        ToDo newTask = new ToDo(task);
//        taskList[nTasks] = newTask;
        taskList.add(newTask);
        System.out.printf("Got it. I've added this task:\n%s\n", newTask.toString());
        nTasks += 1;
        System.out.printf("Now you have %d tasks in the list. \n", nTasks);
    }
    public static void addDeadline(String task, LocalDateTime by) {
        Deadline newTask = new Deadline(task, by);
//        taskList[nTasks] = newTask;
        taskList.add(newTask);
        System.out.printf("Got it. I've added this task:\n%s\n", newTask.toString());
        nTasks += 1;
        System.out.printf("Now you have %d tasks in the list. \n", nTasks);
    }
    public static void addEvent(String task, String from, String to) {
        Event newTask = new Event(task, from, to);
//        taskList[nTasks] = newTask;
        taskList.add(newTask);
        System.out.printf("Got it. I've added this task:\n%s\n", newTask.toString());
        nTasks += 1;
        System.out.printf("Now you have %d tasks in the list. \n", nTasks);
    }

    public static void delete(int n) {
        Task removedTask = taskList.get(n-1);
        taskList.remove(n-1);
        nTasks -= 1;
        System.out.println("Noted. I've removed this task:");
        System.out.println(removedTask.toString());
        System.out.printf("Now you have %d tasks in the list.\n", nTasks);

    }

    public static void list() {
        for (int i = 0; i < nTasks; i++) {
            Task task = taskList.get(i);
//            Task task = taskList[i];
            System.out.printf("%d. %s\n", i+1, task.toString());
        }
    }

    public static void mark(int n) {
        taskList.get(n-1).setDone(true);
        System.out.println("Nice! I've marked this task as done:");
        System.out.printf("%d. %s\n", n, taskList.get(n-1).toString());
    }

    public static void unmark(int n) throws IOException {
        taskList.get(n-1).setDone(false);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.printf("%d. %s\n", n, taskList.get(n-1).toString());
    }

    public static void bye() {
        String greeting = "Bye. Hope to see you again soon!";
        System.out.println(greeting);
    }

    public static void saveTasksToDisk() throws IOException {
        String directoryPath = "./data";
        String filePath = "./data/dude.txt";

        File directory = new File(directoryPath);
        if (!directory.exists()) {
            if (directory.mkdirs()) {
                System.out.println("Directory created at: " + directoryPath);
            } else {
                System.err.println("Failed to create directory.");
                return;
            }
        }

        File file = new File(filePath);
        if (!file.exists()) {
            try {
                if (file.createNewFile()) {
                    System.out.println("File created at: " + filePath);
                } else {
                    System.err.println("Failed to create the file.");
                }
            } catch (IOException e) {
                System.err.println("An error occurred while creating the file: " + e.getMessage());
            }
        }

        String data = "";
        for (int i = 0; i < nTasks; i++) {
            Task task = taskList.get(i);
            data += task.saveTask();
        }
        try {
            FileWriter fw = new FileWriter(file);
            fw.write(data);
            fw.close();
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }

    public static void loadTasksFromDisk() throws FileNotFoundException {
        String filePath = "./data/dude.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                //System.out.println(line);
                String[] taskInfo = line.split("\\s+\\|\\s+");

                Task task;

                if (taskInfo[0].equals("T")) {
                    task = new ToDo(taskInfo[2]);
                    task.setDone(taskInfo[1] == "1");
                    taskList.add(task);
                    nTasks += 1;
                } else if (taskInfo[0].equals("D")) {
                    String byInput = taskInfo[3]; // ISO-8601 e.g. 2023-09-06T14:30
                    LocalDateTime by = LocalDateTime.parse(byInput);
                    // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                    // LocalDateTime by = LocalDateTime.parse(byInput, formatter);
                    task = new Deadline(taskInfo[2], by);
                    task.setDone(taskInfo[1] == "1");
                    taskList.add(task);
                    nTasks += 1;
                } else if (taskInfo[0].equals("E")) {
                    task = new Event(taskInfo[2], taskInfo[3], taskInfo[4]);
                    task.setDone(taskInfo[1] == "1");
                    taskList.add(task);
                    nTasks += 1;
                }
            }
        } catch (IOException e) {
            System.err.println("An error occurred while reading the file: " + e.getMessage());
        }
    }

    public static void main(String[] args) throws IOException {
        String greeting = "Hello, I'm Dude!\n" +
                "What can I do for you?";
        System.out.println(greeting);

        loadTasksFromDisk();
        System.out.printf("You have %d saved tasks:\n", nTasks);
        list();
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            String[] words = input.split(" ");

            if (words[0].equals("list")) {
                list();
            } else if (words[0].equals("bye")) {
                bye();
                break;
            } else if (words[0].equals("mark")) {
                mark(Integer.valueOf(words[1]));
                saveTasksToDisk();
            } else if (words[0].equals("unmark")) {
                unmark(Integer.valueOf(words[1]));
                saveTasksToDisk();
            } else if (words[0].equals("todo")) {
                if (words.length > 1) {
                    addTodo(input.substring(5));
                    saveTasksToDisk();
                } else {
                    System.out.println("OOPS!!! The description of a todo cannot be empty.");
                }
            } else if (words[0].equals("deadline")) {
                if (words.length == 1) {
                    System.out.println("OOPS!!! The description of a deadline cannot be empty.");
                }

                String[] taskWords = input.substring(9).split(" /by ");
                String byInput = taskWords[1]; //dd/mm/yyyy hh:mm format
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
                LocalDateTime by = LocalDateTime.parse(byInput, formatter);
                addDeadline(taskWords[0], by);
                saveTasksToDisk();
            } else if (words[0].equals("event")) {
                if (words.length == 1) {
                    System.out.println("OOPS!!! The description of an event cannot be empty.");
                }
                String[] taskWords = input.substring(6).split(" /");
                String from = taskWords[1].substring(5);
                String to = taskWords[2].substring(3);
                addEvent(taskWords[0], from, to);
                saveTasksToDisk();
            } else if (words[0].equals("delete")) {
                delete(Integer.valueOf(words[1]));
            } else {
                System.out.println(" OOPS!!! I'm sorry, but I don't know what that means :-(");
            }



        }
    }
}