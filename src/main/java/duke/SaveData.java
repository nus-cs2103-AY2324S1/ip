package duke;

import duke.task.Deadline;
import duke.task.Events;
import duke.task.Task;
import duke.task.ToDo;

import java.rmi.server.ServerNotActiveException;

/**
 * Object to keep track of all data from user's task list
 *
 * @author Lian Zhi Xuan
 */
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

    @Override
    public boolean equals(Object o) {
        if (o instanceof SaveData) {
            SaveData temp = (SaveData) o;

            if (type.length != temp.type.length) {
                return false;
            }

            for (int i = 0; i < type.length; i++) {

                if (!type[i].equals(temp.type[i])) {
                    return false;
                }

                switch (type[i]) {
                case "Task.ToDo":
                    if (!toDos[i].equals(temp.toDos[i])) {
                        return false;
                    }
                    break;

                case "Task.Deadline":
                    if (!deadlines[i].equals(temp.deadlines[i])) {
                        return false;
                    }
                    break;

                case "Task.Events":
                    if (!events[i].equals(temp.events[i])) {
                        return false;
                    }
                    break;
                }

            }
            return true;
        }
        return false;
    }

}
