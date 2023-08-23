import java.util.ArrayList;
public class AllyList {
    ArrayList<String> arr;
    public AllyList() {
        arr = new ArrayList<>(100);
    }

    public void addElements(String str) {
        arr.add(str);
    }

    public void printElements() {
        for (int i = 0; i < arr.size(); i++) {
            System.out.println((i+ 1) +". " + arr.get(i));
        }
    }

}
