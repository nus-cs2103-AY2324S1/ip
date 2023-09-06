package duke.ui;

import java.util.ArrayList;

import duke.task.Task;

public class UI {
    public static final String LINEBREAK = "____________________________________________________________";
    public static String printMessage(String... msgs) {
        System.out.println(LINEBREAK);
        String toprint = "";
        for (String msg : msgs) {
            System.out.println(msg);
            toprint += msg + "\n";
        }
        System.out.println(LINEBREAK);
        return toprint;
    }

    public static String printFileError() {
        UI.printMessage("Something is wrong with the list file");
        return "Something is wrong with the list file";
    }

    public static String noSuchTaskError() {
        return UI.printMessage("No Such Task");
    }

    public static String printList(ArrayList<Task> items) {
        System.out.println(LINEBREAK);
        System.out.println("Here are the tasks in your list:");
        String response = "" ;
        for (int i = 0; i < items.size(); i++) {
            String index = String.valueOf(i + 1);
            String newprint = index + ". " + items.get(i).showTaskinList();
            System.out.println(newprint);
            response += newprint + "\n";
        }
        System.out.println(LINEBREAK);
        return response;
    }

    public static String printFound(ArrayList<Task> items) {
        System.out.println(LINEBREAK);
        System.out.println("Here are the matching tasks in your list:");
        String toprint = "";
        for (int i = 0; i < items.size(); i++) {
            String index = String.valueOf(i + 1);
            String newtoprint = index + ". " + items.get(i).showTaskinList();
            System.out.println(newtoprint);
            toprint += newtoprint + "\n";
        }
        System.out.println(LINEBREAK);
        return toprint;
    }


}
