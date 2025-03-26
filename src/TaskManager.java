public interface TaskManager {
    void epicStatusCheck();

    void showFunctions();

    void displayTasks();

    void deleteAll();

    void getById();

    void create();

    Task taskCreator(int taskId, boolean newCheck);

    Epic epicCreator(int epicId, boolean newCheck);

    Subtask subTaskCreator(int subTaskId, int count, boolean newCheck);

    void updateTask();

    void updateStatus();

    void deleteTask();
}