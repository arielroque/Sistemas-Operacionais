package br.edu.ufcg.so.jantar;
import br.edu.ufcg.so.jantar.models.Dinner;
import br.edu.ufcg.so.jantar.models.Philosopher;
import br.edu.ufcg.so.jantar.solutions.monitor.MonitorSolution;
import br.edu.ufcg.so.jantar.solutions.semaphore.SemaphoreSolution;

import java.util.Scanner;

public class Main {

    private static final int PHILOSOPHERS_NUMBER = 7;
    private static final int PHILOSOPHERS_EATING_DURATION = 1000;
    private static final int PHILOSOPHERS_THINKING_DURATION = 1000;

    public static void main(String[] args) {

        Dinner dinner;

        System.out.println("========= SOLUTIONS ==========");
        System.out.println("1 - Semaphore");
        System.out.println("2 - Monitor");
        System.out.println("Press a number to continue:");

        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();
        scanner.close();

        switch (option) {
            case 1:
                dinner = new SemaphoreSolution(PHILOSOPHERS_NUMBER);

                for (int i = 0; i < PHILOSOPHERS_NUMBER; i++) {
                    new Philosopher(i, PHILOSOPHERS_EATING_DURATION, PHILOSOPHERS_THINKING_DURATION, dinner);
                }

                break;

            case 2:
                dinner = new MonitorSolution(PHILOSOPHERS_NUMBER);

                for (int i = 0; i < PHILOSOPHERS_NUMBER; i++) {
                    new Philosopher(i, PHILOSOPHERS_EATING_DURATION, PHILOSOPHERS_THINKING_DURATION, dinner);
                }

                break;

            default:
                System.out.println("Option not found :( .  Try again.");
                System.exit(1);
        }
    }
}
