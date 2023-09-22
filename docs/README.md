# User Guide
This is Meowies, a task list manager that helps you to keep track of your tasks.

## Quick Start
1. Ensure you have Java `11` or above installed in your Computer.
2. Download the latest `duke.jar` from [here](https://github.com/KamiliArsyad/ip/).
3. Double-click the file to start the app. You should see the GUI below.

![DukeUi](Ui.png)
## Features

### Feature-Todo

Adds a **Todo** task to your list of tasks.

Usage: `todo <task>`

eg. `todo love the cats`

You should see the following from Meowies:
```
added: [T][] love the cats
```

### Feature-Deadline

Adds a task with a deadline (a **deadline** task) to your list of tasks.

Usage: `deadline <task_name> /by <YYYY-MM-DD>`

e.g. `deadline Cat Bath /by 2022-09-10`

You should see the following from Meowies:

```
added: [D][] Cat Bath (by: Sep 10 2022)
```

### Feature-Event

Adds an **event** task that begins and ends at the specified dates to your list of tasks.

Usage: `event <task_name> /from <YYYY-MM-DD> /to <YYYY-MM-DD>`

e.g. `event cat parade /from 2022-09-10 /to 2022-09-11`

You should see the following from Meowies:
```
added: [E][] cat parade (from: Sep 10 2022, to: Sep 11 2022)
```

### Feature-Mark

**Marks** a task from your task list **as completed**.

Usage: `mark <task_index>`

e.g. `mark 1`

For example, if you marked a `Deadline` as done, You should see the following from Meowies:
```
Nice! I've meowrked this task as done:
    [D][X] CS2103T assignment 1 (by 2020-09-10)
```
The `X` denotes that the task has been marked as completed.

### Feature-Unmark

**Marks** a task from your task list **as incomplete**.

Usage: `unmark <task_index>`

e.g. `unmark 1`

For example, if you marked a `Deadline` as done, You should see the following from Meowies:
```
Oh meow! I've unmarked this task as done:
    [D][ ] CS2103T assignment 1 (by 2020-09-10)
```

### Feature-Delete

**Deletes** a task from your task list.

Usage: `delete <task_index>`
e.g. `delete 1`

For example, if you deleted a `Todo` task, You should see the following from Meowies:
```
Noted. I've removed this task:
    [T][ ] love the cats
    Now you have 2 tasks in the list.
```


### Feature-List

**Lists** all the tasks in your task list.

Usage: `list`

You should see the following from Meowies:

```
  [T][X] love the cats
  [D][ ] CS2103T assignment 1 (by 2020-09-10)
```

Tasks denoted by `X` are tasks that you have marked as completed.


### Feature-Find

**Finds** tasks in your task list that match a certain search term.

Usage: `find <search_term>`
e.g. `find cat`

If there exists a task that matches the search term, You should see the following from Meowies:
```
  [T][X] love the cats
```

### Feature-Sort

**Sorts** the tasks in your task list by date and by the alphanumerical order of the task type.

Usage: `sort`

E.g. `sort` sorts the list in **ascending** order, by **deadline/time** of tasks.
You should see the following from Meowies:
```
  [T][X] love the cats
  [D][ ] CS2103T assignment 1 (by 2020-09-10)
```

### Feature-Bye

**Exits** the application.

Usage: `bye`
