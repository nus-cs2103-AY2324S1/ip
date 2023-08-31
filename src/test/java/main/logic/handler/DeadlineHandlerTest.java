package main.logic.handler;

import exceptions.syntax.MissingNamedArgsException;
import main.KniazSession;
import org.junit.jupiter.api.Test;
import storage.TaskList;
import task.Deadline;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
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
                Map.of("by","time"));

        assertEquals(out, new Deadline("test","time").toPrintString());



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
            assertEquals(e.getUserMessage(), new MissingNamedArgsException(List.of("by")
                    ,List.of(""),
                    null).getUserMessage());
        }





    }
}