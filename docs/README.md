# User Guide

## Key Features 

### Add tasks
Easily create new tasks, whether it is a todo, deadline or an event. 

### Keep track of them
Too many tasks? Start on one now and get the satisfaction of marking them done.

### Get reminders
View the tasks that are due today, next week, or the next two weeks.

## Note on command format
- Words in `UPPER_CASE` are parameters to be supplied by the user.
    
    e.g. in `todo TASK_INFO`, `TASK_INFO` is a parameter that can be used as `todo bake cookies`.
- Input dates must be of the format `YYYY-MM-DD HH:mm` format.

  e.g. the `DATE` parameter can be`2023-09-09 07:00`
- Items in square brackets are optional.

    e.g. in `list [FLAG]`, the `[FLAG]` can be omitted. Hence, `list` is also a valid command that 
lists all tasks. 

## Usage

### `todo` - Adding a todo

Adds a todo task to the list.

Format: `todo TASK_INFO`

Example of usage: `todo bake cookies`

Expected outcome:

```
(｀･ω･´)ﾉ New task added:
[T] [] bake cookies
Now you have 1 task in the list!
```

### `deadline` - Adding a deadline

Adds a deadline task to the list.

Format: `deadline TASK_INFO /by DATE`

Example of usage: `deadline bake 2 cookie batches /by 2023-10-10 09:00`

Expected outcome:

```
(｀･ω･´)ﾉ New task added:
[D] [] bake 2 cookie batches (by: 2023-10-10 09:00)
Now you have 2 tasks in the list!
```

### `event` - Adding an event

Adds an event task to the list.

Format: `event TASK_INFO /from START_DATE /to END_DATE`

Example of usage: `event cookie fundraiser /from 2023-10-10 14:00 /to 2023-10-11 11:00`

Expected outcome:

```
(｀･ω･´)ﾉ New task added:
[E] [] cookie fundraiser (from: 2023-10-10 14:00 to: 2023-10-11 11:00)
Now you have 3 tasks in the list!
```

### `list` - Listing tasks

Lists the tasks in the list.

Format: `list [FLAG]`

`[FLAG]` can be used to list tasks that are due/happen during a certain period. 
- `list` : lists all tasks
- `list today` : lists tasks that are due/happen today
- `list week` : lists tasks that are due/happen this week
- `list fort` : lists tasks that are due/happen in the next 2 weeks

Example of usage: `list`

Expected outcome:

```
(⇀‸↼‶)⊃━☆ﾟ.*･｡ﾟHere are your tasks for the day:
1. [T] [] bake cookies
2. [D] [] bake 2 cookie batches (by: 2023-10-10 09:00)
3. [E] [] cookie fundraiser (from: 2023-10-10 14:00 to: 2023-10-11 11:00)
```

### `mark` - Marking a task as done

Marks the task identified by the input task ID as completed.

Format: `mark TASK_ID`

Example of usage: `mark 2`

Expected outcome:

```
ଘ(੭ˊᵕˋ)੭ Yay! This task is completed:
[D] [X] bake 2 cookie batches (by: 2023-10-10 09:00)
```

### `unmark` - Unmarking a task

Marks the task identified by the input task ID as uncompleted.

Format: `unmark TASK_ID`

Example of usage: `unmark 2`

Expected outcome:

```
"໒( ̿･ ᴥ ̿･ )ʋ All righty, I've marked this task as uncompleted:
[D] [] bake 2 cookie batches (by: 2023-10-10 09:00)
```

### `find` - Finding a task

Finds tasks that contain the input keyword.

Format: `find KEYWORD`

Example of usage: `find batches`

Expected outcome:

```
(⇀‸↼‶)⊃━☆ﾟ.*･｡ﾟ Here are the matching tasks in your list:
1. [D] [] bake 2 cookie batches (by: 2023-10-10 09:00)
```

### `delete` - Deleting a task

Deletes the task identified by the input task ID.

Format: `delete TASK_ID`

Example of usage: `delete 1`

Expected outcome:

```
(/ˊuˋ)/ Ok! I've removed this task:
[T] [] bake cookies
Now you have 2 tasks in the list!
```

### `bye` - Saying goodbye to BUTTER

Terminates the BUTTER chatbot program.

Format: `bye`

Expected outcome:

```
໒(⊙ᴗ⊙)७ Signing off, see you later!
```