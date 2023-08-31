import java.util.Scanner;
import java.time.format.DateTimeParseException;

import static java.lang.Integer.parseInt;

public class Max {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    public Max(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (MaxException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }
    public void run() {
        ui.showGreeting();
        boolean isExit = false;
        while (!isExit) {
            try {
                Parser parser = new Parser();
                String fullCommand = ui.readCommand(); // return the first word of input
                Command c = parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (MaxException e) {
                ui.showError(e.getMessage());
            } catch (DateTimeParseException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }

//
//        boolean scannerOpen = true;
//
//        while (scannerOpen) {
//            Parser parser = new Parser(userInput);
//            try {
//                CommandEnum commandEnum = parser.parse(userInput);
//                switch (commandEnum) {
//                case BYE:
//                    input.close();
//                    ui.exit();
//                    storage.writeToFile(tasks);
//                    scannerOpen = false;
//                    break;
//                case LIST:
//                    tasks.list();
//                    break;
//                case ADD:
//                    tasks.add(userInput);
//                    break;
//                case UNMARK:
//                    int unmarkNumber = parseInt(userInput.substring(7));
//                    tasks.myList.get(unmarkNumber - 1).unmark();
//                    break;
//                case MARK:
//                    int markNumber = parseInt(userInput.substring(5));
//                    tasks.myList.get(markNumber - 1).mark();
//                    break;
//                case DELETE:
//                    int deleteNumber = parseInt(userInput.substring(7));
//                    tasks.delete(deleteNumber);
//                    break;
//                case UNKNOWN:
//                    throw new MaxException("     Oh no! I cannot recognise that command.");
//                default:
//                    break;
//                }
//            } catch (MaxException e) {
//                System.out.println(e.getMessage());
//                ui.showLine();
//            } catch (DateTimeParseException e) {
//                System.out.println("Please use yyyy-mm-dd format!");
//            } finally {
//                ui.showLine();
//            }
        }
    public static void main(String[] args) {
        new Max("./data/max.txt").run();
    }
}
