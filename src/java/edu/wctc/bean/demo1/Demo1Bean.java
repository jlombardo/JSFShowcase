package edu.wctc.bean.demo1;

// IMPORTANT: this demo uses the JSF faces version of these classes
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

// Example of a CDI version of a class. Do not use here!
//import javax.annotation.ManagedBean;



/**
 *
 * @author jlombardo
 */
@ManagedBean
@RequestScoped
public class Demo1Bean {
    private String msg = "Hello World";

    public Demo1Bean() {
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String processRequest() {
        return "index"; // name of page to navigate to next
    }
}
