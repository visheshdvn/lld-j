package components;

public class Account {
    private Integer balance;

    public Account(Integer balance) {
        this.balance = balance;
    }

    // 
    public int deductBalance(int sum) throws IllegalStateException {
        if(sum <= balance) {
            balance -= sum;
            return balance;
        }

        throw new IllegalStateException("sum cannot be greater than account balance");
    }

    /*
    getters and setters
     */
    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }
}
