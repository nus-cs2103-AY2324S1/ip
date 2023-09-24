package dogebot;

/**
 * Parser class handles user commands and calls the next appropriate method for its respective action.
 *
 * @author Kenvyn Kwek
 */
public class Parser {
    /**
     * Reads user command and calls the next appropriate method for its respective action. If "bye" command is entered,
     * false is returned to end the DogeBot.run() method's input loop.
     *
     * @return Loop status.
     */
    public String scan(String input) {
        String commandType = input.split(" ")[0];
        String result = "";

        try {
            switch (commandType.toLowerCase()) {
            case "bye":
                result = "Bye~ See you again";
                break;
            case "list":
                result = TaskList.list();
                break;
            case "mark":
                result = TaskList.mark(input);
                break;
            case "unmark":
                result = TaskList.unmark(input);
                break;
            case "todo":
                result = TaskList.todo(input);
                break;
            case "deadline":
                result = TaskList.deadline(input);
                break;
            case "event":
                result = TaskList.event(input);
                break;
            case "delete":
                result = TaskList.delete(input);
                break;
            case "find":
                result = TaskList.find(input);
                break;
            case "reminder":
                result = TaskList.reminder();
                break;
            default:
                result = "Wuff, I'm not sure what that means :(";
            }
        } catch (IndexOutOfBoundsException e) {
            return "Oh no :( I think that number is too big~";
        } catch (DogeBotException e) {
            return e.getMessage();
        }

        return result;
    }
}
