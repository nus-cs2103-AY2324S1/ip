package main.logic.handler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import exceptions.syntax.MissingNamedArgsException;
import main.KniazSession;
import storage.TaskList;
import task.Deadline;



class DeadlineHandlerTest {

    @Test
    void deadlineHandler_goodArgs_deadLine() {
        TaskList testList = new TaskList();
        KniazSession dummySession = new KniazSession() {
            @Override
            public TaskList getTaskList() {
                return testList;
            }
        };


        String out = new DeadlineHandler().handle(dummySession,
                List.of("test"),
                Map.of("by", "time"));

        assertEquals(out, new Deadline("test", "time").toPrintString());



    }

    void deadlineHandler_noNamedArgs_throwException() {
        TaskList testList = new TaskList();
        KniazSession dummySession = new KniazSession() {
            @Override
            public TaskList getTaskList() {
                return testList;
            }
        };


        try {
            String out = new DeadlineHandler().handle(dummySession,
                    List.of("test"),
                    Map.of());
            fail();
        } catch (MissingNamedArgsException e) {
            assertEquals(e.getUserMessage(), new MissingNamedArgsException(List.of("by"),
                    List.of(""),
                    null).getUserMessage());
        }





    }
}
