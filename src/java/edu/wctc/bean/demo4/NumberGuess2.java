package edu.wctc.bean.demo4;

// IMPORTANT: this demo uses the JSF faces version of these classes
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * This class is a ManagedBean that demonstrates request handling, session
 * management and form field validation using annotations. No JSF component
 * validation features in the view are used for this demo.
 *
 * @author jlombardo
 */
@ManagedBean
@SessionScoped
public class NumberGuess2 implements Serializable {
    private static final long serialVersionUID = 1L;
//    These can be used when you need a custom message. The Min and Max
//    annotation used below do not allow custom messages.
//    @DecimalMin(value="1", message="Value must be greater than 0")
//    @DecimalMax(value="10", message="Value must be less than 11")
    @NotNull(message="Field cannot be empty")
    @Min(1)
    @Max(10)
    private int guess;
    private int minValue = 1;
    private int maxValue = 10;
    private int correctAnswer = 5;
    private int totalGuessesForUser = 0;

    /** Creates a new instance of NumberGuess1 */
    public NumberGuess2() {
    }

    /**
     * This method only checks whether the guess is correct or not.
     * If no guess was provided or the guess is not between minValue and
     * maxValue, the annotations for Min and Max above do the work.
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
