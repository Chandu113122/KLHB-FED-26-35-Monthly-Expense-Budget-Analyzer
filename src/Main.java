
import java.util.*;
import java.io.*;
import java.util.stream.Collectors;

// CO4: Class and encapsulation
class Expense {
    private String category;
    private double amount;
    private String date;
    private String note;

    public Expense(String category, double amount, String date, String note) {
        this.category = category;
        this.amount = amount;
        this.date = date;
        this.note = note;
    }

    public String getCategory() {
        return category;
    }

    public double getAmount() {
        return amount;
    }

    public String getDate() {
        return date;
    }

    public String getNote() {
        return note;
    }

    public String toString() {
        return date + " | " + category + " | Rs." + amount + " | " + note;
    }
}

public class Main {
    static Scanner sc = new Scanner(System.in);
    static ArrayList<Expense> expenses = new ArrayList<>();

    static double income = 0;
    static double budget = 0;

    // Main program
    public static void main(String[] args) {
        System.out.println("==============================");
        System.out.println(" MONTHLY EXPENSE ANALYZER");
        System.out.println("==============================");

        readFinancialDetails();

        int choice;

        // CO2: Loop and switch
        do {
            showMenu();
            choice = readInt("Enter choice: ");

            switch (choice) {
                case 1:
                    addExpense();
                    break;

                case 2:
                    showExpenses();
                    break;

                case 3:
                    showSummary();
                    break;

                case 4:
                    categorySummary();
                    break;

                case 5:
                    saveReport();
                    break;

                case 6:
                    updateDetails();
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

    // CO1: Variables and input
    static void readFinancialDetails() {
        income = readNonNegative("Monthly income: ");
        budget = readNonNegative("Monthly budget: ");
    }

    static void showMenu() {
        System.out.println("\n-------- MENU --------");
        System.out.println("1. Add expense");
        System.out.println("2. View expenses");
        System.out.println("3. Monthly summary");
        System.out.println("4. Category summary");
        System.out.println("5. Save report");
        System.out.println("6. Update income/budget");
        System.out.println("0. Exit");
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

        double amount = readPositive("Enter amount: ");
        sc.nextLine();

        System.out.print("Enter date: ");
        String date = sc.nextLine().trim();

        if (date.isEmpty()) {
            date = "Not specified";
        }

        System.out.print("Enter note: ");
        String note = sc.nextLine().trim();

        if (note.isEmpty()) {
            note = "No note";
        }

        Expense e = new Expense(category, amount, date, note);
        expenses.add(e); // CO6: ArrayList

        System.out.println("Expense added successfully.");
    }

    // CO2: Conditions and loops
    static void showExpenses() {
        if (expenses.isEmpty()) {
            System.out.println("No expenses found.");
            return;
        }

        System.out.println("\n------ EXPENSE LIST ------");

        for (int i = 0; i < expenses.size(); i++) {
            System.out.println((i + 1) + ". " + expenses.get(i));
        }
    }

    // CO3: Method and calculations
    static double calculateTotal() {
        double total = 0;

        for (Expense e : expenses) {
            total += e.getAmount();
        }

        return total;
    }

    static void showSummary() {
        double total = calculateTotal();
        double savings = income - total;
        double remaining = budget - total;
        double percentage = 0;

        if (budget > 0) {
            percentage = total / budget * 100;
        }

        System.out.println("\n------ MONTHLY SUMMARY ------");
        System.out.printf("Income: Rs.%.2f%n", income);
        System.out.printf("Budget: Rs.%.2f%n", budget);
        System.out.printf("Expenses: Rs.%.2f%n", total);
        System.out.printf("Savings: Rs.%.2f%n", savings);
        System.out.printf("Remaining budget: Rs.%.2f%n", remaining);
        System.out.printf("Budget used: %.2f%%%n", percentage);

        // CO2: Decision making
        if (total > budget) {
            System.out.println("Status: Budget Exceeded");
        } else if (total == budget) {
            System.out.println("Status: Budget Fully Used");
        } else {
            System.out.println("Status: Within Budget");
        }

        if (savings < 0) {
            System.out.println("Warning: Expenses exceed income!");
        }
    }

    // CO6: Streams and category grouping
    static void categorySummary() {
        if (expenses.isEmpty()) {
            System.out.println("No expenses available.");
            return;
        }
        
    Map<String, Double> totals = expenses.stream (http://expenses.stream/)()
            .collect(Collectors.groupingBy(
                Expense::getCategory,
                TreeMap::new,
                Collectors.summingDouble(Expense::getAmount)
            ));

        System.out.println("\n------ CATEGORY SUMMARY ------");

        totals.forEach((category, amount) ->
            System.out.printf("%s: Rs.%.2f%n", category, amount));
    }

    // CO5: File handling
    static void saveReport() {
        double total = calculateTotal();

        try (PrintWriter writer =
                 new PrintWriter(new FileWriter("expense_report.txt"))) {

            writer.println("MONTHLY EXPENSE REPORT");
            writer.println("----------------------");
            writer.println("Income: " + income);
            writer.println("Budget: " + budget);
            writer.println("Expenses: " + total);
            writer.println("Savings: " + (income - total));
            writer.println("Remaining budget: " + (budget - total));

            writer.println("\nEXPENSE DETAILS");

            for (Expense e : expenses) {
                writer.println(e);
            }

            writer.println("\nCATEGORY SUMMARY");

            Map<String, Double> totals = expenses.stream (http://expenses.stream/)()
                .collect(Collectors.groupingBy(
                    Expense::getCategory,
                    TreeMap::new,
                    Collectors.summingDouble(Expense::getAmount)
                ));

            totals.forEach((category, amount) ->
                writer.println(category + ": Rs." + amount));

            System.out.println("Report saved successfully.");

        } catch (IOException e) {
            System.out.println("Error saving report.");
        }
    }

    // CO2: Update values using input validation
    static void updateDetails() {
        System.out.println("Update financial details");

        income = readNonNegative("New monthly income: ");
        budget = readNonNegative("New monthly budget: ");

        System.out.println("Details updated successfully.");
    }

    // CO1 and CO2: Input validation
    static double readNonNegative(String message) {
        while (true) {
            System.out.print(message);

            if (sc.hasNextDouble()) {
                double value = sc.nextDouble();

                if (Double.isFinite(value) && value >= 0) {
                    return value;
                }

                System.out.println("Enter a non-negative number.");
            } else {
                System.out.println("Invalid number.");
                sc.next (http://sc.next/)();
            }
        }
    }

    static double readPositive(String message) {
        while (true) {
            double value = readNonNegative(message);

            if (value > 0) {
                return value;
            }

            System.out.println("Amount must be greater than zero.");
        }
    }

    static int readInt(String message) {
        while (true) {
            System.out.print(message);

            if (sc.hasNextInt()) {
                return sc.nextInt();
            }

            System.out.println("Enter a valid whole number.");
            sc.next ();
        }
    }
}

      
