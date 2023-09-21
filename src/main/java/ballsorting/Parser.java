package ballsorting;

import java.time.LocalDateTime;
import java.util.Scanner;

/**
 * Encapsulates the reading of user input.
 */
public class Parser {
    private Scanner sc;
    private Ui ui;
    public Parser() {
        this.ui = new Ui();
    }

    /**
     * Creates a new instance of the Parser.
     * @param sc Scans the user input.
     */
    public Parser(Scanner sc) {
        this.sc = sc;
    }

    /**
     * Parses the user input.
     * @param input Command entered by user.
     * @param taskList Tasks in the chatbot.
     */
    public String parseUserInput(String input, TaskList taskList) {
        try {
            StringBuilder command = new StringBuilder();
            int c = 0;
            while (c < input.length() && input.charAt(c) != ' ') {
                command.append(input.charAt(c));
                c++;
            }

            switch (command.toString()) {
            case "list":
                return ui.handleList(input, taskList);
            case "help":
                return ui.handleHelp();
            case "mark":
                return ui.handleMark(input, taskList);
            case "unmark":
                return ui.handleUnmark(input, taskList);
            case "delete":
                return ui.handleDelete(input, taskList);
            case "find":
                return ui.handleFind(input, taskList);
            case "todo":
                return ui.handleTodo(input, taskList);
            case "deadline":
                return ui.handleDeadline(input, taskList);
            case "event":
                return ui.handleEvent(input, taskList);
            default:
                throw new CustomError.commandNotFoundException();
            }
        } catch (CustomError e) {
            return e.getMessage();
        }

    }
    public void parseStoredTodo(String input, TaskList taskList) {
        int stat = Integer.parseInt(input.substring(2, 3));
        Task curr = new Todo(input.substring(4));
        if (stat == 1) {
            curr.markDone();
        }
        assert curr != null;
        taskList.addTask(curr);
    }
    public void parseStoredDeadline(String input, TaskList taskList) {
        int stat = Integer.parseInt(input.substring(2, 3));
        StringBuilder desc = new StringBuilder();
        StringBuilder start = new StringBuilder();
        Task curr;

        int i = "D | ".length(); //4
        while (input.charAt(i) != '|') {
            desc.append(input.charAt(i));
            i++;
        }
        i++;
        LocalDateTime endDateTime = LocalDateTime.parse(input.substring(i), Ballsorter.inputFormatter);
        curr = new Deadline(desc.toString(), endDateTime);

        if (stat == 1) {
            curr.markDone();
        }
        assert curr != null;
        taskList.addTask(curr);
    }
    public void parseStoredEvent(String input, TaskList taskList) {
        int stat = Integer.parseInt(input.substring(2, 3));
        StringBuilder desc = new StringBuilder();
        StringBuilder start = new StringBuilder();
        Task curr;

        int i = "E | ".length();
        while (input.charAt(i) != '|') {
            desc.append(input.charAt(i));
            i++;
        }
        i++;
        while (input.charAt(i) != '|') {
            start.append(input.charAt(i));
            i++;
        }
        i++;
        LocalDateTime startDateTime = LocalDateTime.parse(start.toString(), Ballsorter.inputFormatter);
        LocalDateTime endDateTime = LocalDateTime.parse(input.substring(i), Ballsorter.inputFormatter);
        assert startDateTime.isBefore(endDateTime);
        curr = new Event(desc.toString(), startDateTime, endDateTime);

        if (stat == 1) {
            curr.markDone();
        }
        assert curr != null;
        taskList.addTask(curr);
    }
}
