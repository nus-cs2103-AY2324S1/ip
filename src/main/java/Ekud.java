import java.util.Scanner;

import command.*;
import task.*;

public class Ekud {
    public Store store = new Store();

    public void processList(ListCommand command) {
        if (!store.hasTasks()) {
            System.out.println("No tasks yet. Add one!");
            return;
        }

        System.out.println("Here are the tasks in your list:");

        for (int taskId = 1; taskId <= store.getTaskCount(); taskId++) {
            // Add padding to align single-digit numbers if we'll render
            // two-digit numbers later on.
            if (store.getTaskCount() > 9 && taskId < 10) {
                System.out.print(" ");
            }
            System.out.print(taskId);
            Task task = store.getTask(taskId);
            System.out.println(". " + task.toString());
        }
    }

    public void processMark(MarkCommand command) {
        int taskId = command.getTaskId();

        if (taskId < 1 || taskId > store.getTaskCount()) {
            System.out.println("Invalid task number provided.");
            return;
        }

        Task task = store.getTask(taskId);
        task.mark();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("   " + task);
    }

    public void processUnmark(UnmarkCommand command) {
        int taskId = command.getTaskId();

        if (taskId < 1 || taskId > store.getTaskCount()) {
            System.out.println("Invalid task number provided.");
            return;
        }

        Task task = store.getTask(taskId);
        task.unmark();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("   " + task);
    }

    public void processEvent(EventCommand command) {
        Task task = new EventTask(command.getTitle(), command.getFrom(), command.getTo());
        store.addTask(task);
        System.out.println("Got it. I've added this task:");
        System.out.println("   " + task);
        System.out.println("Now you have " + store.getTaskCount() + " tasks in the list.");
    }

    public void processDeadline(DeadlineCommand command) {
        Task task = new DeadlineTask(command.getTitle(), command.getBy());
        store.addTask(task);
        System.out.println("Got it. I've added this task:");
        System.out.println("   " + task);
        System.out.println("Now you have " + store.getTaskCount() + " tasks in the list.");
    }

    public void processTodo(TodoCommand command) {
        Task task = new TodoTask(command.getTitle());
        store.addTask(task);
        System.out.println("Got it. I've added this task:");
        System.out.println("   " + task);
        System.out.println("Now you have " + store.getTaskCount() + " tasks in the list.");
    }

    public boolean processLine(String line) {
        Command command;
        try {
            command = Command.parse(line);
        } catch (ParseException error) {
            System.out.println(error.getMessage());
            return false;
        }

        if (command instanceof ByeCommand) {
            return true;
        }

        if (command instanceof ListCommand) {
            processList((ListCommand) command);
        } else if (command instanceof EventCommand) {
            processEvent((EventCommand) command);
        } else if (command instanceof DeadlineCommand) {
            processDeadline((DeadlineCommand) command);
        } else if (command instanceof TodoCommand) {
            processTodo((TodoCommand) command);
        } else if (command instanceof MarkCommand) {
            processMark((MarkCommand) command);
        } else if (command instanceof UnmarkCommand) {
            processUnmark((UnmarkCommand) command);
        }

        return false;
    }

    public static void main(String[] args) {
        Ekud program = new Ekud();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Hello! I'm Ekud!");
        System.out.println("What can I do for you?");

        while (true) {
            System.out.print("> ");

            String line = scanner.nextLine().trim();
            if (line.isEmpty()) {
                continue;
            }

            if (program.processLine(line)) {
                break;
            }
        }

        System.out.println("Bye. Hope to see you again soon!");

        scanner.close();
    }
}
