import message.AddedMessage;
import message.TodoListMessage;

import java.util.ArrayList;

public class TodoList {
    private final ArrayList<String> list;
    public TodoList() {
        list = new ArrayList<String>();
    }
    public void add(String item) {
        list.add(item);
        new AddedMessage(item).send();
    }
    public void printList() {
        new TodoListMessage(list).send();
    }
}
