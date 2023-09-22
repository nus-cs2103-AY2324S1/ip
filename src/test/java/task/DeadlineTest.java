package task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class DeadlineTest {

    @Test
    public void testYYYYMMDD(){
        assertEquals("[D][ ] Read book (by: 10 Oct 2023 00:00)", new Deadline("Read book", "2023/10/10").toString());
    }

    @Test
    public void testDDMMYYYY(){
        assertEquals("[D][ ] Read book (by: 10 Oct 2023 00:00)", new Deadline("Read book", "10/10/2023").toString());
    }

    @Test
    public void testDDMMMYYYY(){
        assertEquals("[D][ ] Read book (by: 10 Oct 2023 00:00)", new Deadline("Read book", "10-Oct-2023").toString());
    }

    @Test
    public void testYYYYMMMDD(){
        assertEquals("[D][ ] Read book (by: 10 Oct 2023 00:00)", new Deadline("Read book", "2023/Oct/10").toString());
    }

    @Test
    public void testOverdue(){
        assertEquals(true, new Deadline("Read book", "2020/Oct/10").isOverdue());
    }

    @Test
    public void testNotOverdue(){
        assertEquals(false, new Deadline("Read book", "2024/Oct/10").isOverdue());
    }

    @Test
    public void testDueBy(){
        assertEquals(true, new Deadline("Read book", "2024/Oct/10").isDueBy(Deadline.convertDate("2024/Oct/11")));
    }
}
