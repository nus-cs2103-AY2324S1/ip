package command;

import exception.InvalidDateException;
import exception.InvalidTimeException;

import enums.Command;

import parser.Time;

import task.Events;
import task.TaskList;

import ui.Reply;

import java.util.Scanner;

/**
 * Class for Event command with its static implementation of its processes
 */
public class EventCommand {
    private static Reply reply = Reply.init();
    private static TaskList tasks = TaskList.init();

    /**
     * Main process of Event Command.
     * Prompts user to enter the task, start and end date/time before validating and adding it to the list.
     * Returns to homepage otherwise.
     */
    public static void start() {

        Scanner scanner = new Scanner(System.in);

        reply.printDialog("So you want to add a event task. Tell me what's the task.");
        String desc = scanner.nextLine();

        reply.printDialog("Now indicate the start date.");
        String from = scanner.nextLine();
        try {
            from = Time.formatDate(from);
        } catch (InvalidDateException e) {
            reply.printDialog(e.toString());
            return;
        }

        reply.printDialog("Indicate a start time in ranging from 0000 - 2359. You may enter 'Skip' to not indicate a time");
        String fromTime = scanner.nextLine();
        if (!fromTime.toLowerCase().equals(Command.SKIP.getCommand())) {
            try {
                from = Time.formatTime(from, fromTime);
            } catch (InvalidTimeException | InvalidDateException e) {
                reply.printDialog(e.toString());
                return;
            } catch (NumberFormatException e) {
                reply.printDialog("Non-numerical characters detected. Please enter numbers only. Returning to homepage...");
                return;
            }
        }

        reply.printDialog("Now indicate the end date.");
        String to = scanner.nextLine();
        try {
            to = Time.formatDate(to);
        } catch (InvalidDateException e) {
            reply.printDialog(e.toString());
            return;
        }

        reply.printDialog("Indicate a start time in ranging from 0000 - 2359. You may enter 'Skip' to not indicate a time");
        String toTime = scanner.nextLine();
        if (!toTime.toLowerCase().equals(Command.SKIP.getCommand())) {
            try {
                to = Time.formatTime(to, toTime);
            } catch (InvalidTimeException | InvalidDateException e) {
                reply.printDialog(e.toString());
                return;
            } catch (NumberFormatException e) {
                reply.printDialog("Non-numerical characters detected. Please enter numbers only. Returning to homepage...");
                return;
            }
        }

        tasks.addTask(new Events(desc, from, to));
    }
}
