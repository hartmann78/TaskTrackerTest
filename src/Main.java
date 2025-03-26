import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Manager manager = new Manager();
        System.out.println("Трекер задач");

        while (true) {
            manager.epicStatusCheck();
            manager.showFunctions();
            String command = scanner.next();
            switch (command) {
                case ("1"):
                    manager.showTasks();
                    break;
                case ("2"):
                    manager.removeAll();
                    break;
                case ("3"):
                    manager.getById();
                    break;
                case ("4"):
                    manager.createTask();
                    break;
                case ("5"):
                    manager.updateStatus();
                    break;
                case ("6"):
                    manager.deleteTask();
                    break;
                case ("7"):
                    System.out.println("Завершение...");
                    return;
                default:
                    System.out.println("Такая команда отсутствует!");
                    break;
            }
        }
    }
}
