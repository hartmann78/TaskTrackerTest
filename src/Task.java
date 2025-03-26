public class Task {
    protected int id;
    protected String name;
    protected String description;
    protected Status status;

    public Task(int id, String name, String description, Status status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setStatus(Status status, String taskType) {
        this.status = status;
        System.out.println("Cтатуc " + taskType + " обновлён!");
    }
}
