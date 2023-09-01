import java.util.ArrayList;

public class TaskList {
    ArrayList<UserInput> toDos;

    public TaskList() {
        toDos = new ArrayList<>(100);
    }

    //keep taking in userInput
    //add userInput to the list
   /* public void addInput(String textInput) {
        UserInput userInput = new UserInput(textInput);
            this.toDos.add(userInput);
            System.out.println("added:" + userInput.text);
    } */

    public void addToDo(String task) throws EmptyDescException {
        if (task.isEmpty()) {
            throw new EmptyDescException("Task description cannot be empty after 'todo'.");
        }
        ToDo toDo = new ToDo(task);
        this.toDos.add(toDo);
            System.out.println("GOT IT. ADDED:\n" + toDo.toString());
    }

    public void addDead(String task, String by) throws EmptyDescException {
        if (task.isEmpty()) {
            throw new EmptyDescException("Task description cannot be empty after 'deadline'.");
        }
        if (by.isEmpty()) {
            throw new EmptyDescException("Deadline not provided, type again in the format /by (deadline)");
        }
        Deadline dead = new Deadline(task, by);
        this.toDos.add(dead);
        System.out.println("GOT IT. ADDED. DEADD:\n" + dead.toString());
    }

    public void markTask(int taskNumber) throws IllegalArgumentException {
        if (taskNumber <= 0 || taskNumber > 100) {
            throw new IllegalArgumentException("Invalid task number");
        }
        UserInput task = this.toDos.get(taskNumber -1);
        task.markAsDone();
        System.out.println("Done. You're Done. \n" + task.toString());
    }

    public void unMarkTask(int taskNumber) {
        UserInput task = this.toDos.get(taskNumber - 1);
        task.unMark();
        System.out.println("Done. Stop being lazy.\n" + task.toString());
    }


    public void listOut() {
        for (int i = 0; i < toDos.size(); i++) {
            System.out.println((i + 1) + "." + this.toDos.get(i).toString());
        }
    }

    public int findTask(String task) {
        for (int i = 0; i < toDos.size(); i++) {
            if (task.equalsIgnoreCase(toDos.get(i).text)) {
                return i;
            }
        }
        return -1;
    }

    public void addEvent(String task, String from, String to) throws EmptyDescException {
        if (from.isEmpty() && to.isEmpty()) {
            throw new EmptyDescException("Event time not provided, type again in the format /from (timing) /to (timing)");
        }
        if (task.isEmpty()){
            throw new EmptyDescException("Task description cannot be empty after 'Event'.");
        }
        Event event = new Event(task, from, to);
        this.toDos.add(event);
        System.out.println("GOT IT. ADDED:\n" + event.toString());
    }

    public void deleteTask(int taskNum) {
        UserInput task = this.toDos.get(taskNum -1);
        this.toDos.remove(taskNum - 1);
        int len = this.toDos.size();
        System.out.println("REMOVED FOR YOU:)\n" + task.toString() +"\n Now you have:" + len + " tasks left.");
    }
}
