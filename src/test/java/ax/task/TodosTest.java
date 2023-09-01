package ax.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TodosTest {

    @Test
    void testToString() {
        Todos temp = new Todos("hi");
        assertEquals(temp.toString(), "[T] [ ] hi");
    }

    @Test
    void testSetDone() {
        Todos temp = new Todos("hi");
        temp.setDone(true);
        assertEquals(temp.toString(), "[T] [✅] hi");
        temp.setDone(false);
        assertEquals(temp.toString(), "[T] [ ] hi");
    }
}