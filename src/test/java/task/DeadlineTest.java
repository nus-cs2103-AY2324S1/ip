package task;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.format.DateTimeFormatter;

public class DeadlineTest {

    @Test
    public void outputStoreFormat() {
        assertEquals("D | 0 | homework | tomorrow", new Deadline("homework",
                "tomorrow").outputStoreFormat());
        assertEquals("D | 0 | clean room | tonight 8pm", new Deadline("clean room",
                "tonight 8pm").outputStoreFormat());
        assertEquals("D | 0 | watch movie | 16 Aug 1977 2:00AM", new Deadline("watch movie",
                LocalDateTime.parse("16-08-1977 0200",
                        DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"))).outputStoreFormat());
    }


}
