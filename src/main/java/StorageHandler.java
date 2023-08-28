import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class StorageHandler {
    private Task[] todoList;
    private int size;
    public StorageHandler() {
        todoList = SaveHandler.loadFrom();
        size = SaveHandler.size();
    }
    public void readArray() {
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
    public void addToArray(Task t) {
        todoList[size] = t;
        System.out.println("Added: \n   " + todoList[size].toString() + "\nPeko!");
        System.out.println("You have: " + (size+1) + " tasks now Peko");
        SaveHandler.saveTo();
    }
    public void setMarkArray(int i) {
        todoList[i-1].setMark();
        System.out.println("Marked as done peko!");
        System.out.println("    " + todoList[i-1]);
        SaveHandler.saveTo();
    }
    public void setUnmarkArray(int i) {
        todoList[i-1].setUnmark();
        System.out.println("You haven't done this yet peko?!");
        System.out.println("    " + todoList[i-1]);
        SaveHandler.saveTo();
    }
    public void setDelete(int i) {
        i--;
        while (i <= size) {
            todoList[i] = todoList[i+1];
            i++;
        }
        size--;
        SaveHandler.saveTo();
    }
    public void degen() throws FileNotFoundException {
        File text = new File("src/main/Copypasta.txt");
        Scanner sc = new Scanner(text);
        while (sc.hasNextLine()) {
            System.out.println(sc.nextLine());
        }
    }

}
