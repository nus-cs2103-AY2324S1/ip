package duke.parser;
import org.junit.jupiter.api.Test;

public class ParserTest {
    @Test
    public void test1() {
        Parser.parse("");
    }

    @Test
    public void test2() {
        Parser.parse("todo");
    }

    @Test
    public void test3() {
        Parser.parse("deadline read book /by 1000-12-31");
    }

    @Test
    public void test4() {
        Parser.parse("blah");
    }

    @Test
    public void test5() {
        Parser.parse("unmark 999");
    }

    @Test
    public void test6() {
        Parser.parse("delete ");
    }

    @Test
    public void test7() {
        Parser.parse("event /from 2220-10-23");
    }

    @Test
    public void test8() {
        Parser.parse("mark 1");
    }

    @Test
    public void test9() {
        Parser.parse("mark 1");
    }

    @Test
    public void test10() {
        Parser.parse("remark 1");
    }

    @Test
    public void test11() {
        Parser.parse("remark 1");
    }

    @Test
    public void test12() {
        Parser.parse("list");
    }

    @Test
    public void test13() {
        Parser.parse("delete 1");
    }

    @Test
    public void test14() {
        Parser.parse("list");
    }

    @Test
    public void test15() {
        Parser.parse("bye");
    }
}
