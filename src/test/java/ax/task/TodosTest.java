package ax.task;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

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
        assertEquals(temp.toString(), "[T] [M] hi");
        temp.setDone(false);
        assertEquals(temp.toString(), "[T] [ ] hi");
    }
}
