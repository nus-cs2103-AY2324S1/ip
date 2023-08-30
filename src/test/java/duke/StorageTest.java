package duke; 

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

public class StorageTest {
    @Test
    public void test(){
        Storage s = new Storage("./data/testList.txt");
        ArrayList<Item> res = s.loadList();
        System.out.println(res.toString());
        assertEquals("[[E][X] seminar (from: Aug 23 2023 1600hrs to: Aug 23 2023 1700hrs), "
                        + "[E][ ] concert (from: Dec 20 2023 1800hrs to: Dec 21 2023 0100hrs), "
                        + "[T][ ] cs2103t homework, [D][ ] duke (by: Aug 30 2023 1500hrs)]", res.toString());
    }
}