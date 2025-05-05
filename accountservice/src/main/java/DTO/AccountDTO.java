package DTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public class AccountDTO {

    @NotNull
    private String accountType;

    @NotNull
    @Positive
    private BigDecimal balance;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private String fatherName;

    @NotNull
    private String job;

    @NotNull
    @Email
    private String email;

    private String phone;
    private String address;


    public @NotNull String getAccountType() {
        return accountType;
    }

    public void setAccountType(@NotNull String accountType) {
        this.accountType = accountType;
    }

    public @NotNull @Positive BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(@NotNull @Positive BigDecimal balance) {
        this.balance = balance;
    }

    public @NotNull String getFirstName() {
        return firstName;
    }

    public void setFirstName(@NotNull String firstName) {
        this.firstName = firstName;
    }

    public @NotNull String getLastName() {
        return lastName;
    }

    public void setLastName(@NotNull String lastName) {
        this.lastName = lastName;
    }

    public @NotNull String getFatherName() {
        return fatherName;
    }

    public void setFatherName(@NotNull String fatherName) {
        this.fatherName = fatherName;
    }

    public @NotNull String getJob() {
        return job;
    }

    public void setJob(@NotNull String job) {
        this.job = job;
    }

    public @NotNull @Email String getEmail() {
        return email;
    }

    public void setEmail(@NotNull @Email String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
