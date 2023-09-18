# User Guide
RatSpeak

## Features 

### list
Show the list of tasks that have been added to the chatbot\
Format: ```list```\
Example: ```list```\
Expected output:
```
Sure, here are the list of tasks:
1: [T][ ] buy groceries
2: [T][ ] buy books
3: [D][ ] CS2103 Assignment (by: Sep 22 2023)
4: [E][ ] Green Month (from: Oct 9 2023 to: Nov 9 2023)

Here are the urgent task to be done within the week:
3: [D][ ] CS2103 Assignment (by: Sep 22 2023)
```

### todo
Add a todo task to the list\
Format: ```todo {task name}```\
Example: ```todo buy groceries```\
expected Output:
```
Got it. I've added this task:
[T][ ] buy groceries
Now you have 5 tasks in the list.
```

### deadline
Add a deadline task to the list\
Format: ```deadline {task name} /by {YYYY-MM-DD}```\
Example: ```deadline CS2103 Assignment /by 2023-10-10```\
expected output:
```
Got it. I've added this task:
[D][ ] CS2103 Assignment (by: Oct 10 2023)
Now you have 6 tasks in the list.
```

### event
Add an event task to the list\
Format:```event {task name} /from {YYYY-MM-DD} /to {YYYY-MM-DD}```\
Example: ```event Green Month /from 2023-10-09 /to 2023-11-09```\
Expected output:
```
Got it. I've added this task:
[E][ ] Green Month (from: Oct 9 2023 to: Nov 9 2023)
Now you have 7 tasks in the list.
```
### mark
mark a task by index in the list\
Format:```mark {task number}```\
Example: ```mark 1```\
Expected output:
```
Nice! I've marked this task as done:
[T][X] buy groceries
```
### unmark
unmark a task by index in the list\
Format:```unmark {task number}```\
Example: ```unmark 1```\
Expected output:
```
OK, I've marked this task as not done yet:
[T][ ] buy groceries
```

### delete
delete a task by index in the list\
Format:```delete {task number}```\
Example: ```delete 1```\
Expected output:
```
Noted. I've removed this task:
[T][ ] buy groceries
Now you have 6 tasks in the list.
```
### bye
exit the chatbot\
Format:```bye```\
Example: ```bye```\
Expected output: 
```
Bye. Hope to see you again soon!
```

### find
shows tasks whose name matched the input in the list\
Format:```find {substring}```\
Example: ```find CS2103```\
Expected output:
```
Here are the matching tasks in your list:
1. [D][ ] CS2103 Assignment (by: Sep 22 2023)
```
