import java.util.Scanner;

public class Duke {
    private Scanner scanner;
    private TaskList taskList;

    public Duke() {
        scanner = new Scanner(System.in);
        taskList = new TaskList();
    }

    private Task parseTaskInput(String input) throws DukeException {
        String[] words = input.split(" ");
        String taskType = words[0].toLowerCase();

        String taskDescription = input.substring(taskType.length()).trim();

        switch (taskType) {
            case "add":
                if (taskDescription.isEmpty()) {
                    throw new DukeException("What should I add in? Pleas add in a description :)");
                }
                return new Add(taskDescription);
            case "todo":
                if (taskDescription.isEmpty()) {
                    throw new DukeException("Task Description cannot be EMPTY. Please add in a description :)");
                }
                return new ToDo(taskDescription);
            case "deadline":
                String[] deadlineParts = taskDescription.split("/by", 2);
                String description = deadlineParts[0].trim();
                if (description.isEmpty()) {
                    throw new DukeException("Please provide a task description.");
                }
                if (deadlineParts.length < 2 || deadlineParts[0].trim().isEmpty()) {
                    throw new DukeException("When is " + deadlineParts[0] + " due? use /by: (date)");
                }
                return new DeadLine(deadlineParts[0].trim(), deadlineParts[1].trim());
            case "event":
                String[] eventParts = taskDescription.split("/from", 2);
                String desc = eventParts[0].trim();
                if (desc.isEmpty() || desc.equals("/from") || desc.equals("/to") ) {
                    throw new DukeException("Please provide a task description.");
                } else if (eventParts.length < 2) {
                    throw new DukeException("When is " + desc + "? use /from: (date) /to: (date)");
                } else {
                    String[] toParts = eventParts[1].split("/to", 2);
                    if (toParts.length < 2 || toParts[1].trim().isEmpty()) {
                        throw new DukeException("When is " + eventParts[0] + "? use /from: (date) /to: (date)");
                    } else {
                        return new Event(desc, toParts[0].trim(), toParts[1].trim());
                    }
                }
        }
        return null;
    }

    public void start() throws DukeException {
        System.out.println("Hello from\n" + "Bloooooooop");
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm BloopBot");
        System.out.println(" What can I do for you?");
        System.out.println(" Bloop will echo by default, without any keywords");
        System.out.println(" Keywords: add, deadline, event, todo, bye");
        System.out.println("\n____________________________________________________________");

        boolean isEcho = true;


        while (isEcho) {
            String strInput = scanner.nextLine();
            System.out.println("____________________________________________________________");

            String[] words = strInput.split(" ");
            String firstWord = words[0].toLowerCase();

            try {
                if (firstWord.equals("bye")) {
                    isEcho = false;
                    System.out.println(" Bye. Hope to see you again soon!");
                } else if (firstWord.equals("list")) {
                    System.out.println("Here are the tasks in your list:");
                    taskList.displayTasks();
                } else if (firstWord.equals("mark")) {
                    int taskIndex = Integer.parseInt(words[1]) - 1;
                    if (taskList.isMarked(taskIndex)) {
                        throw new DukeException("Task is already marked");
                    } else {
                        taskList.doneAndDusted(taskIndex);
                    }
                } else if (firstWord.equals("unmark")) {
                    int taskIndex = Integer.parseInt(words[1]) - 1;
                    if (taskList.isMarked(taskIndex)) {
                        taskList.notDoneNotDusted(taskIndex);
                    } else {
                        throw new DukeException("Task is already unmarked");
                    }
                } else if (firstWord.equals("add") || firstWord.equals("todo") ||
                        firstWord.equals("deadline") || firstWord.equals("event")) {
                    Task newTasks = parseTaskInput(strInput);
                    if (newTasks != null) {
                        taskList.addTask(newTasks);
                    }
                } else if (firstWord.equals("delete")) {
                    if (words.length == 1) {
                        throw new DukeException("Please provide a task number to delete");
                    }
                    int taskIndex = Integer.parseInt(words[1]) - 1;
                    taskList.deleteTask(taskIndex);
                } else {
                    System.out.println(" Echo!! " + strInput);
                }
            } catch (DukeException e) {
                System.out.println("Error: " + e.getMessage());
            }
            System.out.println("\n____________________________________________________________");
        }
    }

    public static void main(String[] args) throws DukeException {
        Duke bloopBot = new Duke();
        bloopBot.start();
    }
}