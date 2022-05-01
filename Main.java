package homework14;

import homework14.service.Manager;

public class Main {

    public static void main(String[] args) {
        Manager manager = new Manager();

        String task = args[0];
        String method = args[1];
        Long id = Long.parseLong(args[2]);
        manager.findWebClientTask(task, method, id);
    }
}
