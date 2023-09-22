package bot;

import bot.command.Command;
import bot.command.DeadlineCommand;
import bot.command.DeleteCommand;
import bot.command.EventCommand;
import bot.command.ListCommand;
import bot.command.MarkCommand;
import bot.command.TerminateCommand;
import bot.command.TodoCommand;
import bot.command.UnmarkCommand;
import bot.command.FindCommand;

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

    public ControlFlow(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Returns the Command object that is to be executed based on user input.
     *
     * @param str the input by the user
     * @return Command the necessary tasks that needed to be executed as per the user's input
     * @throws IllegalExpressionBotException
     * @throws IncompleteBotException
     * @throws FileErrorBotException
     * @throws DateTimeParseBotException
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
        String[] taskSplit;
        String taskDetail;
        String timeline;
        String[] timelineArr;
        String timeFrom;
        String timeTo;
        switch(taskName) {
        case ControlFlow.TERMINATE:
            return new TerminateCommand();
        case ControlFlow.TODO:
            if (remainder.isBlank()) {
                throw new IncompleteBotException("OOPS!!! The description of a todo cannot be empty.");
            } else {
                return new TodoCommand(this.taskList, remainder);
            }
        case ControlFlow.DEADLINE:
            if (remainder.isBlank()) {
                throw new IncompleteBotException("OOPS!!! The description of a deadline cannot be empty.");
            } else {
                taskSplit = InputParser.getSplitAtBy(remainder);
                if (taskSplit.length == 1) {
                    throw new IncompleteBotException("OOPS!!! The timing of a deadline cannot be empty.");
                }
                taskDetail = InputParser.getLeftOfSplit(taskSplit);
                timeline = InputParser.getRightOfSplit(taskSplit);
                return new DeadlineCommand(this.taskList, taskDetail, timeline);
            }
        case ControlFlow.EVENT:
            if (remainder.isBlank()) {
                throw new IncompleteBotException("OOPS!!! The description of an event cannot be empty.");
            } else {
                taskSplit = InputParser.getSplitAtFrom(remainder);
                if (taskSplit.length == 1) {
                    throw new IncompleteBotException("OOPS!!! The starting timing of an event cannot be empty.");
                }
                taskDetail = InputParser.getLeftOfSplit(taskSplit);
                timeline = InputParser.getRightOfSplit(taskSplit);
                timelineArr = InputParser.getSplitAtTo(timeline);
                if (timelineArr.length == 1) {
                    throw new IncompleteBotException("OOPS!!! The ending timing of an event cannot be empty.");
                }
                timeFrom = InputParser.getLeftOfSplit(timelineArr);
                timeTo = InputParser.getRightOfSplit(timelineArr);
                return new EventCommand(this.taskList, taskDetail, timeFrom, timeTo);
            }
        case ControlFlow.MARK:
            if (remainder.isBlank()) {
                throw new IncompleteBotException("OOPS!!! The task number to mark cannot be empty.");
            } else {
                return new MarkCommand(this.taskList, remainder);
            }
        case ControlFlow.UNMARK:
            if (remainder.isBlank()) {
                throw new IncompleteBotException("OOPS!!! The task number to unmark cannot be empty.");
            } else {
                return new UnmarkCommand(this.taskList, remainder);
            }
        case ControlFlow.LIST:
            return new ListCommand(this.taskList);
        case ControlFlow.DELETE:
            if (remainder.isBlank()) {
                throw new IncompleteBotException("OOPS!!! The task number to unmark cannot be empty.");
            } else {
                return new DeleteCommand(this.taskList, remainder);
            }
        case ControlFlow.FIND:
            return new FindCommand(this.taskList, remainder);
        default:
            throw new IllegalExpressionBotException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
