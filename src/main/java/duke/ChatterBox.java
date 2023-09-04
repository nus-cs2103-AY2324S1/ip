package duke;

import java.util.ArrayList;
import java.util.Scanner;

import java.io.IOException;


/**
 * An interactive digital task manager called ChatterBox.
 * This minimal chatbot can take in simple commands, remember
 * tasks even after termination, and manipulate them as per the 
 * user's requests. 
 */
public class ChatterBox {

    private Ui ui;
    private TaskList tl;
    private Storage store;

    /**
     * Constructs a ChatterBox object with all parameters specified.
     * @param ui The supplied Ui object.
     * @param tl The supplied TaskList object.
     * @param store The supplied Storage object.
     */
    ChatterBox(Ui ui, TaskList tl, Storage store) {
        this.ui = ui;
        this.tl = tl;
        this.store = store;
    }

    /**
     * Initializes a new ChatterBox object and calls the run() method.
     *
     * @throws DukeException For various Chat-specific errors.
     * @throws IOException If FileIO presents any issues during runtime.
     */
    public static void main(String[] args) throws DukeException, IOException {
        ChatterBox cb = new ChatterBox(new Ui(), new TaskList(), new Storage());
        cb.run();
    }

    /**
     * Runs the actual program.
     *
     * @throws DukeException For various Chat-specific errors.
     * @throws IOException If FileIO presents any issues during runtime.
     */
    public void run() throws DukeException, IOException {

        ArrayList<Task> taskList = this.tl.getTaskList();
        this.store.fileToTaskList(this.tl);
        ui.startScreen();


        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {

            Parser p = new Parser(sc.nextLine());
            String command = p.command();
            String fullLine = p.fullLine;

            if (command.equals("bye")) {
                ui.byeScreen();
                break;

            } else if (command.equals("list")) {
                ui.taskListPrinter(tl);

            } else if (command.equals("mark")) {
                tl.mark(p.num());
                this.store.taskListToFile(tl);
                ui.markPrinter(tl, p.num());

            } else if (command.equals("unmark")) {
                tl.unmark(p.num());
                this.store.taskListToFile(tl);
                ui.unmarkPrinter(tl, p.num());

            } else if (command.equals("delete")) {
                Task tempDelete = taskList.get(p.num());
                tl.remove(p.num());
                this.store.taskListToFile(tl);
                ui.removedTaskScreen(tempDelete, tl.size());

            } else if (command.equals("find")) {
                ui.findListPrinter(tl, p.word());

            } else {

                if (command.equals("todo")) {

                    ToDo tempToDo = p.parseTodo();
                    tl.add(tempToDo);
                    this.store.taskListToFile(tl);
                    ui.addedTaskScreen(tempToDo, tl.size());

                } else if (command.equals("deadline")) {

                    Deadline tempDeadline = p.parseDeadline();
                    tl.add(tempDeadline);
                    this.store.taskListToFile(tl);
                    ui.addedTaskScreen(tempDeadline, taskList.size());

                } else if (command.equals("event")) {

                    Event tempEvent = p.parseEvent();
                    tl.add(tempEvent);
                    this.store.taskListToFile(tl);
                    ui.addedTaskScreen(tempEvent, tl.size());

                } else {

                    ui.linePrinter();
                    throw new DukeException(ui.unknownError());
                }
            }
        }
    }

}
