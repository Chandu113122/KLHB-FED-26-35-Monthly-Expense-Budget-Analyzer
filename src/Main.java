import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("====================================");
        System.out.println("MONTHLY EXPENSE & BUDGET ANALYZER");
        System.out.println("====================================");

        System.out.print("Enter your monthly income: ");
        double income = sc.nextDouble();

        System.out.print("Enter your monthly budget: ");
        double budget = sc.nextDouble();

        System.out.println("Monthly Income: Rs" + income);
        System.out.println("Monthly Budget: Rs" + budget);

        sc.close();
    }
}