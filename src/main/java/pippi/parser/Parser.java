package pippi.parser;

import java.util.ArrayList;

import pippi.PippiException;
import pippi.storage.Storage;
import pippi.task.Deadline;
import pippi.task.Event;
import pippi.task.Task;
import pippi.task.ToDo;
import pippi.ui.Ui;

public class Parser {

    public static String reply(String userMessage, ArrayList<Task> tasks) {
        String output = "";
        try {
            String[] input = userMessage.split(" ", 2);
            String command = input[0];
            switch (command) {
            case "bye":
                output = Ui.wrapText("Bye. Hope to see you again soon!");
                // Pippi.returnToPokeball();
                break;
            case "todo":
                if ((input.length < 2) || input[1].trim().isEmpty()) {
                    output = Ui.wrapText("Moonblast!!! The description of a todo cannot be empty.");
                    break;
                }
                ToDo td = new ToDo(input[1]);
                tasks.add(td);
                Storage.update(tasks);
                output = Ui.wrapText("Got it. I've added this task:\n" + td.toString()
                        + "\nNow you have " + tasks.size() + " tasks in the list.");
                break;
            case "deadline":
                if (input.length < 2 || input[1].trim().isEmpty()) {
                    output = "Metronome!!! The description and due time of a deadline cannot be empty.";
                    throw new PippiException(output);
                }
                if (input[1].split("/by").length < 2) {
                    output = "Metronome!!! Due time or description is missing";
                    throw new PippiException(output);
                }
                String title = input[1].split("/by ")[0].trim();
                String due = input[1].split("/by ")[1].trim();
                if (!DateFormatter.isValidFormat(due)) {
                    output = "Please enter due in format yyyy-mm-dd";
                    throw new PippiException("Please enter due in format yyyy-mm-dd");
                }
                Deadline dl = new Deadline(title, DateFormatter.convertToLocalDate(due));
                tasks.add(dl);
                Storage.update(tasks);
                output = Ui.wrapText("Got it. I've added this task:\n" + dl.toString()
                        + "\nNow you have " + tasks.size() + " tasks in the list.");
                break;
            case "event":
                if (input.length < 2 || input[1].trim().isEmpty()) {
                    output = "Pound!!! The description and start, end time of an event cannot be empty.";
                    throw new PippiException(output);
                }
                if (input[1].split("/from").length < 2) {
                    output = "Pound!!! pippi.task.Event title or duration is missing";
                    throw new PippiException(output);
                }
                String evTitle = input[1].split("/from ")[0].trim();
                String duration = input[1].split("/from ")[1];

                if (duration.split("/to ").length < 2) {
                    output = "Pound!!! Start or end time is missing";
                    throw new PippiException(output);
                }
                String start = duration.split("/to ")[0];
                String end = duration.split("/to ")[1];

                Event event = new Event(evTitle, start, end);
                tasks.add(event);
                Storage.update(tasks);
                output = Ui.wrapText("Got it. I've added this task:\n" + event.toString()
                        + "\nNow you have " + tasks.size() + " tasks in the list.");
                break;
            case "list":
                String all = "";
                for (int i = 0; i < tasks.size(); i++) {
                    Task curr = tasks.get(i);
                    all = all + String.valueOf(i + 1) + "." + curr.toString() + "\n";
                }
                output = Ui.wrapText("Here are the tasks in your list:\n" + all);
                break;
            case "mark":
                int idx = Integer.parseInt(input[1]) - 1;
                tasks.get(idx).mark();
                output = Ui.wrapText("Nice I've marked this task as done:\n"
                        + tasks.get(idx).toString());
                Storage.update(tasks);
                break;
            case "unmark":
                int id = Integer.parseInt(input[1]) - 1;
                tasks.get(id).unmark();
                output = Ui.wrapText("OK, I've marked this task as not done yet:\n"
                        + tasks.get(id).toString());
                Storage.update(tasks);
                break;
            case "find":
                String keyword = input[1].trim();
                String found = "";
                int count = 1;
                for (int i = 0; i < tasks.size(); i++) {
                    Task curr = tasks.get(i);
                    if (curr.getDescription().contains(keyword)) {
                        found = found + String.valueOf(count) + "." + curr.toString() + "\n";
                        count++;
                    }
                }
                output = Ui.wrapText("Here are the matching tasks in your list:\n" + found);
                break;
            case "delete":
                int i = Integer.parseInt(input[1]) - 1;
                output = Ui.wrapText("Noted. I've removed this task:\n"
                        + tasks.get(i).toString()
                        + "\nNow you have " + (tasks.size() - 1) + " tasks in the list.");
                tasks.remove(i);
                Storage.update(tasks);
                break;
            default:
                output = Ui.wrapText("Amnesia!!! I'm sorry, but I don't know what that means");
            }
        } catch (PippiException e) {
            output = Ui.wrapText(e.getMessage());
        }
        return output;
    }
}
