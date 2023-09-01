import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;


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
<<<<<<< HEAD
        this.toDos.add(toDo);
=======
        toDos.add(toDo);
>>>>>>> branch-Level-8
            System.out.println("GOT IT. ADDED:\n" + toDo.toString());
    }

    public void addDead(String task, LocalDateTime by) throws EmptyDescException {
        if (task.isEmpty()) {
            throw new EmptyDescException("Task description cannot be empty after 'deadline'.");
        }
        Deadline dead = new Deadline(task, by);
<<<<<<< HEAD
        this.toDos.add(dead);
=======
        toDos.add(dead);
>>>>>>> branch-Level-8
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

    public void addEvent(String task, LocalDateTime from, LocalDateTime to) throws EmptyDescException {
        if (task.isEmpty()){
            throw new EmptyDescException("Task description cannot be empty after 'Event', type again in the format event (your task) /from (yyyy-mm-dd TIME) /to (yyyy-mm-dd TIME).");
        }
        Event event = new Event(task, from, to);
<<<<<<< HEAD
        this.toDos.add(event);
=======
        toDos.add(event);
>>>>>>> branch-Level-8
        System.out.println("GOT IT. ADDED:\n" + event.toString());
    }

    public void deleteTask(int taskNum) {
        UserInput task = this.toDos.get(taskNum -1);
        this.toDos.remove(taskNum - 1);
        int len = this.toDos.size();
        System.out.println("REMOVED FOR YOU:)\n" + task.toString() +"\n Now you have:" + len + " tasks left.");
    }
}
