package parser;

import java.util.ArrayList;

import commands.ByeCommand;
import commands.Command;
import commands.DeadlineCommand;
import commands.DeleteCommand;
import commands.EventCommand;
import commands.FindCommand;
import commands.ListCommand;
import commands.MarkCommand;
import commands.TodoCommand;
import commands.UnmarkComment;
import duke.Duke;
import duke.DukeException;


/**
 * This class handles all string manipulation needed
 * to read user inputs.
 */
public class Parser {

    private String type = "";

    private enum Commands {
        bye("bye"),
        list("list"),
        mark("mark"),
        unmark("unmark"),
        deadline("deadline"),
        event("event"),
        todo("todo"),
        delete("delete"),
        find("find");

        private final String command;

        Commands(String command) {
            this.command = command;
        }

        @Override
        public String toString() {
            return command;
        }
    }

    /**
     * Parser empty constructor.
     */
    public Parser() {
    }

    /**
     * Parser constructor that takes in a String.
     * @param type The type of task.
     */
    public Parser(String type) {
        this.type = type;
    }

    /**
     * Returns the intended command after reading the user input.
     * @param input Input from the user.
     * @return The intended command.
     * @throws DukeException If user typed invalid commands.
     */
    public Command getCommand(String input) throws DukeException {
        String userCommand = this.splitter(input, " ").get(0);
        Commands command;
        try {
            command = Commands.valueOf(userCommand);
        } catch (IllegalArgumentException e) {
            throw new DukeException("Invalid command");
        }
        switch (command) {
        case bye: {
            return new ByeCommand();
        }
        case list: {
            return new ListCommand();
        }
        case unmark: {
            return parseUnmark(input);
        }
        case mark: {
            return parseMark(input);
        }
        case deadline: {
            return parseDeadline(input);
        }
        case event: {
            return parseEvent(input);
        }
        case todo: {
            return parseTodo(input);
        }
        case delete: {
            return parseDelete(input);
        }
        case find: {
            return parseFind(input);
        }
        default: {
            assert false;
            throw new DukeException("Something went wrong here");
        }
        }
    }

    /**
     * Converts the user input of different type of tasks into ArrayList.
     * @param input User input of the tasks.
     * @return Required data of task from the user input in ArrayList.
     * @throws DukeException If user typed invalid tasks commands.
     */
    public ArrayList<String> convertTaskInput(String input) throws DukeException {
        switch (type) {
        case "E": {
            return extractEvent(input);
        }
        case "D": {
            return extractDeadline(input);
        }
        case "T": {
            return extractTodo(input);
        }
        case "P": {
            return extractFind(input);
        }
        default: {
            assert false : type;
            throw new DukeException("Something went wrong here");
        }
        }
    }

    /**
     * Separating words from a string that has a divider between it and store the words in
     * ArrayList.
     * @param text The text to split.
     * @param cutOff The divider to cut the string.
     * @return Separated words stored in ArrayList from a string that was merged with a divider.
     */
    public ArrayList<String> splitter(String text, String cutOff) {
        ArrayList<String> res = new ArrayList<>();
        String resStrAftCutting = text;
        while (resStrAftCutting.contains(cutOff)) {
            String sub = resStrAftCutting.substring(0, resStrAftCutting.indexOf(cutOff));
            res.add(sub);
            resStrAftCutting = resStrAftCutting.substring(resStrAftCutting.indexOf(cutOff) + 1);
        }
        res.add(resStrAftCutting);
        return res;
    }

    private ArrayList<String> extractEvent(String input) throws DukeException {
        ArrayList<String> texts = new ArrayList<>();
        if (input.indexOf("/from") - 1 < 6) {
            throw new DukeException("Your Event name is blank");
        }
        String desc = input.substring(6, input.indexOf("/from") - 1);
        if (desc.isBlank() || desc.isEmpty()) {
            throw new DukeException("Your Event name is blank");
        }
        texts.add(desc);
        String from = input.substring(input.indexOf("/from") + 6, input.indexOf("/to") - 1);
        texts.add((from));
        String to = input.substring(input.indexOf("/to") + 4);
        texts.add(to);
        return texts;
    }

