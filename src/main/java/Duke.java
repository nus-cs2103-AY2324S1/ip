import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

// Solution below inspired by https://stackoverflow.com/questions/47150081/while-loop-for-multiple-inputs
// Solution below inspired by ChatGPT, to solve the issue in the else block of incrementing the num_items counter to add
// the new item subsequently.
/* Solution below inspired by ChatGPT, to solve the issue for the command list, to show the separators only at the start
and end, by moving
the statement outside the for loop
 */
// Solution below inspired by ChatGPT, regarding the method .contains("mark"), as it might be a bug if the input contains mark,
// without a corresponding integer.
// Solution below inspired by https://stackoverflow.com/questions/12973871/read-multiple-user-input-words-and-split-them
// Solution below inspired by ChatGPT, employed its help to solve my indexing problem. (ie mark 1 and unmark 1 refers to
// diff items)
/* Solution below inspired by ChatGPT, employed its help to solve the list not updating and showing the items with their
correct status icon, by creating a new task array of tasks instead of a string array.
 */
// Solution below inspired by https://stackoverflow.com/questions/10405789/regex-append-or-replace-only-the-first-letter-of-each-word
// Solution below inspired by https://www.programiz.com/java-programming/library/string/replacefirst
// Solution below adapted by ChatGPT, to solve the exception error when invoking last line of the loop. when there is no next line.
// Solution below inspired by https://stackoverflow.com/questions/32733084/pass-a-simple-enum-into-a-constructor-in-java
// Solution below inspired from ChatGPT, seeked clarification if the enums have to be passed into the child classes of parent class Task's constructor

public class Duke {
    private static String filePath = "./data/duke.txt";

    public static void main(String[] args) throws DukeException.NoSuchItemException, DukeException.ToDoException, IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        Ui ui = new Ui();
        Storage storage = new Storage(filePath);
        TaskList taskList = new TaskList();
        Parser parser = new Parser();

        try {
            tasks = storage.loadTasks(); // Load tasks from file
            taskList.setTasks(tasks);
        } catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }

        ui.displayWelcomeText();

        Scanner sc = new Scanner(System.in);
        // int num_items = tasks.size();

        String user_text = sc.nextLine();

        while (!user_text.isEmpty()) {
            try {
                Command command = parser.parseCommand(user_text);
                command.execute(taskList, ui, storage);
            } catch (DukeException.ToDoException e) {
                ui.printToDoException();
            } catch (DukeException.NoSuchItemException e) {
                ui.printNoSuchElementException();
            } catch (DukeException.EventException e) {
                ui.printEventException();
            } catch (DukeException.DeadlineException e) {
                ui.printDeadlineException();
            } catch (DukeException.MarkException e) {
                ui.printMarkException();
            } catch (DukeException.UnmarkException e) {
                ui.printUnmarkException();
            } catch (DukeException.DeadlineFormatException e) {
                ui.printDeadlineFormatException();
            } catch (DukeException.EventFormatException e) {
                ui.printEventFormatException();
            } catch (DukeException e) {
                throw new RuntimeException(e);
            }
            user_text = sc.hasNextLine() ? sc.nextLine() : "";
        }
        sc.close();
    }
}