import org.junit.jupiter.api.Test;
import types.Task;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestUtils {

    /**
     * Tests if Utils::getDateList is able to return an empty list when the current list is empty.
     */
    @Test
    public void testEmptyList(){
        ArrayList<Task> list = Utils
                .getDateList(LocalDate.parse("2023-08-20"), new ArrayList<>());

        assertEquals(list.size(), 0);

    }
}
