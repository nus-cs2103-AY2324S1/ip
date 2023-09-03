import java.util.ArrayList;
import java.util.Scanner;

import java.io.File;
import java.io.IOException;


public class ChatterBox {

    private Ui ui;
    private TaskList tl;
    private Storage store;

    ChatterBox(Ui ui, TaskList tl, Storage store) {
        this.ui = ui;
        this.tl = tl;
        this.store = store;
    }

    public static void main(String[] args) throws DukeException, IOException {
        ChatterBox cb = new ChatterBox(
                new Ui(),
                new TaskList(),
                new Storage());

        cb.run();
    }

    public void run() throws DukeException, IOException {

        ArrayList<Task> taskList = this.tl.getTaskList();
        this.store.fileToTaskList(this.tl);
        ui.startScreen();


        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            /*String fullLine = sc.nextLine();
            String[] inputLine = fullLine.split(" ");
            String input = inputLine[0];
            */

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

            } else {

                if (command.equals("todo")) {
                    if (fullLine.split("todo ").length < 1) {
                        ui.todoErrorPrinter();
                        throw new DukeException(ui.todoErrorString());
                    }

                    String taskName = fullLine.split("todo ")[1];
                    ToDo tempToDo = new ToDo(taskName);
                    tl.add(tempToDo);
                    this.store.taskListToFile(tl);

                    ui.addedTaskScreen(tempToDo, tl.size());


                } else if (command.equals("deadline")) {

                    Deadline tempDeadline = p.parseDeadline();
                    tl.add(tempDeadline);
                    this.store.taskListToFile(tl);
                    ui.addedTaskScreen(tempDeadline, taskList.size());

                } else if (command.equals("event")) {
                    if (fullLine.split("/").length < 3) {
                        ui.eventErrorPrinter();
                        throw new DukeException(ui.eventErrorString());
                    }
                    String[] longNameArray = fullLine.split("/");
                    String longName = longNameArray[0];
                    String fromTime = longNameArray[1];
                    String endTime = longNameArray[2];
                    String taskName = longName.split("event ")[1];
                    String eventName = taskName +
                            String.format("(from: %s to: %s)",
                                    fromTime, endTime);
                    Event tempEvent = new Event(eventName);
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
