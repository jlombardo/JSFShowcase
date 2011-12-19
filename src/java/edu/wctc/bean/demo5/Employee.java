package edu.wctc.bean.demo5;

import java.io.Serializable;
import java.util.Date;

/**
 * This class is an entity class that maps to the associated database table.
 * It also acts as a Data Transfer Object betweeen the view and the the Model.
 *
 * @author  Jim Lombardo
 * @version 1.00
 */
public class Employee implements Serializable {
	private static final long serialVersionUID = 1L;

    private Long id;
    private String lastName;
    private String firstName;
    private String email;
    private Date hireDate;

    public Employee() {}

    public Employee(Long id, String lastName, String firstName,
    String email, Date hireDate)
    {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.hireDate = hireDate;
    }
    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the hireDate
     */
    public Date getHireDate() {
        return hireDate;
    }

    /**
     * @param hireDate the hireDate to set
     */
    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }
}
