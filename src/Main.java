import java.util.*;
import java.io.*;
import java.util.stream.*;

// CO4: Class and encapsulation
class Expense {
    private String category;
    private double amount;

    Expense(String category, double amount) {
        this.category = category;
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public double getAmount() {
        return amount;
    }
}

public class Main{
    static Scanner sc = new Scanner(System.in);
    static ArrayList<Expense> expenses = new ArrayList<>();
    static double income, budget;

    public static void main(String[] args) {
        // CO1: Input and variables
        income = readAmount("Enter monthly income: ");
        budget = readAmount("Enter monthly budget: ");

        int choice;
        do {
            System.out.println("\n1.Add Expense");
            System.out.println("2.View Summary");
            System.out.println("3.Category Summary");
            System.out.println("4.Save Report");
            System.out.println("0.Exit");
            System.out.print("Enter choice: ");

            while (!sc.hasNextInt()) {
                System.out.print("Enter a valid choice: ");
                sc.next ();
            }
            choice = sc.nextInt();

            // CO2: Switch and decision-making
            switch (choice) {
                case 1:
                    addExpense();
                    break;
                case 2:
                    showSummary();
                    break;
                case 3:
                    showCategories();
                    break;
                case 4:
                    saveReport();
                    break;
                case 0:
                    System.out.println("Thank you!");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 0);

        sc.close();
    }

    static double readAmount(String message) {
        while (true) {
            System.out.print(message);
            if (sc.hasNextDouble()) {
                double value = sc.nextDouble();
                if (Double.isFinite(value) && value >= 0)
                    return value;
            } else {
                sc.next();
            }
            System.out.println("Enter a valid non-negative amount.");
        }
    }

    // CO3: Reusable method
    static void addExpense() {
        sc.nextLine();
        System.out.print("Enter category: ");
        String category = sc.nextLine().trim();

        if (category.isEmpty()) {
            System.out.println("Category cannot be empty.");
            return;
        }

        double amount = readAmount("Enter amount: ");
        if (amount <= 0) {
            System.out.println("Amount must be greater than zero.");
            return;
        }

        expenses.add(new Expense(category, amount)); // CO6
        System.out.println("Expense added successfully.");
    }

    static double calculateTotal() {
        double total = 0;
        for (Expense e : expenses)
            total += e.getAmount();
        return total;
    }

    static void showSummary() {
        double total = calculateTotal();
        System.out.printf("Income: Rs.%.2f%n", income);
        System.out.printf("Budget: Rs.%.2f%n", budget);
        System.out.printf("Expenses: Rs.%.2f%n", total);
        System.out.printf("Savings: Rs.%.2f%n", income - total);
        System.out.printf("Remaining budget: Rs.%.2f%n", budget - total);

        // CO2: Conditional statements
        if (total > budget)
            System.out.println("Status: Budget Exceeded");
        else
            System.out.println("Status: Within Budget");
    }

    // CO6: Streams and collections
    static void showCategories() {
        Map<String, Double> totals = expenses.stream()
            .collect(Collectors.groupingBy(
                Expense::getCategory,
                TreeMap::new,
                Collectors.summingDouble(Expense::getAmount)
            ));

        totals.forEach((category, amount) ->
            System.out.println(category + ": Rs." + amount));
    }

    // CO5: File handling
    static void saveReport() {
        try (PrintWriter out =
                 new PrintWriter(new FileWriter("expense_report.txt"))) {
            out.println("MONTHLY EXPENSE REPORT");
            out.println("Income: " + income);
            out.println("Budget: " + budget);
            out.println("Expenses: " + calculateTotal());
            out.println("Savings: " + (income - calculateTotal()));

            for (Expense e : expenses) {
                out.println(e.getCategory() + ": " + e.getAmount());
            }
            System.out.println("Report saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving report.");
        }
    }
}
