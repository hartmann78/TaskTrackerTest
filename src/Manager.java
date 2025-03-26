import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Manager {
    int id = 1;
    int subTaskId = 1;
    Scanner scanner = new Scanner(System.in);
    HashMap<Integer, Task> taskList = new HashMap<>();

    public void epicStatusCheck() {
        for (Task checkEpic : taskList.values()) {
            if (checkEpic instanceof Epic updateEpic) {
                for (Subtask subtask : updateEpic.subtasks.values()) {
                    if (subtask.status != Status.DONE) {
                        return;
                    }
                }
                checkEpic.setStatus(Status.DONE);
                System.out.println("Эпик " + updateEpic.name + " переведён в статус DONE.");
                return;
            }
        }
    }

    public void showFunctions() {
        System.out.println("Выберите функцию:");
        System.out.println("1 - получение списка задач");
        System.out.println("2 - удаление всех задач");
        System.out.println("3 - получение по индентификатору");
        System.out.println("4 - создание задач");
        System.out.println("5 - обновление статуса задачи");
        System.out.println("6 - удаление задач по индентификатору");
        System.out.println("7 - завершить работу");
    }

    // Function 1
    public void showTasks() {
        if (isEmptyCheck()) return;
        for (Task showTask : taskList.values()) {
            System.out.println("Название: " + showTask.name);
            System.out.println("Описание: " + showTask.description);
            System.out.println("Статус: " + showTask.status);
        }
    }

    // Function 2
    public void removeAll() {
        if (isEmptyCheck()) return;
        taskList.clear();
        System.out.println("Все задачи удалены!");
    }

    // Function 3
    public void getById() {
        if (isEmptyCheck()) return;
        while (true) {
            System.out.println("Введите индентификатор:");
            int id = scanner.nextInt();
            if (taskList.containsKey(id)) {
                System.out.println("Название: " + taskList.get(id).name);
                System.out.println("Описание: " + taskList.get(id).description);
                System.out.println("Статус: " + taskList.get(id).status);

                if (taskList.get(id) instanceof Epic epic) {
                    System.out.println("Желаете ли вы получить список подзадач эпика?");
                    System.out.println("1 - да. 2 - нет");
                    String subtaskList = scanner.next();

                    if (subtaskList.equals("1")) {
                        for (Map.Entry<Integer, Subtask> subtask : epic.subtasks.entrySet()) {
                            System.out.println("Подзадача " + subtask.getKey());
                            System.out.println("Название: " + subtask.getValue().name);
                            System.out.println("Описание: " + subtask.getValue().description);
                            System.out.println("Статус: " + subtask.getValue().status);
                        }
                    }
                }
                return;
            } else if (id == 0) {
                System.out.println("Выход");
                return;
            } else {
                System.out.println("Неверный идентификатор!");
            }
        }
    }

    // Function 4
    public void createTask() {
        while (true) {
            System.out.println("Что вы хотите создать?");
            System.out.println("1 - задача");
            System.out.println("2 - эпик");
            String command = scanner.nextLine();
            switch (command) {
                case ("1"):
                    System.out.println("Введите название задачи:");
                    String taskName = scanner.nextLine();
                    System.out.println("Введите описание задачи:");
                    String taskDescription = scanner.nextLine();
                    taskList.put(id++, new Task(taskName, taskDescription, Status.NEW));
                    return;
                case ("2"):
                    System.out.println("Введите название эпика:");
                    String epicName = scanner.nextLine();
                    System.out.println("Введите описание эпика:");
                    String epicDescription = scanner.nextLine();
                    Epic newEpic = new Epic(epicName, epicDescription, Status.NEW);

                    System.out.println("Сколько подзадач вы желаете добавить?");
                    int subtasks = scanner.nextInt();
                    for (int i = 0; i < subtasks; i++) {
                        System.out.println("Подзадача " + (i + 1));
                        System.out.println("Введите название подзадачи:");
                        String subtaskName = scanner.nextLine();
                        System.out.println("Введите описание подзадачи:");
                        String subtaskDescription = scanner.nextLine();
                        newEpic.subtasks.put(subTaskId++, new Subtask(subtaskName, subtaskDescription, Status.NEW));
                    }
                    subTaskId = 1;
                    taskList.put(id++, newEpic);
                    return;
                case ("0"):
                    System.out.println("Выход");
                    return;
                default:
                    System.out.println("Неверная команда");
            }
        }
    }

    // Function 5
    public void updateStatus() {
        if (isEmptyCheck()) return;
        while (true) {
            System.out.println("Введите индентификатор задачи:");
            int selectId = scanner.nextInt();
            if (taskList.containsKey(selectId)) {
                if (taskList.get(selectId) instanceof Epic selectSubtask) {
                    System.out.println("Список подзадач:");
                    for (Integer key : selectSubtask.subtasks.keySet()) {
                        System.out.println("id: " + key + " - " + selectSubtask.subtasks.get(key).name);
                    }
                    System.out.println("Выберите подзадачу:");
                    int subtaskId = scanner.nextInt();
                    setNewStatus(selectSubtask.subtasks.get(subtaskId));
                } else {
                    setNewStatus(taskList.get(selectId));
                }
                return;
            } else if (selectId == 0) {
                System.out.println("Выход");
                return;
            } else {
                System.out.println("Неверный идентификатор!");
            }
        }
    }

    //дополнение к updateStatus()
    public void setNewStatus(Task updateId) {
        while (true) {
            System.out.println("Введите новый статус:");
            System.out.println("1 - Новый");
            System.out.println("2 - В процессе");
            System.out.println("3 - Выполнен");
            String newStatus = scanner.nextLine(); // Во время работы компилятор почему-то игноирует её
            newStatus = scanner.nextLine();
            switch (newStatus) {
                case ("1"):
                    updateId.setStatus(Status.NEW);
                    return;
                case ("2"):
                    updateId.setStatus(Status.IN_PROGRESS);
                    return;
                case ("3"):
                    updateId.setStatus(Status.DONE);
                    return;
                case ("0"):
                    System.out.println("Выход");
                    return;
                default:
                    System.out.println("Неверный номер!");
                    break;
            }
        }
    }

    // Function 6
    public void deleteTask() {
        if (isEmptyCheck()) return;
        while (true) {
            System.out.println("Введите идентификатор который желаете удалить: ");
            int deleteId = scanner.nextInt();
            if (taskList.containsKey(deleteId)) {
                taskList.remove(deleteId);
                System.out.println("Удаление успешно!");
                return;
            } else if (deleteId == 0) {
                System.out.println("Выход");
                return;
            } else {
                System.out.println("Неверный идентификатор!");
            }
        }
    }

    public Boolean isEmptyCheck() {
        if (taskList.isEmpty()) {
            System.out.println("Список задач пуст!");
            return true;
        }
        return false;
    }
}