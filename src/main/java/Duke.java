import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Duke {

    private boolean isRunning = true;
    private final ArrayList<Task> tasks = new ArrayList<>();
    private int numOfTasks = 0;
    private int numOfCompletedTasks = 0;
    private int consecInvalidInputCount = 0;
    private final String name;
    public enum Command {
        TASK, TODO, DEADLINE, EVENT, LIST, MARK, UNMARK, DELETE, COMMANDS, BYE
    }

    public Duke() {
        this.name = "Leon";
        this.run();
    }

    public void run() {
        this.printSelfIntroduction();
        Scanner sc = new Scanner(System.in);
        while (this.isRunning) {
            if (this.consecInvalidInputCount >= 10) {
                break;
            } else if (this.consecInvalidInputCount == 8) {
                System.out.println("If you keep giving me nonsense, I'm leaving!");
            }
            String msg = sc.nextLine();
            this.readInput(msg);
        }
        this.exit();
    }

    public void printHorizontalLine() {
        for (int i = 0; i < 20; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    public void printCommands() {
        System.out.printf("\033[3mtask\033[0m - Create a new task%n");
        System.out.printf("\033[3mtodo\033[0m - Create a new todo%n");
        System.out.printf("\033[3mdeadline\033[0m - Create a new deadline%n");
        System.out.printf("\033[3mevent\033[0m - Create a new event%n");
        System.out.printf("\033[3mlist\033[0m - View your current tasks and completion status%n");
        System.out.printf("\033[3mmark\033[0m - Mark a task as complete%n");
        System.out.printf("\033[3munmark\033[0m - Mark a task as incomplete%n");
        System.out.printf("\033[3mdelete\033[0m - Delete a task%n");
        System.out.printf("\033[3mbye\033[0m - Exit the program%n");
        this.consecInvalidInputCount = 0;
        printHorizontalLine();
    }

    public void printSelfIntroduction() {
        System.out.printf("I'm %s. Make it quick, thanks.%n", this.name);
        System.out.println("Anyway, I only support the following commands:" + "\n");
        printCommands();
    }

    public void printEndOfOperation() {
        System.out.println();
        System.out.printf("Anything else you want me to do?%n");
        System.out.printf("Just so you know, you can input \033[3mcommands\033[0m " +
                "to view the commands again.%n");
        this.consecInvalidInputCount = 0;
        printHorizontalLine();
    }

    public boolean checkValidTask(String details) {
        return !details.isEmpty();
    }

    public boolean checkDuplicates(String details) {
        for (Task t : tasks) {
            if (details.equals(t.getDetails())) {
                return true;
            }
        }
        return false;
    }

    public void readInput(String message) {
        try {
            executeCommand(Command.valueOf(message.toUpperCase()));
        }
        catch (IllegalArgumentException e) {
            System.out.printf("I'm just a robot!%n" +
                    "I don't understand what %s is!%n", message);
            this.consecInvalidInputCount++;
            printHorizontalLine();
        }
    }

    public void executeCommand(Command command) {
        switch (command) {
            case TASK:
                this.createTask();
                break;
            case TODO:
                this.createToDo();
                break;
            case DEADLINE:
                this.createDeadline();
                break;
            case EVENT:
                this.createEvent();
                break;
            case LIST:
                this.list();
                break;
            case MARK:
                this.markAsComplete();
                break;
            case UNMARK:
                this.markAsIncomplete();
                break;
            case DELETE:
                this.deleteTask();
                break;
            case COMMANDS:
                this.printCommands();
                break;
            case BYE:
                this.isRunning = false;
                break;
        }
    }

    public String checkUserInput(String taskType, String input) {
        Scanner sc = new Scanner(System.in);
        System.out.printf("Input %s %s.%n", taskType, input);
        String message = sc.nextLine();
        if (!checkValidTask(message)) {
            System.out.printf("The %s %s cannot be empty.%n", taskType, input);
            printEndOfOperation();
            return null;
        } else if (checkDuplicates(message)) {
            System.out.printf("Task %s already exists.%n", message);
            printEndOfOperation();
            return null;
        }
        return message;
    }

    public void createTask() {
        String details = checkUserInput("task", "details");
        if (details != null) {
            tasks.add(new Task(details));
            numOfTasks++;
            System.out.printf("Don't expect me to %s for you!%n", details);
            printEndOfOperation();
        }
    }

    public void createToDo() {
        String details = checkUserInput("todo", "details");
        if (details != null) {
            tasks.add(new ToDo(details));
            numOfTasks++;
            System.out.printf("Stop talking to me! Go and %s!%n", details);
            printEndOfOperation();
        }
    }

    public void createDeadline() {
        String details = checkUserInput("deadline","details");
        if (details == null) {
            return;
        }
        String due = checkUserInput("deadline", "due date");
        if (due != null) {
            tasks.add(new Deadline(details, due));
            numOfTasks++;
            System.out.printf("Just saying, better %s now.%n" +
                    "Not like it's my problem if you don't.%n", details);
            printEndOfOperation();
        }
    }

    public void createEvent() {
        String details = checkUserInput("event", "details");
        if (details == null) {
            return;
        }
        String start = checkUserInput("event","start time");
        if (start == null) {
            return;
        }
        String end = checkUserInput("event", "end time");
        if (end != null) {
            tasks.add(new Event(details, start, end));
            numOfTasks++;
            System.out.printf("Wow, you have a %s at %s?%n" +
                    "Uhh, n-not like I wanna join you!%n", details, start);
            printEndOfOperation();
        }
    }

    public void list() {
        System.out.printf("You have %d tasks. (%d complete, %d incomplete)%n",
                numOfTasks, numOfCompletedTasks, numOfTasks - numOfCompletedTasks);
        for (int i = 0; i < numOfTasks; i++) {
            System.out.printf("%d. " + tasks.get(i).toString()
                    + "%n", i + 1);
        }
        if (numOfCompletedTasks == numOfTasks) {
            System.out.println("You've completed all your tasks. Good for you.");
        } else {
            System.out.println("Don't expect me to remember them for you!");
        }
        printEndOfOperation();
    }

    public void markAsComplete() {
        if (this.numOfTasks == 0) {
            System.out.println("No tasks to mark.");
            System.out.println("Please create a task first.");
            this.consecInvalidInputCount++;
            printHorizontalLine();
        } else {
            Integer taskNumber = this.launchConfirmationScreen("mark as complete");
            if (taskNumber != null) {
                Task task = tasks.get(taskNumber - 1);
                if (!task.isCompleted) {
                    task.setCompleted();
                    this.numOfCompletedTasks++;
                    System.out.printf("Task %d set as complete.%n", taskNumber);
                } else {
                    System.out.printf("Task %d is already complete.%n" +
                            "Stop wasting my time!%n", taskNumber);
                }
            }
            printEndOfOperation();
        }
    }

    public void markAsIncomplete() {
        if (this.numOfCompletedTasks == 0) {
            System.out.println("No tasks to unmark.");
            if (this.numOfTasks != 0) {
                System.out.println("You have no completed tasks.");
            } else {
                System.out.println("Please create a task first.");
            }
            this.consecInvalidInputCount++;
            printHorizontalLine();
        } else {
            Integer taskNumber = this.launchConfirmationScreen("mark as incomplete");
            if (taskNumber != null) {
                Task task = tasks.get(taskNumber - 1);
                if (task.isCompleted) {
                    task.setIncomplete();
                    this.numOfCompletedTasks--;
                    System.out.printf("Task %d set as incomplete.%n", taskNumber);
                } else {
                    System.out.printf("Task %d is already incomplete.%n" +
                            "Stop wasting my time!%n", taskNumber);
                }
            }
            printEndOfOperation();
        }
    }

    public void deleteTask() {
        if (this.numOfTasks == 0) {
            System.out.println("No tasks to delete.");
            System.out.println("Please create a task first.");
            this.consecInvalidInputCount++;
            printHorizontalLine();
        } else {
            Integer taskNumber = this.launchConfirmationScreen("delete");
            if (taskNumber != null) {
                Task task = tasks.get(taskNumber - 1);
                if (task.isCompleted) {
                    this.numOfCompletedTasks--;
                }
                tasks.remove(task);
                this.numOfTasks--;
                System.out.printf("Task %d deleted successfully.%n" +
                        "You now have %d tasks.%n", taskNumber, this.numOfTasks);
            }
            printEndOfOperation();
        }
    }

    public Integer launchConfirmationScreen(String message) {
        Scanner sc = new Scanner(System.in);
        System.out.printf("Please input the task number you wish to %s.%n",
                message);
        printHorizontalLine();
        for (int i = 0; i < numOfTasks; i++) {
            System.out.printf("%d. " + tasks.get(i).toString()
                    + "%n", i + 1);
        }
        try {
            int taskNumber = sc.nextInt();
            if (taskNumber > numOfTasks || taskNumber < 1) {
                System.out.println("Request unsuccessful. (reason: invalid task number)");
                return null;
            } else {
                return taskNumber;
            }
        }
        catch (InputMismatchException e) {
            System.out.println("Request unsuccessful. (reason: invalid input)");
            return null;
        }
    }

    public void exit() {
        if (this.consecInvalidInputCount >= 10) {
            System.out.printf("I've had enough of your nonsense!%n" +
                    "Don't let me see you again!%n");
        } else {
            System.out.println("Finally I can rest. Bye!");
        }
        printHorizontalLine();
        System.exit(0);
    }

    public static void main(String[] args) {
        new Duke();
    }
}
