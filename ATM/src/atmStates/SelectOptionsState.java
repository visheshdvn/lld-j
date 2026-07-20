package atmStates;

import java.util.Scanner;

import components.ATM;
import utils.InputUtils;

/**
 * SelectOptionsState
 */
public class SelectOptionsState extends ATMState {
    public SelectOptionsState(ATM atm) {
        displayOptions(atm);
    }

    @Override
    public void displayOptions(ATM atm) {
        // Scanner scan = new Scanner(System.in);

        // System.out.println("Enter 1 to withdraw cash, 2 to check balance: ");
        int option = InputUtils.takeIntInput("Enter 1 to withdraw cash, 2 to check balance: ");

        switch (option) {
            case 1:
                atm.goToCashDispensingPage();
                break;
            case 2:
                atm.goToBalanceCheckPage();
                break;
            default:
                System.out.println("Incorrect option entered");
                atm.exit();
                break;
        }
    }
}