package atmStates;

import amountWithdrawl.AmountWithdrawlProcessor;
import amountWithdrawl.FiveHundredWithdrawlProcessor;
import amountWithdrawl.OneHundredWithdrawlProcessor;
import amountWithdrawl.TwoThousandWithdrawlProcessor;
import components.ATM;
import utils.InputUtils;

public class CashDispensingState extends ATMState {

    private static final AmountWithdrawlProcessor amountWithdrawlProcessor = new TwoThousandWithdrawlProcessor(new FiveHundredWithdrawlProcessor(new OneHundredWithdrawlProcessor(null)));

    public CashDispensingState(ATM atm) {
        dispenseCash(atm);
    }

    @Override
    public void dispenseCash(ATM atm) {
        // System.out.println("Enter the amount to withdraw: ");
        int amount = InputUtils.takeIntInput("Enter the amount to withdraw: ");

        if(amount > atm.getATMBalance()) {
            System.out.println("Sorry, we cannot dispense that amount");
            atm.exit();
            return;
        } else if(amount > atm.getInsertedCard().getAssociatedAccount().getBalance()) {
            System.out.println("Not enough balance in account");
            atm.exit();
            return;
        }

        // TODO: withdraw
        amountWithdrawlProcessor.withdraw(atm, amount);
        atm.getInsertedCard().getAssociatedAccount().deductBalance(amount);
        System.out.println("Cash provided");
        atm.exit();
    }
}
