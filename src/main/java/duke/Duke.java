package duke;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Scanner;

public class Duke {

    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;
    private final Parser parser;
    boolean isRunning = true;

    public Duke() throws IOException {
        this.tasks = new TaskList();
        this.ui = new Ui();
        this.storage = new Storage(tasks);
        this.parser = new Parser(this, tasks, ui);
        this.run();
    }

    public void run() throws IOException {
        storage.launchOnStart();
        try {
            storage.readTasksFromDisk("./data/tasks.txt");
        } catch (FileNotFoundException e) {
            System.out.println("File not found. Please check your directory and try again.");
            this.exit(2);
        }
        ui.printSelfIntroduction();
        Scanner sc = new Scanner(System.in);
        while (this.isRunning) {
            if (ui.getInvalidInputCount() >= 10) {
                this.exit(1);
                break;
            } else if (ui.getInvalidInputCount() == 8) {
                System.out.println("If you keep giving me nonsense, I'm leaving!");
            }
            String msg = sc.nextLine();
            parser.readInput(msg);
        }
        this.exit(0);
    }

    public void createTask() {
        String details = parser.checkTaskInput("task");
        if (details != null) {
            tasks.add(new Task(details));
            System.out.printf("Don't expect me to %s for you!%n", details);
            ui.printEndOfOperation();
        }
    }

    public void createToDo() {
        String details = parser.checkTaskInput("todo");
        if (details != null) {
            tasks.add(new ToDo(details));
            System.out.printf("Stop talking to me! Go and %s!%n", details);
            ui.printEndOfOperation();
        }
    }

    public void createDeadline() {
        String details = parser.checkTaskInput("deadline");
        if (details == null) {
            return;
        }
        LocalDate dueDate = parser.checkDateInput("deadline", "due");
        if (dueDate == null) {
            return;
        }
        LocalTime dueTime = parser.checkTimeInput("deadline", "due");
        if (dueTime == null) {
            // Shouldn't reach here as creation of deadline without time input is supported.
            // Invalid input is also handled in the Parser class.
            // Default dueTime is 23:59.
            return;
        }
        LocalDateTime due = dueTime.atDate(dueDate);
        if (parser.checkStartDateTime("deadline", due)) {
            tasks.add(new Deadline(details, due));
            System.out.printf("Just saying, better %s now.%n"
                    + "Not like it's my problem if you don't.%n", details);
            ui.printEndOfOperation();
        }
    }

    public void createEvent() {
        String details = parser.checkTaskInput("event");
        if (details == null) {
            return;
        }
        LocalDate startDate = parser.checkDateInput("event", "start");
        if (startDate == null) {
            return;
        }
        LocalTime startTime = parser.checkTimeInput("event", "start");
        if (startTime == null) {
            return;
        }
        LocalDate endDate = parser.checkDateInput("event", "end");
        if (endDate == null) {
            return;
        }
        LocalTime endTime = parser.checkTimeInput("event", "end");
        if (endTime == null) {
            return;
        }
        LocalDateTime start = startTime.atDate(startDate);
        LocalDateTime end = endTime.atDate(endDate);
        if (parser.checkStartDateTime("event", end)
                && parser.checkTimeInterval("event", start, end)) {
            tasks.add(new Event(details, start, end));
            System.out.printf("Wow, you have a %s?%n Uhh, n-not like I wanna join you!%n", details);
            ui.printEndOfOperation();
        }
    }

    public void list() {
        int numOfTasks = tasks.getNumOfTasks();
        int numOfCompletedTasks = tasks.getNumOfCompletedTasks();
        System.out.printf("You have %d tasks. (%d complete, %d incomplete)%n",
                numOfTasks, numOfCompletedTasks, numOfTasks - numOfCompletedTasks);
        for (int i = 0; i < numOfTasks; i++) {
            System.out.printf("%d. " + tasks.get(i).toString() + "%n", i + 1);
        }
        if (numOfCompletedTasks == numOfTasks) {
            System.out.println("You've completed all your tasks. Good for you.");
        } else {
            System.out.println("Don't expect me to remember them for you!");
        }
        ui.printEndOfOperation();
    }

    public void markAsComplete() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks to mark.");
            System.out.println("Please create a task first.");
            ui.incrementInvalidInputs();
            ui.printHorizontalLine();
        } else {
            Integer taskNumber = parser.launchConfirmationScreen("mark as complete");
            if (taskNumber != null) {
                Task task = tasks.get(taskNumber - 1);
                if (!task.isCompleted) {
                    task.setCompleted();
                    tasks.incrementCompletedTasks();
                    System.out.printf("Task %d set as complete.%n", taskNumber);
                } else {
                    System.out.printf("Task %d is already complete.%n Stop wasting my time!%n", taskNumber);
                }
            }
            ui.printEndOfOperation();
        }
    }

    public void markAsIncomplete() {
        if (!tasks.hasCompletedTasks()) {
            System.out.println("No tasks to unmark.");
            if (!tasks.isEmpty()) {
                System.out.println("You have no completed tasks.");
            } else {
                System.out.println("Please create a task first.");
            }
            ui.incrementInvalidInputs();
            ui.printHorizontalLine();
        } else {
            Integer taskNumber = parser.launchConfirmationScreen("mark as incomplete");
            if (taskNumber != null) {
                Task task = tasks.get(taskNumber - 1);
                if (task.isCompleted) {
                    task.setIncomplete();
                    tasks.decrementCompletedTasks();
                    System.out.printf("Task %d set as incomplete.%n", taskNumber);
                } else {
                    System.out.printf("Task %d is already incomplete.%n" +
                            "Stop wasting my time!%n", taskNumber);
                }
            }
            ui.printEndOfOperation();
        }
    }

    public void deleteTask() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks to delete.");
            System.out.println("Please create a task first.");
            ui.incrementInvalidInputs();
            ui.printHorizontalLine();
        } else {
            Integer taskNumber = parser.launchConfirmationScreen("delete");
            if (taskNumber != null) {
                Task task = tasks.get(taskNumber - 1);
                tasks.remove(task);
                System.out.printf("Task %d deleted successfully.%n You now have %d tasks.%n",
                        taskNumber, tasks.getNumOfTasks());
            }
            ui.printEndOfOperation();
        }
    }

    public void exit(int status) throws IOException {
        storage.saveTasksToDisk("./data/tasks.txt", tasks);
        if (status == 1) {
            System.out.printf("I've had enough of your nonsense!%n Don't let me see you again!%n");
        } else if (status == 0) {
            System.out.println("Finally I can rest. Bye!");
        }
        ui.printHorizontalLine();
        System.exit(0);
    }

    public static void main(String[] args) throws IOException {
        new Duke();
    }
}
