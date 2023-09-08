package peko;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class StorageHandler {
    private static Task[] todoList;
    private static int size;
    public StorageHandler() {
        todoList = SaveHandler.loadFrom();
        size = SaveHandler.size();
    }
    public static String readArray() {
        int i = 0;
        String out = "--------------LIST-PEKO------------------\n";
        while (todoList[i] != null) {
            out += (i+1 + ". " + todoList[i] + "\n");
            i++;
        }
        if (i == 0) {
            out = ("You are FREE PEKO!!!!!\n");
        }
        return out;
    }
    public static String addToArray(Task t) {
        todoList[size] = t;
        String out = "Added: \n   " + todoList[size].toString() + "\npeko!";
        out += "You have: " + (size+1) + " tasks now peko";
        System.out.println(out);
        size++;
        SaveHandler.saveTo();
        return out;
    }
    public static String setMarkArray(int i) {
        String out;
        try {
            todoList[i-1].setMark();
            out = "Marked as done peko!\n";
            out += ("    " + todoList[i-1] + "\n");
            SaveHandler.saveTo();

        } catch (NullPointerException e) {
            out = ("You don't have so many Tasks Peko!\n");
        }
        return out;
    }
    public static void setUnmarkArray(int i) {
        try {
            todoList[i-1].setUnmark();
            System.out.println("You haven't done this yet peko?!");
            System.out.println("    " + todoList[i-1]);
            SaveHandler.saveTo();
        } catch (NullPointerException e) {
            System.out.println("You don't have so many Tasks Peko!");
        }

    }

    public static Task[] search(String searchQuery) {
        Task[] temp = todoList;
        Task[] tempOutput = new Task[100];
        int pos = 0;
        for (Task t : temp) {
            if (t == null) {
                System.out.println("break");
                break;
            }
            if (t.hasString(searchQuery)) {
                System.out.println(t);
                tempOutput[pos] = t;
                pos++;
            }
        }
        return tempOutput;
    }

    public static void setDelete(int i) {
        i--;
        while (i <= size) {
            todoList[i] = todoList[i+1];
            i++;
        }
        size--;
        SaveHandler.saveTo();
    }
    public static void degen() throws FileNotFoundException {
        File text = new File("src/main/Copypasta.txt");
        Scanner sc = new Scanner(text);
        while (sc.hasNextLine()) {
            System.out.println(sc.nextLine());
        }
    }

}
