package catbot.internal;

public class Bounds {
    private final int lowerBound;
    private final int upperBound;

    public Bounds(int lowerBound, int upperBound) {
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
    }

    public boolean contains(int i) {
        return this.lowerBound <= i && i <= this.upperBound;
    }

    public int getLower() {
        return lowerBound;
    }

    public int getUpper() {
        return upperBound;
    }
}
