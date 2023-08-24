import TaskList.TaskList;
import java.util.Scanner;
import Exception.*;
import Parser.*;

public class Kevin {
    public static void main(String[] args) {
        Parser parser = new Parser(System.in);
        parser.hello();

        TaskList taskList = parser.getTaskList();

        parser.bye();
    }
}



