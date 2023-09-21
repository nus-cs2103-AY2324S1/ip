# Pardiyem Chatbot

Meet Pardiyem, an Italian-English semi-bilingual chatbot raised in the great island of Java.
It is made to help you organise your tasks. 

Pardiyem supports 3 types of entries: Todo, Deadline, Event. The `TASK_DESC` field for all entries are expected to not be empty. Arguments with date-time information are expected to be entered in either the `DD/MM/YYYY` or `DD/MM/YYYY HH:MM:SS` format.
A task can be represented with the following string:

```
[{TASK_TYPE}][{COMPLETION_STATUS}] TASK_DESC {EXTRA_DATETIME_DESC}
```

The `TASK_TYPE` field can have values of `D | T | E`. The `COMPLETION_STATUS` field will show either `X` if the task has been completed and an empty space otherwise.
`EXTRA_DATETIME_DESC` won't be shown if the entry is a Todo-type.

Pardiyem can:
## 1. List tasks in your task-list

To view all tasks currently in your task-list. Simply type:

```
list
```

No arguments are expected, so if you do type in an argument, Pardiyem will return an error.

### Example:

```
>>> list

1. [E][ ] Dinner with Lo (from: 21/09/2023 18:00:00 to: 21/09/2023 21:00:00)
2. [T][ ] Build the new Fokker model
3. [T][X] Get a new screwdriver
4. [D][ ] Prepare the UG presentation (by: 28/09/2023)
```

## 2. Add entries to your task-list

A Todo-typed entry simply represents a task that needs to be done, and therefore requires no extra temporal arguments. You add a Todo-typed entry by entering the following command:

```
todo TASK_DESC
```

A Deadline-typed entry represents a task that has to be done before a certain deadline, and therefore must include an extra temporal argument.
A Deadline-typed entry can be added with the following command:

```
deadline TASK_DESC /by DEADLINE
```
Since `DEADLINE` is a date-time argument, it is expected to follow the format mentioned earlier.

Finally, an Event-typed entry represents a task that has a starting and ending time. An Event-typed entry can be added with the following command:

```
deadline TASK_DESC /from START_TIME /to END_TIME 
```
Again, since `START_TIME` and `END_TIME` are date-time arguments, they must follow the given format.

If the command works, you can expect to see something like the following returns:
```
Got it. I've added this task:
{TASK_STRING}
Now you have {TASKLIST_LENGTH} task(s) in the list
```
Otherwise, the chatbot will simply print an error message corresponding to what type of error was detected. 

### Example:
```
>>> deadline final paper submission /by 23-02-2015

Got it. I've added this task:
[D][ ] final paper submission (by: 23-02-2015)
Now you have 1 task(s) in the list
```
```
>>> deadline final paper submission /by 02-23-2015

Please input your time in the format of either "YYYY-MM-DD" or "YYYY-MM-DD HH:MM:SS"
```

## 3. Delete entries from the task-list

To delete an item from the task-list, simply use:

```
delete TASK_INDEX
```

`TASK_INDEX` is expected to be a numerical index within the bounds of your tasklist. Otherwise, Pardiyem will throw an exception.

### Example: 

```
>>> list

1. [E][ ] Dinner with Lo (from: 21/09/2023 18:00:00 to: 21/09/2023 21:00:00)
2. [T][ ] Build the new Fokker model
3. [T][X] Get a new screwdriver
4. [D][ ] Prepare the UG presentation (by: 28/09/2023)

>>> delete 3

Noted. I've removed this task:
[T][X] Get a new screwdriver
Now you have 3 task(s) on the list

>>> list

1. [E][ ] Dinner with Lo (from: 21/09/2023 18:00:00 to: 21/09/2023 21:00:00)
2. [T][ ] Build the new Fokker model
3. [D][ ] Prepare the UG presentation (by: 28/09/2023)

>>> delete 4

Whoops, that number is not an index in the list. Please select a valid index

>>> delete aaaa

Whoops, you need to type in a valid integer
```

## 4. Mark/Unmark tasks as done

To mark a task as done, simply use:

```
mark TASK_INDEX
```

To unmark a task, use:

```
unmark TASK_INDEX
```

Again, `TASK_INDEX` is expected to be a numerical index within the bounds of your tasklist. Otherwise, Pardiyem will throw an exception.

### Example:

```
>>> list

1. [E][ ] Dinner with Lo (from: 21/09/2023 18:00:00 to: 21/09/2023 21:00:00)
2. [T][ ] Build the new Fokker model
3. [T][X] Get a new screwdriver
4. [D][ ] Prepare the UG presentation (by: 28/09/2023)

>>> mark 3

Hey, just letting you know that the task has already been done previously :)

>>> list 

1. [E][ ] Dinner with Lo (from: 21/09/2023 18:00:00 to: 21/09/2023 21:00:00)
2. [T][ ] Build the new Fokker model
3. [T][X] Get a new screwdriver
4. [D][ ] Prepare the UG presentation (by: 28/09/2023)

>>> mark 4

Bellisimo! I've marked this task as done!

>>> list

1. [E][ ] Dinner with Lo (from: 21/09/2023 18:00:00 to: 21/09/2023 21:00:00)
2. [T][ ] Build the new Fokker model
3. [T][X] Get a new screwdriver
4. [D][X] Prepare the UG presentation (by: 28/09/2023)

>>> unmark 3

Va bene, I've marked this task as undone
```

## 5. Search for entries in your task-list

To find entries in your task-list that matches a certain keyword, simply use:

```
find KEYWORD
```

Any task with description that contains `KEYWORD` will be listed and returned.

### Example:

```
>>> list

1. [E][ ] Dinner with Lo (from: 21/09/2023 18:00:00 to: 21/09/2023 21:00:00)
2. [T][ ] Build the new Fokker model
3. [T][X] Get a new screwdriver
4. [D][X] Prepare the UG presentation (by: 28/09/2023)
5. [T][ ] Display the Fokker model

>>>  find Fokker

1. [T][ ] Build the new Fokker model
2. [T][ ] Display the Fokker model
```