package catbot.task;

import java.util.ArrayList;
import java.util.function.Consumer;

public class TaskList extends ArrayList<Task> {

    public void ifValidIndexElse(int index, Consumer<Integer> ifValid, Consumer<Integer> otherwise) {
        Bounds bounds = getIndexBounds();
        if (bounds.contains(index)) {
            ifValid.accept(index);
        } else {
            otherwise.accept(index);
        }
    }

    public Bounds getIndexBounds() {
        return new Bounds(1, super.size());
    }

    public static class Bounds {
        public final int lowerBound, upperBound;

        public Bounds(int lowerBound, int upperBound) {
            this.lowerBound = lowerBound;
            this.upperBound = upperBound;
        }

        public boolean contains(int i) {
            return this.lowerBound <= i && i <= this.upperBound;
        }
    }

    public void markTask(int index) {
        super.get(index).setDone();
    }

    public void unmarkTask(int index) {
        super.get(index).setUndone();
    }

    public Task removeTask(int index) {
        return super.remove(index);
    }

}
