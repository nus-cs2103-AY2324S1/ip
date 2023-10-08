# Robert - User Guide
![Robert tagline](RobertTagline.png)

Robert is an application designed for efficient task, deadline, and event
management. It also offers functionality to mark and filter activities that 
are tracked by the application. If you are a fast typer, Robert can accomplish
tracking tasks faster than traditional GUI applications.

## Features 

### ToDo, Event, Deadline

Robert allows for the tracking of different types of tasks:
- A generic todo task that has no time restrictions
- An event task that has a specific timeframe
- A deadline task that has a due date.

### List, Mark, Unmark, Delete, Filter, Find, Clear

Robert also allows for the manipulation of stored tasks for more flexible
storage of information. These additional features include:
- marking and unmarking of tasks
- deleting and clearing of particular/all task(s)
- filtering and finding of tasks with relevant dates or keywords

## Usage

### `todo` - Add a todo

This command will add a todo task into your task list.

Format: 

`todo [DESCRIPTION]`

Example of usage:

`todo Buy toothpaste`

Expected outcome:

A `todo` task of buying toothpaste is added to your task list.

```
Got it. I have added this task:
 [T][] Buy toothpaste
Now you have 1 task in the list.
```

### `event` - Add an event

This command will add an event task into your task list.

Format:

`event [DESCRIPTION] /from [YYYY-MM-DD] /to [YYYY-MM-DD]`

Example of usage:

`event Party /from 2023-10-03 /to 2023-10-10`

Expected outcome:

An `event` task of a party with the given timeframe is added to your task list.

```
Got it. I have added this task:
 [E][] Party (from: Oct 03 2023 to: Oct 10 2023)
Now you have 1 task in the list.
```

### `deadline` - Add a deadline

This command will add a deadline task into your task list.

Format:

`deadline [DESCRIPTION] /by [YYYY-MM-DD]`

Example of usage:

`deadline Assignment /by 2023-11-06`

Expected outcome:

A `deadline` task of assignment with the given deadline is added to your task list.

```
Got it. I have added this task:
 [D][] Assignment (by: Nov 06 2023)
Now you have 1 task in the list.
```

### `list` - List tasks

This command will list all the tasks stored in the task list.

Example of usage:

`list`

Expected outcome:

All stored tasks are listed.

```
Here are the tasks in your list:
1.[E][] Party (from: Oct 03 2023 to: Oct 10 2023)
2.[D][] Assignment (by: Nov 06 2023)
```

### `mark` - Mark a task

This command will mark a task to signify task as completed.

Format:

`mark [INDEX]`

Example of usage:

`mark 2`

Expected outcome:

The second task in your list of tasks is marked.

```
Nice! I've marked this task as done:
 [D][X] Assignment (by: Nov 06 2023)
```

### `unmark` - Mark a task

This command will unmark a task to signify task as incomplete.

Format:

`unmark [INDEX]`

Example of usage:

`unmark 2`

Expected outcome:

The second task in your list of tasks is unmarked.

```
Ok, I've unmarked this task as not done yet:
 [D][] Assignment (by: Nov 06 2023)
```

### `delete` - Delete a task

This command will delete a task in your task list.

Format:

`delete [INDEX]`

Example of usage:

`delete 2`

Expected outcome:

The second task in your list of tasks is deleted.

```
Noted. I've removed this task:
 [D][] Assignment (by: Nov 06 2023)
Now you have 1 task in the list.
```

### `clear` - Delete all tasks

This command will delete all tasks in your task list.

Example of usage:

`clear`

Expected outcome:

All tasks are cleared in your task list.

```
Understood. I've removed every task in your list.
Now your list of tasks is empty!
```

### `filter` - Filter tasks with relevant date

This command will filter out events that is happening on given date,
and filter out deadlines that have the given date as deadline.

Format:

`filter [YYYY-MM-DD]`

Example of usage:

`filter 2023-11-25`

Expected outcome:

Events that are happening on given date and deadlines that have the 
given date as deadline are printed out.

```
Here are the tasks that are due on Nov 25 2023:
1. [D][] Assignment (by: Nov 25 2023)
2. [D][] Project (by: Nov 25 2023)
This is the task that is happening on Nov 25 2023:
1. [E][] Exams (from: Nov 10 2023 to: Dec 02 2023)
```


### `find` - Find tasks with relevant keyword

This command will find tasks that are relevant to given keyword.

Format:

`find [KEYWORD]`

Example of usage:

`find Assignment`

Expected outcome:

Tasks that contain the word `Assignment` will be shown.

```
Below is/are the task(s) associated with 'Assignment':
1.[D][] Assignment (by: Nov 25 2023)
2.[T][] CS2103 Assignment
```


### `bye` - Exit the application

This command will exit your application.

Example of usage:

`bye`

Expected outcome:

Robert will close after a few seconds.

```
Goodbye. Hope to see you again soon! Closing in a few seconds...
```