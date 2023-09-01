package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;
import duke.task.Task;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

public class Parser {
        public static void executeCommand(String command, TaskList tasks) throws DukeException {
            String[] separateCommand = command.split(" ");
            if (command.equals("list")) {
                Ui.listTasks(tasks.getAll());
            } else if (command.startsWith("mark") || command.startsWith("unmark")) {
                try {
                    if (separateCommand.length > 2 || Integer.parseInt(separateCommand[1]) > tasks.getAll().size()) {
                        throw new DukeException("☹ OOPS!!! Invalid number");
                    }
                    int taskNumber = Integer.parseInt(separateCommand[1]);
                    if (command.startsWith("mark")) {
                        tasks.getAll().get(taskNumber - 1).markAsDone();
                        Ui.markDoneMessage();
                    } else if (command.startsWith("unmark")) {
                        tasks.getAll().get(taskNumber - 1).markAsUndone();
                        Ui.markUndoneMessage();
                    }
                    Ui.showTaskMessage(tasks.getAll().get(taskNumber - 1));
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    throw new DukeException("☹ OOPS!!! Invalid number");
                }
            } else if (command.startsWith("todo") || command.startsWith("deadline") || command.startsWith("event")) {
                if (command.startsWith("todo")) {
                    try {
                        String description = command.substring(5);
                        if (description.length() == 0) {
                            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                        }
                        tasks.addTask(new ToDo(description));
                    } catch (StringIndexOutOfBoundsException e) {
                        throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                    }
                } else if (command.startsWith("deadline")) {
                    try {
                        String[] parts = command.split("/by");
                        String description = parts[0].substring(9).trim();
                        if (description.length() == 0) {
                            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                        }
                        try {
                            LocalDate by = LocalDate.parse(parts[1].trim());
                            tasks.addTask(new Deadline(description, by));
                        } catch (DateTimeParseException e) {
                            throw new DukeException("☹ OOPS!!! Invalid date format.");
                        }
                    } catch (StringIndexOutOfBoundsException e) {
                        throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                    }
                } else if (command.startsWith("event")) {
                    try {
                        String[] parts = command.split("/from");
                        String description = parts[0].substring(6).trim();
                        if (description.length() == 0) {
                            throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
                        }
                        String[] timeParts = parts[1].split("/to");
                        String start = timeParts[0].trim();
                        String end = timeParts[1].trim();
                        tasks.addTask(new Event(description, start, end));
                    } catch (StringIndexOutOfBoundsException e) {
                        throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
                    }
                } else {
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
                Ui.addTaskMessage(tasks);
            } else if (command.startsWith("delete")) {
                try {
                    if (separateCommand.length > 2 || Integer.parseInt(separateCommand[1]) > tasks.getAll().size()) {
                        throw new DukeException("☹ OOPS!!! Invalid number");
                    }
                    int taskNumber = Integer.parseInt(separateCommand[1]);
                    if (command.startsWith("delete")) {
                        Ui.removeTaskMessage();
                        Ui.showTaskMessage(tasks.getAll().get(taskNumber - 1));
                        tasks.removeTask(taskNumber - 1);
                    }
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    throw new DukeException("☹ OOPS!!! Invalid number");
                }
            }  else if (command.startsWith(("find"))) {
                String keyword = command.substring(5).trim();
                List<Task> matchingTasks = tasks.findTasks(keyword);
                Ui.listMatchedTasks(matchingTasks);
            } else {
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }

}
