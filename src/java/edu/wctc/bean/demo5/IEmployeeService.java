package edu.wctc.bean.demo5;

import java.util.Date;
import java.util.List;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author jlombardo
 */
public interface IEmployeeService {

    List<Employee> findAll();

    List<Employee> findPage(List<Employee> empList, int firstRecord, int pageSize, String sortField, boolean sortOrder);

    IEmployeeDAO getDao();

    int getTotalRecordCount(String table);

    @Transactional(readOnly = false, rollbackFor = Exception.class, isolation = Isolation.READ_UNCOMMITTED)
    void saveOrUpdate(Long pk, String lastName, String firstName, String email, Date hireDate);

    void setDao(IEmployeeDAO dao);
    
}
