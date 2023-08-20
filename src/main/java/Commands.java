import Exceptions.*;
import Tasks.Deadline;
import Tasks.Events;
import Tasks.Task;
import Tasks.Todo;

import java.util.ArrayList;

public class Commands {

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

    private String cmd;
    private ArrayList<Task> taskList;

    private String input;
    String divider = "\n____________________________________________________________";

    /**
     * Constructor for Commands to initalise the relevant parameters
     *
     * @param cmd      The command entered
     * @param input    The whole user input
     * @param taskList The list of tasks recorded
     */
    public Commands(String cmd, String input, ArrayList<Task> taskList) {
        this.taskList = taskList;
        this.input = input;
        this.cmd = cmd;
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

            return "Got it macho! I've added this task:\n" + td.toString() + "\n" +
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
    public String createDeadline(String input) throws InvalidArgumentException {
        String[] parts = input.split("/by", 2);
        if (parts.length == 1 || parts[1].isEmpty() || parts[1].isBlank()) {
            throw new InvalidArgumentException(input.substring(9), "/by");
        } else {
            String taskDesc = parts[0].split(" ", 2)[1];
            String by = parts[1];
            Deadline dl = new Deadline(taskDesc, false, by);
            this.taskList.add(dl);

            return "Got it macho! I've added this task:\n" + dl.toString() + "\nYou now have " + this.taskList.size()
                    + " tasks in the list, macho!";
        }
    }

    /**
     * Creates a task relating to Events.
     *
     * @param input String of user input
     * @return output String
     * @throws InvalidArgumentException if input does not contain the correct /from and /to argument
     */
    public String createEvent(String input) throws InvalidArgumentException {
        String[] parts = input.split("\\s+/from\\s+|\\s+/to\\s+");
        if (parts.length < 3) {
            throw new InvalidArgumentException(input.substring(6), "/from and /to");
        } else {
            String taskDesc = parts[0].split(" ", 2)[1];
            String afterFrom = parts[1];
            String afterTo = parts[2];
            Events ev = new Events(taskDesc, false, afterFrom, afterTo);
            this.taskList.add(ev);
            return "Got it macho! I've added this task:\n" + ev.toString() + "\nYou now have " + this.taskList.size()
                    + " tasks in the list, macho!";
        }
    }

    /**
     * Marks a task as uncompleted.
     *
     * @param input String of user input
     * @return output String
     * @throws EmptyTasksException if list of tasks is empty.
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
                return "I have marked this task as undone yet, per your request, macho!\n" + task.toString();
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
     * @throws EmptyTasksException if list of tasks is empty.
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
                return "I have marked this task as done per your request, macho!\n" + task.toString();
            } catch (IndexOutOfBoundsException e) {
                throw new InvalidIndexException(input);
            }
        }
    }

    /**
     * Deletes a task from the task list.
     * @param input String of user input
     * @return output String
     * @throws EmptyTasksException if list of tasks is empty.
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
     * @return ArrayList of Task
     * @throws InvalidCommandException if user enters an invalid command
     */
    public ArrayList<Task> execute() throws InvalidCommandException {
        CommandsList command;
        try {
            try {
                System.out.println(divider);
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
            System.out.println(divider);

        } catch (InvalidArgumentException | EmptyTasksException | InvalidCommandException |
                 InvalidTaskDescriptionException | InvalidIndexException e) {
            System.out.println(e.getMessage());
            System.out.println(divider);
            return this.taskList;
        }

        return this.taskList;
    }


}
