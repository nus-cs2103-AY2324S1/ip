import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Pau {

    private TaskList list;
    private static final String botName = "\n" +
            "██████╗  █████╗ ██╗   ██╗\n" +
            "██╔══██╗██╔══██╗██║   ██║\n" +
            "██████╔╝███████║██║   ██║\n" +
            "██╔═══╝ ██╔══██║██║   ██║\n" +
            "██║     ██║  ██║╚██████╔╝\n" +
            "╚═╝     ╚═╝  ╚═╝ ╚═════╝ \n" +
            "                         \n";

    private static final String introduction = " HI! I'm " + botName + "\n" + "ENTERTAIN MEEE";
    public static void exit() {
        System.out.println("byebyeee come play with me next time");
    }

    /**
     * Prints out a reminder to user that command is invalid.
     */
    public static void invalidCommand() {
        System.out.println("can you follow instructions");
    }

    public static TaskList loadTasks() {
        try {
            File toLoad = new File("./data/paulist.txt");
            Scanner scan = new Scanner(toLoad);
            TaskList list = new TaskList();
            while (scan.hasNext()) {
                String input = scan.nextLine();
                list.createTask(input);
            }
            return list;
        } catch (FileNotFoundException e) {
            System.out.println("is this your first time with pau?");
        }
        return new TaskList();
    }

    public static void main(String[] args) {
        System.out.println(Pau.introduction);

        String input;
        Scanner scan = new Scanner(System.in);
        TaskList list = loadTasks();
        list.checkList();

        while (true) {
            input = scan.nextLine();
            if (input.equals("bye")) {
                Pau.exit();
                break;
            } else if (input.equals("list")) {
                list.checkList();
            } else if (input.startsWith("mark")) {
                list.markTask(input);
            } else if (input.startsWith("unmark")) {
                list.unMarkTask(input);
            } else if (input.startsWith("delete")) {
                list.deleteTask(input);
            } else if (input.startsWith("todo")) {
                list.addsToDo(input);
            } else if (input.startsWith("deadline")) {
                list.addDeadline(input);
            } else if (input.startsWith("event")) {
                list.addEvent(input);
            } else {
                Pau.invalidCommand();
            }
            list.saveTasksToFile();
        }

    }
}
