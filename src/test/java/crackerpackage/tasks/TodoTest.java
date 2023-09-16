package crackerpackage.tasks;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TodoTest {

    @Test
    public void testToString(){
        try{
            Todo s = new Todo("aaaaa");
            assertEquals("[T][ ] aaaaa",s.toString());
        } catch(Exception e){
            fail();
        }
    }
    @Test
    public void testMarking(){
        try{
            Todo s = new Todo("aaaaa");
            s.markDone();
            assertEquals("[T][X] aaaaa",s.toString());
        } catch(Exception e){
            fail();
        }
    }
}
