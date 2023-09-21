# User Guide

## Features 

list, mark, unmark, delete, todo, deadline, event, find, sort, bye

### Feature-list
lists out the tasks in your todo list

### Feature-mark,unmark
marks/unmarks a task as done

### Feature-delete
deletes a task or all tasks from task list

### Feature-todo,deadline,event
adds the corresponding type of task in to taskslist

### Feature-find
finds tasks based on their description

### Feature-sort
sorts the tasks in your tasklist based on time
event tasks sorted based on start time
deadline tasks sorted based on the done by time
todo tasks default to be placed lower than events and deadlines
tasks are sorted alphanumerically if their time are the same

## Usage

### `list` -  list out tasks
Example of usage: 

`list`

Expected outcome:

a list of the tasks in the list of tasks

```
OK patrick
Here are the tasks in your list:
1. [D][ ] homework (by: 2023-08-20)
2. [T][ ] projectwork
```

### `mark` -  mark a task as done
Example of usage:

`mark 2`

Expected outcome:

mark the 2nd task in the task list with an X

```
OK patrick
Here are the tasks in your list:
1. [D][ ] homework (by: 2023-08-20)
2. [T][X] projectwork
```

### `unmark` -  unmark a task
Example of usage:

`unmark 2`

Expected outcome:

unmark the 2nd task in the task list

```
OK patrick
Here are the tasks in your list:
1. [D][ ] homework (by: 2023-08-20)
2. [T][] projectwork
```

### `delete` - deletes a task
Example of usage:

1. `delete 2`
2. `delete All`

Expected outcome:

1. deletes the 2nd task in the task list
2. deletes all the tasks in the task list

```
OK patrick
Noted . I've removed this task:
[T][] projectwork
Now you have 1 tasks in your list
```
```
OK patrick
I have cleared all tasks from your todo list
```

### `todo` -  adds a todo tasks into task list
Example of usage:

`todo practice quiz`

Expected outcome:

adds a todo tasks with description practice quiz into task list

```
OK patrick
Got it! I've added this task:
 [T][] practice quiz
Now you have 1 tasks in the list.
```

### `deadline` -  adds a deadline tasks into task list
Example of usage:

`deadline assignment1 /by 2023-09-12`

Expected outcome:
adds a deadline with description assignment1 that needs to be done by 2023-09-12

```
OK patrick
Got it! I've added this task:
 [D][] assignment1 (by: 2023-09-12)
Now you have 2 tasks in the list.
```

### `event` -  adds an event tasks into task list
Example of usage:

`event assignment1 /from 2023-09-11 /to 2023-09-13`

Expected outcome:
adds an event with description assignment1 that runs from 2023-09-11 to 2023-09-13

```
OK patrick
Got it! I've added this task:
 [E][] orientation (from: 2023-09-11, to: 2023-09-13)
Now you have 3 tasks in the list.
```

### `find` -  finds tasks based on a keyword that matches the task's description
Example of usage:

`find assignment`

Expected outcome:
returns a list of tasks that have description that contains the keyword given
```
OK patrick
Here are the matching tasks in your list:
1. [E][] assignment1 (from: 2023-09-11, to: 2023-09-13)
```

### `sort` - sort and returns the list of tasks
Example of usage:

`sort`

Expected outcome:
sorts and returns the list of tasks
```
OK patrick
I have sorted your list
Here are the tasks in your list
1. [E][ ] orientation (from: 2023-09-11, to: 2023-09-13)
2. [D][ ] assignment1 (/by 2023-09-12)
3. [T][ ] practice quiz
```

### `bye` - closes the application after 3 seconds
Example of usage:

`bye`

Expected outcome:
after saying bye
window will close itself after a 3 seconds delay
```
OK patrick
Bye, this window will magically disappear in 3 seconds
```




