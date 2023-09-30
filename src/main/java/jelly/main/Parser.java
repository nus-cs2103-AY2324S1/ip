package jelly.main;

import jelly.commands.ByeCommand;
import jelly.commands.Command;
import jelly.commands.DeadlineCommand;
import jelly.commands.DeleteCommand;
import jelly.commands.EventCommand;
import jelly.commands.FindCommand;
import jelly.commands.ListCommand;
import jelly.commands.MarkCommand;
import jelly.commands.PriorityCommand;
import jelly.commands.ToDoCommand;
import jelly.commands.UnmarkCommand;
import jelly.exceptions.JellyBlankMessageException;
import jelly.exceptions.JellyException;
import jelly.exceptions.JellyUnknownCommandException;

/**
 * Responsible for parsing commands from the user, creates a Command object.
 */
public class Parser {

    /**
     * Parses the command inputted.
     *
     * @param command The user's input etc. list, bye.
     * @return An instance of Command that matches the user's input.
     * @throws JellyException If the input is invalid.
     */
    public static Command parse(String command) throws JellyException {
        assert command != null : "Command cannot be empty";
        try {
            String[] stringArray = command.split(" ");
            if (stringArray[0].equals("bye")) {
                return new ByeCommand();
            }
            if (stringArray[0].equals("list")) {
                return new ListCommand();
            } else if (stringArray[0].startsWith("mark")) {
                if (stringArray.length == 1) {
                    throw new JellyBlankMessageException("mark");
                }
                return new MarkCommand(Integer.parseInt(stringArray[1]));
            } else if (stringArray[0].equals("unmark")) {
                if (stringArray.length == 1) {
                    throw new JellyBlankMessageException("unmark");
                }
                return new UnmarkCommand(Integer.parseInt(stringArray[1]));
            } else if (stringArray[0].equals("find")) {
                if (stringArray.length == 1) {
                    throw new JellyBlankMessageException("find");
                }
                return new FindCommand(stringArray[1]);
            } else if (stringArray[0].equals("priority")) {
                if (stringArray.length == 1) {
                    throw new JellyBlankMessageException("priority");
                }
                return new PriorityCommand(Integer.parseInt(stringArray[1]), Integer.parseInt(stringArray[2]));
            } else if (stringArray[0].equals("todo")) {
                String toDoString = "";
                if (stringArray.length == 1) {
                    throw new JellyBlankMessageException("todo");
                }
                for (int i = 1; i < stringArray.length; i++) {
                    if (stringArray[i] != null) {
                        toDoString += stringArray[i] + " ";
                    } else {
                        toDoString = toDoString.trim();
                        break;
                    }
                }
                return new ToDoCommand(toDoString);
            } else if (stringArray[0].equals("deadline")) {
                if (stringArray.length == 1) {
                    throw new JellyBlankMessageException("deadline");
                }

                String deadlineString = "";
                String byWhen = "";

                for (int i = 1; i < stringArray.length; i++) {
                    if (stringArray[i] != null) {
                        if (stringArray[i].equals("/by")) {
                            for (int j = i + 1; j < stringArray.length; j++) {
                                byWhen += stringArray[j] + " ";
                            }
                            byWhen = byWhen.trim();
                            break;
                        }
                        deadlineString += stringArray[i] + " ";
                    } else {
                        deadlineString = deadlineString.trim();
                        break;
                    }
                }
                return new DeadlineCommand(deadlineString, byWhen);
            } else if (stringArray[0].equals("event")) {
                if (stringArray.length == 1) {
                    throw new JellyBlankMessageException("event");
                }

                String eventString = "";
                String fromWhen = "";
                String toWhen = "";
                //checking if the string contains /from and /to
                for (int i = 1; i < stringArray.length; i++) {
                    if (stringArray[i] != null) {
                        if (stringArray[i].equals("/from")) {
                            for (int j = i + 1; j < stringArray.length; j++) {
                                if (stringArray[j].equals("/to")) {
                                    for (int k = j + 1; k < stringArray.length; k++) {
                                        toWhen += stringArray[k] + " ";
                                    }
                                    toWhen = toWhen.trim();
                                    break;
                                }
                                fromWhen += stringArray[j] + " ";
                            }
                            fromWhen = fromWhen.trim();
                            break;
                        }
                        eventString += stringArray[i] + " ";
                    } else {
                        eventString = eventString.trim();
                        break;
                    }
                }
                return new EventCommand(eventString, fromWhen, toWhen);
            } else if (stringArray[0].equals("delete")) {
                if (stringArray.length == 1) {
                    throw new JellyBlankMessageException("delete");
                }
                return new DeleteCommand(Integer.parseInt(stringArray[1]));
            } else {
                throw new JellyUnknownCommandException();
            }
        } catch (JellyException e) {
            System.out.println(e.getMessage());
        }
        throw new JellyUnknownCommandException();
    }
}
