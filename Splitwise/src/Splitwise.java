import balanceSheet.BalanceSheetController;
import expense.Expense;
import expense.splits.policy.SplitPolicy;
import groups.Group;
import users.User;
import users.UserController;

import java.util.List;

class Splitwise {
    public void demo() {
        UserController userController = UserController.getController();

        User user1 = userController.createUser("U001", "Amar");
        User user2 = userController.createUser("U002", "Akbar");
        User user3 = userController.createUser("U003", "Anthony");

        Group g1 = new Group("Travel", user1);
        g1.addUsers(List.of(user2, user3));

//        create a new expense
        Expense exp = g1.addExpense(
                "Apple Pie",
                300F,
                user2,
                SplitPolicy.EQUAL_SPLIT,
                List.of(user1, user2, user3));

//        g1.printExpenses();

        BalanceSheetController.printBalanceSheetOfUser(user1);
        BalanceSheetController.printBalanceSheetOfUser(user2);
        BalanceSheetController.printBalanceSheetOfUser(user3);
    }
}