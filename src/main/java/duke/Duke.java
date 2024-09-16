package duke;
import java.io.IOException;
import java.util.ArrayList;

// Solution below inspired by https://stackoverflow.com/questions/47150081/while-loop-for-multiple-inputs
// Solution below inspired by ChatGPT, to solve the issue in the else block of incrementing the num_items counter to add
// the new item subsequently.
/* Solution below inspired by ChatGPT, to solve the issue for the command list, to show the separators only at the start
and end, by moving
the statement outside the for loop
 */
// Solution below inspired by ChatGPT, regarding the method .contains("mark"), as it might be a bug if the input
// contains mark, without a corresponding integer.
// Solution below inspired by https://stackoverflow.com/questions/12973871/read-multiple-user-input-words-and-split-them
// Solution below inspired by ChatGPT, employed its help to solve my indexing problem. (ie mark 1 and unmark 1 refers to
// diff items)
/* Solution below inspired by ChatGPT, employed its help to solve the list not updating and showing the items with their
correct status icon, by creating a new task array of tasks instead of a string array.
 */
// Solution below inspired by https://stackoverflow.com/questions/10405789/regex-append-or-replace-only-the-first-
// letter-of-each-word
// Solution below inspired by https://www.programiz.com/java-programming/library/string/replacefirst
// Solution below adapted by ChatGPT, to solve the exception error when invoking last line of the loop.
// when there is no next line.
// Solution below inspired by https://stackoverflow.com/questions/32733084/pass-a-simple-enum-into-a-constructor-in-java
// Solution below inspired from ChatGPT, seeked clarification if the enums have to be passed into the child classes of
// parent class Task's constructor

/**
 * The main class for the Duke application.
 * Duke is a task management application that allows users to manage their tasks, including todos, deadlines, and events
 */
public class Duke {
    private static final String FILE_PATH = "./data/duke.txt";
    private static Ui ui;
    private static Storage storage;
    private static TaskList taskList;
    private static Parser parser;

    /**
     * Initializes a Duke instance by setting up the user interface, storage, task list,
     * and parser. It also attempts to load tasks from a file and displays a welcome message.
     */
    public Duke() {
        ArrayList<Task> tasks;
        ui = new Ui();
        storage = new Storage(FILE_PATH);
        taskList = new TaskList();
        parser = new Parser();

        try {
            tasks = storage.loadTasks(); // Load tasks from file
            taskList.setTasks(tasks);
            ui.displayWelcomeText();
        } catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }
        ui.displayWelcomeText();
    }

    String getResponse(String input) {
        try {
            Command command = parser.parseCommand(input);
            return command.execute(taskList, ui, storage);
        } catch (DukeException.ToDoException e) {
            return ui.printToDoException();
        } catch (DukeException.NoSuchItemException e) {
            return ui.printNoSuchElementException();
        } catch (DukeException.EventException e) {
            return ui.printEventException();
        } catch (DukeException.DeadlineException e) {
            return ui.printDeadlineException();
        } catch (DukeException.MarkException e) {
            return ui.printMarkException();
        } catch (DukeException.UnmarkException e) {
            return ui.printUnmarkException();
        } catch (DukeException.DeadlineFormatException e) {
            return ui.printDeadlineFormatException();
        } catch (DukeException.EventFormatException e) {
            return ui.printEventFormatException();
        } catch (DukeException.SearchException e) {
            return ui.printSearchException();
        } catch (DukeException.EmptyException e) {
            return ui.printEmptyCommandException();
        } catch (DukeException e) {
            throw new RuntimeException(e);
        }
    }
}
