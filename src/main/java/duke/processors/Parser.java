package duke.processors;

import duke.exception.DukeNoDateException;
import duke.exception.DukeNoDescriptionException;
import duke.exception.DukeUnknownInstruction;
import duke.task.*;

public class Parser {
    private final TaskList tasks;
    private final FileHandler duke = new FileHandler();
    private final Ui ui = new Ui();
    private boolean terminate;

    public Parser(TaskList tasks) {
        this.tasks = tasks;
        terminate = false;
    }

    private void displayInfo(String msg) throws DukeUnknownInstruction
            , DukeNoDescriptionException, DukeNoDateException{
        Task task;

        if (msg.startsWith("todo")) {
            task = new ToDo(msg);
            tasks.addTasks(task);
        } else if (msg.startsWith("deadline")) {
            task = new Deadline(msg);
            tasks.addTasks(task);
        } else if (msg.startsWith("event")) {
            task = new Event(msg);
            tasks.addTasks(task);
        } else {
            throw new DukeUnknownInstruction();
        }
        duke.writeFile(task.toString());
        ui.printNumOfTasks(tasks);
    }

    public void readInputs(String msg) throws DukeUnknownInstruction,
            DukeNoDescriptionException, DukeNoDateException{
        if (msg.equals("list")) {

            tasks.listTasks();

        } else if (msg.equals("bye")) {
            this.terminate = true;
        } else {
            boolean isKeyword = msg.matches(".*\\040[0-9]");
            if (isKeyword) {
                String[] part = msg.split("\\s+");
                int ind = Integer.parseInt(part[1]) - 1;
                try {
                    if (ind > tasks.getCount() || ind < 0) {
                        throw new IndexOutOfBoundsException();
                    }
                    switch (part[0]) {
                    case "mark":
                        tasks.get(ind).MarkAsDone(duke);
                        break;
                    case "unmark":
                        tasks.get(ind).MarkAsUnDone(duke);
                        break;
                    case "delete":
                        tasks.deleteTask(ind, duke);
                        break;
                    }
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("The given index is not in the available range");
                }
            } else {
                displayInfo(msg);
            }
        }
    }

    public boolean getTerminate() {

        return this.terminate;
    }
}
