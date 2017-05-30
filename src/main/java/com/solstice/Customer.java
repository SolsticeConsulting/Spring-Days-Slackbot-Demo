package com.solstice;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Justin Baumgartner on 5/18/17.
 */
@Entity
public class Customer {

    @Id
    private String id;
    private String username;
    private String password;
    private String firstName;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<SecurityQuestionAnswer> securityQuestionAnswers;

    protected Customer() {}

    public Customer(String id, String username, String password, String firstName) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.securityQuestionAnswers = new ArrayList<SecurityQuestionAnswer>();
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void addSecurityQuestionAnswer(SecurityQuestionAnswer securityQuestionAnswer) {
        securityQuestionAnswers.add(securityQuestionAnswer);
    }

    public List<SecurityQuestionAnswer> getSecurityQuestionAnswers() {
        return securityQuestionAnswers;
    }

}
