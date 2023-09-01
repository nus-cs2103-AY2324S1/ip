import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
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
    public enum TaskType {
        TASK, TODO, DEADLINE, EVENT
    }
    public enum Command {
        TASK, TODO, DEADLINE, EVENT, LIST, MARK, UNMARK, DELETE, COMMANDS, BYE
    }

    public Duke() throws IOException {
        this.name = "Meg";
        this.run();
    }

    public void run() throws IOException {
        this.launchOnStart();
        try {
            this.readTasksFromDisk("./data/tasks.txt");
        } catch (FileNotFoundException e) {
            System.out.println("File not found. Please check your directory and try again.");
            this.exit(2);
        }
        this.printSelfIntroduction();
        Scanner sc = new Scanner(System.in);
        while (this.isRunning) {
            if (this.consecInvalidInputCount >= 10) {
                this.exit(1);
                break;
            } else if (this.consecInvalidInputCount == 8) {
                System.out.println("If you keep giving me nonsense, I'm leaving!");
            }
            String msg = sc.nextLine();
            this.readInput(msg);
        }
        this.exit(0);
    }

    /**
     * Greets the user and creates the requisite folder and text file, if required.
     */
    public void launchOnStart() {
        File f = new File("./data");
        if (f.mkdirs()) {
            System.out.printf("Welcome, new user! I'm %s!%n" +
                    "Hope you have a great time!%n", this.name);
        } else {
            System.out.println("Welcome back, friend!");
        }
        try {
            File file = new File("./data/tasks.txt");
            if (file.createNewFile()) {
                System.out.printf("Your tasks will be automatically saved.%n" + "\n" +
                        "Feeling overwhelmed in school and having trouble remembering commitments?%n" +
                        "I'm here to help!%n");
            } else {
                System.out.println("Tasks from previous session loaded successfully!");
            }
        } catch (IOException e) {
            System.out.println("Due to technical issues, I'm only available in guest mode.%n" +
                    "I sincerely apologise to the inconvenience caused.");
        } finally {
            printHorizontalLine();
        }
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
        System.out.println("I support the following commands:" + "\n");
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

    public void readTasksFromDisk(String filePath) throws FileNotFoundException,
            IllegalArgumentException {
        File f = new File(filePath);
        Scanner sc = new Scanner(f);
        while (sc.hasNext()) {
            String[] args = sc.nextLine().split("-", -1);
            TaskType type;
            LocalDateTime due = null;
            LocalDateTime start = null;
            LocalDateTime end = null;
            try {
                type = TaskType.valueOf(args[0].toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Task not found");
                return;
            }
            String details = args[1];
            boolean isCompleted = args[2].equals("Y");
            try {
                if (type == TaskType.DEADLINE) {
                    due = LocalDateTime.parse(args[3]);
                } else if (type == TaskType.EVENT) {
                    start = LocalDateTime.parse(args[3]);
                    end = LocalDateTime.parse(args[4]);
                }
            } catch (DateTimeParseException e) {
                System.out.println("Could not retrieve date and/or time");
                return;
            }
            switch (type) {
                case TASK:
                    Task t = new Task(details, isCompleted);
                    tasks.add(t);
                    this.numOfTasks++;
                    if (t.isCompleted) {
                        this.numOfCompletedTasks++;
                    }
                    break;
                case TODO:
                    ToDo todo = new ToDo(details, isCompleted);
                    tasks.add(todo);
                    this.numOfTasks++;
                    if (todo.isCompleted) {
                        this.numOfCompletedTasks++;
                    }
                    break;
                case DEADLINE:
                    Deadline d = new Deadline(details, isCompleted, due);
                    tasks.add(d);
                    this.numOfTasks++;
                    if (d.isCompleted) {
                        this.numOfCompletedTasks++;
                    }
                    break;
                case EVENT:
                    Event e = new Event(details, isCompleted, start, end);
                    tasks.add(e);
                    this.numOfTasks++;
                    if (e.isCompleted) {
                        this.numOfCompletedTasks++;
                    }
                    break;
                default:
                    // Shouldn't reach here
                    break;
            }
        }
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
            default:
                // Shouldn't reach here
                // Input errors should already be caught in the readInput() method.
                break;
        }
    }

    public String checkUserInput(String taskType, String input) {
        Scanner sc = new Scanner(System.in);
        System.out.printf("Input %s %s.%n", taskType, input);
        String message = sc.nextLine();
        try {
            if (!checkValidTask(message)) {
                if (taskType.equals("deadline") &&
                        input.equals("due time (Optional, Required format: HH:MM)")) {
                    return "23:59";
                } else {
                    System.out.printf("The %s %s cannot be empty.%n", taskType, input);
                    printEndOfOperation();
                    return null;
                }
            } else if (checkDuplicates(message)) {
                System.out.printf("Task %s already exists.%n", message);
                printEndOfOperation();
                return null;
            }
        } catch (DateTimeParseException e) {
            System.out.printf("Invalid date or time format. Please try again.%n");
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
        LocalDate dueDate = LocalDate.parse(checkUserInput("deadline",
                "due date (Required format: YYYY-MM-DD)"));
        LocalTime dueTime = LocalTime.parse(checkUserInput("deadline",
                "due time (Optional, Required format: HH:MM)"));
        LocalDateTime due = dueTime.atDate(dueDate);
        tasks.add(new Deadline(details, due));
        numOfTasks++;
        System.out.printf("Just saying, better %s now.%n" +
                "Not like it's my problem if you don't.%n", details);
        printEndOfOperation();
    }

    public void createEvent() {
        String details = checkUserInput("event", "details");
        if (details == null) {
            return;
        }
        LocalDate startDate = LocalDate.parse(checkUserInput("event",
                "start date (Required format: YYYY-MM-DD)"));
        LocalTime startTime = LocalTime.parse(checkUserInput("event",
                "start time (Required format: HH:MM)"));
        LocalDate endDate = LocalDate.parse(checkUserInput("event",
                "end date (Required format: YYYY-MM-DD)"));
        LocalTime endTime = LocalTime.parse(checkUserInput("event",
                "end time (Required format: HH:MM)"));
        LocalDateTime start = startTime.atDate(startDate);
        LocalDateTime end = endTime.atDate(endDate);
        tasks.add(new Event(details, start, end));
        numOfTasks++;
        System.out.printf("Wow, you have a %s at %s?%n" +
                "Uhh, n-not like I wanna join you!%n", details, start);
        printEndOfOperation();
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
                System.out.println("Request unsuccessful (reason: invalid task number)");
                return null;
            } else {
                return taskNumber;
            }
        }
        catch (InputMismatchException e) {
            System.out.println("Request unsuccessful (reason: invalid input)");
            return null;
        }
    }

    public void saveTasksToDisk(String filePath) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        BufferedWriter bw = new BufferedWriter(fw);
        try {
            for (Task t : tasks) {
                if (t instanceof ToDo) {
                    bw.write(String.format("ToDo-%s-%c",
                            t.getDetails(),
                            t.isCompleted ? 'Y' : 'N'));
                } else if (t instanceof Deadline) {
                    bw.write(String.format("Deadline-%s-%c-%s",
                            t.getDetails(),
                            t.isCompleted ? 'Y' : 'N',
                            ((Deadline) t).due));
                } else if (t instanceof Event) {
                    bw.write(String.format("Event-%s-%c-%s-%s",
                            t.getDetails(),
                            t.isCompleted ? 'Y' : 'N',
                            ((Event) t).start,
                            ((Event) t).end));
                } else {
                    bw.write(String.format("Task-%s-%c",
                            t.getDetails(),
                            t.isCompleted ? 'Y' : 'N'));
                }
                bw.newLine();
            }
            bw.flush();
            bw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    public void exit(int status) throws IOException {
        this.saveTasksToDisk("./data/tasks.txt");
        if (status == 1) {
            System.out.printf("I've had enough of your nonsense!%n" +
                    "Don't let me see you again!%n");
        } else if (status == 0) {
            System.out.println("Finally I can rest. Bye!");
        }
        printHorizontalLine();
        System.exit(0);
    }

    public static void main(String[] args) throws IOException {
        new Duke();
    }
}
