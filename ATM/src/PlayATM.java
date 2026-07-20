import components.ATM;
import components.Account;
import components.Card;
import components.User;

public class PlayATM {
    public static void main(String[] args) throws Exception {
        Account account = new Account(5000);
        Card card = new Card(account);
        User user = new User(card);

        ATM atm = ATM.getAtmObject();
        atm.insertBills(5, 5, 5);
        atm.insertCard(card);
    }
}
