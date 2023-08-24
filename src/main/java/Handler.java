public class Handler {
    private TaskList taskList;
    private Ui ui;

    public Handler(TaskList taskList, Ui ui) {
        this.taskList = taskList;
        this.ui = ui;
    }

    public String handleMark(String command) {
        String[] parsed = Parser.splitSpace(command);
        int ind = Integer.parseInt(parsed[1]);
        taskList.mark(ind-1);
        return ui.markText(taskList.getTask(ind-1));
    }

    public String handleUnmark(String command) {
        String[] parsed = Parser.splitSpace(command);
        int ind = Integer.parseInt(parsed[1]);
        taskList.unmark(ind-1);
        return ui.unmarkText(taskList.getTask(ind-1));
    }

    public String handleTodo(String command) {
        String[] parsed = Parser.splitSpace(command);
        ToDo todo = new ToDo(parsed[1]);
        this.taskList.addTask(todo);
        return ui.taskText(todo, taskList.getLength());
    }

    public String handleEvent(String command) {
        String[] parsed = Parser.splitEvent(command);
        Event event = new Event(parsed[0], parsed[1], parsed[2]);
        this.taskList.addTask(event);
        return ui.taskText(event, taskList.getLength());
    }

    public String handleDeadline(String command) {
        String[] parsed = Parser.splitDeadline(command);
        Deadline deadline = new Deadline(parsed[0], parsed[1]);
        this.taskList.addTask(deadline);
        return ui.taskText(deadline, taskList.getLength());
    }
}
