package message;

import javax.swing.*;
import java.util.ArrayList;

public class TodoListMessage extends Message {
    private final String[] list;
    public TodoListMessage(ArrayList<String> list) {
        this.list = formatList(list);
    }
    private String[] formatList(ArrayList<String> list) {
        String[] ls = new String[list.size() + 1];
        int index = 1;
        for (String item: list) {
            ls[index - 1] = String.format("%d. %s", index, item);
            index++;
        }
        ls[index - 1] = horizontalLine;
        return ls;
    }
    @Override
    public void send() {
        System.out.println(createMessage(list));
    }
}
