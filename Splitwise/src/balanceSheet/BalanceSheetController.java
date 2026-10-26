package balanceSheet;

import expense.Expense;
import expense.splits.Split;
import users.User;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class BalanceSheetController {
    public static void updateBalanceSheet(Expense expense) {
        User spentByUser = expense.getPaidBy();
        Float totalAmount = expense.getAmount();
        List<Split> splits = expense.getSplits();

        for (Split split: splits) {
            User owedByUser = split.getOwedTo();
            owedByUser.getBalanceSheet().addToBalanceSheet(spentByUser, new BalanceSheet.BalanceSheetRecord(split.getAmount(), null));
            spentByUser.getBalanceSheet().addToBalanceSheet(owedByUser, new BalanceSheet.BalanceSheetRecord(null, split.getAmount()));
        }
    }

    public static void printBalanceSheetOfUser(User user) {
        IO.println("=== Balance Sheet of user " + user.getName() + " ===");
        printBalanceSheet(user.getBalanceSheet());
        IO.println("=== Balance Sheet Ends ===\n\n");
    }

    public static void printBalanceSheet(BalanceSheet balanceSheet) {
        IO.println("Total owes = " + balanceSheet.getTotalOwes());
        IO.println("Total receives = " + balanceSheet.getTotalGet());

        IO.println("All user's khata :");

        Map<User, List<BalanceSheet.BalanceSheetRecord>> allRecords = balanceSheet.getRecordWithUser();

        for (var balanceEntry: allRecords.entrySet()) {
            User forUser = balanceEntry.getKey();
            List<BalanceSheet.BalanceSheetRecord> records = balanceEntry.getValue();

            IO.println("With User: " + forUser.getName());

            double totalOwes = records.stream()
                    .map(BalanceSheet.BalanceSheetRecord::getOwe)
                    .filter(Objects::nonNull)
                    .mapToDouble(Float::doubleValue)
                    .sum();


            double totalGets = records.stream()
                    .map(BalanceSheet.BalanceSheetRecord::getReceive)
                    .filter(Objects::nonNull)
                    .mapToDouble(Float::doubleValue)
                    .sum();

            IO.println("Total owed: " + totalOwes);
            IO.println("Total gets: " + totalGets + "\n");
        }
    }
}
