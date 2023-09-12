package duke;

import java.util.Arrays;

import javafx.application.Platform;


/** Chatbot to assist individuals in keeping track of pending tasks. */
public class Duke {

    private enum SpecialTaskKeyword {
        TODO,
        DEADLINE,
        EVENT
    }
    private SpecialTaskKeyword[] specialTasksKeywords = SpecialTaskKeyword.values();
    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private Parser parser;

    /** Constructor to initialise the chatbot */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.taskList = new TaskList(this.storage.load(), this.ui);
        } catch (NoTasksStoredException e) {
            this.taskList = new TaskList(this.ui);
        }
        this.parser = new Parser();
    }

    /**
     * Returns Duke's response to the user's input.
     * @param input a string representation of the user's input.
     * @return a string message representing Duke's response.
     */
    public String getResponse(String input) {
        try {
            String actionWord = this.parser.parseActionWord(input);
            int taskNumber = -1;
            switch (actionWord) {
            case "bye":
                this.taskList.save(this.storage);
                Platform.exit();
                break;
            case "list":
                return this.taskList.listTasks();
            case "mark":
                taskNumber = this.parser.parseTaskNumber(input);
                return this.taskList.markTaskAsDone(taskNumber);
            case "unmark":
                taskNumber = this.parser.parseTaskNumber(input);
                return this.taskList.unmarkTask(taskNumber);
            case "delete":
                taskNumber = this.parser.parseTaskNumber(input);
                return this.taskList.deleteTask(taskNumber);
            case "find":
                String description = this.parser.parseFindDescription(input);
                return this.taskList.filterTasks(description);
            default:
                if (Arrays.stream(this.specialTasksKeywords)
                        .anyMatch(keyword -> keyword.toString().toLowerCase().equals(actionWord))) {
                    try {
                        Task task = this.parser.parseAddTaskInput(input, actionWord);
                        return this.taskList.addTask(task);
                    } catch (InvalidTaskException e) {
                        System.out.println(e.getMessage());
                    }
                } else {
                    throw new InvalidInputException("ERROR: Invalid input");
                }
            }
        } catch (InvalidInputException e) {
            return "INVALID INPUT";
        }
        return "ERROR";
    }
}
