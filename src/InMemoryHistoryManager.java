import java.util.*;

public class InMemoryHistoryManager implements HistoryManager {
    static HashMap<Integer, Node<Task>> history = new HashMap<>();

    public void clearHistory() {
        for (Node<Task> taskNode : history.values()) {
            removeNode(taskNode);
        }
        history.clear();
    }

    public void check() {
        Node<Task> check = checkSize();
        if (check != null) {
            history.remove(check.data.id);
            removeNode(check);
        }
    }

    @Override
    public void addTaskToHistory(Task task) {
        Node<Task> taskNode = linkLast(task);
        history.put(task.id, taskNode);
        check();
    }

    @Override
    public List<Task> getHistory() {
        return getTasks();
    }

    public void showHistory(List<Task> history) {
        if (history == null || history.isEmpty()) {
            System.out.println("История просмотров пуста.");
        } else {
            int count = 1;
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
        }
    }

    @Override
    public void removeTaskInHistory(int id) {
        Node<Task> taskNode = history.get(id);
        if (taskNode != null) {
            history.remove(id);
            removeNode(taskNode);
        }
    }

    //Linked list//

    static private Node<Task> head;
    static private Node<Task> tail;
    static private int size = 0;

    public Node<Task> linkLast(Task element) {
        Node<Task> iterator = head;
        while (iterator != null) {
            if (iterator.data == element) {
                removeNode(iterator);
                break;
            }
            iterator = iterator.next;
        }

        final Node<Task> oldTail = tail;
        final Node<Task> newNode = new Node<>(oldTail, element, null);
        tail = newNode;
        if (oldTail == null)
            head = newNode;
        else
            oldTail.next = newNode;
        size++;
        return newNode;
    }

    public Node<Task> checkSize() {
        if (getSize() > 10) {
            return head;
        }
        return null;
    }

    public ArrayList<Task> getTasks() {
        if (head == null && tail == null) return null;
        ArrayList<Task> tasks = new ArrayList<>();
        final Node<Task> oldTail = tail;
        while (tail != null) {
            tasks.add(tail.data);
            tail = tail.prev;
        }
        tail = oldTail;
        return tasks;
    }

    public int getSize() {
        return size;
    }

    public void removeNode(Node<Task> node) {
        final Node<Task> next = node.next;
        final Node<Task> prev = node.prev;

        if (prev == null) {
            head = next;
        } else {
            prev.next = next;
            node.prev = null;
        }

        if (next == null) {
            tail = prev;
        } else {
            next.prev = prev;
            node.next = null;
        }
        node.data = null;
        size--;
    }
}