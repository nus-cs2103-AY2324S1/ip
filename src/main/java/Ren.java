import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;

public class Ren {
    public static void main(String[] args) throws InvalidRenCommand {

        Set<String> TASK_TYPES = Set.of(
                "todo", "deadline", "event"
        );


        RenObjectMapper objectMapper = new RenObjectMapper();
        TaskList tasks;
        try {
            tasks = objectMapper.retrieveFromHarddisk();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            tasks = new TaskList(new ArrayList<Task>());
        }


        Scanner input = new Scanner(System.in);
        String welcomeMsg = "____________________________________________________________\n" +
                " Hello! I'm Ren\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n";
        String goodbyeMsg = "____________________________________________________________\n" +
                " Bye! Pleasure speaking with you :) \n" +
                "____________________________________________________________\n";


        System.out.println(welcomeMsg);
        String inputStr = input.nextLine();
        while (!inputStr.equals(Commands.EXIT_COMMAND.getValue())) {
            String[] commandArr = inputStr.split(" ");
            if (commandArr[0].equals(Commands.LS_COMMAND.getValue())) {
                System.out.println("____________________________________________________________\n");
                tasks.listTasks();
                System.out.println("____________________________________________________________\n");
            } else if (commandArr[0].equals(Commands.DELETE_COMMAND.getValue())) {
                try {
                    Task task = tasks.deleteTask(commandArr);

                    System.out.println("____________________________________________________________\n" +
                            String.format("Deleted %s\n", task) +
                            tasks.declareNumOfTasks() +
                            "____________________________________________________________\n");
                } catch (InsufficientArguments e) {
                    System.out.println("____________________________________________________________\n");
                    System.out.println(e.getMessage());
                    System.out.println("____________________________________________________________\n");
                }
            } else if (commandArr[0].equals(Commands.MARK_COMMAND.getValue())
                    || commandArr[0].equals(Commands.UNMARK_COMMAND.getValue())) {
                Task task = tasks.toggleTask(commandArr);
                System.out.println("____________________________________________________________\n" +
                        String.format("Marked as %s!\n %s\n",
                                commandArr[0].equals(Commands.MARK_COMMAND.getValue()) ? "done" : "undone",
                                task) +
                        "____________________________________________________________\n");
            } else if (TASK_TYPES.contains(commandArr[0])) {
                try {
                    Task task = tasks.addTask(commandArr);

                    System.out.println("____________________________________________________________\n" +
                            String.format("Added %s\n", task) +
                            tasks.declareNumOfTasks() +
                            "____________________________________________________________\n");
                } catch (InsufficientArguments e) {
                    System.out.println("____________________________________________________________\n");
                    System.out.println(e.getMessage());
                    System.out.println("____________________________________________________________\n");
                }

            } else {
                System.out.println("____________________________________________________________\n");
                System.out.println("Please Enter a valid command.");
                System.out.println("____________________________________________________________\n");
            }

            inputStr = input.nextLine();
        }

        input.close();
        System.out.println(goodbyeMsg);
    }
}
