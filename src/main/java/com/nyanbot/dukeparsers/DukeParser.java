package com.nyanbot.dukeparsers;

import com.nyanbot.duketasklist.DukeTaskList;
import com.nyanbot.duketasks.InvalidTask;
import com.nyanbot.duketasks.Task;
import com.nyanbot.dukeuiclasses.DukeUi;

/**
 * Encapsulates a class that parses input.
 *
 * @author Tan Kerway
 */
public class DukeParser {
    private final DukeUi ui;
    private final DukeTaskList taskListUtility;

    /**
     * Constructs a parser for DukeLauncher.Duke.
     *
     * @author Tan Kerway
     */
    public DukeParser(DukeTaskList taskListUtility) {
        this.ui = new DukeUi();
        assert taskListUtility != null;
        this.taskListUtility = taskListUtility;
    }

    /**
     * Processes user commands. Breaks down the input and
     * Checks which command the user types in.
     *
     * @author Tan Kerway
     * @param input the input to be processed
     * @return empty String if the command is not "bye", non-empty String otherwise
     */
    public String processUserCommand(String input) {
        // case where the chatbot has been first initialised
        if (input == null) {
            return "";
        }
        // case where the input is "list" => enumerate all tasks
        if (input.equals("list")) {
            return this.ui.listAllTasks(this.taskListUtility.getTasks());
        }
        // case where the input is "bye" => terminate early
        if (input.equals("bye")) {
            return "bye!";
        }
        // case where the input is the mark command => mark the task as done
        if (input.startsWith("mark")) {
            return this.taskListUtility.handleMark(input);
        }
        // case where the input is unmark
        if (input.startsWith("unmark")) {
            return this.taskListUtility.handleUnmark(input);
        }
        // case where the input is deleted
        if (input.startsWith("delete")) {
            return this.taskListUtility.handleDelete(input);
        }
        Task createdTask = this.taskListUtility.addTask(input);
        // case where the input is to find matching tasks
        if (input.startsWith("find")) {
            return this.taskListUtility.handleFind(input);
        }
        if (createdTask instanceof InvalidTask) {
            assert !createdTask.isValid();
            return createdTask.getDescription();
        } else {
            assert createdTask.isValid();
            return this.ui.echoTaskAdded(createdTask, this.taskListUtility.getTasks().size());
        }
    }

    /**
     * Returns the length of the task list.
     *
     * @author Tan Kerway
     * @return the length of the tasklist
     */
    public int getTaskListSize() {
        assert this.taskListUtility != null;
        return this.taskListUtility.getTasks().size();
    }

    /**
     * Parses the String. if there is error, this method will return null to
     * indicate unsuccessful parsing.
     *
     * @author Tan Kerway
     * @param numberString the number to parse
     * @return an integer if parsing was successful, null otherwise
     */
    public Integer parseString(String numberString) {
        assert !numberString.isEmpty();
        int res = 0;
        numberString = numberString.trim();
        for (int i = 0; i < numberString.length(); i++) {
            char currentChar = numberString.charAt(i);
            if (!Character.isDigit(currentChar)) {
                return null;
            }
            res = res * 10 + (currentChar - '0');
        }
        return res - 1 < 0 || res - 1 >= this.taskListUtility.getTasks().size() ? null : res;
    }
}
