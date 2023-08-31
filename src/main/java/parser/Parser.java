package parser;

import commands.*;
import duke.Duke;
import duke.DukeException;
import tasks.Task;

import java.util.ArrayList;

public class Parser {

    private String type = "";

    private enum command {
        bye("bye"),
        list("list"),
        mark("mark"),
        unmark("unmark"),
        deadline("deadline"),
        event("event"),
        todo("todo"),
        delete("delete");

        private final String command;

        command(String command) {
            this.command = command;
        }

        @Override
        public String toString() {
            return command;
        }
    }

    public Parser(){
    }

    public Parser(String type) {
        this.type = type;
    }

    public Command getCommand(String input) throws DukeException {
        if (input.equals(command.bye.toString())) {
            return new ByeCommand();

        } else if (input.equals(command.list.toString())) {
            return new ListCommand();

        } else if (input.contains(command.unmark.toString())) {
            int selected = Integer.parseInt(input.substring(7)) - 1;
            return new UnmarkComment(selected);

        } else if (input.contains(command.mark.toString())) {
            int selected = Integer.parseInt(input.substring(5)) - 1;
            return new MarkCommand(selected);

        } else if (input.contains(command.deadline.toString())) {
            if (input.equals(command.deadline.toString())) {
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

        } else if (input.contains(command.event.toString())) {
            if (input.equals(command.event.toString())) {
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

        } else if (input.contains(command.todo.toString())) {
            if (input.equals(command.todo.toString())) {
                throw new DukeException("Empty todo");
            }
            if (input.charAt(4) != ' ') {
                throw new DukeException("Invalid command");
            }
            Parser parser = new Parser("T");
            ArrayList<String> texts = parser.convertTaskInput(input);
            return new TodoCommand(texts);

        } else if (input.contains(command.delete.toString())) {
            int selected = Integer.parseInt(input.substring(input.indexOf("delete") + 7)) - 1;
            return new DeleteCommand(selected);
        } else {
            throw new DukeException("Invalid command");
        }
    }

    public ArrayList<String> convertTaskInput(String input) throws DukeException {
        ArrayList<String> texts = new ArrayList<>();
        switch (type) {
        case "E": {
            String desc = input.substring(6, input.indexOf("/from") - 1);
            if (desc.isBlank() || desc.isEmpty()) {
                throw new DukeException("Your Event name is blank");
            }
            texts.add(desc);
            String from = input.substring(input.indexOf("/from") + 6, input.indexOf("/to") - 1);
            texts.add((from));
            String to = input.substring(input.indexOf("/to") + 4);
            texts.add(to);
            break;
        }
        case "D": {
            String desc = input.substring(9, input.indexOf("/by") - 1);
            if (desc.isBlank() || desc.isEmpty()) {
                throw new DukeException("Your Deadline name is blank");
            }
            texts.add(desc);
            String by = input.substring(input.indexOf("/by") + 4);
            texts.add(by);
            break;
        }
        case "T": {
            String desc = input.substring(input.indexOf("todo") + 5);
            if (desc.isBlank() || desc.isEmpty()) {
                throw new DukeException("Your Todo name is blank");
            }
            texts.add(desc);
            break;
        }
        }
       return texts;
    }

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

}
