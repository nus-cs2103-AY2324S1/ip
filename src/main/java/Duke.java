import duke.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Main class for the duke application.
 */

public class Duke {
    private Parser parser;
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke() {
        ui = new Ui();
        parser = new Parser();
        storage = new Storage("./data/duke.txt");
        try {
            tasks = new TaskList(storage.load());
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
        String response = "";
        GuiResponse guiResponse = new GuiResponse();
        if (command.equals("list")) {
            response = guiResponse.getTaskList(tasks);
        } else if (command.equals("mark")) {
            int index = parser.parseToIndex();
            Task curr = tasks.getTask(index);
            curr.markAsDone();
            storage.store(tasks);
            response = guiResponse.getMark(curr, index);
        } else if (command.equals("unmark")) {
            int index = parser.parseToIndex();
            Task curr = tasks.getTask(index);
            curr.markAsNotDone();
            storage.store(tasks);
            response = guiResponse.getUnmark(curr, index);
        } else if (command.equals("bye")) {
            response = guiResponse.getGoodbyeMessage();
        } else if (command.equals("todo") || command.equals("deadline") || command.equals("event")) {
            Task curr = parser.parseToTask();
            if (curr == null) {
                //need some handling here todo
                return "";
            }
            tasks.addTask(curr);
            storage.store(tasks);
            response = guiResponse.getAddTask(curr, tasks.getSize());
        } else if (command.equals("delete")) {
            int index = parser.parseToIndex();
            Task curr = tasks.getTask(index);
            tasks.deleteTask(index);
            storage.store(tasks);
            response = guiResponse.getDelete(curr, tasks.getSize());
        } else if (command.equals("find")) {
            String query = parser.parseQuery();
            response = guiResponse.getQueryResult(tasks.searchTask(query));
        } else {
            //nothing found
            response = "OOPS!!! I'm sorry, but I don't know what that means :-(";
        }
        return response;
    }

    /**
     * Main driver code for duke class.
     */
    public void run() throws IOException {
        ui.printWelcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            String str = bf.readLine();
            String command = parser.parseCommand(str);
            if (command.equals("list")) {
                ui.printTaskList(tasks);
            } else if (command.equals("mark")) {
                int index = parser.parseToIndex();
                Task curr = tasks.getTask(index);
                curr.markAsDone();
                storage.store(tasks);
                ui.printMark(curr, index);
            } else if (command.equals("unmark")) {
                int index = parser.parseToIndex();
                Task curr = tasks.getTask(index);
                curr.markAsNotDone();
                storage.store(tasks);
                ui.printUnmark(curr, index);
            } else if (command.equals("bye")) {
                ui.printGoodbyeMessage();
                isExit = true;
            } else if (command.equals("todo") || command.equals("deadline") || command.equals("event")) {
                Task curr = parser.parseToTask();
                if (curr == null) {
                    continue;
                }
                tasks.addTask(curr);
                storage.store(tasks);
                ui.printAddTask(curr, tasks.getSize());
            } else if (command.equals("delete")) {
                int index = parser.parseToIndex();
                Task curr = tasks.getTask(index);
                tasks.deleteTask(index);
                storage.store(tasks);
                ui.printDelete(curr, tasks.getSize());
            } else if (command.equals("find")) {
                String query = parser.parseQuery();
                ui.printQueryResult(tasks.searchTask(query));
            } else {
                //nothing found
                System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }

    }
}
