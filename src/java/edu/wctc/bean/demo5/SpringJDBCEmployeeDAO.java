package edu.wctc.bean.demo5;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCountCallbackHandler;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jlombardo
 */
@Repository
@Scope("session")
public class SpringJDBCEmployeeDAO implements IEmployeeDAO, Serializable {
    private static final long serialVersionUID = 1L;
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Employee> findAll() throws DataAccessException {
        List employeeList = new ArrayList<Employee>();
        
        final String sql =
            "SELECT * FROM EMPLOYEE";

        // Spring will do the low-level db access
        List<Map<String, Object>> rawList = jdbcTemplate.queryForList(sql);
        
        // Now transform the raw data into Employee instances
        for(Map<String,Object> map : rawList) {
            Employee e = new Employee();
            e.setFirstName(map.get("FIRSTNAME").toString());
            e.setLastName(map.get("LASTNAME").toString());
            e.setEmail(map.get("EMAIL").toString());
            e.setHireDate((Date)map.get("HIREDATE"));
            employeeList.add(e);
        }
        
        return employeeList;
    }

    @Override
    public void store(Long pk, String lastName, String firstName, String email, Date hireDate) {
         if(pk == null) {
             final String sql = "INSERT INTO EMPLOYEE "
                + "(FIRSTNAME, LASTNAME, EMAIL, HIREDATE, version, dept_id) "
                + "VALUES (?, ?, ?, ?, 1, 1)";
             jdbcTemplate.update(sql, firstName, lastName, email, hireDate);
             
         } else {
             final String sql = "UPDATE EMPLOYEE SET FIRSTNAME=?, "
                + "LASTNAME=?, EMAIL=?, HIREDATE=? WHERE ID=?";
             jdbcTemplate.update(sql, firstName, lastName, email, hireDate, pk);
             
         }
    }

    @Override
    public List<Employee> findPage(List<Employee> empList, int firstRecord, 
        int pageSize, String sortField, boolean sortOrder) 
        throws DataAccessException {

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM (");
        sql.append("SELECT TOP ").append(pageSize).append(" * FROM (");
        sql.append("SELECT TOP ").append(pageSize + firstRecord).append(" * ");

        if(sortField == null) {
            sql.append("FROM EMPLOYEE ORDER BY ID ASC) as foo ORDER BY ID DESC) ");
            sql.append("as bar ORDER BY ID ASC");
        } else {
            String orderDir = sortOrder ? "DESC" : "ASC";
            sql.append("FROM EMPLOYEE ORDER BY ").append(sortField);
            sql.append(" ").append(orderDir);
            sql.append(") as foo ORDER BY ");
            sql.append(sortField).append(" ");
            sql.append(sortOrder ? "ASC" : "DESC");
            sql.append(") ");
            sql.append("as bar ORDER BY ").append(sortField).append(" ");
            sql.append(orderDir);
        }

        empList.clear();

        // Spring will do the low-level db access
        List<Map<String, Object>> rawList = 
                jdbcTemplate.queryForList(sql.toString());
        
        // Now transform the raw data into Employee instances
        for(Map<String,Object> map : rawList) {
            Employee e = new Employee();
            e.setFirstName(map.get("FIRSTNAME").toString());
            e.setLastName(map.get("LASTNAME").toString());
            e.setEmail(map.get("EMAIL").toString());
            e.setHireDate((Date)map.get("HIREDATE"));
            empList.add(e);
        }
        
        return empList;
    }

    @Override
    public int getTotalRecordCount(String table) {
        RowCountCallbackHandler countCallback = new RowCountCallbackHandler();  // not reusable
        jdbcTemplate.query("SELECT * FROM EMPLOYEE", countCallback);
        return countCallback.getRowCount();
    }
    
    public static void main(String[] args) {
        AbstractApplicationContext ctx = 
                new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        IEmployeeDAO dao = (IEmployeeDAO)ctx.getBean("springJDBCEmployeeDAO");
        List<Employee> list = dao.findAll();
        System.out.println(list);
    }
    
}
