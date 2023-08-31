package duke;
public class Parser {
    private Ui ui;
    private TaskList tasks;
    private boolean finish;

    /**
     * Constructor to create a Parser.
     *
     * @param ui Instance to handle the printing of user interface.
     * @param tasks Instance of TaskList to handle commands.
     */
    public Parser(Ui ui, TaskList tasks) {
        this.ui = ui;
        this.tasks = tasks;
        this.finish = false;
    }

    /**
     * Parses the user input and calls the appropriate task command.
     *
     * @param s User input picked up by the scanner.
     */
    public void parse(String s) {
        String[] input = s.split(" ", 2);
        String command = input[0];
        ui.horizontalLine();
        if (command.equals("list")) {
            tasks.showList(input);
        } else if (command.equals("mark")) {
            tasks.markTask(input);
        } else if (command.equals("unmark")) {
            tasks.unmarkTask(input);
        } else if (command.equals("delete")) {
            tasks.deleteTask(input);
        } else if (command.equals("deadline")) {
            tasks.addDeadline(input);
        } else if (command.equals("event")) {
            tasks.addEvent(input);
        } else if (command.equals("todo")) {
            tasks.addToDo(input);
        } else if (command.equals("find")) {
            tasks.find(input);
        } else if (command.equals("bye")){
            this.finish = true;
            ui.bye();
        } else {
            ui.oops();
        }
        ui.horizontalLine();
    }

    /**
     * Indicates if parsing should stop.
     *
     * @return A boolean value to indicate when to stop asking for user input.
     */
    public boolean isDone() {
        return finish;
    }
}
