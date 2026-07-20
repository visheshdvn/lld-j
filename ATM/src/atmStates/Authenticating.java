package atmStates;

import java.util.Scanner;

import components.ATM;
import utils.InputUtils;

/**
 * Authenticating
 */
public class Authenticating extends ATMState {
    public Authenticating(ATM atm) {
        authenticatePIN(atm);
    }
    
    @Override
    public void authenticatePIN(ATM atm) {
        // System.out.println("Enter card PIN");
        // Scanner scan = new Scanner(System.in);

        // int pin = scan.nextInt();
        // scan.nextLine();
        // scan.close();
        int pin = InputUtils.takeIntInput("Enter card PIN: ");

        boolean authenticated = atm.getInsertedCard().authenticate(pin);

        if(authenticated) {
            System.out.println("Card authentication successfull: ");
            atm.gotoDisplayOptionsPage();
        } else {
            System.out.println("Card authentication failure: ");
            atm.exit();
        }
    }
}