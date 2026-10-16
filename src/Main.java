import java.util.*;
import java.io.*;
import java.util.stream.*;

class Expense { // CO4: Class and encapsulation
private String category;
private double amount;

Expense(String category, double amount) {
this.category = category;
this.amount = amount;
}

public String getCategory() { return category; }
public double getAmount() { return amount; }
}

public class Main {
static double total(ArrayList<Expense> list) { // CO3: Method
double sum = 0;
for (Expense e : list)
sum += e.getAmount();
return sum;
}

public static void main(String[] args) {
Scanner sc = new Scanner(System.in); // CO1: Input
ArrayList<Expense> expenses = new ArrayList<>(); // CO6
  System.out.print("Monthly income: ");
double income = sc.nextDouble();
System.out.print("Monthly budget: ");
double budget = sc.nextDouble();

// CO2: Loop and conditional statements
System.out.print("Number of expenses: ");
int n = sc.nextInt();
sc.nextLine();

for (int i = 0; i < n; i++) {
System.out.print("Category and amount: ");
String category = sc.next (http://sc.next/)();
double amount = sc.nextDouble();

if (amount > 0)
expenses.add(new Expense(category, amount));
else
System.out.println("Invalid amount");
}

double spent = total(expenses);
double savings = income - spent;

// CO6: Stream analysis
expenses.stream (http://expenses.stream/)()
.collect(Collectors.groupingBy(
Expense::getCategory,
Collectors.summingDouble(Expense::getAmount)))
.forEach((c, a) -> System.out.println(c + ": " + a));

System.out.println("Total: " + spent);
System.out.println("Savings: " + savings);
System.out.println(spent > budget
? "Budget Exceeded" : "Within Budget");

// CO5: File handling
try (PrintWriter out = new PrintWriter("report.txt")) {
out.println("Income: " + income);
out.println("Expenses: " + spent);
out.println("Savings: " + savings);
} catch (IOException e) {
System.out.println("File error");
}
sc.close();
}
}
