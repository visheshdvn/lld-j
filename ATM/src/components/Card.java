package components;

public class Card {
    private Account account;

    private int pin = 1234;

    public Card(Account account) {
        this.account = account;
    }

    public Account getAssociatedAccount() {
        return account;
    }

    /*
     util methods
     */
    public int debitAmount(int x) {
        return account.deductBalance(x);
    }

    public boolean authenticate(int enteredPin) {
        return enteredPin == pin;
    }
}
