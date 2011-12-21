package edu.wctc.bean.demo5;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author jlombardo
 */
@Service("employeeService")
@Scope("singleton")
@Transactional(readOnly=true)
public class EmployeeService implements Serializable {
    private static final long serialVersionUID = 1L;
    @Inject
    private IEmployeeDAO dao;

    public EmployeeService() {
    }

    @Transactional(readOnly = false, rollbackFor = Exception.class, 
        isolation = Isolation.READ_UNCOMMITTED)
    public void saveOrUpdate(Long pk, String lastName, String firstName,
            String email, Date hireDate) {

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
    
    public IEmployeeDAO getDao() {
        return dao;
    }

    public void setDao(IEmployeeDAO dao) {
        this.dao = dao;
    }
    

    
//    public static void main(String[] args) {
//        AbstractApplicationContext ctx = 
//                new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
//        EmployeeService srv = (EmployeeService)ctx.getBean("employeeService");
//        List<Employee> list = srv.findAll();
//        System.out.println(list);
//    }

    
    
}
