package duke.tasklist;
import duke.exception.DukeException;


import java.time.LocalDate;
import org.junit.jupiter.api.Test;

public class TaskListTest {

    private TaskList lst = new TaskList();
    @Test
    public void test1() throws DukeException {
        lst.delete(1);
    }

    @Test
    public void test2() {
        lst.addTask("read book");
    }

    @Test
    public void test3() {
        lst.addTask("return book", LocalDate.parse("1202-10-12"));
    }

    @Test
    public void test4() throws DukeException {
        lst.mark(1);
    }

    @Test
    public void test5() throws DukeException {
        lst.mark(1);
    }

    @Test
    public void test6() throws DukeException {
        lst.unmark(1);
    }

    @Test
    public void test7() throws DukeException {
        lst.mark(1);
    }

    @Test
    public void test8() {
        lst = new TaskList(new TaskList());
    }

    @Test
    public void test9() {
        lst = new TaskList();
    }

    @Test
    public void test10() throws DukeException {
        lst.mark(1);
    }
}
