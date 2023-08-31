package duke;

import java.util.ArrayList;
import java.util.Objects;

public class Parser {
    protected String[] words;
    private Ui ui;

    public Parser(String command) {
        this.words = command.split(" ");
        ui = new Ui();
    }

    /**
     * Analyses user's commands and performs the appropriate actions.
     * If the user's command is "Bye", throws an Exception such that main Duke class can be terminated.
     *
     * @param tasks current Tasks the user has stored. Aids in operation of deleting/appending tasks.
     * @throws Exception If command is "Bye"
     */
    public void validateParser(TaskList tasks) throws Exception {
        String action = this.words[0];
        String taskDescription = getTaskDescription(this.words);
        try {
            if (Objects.equals(action, "todo")) {
                if (isValidToDoCommand(this.words)) {
                    Task newTask = new Todo(taskDescription);
                    tasks.addTask(newTask);
                    ui.addTaskText(newTask, tasks.userData.size());
                }
            } else if (Objects.equals(action, "deadline")) {
                if (isValidDeadlineCommand(this.words)) {
                    Task newTask = new Deadline(taskDescription, searchDeadline(this.words, "/by"));
                    tasks.addTask(newTask);
                    ui.addTaskText(newTask, tasks.userData.size());
                }
            } else if (Objects.equals(action, "event")) {
                String[] fromTo = searchFromTo(this.words, "/from", "/to");
                if (isValidEventCommand(this.words)) {
                    Task newTask = new Event(taskDescription, fromTo[0], fromTo[1]);
                    tasks.addTask(newTask);
                    ui.addTaskText(newTask, tasks.userData.size());
                }
            } else if (Objects.equals(action, "bye")) {
                ui.textGenerator("Bye. Hope to see you again soon!");
                throw new Exception("Termination");
            } else if (Objects.equals(action, "list")) {
                for (int i = 0; i < tasks.userData.size(); i++) {
                    Task currentTask = tasks.userData.get(i);
                    ui.displayTaskInList(i, currentTask);
                }
            } else if (Objects.equals(action, "mark")) {
                int index = Integer.parseInt(this.words[1]);
                Task currentTask = tasks.userData.get(index - 1);
                currentTask.isDone = true;
                ui.markTaskText(currentTask);
            } else if (Objects.equals(action, "unmark")) {
                int index = Integer.parseInt(this.words[1]);
                Task currentTask = tasks.userData.get(index - 1);
                currentTask.isDone = false;
                ui.unmarkTaskText(currentTask);
            } else if (Objects.equals(action, "delete")) {
                int index = Integer.parseInt(this.words[1]);
                Task deletedTask = tasks.userData.get(index - 1);
                tasks.deleteTask(index - 1);
                ui.deleteTaskText(deletedTask, tasks.userData.size());
            } else if (Objects.equals(action, "find"))  {
                String keyword = taskDescription;
                ArrayList<Task> filteredTasks = tasks.filter(keyword);
                for (int i = 0; i < filteredTasks.size(); i++) {
                    Task currentTask = filteredTasks.get(i);
                    ui.displayTaskInList(i, currentTask);
                }
            } else {
                throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        } catch (DukeException DE) {
            System.out.println(DE.getMessage());
        }
    }

    /**
     * Returns task description for various Task types (Todo/Deadline/Event)
     *
     * @param arrUserCommand Array of the particular user's command split by " "
     * @return Task description for a particular Task (String)
     */
    public String getTaskDescription(String[] arrUserCommand) {
        StringBuilder result = new StringBuilder();
        for (int i = 1; i < arrUserCommand.length; i++) {
            if ("/by".equals(arrUserCommand[i]) || "/from".equals(arrUserCommand[i])) {
                break;
            }
            result.append(" ").append(arrUserCommand[i]);
        }
        return result.toString().trim();
    }

    /**
     * Returns True if the user's command for a Todo event is valid. I.e. has to contain >= 1 words
     * Throws DukeException if the user's command contains only 1 word (No Task Description)
     *
     * @param userCommand Array of the particular user's command split by " "
     * @return True if appropriate Todo command
     * @throws DukeException If the command is only one word. I.e. no task description
     */
    public boolean isValidToDoCommand(String[] userCommand) throws DukeException {
        if (userCommand.length <= 1) {
            String error = String.format("OOPS!!! The description of a %s cannot be empty.", userCommand[0]);
            throw new DukeException(error);
        } else {
            return true;
        }
    }

    /**
     * Returns string from delimiter to end of array. Array is from user commands.
     *
     * @param userCommand Array of the particular user's command split by " "
     * @param delimiter Use-case would be "/by" for a deadline task
     * @return In Deadline task context, it should return the due date that the user has input. (String)
     */
    public String searchDeadline(String[] userCommand, String delimiter) {
        StringBuilder result = new StringBuilder();
        int index = -1;
        for (int i = 0; i < userCommand.length; i++) {
            if (delimiter.equals(userCommand[i])) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            for (int i = index + 1; i < userCommand.length; i++) {
                result.append(userCommand[i]).append(" ");
            }
        }
        return result.toString().trim();
    }

    /**
     * Returns an Array of length 2 where the first index is the "/from" specification and the second index is the "/to" specification for Event Task
     *
     * @param userCommand Array of the particular user's command split by " "
     * @param delimiter Use-case would be "/from" for an "Event" task
     * @param delimiter_2 Use-case would be "/to" for an "Event" task
     * @return In Event task context, it should return an array where array[0] is "/from" specs and array[1] is "/to" specs.
     */
    public String[] searchFromTo(String[] userCommand, String delimiter, String delimiter_2) {
        StringBuilder firstResult = new StringBuilder();
        StringBuilder secondResult = new StringBuilder();
        int index = -1;
        int index_2 = -1;
        for (int i = 0; i < userCommand.length; i++) {
            if (delimiter.equals(userCommand[i])) {
                index = i;
            } else if (delimiter_2.equals(userCommand[i])) {
                index_2 = i;
            }
        }
        if (index != -1 && index_2 != -1 && index < index_2) {
            for (int i = index + 1; i < index_2; i++) {
                firstResult.append(userCommand[i]).append(" ");
            }
            for (int i = index_2 + 1; i < userCommand.length; i++) {
                secondResult.append(userCommand[i]).append(" ");
            }
        }
        return new String[]{ firstResult.toString().trim(), secondResult.toString().trim() };
    }

    public boolean isValidDeadlineCommand(String[] userCommand) throws DukeException {
        if (userCommand.length <= 1) {
            throw new DukeException(String.format("OOPS!!! The description of a %s cannot be empty.", userCommand[0]));
        } else if (userCommand.length <= 2){
            throw new DukeException("For deadlines, please give a gauge of when it is due");
        } else {
            String result = searchDeadline(userCommand, "/by");
            if (Objects.equals(result, "")) {
                throw new DukeException("For deadlines, please give a gauge of when it is due");
            } else {
                String datePattern = "\\d{4}-\\d{2}-\\d{2}";
                String dateTimePattern = "\\d{4}-\\d{2}-\\d{2} \\d{4}";
                if (!result.matches(datePattern) && !result.matches(dateTimePattern)) {
                    throw new DukeException("Please specify the deadline in the format 'yyyy-MM-dd' or 'yyyy-MM-dd HHmm'.");
                }
            }
        }
        return true;
    }

    public boolean isValidEventCommand(String[] userCommand) throws DukeException {
        if (userCommand.length <= 1) {
            throw new DukeException(String.format("OOPS!!! The description of a %s cannot be empty.", userCommand[0]));
        } else if (userCommand.length <= 2){
            throw new DukeException("For Events, please provide a valid FROM/TO timeframe.");
        } else {
            String[] result = searchFromTo(userCommand, "/from", "/to");
            if (result[0].equals("") || result[1].equals("")) {
                throw new DukeException("For Events, please provide a valid FROM/TO timeframe.");
            }
        }
        return true;
    }
}
