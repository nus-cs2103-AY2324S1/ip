package ipbot.model;

public class Pair <S, T> {
    private S first;
    private T second;

    public Pair(S first, T second) {
        this.first = first;
        this.second = second;
    }

    public S getFirst() {
        return this.first;
    }

    public T getSecond() {
        return this.second;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this.first == null || this.second == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj instanceof Pair<?,?>) {
            Pair<?,?> p = (Pair<?, ?>) obj;
            if (p.first == null || p.second == null) {
                return false;
            }
            return this.first.equals(p.first) && this.second.equals(p.second);
        }
        return false;
    }
}
