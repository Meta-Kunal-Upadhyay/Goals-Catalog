package VirtualCommandPrompt;

import java.util.Scanner;

public class CommandPrompt {
    private DirectoryManager manager;

    public CommandPrompt() {
        manager = new DirectoryManager();
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Virtual Command Prompt (VCP).");
        while (true) {
            String currentPath = manager.getCurrentDirectory().getName();
            System.out.print(currentPath + "> ");
            String commandInput = scanner.nextLine().trim();
            String[] args = commandInput.split("\\s+");

            if (args.length == 0) continue;

            String command = args[0];
            if ("mkdir".equals(command) && args.length == 2) {
                System.out.println(manager.mkdir(args[1]));
            } else if ("cd".equals(command) && args.length == 2) {
                System.out.println(manager.cd(args[1]));
            } else if ("bk".equals(command) && args.length == 1) {
                System.out.println(manager.cd(".."));
            } else if ("ls".equals(command) && args.length == 1) {
                System.out.println(manager.list());
            } else if ("find".equals(command) && args.length == 2) {
                System.out.println(manager.find(args[1]));
            } else if ("tree".equals(command) && args.length == 1) {
                System.out.println(manager.tree());
            } else if ("exit".equals(command) && args.length == 1) {
                System.out.println("Exiting VCP.");
                break;
            } else {
                System.out.println("Error: Command not found or invalid arguments.");
            }
        }
        scanner.close();
    }


    public static void main(String[] args) {
        CommandPrompt prompt = new CommandPrompt();
        prompt.start();
    }    

}