    private ArrayList<String> extractDeadline(String input) throws DukeException {
        ArrayList<String> texts = new ArrayList<>();
        if (input.indexOf("/by") - 1 < 9) {
            throw new DukeException("Your Deadline name is blank");
        }
        String desc = input.substring(9, input.indexOf("/by") - 1);
        if (desc.isBlank() || desc.isEmpty()) {
            throw new DukeException("Your Deadline name is blank");
        }
        texts.add(desc);
        String by = input.substring(input.indexOf("/by") + 4);
        texts.add(by);
        return texts;
    }

    private ArrayList<String> extractTodo(String input) throws DukeException {
        ArrayList<String> texts = new ArrayList<>();
        String desc = input.substring(input.indexOf("todo") + 5);
        if (desc.isBlank() || desc.isEmpty()) {
            throw new DukeException("Your Todo name is blank");
        }
        texts.add(desc);
        return texts;
    }

    private ArrayList<String> extractFind(String input) {
        ArrayList<String> texts = new ArrayList<>();
        String keyWord = splitter(input, " ").get(1);
        texts.add(keyWord);
        return texts;
    }

    private Command parseMark(String input) throws DukeException {
        if (input.equals(Commands.mark.toString())) {
            throw new DukeException("mark cannot be empty");
        }
        if (input.charAt(4) != ' ') {
            throw new DukeException("Invalid command");
        }
        int selected = Integer.parseInt(input.substring(5)) - 1;
        return new MarkCommand(selected);
    }

    private Command parseUnmark(String input) throws DukeException {
        if (input.equals(Commands.unmark.toString())) {
            throw new DukeException("unmark cannot be empty");
        }
        if (input.charAt(6) != ' ') {
            throw new DukeException("Invalid command");
        }
        int selected = Integer.parseInt(input.substring(7)) - 1;
        return new UnmarkComment(selected);
    }

    private Command parseDelete(String input) throws DukeException {
        if (input.equals(Commands.delete.toString())) {
            throw new DukeException("delete cannot be empty");
        }
        if (input.charAt(6) != ' ') {
            throw new DukeException("Invalid command");
        }
        int selected = Integer.parseInt(input.substring(input.indexOf("delete") + 7)) - 1;
        return new DeleteCommand(selected);
    }

    private Command parseFind(String input) throws DukeException {
        if (input.equals(Commands.find.toString())) {
            throw new DukeException("find cannot be empty");
        }
        if (input.charAt(4) != ' ') {
            throw new DukeException("Invalid command");
        }
        Parser parser = new Parser("P");
        ArrayList<String> texts = parser.convertTaskInput(input);
        return new FindCommand(texts.get(0));
    }

    private Command parseTodo(String input) throws DukeException {
        if (input.equals(Commands.todo.toString())) {
            throw new DukeException("Empty todo");
        }
        if (input.charAt(4) != ' ') {
            throw new DukeException("Invalid command");
        }
        Parser parser = new Parser("T");
        ArrayList<String> texts = parser.convertTaskInput(input);
        return new TodoCommand(texts);
    }

    private Command parseEvent(String input) throws DukeException {
        if (input.equals(Commands.event.toString())) {
            throw new DukeException("Empty event");
        }
        if (input.charAt(5) != ' ') {
            throw new DukeException("Invalid command");
        }
        if (!input.contains("to") || !input.contains("from")) {
            throw new DukeException("Event does not contain to or from");
        }
        Parser parser = new Parser("E");
        ArrayList<String> texts = parser.convertTaskInput(input);
        return new EventCommand(texts);
    }

    private Command parseDeadline(String input) throws DukeException {
        if (input.equals(Commands.deadline.toString())) {
            throw new DukeException("Empty deadline");
        }
        if (input.charAt(8) != ' ') {
            throw new DukeException("Invalid command");
        }
        if (!input.contains("/by")) {
            throw new DukeException("Deadline does not contain /by");
        }
        Parser parser = new Parser("D");
        ArrayList<String> texts = parser.convertTaskInput(input);
        return new DeadlineCommand(texts);

    }



}
