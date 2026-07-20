package amountWithdrawl;

import components.ATM;

public class OneHundredWithdrawlProcessor extends AmountWithdrawlProcessor {
    int billAmount = 100;

    public OneHundredWithdrawlProcessor(AmountWithdrawlProcessor nextWithdrawlProcessor) {
        super(nextWithdrawlProcessor);
    }

    public void withdraw(ATM atm, int amount) {
        int billCount = amount / billAmount;
        int remaining = amount % billAmount;

        if(billCount > atm.getRs1hBills()) {
            int deducting = atm.getRs1hBills();
            atm.deduct1hNotes(deducting);
            remaining = amount-billAmount*deducting;

            System.out.println("deducting " + deducting + " 1h notes");
        } else {
            atm.deduct1hNotes(billCount);
            System.out.println("deducting " + billCount + " 1h notes");
        }

        if(remaining > 0) {
            System.out.println("Something went wrong");
        }
    }
}