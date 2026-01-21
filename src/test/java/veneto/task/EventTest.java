package veneto.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EventTest {

    @Test
    void toString_toDoWithDescriptionOnly_unmarkedToDo() {
        assertEquals(
                "[E][ ] a (from: Sep 10 2019 to: Sep 11 2019)",
                new Event("a", "2019-09-10", "2019-09-11").toString()
        );
    }

    @Test
    void toString_toDoWithDescriptionAndStatus_markedToDo() {
        assertEquals(
                "[E][V] a (from: Sep 10 2019 to: Sep 11 2019)",
                new Event("a", 1, "2019-09-10", "2019-09-11").toString()
        );
    }

    @Test
    void saveToString_toDoWithDescriptionOnly_unmarkedToDo() {
        assertEquals(
                "event,a,0,2019-09-10,2019-09-11",
                new Event("a", "2019-09-10", "2019-09-11").saveToString()
        );
    }

    @Test
    void saveToString_toDoWithDescriptionAndStatus_markedToDo() {
        assertEquals(
                "event,a,1,2019-09-10,2019-09-11",
                new Event("a", 1, "2019-09-10", "2019-09-11").saveToString()
        );
    }
}