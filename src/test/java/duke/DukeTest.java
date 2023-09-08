package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {

    private DtFormat dtf = new DtFormat();
    private Ui ui = new Ui(dtf);
    private TaskList tl = new TaskList();
    private Parser p = new Parser(dtf, ui, tl);

    @Test
    public void test1() {
        Deadline a;
        try {
            a = new Deadline("help", true,
                    "/by 8/8/2020 1630", dtf.getFormatters());
            assert(a.getFormattedDatetime(dtf.getOutFormatter()).equals("[D][X]  help (by: 2020-08-08 16:30)"));
        } catch (DukeException e) {
            System.out.println("Error occurred while converting item to string.");
        }

    }

    @Test
    public void test2() {
        TaskList tl = new TaskList();
        Deadline a;
        try {
            a = new Deadline("help", true,
                    "/by 8/8/2020 1630", dtf.getFormatters());
            tl.addItem(a);
            tl.addItem(a);
            tl.addItem(a);
            assertEquals(tl.getSize(), 3);
        } catch (DukeException e) {
            System.out.println("Error occurred while adding items to Tasklist.");
        }


    }
}
