package command;

import exception.InvalidDateException;
import exception.InvalidTimeException;

import task.TaskList;
import task.Deadlines;

import ui.Reply;

import parser.Time;

import java.util.Scanner;

/**
 * Class for Deadline command with its static implementation of its processes
 */
public class DeadlineCommand {
    private static Reply reply = Reply.init();
    private static TaskList tasks = TaskList.init();

    /**
     * Main process of Deadline Command.
     * Prompts user to enter the task and deadline date and time before validating and adding it to the list.
     * Returns to homepage otherwise.
     */
    public static void start() {

        Scanner scanner = new Scanner(System.in);

        reply.printDialog("So you want to add a task with deadline. Tell me what's the task.");
        String desc = scanner.nextLine();

        reply.printDialog("Now indicate the deadline date.");
        String date = scanner.nextLine();
        try {
            date = Time.formatDate(date);
        } catch (InvalidDateException e) {
            reply.printDialog(e.toString());
            return;
        }

        reply.printDialog("Indicate a start time in ranging from 0000 - 2359. You may enter 'Skip' to not indicate a time");
        String time = scanner.nextLine();
        if (!time.toLowerCase().equals(enums.Command.SKIP.getCommand())) {
            try {
                date = Time.formatTime(date, time);
            } catch (InvalidTimeException | InvalidDateException e) {
                reply.printDialog(e.toString());
                return;
            } catch (NumberFormatException e) {
                reply.printDialog("Non-numerical characters detected. Please enter numbers only. Returning to homepage...");
                return;
            }
        }

        tasks.addTask(new Deadlines(desc, date));
    }
}
