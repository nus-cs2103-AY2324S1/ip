# BiuBiu

> meooow :eyes: :heart_on_fire:

### BiuBiu is here to help you remember your schedule! It's:
 - text-based
 - easy to learn
 - ~~fast~~ **SUPER SUPER FAST** to use

### Features:

- [x] Memorise tasks
- [x] Update tasks
- [ ] Reminders (coming soon!)

### `Keyword` Reference:

- `todo <task_descr>`: add a Todo task
    
    eg: `todo get a cup of coffee`
- `deadline <task_descr> /by <time>`: add a Deadline task(time format eg: 2023-09-21)

    eg: `deadline get a haircut /by 2023-09-22`
- `event <task_descr> /from <start_time /to <end_time>>`: add an Event task(time format eg: 2023-09-21 1400)

    eg: `event hang out with friends /from 2023-09-22 1400 /to 2023-09-22 2000`
- `mark <task_index>`: mark the (task_index)th task as done(task_index is in integer!)
 
    eg: `mark 1`
- `unmark <task_index>`: mark the (task_index)th task as not done yet
  
    eg: `unmark 1`
- `find <task_keyword>`: find and list all the tasks that contain the keyword

    eg: `find haircut`
- `delete <task_index>`: delete the (task_index)th task in the list
  
    eg: `delete 1`
- `update <task_index> </parts> </new_info>`: update the certain part of the (task_index)th task (/parts: /decsr, /time, /start_time, /end_time)

    eg: `update 3 /descr /hang out with doggy <3`
- `list`: list out all the tasks in your TaskList.

    eg: `list`

### All you need to do is,

1. download it from [here](https://github.com/Chen-Kuei/ip/tree/master).
2. double-click it.
3. add your tasks.
4. let it manage your tasks for u :kissing_cat:

*If you have the JAR file, you can simply type the command below at the folder containing the file:*
```ruby
java -jar BiuBiu.jar
```
