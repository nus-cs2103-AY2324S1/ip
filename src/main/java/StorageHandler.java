import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class StorageHandler {
    private static Task[] todoList;
    private static int size;
    private static SaveHandler saveHandler;
    public StorageHandler() {
        saveHandler = new SaveHandler();
        todoList = saveHandler.loadFrom();
        size = saveHandler.size();
    }
    public static void readArray() {
        int i = 0;
        System.out.println("--------------LIST-PEKO------------------");
        while (todoList[i] != null) {
            System.out.println(i+1 + ". " + todoList[i]);
            i++;
        }
        if (i == 0) {
            System.out.println("You are FREE PEKO!!!!!");
        }
    }
    public static void addToArray(Task t) {
        todoList[size] = t;
        todoList[size].reply(size);
        size++;
        saveHandler.saveTo();
    }
    public static void setMarkArray(int i) {
        todoList[i-1].setMark();
        System.out.println("Marked as done peko!");
        System.out.println("    " + todoList[i-1]);
        saveHandler.saveTo();
    }
    public static void setUnmarkArray(int i) {
        todoList[i-1].setUnmark();
        System.out.println("You haven't done this yet peko?!");
        System.out.println("    " + todoList[i-1]);
        saveHandler.saveTo();
    }
    public static void setDelete(int i) {
        i--;
        while (i <= size) {
            todoList[i] = todoList[i+1];
            i++;
        }
        size--;
        saveHandler.saveTo();
    }
    public static void degen() throws FileNotFoundException {
        File text = new File("src/main/Copypasta.txt");
        Scanner sc = new Scanner(text);
        while (sc.hasNextLine()) {
            System.out.println(sc.nextLine());
        }
    }

}
