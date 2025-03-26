import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        InMemoryTaskManager taskManager = Managers.getDefault();
        InMemoryHistoryManager historyManager = new InMemoryHistoryManager();

        while (true) {
            taskManager.epicStatusCheck();
            taskManager.showFunctions();
            String command = scanner.next();
            switch (command) {
                case "1":
                    taskManager.displayTasks();
                    break;
                case "2":
                    taskManager.deleteAll();
                    break;
                case "3":
                    taskManager.getById();
                    break;
                case "4":
                    taskManager.create();
                    break;
                case "5":
                    taskManager.updateTask();
                    break;
                case "6":
                    taskManager.updateStatus();
                    break;
                case "7":
                    taskManager.deleteTask();
                    break;
                case "8":
                    historyManager.getHistory();
                    break;
                case "9":
                    System.out.println("Завершение...");
                    return;
                default:
                    System.out.println("Такая команда отсутствует!");
                    break;
            }
        }
    }
}
