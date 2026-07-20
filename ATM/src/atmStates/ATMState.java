package atmStates;

import components.ATM;

/**
 * ATMState
 */
public abstract class ATMState {
    public void authenticatePIN(ATM atm) {
        System.out.println("Something went wrong!");
    }

    public void displayOptions(ATM atm) {
        System.out.println("Something went wrong!");
    }

    public void dispenseCash(ATM atm) {
        System.out.println("Something went wrong!");
    }

    public void checkBalance(ATM atm) {
        System.out.println("Something went wrong!");
    }
}