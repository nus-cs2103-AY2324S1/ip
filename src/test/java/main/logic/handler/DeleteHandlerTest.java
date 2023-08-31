package main.logic.handler;

import exceptions.syntax.ArgFormatException;
import main.KniazSession;
import org.junit.jupiter.api.Test;
import storage.TaskList;
import task.Task;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class DeleteHandlerTest {

    @Test
    public void deleteHandler_badUnnamedArg_exceptionThrown() {
        DeleteHandler handler = new DeleteHandler();

        List<List<String>> badUnnamedList = List.of(
                List.of("o"),
                List.of("this is not a number"),
                List.of("1.1"),
                List.of("\n"),
                List.of("0.2"));
        Map<String,String> emptyNamedArgs = new HashMap<>();
        for (int i = 0; i < badUnnamedList.size(); i++) {
            String currArg = badUnnamedList.get(i).get(0);
            try {
                handler.handle(null, badUnnamedList.get(i),emptyNamedArgs);
                fail();
            } catch (ArgFormatException e){
                assertEquals(String.format("I could not interpret %s as an integer, what is this?",currArg),
                        e.getUserMessage());
            }
        }
    }


    @Test
    public void deleteHandler_goodArgs_goodDelete(){

        DeleteHandler handler = new DeleteHandler();

        List<List<String>> goodUnnamedList = List.of(
                List.of("1"),
                List.of("2"),
                List.of("3"),
                List.of("2"),
                List.of("1")
        ); //should be empty list after all deletion
        List<Integer> valueList = List.of(
                0,
                1,
                2,
                1,
                0
        ); // internals uses 0-based, interface via string should be 1-based
        class DummyTask extends Task{

            public final int identifier;
            protected DummyTask(int identifier) {
                super(null,false);
                this.identifier = identifier;
            }

            @Override
            public boolean equals(Object obj) {
                if (obj instanceof DummyTask) {
                    return this.identifier == ((DummyTask) obj).identifier;
                } else {
                    return false;
                }
            }
        }
        TaskList startList = new TaskList();
        for (int i = 0; i < goodUnnamedList.size(); i++) {
            startList.add(new DummyTask(i));
        }

        TaskList expectedList = (TaskList) startList.clone();
        // clone obviously returns a tasklist so this is fine

        KniazSession dummySession = new KniazSession() {
            @Override
            public TaskList getTaskList() {
                return startList;
            }
        };


        assertEquals(dummySession.getTaskList() ,expectedList); // must match expected result

        for (int i = 0; i < goodUnnamedList.size(); i++) {
            TaskList toTest = dummySession.getTaskList();
            handler.handle(dummySession, goodUnnamedList.get(i), null);
            expectedList.remove(valueList.get(i));

            assertEquals(toTest, dummySession.getTaskList());

        }
    }
}