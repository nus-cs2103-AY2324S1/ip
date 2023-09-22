package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DukeTest {

    private DtFormat dtf = new DtFormat();
    private Ui ui = new Ui(dtf);
    private TaskList tl = new TaskList();


    @Test
    public void testFormatDeadline() {
        try {
            Deadline deadline = new Deadline("help", true,
                    "/by 8/8/2020 1630");
            String formattedDeadline = deadline.getFormattedDatetime(dtf.getOutFormatter());
            assert(formattedDeadline.equals("[D][X]  help (by: 2020-08-08 16:30)"));
        } catch (DukeException e) {
            System.out.println("Error occurred while converting item to string.");
        }

    }
    @Test
    public void testTaskListSize() {
        TaskList tl = new TaskList();
        try {
            Deadline deadline = new Deadline("help", true,
                    "/by 8/8/2020 1630");
            tl.addItem(deadline);
            tl.addItem(deadline);
            tl.addItem(deadline);
            int size = tl.getSize();
            assertEquals(size, 3);
        } catch (DukeException e) {
            System.out.println("Error occurred while adding items to Tasklist.");
        }
    }

    @Test
    public void testHandleMarkCommand() {

        try {
            TaskList tl = new TaskList();
            CommandHandler ch = new CommandHandler(tl, ui, dtf);
            String userInput = "event build sandcastle /from 18/8/2020 1700 /to 18/8/2020 1800";
            String[] splitStr = userInput.split("\\s+");
            ch.handleEventCommand(splitStr);
            String userInput2 = "mark 1";
            String[] splitStr2 = userInput2.split("\\s+");
            ch.handleMarkCommand(splitStr2);
            boolean isMarked = tl.getItem(0).getIsCompleted();
            assertEquals(isMarked, true);
        } catch (DukeException e) {
            System.out.println("Error occurred while adding items to Tasklist.");
        }
    }

    @Test
    public void testHandleUnmarkCommand() {

        try {
            TaskList tl = new TaskList();
            CommandHandler ch = new CommandHandler(tl, ui, dtf);
            String userInput = "event build sandcastle /from 18/8/2020 1700 /to 18/8/2020 1800";
            String[] splitStr = userInput.split("\\s+");
            ch.handleEventCommand(splitStr);
            String userInput2 = "mark 1";
            String[] splitStr2 = userInput2.split("\\s+");
            ch.handleMarkCommand(splitStr2);
            String userInput3 = "unmark 1";
            String[] splitStr3 = userInput3.split("\\s+");
            ch.handleUnmarkCommand(splitStr3);
            boolean isMarked = tl.getItem(0).getIsCompleted();
            assertEquals(isMarked, false);
        } catch (DukeException e) {
            System.out.println("Error occurred while adding items to Tasklist.");
        }
    }
}
