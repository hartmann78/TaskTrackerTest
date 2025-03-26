import java.util.HashMap;

public class Epic extends Task {
    protected HashMap<Integer, Subtask> subtasks = new HashMap<>();

    public Epic(int id, String name, String description, Status status) {
        super(id, name, description, status);
    }
}
