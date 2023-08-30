package duke.task;

import java.util.ArrayList;

public abstract class Task {
    protected boolean done;
    protected String name;

    protected ArrayList<ArrayList<Integer>> tracker = new ArrayList<ArrayList<Integer>>(26);

    public Task(String name) {
        this.name = name;
        for (int i = 0; i < 26; i++) {
            tracker.add(new ArrayList<>());
        }
        for (int i = 0; i < name.length(); ++i) {
            if (name.charAt(i) != ' ') {
                if (name.charAt(i) >= 'a' && name.charAt(i) <= 'z') {
                    tracker.get((int) (name.charAt(i) - 'a')).add(i);
                } else if (name.charAt(i) >= 'A' && name.charAt(i) <= 'Z') {
                    tracker.get((int) (name.charAt(i) - 'A')).add(i);
                }
            }
        }
        this.done = false;
    }

    public void mark() {
        done = true;
    }

    public void unmark() {
        done = false;
    }

    public abstract String stringifyTask();

    public boolean find(String arg) {
        int k = arg.charAt(0) - 'A';
        if (k < 0) {
            return false;
        }
        if (k >= 26) {
            k = arg.charAt(0) - 'a';
        }
        if (k < 0 || k >= 26) {
            return false;
        }
        for (int i : tracker.get(k)) {
            boolean cannot = false;
            for (int j = 0; j < arg.length(); j++) {
                if (i + j >= name.length()) {
                    cannot = true;
                    break;
                }
                if (name.charAt(i + j) != arg.charAt(j)) {
                    cannot = true;
                    break;
                }
            }
            if (!cannot) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        if (done) {
            return "[X] " + name;
        } else {
            return "[ ] " + name;
        }
    }
}
