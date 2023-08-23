package ToDoList;

import java.util.ArrayList;

public class ToDo {
    private final String name;

    public ToDo(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
