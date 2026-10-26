package expense;

import expense.splits.Split;
import users.User;

import java.util.List;

public class Expense {
    String expenseId;
    String expenseDescription;
    Float amount;
    User paidBy;

    List<Split> splits;

    public Expense(String expenseId, String expenseDescription, Float amount, User paidBy) {
        this.expenseId = expenseId;
        this.expenseDescription = expenseDescription;
        this.amount = amount;
        this.paidBy = paidBy;
    }

    /*
    getters and setters
     */

    public Float getAmount() {
        return amount;
    }

    public String getExpenseId() {
        return expenseId;
    }

    public String getExpenseDescription() {
        return expenseDescription;
    }

    public User getPaidBy() {
        return paidBy;
    }

    public List<Split> getSplits() {
        return splits;
    }

    public void setSplits(List<Split> splits) {
        this.splits = splits;
    }

    /*

     */

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("Expense id: " + this.expenseId + " total: " + this.amount + "\n");
        for (Split split: this.splits) {
            s.append(split.toString() + "\n");
        }

        return s.toString();
    }
}