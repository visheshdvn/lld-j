package groups;

import balanceSheet.BalanceSheetController;
import expense.Expense;
import expense.ExpenseController;
import expense.splits.policy.SplitPolicy;
import users.User;
import users.UserController;

import java.util.*;

public class Group {
    private String id;
    private String name;
    private User createdBy;

    List<User> members = new ArrayList<>();

//    Every group contains its own expense controller
//    the controller manages the expense collection
    private ExpenseController expenseController;

    private UserController userController;

    public Group(String name, User createdBy) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.createdBy = createdBy;

        this.expenseController = new ExpenseController();
        this.userController = UserController.getController();

        members = new ArrayList<>();
        members.add(createdBy);
    }

    /*
    operations
     */
    public void addUsers(List<User> users) {
        if(users == null) return;
        members.addAll(users);
    }

    public Expense addExpense(
            String description,
            Float amount,
            User paidBy,
            SplitPolicy policy,
            List<User> users
    ) {
        Expense exp = expenseController.createExpense(description, amount, paidBy);

        if (Objects.requireNonNull(policy) == SplitPolicy.EQUAL_SPLIT) {
            splitExpense(policy, exp.getExpenseId(), users);
        }

        BalanceSheetController.updateBalanceSheet(exp);
        return exp;
    }

    public void splitExpense(SplitPolicy policy, String expenseId, List<User> users) {
//        List<User> users = usersIds.stream()
//                .map(this::findUserFromMembers)
//                .flatMap(Optional::stream)
//                .toList();

        this.expenseController.splitExpense(
                policy,
                expenseId,
                users
                );
    }

    public void printExpenses() {
        List<Expense> allExpenses = expenseController.getAllExpenses();
        for (Expense exp : allExpenses) {
            IO.println(exp.toString());
        }
    }


    /*
    helpers
     */
    private Optional<User> findUserFromMembers(String userId) {
        return members.stream().filter(u -> u.getId().equalsIgnoreCase(userId)).findFirst();
    }

    /*
    getters & setters
     */


}
