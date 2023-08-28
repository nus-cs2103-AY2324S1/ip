package duke;

import java.io.IOException;
import java.time.LocalDateTime;

import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Parser {

    public static void parse(String text, UI ui, TaskList list, Storage storage) throws DukeException{

                if (text.length() > 3 && text.substring(0, 4).equals("list")) {
                    ui.printline();

                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i) == null) {
                            break;
                        } else {
                            System.out.println(String.format("%d. [%s] [%s] %s", i + 1, list.get(i).tag,
                                    list.get(i).getStatusIcon(), list.get(i)));
                        }
                    }
                    ui.printline();

                } else if (text.startsWith("unmark")) {
                    try {
                        storage.appendToFile(text + "\n");
                    } catch (IOException e) {
                        System.out.println("Something went wrong: " + e.getMessage());
                    }
                    String[] splitText = text.split(" ");
                    int numToUnmark = Integer.parseInt(splitText[1]) - 1;
                    list.get(numToUnmark).markAsIncomplete();

                    ui.printline();
                    System.out.println("Alright! I'll uncheck this task for you: ");
                    System.out.println(String.format("\t [%s] [%s] %s", list.get(numToUnmark).tag,
                            list.get(numToUnmark).getStatusIcon(), list.get(numToUnmark)));
                    ui.printline();

                } else if (text.startsWith("mark")) {
                    try {
                        storage.appendToFile(text + "\n");
                    } catch (IOException e) {
                        System.out.println("Something went wrong: " + e.getMessage());
                    }
                    String[] splitText = text.split(" ");
                    int numToMark = Integer.parseInt(splitText[1]) - 1;
                    list.get(numToMark).markAsComplete();

                    ui.printline();
                    System.out.println("Alright! I'll check this task as complete for you: ");
                    System.out.println(String.format("\t [%s] [%s] %s", list.get(numToMark).tag,
                            list.get(numToMark).getStatusIcon(), list.get(numToMark)));
                    ui.printline();

                } else if (text.equals("bye")) {
                    ui.printline();
                    System.out.println("Goodbye. Hope to be of service again soon!");
                    ui.printline();

                } else if (text.startsWith("todo")) {
                    String description = text.substring(4);
                    if (description.isEmpty()) {
                        throw new DukeException("I apologise, sir. " +
                                "But the description of todo cannot be empty");
                    } else {
                        try {
                            storage.appendToFile( text + "\n");
                        } catch (IOException e) {
                            System.out.println("Something went wrong: " + e.getMessage());
                        }
                        Todo todo = new Todo(description.trim());
                        list.add(todo);

                        ui.printline();
                        System.out.println("Noted Sir. I've added this task to your list: ");
                        System.out.println(String.format("\t [%s] [%s] %s", todo.tag,
                                todo.getStatusIcon(), todo.toString()));
                        System.out.println(String.format("As of now, you have %d tasks on the agenda.",
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
                        try {
                            storage.appendToFile( text + "\n");
                        } catch (IOException e) {
                            System.out.println("Something went wrong: " + e.getMessage());
                        }

                        String deadlineText = splitText[1].substring(3);
                        try{
                            LocalDateTime deadline = LocalDateTime.parse(deadlineText);
                            Deadline dl = new Deadline(description.trim(), deadline);
                            list.add(dl);
                            ui.printline();
                            System.out.println("Noted Sir. I've added this task to your list: ");
                            System.out.println(String.format("\t [%s] [%s] %s", dl.tag, dl.getStatusIcon(),
                                    dl.toString()));
                            System.out.println(String.format("As of now, you have %d tasks on the agenda.",
                                    list.size()));
                            ui.printline();
                        } catch (DateTimeParseException e) {
                        throw new DukeException("Invalid Date Format: should be YYYY-MM-DDTTime. " +
                                "Example: 2023-12-12T06:30:00");
                    }


                    }
                } else if (text.startsWith("event")) {
                    String[] splitText = text.split("/");
                    String description = splitText[0].substring(5);
                    if (description.isEmpty()) {
                        throw new DukeException("I apologise, sir. " +
                                "But the description, start and end cannot be empty");

                    } else {
                        try {
                            storage.appendToFile( text + "\n");
                        } catch (IOException e) {
                            System.out.println("Something went wrong: " + e.getMessage());
                        }
                        String startText = splitText[1].trim().substring(5);
                        String endText = splitText[2].trim().substring(3);

                        try {
                            LocalDateTime start = LocalDateTime.parse(startText);
                            LocalDateTime end = LocalDateTime.parse(endText);
                            Event event = new Event(description.trim(), start, end);
                            list.add(event);


                            ui.printline();
                            System.out.println("Noted Sir. I've added this task to your list: ");
                            System.out.println(String.format("\t [%s] [%s] %s", event.tag,
                                    event.getStatusIcon(),
                                    event.toString()));
                            System.out.println(String.format("As of now, you have %d tasks on the agenda.",
                                    list.size()));
                            ui.printline();
                        } catch (DateTimeParseException e) {
                            throw new DukeException("Invalid Date Format: should be YYYY-MM-DDTTime. " +
                                    "Example: 2023-12-12T06:30:00");
                        }
                    }

                } else if (text.startsWith("delete")) {
                    try {
                        storage.appendToFile( text + "\n");
                    } catch (IOException e) {
                        System.out.println("Something went wrong: " + e.getMessage());
                    }
                    String[] splitText = text.split(" ");
                    int numToDelete = Integer.parseInt(splitText[1]) - 1;


                    ui.printline();
                    System.out.println("Alright Sir, I have removed this task from the list for you.");
                    System.out.println(String.format("\t [%s] [%s] %s", list.get(numToDelete).tag,
                            list.get(numToDelete).getStatusIcon(), list.get(numToDelete).toString()));
                    list.remove(numToDelete);
                    System.out.println(String.format("Now you have %d tasks left.", list.size()));
                } else {
                    throw new DukeException("I apologise, sir. But I do not understand what you mean.");
                }

            }
        }



