import command.Command;
import command.DeadlineCommand;
import command.DeleteCommand;
import command.ErrorCommand;
import command.EventCommand;
import command.ListCommand;
import command.MarkCommand;
import command.TerminateCommand;
import command.TodoCommand;
import command.UnmarkCommand;

import exception.FileErrorBotException;
import exception.IllegalExpressionBotException;
import exception.IncompleteBotException;

import parsers.Parser;
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
            IncompleteBotException, FileErrorBotException {
        if (str.isBlank()) {
            throw new IncompleteBotException("OOPS!!! There is no task specified.");
        }
        String[] strSplit = Parser.getSplitAtSpace(str);
        String taskName = Parser.getLeftOfSplit(strSplit);
        String remainder = "";
        if (strSplit.length > 1) {
            remainder = Parser.getRightOfSplit(strSplit);
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
                    taskSplit = Parser.getSplitAtBy(remainder);
                    if (taskSplit.length == 1) {
                        throw new IncompleteBotException("OOPS!!! The timing of a deadline cannot be empty.");
                    }
                    taskDetail = Parser.getLeftOfSplit(taskSplit);
                    timeline = Parser.getRightOfSplit(taskSplit);
                    return new DeadlineCommand(this.taskList, taskDetail, timeline);
                }
            case ControlFlow.EVENT:
                if (remainder.isBlank()) {
                    throw new IncompleteBotException("OOPS!!! The description of an event cannot be empty.");
                } else {
                    taskSplit = Parser.getSplitAtFrom(remainder);
                    if (taskSplit.length == 1) {
                        throw new IncompleteBotException("OOPS!!! The starting timing of an event cannot be empty.");
                    }
                    taskDetail = Parser.getLeftOfSplit(taskSplit);
                    timeline = Parser.getRightOfSplit(taskSplit);
                    timelineArr = Parser.getSplitAtTo(timeline);
                    if (timelineArr.length == 1) {
                        throw new IncompleteBotException("OOPS!!! The ending timing of an event cannot be empty.");
                    }
                    timeFrom = Parser.getLeftOfSplit(timelineArr);
                    timeTo = Parser.getRightOfSplit(timelineArr);
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
                return new ErrorCommand();
        }
    }
}
