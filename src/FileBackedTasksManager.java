import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileBackedTasksManager extends InMemoryTaskManager implements TaskManager {
    String filePath;
    InMemoryHistoryManager memoryHistoryManager = new InMemoryHistoryManager();

    enum Types {
        TASK,
        EPIC,
        SUBTASK
    }

    public FileBackedTasksManager(String filePath) {
        this.filePath = filePath;
    }

    public void save() throws IOException {
        try (Writer writer = new FileWriter(filePath)) {
            for (Task task : taskList.values()) {
                if (task instanceof Epic epic) {
                    writer.write(String.valueOf(epic.id) + ',' + Types.EPIC + ',' + epic);

                    for (Subtask subtask : epic.subtasks.values()) {
                        writer.write(String.valueOf(subtask.id) + ',' + Types.SUBTASK + ',' + subtask);
                    }

                } else {
                    writer.write(String.valueOf(task.id) + ',' + Types.TASK + ',' + task);
                }
            }
            writer.write('\n');

            if (memoryHistoryManager.getTasks() != null) {
                StringBuilder text = new StringBuilder();
                for (Task task : memoryHistoryManager.getTasks()) {
                    text.append(task.id).append(',');
                }
                writer.write(text.substring(0, text.length() - 1));
            }
        }
    }

//    static FileBackedTasksManager loadFromFile(File file) throws IOException {
//        String data =
//
//    }

    public String read() throws IOException {
        return Files.readString(Path.of(filePath));
    }

    // Override super functions //

    @Override
    public void deleteAll() {
        super.deleteAll();
        try {
            save();
        } catch (IOException e) {
            throw new ManagerSaveException(e);
        }
    }

    @Override
    public void getById() {
        super.getById();
        try {
            save();
        } catch (IOException e) {
            throw new ManagerSaveException(e);
        }
    }

    @Override
    public void create() {
        super.create();
        try {
            save();
        } catch (IOException e) {
            throw new ManagerSaveException(e);
        }
    }

    @Override
    public void updateTask() {
        super.updateTask();
        try {
            save();
        } catch (IOException e) {
            throw new ManagerSaveException(e);
        }
    }

    @Override
    public void updateStatus() {
        super.updateStatus();
        try {
            save();
        } catch (IOException e) {
            throw new ManagerSaveException(e);
        }
    }

    @Override
    public void deleteTask() {
        super.deleteTask();
        try {
            save();
        } catch (IOException e) {
            throw new ManagerSaveException(e);
        }
    }
}
