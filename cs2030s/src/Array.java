// version 0.2
class Array<T> {
    private T[] array;

    Array(int size) {
        @SuppressWarnings("unchecked")
        this.array = (T[]) new Object[size];
    }

    public void set(int index, T item) {
        this.array[index] = item;
    }

    public T get(int index) {
        return this.array[index];
    }
}
