# ElonBot Project Template

ElonBot is your personal task management bot! He can help you keep track of tasks to be done, deadlines and upcoming
events.

## Features

### Listing all tasks

Lists all tasks you have entered.

Command format: `list`

Example usage:

```
list
```

### Adding a Todo Task

Add a Todo task.
A todo task only has a name.

Command format: `todo %TASK_NAME%`

Example usage:

```agsl
todo Go to the hospital // %TASK_NAME% = Go to the hospital
todo Complete CS2100 Assignment // %TASK_NAME% = Complete CS2100 Assignment
```

### Add a Deadline Task

Add a deadline task.
A deadline task has a set deadline for the task.

Command format: `deadline %TASK_NAME% /by %DEADLINE%`

where `%DEADLINE%` is of the following format:

- yyyy-MM-dd HH:mm

Example usage:

```agsl
deadline Submit iP /by 2023-01-01 10:00 // %DEADLINE% = 2023-01-01 10:00
```

### Add an Event Task

Add an event.
An event has a name, a start time, and an end time.

Command format: `event %TASK_NAME% /from %START_TIME% /to %END_TIME%`

where `%START_TIME%` and `%END_TIME%` is of the following format:

- yyyy-MM-dd HH:mm

Example usage:

```agsl
event Group Meeting /from 2023-01-01 12:00 /to 2023-01-01 13:00 
// %START_TIME% = 2023-01-01 12:00, %END_TIME = 2023-01-01 13:00
```

### Mark a Task as done

Marks a task as completed. 
Displays a [X] next to the name to indicate that it is done.

Command format: `mark %TASK_ID%`

where Task ID is a unique number to each task and can be seen when typing `list`.

Example usage:

```agsl
mark 1 // %TASK_ID% = 1. Marks the task with ID 1 as done.
```

### Mark a Task as undone

Marks a task as not completed.
Removes the [X] and replaces it with a [ ] next to the name to indicate that it is undone.

Command format: `unmark %TASK_ID%`

where Task ID is a unique number to each task and can be seen when typing `list`.

Example usage:

```agsl
unmark 1 // %TASK_ID% = 1. Marks the task with ID 1 as undone.
```

### Delete a Task

Deletes a task from the list.

Command format: `delete %TASK_ID%`

where Task ID is a unique number to each task and can be seen when typing `list`.

Example usage:

```agsl
delete 1 // %TASK_ID% = 1. Deletes the task with ID 1.
```

### Find a Task

Find a task by task name, based on a search query provided by the user.

Command format: `find %SEARCH%`

where search is any string input by the user.

Example usage:

```agsl
find CS2100 // %SEARCH% = CS2100. Finds all tasks with CS2100 in the name.
```

## Sort tasks

Sort tasks in one of 4 sorting methods, either:
1. by ID
2. by Name
3. by Type (Event, Deadline, Todo)
4. by Deadline Date

and in either 
1. Ascending order
2. Descending order.

The sort order is saved, so when you next type `list`, you will see the last chosen sorting order.

Command format: `sort %SORT_TYPE% %SORT_ORDER%`

where `%SORT_TYPE%` is either
1. id
2. name
3. type
4. deadline

and `%SORT_ORDER%` is either
1. asc
2. desc

Example Usage:

```agsl
sort name asc // %SORT_TYPE% = name, %SORT_ORDER% = asc. Sorts by name, ascending
sort deadline desc // %SORT_TYPE% = deadline, %SORT_ORDER% = desc. Sorts by deadline, descending.
```

