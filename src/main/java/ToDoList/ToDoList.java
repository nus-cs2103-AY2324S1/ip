package ToDoList;

import java.util.ArrayList;

public class ToDoList {
    private final ArrayList<ToDo> toDoList;

    public ToDoList() {
        this.toDoList = new ArrayList<ToDo>();
    }

    public String addList(String newList) {
        ToDo newToDo = new ToDo(newList);
        this.toDoList.add(newToDo);
        return "added: " + newToDo;
    }

    public String list() {
        StringBuilder listOutput = new StringBuilder();
        for (int i = 0; i < this.toDoList.size(); i++) {
            if (i != 0) {
                listOutput.append("\n\t");
            }
            listOutput.append((i + 1)).
                    append(". ").
                    append(this.toDoList.get(i));
        }
        return listOutput.toString();
    }
    @Override
    public String toString() {
        System.out.println(this.toDoList);
        return this.toDoList.toString();
    }
}
