import java.util.List;

public interface HistoryManager {
    void addTaskToHistory(Task task);

    void removeTaskInHistory(int id);

    List<Task> getHistory();
}