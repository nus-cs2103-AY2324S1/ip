package Command;

import Exception.InvalidDateException;
import Exception.InvalidTimeException;
import Enums.Command;
import Parser.Time;
import Task.Events;
import Task.TaskList;
import Ui.Reply;

import java.util.Scanner;

public class EventCommand {
    private static Reply reply = Reply.init();
    private static TaskList tasks = TaskList.init();
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
