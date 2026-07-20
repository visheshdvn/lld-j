package amountWithdrawl;

import components.ATM;

public class TwoThousandWithdrawlProcessor extends AmountWithdrawlProcessor {

    int billAmount = 2000;

    public TwoThousandWithdrawlProcessor(AmountWithdrawlProcessor nextWithdrawlProcessor) {
        super(nextWithdrawlProcessor);
    }

    public void withdraw(ATM atm, int amount) {
        int billCount = amount / billAmount;
        int remaining = amount % billAmount;

        if(billCount > atm.getRs2kBills()) {
            int deducting = atm.getRs2kBills();
            atm.deduct2kNotes(deducting);
            remaining = amount-billAmount*deducting;

            System.out.println("deducting " + deducting + " 2k notes");
        } else {
            atm.deduct2kNotes(billCount);
            System.out.println("deducting " + billCount + " 2k notes");
        }

        if(remaining > 0) {
            this.nextProcessor.withdraw(atm, remaining);
        }
    }
}
