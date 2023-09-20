# BaekBot

BaekBot is a simple **task managing application** running on a graphical user interface(GUI). <br>
Users can develop great task managing skills with the help of BaekBot.

## Features

- Words in `UPPER_CASE` are the parameters to be supplied by the user.
  e.g. in `todo NAME`, `NAME` is a parameter which can be used as `todo homework`.

## Usage

### `list` - Listing all tasks

Generates a list of all current tasks.

Example of usage: 

`list`

<br>

### `find` - Finding of task by name

Finds all tasks that contain the keyword

Example of usage:

`find KEYWORD` 

- All tasks that contain the keyword will be retrieved. For example, if the task list contains `homework` and `homeworks`, `find homework` will retrieve both tasks.

Description of the outcome:
> Here are the matching tasks! <br>
> ...

<br>

### `delete` - Deleting a task from the list

Deletes a task at a given index on the list.

Example of usage:

`delete NUMBER`

- Tasks are numbered from 1 to n. For example, `delete 1` will delete the first task on the list.

Description of the outcome:
> Noted. I've removed this task: <br>
> ...

<br>

### `mark` / `unmark` - Marking a task on the list

Marks/Unmarks a task at a given index on the list.

Example of usage:

`mark NUMBER` / `unmark NUMBER`

- Tasks are numbered from 1 to n. For example, `mark 1` will mark the first task on the list as done, and `unmark 1` will unmark the first task on the list.

Description of the outcome:
> Nice! I've marked this task as done: <br>
> ...

> OK, I've marked this task as not done yet: <br>
> ...

<br>

### `todo` / `deadline` / `event` - Adding a task 

Adds a task to tasklist based on type. For tasks requiring input of date, date must be formatted in `YYYY-MM-DD` format.

Example of usage:

`todo NAME` 
<br><br>
`deadline NAME /by DUE_DATE`
<br><br>
`event NAME /from START_DATE /to END_DATE`

- There cannot be duplicate tasks sharing the same name. For example, once a `todo homework` is added, another `todo homework` cannot be added. 
- However, a `deadline` or `event` with the description `homework` can be added. <br>

Description of the outcome:
> Got it. I've added this task:
> <br>...

