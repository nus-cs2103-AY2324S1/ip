import java.util.ArrayList;

public class DukeList {
    ArrayList<String> arr;

    public DukeList() {
        arr = new ArrayList<>(100);
    }

    public void addToList (String userInput) {
        arr.add(userInput);
        System.out.println("added: " + userInput);
    }

    public void printList() {
        int arrSize = arr.size();
        for (int i = 0; i < arrSize; i++) {
            int num = i + 1;
            System.out.println(num + ". " + arr.get(i));
        }
    }
}
