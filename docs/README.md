# Duke

A chatBot to help maintain all your current tasks.
## Features 

### Add ToDo, Deadline and Event

1. ToDos: tasks without any date/time attached to it _e.g., visit new theme park_
2. Deadlines: tasks that need to be done before a specific date and time _e.g., submit report by 11-10-2023 1800_
3. Events: tasks that start at a specific date & time and ends at a specific date &time
_e.g., (a) team project meeting 2-10-2023 1000 to 2-10-2023 1200_

### Delete Task

The ability to delete a specific task that has already been added.

### Mark Task 

The ability to mark a specific task as completed. 

### UnMark Task 

The ability to unmark a specific task as not completed yet. 

### Mass Operation 

The ability to mass mark, unmark or delete a few specific tasks. 

### List

The ability to see all the tasks and their status that has already been added.

### Bye

Saves all current tasks. 

## Usage

### `todo [name]` - Adds a todo task

- Creates a new task with name as `[name]` and adds it to the list. 
- The new todo task created will be not completed yet.

Example of usage: 

`todo item 1` 

Expected outcome:

```
Got it. I've added this task: 
[T] [ ] item 1
Now you have 1 tasks in the list
```

### `deadline [name] /by [DD-MM-YYY HHMM]` - Adds a deadline task 

- Creates a new task with name as `[name]` and deadline given by `[DD-MM-YYYY HHMM]` adds it to the list. 
- The deadline date must be in `DD-MM-YYYY` and time must be `HHMM` in 24-hour format.
- The new deadline task created will be not completed yet.

Example of usage:

`deadline item 1 /by 23-10-2023 1800`

Expected outcome:

```
Got it. I've added this task: 
[D] [ ] item 1 (by: Oct 23 2023 18:00)
Now you have 1 tasks in the list.
```

### `event [name] /from [DD-MM-YYYY HHMM] /to [DD-MM-YYYY HHMM]` - Adds a event task

- Creates a new task with name as `[name]`, event start given by the first `[DD-MM-yYYY HHMM]` and
event end given by the second `[DD-MM-YYYY HHMM]` adds it to the list.
- The event date must be in `DD-MM-YYYY` and time must be `HHMM` in 24-hour format.
- The new event task created will be not completed yet.

Example of usage:

`event item 1 /from 23-10-2023 1800 /to 24-10-2023 1800` 

Expected outcome:

```
Got it. I've added this task: 
[E] [ ] item 1 (from: Oct 23 2023 18:00 to: Oct 24 2023 18:00)
Now you have 1 tasks in the list.
```

### `list` - Displays all current tasks that have been added

- Shows all items in the current system with their status 
- A `[T]` represents a ToDo task. A `[D]` represents a Deadline task. A `[E]` represents an Event task.
- A `[X]` marks that the task has already been completed. A `[ ]` marks that the task has not been completed.

Example of usage:

`list`

Expected outcome:

```
Here are the tasks in your list: 
------------------------------------
1. [T][X] item 1
2. [D][ ] item 2 (by: Oct 23 2023 18:00)

------------------------------------
```

### `mark [index]` - Marks item as completed

- Mark the item given by `[index]` as completed.

Example of usage:

`mark 1`

Expected outcome:

```
Nice! I've marked this task as done:
[T][X] item 1
```

### `unmark [index]` - Marks item as not completed

- Mark the item given by `[index]` as not completed.

Example of usage:

`unmark 1`

Expected outcome:

```
Ok, I've marked this task as not done yet:
[T][ ] item 1
```

### `delete [index]` - Deletes the item

- Deletes the item given by `[index]` from the system.

Example of usage:

`delete 1`

Expected outcome:

```
Noted, I've removed this task:
[T][ ] item 1
Now you have 2 tasks in your list.
```

### `mass [action] [indices]` - Performs mass operations

- Perform action given by `[action]` to the items given by `[indices]`
- `[action]` can be `delete`, `mark` or `unmark`

Example of usage:

`mass mark 1 2 3` 

Expected outcome:

```
Nice! I've marked this task as done:
[T][X] item 1

Nice! I've marked this task as done:
[T][X] item 2

Nice! I've marked this task as done:
[T][X] item 3
```
### `bye` - Shuts the bot down and saves current tasks

- Saves all the current tasks and state of tasks to a file.
- Essential command before closing the bot, otherwise tasks will not be saved.

Example of usage:

`bye`

Expected outcome:

```
Bye. Hope to see you again soon!
```