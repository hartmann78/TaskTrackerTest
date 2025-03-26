import java.util.HashMap;

public class Epic extends Task {
    protected HashMap<Integer, Subtask> subtasks = new HashMap<>();

    public Epic(String name, String description, Status status) {
        super(name, description, status);
    }
}
