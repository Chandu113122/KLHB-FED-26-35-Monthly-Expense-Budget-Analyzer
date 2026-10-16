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
