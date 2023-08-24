class Task {
    private final String name;

    Task(String name) {
        this.name = name;
    }

    /**
     * Returns the name of task to be done.
     * @return Name of task to be done
     */
    @Override
    public String toString() {
        return this.name;
    }
}
