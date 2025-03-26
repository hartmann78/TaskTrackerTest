public class Managers {
    public static InMemoryTaskManager getDefault() {
        return new InMemoryTaskManager();
    }

    public static InMemoryTaskManager getSave() {
        return new FileBackedTasksManager("C:\\Users\\user\\Desktop\\Java\\Java\\TaskTracker\\src\\Tracker_v4\\save.csv");
    }
}