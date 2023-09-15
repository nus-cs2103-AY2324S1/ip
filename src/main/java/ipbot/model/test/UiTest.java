package ipbot.model.test;

import ipbot.model.TaskStub;
import ipbot.util.Ui;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UiTest {

    @Test
    public void testTaskListFormatString() {
        Assertions.assertEquals(
                "1. <Some Task String>",
                Ui.taskListFormatString(new TaskStub("asdf"), 1)
        );
        Assertions.assertEquals(
                "2. <Some Task String>",
                Ui.taskListFormatString(new TaskStub("asdf"), 2)
        );
        Assertions.assertEquals(
                "3. <Some Task String>",
                Ui.taskListFormatString(new TaskStub("asdf"), 3)
        );
    }

    @Test
    public void testPrintAddedItem() {
        Assertions.assertEquals(
                "Added taskType item: <Some Task String>",
                Ui.addedItemString(new TaskStub("asdf"), "taskType"));
    }
}
