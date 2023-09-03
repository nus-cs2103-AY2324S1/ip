package chatterbot.parser;

import chatterbot.storage.Storage;
import chatterbot.ui.Ui;
import chatterbot.data.*;

import java.io.IOException;
import java.util.ArrayList;

public class Parser {

    public static void evaluateCommand(String userMessage, Ui ui, ArrayList<Task> list, Storage storage, String file, TaskList taskList) {
        if (userMessage.toLowerCase().equals("bye")) {
            ui.showGoodbyeMessage();
            return;
        } else if (userMessage.toLowerCase().equals("list")) {
            ui.showTaskList();
        } else if (userMessage.startsWith("mark") && isInteger(userMessage.substring(5))) {
            String toMark = userMessage.substring(5);
            list.get(Integer.parseInt(toMark) - 1).markAsDone();
            ui.showMarked(toMark);
        } else if (userMessage.startsWith("unmark")) {
            String toUnmark = userMessage.substring(7);
            list.get(Integer.parseInt(toUnmark) - 1).markAsUndone();
            ui.showUnmarked(toUnmark);
        } else {
            if (userMessage.startsWith("deadline")) {
                try {
                    if (userMessage.length() <= 9) {
                        throw new IllegalArgumentException("No task description");
                    }
                    try {
                        storage.appendToFile(file, userMessage);
                    } catch (IOException e) {
                        System.out.println("Unable to append to file!");
                    }
                    int slashDeadline = userMessage.indexOf("/");
                    String deadlineDescription = userMessage.substring(9, slashDeadline).trim();
                    String deadlineBy = userMessage.substring(slashDeadline + 3).trim();
                    Deadline d = new Deadline(deadlineDescription, deadlineBy);
                    list.add(d);
                    ui.showAddedDeadline(d);
                } catch (IllegalArgumentException e) {
                    System.out.println("OOPS!!! Invalid input!");
                }
            } else if (userMessage.startsWith("todo")) {
                try {
                    if (userMessage.length() <= 5) {
                        throw new IllegalArgumentException("No task description");
                    }
                    try {
                        storage.appendToFile(file, userMessage);
                    } catch (IOException e) {
                        System.out.println("Unable to append to file!");
                    }
                    Todo td = new Todo(userMessage.substring(5));
                    list.add(td);
                    ui.showAddedTodo(td);
                } catch (IllegalArgumentException e) {
                    System.out.println("OOPS!!! Invalid input!");
                }
            } else if (userMessage.startsWith("event")) {
                try {
                    if (userMessage.length() <= 6) {
                        throw new IllegalArgumentException("No task description");
                    }
                    try {
                        storage.appendToFile(file, userMessage);
                    } catch (IOException e) {
                        System.out.println("Unable to append to file!");
                    }
                    String[] eventSplit = userMessage.split("/");
                    String eventDescription = eventSplit[0].substring(6);
                    String eventTo = eventSplit[1].substring(5);
                    String eventFrom = eventSplit[2].substring(3);
                    Event e = new Event(eventDescription, eventTo, eventFrom);
                    list.add(e);
                    ui.showAddedEvent(e);
                } catch (IllegalArgumentException e) {
                    System.out.println("OOPS!!! Invalid input!");
                }
            } else if (userMessage.startsWith("delete") && isInteger(userMessage.substring(7))) {
                ui.showDeleted(userMessage);
                list.remove((Integer.parseInt(userMessage.substring(7))) - 1);
                try {
                    storage.writeToFile(file, taskList.convertToString(list));
                } catch (IOException e) {
                    System.out.println("Error!");
                }
            } else {
                ui.showUnknownCommand(userMessage);
            }
        }
    }

    public static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
