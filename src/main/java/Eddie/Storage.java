package Eddie;

import Eddie.Tasks.Deadline;
import Eddie.Tasks.Event;
import Eddie.Tasks.Task;
import Eddie.Tasks.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * Class to handle storing and loading of past data saved on the hard disk.
 */
public class Storage {
    /**
     * Writes to the txt file for saving data.
     * @throws IOException Thrown if input is not correct.
     */
    private static void writeToFile () throws IOException {
        FileWriter eddieTaskList = new FileWriter("EddieTaskList.txt");
        for (int i = 0; i < TaskList.size(); i++) {
            Task t = TaskList.get(i);

            if (t.getType() == "T") {
                eddieTaskList.write("T , " + t.getStatus() + " , " + t.getName() + " , \n");
            } else if (t.getType() == "D") {
                eddieTaskList.write("D , " + t.getStatus() + " , " + t.getName() + " , " + t.getDeadline() + " , \n");
            } else if (t.getType() == "E") {
                eddieTaskList.write("E , " + t.getStatus() + " , " + t.getName() + " , " + t.getStartDate() + " , "
                        + t.getEndDate() + " , \n");
            }
        }
        eddieTaskList.close();
    }

    /**
     * Read the file and extract its contents into the Tasklist.
     */
    public static void readFile() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");
        try {
            File eddieTaskList = new File("EddieTaskList.txt");
            Scanner sc = new Scanner(eddieTaskList);
            while (sc.hasNextLine()) {
                String t = sc.nextLine();
                String[] task = new String[5];
                task = t.split(" , ");

                if(task[0].equals("T")) {
                    //System.out.println(task[2]);
                    Todo todo = new Todo(task[2]);
                    if (task[1].equals("x")) {
                        todo.taskIsDone();
                    }

                    TaskList.add(todo);
                } if (task[0].equals("D")) {
                    Deadline deadline = new Deadline(task[2], LocalDate.parse(task[3], formatter));
//                    System.out.println(task[1]);
                    if (task[1].equals("x")){
                        deadline.taskIsDone();
                    }
                    TaskList.add(deadline);
                } if (task[0].equals("E")) {
                    Event event = new Event(task[2], LocalDate.parse(task[3], formatter), LocalDate.parse(task[4], formatter));

                    if (task[1].equals("x")) {
                        event.taskIsDone();
                    }

                    TaskList.add(event);
                }
            }

            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    /**
     * Public method used to write to hard disk.
     */
    public static void write() {
        try {
            writeToFile();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
