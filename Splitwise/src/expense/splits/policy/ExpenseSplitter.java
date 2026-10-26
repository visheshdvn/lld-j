package expense.splits.policy;

import expense.Expense;
import expense.splits.Split;
import users.User;

import java.util.List;

public interface ExpenseSplitter {
    SplitPolicy getSupportedPolicy();

    List<Split> split(List<User> splitBetween, Expense expense);
}
