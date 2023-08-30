package seedu.duke;
import java.io.StreamCorruptedException;
import java.time.LocalDateTime;

public class Parser {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Parser(Storage storage, TaskList tasks, Ui ui) {
        this.storage = storage;
        this.tasks = tasks;
        this.ui = ui;
    }

    // Takes a command and executes it accordingly
    public void parse(String cmd) {
        // Exit if command is "bye"
            try {
                String type = cmd.split(" ", 2)[0];

                // If cmd is "list", list items and wait for next command
                if (cmd.equals("list")) {
                    ui.printListItems(tasks);
                } else if (type.equals("todo")) {
                    // Check if description is empty
                    if (descriptionIsEmpty(cmd)) {
                        throw new InvalidDescriptionException("todo");
                    }

                    String taskName = cmd.split(" ", 2)[1];
                    Task todo = new ToDo(taskName);
                    tasks.addTask(todo);
                    ui.printAddTaskMessage(todo, tasks);
                } else if (type.equals("deadline")) {
                    if (descriptionIsEmpty(cmd)) {
                        throw new InvalidDescriptionException("deadline");
                    }

                    String taskWithDeadline = cmd.split(" ", 2)[1];

                    if (hasNoDeadline(taskWithDeadline)) {
                        throw new NoDeadlineException();
                    }

                    String taskName = taskWithDeadline.split("/", 2)[0];
                    String deadlineDescription = taskWithDeadline.split("/", 2)[1];

                    try {
                        Task deadline = new Deadline(taskName, checkDeadline(deadlineDescription));
                        tasks.addTask(deadline);
                        ui.printAddTaskMessage(deadline, tasks);
                    } catch(Exception e) {
                        System.out.println(e.getMessage());
                    }
                } else if (type.equals("event")) {
                    if (descriptionIsEmpty(cmd)) {
                        throw new InvalidDescriptionException("event");
                    }

                    String taskWithDuration = cmd.split(" ", 2)[1];
                    String[] time = taskWithDuration.split("/");

                    // Check if there is a valid duration
                    if (time.length != 3) {
                        throw new IncompleteDurationException();
                    }

                    String taskName = time[0];
                    String starting = time[1];
                    String ending = time[2];

                    // Assumes that starting and ending both start with "from" and "to" respectively
                    Task event = new Event(taskName, starting.split(" ", 2)[1].trim(), ending.split(" ", 2)[1].trim());
                    tasks.addTask(event);
                    ui.printAddTaskMessage(event, tasks);
                } else if (type.equals("delete")) {
                    if (descriptionIsEmpty(cmd)) {
                        throw new InvalidDescriptionException("delete");
                    }

                    int taskNumber = -1;
                    String integer = cmd.split(" ", 2)[1];

                    try {
                        taskNumber = Integer.parseInt(integer);
                    } catch (Exception e) {
                        throw new InvalidIntegerException();
                    }

                    if (!isValidTaskNumber(taskNumber)) {
                        throw new InvalidTaskNumberException(taskNumber);
                    }

                    Task task = tasks.markOrDeleteTask(taskNumber - 1, "delete");
                    ui.printDeleteTaskMessage(task, new TaskList(storage.load()));
                } else if (type.equals("mark")) {
                    if (descriptionIsEmpty(cmd)) {
                        throw new InvalidDescriptionException("mark");
                    }

                    int taskNumber = -1;
                    String integer = cmd.split(" ", 2)[1];

                    try {
                        taskNumber = Integer.parseInt(integer);
                    } catch (Exception e) {
                        throw new InvalidIntegerException();
                    }

                    if (!isValidTaskNumber(taskNumber)) {
                        throw new InvalidTaskNumberException(taskNumber);
                    }

                    Task task = tasks.markOrDeleteTask(taskNumber - 1, "mark");
                    ui.printMarkedTaskMessage(task);
                } else if (type.equals("unmark")) {
                    if (descriptionIsEmpty(cmd)) {
                        throw new InvalidDescriptionException("unmark");
                    }

                    int taskNumber = -1;
                    String integer = cmd.split(" ", 2)[1];

                    try {
                        taskNumber = Integer.parseInt(integer);
                    } catch (Exception e) {
                        throw new InvalidIntegerException();
                    }

                    if (!isValidTaskNumber(taskNumber)) {
                        throw new InvalidTaskNumberException(taskNumber);
                    }

                    Task task = tasks.markOrDeleteTask(taskNumber - 1, "unmark");
                    ui.printUnmarkedTaskMessage(task);
                } else {  // If the inputted command is not valid, throw TaskTypeException
                    throw new TaskTypeException();
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
    }

    // Checks if an input task has a task title
    public boolean descriptionIsEmpty(String cmd) {
        return cmd.split(" ").length == 1;
    }

    // Checks whether the input task has no deadline
    // A task has no deadline if there is no "/" followed by at least one character
    public boolean hasNoDeadline(String taskWithDeadline) {
        return taskWithDeadline.split("/").length == 1;
    }

    // Checks whether the input deadline is a proper date and time
    // Returns a LocalDateTime containing the date and time given
    public LocalDateTime checkDeadline(String deadline) {
        String[] parts = deadline.split(" ");
        try {
            // If there isn't exactly three components in the deadline, return false
            if (parts.length != 3) {
                throw new InvalidDeadlineException(deadline);
            }

            String by = parts[0];
            String date = parts[1];
            String time = parts[2];

            // If the first word is not "by", return false
            if (!by.equals("by")) {
                throw new InvalidDeadlineException(deadline);
            }

            String[] dateParts = date.split("/");
            // If the date does not have three components, return false
            if (dateParts.length != 3) {
                throw new InvalidDeadlineException(deadline);
            }

            int day = Integer.parseInt(dateParts[0]);
            int month = Integer.parseInt(dateParts[1]);
            int year = Integer.parseInt(dateParts[2]);

            // If time is not a four digit number, return false
            if (time.length() != 4) {
                throw new InvalidDeadlineException(deadline);
            }

            int hour = Integer.parseInt(time.substring(0, 2));
            int min = Integer.parseInt(time.substring(2, 4));

            return LocalDateTime.of(year, month, day, hour, min);
        } catch (InvalidDeadlineException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    // Checks whether a task with the given task number argument exists
    public boolean isValidTaskNumber(int number) {
        try {
            int listSize = storage.load().size();
            return number > 0 && number <= listSize;
        } catch (InvalidDataFormatException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
