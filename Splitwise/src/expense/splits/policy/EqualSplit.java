package expense.splits.policy;

import expense.Expense;
import expense.splits.Split;
import users.User;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class EqualSplit implements ExpenseSplitter {

    @Override
    public SplitPolicy getSupportedPolicy() {
        return SplitPolicy.EQUAL_SPLIT;
    }

    @Override
    public List<Split> split(List<User> splitBetween, Expense expense) {
        User spentBy = expense.getPaidBy();
        Float totalAmount = expense.getAmount();

        Float perUserShare = totalAmount / splitBetween.size();

        List<Split> splits = new ArrayList<>();

        splitBetween.forEach(user -> {
            if(!user.getId().equals(expense.getPaidBy().getId())) {
                splits.add(new Split(
                        UUID.randomUUID().toString(),
                        perUserShare,
                        spentBy,
                        user
                ));
            }
        });

        return splits;
    }
}
