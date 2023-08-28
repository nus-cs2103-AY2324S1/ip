package duke;

import duke.task.Deadline;
import duke.task.Events;
import duke.task.Task;
import duke.task.ToDo;

public class SaveData {
    
    public String[] type;
    
    public ToDo[] toDos;
    
    public Deadline[] deadlines;
    
    public Events[] events;

    public SaveData(Task[] list) {
        int n = list.length;
        type = new String[n];
        toDos = new ToDo[n];
        deadlines = new Deadline[n];
        events = new Events[n];

        for (int i = 0; i < n; i++) {
            type[i] = list[i].type();
            switch (type[i]) {
                case "Task.ToDo":
                    toDos[i] = (ToDo) list[i];
                    break;
                case "Task.Deadline":
                    deadlines[i] = (Deadline) list[i];
                    break;
                case "Task.Events":
                    events[i] = (Events) list[i];
                    break;

            }
        }
    }

}
