import java.util.Scanner;
public class Bee {
    private static TaskList listOfTasks = new TaskList();

    public static void main(String[] args) {
        String logo = " \n" +
                "__________               \n" +
                "\\______   \\ ____   ____  \n" +
                " |    |  _// __ \\_/ __ \\ \n" +
                " |    |   \\  ___/\\  ___/ \n" +
                " |______  /\\___  >\\___  >\n" +
                "        \\/     \\/     \\/ \n";
        System.out.println("Hello! I'm " + logo);

        Scanner scanner = new Scanner(System.in);
        System.out.println("~Bzzzz~ What may I assist you with today? ~Bzzzz~\n");

        boolean isRunning = true;
        while (isRunning) {
            String userInput = scanner.nextLine();
            // If user enters "bye", ends the program and says goodbye to the user.
            if (userInput.equals("bye")) {
                System.out.println("By-ee!. Hope to see you soon! ~Bzzz~");
                break;
            } else if (userInput.equals("list")) {
                listOfTasks.listAllTasks();
            } else if (userInput.startsWith("mark")) {
                int taskIndex = Integer.parseInt(userInput.split(" ")[1]);
                listOfTasks.setTaskDone(taskIndex);
            } else if (userInput.startsWith("unmark")) {
                int taskIndex = Integer.parseInt(userInput.split(" ")[1]);
                listOfTasks.setTaskNotDone(taskIndex);
            } else {
                // Else, echo back the user input and add to list
                listOfTasks.addTask(new Task(userInput));
            }
        }
    }
}