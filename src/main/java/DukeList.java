import java.util.ArrayList;

public class DukeList {
    private ArrayList<String> dukeList;

    public DukeList() {
        dukeList = new ArrayList<>(100);
    }

    public void addToList(String input) {
        dukeList.add(input);
        System.out.println("added: " + input);
    }

    public void displayList() {
        int len = dukeList.size();
        for (int i = 0; i < len; i++) {
            int num = i + 1;
            System.out.println(num + ". " + dukeList.get(i));
        }
    }
}
