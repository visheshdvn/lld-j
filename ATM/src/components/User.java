package components;

public class User {
    private Card card;

    public User(Card card) {
        this.card = card;
    }

    /*
    getters and setters
     */

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }
}
