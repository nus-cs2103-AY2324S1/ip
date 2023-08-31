package Helpers;

import Exceptions.*;
import Tasks.Deadline;
import Tasks.Events;
import Tasks.Task;
import Tasks.Todo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Parser {

    protected final String cmd;
    protected final ArrayList<Task> taskList;
    protected final String input;
    protected final String DIVIDER = "\n____________________________________________________________";
    private final static DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    protected Storage storage;
    /**
     * Constructor for Helpers.Commands to initalise the relevant parameters
     *
     * @param cmd      The command entered
     * @param input    The whole user input
     */
    public Parser(String cmd, String input, String filepath) {
        this.input = input;
        this.cmd = cmd;
        this.storage = new Storage(filepath);
        this.taskList = this.storage.read();
    }

    /**
     * Gets a list of tasks.
     *
     * @return Arraylist of tasks
     */
    public String getList() {
        if (this.taskList.isEmpty()) {
            return "No tasks recorded, macho!";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.taskList.size(); i++) {
            int index = i + 1;
            Task task = this.taskList.get(i);
            sb.append(index).append(".").append(task.toString()).append("\n");
        }
        this.storage.write(this.taskList);
        return sb.toString().trim();
    }

    /**
     * Creates a task relating to ToDo.
     *
     * @param input String of user input
     * @return output String
     * @throws InvalidTaskDescriptionException if task description inside input is empty
     */
    public String createToDo(String input) throws InvalidTaskDescriptionException {
        String taskDesc = input.split(" ", 2)[1];
        if (input.split(" ").length < 2) {
            throw new InvalidTaskDescriptionException("To-Do task");
        } else {
            Todo td = new Todo(taskDesc, false);
            this.taskList.add(td);
            this.storage.write(this.taskList);
            return "Got it macho! I've added this task:\n" + td + "\n" +
                    "You now have " + this.taskList.size() + " tasks in the list, macho!";
        }
    }

    /**
     * Creates a task relating to Deadline.
     *
     * @param input String of user input
     * @return output String
     * @throws InvalidArgumentException if input does not contain the correct /by argument
     */
    public String createDeadline(String input) throws InvalidArgumentException, InvalidTimeFormatException {
        String[] parts = input.split(" /by ", 2);
        if (parts.length == 1 || parts[1].isEmpty() || parts[1].isBlank()) {
            throw new InvalidArgumentException(input.substring(9), "/by");
        } else {
            try {
                String taskDesc = parts[0].split(" ", 2)[1];
                LocalDateTime by = LocalDateTime.parse(parts[1], dateTimeFormat);
                Deadline dl = new Deadline(taskDesc, false, by);
                this.taskList.add(dl);
                this.storage.write(this.taskList);
                return "Got it macho! I've added this task:\n" + dl + "\nYou now have " + this.taskList.size()
                        + " tasks in the list, macho!";
            } catch (Exception e) {
              throw new InvalidTimeFormatException(parts[1]);
            }

        }
    }

    /**
     * Creates a task relating to Events.
     *
     * @param input String of user input
     * @return output String
     * @throws InvalidArgumentException if input does not contain the correct /from and /to argument
     */
    public String createEvent(String input) throws InvalidArgumentException, InvalidTimeFormatException {
        String[] parts = input.split("\\s+/from\\s+|\\s+/to\\s+");
        if (parts.length < 3) {
            throw new InvalidArgumentException(input.substring(6), "/from and /to");
        } else {
            try {
            String taskDesc = parts[0].split(" ", 2)[1];
            LocalDateTime afterFrom = LocalDateTime.parse(parts[1], dateTimeFormat);
            LocalDateTime afterTo = LocalDateTime.parse(parts[2], dateTimeFormat);
            Events ev = new Events(taskDesc, false, afterFrom, afterTo);
            this.taskList.add(ev);
            this.storage.write(this.taskList);
            return "Got it macho! I've added this task:\n" + ev + "\nYou now have " + this.taskList.size()
                    + " tasks in the list, macho!";

            } catch (Exception e) {
                throw new InvalidTimeFormatException(parts[1] + " " + parts[2]);
            }
        }
    }

    /**
     * Marks a task as uncompleted.
     *
     * @param input String of user input
     * @return output String
     * @throws EmptyTasksException   if list of tasks is empty.
     * @throws InvalidIndexException if index does not match with indexes of task list
     */
    public String unmark(String input) throws EmptyTasksException, InvalidIndexException {
        int index = Integer.parseInt(input.split(" ", 2)[1]);
        if (index < 0 || this.taskList.isEmpty()) {
            throw new EmptyTasksException(input);
        } else {
            try {
                Task task = this.taskList.get(index - 1);
                task.markedAsUndone();
                this.storage.write(this.taskList);
                return "I have marked this task as undone yet, per your request, macho!\n" + task;
            } catch (IndexOutOfBoundsException e) {
                throw new InvalidIndexException(input);
            }

        }
    }

    /**
     * Marks a task as completed.
     *
     * @param input String of user input
     * @return output String
     * @throws EmptyTasksException   if list of tasks is empty.
     * @throws InvalidIndexException if index does not match with indexes of task list
     */
    public String mark(String input) throws EmptyTasksException, InvalidIndexException {
        int index = Integer.parseInt(input.split(" ", 2)[1]);
        if (index < 0 || this.taskList.isEmpty()) {
            throw new EmptyTasksException(input);
        } else {
            try {
                Task task = this.taskList.get(index - 1);
                task.markedAsDone();
                this.storage.write(this.taskList);
                return "I have marked this task as done per your request, macho!\n" + task;
            } catch (IndexOutOfBoundsException e) {
                throw new InvalidIndexException(input);
            }
        }
    }

    /**
     * Deletes a task from the task list.
     *
     * @param input String of user input
     * @return output String
     * @throws EmptyTasksException   if list of tasks is empty.
     * @throws InvalidIndexException if index does not match with indexes of task list
     */
    public String deleteTask(String input) throws InvalidIndexException, EmptyTasksException {
        int index = Integer.parseInt(input.split(" ", 2)[1]) - 1;
        if (index < 0 || this.taskList.isEmpty()) {
            throw new EmptyTasksException(input);
        } else {
            try {
                Task task = this.taskList.get(index);
                this.taskList.remove(index);
                this.storage.write(this.taskList);
                return "I have deleted this task as done per your request, macho!\n" + task.toString()
                        + "\nYou now have " + this.taskList.size() + " tasks in the list, macho!";
            } catch (IndexOutOfBoundsException e) {
                throw new InvalidIndexException(input);
            }
        }
    }

    /**
     * Execute the Duke's functions to process user's input.
     *
     * @throws InvalidCommandException if user enters an invalid command
     */
    public void execute() throws InvalidCommandException {
        CommandsList command;
        try {
            try {
                System.out.println(DIVIDER);
                command = CommandsList.valueOf(this.cmd.toUpperCase());
            } catch (Exception e) {
                throw new InvalidCommandException(this.cmd);
            }

            switch (command) {
                case LIST:
                    System.out.println(getList());
                    break;

                case TODO:
                    System.out.println(createToDo(this.input));
                    break;

                case DEADLINE:
                    System.out.println(createDeadline(this.input));
                    break;

                case EVENT:
                    System.out.println(createEvent(this.input));
                    break;

                case UNMARK:
                    System.out.println(unmark(this.input));
                    break;

                case MARK:
                    System.out.println(mark(this.input));
                    break;

                case DELETE:
                    System.out.println(deleteTask(this.input));
                    break;

                case BYE:
                    System.out.println("\nBye! Hope to see you again soon, macho!");
                    break;

                default:
                    System.out.println("Invalid command macho! Please try again!");
            }
            System.out.println(DIVIDER);

        } catch (InvalidArgumentException | EmptyTasksException | InvalidCommandException |
                 InvalidTaskDescriptionException | InvalidIndexException | InvalidTimeFormatException e) {
            System.out.println(e.getMessage());
            System.out.println(DIVIDER);
        }

    }

    private enum CommandsList {
        LIST,
        TODO,
        MARK,
        UNMARK,
        DEADLINE,
        EVENT,
        DELETE,
        BYE
    }


}
