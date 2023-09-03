package Task;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.format.DateTimeFormatter;

public class DeadlineTest {

    @Test
    public void storeFormatTest() {
        assertEquals("D | 0 | homework | tomorrow", new Deadline("homework",
                "tomorrow").storeFormat());
        assertEquals("D | 0 | clean room | tonight 8pm", new Deadline("clean room",
                "tonight 8pm").storeFormat());
        assertEquals("D | 0 | watch movie | 16 Aug 1977 2:00AM", new Deadline("watch movie",
                LocalDateTime.parse("16-08-1977 0200",
                        DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"))).storeFormat());
    }


}
