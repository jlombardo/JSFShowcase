package edu.wctc.bean.demo4;

// IMPORTANT: this demo uses the JSF faces version of these classes
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

/**
 * This class is a ManagedBean that demonstrates request handling, session
 * management and form field validation using a validateXXXX method (this is
 * called Bean validaiton. Only the JSF component "required" attribute
 * is used for client-side validation in this demo. The rest of the
 * validation logic is in the validateGuess method.
 *
 * @author jlombardo
 */
@ManagedBean
@SessionScoped
public class NumberGuess3 implements Serializable {
    private static final long serialVersionUID = 1L;
    private int guess;
    private int minValue = 1;
    private int maxValue = 10;
    private int correctAnswer = 5;
    private int totalGuessesForUser = 0;

    /** Creates a new instance of NumberGuess1 */
    public NumberGuess3() {
    }

    /**
     * This is a custom validation method that provides a more robust way to do
     * server side validation. By
     * using a method instead of client-side JSF features or annotations as in
     * previous examples, using this technique you can create very complex
     * validation algorithms. Note however that you must still use the JSF
     * component required attribute if necessary because you cannot use a
     * validation method to check for null values. So, really, this is a
     * hybrid validation technique.
     *
     * @param context - needed to send data back to the view.
     * @param toValidate - the JSF component that is bound to this method
     * @param value - the value sent from the JSF component (must not be null)
     * @throws ValidatorException if validation fails
     */
    public void validateGuess(FacesContext context, UIComponent toValidate, Object value)
            throws ValidatorException {
        Integer theGuess = (Integer) value;
        if (theGuess < minValue || theGuess > maxValue) {
            FacesMessage msg = new FacesMessage("Only numbers between "
                    + minValue + " and " + maxValue + " are allowed");
            throw new ValidatorException(msg); // halts further processing
            // and causes the error message to be sent to the view
        }
    }

    /**
     * This method only checks whether the guess is correct or not.
     * Validation is performed elsewhere. (See above.)
     *
     * Note the use of FacesMssage to produce message text that will be
     * displayed on the view page using the message or messages tag (JSF).
     *
     * @return the name of the page (less the .xhtml extension) relative to
     * the page that the submit came from.
     */
    public String processGuess() {
        String guessMsg = "Sorry, that is not the number, please try again.";
        FacesContext context = FacesContext.getCurrentInstance();

        if (guess == correctAnswer) {
            guessMsg = "Correct";
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    guessMsg, guessMsg));
        } else {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,
                    guessMsg, guessMsg));
        }

        this.totalGuessesForUser++;

        return "guess2"; // navigate back to original view page


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
