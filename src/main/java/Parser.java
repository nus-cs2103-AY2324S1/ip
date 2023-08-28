public class Parser {
    TaskList taskList;

    public Parser(TaskList taskList) {
        this.taskList = taskList;
    }

    public boolean parse(String s) throws BrunoException {
        TaskType type = TaskType.valueOf(s.split(" ")[0].toUpperCase());
        switch (type) {
            case BYE:
                return false;
            case LIST:
                taskList.displayList();
                return true;
            case MARK:
                taskList.markTask(s);
                return true;
            case UNMARK:
                taskList.unmarkTask(s);
                return true;
            case DELETE:
                taskList.deleteTask(s);
                taskList.displayListSum();
                return true;
            case TODO:
                taskList.addToDo(s);
                taskList.displayListSum();
                return true;
            case DEADLINE:
                taskList.addDeadline(s);
                taskList.displayListSum();
                return true;
            case EVENT:
                taskList.addEvent(s);
                taskList.displayListSum();
                return true;
            case SCHEDULE:
                taskList.showSchedule(s);
                return true;
            default:
                throw new BrunoUnknownTaskException();
        }
    }
}
