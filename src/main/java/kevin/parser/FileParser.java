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

        Commands command;
        boolean isDone;
        ArrayList<String> args = new ArrayList<>();

        try {
            command = Commands.valueOf(querySplit[0].toUpperCase());
            isDone = Boolean.parseBoolean(querySplit[1]);
            args.add(String.valueOf(isDone));
        } catch (IllegalArgumentException err) {
            throw new KevinException("Command is invalid.");
        }

        if (command == Commands.TODO) {
            if (querySplit.length != 3) {
                throw new KevinException("Todo command needs to follow \"Todo - {isDone} - {todo name}.\"");
            }
            args.add(querySplit[2]);
        } else if (command == Commands.DEADLINE) {
            String[] argumentSplit = querySplit[2].split(" - ");
            if (argumentSplit.length != 2) {
                throw new KevinException("Deadline command needs to follow " +
                        "\"Deadline - {isDone} - {deadline name} - {deadline date}\".");
            }
            args.add(argumentSplit[0]);
            args.add(argumentSplit[1]);
        } else if (command == Commands.EVENT) {
            String[] argumentGetName = querySplit[2].split(" - ", 2);
            if (argumentGetName.length != 2) {
                throw new KevinException("Event command needs to follow " +
                        "\"Event - {isDone} - {deadline name} - {event start time} - {event end time}\".");
            }
            String[] argumentGetDate = argumentGetName[2].split(" - ", 2);
            if (argumentGetDate.length != 2) {
                throw new KevinException("Event command needs to follow " +
                        "\"Event - {isDone} -{deadline name} - {event start time} - {event end time}\".");
            }
            args.add(argumentGetName[0]);
            args.add(argumentGetDate[0]);
            args.add(argumentGetDate[1]);
        }
        return new QueryObject(command, args);
    }
}
