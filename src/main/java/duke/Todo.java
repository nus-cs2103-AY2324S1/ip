package duke;

/**
 * Represents a Todo task.
 * A Todo task is a task without any date/time attached to it.
 */
public class Todo extends Task {

    /**
     * Creates a new Todo task with the given description.
     *
     * @param description The description of the Todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the Todo task.
     *
     * @return A string representing the Todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a string representation of the Todo task for file storage.
     *
     * @return A string suitable for storing the Todo task in a file.
     */
    @Override
    public String toFileString() {
        return "T" + super.toFileString();
    }
}





/*
while (true) {
            try {
                String userInput = scanner.nextLine();

                if ("bye".equalsIgnoreCase(userInput)) {
                    break;
                }

                if ("list".equalsIgnoreCase(userInput)) {
                    ui.showTasksList(tasks);
                } else if (userInput.startsWith("mark")) {
                    processTask(userInput, tasks, true);
                    try {
                        duke.Storage.saveTasks(tasks); // Save the updated tasks to file
                    } catch (IOException e) {
                        ui.showSavingError(e.getMessage());
                    }

                } else if (userInput.startsWith("unmark")) {
                    processTask(userInput, tasks, false);
                    try {
                        duke.Storage.saveTasks(tasks); // Save the updated tasks to file
                    } catch (IOException e) {
                        ui.showSavingError(e.getMessage());
                    }

                } else if (userInput.startsWith("todo")) {
                    String description = userInput.substring(4).trim();

                    if (description.isEmpty()) {
                        throw new duke.DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                    }

                    duke.Todo newTodo = new duke.Todo(description);
                    tasks.add(newTodo);

                    try {
                        duke.Storage.saveTasks(tasks); // Save the updated tasks to file
                    } catch (IOException e) {
                        ui.showSavingError(e.getMessage());
                    }

                    ui.showAddedTask(newTodo, tasks.size());
                } else if (userInput.startsWith("deadline")) {
                    String content = userInput.substring(8).trim();
                    int index = content.indexOf("/by");

                    if (index == -1) {
                        throw new duke.DukeException("Please use '/by' to specify the deadline time.");
                    } else {
                        String description = content.substring(0, index).trim();
                        String by = content.substring(index + 4).trim();
                        duke.Deadline newDeadline;
                        try {
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
                            LocalDateTime dateTime = LocalDateTime.parse(by, formatter);
                            newDeadline = new duke.Deadline(description, dateTime);
                            tasks.add(newDeadline);

                            try {
                                duke.Storage.saveTasks(tasks); // Save the updated tasks to file
                            } catch (IOException e) {
                                ui.showSavingError(e.getMessage());
                            }
                        } catch (DateTimeParseException e) {
                            throw new duke.DukeException("Invalid date-time format! Please use d/M/yyyy HHmm format.");
                        }

                        ui.showAddedTask(newDeadline, tasks.size());
                    }
                } else if (userInput.startsWith("event")) {
                    String content = userInput.substring(5).trim();

                    String[] parts = content.split("/from | /to ");

                    if (parts.length < 3) {
                        throw new duke.DukeException("Please use the format: event [description] /from [start time] /to [end time]");
                    }

                    String description = parts[0].trim();
                    String from = parts[1].trim();
                    String to = parts[2].trim();

                    duke.Event newEvent;
                    try {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
                        LocalDateTime dateTimeFrom = LocalDateTime.parse(from, formatter);
                        LocalDateTime dateTimeTo = LocalDateTime.parse(to, formatter);
                        newEvent = new duke.Event(description, dateTimeFrom, dateTimeTo);
                        tasks.add(newEvent);

                        try {
                            duke.Storage.saveTasks(tasks); // Save the updated tasks to file
                        } catch (IOException e) {
                            ui.showSavingError(e.getMessage());
                        }
                    } catch (DateTimeParseException e) {
                        throw new duke.DukeException("Invalid date-time format! Please use d/M/yyyy HHmm format.");
                    }

                    ui.showAddedTask(newEvent, tasks.size());
                } else if (userInput.startsWith("delete")) {
                    int taskNumber;
                    try {
                        taskNumber = Integer.parseInt(userInput.split(" ")[1]);
                        if (taskNumber <= 0 || taskNumber > tasks.size()) {
                            throw new duke.DukeException("Invalid task number!");
                        }

                        duke.Task deletedTask = tasks.get(taskNumber - 1);

                        tasks.remove(taskNumber - 1);

                        try {
                            duke.Storage.saveTasks(tasks); // Save the updated tasks to file
                        } catch (IOException e) {
                            ui.showSavingError(e.getMessage());
                        }

                        ui.showDeletedTask(deletedTask, tasks.size());
                    } catch (NumberFormatException e) {
                        ui.showInvalidTaskNumber();
                    } catch (duke.DukeException de) {
                        System.out.println("____________________________________________________________");
                        System.out.println(de.getMessage());
                        System.out.println("____________________________________________________________");
                    }
                } else {
                    throw new duke.DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (duke.DukeException de) {
                ui.printMessage(de.getMessage());
            }
        }

        ui.showGoodbye();
    }
 */

