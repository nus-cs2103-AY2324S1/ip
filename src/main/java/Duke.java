import command.Command;

import duke.Parser;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

import exception.*;

public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.taskList = new TaskList(storage.load());
    }
    public static void main(String[] args) {
        new Duke("./data/paimon.txt").run();
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                Parser parser = new Parser(this.taskList, this.ui);
                String fullCommand = ui.readCommand();
                Command c = parser.parse(fullCommand);
                c.execute(taskList, ui);
                isExit = c.isExit();
            } catch (EmptyInputException e) {
                System.out.println(e.getMessage());
            } catch (EmptyDateTimeException e) {
                System.out.println(e.getMessage());
            } catch (InvalidFormatException e) {
                System.out.println(e.getMessage());
            } catch (InvalidCommandException e) {
                System.out.println(e.getMessage());
            } catch (InvalidDateTimeException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("☹ OOPS!!! Something went wrong D:"
                        + Ui.SEPARATOR);
                // e.printStackTrace();
            } finally {
                storage.writeTasks(taskList);
            }
        }
    }
}

