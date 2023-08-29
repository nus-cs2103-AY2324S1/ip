package duke.ui;

import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;

public class UiTest {

    @Test
    public void stringifyList_list_enumeratedList() {
        assertEquals(Ui.stringifyList(List.of("first", "second", "third")), "1. first\n2. second\n3. third");
    }

}
