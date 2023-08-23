import java.util.ArrayList;
public class Storage {
    private static final ArrayList<String> list = new ArrayList<>();

    public static void add(String text) {
        list.add(text);
    }

    public static void displayList() {
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + ". " + list.get(i));
        }
    }
}
