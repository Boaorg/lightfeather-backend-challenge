package io.lightfeather.springtemplate.dto;

public class Form {

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String supervisor;

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("First name: ").append(this.firstName).append("\n");
        sb.append("Last name: ").append(this.lastName).append("\n");
        sb.append("Email: ").append(this.email).append("\n");
        sb.append("Phone number: ").append(this.phoneNumber).append("\n");
        sb.append("Supervisor: ").append(this.supervisor).append("\n");
        return sb.toString();
    }

    public String getFirstName(){
        return this.firstName;
    }
    public String getLastName(){
        return this.lastName;
    }
    public String getEmail(){
        return this.email;
    }
    public String getPhoneNumber(){
        return this.phoneNumber;
    }
    public String getSupervisor(){
        return this.supervisor;
    }

    public void setFirstName(String firstName) {
        this.firstName=firstName;
    }

    public void setLastName(String lastName) {
        this.lastName=lastName;
    }

    public void setEmail(String email) {
        this.email=email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber=phoneNumber;
    }

    public void setSupervisor(String supervisor) {
        this.supervisor=supervisor;
    }
    
}
