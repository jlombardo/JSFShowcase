package edu.wctc.bean.demo4;

// IMPORTANT: this demo uses the JSF faces version of these classes
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 * This class is a JSF ManagedBean and it demonstrates request handling,
 * session management and NO VALIDATION. The validation is handled in the view
 * page ("guess1.xhtml) which uses JSF component validation features.
 * 
 * @author jlombardo
 */
@ManagedBean
@SessionScoped
public class NumberGuess1 implements Serializable {
    private static final long serialVersionUID = 1L;
    private int guess;
    private int minValue = 1;
    private int maxValue = 10;
    private int correctAnswer = 5;
    private int totalGuessesForUser = 0;

    /** Creates a new instance of NumberGuess1 */
    public NumberGuess1() {
    }

    /**
     * This method only checks whether the guess is correct or not.
     * If no guess was provided or the guess is not between minValue and
     * maxValue, the client-side validation in the JSF tag does the work.
     *
     * Note the use of FacesMssage to produce message text that will be
     * displayed on the view page using the message or messages tag (JSF).
     *
     * @return the name of the page (less the .xhtml extension) relative to
     * the page that the submit came from.
     */
    public String processGuess() {
        String msg = "Sorry, that is not the number, please try again.";
        FacesMessage facesMsg = null;
        if(guess == correctAnswer) {
            msg = "Correct";
            facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg);
        } else {
            facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg);
        }

        this.totalGuessesForUser++;
        FacesContext.getCurrentInstance().addMessage("successInfo", facesMsg);
        
        return "guess1"; // navigate back to original view page
    }
    public int getGuess() {
        return guess;
    }

    public void setGuess(int guess) {
        this.guess = guess;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    public int getMinValue() {
        return minValue;
    }

    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(int correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public int getTotalGuessesForUser() {
        return totalGuessesForUser;
    }

    public void setTotalGuessesForUser(int totalGuessesForUser) {
        this.totalGuessesForUser = totalGuessesForUser;
    }

}
