package edu.wctc.bean.demo1;

// IMPORTANT: this demo uses the JSF faces version of these classes
import java.io.Serializable;
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
public class Demo1Bean implements Serializable {
    private static final long serialVersionUID = 1L;
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
