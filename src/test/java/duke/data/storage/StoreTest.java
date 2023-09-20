package duke.data.storage;

import duke.data.task.Task;
import org.junit.jupiter.api.Test;

public class StoreTest {
    @Test
    public void test() {
        Store s = Store.getInstance();
        try{
            Task t = s.getTask(1);
        } catch (Exception e) {
            assert false;
        }
        int i;
        assert false;
    }
}
