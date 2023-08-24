import java.util.Scanner;
public class Bee {
    private static TaskList listOfTasks = new TaskList();

    public static void main(String[] args) {
        String logo = "\n" +
                "__________\n" +
                "\\______   \\ ____   ____\n" +
                " |    |  _// __ \\_/ __ \\\n" +
                " |    |   \\  ___/\\  ___/\n" +
                " |______  /\\___  >\\___  >\n" +
                "        \\/     \\/     \\/\n";
        System.out.println("Hello! I'm" + logo);

        Scanner scanner = new Scanner(System.in);
        System.out.println("~Bzzzz~ What may I assist you with today? ~Bzzzz~\n");

        boolean isRunning = true;
        while (isRunning) {
            String userInput = scanner.nextLine();
            String[] splitInput = userInput.split(" ");
            // If user enters "bye", ends the program and says goodbye to the user.
            if (splitInput[0].equals("bye")) {
                System.out.println("By-ee!. Hope to see you soon! ~Bzzz~");
                break;
            } else if (splitInput[0].equals("list")) {
                listOfTasks.listAllTasks();
            } else if (splitInput[0].equals("todo")) {
                // Take the input after "todo "
                String editedInput = userInput.substring(5);
                Todo todo = new Todo(editedInput);
                listOfTasks.addTask(todo);
            } else if (splitInput[0].equals("deadline")) {
                // Take the input after "deadline "
                String editedInput = userInput.substring(9);
                String[] splitEditedInput = editedInput.split(" /by ");
                String deadlineDescription = splitEditedInput[0];
                String deadlineDate = splitEditedInput[1];
                Deadline deadlineTask = new Deadline(deadlineDescription, deadlineDate);
                listOfTasks.addTask(deadlineTask);
            } else if (splitInput[0].equals("event")) {
                // Take the input after "event "
                String editedInput = userInput.substring(6);
                String[] splitEditedInput = editedInput.split(" /from ");
                String[] splitEditedInput2 = splitEditedInput[1].split(" /to ");
                String eventDescription = splitEditedInput[0];
                String eventStartDate = splitEditedInput2[0];
                String eventEndDate = splitEditedInput2[1];
                Event event = new Event(eventDescription, eventStartDate, eventEndDate);
                listOfTasks.addTask(event);
            }
            else if (splitInput[0].startsWith("mark")) {
                int taskIndex = Integer.parseInt(userInput.split(" ")[1]);
                listOfTasks.setTaskDone(taskIndex);
            } else if (splitInput[0].startsWith("unmark")) {
                int taskIndex = Integer.parseInt(userInput.split(" ")[1]);
                listOfTasks.setTaskNotDone(taskIndex);
            } else {
                // Else, echo back the user input and add to list
                listOfTasks.addTask(new Task(userInput));
            }
        }
    }
}