package components;

import atmStates.ATMState;
import atmStates.Authenticating;
import atmStates.CashDispensingState;
import atmStates.CheckBalance;
import atmStates.IdleState;
import atmStates.SelectOptionsState;

/**
 * ATM
 */
public class ATM {
    private static ATM atmObject;

    private Card insertedCard;
    ATMState atmState;

    private int atmBalance = 0;
    int rs2kBills = 0;
    int rs5hBills = 0;
    int rs1hBills = 0;


    private ATM() {
        makeIdle();
    }

    public static ATM getAtmObject() {
        if(atmObject == null) {
            atmObject = new ATM();
        }
        return atmObject;
    }

    /*
    operations
     */
    public void exit() {
        makeIdle();
        System.out.println("Returning card");
        insertedCard = null;
    }

    public void deduct2kNotes(int count) {
        this.rs2kBills -= count;
        updateBalance(this.rs2kBills, this.rs5hBills, this.rs1hBills);
    }

    public void deduct5hNotes(int count) {
        this.rs5hBills -= count;
        updateBalance(this.rs2kBills, this.rs5hBills, this.rs1hBills);
    }

    public void deduct1hNotes(int count) {
        this.rs1hBills -= count;
        updateBalance(this.rs2kBills, this.rs5hBills, this.rs1hBills);
    }

    public void insertBills(int rs2kBills, int rs5hBills, int rs1hBills) {
        this.rs2kBills = rs2kBills;
        this.rs5hBills = rs5hBills;
        this.rs1hBills = rs1hBills;

        updateBalance(rs2kBills, rs5hBills, rs1hBills);
    }

    public int updateBalance(int rs2kBills, int rs5hBills, int rs1hBills) {
        this.atmBalance = 2000*rs2kBills + 500*rs5hBills + 100*rs1hBills;
        return this.atmBalance;
    }

    /*
    state change methods
     */
    public void makeIdle() {
        this.setAtmState(new IdleState(atmObject));
    }

    public void insertCard(Card card) {
        System.out.println("Card inserted");
        this.setCard(card);
        this.setAtmState(new Authenticating(atmObject));
    }

    public void removeCard() {
        this.insertedCard = null;
    }

    public void gotoDisplayOptionsPage() {
        this.setAtmState(new SelectOptionsState(atmObject));
    }

    public void goToCashDispensingPage() {
        this.setAtmState(new CashDispensingState(atmObject));
    }

    public void goToBalanceCheckPage() {
        this.setAtmState(new CheckBalance(atmObject));
    }


    /*
    getters & setters
     */
    public void setCard(Card card) {
        this.insertedCard = card;
    }

    public void setAtmState(ATMState atmState) {
        this.atmState = atmState;
    }

    public Card getInsertedCard() {
        return insertedCard;
    }

    public int getATMBalance() {
        return atmBalance;
    }

    public int getRs2kBills() {
        return rs2kBills;
    }

    public int getRs5hBills() {
        return rs5hBills;
    }

    public int getRs1hBills() {
        return rs1hBills;
    }
}