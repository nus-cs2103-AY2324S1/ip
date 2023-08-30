import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.ui.Ui;
import duke.list.FunnyList;


public class Duke {
    private Storage storage;
    private FunnyList tasks;
    private Ui ui;
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasks = new FunnyList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            this.tasks = new FunnyList();
        }
    }

    public static void main(String[] args) {
        new Duke("./tasks.txt").run();
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.printLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e);
            } finally {
                ui.printLine();
            }
        }
    }
//    public static void main(String[] args) throws Exception {
//        Scanner scanner = new Scanner(System.in);
//        duke.list.FunnyList items = new duke.list.FunnyList();
//        printLine();
//        System.out.println("\tHello! I'm FUNNY.\n\tWhat can I do for you?");
//        printLine();
//        String input = scanner.nextLine().trim();
//
//        while (!input.equals("bye")) {
//            try {
//                if (input.equals("duke.list")) {
//
//                    items.displayList();
//                } else {
//                    String duke.command = input.split(" ")[0];
//                    if (duke.command.equals("mark")) {
//                        String[] data = input.split(" ", 2);
//                        if (data.length < 2) {
//                            throw new duke.exception.DukeException("There must be a selected duke.task");
//                        }
//                        items.completeTask(Integer.valueOf(data[1]) - 1);
//                    } else if (duke.command.equals("unmark")) {
//                        String[] data = input.split(" ", 2);
//                        if (data.length < 2) {
//                            throw new duke.exception.DukeException("There must be a selected duke.task");
//                        }
//                        items.undoTask(Integer.valueOf(data[1]) - 1);
//                    } else if (duke.command.equals("delete")) {
//                        String[] data = input.split(" ", 2);
//                        if (data.length < 2) {
//                            throw new duke.exception.DukeException("There must be a selected duke.task");
//                        }
//                        items.deleteTask(Integer.valueOf(data[1]) - 1);
//                    } else if (duke.command.equals("todo")) {
//                        String[] data = input.split(" ", 2);
//                        if (data.length < 2) {
//                            throw new duke.exception.DukeException("The description of a todo cannot be empty.");
//                        }
//                        items.add(new duke.task.ToDo(input.split(" ", 2)[1]));
//                    } else if (duke.command.equals("deadline")) {
//                        String[] data = input.split(" /by ", 2);
//                        if (data.length < 2) {
//                            throw new duke.exception.DukeException("A duke.task with deadline requires a /by (timedate) descriptor");
//                        }
//                        if (data[0].split(" ", 2).length < 2) {
//                            throw new duke.exception.DukeException("The description of a deadline cannot be empty.");
//                        }
//
//                        items.add(new duke.task.Deadline(data[0].split(" ", 2)[1], data[1]));
//                    } else if (duke.command.equals("event")) {
//                        String[] data = input.split(" /from ", 2);
//                        if (data.length < 2) {
//                            throw new duke.exception.DukeException("An event requires a /from (timedate) descriptor");
//                        }
//
//                        String[] period = data[1].split( "/to ");
//                        if (period.length < 2) {
//                            throw new duke.exception.DukeException("An event requires a /to (timedate) descriptor");
//                        }
//
//                        if (data[0].split(" ", 2).length < 2) {
//                            throw new duke.exception.DukeException("The description of an event cannot be empty.");
//                        }
//
//                        items.add(new duke.task.Event(data[0].split(" ", 2)[1], period));
//                    } else {
//                        throw new duke.exception.DukeException("I'm sorry, but I don't know what that means :-(");
//                    }
//                }
//            } catch (duke.exception.DukeException e) {
//                printLine();
//                System.out.println(e);
//                printLine();
//            } catch (NumberFormatException e) {
//                printLine();
//                System.out.println(new duke.exception.DukeException("Please specify the index of the duke.task (Numbers only)"));
//                printLine();
//            } catch (DateTimeException e) {
//                printLine();
//                System.out.println(new duke.exception.DukeException("Please represent time in a proper time format of yyyy-mm-dd"));
//            } finally {
//                input = scanner.nextLine().trim(); // Get next input
//            }
//        }
//        printLine();
//        System.out.println("\tBye. Hope to see you again soon!");
//        printLine();
//    }



}
