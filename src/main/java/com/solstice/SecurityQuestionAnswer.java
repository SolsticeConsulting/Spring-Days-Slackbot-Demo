package com.solstice;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by Justin Baumgartner on 5/25/17.
 */
@Entity
public class SecurityQuestionAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private String question;
    private String answer;

    protected SecurityQuestionAnswer() {}

    public SecurityQuestionAnswer(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

}
