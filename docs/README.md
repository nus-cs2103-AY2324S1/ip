# KimDuke User Guide

## Features

### Creating, Listing and Deleting Tasks
Variety of tasks like todo, event, deadline are supported

### DateTime Supported
Accepts DateTime Format, Convert into Readable Format.

### Tag Supports
Supports to add or remove tag to the tasks for categorising purpose




## Usage


### 1. `todo` - Create ToDo Task
Register new toDo task into current database.

Usage format:

`todo [task]`

Example Usage :

`todo Study CS2103T Chapter 2`

Expected outcome:

* Successful

```
Got it. I've added this task:
[T][] Study CS2103T Chapter 2
Now you have 2 tasks in the list."
```
* Unsuccessful :

```
OOPS!!! The description of a ToDo cannot be empty.
```
### 2. `deadline` - Create Deadline Task
Register new Deadline task with deadline  into current database.

Usage format:

`deadline [task] /by [dd/mm/yyyy HHmm]`

Example Usage :

`deadline CS2103T iP deadline /by 20/09/2023 2359`

Expected outcome:

* Successful

```
Got it. I've added this task:
[D][] CS2103 iP deadline (by: 13:00 Sep 21 2023)
Now you have 2 tasks in the list."
```
* Unsuccessful :

```
OOPS!!! The argument for the deadline is invalid!
Please use this format instead : deadline [task] /by [dd/mm/yyyy HHmm]
```

### 3. `event` - Create Event Task
Register new Event task with fromDate and toDate into current database.

Usage format:

`event [task] /from [dd/mm/yyyy HHmm] /to [dd/mm/yyyy HHmm]`

Example Usage :

`event CS2102 Exam /from 21/09/2023 1300 /to 21/09/2023 1500`

Expected outcome:

* Successful

```
Got it. I've added this task:
[E][] CS2102 Exam (from: 13:00 Sep 21 2023 to: 15:00 Sep 21 2023)
Now you have 2 tasks in the list."
```
* Unsuccessful :

```
OOPS!!! The argument for the event is invalid! 
Please use this format instead : event [task] /from [dd/mm/yyyy HHmm] /to [dd/mm/yyyy HHmm]
```

### 4. `list` - List Tasks
List all existing tasks in the current database.

Usage format:

`list`

Example Usage :

`list`

Expected outcome:

* Successful

```
Here is your upcoming tasks (total : 2):
1. [T][] CS2101 Chapter 2
2. [T][] CS2103 Chapter 2
```

### 5. `remove` - Remove Tasks
Remove specific tasks according to the index (start from 1 based on list) in the current database.

Usage format:

`remove [index]`


Example Usage :

`remove 1`

Expected outcome:

* Successful

```
Noted. I've removed this task:
[T][] CS2101 Chapter 2
Now you have 1 tasks in the list.
```

* Unsuccessful
```
OOPS!!! Invalid Index!
```
### 6. `find` - Find Tasks
Find tasks according to the keyword (contains) in the current database.

Usage format:

`find [keyword]`


Example Usage :

`find Chapter`

Expected outcome:

* Successful

```
Here are the matching tasks in your list:
1. [T][] CS2101 Chapter 2
2. [T][] CS2103 Chapter 2
```
### 7. `mark` - Mark Task as Done
Mark specific tasks according to the index (start from 1 based on list) in the current database.

Usage format:

`mark [index]`


Example Usage :

`mark 1`

Expected outcome:

* Successful

```
Nice! I've marked this task as done:
[T][/] CS2101 Chapter 2

```

* Unsuccessful
```
OOPS!!! Invalid Index!
```

### 8. `unmark` - Mark Task as Done
Mark specific tasks according to the index (start from 1 based on list) in the current database.

Usage format:

`unmark [index]`


Example Usage :

`unmark 1`

Expected outcome:

* Successful

```
OK, I've marked this task as not done yet:
[T][] CS2101 Chapter 2
```

* Unsuccessful
```
OOPS!!! Invalid Index!
```

### 9. `addTag` - Add Tag to Task
Add Tag to Task according to the index (start from 1 based on list) in the current database.

Usage format:

`addTag [index] [tagName]`


Example Usage :

`addTag 1 study`

Expected outcome:

* Successful

```
Tag study has been setup on CS2101 Chapter 2!
[T][]#study CS2101 Chapter 2
```

* Unsuccessful
```
OOPS!!! The argument for the addTag is invalid! \n Please use this format instead : addTag [taskIndex] [tagName]
```

### 10. `removeTag` - Remove Tag from Task
Remove Tag from Task according to the index (start from 1 based on list) in the current database.

Usage format:

`removeTag [index]`


Example Usage :

`removeTag 1`

Expected outcome:

* Successful

```
Tag is successfully being removed
```
* Unsuccessful

```
OOPS!!! The argument for the removeTag is invalid!
Please use this format instead : removeTag [taskIndex] [tagName]
```

### 11. `listTag` - Remove Tag to Task
List Tag based on tagName in the current database.

Usage format:

`listTag [tagName]`


Example Usage :

`listTag study`

Expected outcome:

* Successful

```
Here are the matching tasks according to your tag ( study ) in your list:
1. [T][]#study CS2101 Chapter 2
2. [T][]#study CS2103 Chapter 2
```
* Unsuccessful

```
OOPS!!! The argument for the listTag is invalid or your tag is invalid! 
Please use this format instead : listTag [tagName]"
```

### 12. `save` - Save Tasks in File Database
Update and Save current database into the file database.

Usage format:

`save`


Example Usage :

`save`

Expected outcome:

* Successful

```
Task Data is successfully saved into File Database
```

### 13. `clear` - Clear Tasks in File Database
Clear tasks into the file database.

Usage format:

`clear`


Example Usage :

`clear`

Expected outcome:

* Successful

```
All Tasks is successfully cleared, use command "save" to update changes in File Database
```

### 14. `help` - User Guide Website
Retrieve Helpful User Guide Website.

Usage format:

`help`

Example Usage :

`help`

Expected outcome:

* Successful

```
You can visit our product website :
https://github.com/kimshitong/ip
```
### 15. `bye` - Save and Close Application
Save and Close Down Application

Usage format:

`bye`

Example Usage :

`bye`

Expected outcome:

* Successful

```
All Tasks is successfully cleared, use command "save" to update changes in File Database
```
