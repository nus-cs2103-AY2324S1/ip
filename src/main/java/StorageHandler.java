import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class StorageHandler {
    private static Task[] todoList;
    public StorageHandler() {
        todoList = new Task[100];
        todoList = new SaveHandler().loadFrom();
    }
    public void load() {

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
        System.out.println(lineBreak);
    }
    public static void addToArray(Task t) {
        todoList[pos] = t;
        todoList[pos].reply(pos);
        pos++;
        System.out.println(lineBreak);
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
        while (i <= pos) {
            todoList[i] = todoList[i+1];
            i++;
        }
        pos--;
        saveHandler.saveTo();
    }
    public static void degen() throws FileNotFoundException {
        File text = new File("src/main/Copypasta.txt");
        Scanner sc = new Scanner(text);
        while (sc.hasNextLine()) {
            System.out.println(sc.nextLine());
        }
        System.out.println(lineBreak);

    }

}
