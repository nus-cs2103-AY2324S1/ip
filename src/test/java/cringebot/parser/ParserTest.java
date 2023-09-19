package cringebot.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import cringebot.datafile.Storage;
import cringebot.exceptions.CringeBotException;
import cringebot.mocks.MockStorage;
import cringebot.tasks.TaskList;

public class ParserTest {
    @Test
    public void parserFarewellTest1() {
        Storage storage = new MockStorage();
        TaskList tasks;
        try {
            tasks = new TaskList(storage.loadFromFile());
        } catch (CringeBotException e) {
            tasks = new TaskList();
        }
        assertEquals(
                "Bye. Hope to see you again soon!",
                Parser.parseCommands("bye", tasks, storage)
        );
    }

    @Test
    public void parserGreetingsTest1() {
        Storage storage = new MockStorage();
        TaskList tasks;
        try {
            tasks = new TaskList(storage.loadFromFile());
        } catch (CringeBotException e) {
            tasks = new TaskList();
        }
        assertEquals(
                "OOPS!!! I'm sorry, but I don't know what that means. :(( ",
                Parser.parseCommands("hello", tasks, storage)
        );
    }

    @Test
    public void parserTodoTest1() {
        Storage storage = new MockStorage();
        TaskList tasks;
        try {
            tasks = new TaskList(storage.loadFromFile());
        } catch (CringeBotException e) {
            tasks = new TaskList();
        }
        String expectedString = "Got it. I've added this task:\n"
                + "[T][ ] read book\n"
                + "Now you have 1 tasks in the list.";
        assertEquals(expectedString, Parser.parseCommands("todo read book", tasks, storage));
    }

    @Test
    public void parserEventTest1() {
        Storage storage = new MockStorage();
        TaskList tasks;
        try {
            tasks = new TaskList(storage.loadFromFile());
        } catch (CringeBotException e) {
            tasks = new TaskList();
        }
        String expectedString = "Got it. I've added this task:\n"
                + "[E][ ] project meeting (from: monday to: friday)\n"
                + "Now you have 1 tasks in the list.";
        assertEquals(expectedString, Parser.parseCommands(
                "event project meeting /from monday /to friday",
                tasks,
                storage
        ));
    }

    @Test
    public void parserDeadlineTest1() {
        Storage storage = new MockStorage();
        TaskList tasks;
        try {
            tasks = new TaskList(storage.loadFromFile());
        } catch (CringeBotException e) {
            tasks = new TaskList();
        }
        String expectedString = "Got it. I've added this task:\n"
                + "[D][ ] return book (by: Sep 18 2023)\n"
                + "Now you have 1 tasks in the list.";
        assertEquals(expectedString, Parser.parseCommands(
                "deadline return book /by 2023-09-18",
                tasks,
                storage)
        );
    }

    @Test
    public void parserRecurringDeadlineTest1() {
        Storage storage = new MockStorage();
        TaskList tasks;
        try {
            tasks = new TaskList(storage.loadFromFile());
        } catch (CringeBotException e) {
            tasks = new TaskList();
        }
        String expectedString = "Got it. I've added these task:\n"
                + "[D][ ] return book (by: Sep 18 2023)\n"
                + "[D][ ] return book (by: Sep 25 2023)\n"
                + "Now you have 2 tasks in the list.";
        assertEquals(expectedString, Parser.parseCommands(
                "deadline return book /by 2023-09-18 /recurring 2023-09-30",
                tasks,
                storage
        ));
    }

    @Test
    public void parserListTest1() {
        Storage storage = new MockStorage();
        TaskList tasks;
        try {
            tasks = new TaskList(storage.loadFromFile());
        } catch (CringeBotException e) {
            tasks = new TaskList();
        }
        String expectedString = "Here are the tasks in your list:\n"
                + "1.[T][ ] read book\n"
                + "2.[E][ ] project meeting (from: monday to: friday)\n"
                + "3.[D][ ] return book (by: Sep 18 2023)";
        Parser.parseCommands("todo read book", tasks, storage);
        Parser.parseCommands("event project meeting /from monday /to friday", tasks, storage);
        Parser.parseCommands("deadline return book /by 2023-09-18", tasks, storage);
        assertEquals(expectedString, Parser.parseCommands("list", tasks, storage));
    }

    @Test
    public void parserFindTest1() {
        Storage storage = new MockStorage();
        TaskList tasks;
        try {
            tasks = new TaskList(storage.loadFromFile());
        } catch (CringeBotException e) {
            tasks = new TaskList();
        }
        String expectedString = "Here are the matching tasks in your list:\n"
                + "1.[T][ ] read book";
        Parser.parseCommands("todo read book", tasks, storage);
        Parser.parseCommands("event project meeting /from monday /to friday", tasks, storage);
        Parser.parseCommands("deadline return book /by 2023-09-18", tasks, storage);
        assertEquals(expectedString, Parser.parseCommands("find read", tasks, storage));
    }

    @Test
    public void parserMarkUnmarkTest1() {
        Storage storage = new MockStorage();
        TaskList tasks;
        try {
            tasks = new TaskList(storage.loadFromFile());
            Parser.parseCommands("todo read book", tasks, storage);
            Parser.parseCommands("event project meeting /from monday /to friday", tasks, storage);
            Parser.parseCommands("deadline return book /by 2023-09-18", tasks, storage);
        } catch (CringeBotException e) {
            tasks = new TaskList();
        }
        String expectedString = "Nice! I've marked this task as done:\n"
                + "[T][X] read book";
        assertEquals(expectedString, Parser.parseCommands("mark 1", tasks, storage));
        expectedString = "OK, I've marked this task as not done yet:\n"
                + "[T][ ] read book";
        assertEquals(expectedString, Parser.parseCommands("unmark 1", tasks, storage));
    }

    @Test
    public void parserInvalidInput1() {
        Storage storage = new MockStorage();
        TaskList tasks;
        try {
            tasks = new TaskList(storage.loadFromFile());
            Parser.parseCommands("todo read book", tasks, storage);
            Parser.parseCommands("event project meeting /from monday /to friday", tasks, storage);
            Parser.parseCommands("deadline return book /by 2023-09-18", tasks, storage);
        } catch (CringeBotException e) {
            tasks = new TaskList();
        }
        String expectedString = "OOPS!!! I'm sorry, but I don't know what that means. :(( ";
        assertEquals(expectedString, Parser.parseCommands("invalid input", tasks, storage));
    }

    @Test
    public void parserRecurringEventTest() {
        Storage storage = new MockStorage();
        TaskList tasks;
        try {
            tasks = new TaskList(storage.loadFromFile());
            Parser.parseCommands("event project meeting /from monday /to friday", tasks, storage);
        } catch (CringeBotException e) {
            tasks = new TaskList();
        }
        String expectedString = "Oops! I can't do recurring events :((";
        assertEquals(expectedString, Parser.parseCommands(
                "event project meeting /from monday /to friday /recurring",
                tasks,
                storage
        ));
    }

    @Test
    public void parserRecurringTodoTest() {
        Storage storage = new MockStorage();
        TaskList tasks;
        try {
            tasks = new TaskList(storage.loadFromFile());
            Parser.parseCommands("todo read book", tasks, storage);
        } catch (CringeBotException e) {
            tasks = new TaskList();
        }
        String expectedString = "Oops! I can't do recurring todos :((";
        assertEquals(expectedString, Parser.parseCommands("todo read book /recurring", tasks, storage));
    }
}
