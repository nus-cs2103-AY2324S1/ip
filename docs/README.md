# User Guide
Nila is your task manager application which keeps track of your day to day tasks. It is an easy to way to manage your tasks. It can handle three type of tasks todo, deadline and event. Also this task manager has some additional features for eg: update tasks. 

## Features 

### Feature - Add ToDo tasks
#### todo

todo is a task that has only description of the task and allows you to add new task to the task list.

**Usage** - todo [task description]

**For example:**

`todo write reflection`

**Expected Output:**

```
Got it! I've added the task to your list.
[T][] write reflection

Now you have 1 tasks in the list.
```

### Feature - Add deadline tasks
#### deadline

deadline is a task that has a description of the task and deadline in date time to finish the task. 

**Usage** - `deadline [task description] /by [dateTime]`

**For example:**
`deadline submit ip /by 24-09-2023 23:59`

**Expected Output:**

```
Got it! I've added the task to your list.
[D][] submit ip (by: 24 Sep 2023 23:59)

Now you have 2 tasks in the list.
```

### Feature - Add event tasks
#### event
event is a task that has a description of the task with two other parameters event starting from in date time and the event ending time.

**Usage** - `event [task description] /from [dateTime] /to [time]`

**For example:**

`event attend meeting /from 24-09-2023 10:00 /to 12:00`

**Expected Output:**

```
Got it! I've added the task to your list.
[E][] attend meeting (from: 24 Sep 2023 10:00 to: 12:00)

Now you have 3 tasks in the list.
```

### Feature - mark tasks
#### mark
mark method is used to mark the tasks which are completed using its index number.

**Usage** - `mark [index number]`

**For example:**

`mark 2`

**Expected Output:**

```
Nice! I've marked this task as done.
[D][X] submit ip (by: 24 Sep 2023 23:59)
```

### Feature - unmark tasks
#### unmark
unmark method is used to remove the mark from the tasks which are not completed using its index number.

**Usage** - `unmark [index number]`

**For example:**

`umark 2`

**Expected Output:**

```
Ok, I've unmarked this task.
[D][] submit ip (by: 24 Sep 2023 23:59)
```

### Feature - delete tasks
#### delete
delete method is used to remove a task from the tasks list using its index number.

**Usage** - `delete [index number]`

**For example:**

`delete 2`

**Expected Output:**

```
Noted! I've removed this task from your list.

Now you have 2 tasks in your list.
```

### Feature - view the task list
#### list
list feature is used to display all the tasks from your task list.

**Usage** - `list`

**For example:**

`list`

**Expected Output:**

```
Here are your tasks:
1.[T][] write reflection
2.[E][] attend meeting (from: 24 Sep 2023 10:00 to: 12:00)
```

### Feature - find tasks
#### find
find feature is used to find any tasks that matches the given description.

**Usage** - `find [task description]`

**For example:**

`find write`

**Expected Output:**

```
Here are the matching tasks:
1.[T][] write reflection
```

### Feature - update tasks
#### update
update feature is used to edit any task in the task list using its index number, the new task type and the new description. 

**Usage** - `update [index number] /to [taskType] [task description]`

**For example:**

`update 1 /to deadline finish project /by 25-09-2023 23:59`

**Expected Output:**

```
Got it! I've updated the task 1 to
[D][] finish project (by: 25 Sep 2023 23:59)
```

### Feature - save and exit the task manager
#### bye

bye feature will save all the changes made to task list from the start of the application and it closes the application after 2 seconds.

**Usage** - `bye`

**For example:**

`bye`

**Expected Output:**

```
Bye.Hope to see you again
```
> [!Note]
> Always close your application using the `bye` command to save your changes and exit the application.

> [!WARNING]
> Your changes will not be saved if you close the application manually.











