# A-CAT User Guide

A-CAT (Automated Chatbot Assistant for Tasks) is a desktop application that **manages your tasks**

## Features 

### Add and delete tasks

You can add and delete tasks of various types - Todo, Deadline and Event.

`todo` - Add a todo task

`deadline` - Add a deadline task

`event` - Add an event task

`delete` - Delete a task


### Manage tasks

You can mark the tasks as completed, unmark them or simply find and sort them!

`list` - List all tasks

`mark` - Marks task as done

`unmark` - Marks task as not done

`find` - Find a task

`sort` - Sort the tasks

## Usage

### `list` - List all tasks

Shows a list of all the tasks stored in A-CAT.

Format: `list`

Expected outcome:

A-CAT will list out all tasks if it is not empty.

```
Here are the tasks in your list:
1. [T][X] Feed my cat
2. [D][ ] Finish IP (by: 22 Sep 2023, 11:59PM)
```

If empty, the following outcome occurs.
```
There are no tasks to list.
```

### `todo` - Add a todo task

Adds a todo task to A-CAT.

Format: `todo [Task Name]`

Example: `todo Feed my cat`

Expected outcome:

A-CAT will successfully add the todo task with name `Feed my cat`.

```
Got it. I've added this task:
    [T][ ] Feed my cat
Now you have 1 tasks in the list.
```

### `deadline` - Add a deadline task

Adds a deadline task to A-CAT.

Format: `deadline [Task Name] /by [DateTime]`

`DateTime` - Date in the format of `DD-MM-YY [HHMM]`. [] indicates that `HHMM` is optional.

Example: `deadline Submit IP /by 22-09-2023 2359`

Expected outcome:

A-CAT will successfully add the deadline task with name `Submit IP` with the deadline of `22-09-2023 2359`.

```
Got it. I've added this task:
    [D][ ] Submit IP (by: 22 Sep 2023, 11:59PM)
Now you have 2 tasks in the list.
```

### `event` - Add an event task

Adds an event task to A-CAT.

Format: `event [Task Name] /from [DateTime] /to [DateTime]`

`DateTime` - Date in the format of `DD-MM-YY [HHMM]`. [] indicates that `HHMM` is optional.

Example: `event Recess Week /from 25-09-2023 /to 01-10-2023`

Expected outcome:

A-CAT will successfully add the event with name `Recess Week` happening from `25-09-2023` to `01-10-2023`.

```
Got it. I've added this task:
    [E][ ] Recess Week (25 Sep 2023 - 01 Oct 2023)
Now you have 3 tasks in the list.
```

### `mark` - Marks task as done

Updates the completion status of specified task to be done.

Format: `mark [Index]`

`Index` - The index of the task as shown in the `list` command.

Example: `mark 1`

Expected outcome:

A-CAT will successfully mark the task `Feed my cat` as done.

```
Nice! I've marked this task as done:
    [T][X] Feed my cat
```

### `unmark` - Marks task as not done

Updates the completion status of specified task to be not done.

Format: `unmark [Index]`

`Index` - The index of the task as shown in the `list` command.

Example: `unmark 1`

Expected outcome:

A-CAT will successfully mark the task `Feed my cat` as not done.

```
Ok, I've marked this task as not done yet:
    [T][ ] Feed my cat
```

### `delete` - Delete a task

Deletes the specified task from A-CAT

Format: `delete [Index]`

`Index` - The index of the task as shown in the `list` command.

Example: `delete 1`

Expected outcome:

A-CAT will successfully delete the task `Feed my cat`.

```
Noted. I've removed this task:
    [T][ ] Feed my cat
Now you have 2 tasks in the list.
```

### `find` - Find a task

Shows a list of the tasks containing the keyword stored in A-CAT.

Format: `find [Keyword]`

`Keyword` - A string to search for in your tasks.

Example: `find recess`

Expected outcome:

A-CAT will successfully find the event `Recess Week`.

```
Here are the matching tasks in your list:
1. [E][ ] Recess Week (25 Sep 2023 - 01 Oct 2023)
```

### `sort` - Sort tasks

Shows a list of the sorted tasks. Run the command again to get the opposite sort order (i.e. Ascending/Descending).

Format: `sort [Sorting Criteria]`

`Sorting Criteria` - Can be either name, type, completion or date.

Example: `sort date`

Expected outcome:

A-CAT will successfully sort the tasks.

```
Tasks sorted by date:
1. [D][ ] Finish IP (by: 22 Sep 2023, 11:59PM)
2. [T][X] Feed my cat
3. [E][ ] Recess Week (25 Sep 2023 - 01 Oct 2023)
```

### `bye` - Exit the conversation

Ends the conversation with A-CAT.

Format: `bye`

Expected outcome:

```
Bye! Hope to see you again soon!
```
