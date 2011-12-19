/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.wctc.bean.demo5;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author jlombardo
 */
public interface IEmployeeDAO {

    /**
     * Retrieve all employees in the Employee table.
     * @return <code>List</code> of <code>Employee</code> entities.
     */
    List<Employee> findAll();

    /**
     * Stores new or updated employee information to persistent storage.
     *
     * @param pk - the primary key of the db record. If the
     * primary key is null, an insert will be performed, else an update is
     * performed.
     * @param lastName - the last name of the employee; not validated
     * @param firstName - the first name of the employee; ;not validated
     * @param email - the primary email address of the employee; not validated
     * @hireDate - the date of hire for the employee; not validated
     */
    void store(Long pk, String lastName, String firstName, String email, Date hireDate);

    public List<Employee> findPage(List<Employee> empList, int firstRecord, int pageSize, String sortField, boolean sortOrder);

    public int getTotalRecordCount(String table);
}
