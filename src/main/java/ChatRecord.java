import java.util.ArrayList;

public class ChatRecord {
    private ArrayList<Task> chatRecords;
    private int counter;
    public ChatRecord() {
         chatRecords = new ArrayList<>();
         counter = 0;
    }

    public void addMessage(String message) {
        chatRecords.add(new Task(message));
        counter++;
    }

    public String listMessage() {
        StringBuilder ret = new StringBuilder();
        for (int i = 0; i < counter; i++) {
            ret.append(String.format("\t\t%d. %s\n", i + 1, chatRecords.get(i).toString()));
        }
        return ret.toString();
    }

    public void setMark(int n) {
        chatRecords.get(n - 1).mark();
    }

    public void setUnmark(int n) {
        chatRecords.get(n - 1).unmark();
    }

    public String getTask(int n) {
        return chatRecords.get(n - 1).toString();
    }
}
