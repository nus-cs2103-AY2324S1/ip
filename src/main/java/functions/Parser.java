package functions;

import commands.*;


public class Parser {

    private String input;
    private TaskList taskList;

    private enum ChatFunction {
        LIST,
        MARK,
        UNMARK,
        TODO,
        DEADLINE,
        EVENT,
        DELETE,
        FIND
    }

    /**
     * A public constructor to initialize a new Parser instance
     *
     * @param input file path to load
     * @param taskList a task list containing all the tasks stored
     */
    public Parser(String input, TaskList taskList) {
        this.input = input;
        this.taskList = taskList;
    }

    public String parse() {
        String[] inputArray = input.split(" ");

        try {
            ChatFunction function = ChatFunction.valueOf(inputArray[0].toUpperCase());
            int firstSpaceIndex = input.indexOf(" ");
            String secondHalfInput = input.substring(firstSpaceIndex+1);
            Command command= null;

            switch (function) {
            case LIST:
                command = new ListCommand(this.taskList);
                break;

            case MARK:
                command = new MarkCommand(this.taskList, inputArray);
                break;

            case UNMARK:
                command = new UnmarkCommand(this.taskList, inputArray);
                break;

            case DELETE:
                command = new DeleteCommand(this.taskList, inputArray);
                break;

            case TODO:
                command = new ToDoCommand(this.taskList, secondHalfInput);
                break;

            case DEADLINE:
                command = new DeadlineCommand(this.taskList, secondHalfInput);
                break;

            case EVENT:
                command  = new EventCommand(this.taskList, secondHalfInput);
                break;

            case FIND:
                command  = new FindCommand(this.taskList, secondHalfInput);

            default:
                break;
            }
            String result = command.execute();
            return result;
        } catch (IllegalArgumentException e) {
            // If task inserted not an ENUM
            String illegalArgumentResult = "";
            illegalArgumentResult += "Oops!!! I'm sorry but I don't know what that means :-( \n";
            illegalArgumentResult += "Please use one of the following commands: list, mark, unmark," +
                    " delete, todo, deadline, event, bye";
            return illegalArgumentResult;

        } catch (NullPointerException e) {
            String nullPointerResult = "";
            nullPointerResult += "Oh no, you have entered an invalid statement. \n";
            nullPointerResult += "Please use one of the following commands: list, mark, unmark," +
                    " delete, todo, deadline, event, bye";
            return nullPointerResult;
        } catch (Exception e) {
            return "Please use one of the following commands: list, mark, unmark," +
                    " delete, todo, deadline, event, bye";
        }
    }

}
