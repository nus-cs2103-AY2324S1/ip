package duke;

import java.util.Arrays;

import javafx.application.Platform;


/** Chatbot to assist individuals in keeping track of pending tasks */
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
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        try {
            String actionWord = this.parser.parseActionWord(input);
            if (actionWord.equals("bye")) {
                this.taskList.save(this.storage);
                Platform.exit();
            } else if (actionWord.equals("list")) {
                return this.taskList.listTasks();
            } else if (actionWord.equals("mark")) {
                int taskNumber = this.parser.parseTaskNumber(input);
                return this.taskList.markTaskAsDone(taskNumber);
            } else if (actionWord.equals("unmark")) {
                int taskNumber = this.parser.parseTaskNumber(input);
                return this.taskList.unmarkTask(taskNumber);
            } else if (actionWord.equals("delete")) {
                int taskNumber = this.parser.parseTaskNumber(input);
                return this.taskList.deleteTask(taskNumber);
            } else if (actionWord.equals("find")) {
                String description = this.parser.parseFindDescription(input);
                return this.taskList.filterTasks(description);
            } else if (Arrays.stream(this.specialTasksKeywords).anyMatch(
                    keyword -> keyword.toString().toLowerCase().equals(actionWord))) {
                try {
                    Task task = this.parser.parseAddTaskInput(input, actionWord);
                    return this.taskList.addTask(task);
                } catch (InvalidTaskException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                throw new InvalidInputException("ERROR: Invalid input");
            }
        } catch (InvalidInputException e) {
            return "INVALID INPUT";
        }
        return "ERROR";
    }
}
