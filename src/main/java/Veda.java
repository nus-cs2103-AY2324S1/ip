import java.util.ArrayList;
import java.util.Scanner;

public class Veda {

    private final static String NAME = "Veda";
    private static ArrayList<Task> tasks = new ArrayList<Task>(100);

    private static void addTask(String task) {
        Task newTask = new Task(task);

        if (newTask != null && tasks.add(newTask)) {
            System.out.println("added in mission: " + task);
        } else {
            System.out.println("System is unable to accommodate the new mission");
        }
    }

    private static void markAsDone(int taskIndex) {
        Task task = tasks.get(taskIndex);
        task.updateCompletionStatus();

        System.out.println("Mission status updated! Mission completed successfully.");
        System.out.println("[" + task.getStatusIcon() + "] " + task.getTask());
    }

    private static void markUndone(int taskIndex) {
        Task task = tasks.get(taskIndex);
        task.updateCompletionStatus();

        System.out.println("Mission status updated! Mission completion status reverted.");
        System.out.println("[" + task.getStatusIcon() + "] " + task.getTask());
    }


    public static void main(String[] args) {
        //Greet users upon initialisation
        System.out.println("____________________________________________________________");
        System.out.println(NAME + " initialised. How may I help you?");
        System.out.println("____________________________________________________________");

        Scanner inScanner = new Scanner(System.in);

        while (true) {
            String input = inScanner.nextLine();

            if (input.toLowerCase().equals("bye")) {
                //User wishes to exit the program
                break;
            } else if (input.toLowerCase().equals("list")) {
                //User wishes to see his listed missions
                System.out.println("Missions: ");
                tasks.forEach( task -> System.out.println(
                        (tasks.indexOf(task) + 1) + ".[" + task.getStatusIcon() + "] " + task.getTask()
                ));

                continue;
            } else if (input.toLowerCase().split(" ")[0].equals("mark")) {
                //User wishes to mark task as done
                //TODO Add an exception here in the case that user errorneously type an index that is out of bounds
                markAsDone(Integer.parseInt(input.toLowerCase().split(" ")[1]) - 1);
                continue;
            } else if (input.toLowerCase().split(" ")[0].equals("unmark")) {
                //User wishes to mark task as undone
                //TODO Add an exception here in the case that user errorneously type an index that is out of bounds
                markUndone(Integer.parseInt(input.toLowerCase().split(" ")[1]) - 1);
                continue;
            }

            //Add tasks
            addTask(input);
        }


        //Exits the program
        System.out.println("Bye. All the best for your mission!");
    }
}
