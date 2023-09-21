# Buddy

Hi, I'm Buddy, excited to meet you! Keeping track of your tasks can be extremely tiring so fear not, because I'm here to save the day!

When you enter, you will be greeted by this message:

```
Hey there amigo, excited to meet you! I'm Buddy, your friendly chat companion! What can I do for you?
```

## Features 

I have the following features:

### `todo` - Add a new ToDo Task

If you have a task that you just have to do but there are no dates involved, just add a todo! 

Format:  `todo <Description>`

Example input:
```
todo homework
```

Expected outcome:
```
No problem! I have added this task:
[T][] homework
Now you have 2 tasks in the list.
```


### `deadline` - Add a new Deadline Task

If you have a task that you have to complete by a certain date, just add a deadline task!

Format:  `deadline <Description> /by <deadline in YYYY-MM-DD HHMM>`

Example input:
```
deadline homework /by 2023-08-08 1800
```
Expected outcome:
```
No problem! I have added this task:
[D][] homework (by: Aug 8 2023 6PM)
Now you have 3 tasks in the list.
```

### `event` - Add a new Event Task

If you have an event that takes place between certain dates and times, just add an event task!

Format:  `event <Description> /from <start time in YYYY-MM-DD HHMM> /to <end time in YYYY-MM-DD HHMM>`

Example input:
```
event homework /from 2023-08-08 1800 /to 2023-08-08 2000
```
Expected outcome:
```
No problem! I have added this task:
[E][] homework (from: Aug 8 2023 6PM to: Aug 8 2023 8PM)
Now you have 4 tasks in the list.
```

### `list` - View your list of Tasks

You're forgetful I know but don't worry because you can access the list of tasks you have inputted through this!

Format: `list`

Example input:
```
list
```
Expected outcome:
```
Here are the tasks in your list:
1. [T][] watch show
2. [T][] homework
3. [D][] homework (by: Aug 8 2023 6PM)
4. [E][] homework (from: Aug 8 2023 6PM to: Aug 8 2023 8PM)
```

### `delete` - Delete a Task from the list

If you would like to remove a task, just delete it! I won't mind I promise :)

Format: `delete <index of task in list>`

Example input:
```
delete 1
```

Expected outcome:
```
Okie I've removed this task:
[T][] watch show
Now you have 3 tasks in your list.
```

### `mark` - Mark a task as done

Yay, you're done with this task! Now you can mark it with a 'X'!

Format: `mark <index of task in list>`

Example input:
```
mark 1
```
Expected outcome:
```
Great! I've marked this task as done:
1.[T][X] homework
```

### `unmark` - Mark a task as not done

Maybe you thought you had completed a task but you had actually not. Fret not, I'll unmark it!

Format: `unmark <index of task in list>`

Example input:
```
unmark 1
```
Expected outcome:
```
Ok! I've marked this task as not done yet:
1.[T][] homework
```

### `find` - Find Tasks matching the keyword

Format: `find <keyword>`

Example input:
```
find work
```

Expected outcome:
```
Sure, I can do that! What are buddies for after all?
1. [T][] homework
2. [D][] homework (by: Aug 8 2023 6PM)
3. [E][] homework (from: Aug 8 2023 6PM to: Aug 8 2023 8PM)
```

### `massDelete` - Mass delete more than one Task from the list

Format: `massDelete <array of indexes each separated by a space>`

Example input:
```
massDelete 1 2
```
Expected outcome:
```
Okie I've removed these tasks!
```
Now the list will only have one task, which is the event task from earlier.

### `help` - Get the list of commands that exist

Format: `help`
Example input:
```
help
```

Expected outcome:
```
Sure buddy I'm always here for you! These are the commands I have and what they do!
1. todo: Type this to add a todo to your list!
2. deadline: Type this to add a deadline to your list! (ps remember to add when it is due by adding a /by)
3. event: Type this to add an event to your list! (ps remember to add when it starts and ends by adding a /from and /to)
4. list: Type this to view your tasks!
5. delete: Type this to delete a task. Add the task number as well!
6. mark: Type this to mark a task on the list. Add the task number as well!
6. unmark: Type this to unmark a task on the list. Add the task number as well!
7. find: Type this to find a task with the keyword you are searching for
8. massDelete: Type this to mass delete 2 or more tasks at once! eg: massDelete 1 2
9. bye: Type this and I will say goodbye :(
```

### `bye` - Say GoodBye to Buddy

Format: `bye`
Example input:
```
bye
```

Expected outcome:
```
Bye! Hope to see you again soon!
```
