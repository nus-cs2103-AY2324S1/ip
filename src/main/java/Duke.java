
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;




public class Duke {
    static ArrayList<Task> tasks = new ArrayList<>();

    public static void exit() {
        Ui.exit();
        System.exit(0);
    }



    public static void add(Task t){
        String taskName = t.getName();
        TaskList.add(t);
        Ui.addTask(t.toString(), TaskList.size());

        Storage.write();


    }

    public static void clear() {
        TaskList.clear();
        Ui.clear();

        Storage.write();
    }



    public static void list() {
        int listSize = TaskList.size();
        for (int i = 0; i < listSize ; i++) {
            int num = i + 1;
            Task taskToList = TaskList.get(i);
            Ui.listTask(num, taskToList.toString());
        }
    }

    public static void delete(int num) {
        Task t = TaskList.get(num - 1);
        TaskList.delete(num - 1);

        Ui.removeTask(t.toString(), TaskList.size());
        Storage.write();

    }


    public static void main(String[] args) {

        TaskList.clear();
        Storage.readFile();

        Ui.welcome();
        Parser.parse();
    }
}
