# Alcazar project

This is Alcazar, your own personalised Chatbot ready to do your bidding.


## How do I use it?

### `todo` 
* Add a task that needs to be done without a deadline
* Can be used in the form `todo something todo`
* For example `todo return a book` to add the task to your task list.

### `deadline`
* Add a task to be completed by a given date.
* Can be used in the form `deadline topic of deadline /by yyyy-mm-dd`
* For example `deadline submit homework /by 2023-12-01`

### `event`
* Add a task that is going to occur in a specific duration
* Can be used in the form `event some event /from start time /to end time`
* For example `event go for a move /from Wed 2pm /to Wed 4pm`

### `mark`
* Mark a task as done
* Can be used in the form `mark task number`
* For example `mark 3` will mark the third task done

### `unmark`
* Mark a task as not done
* Can be used in the form `unmark task number`
* For example `unmark 3` will mark the third task not done

### `delete`
* Delete a task
* Can be used in the form `delete task number`
* For example `delete 3` will delete the third task

### `list`
* List all the tasks
* Can be used by the input `list`
  
### `find`
* Used to find all the tasks containing a keyword
* Can be used by `find keyword`
* For example `find books` will result in showing all the tasks containing the word books

### `data source`
* Change the data source of the tasks list storage file
* Can be used by `data source new file path`
* For example `data source ./src/main/java/data/tasks.txt` will change the data source to `./src/main/java/data/tasks.txt`

### `bye`
* Close Alcazar
* Can be used by entering `bye`
