package duke;

import java.util.Arrays;
import java.util.Scanner;

/** Chatbot to assist individuals in keeping track of pending tasks */
public class Duke {

    private enum SpecialTaskKeyword {
        TODO,
        DEADLINE,
        EVENT
    }

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
            this.ui.showLoadingError();
            this.taskList = new TaskList(this.ui);
        }
        this.parser = new Parser();

    }

    /**
     * Allows the user to interact with the chatbot
     */
    public void run() {
        this.ui.greetMessage();
        Scanner scanner = new Scanner(System.in);
        SpecialTaskKeyword[] specialTasksKeywords = SpecialTaskKeyword.values();
        while (true) {
            String userInput = scanner.nextLine();
            try {
                String actionWord = this.parser.parseActionWord(userInput);
                if (actionWord.equals("bye")) {
                    this.taskList.save(this.storage);
                    break;
                } else if (actionWord.equals("list")) {
                    this.taskList.listTasks();
                } else if (actionWord.equals("mark")) {
                    int taskNumber = this.parser.parseTaskNumber(userInput);
                    this.taskList.markTaskAsDone(taskNumber);
                } else if (actionWord.equals("unmark")) {
                    int taskNumber = this.parser.parseTaskNumber(userInput);
                    this.taskList.unmarkTask(taskNumber);
                } else if (actionWord.equals("delete")) {
                    int taskNumber = this.parser.parseTaskNumber(userInput);
                    this.taskList.deleteTask(taskNumber);
                } else if (actionWord.equals("find")) {
                    String description = this.parser.parseFindDescription(userInput);
                    this.taskList.filterTasks(description);
                } else if (Arrays.stream(specialTasksKeywords).anyMatch(
                        keyword -> keyword.toString().toLowerCase().equals(actionWord))) {
                    try {
                        Task task = this.parser.parseAddTaskInput(userInput, actionWord);
                        this.taskList.addTask(task);
                    } catch (InvalidTaskException e) {
                        System.out.println(e.getMessage());
                    }
                } else {
                    throw new InvalidInputException("ERROR: Invalid input");
                }
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
            }
        }
        scanner.close();
    }

    /**
     * Runs the chatbot and allows users to keep track of pending tasks
     *
     * @param args user inputs to interact with the chatbot
     */
    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }




}
