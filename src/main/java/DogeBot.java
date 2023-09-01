import java.io.File;
import java.io.FileNotFoundException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import java.io.FileWriter;
import java.io.BufferedWriter;

public class DogeBot {
    private static ArrayList<Task> tasks = new ArrayList<>();

    private static final String HOME = System.getProperty("user.home"); // get relative path
    private static final java.nio.file.Path PATH = java.nio.file.Paths.get(HOME, "OneDrive", "Desktop", "iP",
        "src", "main");
    private static File file;
    public static void main(String[] args) {
        final String LOGO = "    ___\n"
            + " __/_  `.  .-\"\"\"-."         + "           |                      |             |   \n"
            + " \\_,` | \\-'  /   )`-')"      + "    _` |   _ \\    _` |   _ \\  __ \\    _ \\   __| \n"
            + "  \"\") `\"`    \\  ((`\"`"    + "    (   |  (   |  (   |   __/  |   |  (   |  |   \n"
            + " ___Y  ,    .'7 /|"            + "      \\__,_| \\___/  \\__, | \\___| _.__/  \\___/  \\__| \n"
            + "(_,___/...-` (_/_/"            + "                    |___/";

        System.out.println(LOGO + "\nHi ! I'm DogeBot \nHow may I help you today ?\n");
        Scanner sc = new Scanner(System.in);
        boolean isLoop = true;

        // create 'tasklist.txt' for saving tasks onto hard disk
        file = new File(PATH.toString(), "tasklist.txt");
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        // load 'tasklist.txt' into 'tasks' arraylist
        try {
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String s = reader.nextLine();
                String[] sArray = s.split("\\|");
//                System.out.println("$"+sArray[0]+"$");
//                break;
                switch (sArray[0]) {
                case "T ":
                    tasks.add(new ToDos(sArray[2]));
                    break;
                case "D ":
                    tasks.add(new Deadline(sArray[2], sArray[3]));
                    break;
                case "E ":
                    String[] temp = sArray[3].split("-");
                    tasks.add(new Event(sArray[2], temp[0], temp[1]));
                    break;
                default:
                    break;
                }
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        while (isLoop) {
            try {
                switch (sc.next().toLowerCase()) {
                case "bye":
                    isLoop = false;
                    bye();
                    break;
                case "list":
                    list();
                    break;
                case "mark":
                    mark(sc.nextInt() - 1);
                    break;
                case "unmark":
                    unmark(sc.nextInt() - 1);
                    break;
                case "todo":
                    todo(sc.nextLine()); // sc.nextLine() to get the remaining words
                    break;
                case "deadline":
                    deadline(sc.nextLine());
                    break;
                case "event":
                    event(sc.nextLine());
                    break;
                case "delete":
                    delete(sc.nextInt() - 1);
                    break;
                default:
                    System.out.println("Wuff, I'm not sure what that means :(");
                }
            } catch (InputMismatchException e) {
                sc.nextLine(); // absorb remaining words so 'default' block doesn't act up
                System.out.println("Oops ! Integers only please :c");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Oh no :( I think that number is too big~");
            } catch (DogeBotException e) {
                System.out.println(e.getMessage());
            }

        }

        System.out.println("Bye~ See you again");
        sc.close();
    }

    public static void bye() {
        // overwrite 'tasklist.txt' with 'tasks' arraylist
        try {
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            for (Task task : tasks) {
                bw.write(task.toString() + "\n");
            }
            bw.close();
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void list() throws DogeBotException {
        if (tasks.size() == 0) {
            throw new DogeBotException("Oops ! Your list is empty ! Try adding some tasks first c:");
        }

        System.out.println("Stuff to do:");
        int i = 1;
        for (Task task : tasks) {
            if (task == null) {
                break;
            }
            System.out.println(i++ + ". " + task.toString());
        }
    }

    public static void mark(int index) throws DogeBotException {
        if (tasks.size() == 0) {
            throw new DogeBotException("Oops ! Try adding some tasks first c:");
        }

        tasks.get(index).markTask(true);
        System.out.println("Good job on completing a task ! You deserve a cookie C:");
        System.out.println("\t" + tasks.get(index).toString());
    }

    public static void unmark (int index) throws DogeBotException {
        if (tasks.size() == 0) {
            throw new DogeBotException("Oops ! Try adding some tasks first c:");
        }

        tasks.get(index).markTask(false);
        System.out.println("Oh nyo, did someone make a mistake ?");
        System.out.println("\t" + tasks.get(index).toString());
    }

    public static void updateTasksCounter() {
        System.out.println("You now have " + tasks.size() + " task(s) in your list");
    }

    public static void todo(String words) throws DogeBotException {
        if (words.isBlank()) {
            throw new DogeBotException("Oops ! The description of a todo cannot be empty :(");
        }

        System.out.println("Mama mia ! I've just added this task:");
        Task temp = new ToDos(words);
        tasks.add(temp);
        System.out.println("\t" + temp.toString());
        updateTasksCounter();
    }

    public static void deadline(String words) throws DogeBotException {
        if (words.isBlank()) {
            throw new DogeBotException("Oops ! The description of a deadline cannot be empty :(");
        }

        int split = words.indexOf("/");
        // substring w/o the spaces
        String taskDescription = words.substring(0, split - 1);
        String taskDeadline = words.substring(split + 4, words.length());

        System.out.println("Mama mia ! I've just added this task:");
        Task temp = new Deadline(taskDescription + " ", " " + taskDeadline);
        tasks.add(temp);
        System.out.println("\t" + temp.toString());
        updateTasksCounter();
    }

    public static void event(String words) throws DogeBotException {
        if (words.isBlank()) {
            throw new DogeBotException("Oops ! The description of an event cannot be empty :(");
        }

        // substring w/o the spaces
        int startSplit = words.indexOf("/");
        String taskDescription = words.substring(0, startSplit - 1);
        int endSplit = words.indexOf("/", startSplit + 1); // find "/" after startSplit index
        String start = words.substring(startSplit + 6, endSplit - 1);
        String end = words.substring(endSplit + 4, words.length());

        System.out.println("Mama mia ! I've just added this task:");
        Task temp = new Event(taskDescription + " ", " " + start, end);
        tasks.add(temp);
        System.out.println("\t" + temp.toString());
        updateTasksCounter();
    }

    public static void delete(int index) throws DogeBotException {
        if (tasks.size() == 0) {
            throw new DogeBotException("Oops ! There's no tasks in your list to delete :O");
        }

        Task curr = tasks.get(index);
        System.out.println("Got it~ This task has been removed:");
        System.out.println("\t" + curr.toString());
        tasks.remove(index);
        updateTasksCounter();
    }
}
