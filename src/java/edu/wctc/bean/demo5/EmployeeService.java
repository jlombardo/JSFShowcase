package edu.wctc.bean.demo5;

import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

/**
 *
 * @author jlombardo
 */
@Service("employeeService")
@Scope("session")
public class EmployeeService {
    @Inject
    private IEmployeeDAO dao;

    public EmployeeService() {
    }

    // This should be an atomic transaction
    public void saveOrUpdate(Long pk, String lastName, String firstName,
            String email, Date hireDate) {

        // Part One of transaction...
        dao.store(pk, lastName, firstName, email, hireDate);
    }

    public List<Employee> findAll() {
        return dao.findAll();
    }

    public List<Employee> findPage(List<Employee> empList, int firstRecord,
            int pageSize, String sortField, boolean sortOrder) {
        return dao.findPage(empList, firstRecord, pageSize, sortField, sortOrder);
    }

    public int getTotalRecordCount(String table) {
        return dao.getTotalRecordCount(table);
    }

    
    public static void main(String[] args) {
        AbstractApplicationContext ctx = 
                new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        EmployeeService srv = (EmployeeService)ctx.getBean("employeeService");
        List<Employee> list = srv.findAll();
        System.out.println(list);
    }
    
    
}
