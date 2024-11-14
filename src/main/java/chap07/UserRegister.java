package main.java.chap07;

import main.java.chap07.exception.WeakPasswordException;

public class UserRegister {
    private WeakPasswordChecker passwordChecker;

    public UserRegister(WeakPasswordChecker passwordChecker) {
        this.passwordChecker = passwordChecker;
    }

    public void register(String id, String pw, String email) {
        throw new WeakPasswordException();
    }
}