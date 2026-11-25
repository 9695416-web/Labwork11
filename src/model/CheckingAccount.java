package model;

import exceptions.WithdrawalLimitExceededException;

public class CheckingAccount extends BankAccount {
    private double dailyWithdrawalLimit = 300.0;

    public CheckingAccount(String customerName, String userId, String password) {
        super(customerName, userId, password);
    }

    public CheckingAccount(String customerName, String userId, String password, double dailyWithdrawalLimit) {
        super(customerName, userId, password);
        this.dailyWithdrawalLimit = dailyWithdrawalLimit;
    }

    @Override
    public void withdraw(double amount) {
        if (amount > dailyWithdrawalLimit) {
            throw new WithdrawalLimitExceededException(
                    "Сума зняття (" + amount + ") перевищує денний ліміт (" + dailyWithdrawalLimit + ")."
            );
        }
        super.withdraw(amount);
    }

    public double getDailyWithdrawalLimit() {
        return dailyWithdrawalLimit;
    }

    public void setDailyWithdrawalLimit(double dailyWithdrawalLimit) {
        this.dailyWithdrawalLimit = dailyWithdrawalLimit;
    }
}