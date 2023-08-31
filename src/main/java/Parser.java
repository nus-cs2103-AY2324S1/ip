import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Parser {
    private String input;
    public Parser(String input) {
        this.input = input;
    }

    public static void parse(String userInput) throws MaxException {


//            String userInput = input.nextLine();


            String command = userInput.split(" ")[0];
            Command commandEnum;

            if (command.equals("bye")) {
                // User wants to exit the chatbot
                commandEnum = Command.BYE;
            } else if (command.equals("list")) {
                commandEnum = Command.LIST;
            } else if (command.contains("unmark")) {
                commandEnum = Command.UNMARK;
            } else if (command.contains("mark")) {
                commandEnum = Command.MARK;
            } else if (command.contains("event") || command.contains("todo") ||
                    command.contains("deadline")) {
                commandEnum = Command.ADD;
            } else if (command.contains("delete")) {
                commandEnum = Command.DELETE;
            } else {
                commandEnum = Command.UNKNOWN;
            }

            switch (commandEnum) {
                case BYE:
//                    ui.exit();
//                    scannerOpen = false;
                    break;
                case LIST:
                    TaskList.list();
                    break;
                case ADD:
                    TaskList.add(userInput);
                    break;
                case UNMARK:
                    int unmarkNumber = parseInt(userInput.substring(7));
                    TaskList.myList.get(unmarkNumber - 1).unmark();
                    break;
                case MARK:
                    int markNumber = parseInt(userInput.substring(5));
                    TaskList.myList.get(markNumber - 1).mark();
                    break;
                case DELETE:
                    int deleteNumber = parseInt(userInput.substring(7));
                    TaskList.delete(deleteNumber);
                    break;
                case UNKNOWN:
                    throw new MaxException("     Oh no! I cannot recognise that command.");
                default:
                    break;
            }

        }
    }

