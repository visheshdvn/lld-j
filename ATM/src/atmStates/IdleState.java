package atmStates;

import components.ATM;

public class IdleState extends ATMState {
    public IdleState(ATM atm) {
        if(atm != null) {
            atm.removeCard();        
        }
    }
}
