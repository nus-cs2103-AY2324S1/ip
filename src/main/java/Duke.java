import exceptions.DukeException;
import exceptions.DukeInvalidInputException;

import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        Ui ui = new Ui();
        Storage storage = new Storage("./data/duke.txt");
        TaskList taskList = new TaskList(storage.load());
        boolean running = true;
        System.out.println(ui.startup());
        while (running) {
            try {
                String command = ui.getInput();
                Handler handler = new Handler(taskList, ui, storage);
                if (command.equals("bye")) {
                    System.out.println(ui.exit());
                    running = false;
                } else if (command.equals("list")) {
                    System.out.println(ui.getList(taskList));
                } else if (command.startsWith("mark ")) {
                    System.out.println(handler.handleMark(command));
                } else if (command.startsWith("unmark ")) {
                    System.out.println(handler.handleUnmark(command));
                } else if (command.startsWith("todo ")) {
                    System.out.println(handler.handleTodo(command));
                } else if (command.startsWith("event ")) {
                    System.out.println(handler.handleEvent(command));
                } else if (command.startsWith("deadline ")) {
                    System.out.println(handler.handleDeadline(command));
                } else if (command.startsWith("delete ")) {
                    System.out.println(handler.handleDelete(command));
                } else {
                    throw new DukeInvalidInputException();
                }
            } catch (DukeException e) {
                System.out.println(ui.errorMsg(e.getMessage()));
            }
        }
    }
}
