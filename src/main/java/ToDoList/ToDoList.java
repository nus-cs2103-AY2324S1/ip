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
        listOutput.append("Here are the tasks in your list: ");

        for (int i = 0; i < this.toDoList.size(); i++) {
            listOutput.append("\n\t").
                    append((i + 1)).
                    append(". ").
                    append(this.toDoList.get(i));
        }

        return listOutput.toString();
    }

    public String mark(int toDoIndex) {
        this.toDoList.get(toDoIndex - 1).mark();
        return "Nice! I've marked this task as done: \n\t\t" + this.toDoList.get(toDoIndex - 1);
    }

    public String unmark(int toDoIndex) {
        this.toDoList.get(toDoIndex - 1).unmark();
        return "OK, I've marked this task as not done yet: \n\t\t" + this.toDoList.get(toDoIndex - 1);
    }

    @Override
    public String toString() {
        System.out.println(this.toDoList);
        return this.toDoList.toString();
    }
}
