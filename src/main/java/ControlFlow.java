import Commands.*;
import Task.*;


public class ControlFlow {

    private static final String MARK = "mark";
    private static final String UNMARK = "unmark";
    private static final String LIST = "list";
    private static final String TODO = "todo";
    private static final String EVENT = "event";
    private static final String DEADLINE = "deadline";
    private static final String TERMINATE = "bye";

    private final TaskList taskList;

    public ControlFlow(TaskList taskList) {
        this.taskList = taskList;
    }


    public Command execute(String str) {
        String taskName = Parser.taskInfo(str);
        String remainder = Parser.remainderInfo(str);
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
                return new TodoCommand(this.taskList, remainder);
            case ControlFlow.DEADLINE:
                taskSplit = Parser.getSplitAtBy(remainder);
                taskDetail = Parser.getLeftOfSplit(taskSplit);
                timeline = Parser.getRightOfSplit(taskSplit);
                return new DeadlineCommand(this.taskList, taskDetail, timeline);
            case ControlFlow.EVENT:
                taskSplit = Parser.getSplitAtFrom(remainder);
                taskDetail = Parser.getLeftOfSplit(taskSplit);
                timeline = Parser.getRightOfSplit(taskSplit);
                timelineArr = Parser.getSplitAtTo(timeline);
                timeFrom = Parser.getLeftOfSplit(timelineArr);
                timeTo = Parser.getRightOfSplit(timelineArr);
                return new EventCommand(this.taskList, taskDetail, timeFrom, timeTo);
            case ControlFlow.MARK:
                return new MarkCommand(this.taskList, remainder);
            case ControlFlow.UNMARK:
                return new UnmarkCommand(this.taskList, remainder);
            case ControlFlow.LIST:
                return new ListCommand(this.taskList);
            default:
                return new EchoCommand(str);
        }
    }
}
