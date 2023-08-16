package penguin;

import java.util.ArrayList;

public class Task {
    protected boolean done;
    private String name;

    public Task(String name) {
        this.done = false;
        this.name = name;
    }

    public String getName() {
        return this.name;
    }


}
