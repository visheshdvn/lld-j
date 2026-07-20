package amountWithdrawl;

import components.ATM;

public abstract class AmountWithdrawlProcessor {
    protected AmountWithdrawlProcessor nextProcessor;

    public AmountWithdrawlProcessor(AmountWithdrawlProcessor nextProcessor) {
        this.nextProcessor = nextProcessor;
    }

    public abstract void withdraw(ATM atm, int amount);
}
