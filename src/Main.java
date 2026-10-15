import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("=================================");
        System.out.println(" MONTHLY EXPENSE & BUDGET ANALYZER");
        System.out.println("=================================");

        System.out.print("Enter your monthly income: ");
        double income = sc.nextDouble();

        System.out.print("Enter your monthly budget: ");
        double budget = sc.nextDouble();

        System.out.print("Enter your total monthly expenses: ");
        double expenses = sc.nextDouble();

        double balance = budget - expenses;
        double savings = income - expenses;
double percentage = (expenses / budget) * 100;

        System.out.println("\n--------- SUMMARY ---------");
        System.out.println("Monthly Income  : Rs." + income);
        System.out.println("Monthly Budget  : Rs." + budget);
        System.out.println("Total Expenses  : Rs." + expenses);
        System.out.println("Remaining Budget: Rs." + balance);
        System.out.println("Savings         : Rs." + savings);
System.out.println("Budget Used     : " + percentage + "%");

        if (expenses > budget) {
            System.out.println("Status: Budget Exceeded!");
        } else {
            System.out.println("Status: Within Budget.");
        }
        if (percentage >= 80) {
    System.out.println("Warning: You are close to your budget limit.");
}

        sc.close();
    }
}
