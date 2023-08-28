import jdk.jfr.Event;

import java.util.List;
import java.util.ArrayList;

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
                case "ToDo":
                    toDos[i] = (ToDo) list[i];
                    break;
                case "Deadline":
                    deadlines[i] = (Deadline) list[i];
                    break;
                case "Events":
                    events[i] = (Events) list[i];
                    break;

            }
        }
    }

}
