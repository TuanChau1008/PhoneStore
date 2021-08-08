/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuancq.dto;

/**
 *
 * @author ADmin
 */
public class UserError {
    private String userIDErr;
    private String fullNameErr;
    private String roleIDErr;
    private String password;
    private String confirm;

    public UserError() {
    }

    public UserError(String userIDErr, String fullNameErr, String roleIDErr, String password, String confirm) {
        this.userIDErr = userIDErr;
        this.fullNameErr = fullNameErr;
        this.roleIDErr = roleIDErr;
        this.password = password;
        this.confirm = confirm;
    }

    public String getUserIDErr() {
        return userIDErr;
    }

    public void setUserIDErr(String userIDErr) {
        this.userIDErr = userIDErr;
    }

    public String getFullNameErr() {
        return fullNameErr;
    }

    public void setFullNameErr(String fullNameErr) {
        this.fullNameErr = fullNameErr;
    }

    public String getRoleIDErr() {
        return roleIDErr;
    }

    public void setRoleIDErr(String roleIDErr) {
        this.roleIDErr = roleIDErr;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirm() {
        return confirm;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }
    
    
}
