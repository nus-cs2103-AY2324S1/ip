package crackerpackage;

import crackerpackage.tasks.*;
import org.junit.jupiter.api.Test;

import java.nio.file.*;

import static org.junit.jupiter.api.Assertions.*;

public class StorageTest {
    @Test
    public void testLoadNonExistentFile() {

            Storage s = new Storage("./data/badPath.txt");
            TodoList td = s.load();
            assertEquals(0, td.size());

    }
    @Test
    public void testStore(){
        Storage s = new Storage("./data/badPath.txt");
        TodoList td = s.load();
        try{
            td.store(new Todo("asdasd"));

        } catch (Exception e){
            System.out.println(e);
            fail();
        }
        assertEquals(1,1);



    }

    @Test
    public void testSaveFile(){
        Storage s = new Storage("./data/list.txt");
        TodoList td = s.load();
        try{
            td.store(new Todo("asdasd"));
            s.save(td);
        }  catch(Exception e){
            fail();
        }
        assertEquals(1,1);



    }

}
