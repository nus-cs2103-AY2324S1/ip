package duke;

import java.io.IOException;
import java.time.LocalDateTime;

import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Parser {
    public static int parse(String text, UI ui, TaskList list, Storage storage) throws DukeException {


            if (text.length() > 3 && text.substring(0, 4).equals("list")) {
                ui.printline();

                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i) == null) {
                        break;
                    } else {
                        ui.sendMessage(String.format("%d. [%s] [%s] %s", i + 1, list.get(i).tag,
                                list.get(i).getStatusIcon(), list.get(i)));
                    }
                }
                ui.printline();
            } else if (text.startsWith("unmark")) {
                try {
                    storage.appendToFile(text + "\n");
                } catch (IOException e) {
                    ui.sendMessage("Something went wrong: " + e.getMessage());
                }
                String[] splitText = text.split(" ");
                int numToUnmark = Integer.parseInt(splitText[1]) - 1;
                list.get(numToUnmark).markAsIncomplete();

                ui.printline();
                ui.sendMessage("Alright! I'll uncheck this task for you: ");
                ui.sendMessage(String.format("\t [%s] [%s] %s", list.get(numToUnmark).tag,
                        list.get(numToUnmark).getStatusIcon(), list.get(numToUnmark)));
                ui.printline();

            } else if (text.startsWith("mark")) {
                try {
                    storage.appendToFile(text + "\n");
                } catch (IOException e) {
                    ui.sendMessage("Something went wrong: " + e.getMessage());
                }
                String[] splitText = text.split(" ");
                int numToMark = Integer.parseInt(splitText[1]) - 1;
                list.get(numToMark).markAsComplete();

                ui.printline();
                ui.sendMessage("Alright! I'll check this task as complete for you: ");
                ui.sendMessage(String.format("\t [%s] [%s] %s", list.get(numToMark).tag,
                        list.get(numToMark).getStatusIcon(), list.get(numToMark)));
                ui.printline();

            } else if (text.equals("bye")) {
                ui.printline();
                ui.sendMessage("Goodbye. Hope to be of service again soon!");
                ui.printline();
                return -1;


            } else if (text.startsWith("todo")) {
                String description = text.substring(4);
                if (description.isEmpty()) {
                    throw new DukeException("I apologise, sir. " +
                            "But the description of todo cannot be empty");
                } else {
                    try {
                        storage.appendToFile(text + "\n");
                    } catch (IOException e) {
                        ui.sendMessage("Something went wrong: " + e.getMessage());
                    }
                    Todo todo = new Todo(description.trim());
                    list.add(todo);

                    ui.printline();
                    ui.sendMessage("Noted Sir. I've added this task to your list: ");
                    ui.sendMessage(String.format("\t [%s] [%s] %s", todo.tag,
                            todo.getStatusIcon(), todo.toString()));
                    ui.sendMessage(String.format("As of now, you have %d tasks on the agenda.",
                            list.size()));
                    ui.printline();
                }
            } else if (text.startsWith("deadline")) {
                String[] splitText = text.split("/", 2);

                String description = splitText[0].substring(8);
                if (description.isEmpty()) {
                    throw new DukeException("I apologise, sir. " +
                            "But the description and deadline cannot be empty");
                } else {

                    String deadlineText = splitText[1].substring(3);
                    try {
                        LocalDateTime deadline = LocalDateTime.parse(deadlineText);
                        Deadline dl = new Deadline(description.trim(), deadline);
                        list.add(dl);

                        ui.printline();
                        ui.sendMessage("Noted Sir. I've added this task to your list: ");
                        ui.sendMessage(String.format("\t [%s] [%s] %s", dl.tag, dl.getStatusIcon(),
                                dl.toString()));
                        ui.sendMessage(String.format("As of now, you have %d tasks on the agenda.",
                                list.size()));
                        ui.printline();
                    } catch (DateTimeParseException e) {
                        throw new DukeException("Invalid Date Format: should be YYYY-MM-DDTHH:MM:SS. " +
                                "Example: 2023-12-12T06:30:00");
                    }
                    try {
                        storage.appendToFile(text + "\n");
                    } catch (IOException e) {
                        System.out.println("Something went wrong: " + e.getMessage());
                    }
                }
            } else if (text.startsWith("event")) {
                String[] splitText = text.split("/");
                String description = splitText[0].substring(5);
                if (description.isEmpty()) {
                    throw new DukeException("I apologise, sir. " +
                            "But the description, start and end cannot be empty");

                } else {
                    String startText = splitText[1].trim().substring(5);
                    String endText = splitText[2].trim().substring(3);

                    try {
                        LocalDateTime start = LocalDateTime.parse(startText);
                        LocalDateTime end = LocalDateTime.parse(endText);
                        Event event = new Event(description.trim(), start, end);
                        list.add(event);

                        ui.printline();
                        ui.sendMessage("Noted Sir. I've added this task to your list: ");
                        ui.sendMessage(String.format("\t [%s] [%s] %s", event.tag,
                                event.getStatusIcon(),
                                event.toString()));
                        ui.sendMessage(String.format("As of now, you have %d tasks on the agenda.",
                                list.size()));
                        ui.printline();
                    } catch (DateTimeParseException e) {
                        throw new DukeException("Invalid Date Format: should be YYYY-MM-DDTHH:MM:SS. " +
                                "Example: 2023-12-12T06:30:00");
                    }
                    try {
                        storage.appendToFile(text + "\n");
                    } catch (IOException e) {
                        System.out.println("Something went wrong: " + e.getMessage());
                    }
                }

            } else if (text.startsWith("delete")) {
                try {
                    storage.appendToFile(text + "\n");
                } catch (IOException e) {
                    ui.sendMessage("Something went wrong: " + e.getMessage());
                }
                String[] splitText = text.split(" ");
                int numToDelete = Integer.parseInt(splitText[1]) - 1;

                ui.printline();
                ui.sendMessage("Alright Sir, I have removed this task from the list for you.");
                ui.sendMessage(String.format("\t [%s] [%s] %s", list.get(numToDelete).tag,
                        list.get(numToDelete).getStatusIcon(), list.get(numToDelete).toString()));
                list.remove(numToDelete);
                ui.sendMessage(String.format("Now you have %d tasks left.", list.size()));
            } else if (text.startsWith("find")) {
                String search = text.substring(5);
                if (search.isEmpty()) {
                    throw new DukeException("I apologise, sir. " +
                            "But the description of todo cannot be empty");
                } else if (list.size() == 0) {
                    throw new DukeException("I apologise, sir." +
                            "But your list is empty.");
                } else {
                    boolean hasSearch = false;
                    boolean isFirst = true;
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i).toString().contains(search)) {
                            hasSearch = true;
                            if (hasSearch == true && isFirst == true) {
                                ui.printline();
                                ui.sendMessage("Here are the matching tasks in your list:");
                                ui.sendMessage(String.format("\t [%s] [%s] %s", list.get(i).tag,
                                        list.get(i).getStatusIcon(), list.get(i).toString()));
                                isFirst = false;
                            } else if (hasSearch == true && isFirst == false) {
                                ui.sendMessage(String.format("\t [%s] [%s] %s", list.get(i).tag,
                                        list.get(i).getStatusIcon(), list.get(i).toString()));
                            }
                        }
                    }

                    if (hasSearch == false) {
                        throw new DukeException("I apologise sir." +
                                "But " + search + " cannot be found in your list.");
                    }
                    ui.printline();
                }

            } else {
                throw new DukeException("I apologise, sir. But I do not understand what you mean.");
            } return 1;
        }
    }



