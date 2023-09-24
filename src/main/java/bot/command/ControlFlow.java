package bot.command;

import bot.exception.DateTimeParseBotException;
import bot.exception.FileErrorBotException;
import bot.exception.IllegalExpressionBotException;
import bot.exception.IncompleteBotException;

import bot.parsers.InputParser;
import bot.task.TaskList;


public class ControlFlow {

    private static final String MARK = "mark";
    private static final String UNMARK = "unmark";
    private static final String LIST = "list";
    private static final String TODO = "todo";
    private static final String EVENT = "event";
    private static final String DEADLINE = "deadline";
    private static final String TERMINATE = "bye";
    private static final String DELETE = "delete";
    private static final String FIND = "find";

    private final TaskList taskList;

    /**
     * Creates an instance of a ControlFlow object
     *
     * @param taskList the list of tasks
     */
    public ControlFlow(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Returns the Command object that is to be executed based on user input.
     *
     * @param str the input by the user
     * @return Command the necessary tasks that needed to be executed as per the user's input
     * @throws IllegalExpressionBotException if the instruction name is not correct
     * @throws IncompleteBotException if the instruction name is correct, but there are missing info
     * @throws FileErrorBotException if the file is suddenly missing when trying to access it
     * @throws DateTimeParseBotException if the datetime is wrongly formatted
     */
    public Command execute(String str) throws IllegalExpressionBotException,
            IncompleteBotException, FileErrorBotException, DateTimeParseBotException {
        if (str.isBlank()) {
            throw new IncompleteBotException("OOPS!!! There is no bot.task specified.");
        }
        String[] strSplit = InputParser.getSplitAtSpace(str);
        String taskName = InputParser.getLeftOfSplit(strSplit);
        String remainder = "";
        if (strSplit.length > 1) {
            remainder = InputParser.getRightOfSplit(strSplit);
        }
        switch(taskName) {
        case ControlFlow.TERMINATE:
            return new TerminateCommand();
        case ControlFlow.TODO:
            return new TodoCommand(this.taskList, remainder);
        case ControlFlow.DEADLINE:
            return new DeadlineCommand(this.taskList, remainder);
        case ControlFlow.EVENT:
            return new EventCommand(this.taskList, remainder);
        case ControlFlow.MARK:
            return new MarkCommand(this.taskList, remainder);
        case ControlFlow.UNMARK:
            return new UnmarkCommand(this.taskList, remainder);
        case ControlFlow.LIST:
            return new ListCommand(this.taskList);
        case ControlFlow.DELETE:
            return new DeleteCommand(this.taskList, remainder);
        case ControlFlow.FIND:
            return new FindCommand(this.taskList, remainder);
        default:
            throw new IllegalExpressionBotException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
