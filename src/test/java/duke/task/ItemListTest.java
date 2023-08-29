package duke.task;


import duke.Duke;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;



import static org.junit.jupiter.api.Assertions.assertEquals;

public class ItemListTest {

    private ItemList items;

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setup() {
        this.items = new ItemList(new File(Duke.LISTPATH));
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void showItemsTest(){
        items.addEvent("testevent", "today", "tomorrow");
        items.addTodo("testtodo");
        items.addDeadline("testDeadline", "soon");
        outputStreamCaptor.reset();
        this.items.showitems();

        String expected = "____________________________________________________________\n" +
                "Here are the tasks in your list:\n" +
                "1. [E][ ] testevent(from: today to: tomorrow)\n" +
                "2. [T][ ] testtodo\n" +
                "3. [D][ ] testDeadline(by: soon)\n" +
                "____________________________________________________________";

        String actualOutput = outputStreamCaptor.toString().trim();
        assertEquals(expected , actualOutput);

    }
}
