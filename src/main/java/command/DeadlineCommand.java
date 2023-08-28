package command;

import task.TaskList;
import ui.Reply;
import parser.Time;
import task.Deadlines;
import exception.InvalidDateException;
import exception.InvalidTimeException;

import java.util.Scanner;

public class DeadlineCommand {
    private static Reply reply = Reply.init();
    private static TaskList tasks = TaskList.init();
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
