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
        ChatterBox cb = new ChatterBox(new Ui(),
                new TaskList(), new Storage());
        cb.run();
    }

    public void run() throws DukeException, IOException {

        ArrayList<Task> taskList = this.tl.getTaskList();
        this.store.fileToTaskList(this.tl);
        ui.startScreen();


        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            String fullLine = sc.nextLine();
            String[] inputLine = fullLine.split(" ");
            String input = inputLine[0];

            if (input.equals("bye")) {
                ui.byeScreen();
                break;

            } else if (input.equals("list")) {
                ui.taskListPrinter(tl);

            } else if (input.equals("mark")) {
                int index = Integer.parseInt(inputLine[1]);
                tl.get(index - 1).mark();
                this.store.taskListToFile(tl);

                /*ui.linePrinter();
                ui.tabPrinter("Nice! I've marked this task as done:");
                ui.tabPrinter(taskList.get(index - 1).toString());
                ui.linePrinter();
                */
                ui.markPrinter(tl, index);

            } else if (input.equals("unmark")) {
                int index = Integer.parseInt(inputLine[1]);
                tl.get(index - 1).unmark();
                this.store.taskListToFile(tl);
                ui.linePrinter();
                ui.tabPrinter("OK, I've marked this task as not done yet:");
                ui.tabPrinter(taskList.get(index - 1).toString());
                ui.linePrinter();

            } else if (input.equals("delete")) {
                int index = Integer.parseInt(inputLine[1]);
                Task tempDelete = taskList.get(index - 1);
                tl.remove(index - 1);
                this.store.taskListToFile(tl);
                ui.linePrinter();
                ui.tabPrinter("Noted. I've removed this task:");
                ui.tabPrinter(tempDelete.toString());
                ui.linePrinter();

            } else {

                if (input.equals("todo")) {
                    if (fullLine.split("todo ").length < 1) {
                        ui.linePrinter();
                        ui.tabPrinter("The description of a todo cannot be empty!");
                        ui.linePrinter();
                        throw new DukeException(
                                "The description of a todo cannot be empty!");
                    }
                    String taskName = fullLine.split("todo ")[1];
                    ToDo tempToDo = new ToDo(taskName);
                    tl.add(tempToDo);
                    this.store.taskListToFile(tl);

                    ui.addedTaskScreen(tempToDo, tl.size());


                } else if (input.equals("deadline")) {
                    if (fullLine.split("/by ").length < 1) {
                        ui.slicePrinter("The due date of a deadline cannot be empty!");
                        throw new DukeException(
                                "The due date of a deadline cannot be empty!");
                    }
                    String longName = fullLine.split("/by ")[0];
                    String date = fullLine.split("/by ")[1];
                    String taskName = longName.split("deadline ")[1];
                    String deadlineName = taskName +
                            String.format("(by: %s)", date);
                    Deadline tempDeadline = new Deadline(deadlineName);
                    tl.add(tempDeadline);
                    this.store.taskListToFile(tl);


                    ui.addedTaskScreen(tempDeadline, taskList.size());

                } else if (input.equals("event")) {
                    if (fullLine.split("/").length < 3) {
                        ui.linePrinter();
                        ui.tabPrinter("An event must have both start and end date");
                        ui.linePrinter();
                        throw new DukeException(
                                "An event must have both start and end date");
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
                    throw new
                            DukeException("I'm sorry I don't know what that means.");
                }
            }
        }
    }

}
