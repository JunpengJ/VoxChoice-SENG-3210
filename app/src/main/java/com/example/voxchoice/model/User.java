package com.example.voxchoice.model;
public class User {
    public String username;
    public String password;
    public String account_type;

    public User() {
    }
    public User(String username, String password, String account_type) {
        this.username = username;
        this.password = password;
        this.account_type = account_type;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccount_type() {
        return account_type;
    }
    public void setAccount_type(String account_type) {
        this.account_type = account_type;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", account_type='" + account_type + '\'' +
                '}';
    }
}
