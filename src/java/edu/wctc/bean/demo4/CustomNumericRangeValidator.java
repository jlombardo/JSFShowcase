package edu.wctc.bean.demo4;

import javax.el.ValueExpression;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * This is a custom validator class. The main reason for creating such
 * classes  is that they are reusable with a variety of MessageBeans. Instead
 * of repeating validation logic in each bean, custom validator objects can
 * be referenced directly from the view page.
 *
 * @author jlombardo
 */
@FacesValidator(value="NumericRangeRequirement")
public class CustomNumericRangeValidator implements Validator {

    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        Integer theGuess = (Integer) value;
        Integer minValue = null;
        Integer maxValue = null;

        // Lookkup some properties values using EL
        Application application = context.getApplication();
        ValueExpression veMinValue = application.getExpressionFactory()
                .createValueExpression(context.getELContext(),
                "#{i18n.minValue}", Integer.class);
        ValueExpression veMaxValue = application.getExpressionFactory()
                .createValueExpression(context.getELContext(),
                "#{i18n.maxValue}", Integer.class);

        try {
            minValue = (Integer) veMinValue.getValue(context. getELContext());
            maxValue = (Integer) veMaxValue.getValue(context. getELContext());
        } catch (Throwable e) {
            // log error
        }


        if (minValue == null || theGuess < minValue || theGuess > maxValue) {
            String errMsg = "Only numbers between "
                    + minValue + " and " + maxValue + " are allowed";
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,errMsg,errMsg);
            throw new ValidatorException(msg); // halts further processing
            // and causes the error message to be sent to the view
        }
    }

}
