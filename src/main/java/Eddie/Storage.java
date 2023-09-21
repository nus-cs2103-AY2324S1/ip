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
                eddieTaskList.write("T , " + t.getStatus() + " , " + t.getName() + " ,  " + t.printTags() + " , \n");
            } else if (t.getType() == "D") {
                eddieTaskList.write("D , " + t.getStatus() + " , " + t.getName() + " , " + t.getDeadline() + " ,  " + t.printTags() + " , \n");
            } else if (t.getType() == "E") {
                eddieTaskList.write("E , " + t.getStatus() + " , " + t.getName() + " , " + t.getStartDate() + " ,  "
                        + t.getEndDate() + " , " + t.printTags() + " , \n");
            }
        }
        eddieTaskList.close();
    }

    /**
     * Read the file and extract its contents into the Tasklist.
     */
    public static void readFile () throws  FileNotFoundException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");
        File eddieTaskList = new File("EddieTaskList.txt");
        Scanner sc = new Scanner(eddieTaskList);
        while (sc.hasNextLine()) {
            String t = sc.nextLine();
            String[] task;
            task = t.split(" , ");

            switch (task[0]) {
                case "T":
                    Todo todo = new Todo(task[2]); //creates new task with name
                    if (task[1].equals("x")) {
                        todo.taskIsDone();
                    }

                    //if there are tags to be tagged
                    if (task.length == 4) {
                        String tagsToAdd = task[3];
                        String[] tags = tagsToAdd.split(" #");
                        for (int i = 1; i < tags.length; i++) {
                            todo.tag(tags[i]);
                        }
                    }

                    TaskList.add(todo);
                case "D":
                    Deadline deadline = new Deadline(task[2], LocalDate.parse(task[3], formatter));
//
                    if (task[1].equals("x")) {
                        deadline.taskIsDone();
                    }

                    if (task.length == 5) {
                        String tagsToAdd = task[4];
                        String[] tags = tagsToAdd.split(" #");
                        for (int i = 1; i < tags.length; i++) {
                            deadline.tag(tags[i]);
                        }
                    }
                    TaskList.add(deadline);
                case "E":
                    Event event = new Event(task[2], LocalDate.parse(task[3], formatter), LocalDate.parse(task[4], formatter));

                    if (task[1].equals("x")) {
                        event.taskIsDone();
                    }

                    if (task.length == 6) {
                        String tagsToAdd = task[5];
                        String[] tags = tagsToAdd.split(" #");
                        for (int i = 1; i < tags.length; i++) {
                            event.tag(tags[i]);
                        }
                    }

                    TaskList.add(event);
            }
        }
        sc.close();

    }

    public static void read() {
        try {
            readFile();
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
