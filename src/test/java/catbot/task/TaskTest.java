package catbot.task;

import catbot.internal.NamedParameterMap;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {

    @Test
    public void DoneTest() {
        NamedParameterMap namedParameterMap = new NamedParameterMap();
        namedParameterMap.addNamedParameter("", "placeholder description");
        Optional<Task> optionalTask = Todo.createIfValidElse(
                namedParameterMap,
                (invalidParameterState, map) -> {
                    throw new AssertionError("Should not be invalid");
                });
        assert optionalTask.isPresent();
        Task task = optionalTask.get();
        assert !task.isDone();
        task.setDone();
        assert task.isDone();
        task.setDone();
        assert task.isDone();
        task.setUndone();
        assert !task.isDone();
    }
}
