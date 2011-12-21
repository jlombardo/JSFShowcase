package edu.wctc.bean.demo5;

import java.io.Serializable;
import java.util.*;
import java.util.logging.*;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.model.LazyDataModel;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.jsf.FacesContextUtils;

/**
 * This class is a ManagedBean for JSF Demo 5. Note that it does NOT
 * use the JSF ManagedBean annotation, and instead uses the javax.inject.Named
 * annotation. This is called the CDI version of a ManagedBean and is a new
 * feature of JEE6. CDI stands for "contexts and dependency injection," which
 * is basically an adaptation of some of the features from the Spring framework.
 * You get all the managed bean functionality of a JSF ManagedBean, plus you
 * get easy dependency injection. See below how an EmployeeService instance
 * is injected into this bean using the "@Inject" annotation. Note that to use
 * CDI in your application you must have a beans.xml file in WEB-INF. This
 * file can be used for additional configuration but none is needed for
 * basic functionality. You just have to have the file in your application.
 * 
 * @author jlombardo
 */
@Named("employeeController")
@Scope("session")
public class EmployeeController implements Serializable {
    private static final long serialVersionUID = 1L;
    private final static Logger logger = Logger.getLogger("MyLogger");
    private static int pgSize;

    private Employee selectedEmployee;
//    private static List<Employee> empList;
    private LazyDataModel<Employee> lazyModel;
    private IEmployeeService empService;
    private transient ApplicationContext ctx;



    public EmployeeController() {
        ctx = FacesContextUtils
                .getWebApplicationContext(FacesContext.getCurrentInstance());
        empService = (IEmployeeService)ctx.getBean("employeeService");
        
        lazyModel = new LazyDataModel<Employee>() {

            @Override
            public List<Employee> load(int first, int pageSize, String sortField,
                   boolean sortOrder, Map<String, String> filters) {

                logger.log(Level.INFO,
                        "Loading the lazy car data between {0} and {1}, orderBy {2}",
                        new Object[]{first, (first+pageSize), sortField});

                pgSize = pageSize;
                List<Employee> empList = new ArrayList<Employee>();
                return getPage(empList, first, pageSize, sortField, sortOrder);
            }
        };
        lazyModel.setRowCount(getTotalRecordCount());
        lazyModel.setPageSize(pgSize);
    }

    public List<Employee> getAll() {
        return empService.findAll();
    }

    private int getTotalRecordCount() {
        return empService.getTotalRecordCount("EMPLOYEE");
    }

    private List<Employee> getPage(List<Employee> empList, int firstRecord,
            int pageSize, String sortField, boolean sortOrder) {

        if (!FacesContext.getCurrentInstance().getRenderResponse()) {
            return null;
	}

        return empService
               .findPage(empList, firstRecord, pageSize, sortField, sortOrder);
    }

    public IEmployeeService getEmpService() {
        return empService;
    }

    public void setEmpService(IEmployeeService empService) {
        this.empService = empService;
    }

    public Employee getSelectedEmployee() {
        return selectedEmployee;
    }

    public void setSelectedEmployee(Employee selectedEmployee) {
        this.selectedEmployee = selectedEmployee;
    }

    public LazyDataModel<Employee> getLazyModel() {
        return lazyModel;
    }

    public void setLazyModel(LazyDataModel<Employee> lazyModel) {
        this.lazyModel = lazyModel;
    }
    
        
    public static void main(String[] args) {
        AbstractApplicationContext ctx = 
                new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        EmployeeController srv = (EmployeeController)ctx.getBean("employeeController");
        List<Employee> list = srv.getAll();
        System.out.println(list);
    }


}
