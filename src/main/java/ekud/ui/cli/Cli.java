package ekud.ui.cli;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import ekud.command.Command;
import ekud.error.EkudException;
import ekud.state.Task;
import ekud.state.TaskList;
import ekud.ui.Ui;
import ekud.util.Pair;

/**
 * Represents a command-line user interface to the ekud program.
 */
public final class Cli extends Ui {
    private final Scanner in;
    private final PrintStream out;
    private final PrintStream err;

    /**
     * Creates a new command-line user interface with the given input, output and
     * error streams.
     * 
     * @param in  The input stream to read user commands from.
     * @param out The output stream to print successful results.
     * @param err The error stream to print any error messages.
     */
    public Cli(InputStream in, OutputStream out, OutputStream err) {
        this.in = new Scanner(in);
        this.out = new PrintStream(out);
        this.err = new PrintStream(err);
    }

    public Pair<Command, Boolean> readCommand() {
        out.print("> ");

        String line;
        try {
            line = in.nextLine().trim();
        } catch (NoSuchElementException error) {
            out.println();
            return new Pair<>(null, false);
        }

        if (line.isEmpty()) {
            return new Pair<>(null, true);
        }

        Command command = Parser.parseCommand(line);
        return new Pair<>(command, true);
    }

    public void showGreeting() {
        out.println("Hello! I'm Ekud!");
        out.println("What can I do for you?");
    }

    public void showFarewell() {
        out.println("Bye. Hope to see you again soon!");
    }

    public void showTaskList(TaskList taskList) {
        if (!taskList.hasTasks()) {
            out.println("No tasks yet. Add one!");
            return;
        }

        out.println("Here are the tasks in your list:");
        List<Task> tasks = taskList.asList();
        for (int taskId = 1; taskId <= tasks.size(); taskId++) {
            // Add padding to align single-digit numbers if we'll render two-digit numbers
            // later on.
            if (tasks.size() > 9 && taskId < 10) {
                out.print(" ");
            }
            out.print(taskId);
            Task task = taskList.getTask(taskId);
            out.println(". " + task.toString());
        }
    }

    public void showTaskCount(TaskList taskList) {
        out.println("Now you have " + taskList.asList().size() + " tasks in the list.");
    }

    public void showTaskAdded(Task task) {
        out.println("Got it. I've added this task:");
        out.println("   " + task);
    }

    public void showTaskMarked(Task task) {
        out.println("Nice! I've marked this task as done:");
        out.println("   " + task);
    }

    public void showTaskUnmarked(Task task) {
        out.println("OK, I've marked this task as not done yet:");
        out.println("   " + task);
    }

    public void showTaskRemoved(Task task) {
        out.println("Noted. I've removed this task:");
        out.println("   " + task);
    }

    public void showFoundTasks(List<Task> foundTasks) {
        if (foundTasks.isEmpty()) {
            out.println("No tasks found.");
            return;
        }

        out.println("Here are the matching tasks in your list:");
        for (int taskId = 1; taskId <= foundTasks.size(); taskId++) {
            // Add padding to align single-digit numbers if we'll render two-digit numbers
            // later on.
            if (foundTasks.size() > 9 && taskId < 10) {
                out.print(" ");
            }
            out.print(taskId);
            Task task = foundTasks.get(taskId - 1);
            out.println(". " + task.toString());
        }
    }

    public void showError(EkudException error) {
        err.println("☹ OOPS!!! " + error.getMessage());
    }
}
