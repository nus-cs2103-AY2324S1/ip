package duke.storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;
import duke.taskList.TaskList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {

    /**
     * The constructor of the Storage class.
     */
    public Storage() {

    }

    /**
     * Load the data from duke.txt into the TaskList inside of the program.
     * @param list the TaskList that the data is loaded to from duke.txt.
     */
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
