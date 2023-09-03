package duke;

import command.Command;

import exception.*;

/**
 * Duke is the chatbot program.
 */
public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * The constructor of Duke.
     *
     * @param filePath The file path to be passed into to load the initial tasks.
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.taskList = new TaskList(storage.load());
    }

    /**
     * The main function which will run when the user starts the chatbot.
     * @param args
     */
    public static void main(String[] args) {
        new Duke("./data/paimon.txt").run();
    }

    /**
     * The method to run the chatbot program.
     */
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
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("☹ OOPS!!! Something went wrong D:"
                        + Ui.SEPARATOR);
                e.printStackTrace();
            } finally {
                storage.writeTasks(taskList);
            }
        }
    }
}

