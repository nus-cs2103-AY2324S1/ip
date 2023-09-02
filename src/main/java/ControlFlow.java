import command.Command;
import command.DeadlineCommand;
import command.DeleteCommand;
import command.EventCommand;
import command.ListCommand;
import command.MarkCommand;
import command.TerminateCommand;
import command.TodoCommand;
import command.UnmarkCommand;

import exception.DateTimeParseBotException;
import exception.FileErrorBotException;
import exception.IllegalExpressionBotException;
import exception.IncompleteBotException;

import parsers.InputParser;
import task.TaskList;


public class ControlFlow {

    private static final String MARK = "mark";
    private static final String UNMARK = "unmark";
    private static final String LIST = "list";
    private static final String TODO = "todo";
    private static final String EVENT = "event";
    private static final String DEADLINE = "deadline";
    private static final String TERMINATE = "bye";
    private static final String DELETE = "delete";

    private final TaskList taskList;

    public ControlFlow(TaskList taskList) {
        this.taskList = taskList;
    }


    public Command execute(String str) throws IllegalExpressionBotException,
            IncompleteBotException, FileErrorBotException, DateTimeParseBotException {
        if (str.isBlank()) {
            throw new IncompleteBotException("OOPS!!! There is no task specified.");
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
        default:
            throw new IllegalExpressionBotException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
