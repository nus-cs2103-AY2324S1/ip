package chad.chatengine;

import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import chad.io.IOHandler;
import chad.task.TaskList;

public class ChatEngineTest {
    private ChatEngine chatEngine;
    private IOHandler ioHandler;
    private TaskList taskList;

    @BeforeEach
    public void setUp() {
        ioHandler = mock(IOHandler.class);
        taskList = mock(TaskList.class);
        chatEngine = new ChatEngine(ioHandler, taskList, "some/file/path");
    }

    @Test
    public void handleEvent_validInput_addsEvent() throws ChadException {
        String[] parsedInput = new String[]{"EVENT", "Meeting /from 2023-12-01 14:00 /to 2023-12-01 16:00"};
        chatEngine.handleEvent(parsedInput);

        verify(taskList).addEvent("Meeting", LocalDateTime.of(2023, 12, 1, 14, 0), LocalDateTime.of(2023, 12, 1, 16, 0));
        verify(ioHandler).writeOutput("Added new Event: Meeting from 2023-12-01T14:00 to 2023-12-01T16:00");
    }

    @Test
    public void handleEvent_invalidDateFormat_throwsException() {
        String[] parsedInput = new String[]{"EVENT", "Meeting /from 2023-12-01 14:00 /to 2023-12-01"};
        Exception exception = assertThrows(ChadException.class, () -> chatEngine.handleEvent(parsedInput));

        assertEquals("Invalid date format. Please use yyyy-MM-dd HH:mm", exception.getMessage());
    }
}

