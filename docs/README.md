### PAU CHATBOT USER GUIDE

PAU is a chatbot that helps users keeps track of their tasks, deadlines and events. 
---
##Qiuck Start

1. Ensure you have Java 11 or above installed
2. Get your latest PAU here
3. Open PAU in a Command Line Interface by running `java -jar pau.jar`

---
##Features

#Viewing help: `help`
A lits of commands to use PAU will be shown.

Format: `help`
Example of usage: `help`
Expected Outcome:
```java
1. To create a ToDo: todo [todo name]
2. To create a Deadline: deadline [deadline name] /by [deadline]
3. To create an Event: event [event name] /from [start time] /by [end time]
4. To mark a task as completed: mark [task number]
5. To unmark a task: unmark [task number]
6. To delete a task: delete [task number]
7. To find a task: find [task details]
8. To list all the tasks: list
9. To clear all your tasks: clear
10. To exit the chat: bye
```

#Add ToDo: `todo`
Adds a ToDo to the list of tasks.

Format: `todo [todo name]`
Example of usage: `todo make the bed`
Expected Outcome:
```java
todo added: 
[T][ ] make the bed

there are still 1 task(s) to complete
```

#Add Deadline: `deadline`
Adds a Deadline to the list of tasks.

Format: `deadline [deadline name] /by [deadline]`
Example of usage: 
1. `deadline submit essay /by Wednesday`
2. `deadline watch lecture /by 2023-12-25`
[!NOTE]
If the due date for the deadline is a Date, it must be in the "YYYY-MM-DD" format in the command

Expected Outcome:
1.
```java
deadline added: 
[D][ ] submit essay (by: Wednesday)

there are still 2 task(s) to complete
```
2.
```java
deadline added: 
[D][ ] watch lecture (by: Dec 25 2023)

there are still 3 task(s) to complete
```


#Add Event: `event`
Adds an Event to the list of tasks.

Format: `event [event name] /from [start time] /by [end time]`
Example of usage: `event jennie's birthday party /from Saturday 2pm /to 10pm`
Expected Outcome:
```java
event added: 
[E][ ] jennie's birthday party (from: Saturday 2pm to: 10pm)

there are still 4 task(s) to complete
```

#Check Tasks: `list`
Lists out all the tasks in the task list.

Format: `list`
Example of usage: `list`
Expected Outcome:
```java
sian you still have to complete these:

1. [T][ ] make the bed
2. [D][ ] submit essay (by: Wednesday)
3. [D][ ] watch lecture (by: Dec 25 2023)
4. [E][ ] jennie's birthday party (from: Saturday 2pm to: 10pm)
```

#Mark Task: `mark`
Marks a task as complete.

Format: `mark [task number]`
Example of usage: `mark 1`
Expected Outcome:
```java
good job, you've completed a task! You're so productive!🤩

[T][X] make the bed
```

#Unmark Task: `unmark`
Marks a task as incomplete.

Format: `unmark [task number]`
Example of usage: `unmark 1`
Expected Outcome:
```java
why are you not going to make the bed? remember to do it later!

[T][ ] make the bed
```

#Delete Task: `delete`
Deletes a task.

Format: `delete [task number]`
Example of usage: `delete 1`
Expected Outcome:
```java
not you running away from your responsibilities, i guess you don't have to do this now:

[T][ ] make the bed

but still sucks to be you, you still have 3 tasks
```
#Find Task: `find`
Finds a task.

Format: `find [task details]`
Example of usage: `find birthday`
Expected Outcome:
```java
Pau found these: 

1. [E][ ] jennie's birthday party (from: Saturday 2pm to: 10pm)
```

#Clear Task: `clear`
Clears all tasks in the list.

Format: `clear`
Example of usage: `clear`
Expected Outcome:
```java
you have somehow cleared all your tasks at once
```

[!WARNING]
This is IRREVERSIBLE! Do not use this command unless you are VERY sure you want to clear all tasks.

#Exit: `bye`
Exits the chatbot.

Format: `bye`
Example of usage: `bye`
Expected Outcome:
```java
byebyeee come play with me next time
```
