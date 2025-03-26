import java.util.LinkedList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {
    static List<Task> history = new LinkedList<>();

    @Override
    public void add(Task task) {
        if (history.size() >= 10) {
            history.removeLast();
        }
        history.addFirst(task);
    }

    @Override
    public void getHistory() {
        int count = 1;
        if (!history.isEmpty()) {
            System.out.println("История просмотров задач:");
            for (Task task : history) {
                if (task instanceof Epic) {
                    System.out.println(count + ". getEpic(" + task.id + ")");
                } else if (task instanceof Subtask) {
                    System.out.println(count + ". getSubTask(" + task.id + ")");
                } else {
                    System.out.println(count + ". getTask(" + task.id + ")");
                }
                count++;
            }
        } else {
            System.out.println("История просмотров пуста.");
        }
    }
}
