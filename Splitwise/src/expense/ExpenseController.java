package expense;

import expense.splits.Split;
import expense.splits.policy.EqualSplit;
import expense.splits.policy.SplitPolicy;
import users.User;

import java.util.*;

public class ExpenseController {

    ArrayList<Expense> expenses;

    EqualSplit equalSplit;

    public ExpenseController() {
        this.equalSplit = new EqualSplit();
        expenses = new ArrayList<>();
    }

    /*
    create an expense
     */
    public Expense createExpense(String description, Float amount, User paidBy) {
        Expense expense = new Expense(
                UUID.randomUUID().toString(),
                description,
                amount,
                paidBy
        );

        this.expenses.add(expense);
        return expense;
    }

    /*
    split an expense
     */
    public Expense splitExpense(SplitPolicy policy, String expenseId, List<User> users) {

        Optional<Expense> expenseOptional = expenses.stream().filter(exp -> exp.getExpenseId().equals(expenseId)).findFirst();

        if(expenseOptional.isEmpty()) {
            return null;
        }

        Expense expense = expenseOptional.get();
        if (Objects.requireNonNull(policy) == SplitPolicy.EQUAL_SPLIT) {
            return equalSplitHandler(expense, users);
        }

        throw new IllegalCallerException("Use a valid Split policy");
    }


    private Expense equalSplitHandler(Expense expense, List<User> users) {
        List<Split> splits = equalSplit.split(users, expense);
        expense.setSplits(splits);

        return expense;
    }

    public List<Expense> getAllExpenses() {
        return this.expenses;
    }
}
