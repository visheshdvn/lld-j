package expense.splits;

import users.User;

public class Split {
    String splitId;
    Float amount;
    User paidBy;
    User owedTo;

    public Split(String splitId, Float amount, User paidBy, User owedTo) {
        this.splitId = splitId;
        this.amount = amount;
        this.paidBy = paidBy;
        this.owedTo = owedTo;
    }

    /*
    getters & setters
     */

    public String getSplitId() {
        return splitId;
    }

    public void setSplitId(String splitId) {
        this.splitId = splitId;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public User getPaidBy() {
        return paidBy;
    }

    public void setPaidBy(User paidBy) {
        this.paidBy = paidBy;
    }

    public User getOwedTo() {
        return owedTo;
    }

    public void setOwedTo(User owedTo) {
        this.owedTo = owedTo;
    }
}
