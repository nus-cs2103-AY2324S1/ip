package duke;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * duke.Main class for the duke application.
 */

public class Duke {
    private Parser parser;
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private GuiResponse guiResponse;

    /**
     * Constructor for Duke class
     */
    public Duke() {
        ui = new Ui();
        parser = new Parser();
        storage = new Storage("./data/duke.txt");
        guiResponse = new GuiResponse();
        try {
            tasks = new TaskList(storage.loadTask());
        } catch (DukeException e) {
            ui.showLoadingError(e.getMessage());
            tasks = new TaskList();
        }
    }
    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */

    public String getResponse(String input) {
        String command = parser.parseCommand(input);
        if (command.equals("list")) {
            return getListResponse();
        } else if (command.equals("mark")) {
            return getMarkResponse();
        } else if (command.equals("unmark")) {
            return getUnmarkResponse();
        } else if (command.equals("bye")) {
            return getByeResponse();
        } else if (command.equals("todo") || command.equals("deadline") || command.equals("event")) {
            return getTaskResponse();
        } else if (command.equals("delete")) {
            return getDeleteResponse();
        } else if (command.equals("find")) {
            return getFindResponse();
        }
        return "OOPS!!! I'm sorry, but I don't know what that means :-(";
    }

    private String getFindResponse() {
        String query = parser.parseQuery();
        return guiResponse.getQueryResult(tasks.searchTask(query));
    }

    private String getDeleteResponse() {
        int index = parser.parseToIndex();
        Task curr = tasks.getTask(index);
        tasks.deleteTask(index);
        storage.store(tasks);
        return guiResponse.getDelete(curr, tasks.getSize());
    }

    private String getTaskResponse() {
        Task curr = parser.parseToTask();
        if (curr == null) {
            return "";
        }
        tasks.addTask(curr);
        storage.store(tasks);
        return guiResponse.getAddTask(curr, tasks.getSize());
    }

    private String getByeResponse() {
        return guiResponse.getGoodbyeMessage();
    }

    private String getUnmarkResponse() {
        int index = parser.parseToIndex();
        Task curr = tasks.getTask(index);
        curr.markAsNotDone();
        storage.store(tasks);
        return guiResponse.getUnmark(curr, index);
    }

    private String getMarkResponse() {
        int index = parser.parseToIndex();
        Task curr = tasks.getTask(index);
        curr.markAsDone();
        storage.store(tasks);
        return guiResponse.getMark(curr, index);
    }

    private String getListResponse() {
        return guiResponse.getTaskList(tasks);
    }

    /**
     * duke.Main driver code for duke class.
     */
    public void run() throws IOException {
        ui.printWelcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            String str = bf.readLine();
            String command = parser.parseCommand(str);
            if (command.equals("list")) {
                processList();
            } else if (command.equals("mark")) {
                processMark();
            } else if (command.equals("unmark")) {
                processUnmark();
            } else if (command.equals("bye")) {
                processBye();
                isExit = true;
            } else if (command.equals("todo") || command.equals("deadline") || command.equals("event")) {
                processAddTask();
            } else if (command.equals("delete")) {
                processDeleteTask();
            } else if (command.equals("find")) {
                processFind();
            } else {
                //nothing found
                processInvalidCommand();
            }
        }

    }

    private static void processInvalidCommand() {
        System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    private void processFind() {
        String query = parser.parseQuery();
        ui.printQueryResult(tasks.searchTask(query));
    }

    private void processDeleteTask() {
        int index = parser.parseToIndex();
        Task curr = tasks.getTask(index);
        tasks.deleteTask(index);
        storage.store(tasks);
        ui.printDelete(curr, tasks.getSize());
    }

    private void processAddTask() {
        Task curr = parser.parseToTask();
        if (curr == null) {
            return;
        }
        tasks.addTask(curr);
        storage.store(tasks);
        ui.printAddTask(curr, tasks.getSize());
    }

    private void processBye() {
        ui.printGoodbyeMessage();
    }

    private void processUnmark() {
        int index = parser.parseToIndex();
        Task curr = tasks.getTask(index);
        curr.markAsNotDone();
        storage.store(tasks);
        ui.printUnmark(curr, index);
    }

    private void processMark() {
        int index = parser.parseToIndex();
        Task curr = tasks.getTask(index);
        curr.markAsDone();
        storage.store(tasks);
        ui.printMark(curr, index);
    }

    private void processList() {
        ui.printTaskList(tasks);
    }
}
