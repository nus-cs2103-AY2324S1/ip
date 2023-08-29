package pogo.tasks;

/**
 * Interface for a visitor that visits a task.
 */
public interface TaskVisitor {
    void visit(Deadline deadline);
    void visit(Event event);
    void visit(ToDo toDo);
}
