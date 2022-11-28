import DFA.AutomationImpl;

import java.util.Scanner;

public class Run {

    public static void main(String[] args) {
        AutomationImpl automation = new AutomationImpl();

        System.out.println("ababSbb".contains("S"));
        while (true) {
            Scanner in = new Scanner(System.in);
            System.out.println("1-Check String\n2-Generate String\n3-Exit");
            while (!in.hasNextInt()) {
                in.next();
                System.out.println("Incorrect data");
            }
            int cases = in.nextInt();
            switch (cases) {
                case 1 -> {
                    System.out.println("Input string for checking: ");
                    in.nextLine();
                    String input = in.nextLine();
                    automation.checkString(input);
                }
                case 2 -> {
                    System.out.println("Input quantity");
                    while (!in.hasNextInt()) {
                        in.next();
                        System.out.println("Incorrect data");
                    }
                    int quantity = in.nextInt();
                    System.out.println(automation.generateString(quantity));

                }
                case 3 -> {
                    System.exit(0);

                }
                default -> {
                    System.out.println("Incorrect data");

                }
            }
        }
    }
}


