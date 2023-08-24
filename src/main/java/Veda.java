import java.util.ArrayList;
import java.util.Scanner;

public class Veda {

    private final static String NAME = "Veda";
    private static ArrayList<Task> tasks = new ArrayList<Task>(100);

    private static void addTask(String taskArgs) {
        String type = taskArgs.split(" ")[0].toLowerCase();
        Task newTask = null;
        String description = "";
        String[] descriptions = null; //For multiple arguments

        switch(type) {
            case "todo":
                description = taskArgs.replaceFirst("todo ", "");

                newTask = new ToDo(description);
                break;

            case "deadline":
                //TODO error handling for no "/by" keyword
                description = taskArgs.replaceFirst("deadline ", "");
                descriptions = description.split("/by");

                newTask = new Deadline(descriptions[0], descriptions[1]);
                break;

            case "event":
                //TODO error handling
                description = taskArgs.replaceFirst("event ", "");
                descriptions = description.split("/from ");
                String from = descriptions[1].split(" /to ")[0];
                String to = description.split("/to")[1];

                newTask = new Event(descriptions[0], from, to);
                break;

            default:
                //TODO error input
                break;
        }

        if (newTask != null && tasks.add(newTask)) {
            System.out.println("added in mission:\n" + newTask);
        } else {
            System.out.println("System is unable to accommodate the new mission");
        }
    }

    private static void markAsDone(int taskIndex) {
        Task task = tasks.get(taskIndex);

        if (task.isDone()) {
            //Task already marked as done
            System.out.println("Mission has been completed previously.");
            return;
        }

        task.updateCompletionStatus();

        System.out.println("Mission status updated! Mission completed successfully.");
        System.out.println(task);
    }

    private static void markUndone(int taskIndex) {
        Task task = tasks.get(taskIndex);

        if (!(task.isDone())) {
            //task already marked as undone
            System.out.println("Mission is already marked as undone!");
            return;
        }

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
                        (tasks.indexOf(task) + 1) + "." + task
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
