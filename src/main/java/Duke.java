import java.io.FileNotFoundException;
import java.util.Scanner;

public class Duke {
    private Scanner scanner;
    private TaskList taskList;
    private TaskListManager taskListManager;

    public Duke() throws DukeException, FileNotFoundException {
        scanner = new Scanner(System.in);
        taskList = new TaskList();
        taskListManager = new TaskListManager("data", "tasks.txt", taskList);
        taskListManager.loadTasks();
    }

    private Task parseTaskInput(String input) throws DukeException {
        String[] words = input.split(" ");
        TaskType taskType = TaskType.valueOf(words[0].toUpperCase());
        String taskDescription = input.replaceFirst(words[0], "").trim();

        switch (taskType) {
            case ADD:
                if (taskDescription.isEmpty()) {
                    throw new DukeException("What should I add in? Pleas add in a description :)");
                }
                return new Add(taskDescription);
            case TODO:
                if (taskDescription.isEmpty()) {
                    throw new DukeException("Task Description cannot be EMPTY. Please add in a description :)");
                }
                return new ToDo(taskDescription);
            case DEADLINE:
                String[] deadlineParts = taskDescription.split("/by", 2);
                String description = deadlineParts[0].trim();
                if (description.isEmpty()) {
                    throw new DukeException("Please provide a task description.");
                }
                if (deadlineParts.length < 2 || deadlineParts[0].trim().isEmpty()) {
                    throw new DukeException("When is " + deadlineParts[0] + " due? use /by: (date)");
                }
                return new DeadLine(deadlineParts[0].trim(), deadlineParts[1].trim());
            case EVENT:
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
        System.out.println(" Keywords: add, deadline, event, todo, bye, echo");
        System.out.println("\n____________________________________________________________");

        boolean isEcho = true;

        while (isEcho) {
            String strInput = scanner.nextLine();
            System.out.println("____________________________________________________________");

            String[] words = strInput.split(" ");
            String firstWord = words[0].toLowerCase();

            try {
                TaskType taskType = TaskType.valueOf(firstWord.toUpperCase());

                if (firstWord.equals("add") || firstWord.equals("todo") ||
                        firstWord.equals("deadline") || firstWord.equals("event")) {
                    Task newTasks = parseTaskInput(strInput);
                    if (newTasks != null) {
                        taskList.addTask(newTasks);
                        taskListManager.saveTask(taskList.getTasks());
                    }
                }

                switch (taskType) {
                case BYE:
                    isEcho = false;
                    System.out.println(" Bye. Hope to see you again soon!");
                    taskListManager.saveTask(taskList.getTasks());
                    break;

                case LIST:
                    System.out.println("Here are the tasks in your list:");
                    taskList.displayTasks();
                    break;

                case MARK:
                    int taskIndex = Integer.parseInt(words[1]) - 1;
                    if (taskList.isMarked(taskIndex)) {
                        throw new DukeException("Task is already marked");
                    } else {
                        taskList.doneAndDusted(taskIndex);
                    }
                    break;

                case UNMARK:
                    taskIndex = Integer.parseInt(words[1]) - 1;
                    if (taskList.isMarked(taskIndex)) {
                        taskList.notDoneNotDusted(taskIndex);
                    } else {
                        throw new DukeException("Task is already unmarked");
                    }
                    break;

                case DELETE:
                    if (words.length == 1) {
                        throw new DukeException("Please provide a task number to delete");
                    }
                    taskIndex = Integer.parseInt(words[1]) - 1;
                    taskList.deleteTask(taskIndex);
                    break;

                case ECHO:
                    String[] echoWords = strInput.split(" ", 2);
                    if (echoWords.length < 2) {
                        throw new DukeException(" Nothing to echo!");
                    }
                    String echoedText = echoWords[1].trim();
                    System.out.println(" Echo!! " + echoedText);
                    break;

                case UNKNOWN:
                    throw new DukeException("Woops, I don't know this command. I only know: add, todo, deadline, event, list, mark, unmark, delete, bye, echo");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Woops, I don't know this command, sorry :(");
                System.out.println("Pay $100 to unlock more features!");
            } catch (DukeException e) {
                System.out.println("Error: " + e.getMessage());
            }
            System.out.println("\n____________________________________________________________");
        }
    }

    public static void main(String[] args) throws DukeException, FileNotFoundException {
        try {
            Duke bloopBot = new Duke();
            bloopBot.start();
        } catch (DukeException | FileNotFoundException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}