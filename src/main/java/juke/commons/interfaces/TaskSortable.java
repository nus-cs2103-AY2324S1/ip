package juke.commons.interfaces;

import juke.commons.enums.SortTypeEnum;
import juke.tasks.JukeTask;

/**
 * Defines a contract whereby objects of generic type {@code T extends JukeTask} can be sorted.
 *
 * @param <T> Generic type that extends {@code JukeTask}
 */
public interface TaskSortable<T extends JukeTask> {
    /**
     * Sorts the objects of type {@code T} in the specified order and by the specified sort type.
     *
     * @param task the {@code JukeTask} object to be compared with
     * @param sortType the type of sort to perform on the tasks
     */
    int sortBy(T task, SortTypeEnum sortType);
}
