import java.util.Scanner;

public class Duke {
    private Scanner scanner;
    private TaskList taskList;

    public Duke() {
        scanner = new Scanner(System.in);
        taskList = new TaskList();
    }

    private Task parseTaskInput(String input) {
        String[] words = input.split(" ");
        String taskType = words[0].toLowerCase();

        String taskDescription = input.substring(taskType.length()).trim();

        switch (taskType) {
            case "add":
                return new Add(taskDescription);
            case "todo":
                return new ToDo(taskDescription);
            case "deadline":
                String[] deadlineParts = taskDescription.split("/by", 2);
                if (deadlineParts.length == 2) {
                    return new DeadLine(deadlineParts[0].trim(), deadlineParts[1].trim());
                }
                break;
            case "event":
                String[] eventParts = taskDescription.split("/from", 2);
                if (eventParts.length == 2) {
                    String[] toParts = eventParts[1].split("/to", 2);
                    if (toParts.length == 2) {
                        return new Event(eventParts[0].trim(), toParts[0].trim(), toParts[1].trim());
                    }
                }
                break;
        }
        return null;
    }

    public void start() {
        System.out.println("Hello from\n" + "Bloooooooop");
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm BloopBot");
        System.out.println(" What can I do for you?");
        System.out.println("\n____________________________________________________________");

        boolean isEcho = true;


        while (isEcho) {
            String strInput = scanner.nextLine();
            System.out.println("____________________________________________________________");

            String[] words = strInput.split(" ");
            String firstWord = words[0].toLowerCase();

            if (firstWord.equals("bye")) {
                isEcho = false;
                System.out.println(" Bye. Hope to see you again soon!");
            } else if (firstWord.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                taskList.displayTasks();
            } else if (firstWord.equals("mark")){
                int taskIndex = Integer.parseInt(words[1]) - 1;
                if (taskList.isMarked(taskIndex)) {
                    System.out.println("Task already marked");
                } else {
                    taskList.doneAndDusted(taskIndex);
                }
            } else if (firstWord.equals("unmark")) {
                int taskIndex = Integer.parseInt(words[1]) - 1;
                if (taskList.isMarked(taskIndex)) {
                    taskList.notDoneNotDusted(taskIndex);
                } else {
                    System.out.println("Task already unmarked");
                }
            } else if (firstWord.equals("add") || firstWord.equals("todo") ||
                    firstWord.equals("deadline") || firstWord.equals("event")){
                Task newTasks = parseTaskInput(strInput);
                if (newTasks != null) {
                    taskList.addTask(newTasks);
                } else {
                    System.out.println("Invalid command or task input.");
                }
            } else {
                System.out.println(" Echo!! " + strInput);
            }
            System.out.println("\n____________________________________________________________");
        }
    }

    public static void main(String[] args) {
        Duke bloopBot = new Duke();
        bloopBot.start();
    }
}