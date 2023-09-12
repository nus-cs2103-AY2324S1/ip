package duke;

import java.io.IOException;
import java.time.LocalDateTime;

import java.time.format.DateTimeParseException;
import java.util.Arrays;

public class Parser {
    public static String parse(String text, UI ui, TaskList list, Storage storage) throws DukeException {
        ui.clearStringBuilder();

        if (text.length() > 3 && text.substring(0, 4).equals("list")) {

            if (list.size() == 0) {
                throw new DukeException("I apologise, sir. But you have no tasks on your list.");
            }

            for (int i = 0; i < list.size(); i++) {
                assert list.size() > 0;
                if (list.get(i) == null) {
                    break;
                } else {
                    if (i == list.size() - 1) {
                        ui.buildMessage(String.format("%d. [%s] [%s] %s", i + 1, list.get(i).tag,
                                list.get(i).getStatusIcon(), list.get(i)));
                    } else {
                        ui.buildMessage(String.format("%d. [%s] [%s] %s \n", i + 1, list.get(i).tag,
                                list.get(i).getStatusIcon(), list.get(i)));
                    }
                }
            } return ui.sendMessage();

        } else if (text.startsWith("unmark")) {
            try {
                String[] splitText = text.split(" ");
                int numToUnmark = Integer.parseInt(splitText[1]) - 1;
                assert splitText.length == 2;
                if (numToUnmark > list.size() - 1) {
                    throw new DukeException("I apologise, sir. This task does not exist");
                }
                list.get(numToUnmark).markAsIncomplete();

                storage.appendToFile(text + "\n");

                ui.buildMessage("Alright! I'll uncheck this task for you: \n");
                ui.buildMessage(String.format("\t [%s] [%s] %s", list.get(numToUnmark).tag,
                        list.get(numToUnmark).getStatusIcon(), list.get(numToUnmark)));
                return ui.sendMessage();
            } catch (NumberFormatException e) {
                throw new DukeException("I apologise, sir. But you have to key in a task number.");
            } catch (IOException e) {
                ui.buildMessage("Something went wrong: " + e.getMessage() + "\n");
                return ui.sendMessage();
            }


        } else if (text.startsWith("mark")) {
            try {
                String[] splitText = text.split(" ");
                int numToMark = Integer.parseInt(splitText[1]) - 1;
                assert splitText.length == 2;
                if (numToMark > list.size() - 1) {
                    throw new DukeException("I apologise, sir. This task does not exist");
                }

                list.get(numToMark).markAsComplete();


                storage.appendToFile(text + "\n");


                ui.buildMessage("Alright! I'll check this task as complete for you: \n");
                ui.buildMessage(String.format("\t [%s] [%s] %s", list.get(numToMark).tag,
                        list.get(numToMark).getStatusIcon(), list.get(numToMark)));
                return ui.sendMessage();

            } catch (NumberFormatException e) {
                throw new DukeException("I apologise, sir. But you have to key in a task number.");
            } catch (IOException e) {
                ui.buildMessage("Something went wrong: " + e.getMessage() + "\n");
                return ui.sendMessage();
            }

        } else if (text.equals("bye")) {

            ui.buildMessage("Goodbye. Hope to be of service again soon!\n");
            return "Goodbye. Hope to be of service again soon!";


        } else if (text.startsWith("todo")) {
            String description = text.substring(4);
            if (description.isEmpty()) {
                throw new DukeException("I apologise, sir. " +
                        "But the description of todo cannot be empty");
            } else {
                try {
                    storage.appendToFile(text + "\n");
                } catch (IOException e) {
                    ui.buildMessage("Something went wrong: " + e.getMessage() + " \n");
                    return ui.sendMessage();
                }

                Todo todo = new Todo(description.trim());
                list.add(todo);

                ui.buildMessage("Noted Sir. I've added this task to your list: \n");
                ui.buildMessage(String.format("\t [%s] [%s] %s \n", todo.tag,
                        todo.getStatusIcon(), todo.toString()));
                ui.buildMessage(String.format("As of now, you have %d tasks on the agenda. \n",
                        list.size()));
                System.out.print(ui.sendMessage());

            }
        } else if (text.startsWith("deadline")) {
            String[] splitText = text.split("/", 2);

            String description = splitText[0].substring(8);

            if (description.isEmpty()) {
                throw new DukeException("I apologise, sir. " +
                        "But the description and deadline cannot be empty");
            } else {

                String deadlineText = splitText[1].substring(3);
                assert splitText.length == 2;
                try {
                    LocalDateTime deadline = LocalDateTime.parse(deadlineText);
                    Deadline dl = new Deadline(description.trim(), deadline);
                    list.add(dl);

                    ui.buildMessage("Noted Sir. I've added this task to your list: \n");
                    ui.buildMessage(String.format("\t [%s] [%s] %s \n", dl.tag, dl.getStatusIcon(),
                            dl.toString()));
                    ui.buildMessage(String.format("As of now, you have %d tasks on the agenda.\n",
                            list.size()));

                    storage.appendToFile(text + "\n");
                    return ui.sendMessage();

                } catch (DateTimeParseException e) {
                    throw new DukeException("Invalid Date Format: should be YYYY-MM-DDTHH:MM:SS. " +
                            "Example: 2023-12-12T06:30:00");
                } catch (IOException e) {
                        ui.buildMessage("Something went wrong: " + e.getMessage() + "\n");
                        return ui.sendMessage();
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
                    assert splitText.length == 3;

                    try {
                        LocalDateTime start = LocalDateTime.parse(startText);
                        LocalDateTime end = LocalDateTime.parse(endText);
                        Event event = new Event(description.trim(), start, end);
                        list.add(event);
                        ui.buildMessage("Noted Sir. I've added this task to your list: \n");
                        ui.buildMessage(String.format("\t [%s] [%s] %s \n", event.tag,
                                event.getStatusIcon(),
                                event.toString()));
                        ui.buildMessage(String.format("As of now, you have %d tasks on the agenda. \n",
                                list.size()));
                        storage.appendToFile(text + "\n");
                        return ui.sendMessage();

                    }  catch (DateTimeParseException e) {
                        throw new DukeException("Invalid Date Format: should be YYYY-MM-DDTHH:MM:SS. " +
                                "Example: 2023-12-12T06:30:00");
                    } catch (IOException e) {
                        ui.buildMessage("Something went wrong: " + e.getMessage() + "\n");
                        return ui.sendMessage();
                    }
                }

            } else if (text.startsWith("delete")) {
            String[] splitText = text.split(" ");

            try {
                int numToDelete = Integer.parseInt(splitText[1]) - 1;
                if (numToDelete > list.size() - 1) {
                    throw new DukeException("I apologise, sir. This task does not exist");
                }
                assert splitText.length == 2;

                storage.appendToFile(text + "\n");

                ui.buildMessage("Alright Sir, I have removed this task from the list for you.\n");
                ui.buildMessage(String.format("\t [%s] [%s] %s \n", list.get(numToDelete).tag,
                        list.get(numToDelete).getStatusIcon(), list.get(numToDelete).toString()));
                list.remove(numToDelete);
                ui.buildMessage(String.format("Now you have %d tasks left. \n", list.size()));
                return ui.sendMessage();
            } catch (NumberFormatException e) {
                throw new DukeException("I apologise, sir. But you have to key in a task number.");
            } catch (IOException e) {
                ui.buildMessage("Something went wrong: " + e.getMessage() + "\n");
                return ui.sendMessage();
            }

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

                                ui.buildMessage("Here are the matching tasks in your list: \n");
                                ui.buildMessage(String.format("\t [%s] [%s] %s \n", list.get(i).tag,
                                        list.get(i).getStatusIcon(), list.get(i).toString()));
                                isFirst = false;
                            } else if (hasSearch == true && isFirst == false) {
                                ui.buildMessage(String.format("\t [%s] [%s] %s \n", list.get(i).tag,
                                        list.get(i).getStatusIcon(), list.get(i).toString()));
                            }
                        }
                    }

                    if (hasSearch == false) {
                        throw new DukeException("I apologise sir." +
                                "But " + search + " cannot be found in your list.");
                    } return ui.sendMessage();
                }
        } else {
            throw new DukeException("I apologise, sir. But I do not understand what you mean.");
        }
        return ui.sendMessage();
    }
}



