package Duke;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class TaskListTest {

    @Test
    public void testChangeDateFormat(){
        assertEquals(null,new TaskList().changeDateFormat("2019",new Ui()));
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate date = LocalDate.parse("20190404",format);
        assertEquals(date,new TaskList().changeDateFormat("2019-04-04",new Ui()));
    }

}
