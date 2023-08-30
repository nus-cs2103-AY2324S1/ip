import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileWriter;


public class Pau {

    private static String botName = "\n" +
            "██████╗  █████╗ ██╗   ██╗\n" +
            "██╔══██╗██╔══██╗██║   ██║\n" +
            "██████╔╝███████║██║   ██║\n" +
            "██╔═══╝ ██╔══██║██║   ██║\n" +
            "██║     ██║  ██║╚██████╔╝\n" +
            "╚═╝     ╚═╝  ╚═╝ ╚═════╝ \n" +
            "                         \n";

    private static String introduction = " HI! I'm " + botName + "\n" + "ENTERTAIN MEEE";
    public static void exit() {
        System.out.println("byebye come play with me next time");
    }

    /**
     * Prints out a list of the tasks the user has.
     *
     * @param list The list containing all the tasks that user has input.
     */
    public static void checkList(ArrayList<Task> list) {
        if (list.size() == 0) {
            int starEyesEmoji = 0x1F929;
            System.out.println("yay you don't have anything to do" + new String(Character.toChars(starEyesEmoji)));
        } else {
            System.out.println("sian you still have to complete these:");
            for (int i = 0; i < list.size(); i++) {
                Task curr = list.get(i);
                System.out.println((i + 1) + ". " + curr.toString());
            }
        }
    }

    /**
     * Marks a task as done.
     *
     * @param list  The list containing all the tasks that user has input.
     * @param input The task the user wants to mark as done.
     */
    public static void markTask(ArrayList<Task> list, String input) {
        int starEyesEmoji = 0x1F929;
        System.out.println("good job, you've completed a task! You're so productive!" + new String(Character.toChars(starEyesEmoji)));
        String parts[] = input.split(" ");
        int taskNo = Integer.parseInt(parts[1]);
        Task checkedTask = list.get(taskNo - 1);
        checkedTask.markAsDone();
        System.out.println(checkedTask.toString());
    }

    /**
     * Unmarks a task.
     *
     * @param list  The list containing all the tasks that user has input.
     * @param input The task the user wants to unmark.
     */
    public static void unMarkTask(ArrayList<Task> list, String input) {
        String parts[] = input.split(" ");
        int taskNo = Integer.parseInt(parts[1]);
        Task checkedTask = list.get(taskNo - 1);
        checkedTask.markAsUndone();
        System.out.println("why are you not going to " + checkedTask.description + "? remember to do it later!");
        System.out.println(checkedTask.toString());
    }

    /**
     * Deletes a Task from the list.
     *
     * @param list  The list containing all the tasks that user has input.
     * @param input The task the user wants to delete.
     */
    public static void deleteTask(ArrayList<Task> list, String input) {
        String parts[] = input.split(" ");
        int taskNo = Integer.parseInt(parts[1]);
        Task checkedTask = list.get(taskNo - 1);
        list.remove(checkedTask);
        if (checkedTask.getStatusIcon().equals("X")) {
            System.out.println("good job! you're officially done with this:");
            System.out.println(checkedTask.toString());
        } else {
            System.out.println("not you running away from your responsibilities, i guess you don't have to do this now:");
            System.out.println(checkedTask.toString());
        }
        if (list.size() == 0) {
            System.out.println("THERES NOTHING LEFT TO DO!!!!");
        } else {
            System.out.println("but still sucks to be you, you still have " + list.size() + " tasks");
        }
    }

    /**
     * Adds a ToDo to the list.
     *
     * @param list  The list containing all the tasks that user has input.
     * @param input The ToDo the user wants to add.
     */
    public static void addsToDo(ArrayList<Task> list, String input) {
        try {
            ToDo item = Task.createToDo(input);
            list.add(item);
            System.out.println("todo added: " + item.toString());
            System.out.println("You have this many stuff to complete: " + list.size());
        } catch (NoDescException e) {
        }
    }

    /**
     * Adds a Deadline to the list.
     *
     * @param list  The list containing all the tasks that user has input.
     * @param input The Deadline the user wants to add.
     */
    public static void addDeadline(ArrayList<Task> list, String input) {
        try {
            Deadline item = Task.createDeadline(input);
            list.add(item);
            System.out.println("deadline added: " + item.toString());
            System.out.println("You have this many stuff to complete: " + list.size());
        } catch (NoDescException e) {
        } catch (DeadlineNoEndException e) {
        }
    }

    /**
     * Adds an Event to the list.
     *
     * @param list  The list containing all the tasks that user has input.
     * @param input The Event the user wants to add.
     */
    public static void addEvent(ArrayList<Task> list, String input) {
        try {
            Event item = Task.createEvent(input);
            list.add(item);
            System.out.println("event added: " + item.toString());
            System.out.println("You have this many stuff to complete: " + list.size());
        } catch (NoDescException e) {
        }
    }

    /**
     * Prints out a reminder to user that command is invalid.
     */
    public static void invalidCommand() {
        System.out.println("can you follow instructions");
    }
    public static void saveTasksToFile(ArrayList<Task> list) {
        try {
            String filename = "./data/paulist.txt";
            FileWriter writer = new FileWriter(filename);
            for(int i = 0; i < list.size(); i++) {
                Task task = list.get(i);
                writer.write(task.writeToFile() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("problem saving to file: " + e.getMessage());
        }
    }

    public static ArrayList<Task> loadTasks() {
        try {
            File toLoad = new File("./data/paulist.txt");
            Scanner scan = new Scanner(toLoad);
            ArrayList<Task> list = new ArrayList<>();
            while (scan.hasNext()) {
                String input = scan.nextLine();
                list.add(Task.createTask(input));
            }
            return list;
        } catch (FileNotFoundException e) {
            System.out.println("is this your first time with pau?");
        } catch (NoDescException e) {
            throw new RuntimeException(e);
        } catch (DeadlineNoEndException e) {
            throw new RuntimeException(e);
        }
        return new ArrayList<>();
    }

    public static void main(String[] args) {
        System.out.println(Pau.introduction);

        String input;
        Scanner scan = new Scanner(System.in);
        ArrayList<Task> list = loadTasks();
        Pau.checkList(list);

        while (true) {
            input = scan.nextLine();
            if (input.equals("bye")) {
                Pau.exit();
                break;
            } else if (input.equals("list")) {
                Pau.checkList(list);
            } else if (input.startsWith("mark")) {
                Pau.markTask(list, input);
            } else if (input.startsWith("unmark")) {
                Pau.unMarkTask(list, input);
            } else if (input.startsWith("delete")) {
                Pau.deleteTask(list, input);
            } else if (input.startsWith("todo")) {
                Pau.addsToDo(list, input);
            } else if (input.startsWith("deadline")) {
                Pau.addDeadline(list, input);
            } else if (input.startsWith("event")) {
                Pau.addEvent(list, input);
            } else {
                Pau.invalidCommand();
            }
            saveTasksToFile(list);
        }

    }
}
