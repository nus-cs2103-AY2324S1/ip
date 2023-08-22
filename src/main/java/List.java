import java.util.ArrayList;

public class List {
    private ArrayList<String> taskList = new ArrayList<String>();

    public void add(String string) {
        taskList.add(string);
        System.out.println("\tadded: " + string);
    }

    public void list() {
        String returnString = "";
        for (int i = 0; i < taskList.size(); i++) {
            returnString += i+1 + ". " + taskList.get(i) + "\n";
        }
        System.out.println(returnString);
    }
}
