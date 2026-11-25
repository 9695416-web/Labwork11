package model;

import exceptions.InsufficientFundsException;
import exceptions.InvalidAmountException;
import exceptions.InvalidPasswordException;

public class BankAccount {
    private String customerName;
    private String userId;
    private String password;
    private double balance;

    public BankAccount(String customerName, String userId, String password) {
        validatePassword(password);
        this.customerName = customerName;
        this.userId = userId;
        this.password = password;
        this.balance = 0.0;
    }

    public static void validatePassword(String password) {
        if (password == null || password.length() < 8 || !password.matches(".*\\d.*")) {
            throw new InvalidPasswordException(
                    "Пароль повинен містити щонайменше 8 символів та хоча б одну цифру."
            );
        }
    }

    public boolean checkPassword(String inputPassword) {
        return this.password.equals(inputPassword);
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new InvalidAmountException("Сума внеску повинна бути більшою за 0.");
        }
        this.balance += amount;
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new InvalidAmountException("Сума зняття повинна бути більшою за 0.");
        }
        if (this.balance - amount < 0) {
            throw new InsufficientFundsException("Недостатньо коштів на рахунку. Поточний баланс: " + this.balance);
        }
        this.balance -= amount;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public double getBalance() {
        return balance;
    }
}