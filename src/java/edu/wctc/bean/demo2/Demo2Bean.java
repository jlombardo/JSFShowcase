package edu.wctc.bean.demo2;

// IMPORTANT: this demo uses the JSF faces version of these classes
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

// Example of a CDI version of a class. Do not use here!
//import javax.annotation.ManagedBean;
/**
 *
 * @author jlombardo
 */
@ManagedBean
@SessionScoped
public class Demo2Bean implements Serializable {
    private static final long serialVersionUID = 1L;
    // This count will be maintained during an HttpSession
    private int userInputCount;
    private String msg = "Hello World";

    public Demo2Bean() {
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String processRequest() {
        userInputCount++;
        return "index";
    }

    public int getUserInputCount() {
        return userInputCount;
    }

    public void setUserInputCount(int userInputCount) {
        this.userInputCount = userInputCount;
    }
}
