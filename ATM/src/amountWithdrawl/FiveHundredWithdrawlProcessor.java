package amountWithdrawl;

import components.ATM;

public class FiveHundredWithdrawlProcessor extends AmountWithdrawlProcessor {
    int billAmount = 500;

    public FiveHundredWithdrawlProcessor(AmountWithdrawlProcessor nextWithdrawlProcessor) {
        super(nextWithdrawlProcessor);
    }

    public void withdraw(ATM atm, int amount) {
        int billCount = amount / billAmount;
        int remaining = amount % billAmount;

        if(billCount > atm.getRs5hBills()) {
            int deducting = atm.getRs5hBills();
            atm.deduct5hNotes(deducting);
            remaining = amount-billAmount*deducting;

            System.out.println("deducting " + deducting + " 5h notes");
        } else {
            atm.deduct5hNotes(billCount);
            System.out.println("deducting " + billCount + " 1h notes");
        }

        if(remaining > 0) {
            this.nextProcessor.withdraw(atm, remaining);
        }
    }
}