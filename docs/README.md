# Changoose
Changoose is a task manager that helps you keep track of your tasks, deadlines and events.
With a simple and intuitive interface, Changoose ensures that you never miss out on any important
tasks or events ever again!

## Features
> **Notes on the commands format**
>
> Words in `UPPER_CASE` are the parameters to be supplied by the ***user***.
> e.g. in `todo TASK NAME`, `TASK NAME` is a parameter which can be used, like `todo tutorial x`. 
 
### 1. Task Management
- **Add a Task**
- **List Tasks**
- **Mark Task as Done**
- **Unmark Task as Not Done**
- **Delete a Task**
- **Find a Task**

### 2. Persistent Storage
- Changoose keeps your Tasks ~~safe~~ **saved**!

## Usage

### `todo`/`deadline`/`event` - Add a task
Adds a task of the given type.
> **NOTE**
> 
> For tasks with dates, the date can be given as:
> 1. `monday`, `tuesday`, ... which Changoose will understand as the next Monday, Tuesday etc.
> 2. `today` or `tomorrow`, which Changoose understands as 23:59 of the current day, or the next day.
> 3. For more flexibility, these are the date formats which Changoose understands:
>    - `yyyy-MM-dd HHmm` e.g. 2023-08-10 1600 
>    - `yyyyMMdd HHmm` e.g. 20230810 1600
>    - `d MMM yyyy`, e.g. 10 Aug 2023
>    - `d MMMM yyyy`. e.g. 10 Aug 2023

Example of usage:

`todo TASK NAME`

`deadline TASK NAME /by DEADLINE_DATE`

`event TASK NAME /from START_DATE /to END_DATE`

```
Got it. I've added this task:
...
Now you have x task(s) in the list.
```

### `list` - List all added tasks
Lists all added tasks with a task number prefixed to them.

Example of usage:

`list`

```
1. ...
2. ...
...
```

### `mark`/`unmark` - Mark a task as complete/incomplete
Marks a task as complete, or Unmark a task.

> **NOTE**
> 
> The task number of a task can be obtained by the `list` command

Example of usage:

`mark NUMBER`

`unmark NUMBER`

```
Nice! I've marked this task as done:
    [T][X] some Todo task
```
or
```
OK, I've unmarked this task:
    [T][] some Todo task
```

### `delete` - Delete a task
Deletes an added task.

> **NOTE**
> 
> The task number of a task can be obtained by the `list` command
 
Example of usage:

`delete NUMBER`

```
Noted. I have removed this task:
    [T][X] some Todo task
Now you have x task in the list.
```
### `find` - Search for a task
Finds all tasks with the given keyword

> **NOTE**
> 
> The search is case-insensitive: `lower_case` is treated the same as `UPPER_CASE`
> Changoose can search for incomplete keywords. `home` will return tasks with names `go home` or `do homework`.
 
Example of usage:

`find KEYWORD`

```
Found x matching tasks in your list!
1. ...
2. ...
...
```