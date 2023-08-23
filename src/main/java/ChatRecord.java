import java.util.ArrayList;

public class ChatRecord {
    private ArrayList<String> chatRecords;
    private int counter;
    public ChatRecord() {
         chatRecords = new ArrayList<>();
         counter = 0;
    }

    public void AddMessage(String message) {
        chatRecords.add(message);
        counter++;
    }

    public String ListMessage() {
        StringBuilder ret = new StringBuilder();
        for (int i = 0; i < counter; i++) {
            ret.append(String.format("\t\t%d. %s\n", i, chatRecords.get(i)));
        }
        return ret.toString();
    }
}
