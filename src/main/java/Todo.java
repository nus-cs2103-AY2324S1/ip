class Todo extends Task {
    Todo(String name) {
        super(name);
    }

    Todo(String name, boolean marked) {
        super(name, marked);
    }

    @Override
    public Todo mark() {
        return new Todo(this.name, true);
    }

    @Override
    public Todo unmark() {
        return new Todo(this.name, false);
    }

    /**
     * Returns the name of task to be done.
     * @return Name of task to be done
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
