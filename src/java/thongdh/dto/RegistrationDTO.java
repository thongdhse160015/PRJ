package thongdh.dto;

import java.io.Serializable;

/**
 *
 * @author thongdhse160015
 */
public class RegistrationDTO implements Serializable {

    private String username;
    private String password;
    private String lastname;
    private boolean role;

    public RegistrationDTO(String username, String password, String lastname, boolean role) {
        this.username = username;
        this.password = password;
        this.lastname = lastname;
        this.role = role;
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

    public boolean isRole() {
        return role;
    }

    public void setRole(boolean role) {
        this.role = role;
    }

    public String getFullName() {
        return lastname;
    }

    public void setFullName(String fullName) {
        this.lastname = fullName;
    }

    @Override
    public String toString() {
        return "RegistrationDTO{" + "username=" + username + ", password=" + password + ", lastname=" + lastname + ", role=" + role + '}';
    }

}
