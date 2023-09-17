# Sae User Guide

## Features 

### Manage Tasks

Differentiate tasks between ToDo, Deadline and Event.
Add, delete and mark/unmark any tasks.

### Find Tasks

Find tasks related to user given keyword.

## Usage

### `todo {task_details}` - Create a new Todo task

Example of usage: 

`todo sleep`

Expected outcome: Creates a new todo task and adds it to your list of tasks.

```
Got it. I've added this task:
  [T][] sleep
Now you have 1 tasks(s) in the list.
```

### `deadline {task_details} /by {due date}` - Create a new deadline task

Example of usage: Creates a new deadline task and adds it to your list of tasks.

`deadline go to sleep /by 12/12/2012 2359`

Expected outcome:

```
Got it. I've added this task:
  [D][] go to sleep (by: 12 December 2012 11pm)
Now you have 1 tasks(s) in the list.
```

### `event {task_details} /from {start time} /to {end time}` - Create a new event task

Example of usage: 

`event nap /from 3pm /to 6pm`

Expected outcome: Creates a new event task and adds it to your list of tasks.

```
Got it. I've added this task:
  [E][] nap (from: 3pm to: 6pm)
Now you have 1 tasks(s) in the list.
```

### `find {keyword}` - Find all related tasks

Example of usage: 

`find sleep`

Expected outcome: Lists all tasks related to the given keyword.

```
Here are some matching tasks in your list:
  1.[T][] sleep
  2.[D][] go to sleep (by: 12 December 2012 11pm)
Now you have 1 tasks(s) in the list.
```

### `delete {task_index}` - delete task in the index

Example of usage: 

`delete 4`

Expected outcome: Deletes the specified task at the index from the list.

```
Noted. I've removed this task:
  [T][] sleep
Now you have 3 tasks in the list.
```

### `delete {start_number to end_number}` - delete tasks within a range

Example of usage:

`delete 2 to 4`

Expected outcome: Deletes tasks within the specified range of indices.

```
Task 2 to Task 4 has been deleted.
  The deleted tasks are :
[T][] sleep2
[T][] sleep3
[T][] sleep4
```

### `mark {task_index}` - Marks specified task as done

Example of usage: 

`mark 1`

Expected outcome: Marks the specified task at the index as done.

```
Nice! I've marked this as done:
[T][X] sleep
```
### `unmark {task_index}` - Marks specified task as not done

Example of usage: 

`unmark 1`

Expected outcome: Removes the mark as done from specified task at the index.

```
I have unmarked this task as done:
[T][] sleep
```

### `list` - Lists out all tasks

Example of usage: 

`list`

Expected outcome: Lists out all tasks in the current task list

```
1.[T][] sleep
2.[D][] passaway (by: 12 December 2012 11pm)
3.[E][] nap (from: 3pm to: 6pm)
```


### `bye` - Close program

Example of usage: 

`bye`

Expected outcome: Sae says goodbye and chat bot window is closed

```
Bye. Hope to see you again soon!
```
