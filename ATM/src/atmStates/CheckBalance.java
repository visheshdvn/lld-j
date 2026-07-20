package atmStates;

import components.ATM;

public class CheckBalance extends ATMState {

    public CheckBalance(ATM atm) {
        checkBalance(atm);
    }

    @Override
    public void checkBalance(ATM atm) {
        System.out.println("Account Balance: " + atm.getInsertedCard().getAssociatedAccount().getBalance());
        atm.exit();
    }
}
