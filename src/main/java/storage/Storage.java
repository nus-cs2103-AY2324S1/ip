package storage;
import task.Deadline;
import task.Event;
import task.Todo;
import taskList.TaskList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * deals with loading tasks from the file and saving tasks in the file
 */
public class Storage {

    public Storage() {

    }

    public void load(TaskList list) {
        try {
            File theDir = new File("./src/main/data");
            if (!theDir.exists()){
                theDir.mkdirs();
            }
            File myObj = new File("./src/main/data/duke.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String type = data.substring(0,1);
                switch (type) {
                    case "T":
                        Todo.readData(list.getList(), data);
                        break;
                    case "D":
                        Deadline.readData(list.getList(), data);
                        break;
                    case "E":
                        Event.readData(list.getList(), data);
                        break;
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            File myObj = new File("./src/main/data/duke.txt");
        }
    }

}
