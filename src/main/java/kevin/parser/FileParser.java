package kevin.parser;

import java.util.ArrayList;

import kevin.exception.KevinException;

/**
 * A class to handle the parsing of text from the kevin.txt file.
 */
public class FileParser {
    /**
     * Parses the text that is taken from the kevin.txt file to make it a QueryObject.
     * @param input This is the text input that needs to be parsed.
     * @return Returns a QueryObject containing the Command and arguments.
     * @throws KevinException On the detection of errors.
     */
    public QueryObject parseLine(String input) throws KevinException {
        String[] querySplit = input.trim().split(" - ", 3);

        if (querySplit.length == 0) {
            throw new KevinException("Command is not found.");
        }

        Command command;
        boolean isDone;
        ArrayList<String> args = new ArrayList<>();

        try {
            command = Command.valueOf(querySplit[0].toUpperCase());
            isDone = Boolean.parseBoolean(querySplit[1]);
            args.add(String.valueOf(isDone));
        } catch (IllegalArgumentException err) {
            throw new KevinException("Command is invalid.");
        }

        if (command == Command.TODO) {
            if (querySplit.length != 3) {
                throw new KevinException("Todo command needs to follow \"Todo - {isDone} - {todo name}.\"");
            }
            args.add(querySplit[2]);
        } else if (command == Command.DEADLINE) {
            String[] argumentSplit = querySplit[2].split(" - ", 2);
            if (argumentSplit.length < 2) {
                throw new KevinException(String.format(new StringBuilder().append("Deadline command needs to ")
                        .append("follow \"Deadline - {isDone} - {deadline name} - {deadline date}\".").toString()));
            }
            args.add(argumentSplit[0]);
            args.add(argumentSplit[1]);
        } else if (command == Command.EVENT) {
            String[] argumentSplit = querySplit[2].split(" - ", 3);
            if (argumentSplit.length < 3) {
                throw new KevinException(new StringBuilder().append("Event command needs to follow ")
                        .append("\"Event - {isDone} - {deadline name} - {event start time} - {event end time}\".")
                        .toString());
            }
            args.add(argumentSplit[0]);
            args.add(argumentSplit[1]);
            args.add(argumentSplit[2]);
        }
        return new QueryObject(command, args);
    }
}
