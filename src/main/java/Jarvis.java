import java.util.Scanner;
import java.util.ArrayList;

public class Jarvis {
    private static ArrayList<Task> taskList = new ArrayList<Task>(); // list of Tasks

    private static final String name = "Jarvis";
    private static final String logo = "      **     **     *******   **      ** **  ********\n" +
            "     /**    ****   /**////** /**     /**/** **////// \n" +
            "     /**   **//**  /**   /** /**     /**/**/**       \n" +
            "     /**  **  //** /*******  //**    ** /**/*********\n" +
            "     /** **********/**///**   //**  **  /**////////**\n" +
            " **  /**/**//////**/**  //**   //****   /**       /**\n" +
            "//***** /**     /**/**   //**   //**    /** ******** \n" +
            " /////  //      // //     //     //     // ////////  ";
    private static final String line = "____________________________________________________________";
    private static final String greeting = "Good day Sir! I'm ";
    private static final String question = "How can I help you today Sir?";
    private static final String signOff = "Good bye Sir!";
    private static final String listInforming = "Here are the task in your list Sir:";
    private static final String markInforming = "Roger that Sir, I've marked this task as done:";
    private static final String unmarkInforming = "Noted Sir, I've marked this task as NOT done yet:";
    private static final String taskInforming = "As you please Sir, I've added the task:";

    private static void printGreeting() {
        System.out.println(logo);
        System.out.println(line);
        System.out.println(greeting + name + "!");
        System.out.println(question);
        System.out.println(line);
    }

    private static void printBye() {
        System.out.println(line);
        System.out.println(signOff);
        System.out.println(line);
    }

    private static void printList() {
        System.out.println(line);
        System.out.println(listInforming);
        for (int i = 0; i < taskList.size(); i++) { // listing out the current task
            int count = i + 1;
            Task currentTask = taskList.get(i);
            System.out.println(count + "." + currentTask.toString());
        }
        System.out.println(line);
    }

    private static void printMark(Task currentTask) {
        System.out.println(line);
        System.out.println(markInforming);
        System.out.println(currentTask.toString());
        System.out.println(line);
    }

    private static void printUnmark(Task currentTask) {
        System.out.println(line);
        System.out.println(unmarkInforming);
        System.out.println(currentTask.toString());
        System.out.println(line);
    }

    private static void printTask(Task currentTask) {
        System.out.println(line);
        System.out.println(taskInforming);
        System.out.println(currentTask.toString());
        System.out.println("You have now " + taskList.size() + " tasks in the list Sir.");
        System.out.println(line);
    }

    public static void main(String[] args) {

        printGreeting();

        while (true) {
            Scanner userInput = new Scanner(System.in);
            String command = userInput.nextLine();

            if (command.equals("list")) { // if "list" is entered
                printList();

            } else if (command.equals("bye")) { // if "bye" is entered
                printBye();
                break;

            } else if (command.contains("mark") || command.contains("unmark")) { // if "mark" or "unmark" is entered
                int taskNum = Integer.parseInt(command.substring(command.length() - 1));
                if (command.contains("unmark")) { // if "unmark" is entered
                    Task currentTask = taskList.get(taskNum - 1);
                    currentTask.setDone(false);
                    printUnmark(currentTask);
                } else { // if "mark" is entered
                    Task currentTask = taskList.get(taskNum - 1);
                    currentTask.setDone(true);
                    printMark(currentTask);
                }

            } else { // if task command is entered
                if (command.contains("todo")) { // if "todo" is entered
                    String taskDescription = command.substring(5);
                    ToDo todo = new ToDo(taskDescription);
                    taskList.add(todo);
                    printTask(todo);
                } else if (command.contains("deadline")) { // if "deadline" is entered
                    String tempString = command.substring(9);
                    String[] parts = tempString.split("/");
                    String taskDescription = parts[0];
                    String by = parts[1].substring(3);
                    Deadline deadline = new Deadline(taskDescription, by);
                    taskList.add(deadline);
                    printTask(deadline);
                } else { // if "event" is entered
                    String tempString = command.substring(6);
                    String[] parts = tempString.split("/");
                    String taskDescription = parts[0];
                    String from = parts[1].substring(5);
                    String to = parts[2].substring(3);
                    Event event = new Event(taskDescription, from, to);
                    taskList.add(event);
                    printTask(event);
                }
            }
        }
    }
}