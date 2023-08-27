import java.io.IOException;
import java.util.*;

/**
 * Handles user commands
 */
public class Parser {
    private Ui ui;
    private TaskList tasks;
    private Storage storage;

    public Parser(Ui ui, TaskList tasks, Storage storage) {
        this.ui = ui;
        this.tasks = tasks;
        this.storage = storage;
    }

    public void parse(Scanner sc) {
        while (true) {
            try {
                String nextLine = sc.nextLine();

                if (nextLine.equals("list")) {
                    ui.stringFormat(tasks.displayList());

                } else if (nextLine.contains("mark")) { // if command is to mark or unmark
                    String[] markIndex = nextLine.split(" ");
                    if (markIndex.length == 1) {
                        throw new MissingIndexException();
                    }
                    int index = 0;
                    try {
                        index = Integer.parseInt(markIndex[1]);
                    } catch (NumberFormatException e) {
                        throw new MissingIndexException();
                    }
                    boolean doneOrNot = true;
                    if (nextLine.contains("unmark")) {
                        doneOrNot = false;
                    }
                    ui.stringFormat(tasks.markDoneOrNot(index, doneOrNot));
                    storage.write(tasks.lst);

                } else if (nextLine.contains("delete")) {
                    String[] deleteIndex = nextLine.split(" ");
                    if (deleteIndex.length == 1) {
                        throw new MissingIndexException();
                    }
                    int index = 0;
                    try {
                        index = Integer.parseInt(deleteIndex[1]);
                    } catch (NumberFormatException e) {
                        throw new MissingIndexException();
                    }
                    ui.stringFormat(tasks.deleteTask(index));
                    storage.write(tasks.lst);
                } else if (nextLine.equals("bye")) {
                    sc.close();
                    break;
                } else { // if command is to add tasks
                    if (nextLine.isEmpty()) {
                        throw new NoSuchElementException();
                    }
                    ui.stringFormat(tasks.addToList(nextLine));
                    storage.write(tasks.lst);
                }
            }
            catch (NoSuchElementException e) {
                ui.stringFormat(new String[]{"Write something!"});
            } catch (MissingIndexException e) {
                ui.stringFormat(new String[]{e.message});
            } catch (IndexOutOfBoundsException e) {
                ui.stringFormat(new String[]{"Index provided is wrong!"});
            } catch (IOException e) {
                ui.showLoadingError();
            }
        }
    }
}
