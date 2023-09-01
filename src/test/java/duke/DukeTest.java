package duke;  //same package as the class being tested

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {
    DTFormat dtf = new DTFormat();
    Ui ui = new Ui(dtf);
    TaskList tl = new TaskList();
    Parser p = new Parser(dtf, ui, tl);

    @Test
    public void test1(){
//        Item a;
//        try{
//            a = new Item("help", true,
//                    new Deadline("/by 8/8/2020 1630",dtf.getFormatters()), "/by 8/8/2020 1630" );
//            assert(a.toStringWithDatetimeFormatter(dtf.getOutFormatter()).equals("[D][X]  help (by: 2020-08-08 16:30)"));
//        }catch(DukeException e){
//            System.out.println("Error occurred while converting item to string.");
//        }


    }

    @Test
    public void test2(){
        TaskList tl = new TaskList();
//        try{
//            Item a = new Item("help", true,
//                    new Deadline("/by 8/8/2020 1630",dtf.getFormatters()), "/by 8/8/2020 1630" );
//            tl.addItem(a);
//            tl.addItem(a);
//            tl.addItem(a);
//            assertEquals(tl.getSize(), 3);
//        }catch(DukeException e){
//            System.out.println("Error occurred while adding items to Tasklist.");
//        }


    }
}
