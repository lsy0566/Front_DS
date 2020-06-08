package com.companyd.hompage.seoul.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Users {
    private int id;

    @NotBlank(message = "이메일은 필수")
    @Email(message = "이메일 형식에 맞추")
    private String email;

    @NotBlank(message = "비번 필수")
    private String password;
    @NotBlank(message = "필수")
    private String username;
    @NotBlank(message = "필수")
    private String phoneNumber;

    private int isMember;
    private int admin;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getIsMember() {
        return isMember;
    }

    public void setIsMember(int isMember) {
        this.isMember = isMember;
    }

    public int getAdmin() {
        return admin;
    }

    public void setAdmin(int admin) {
        this.admin = admin;
    }
}
