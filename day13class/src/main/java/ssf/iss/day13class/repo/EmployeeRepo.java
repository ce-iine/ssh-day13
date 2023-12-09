package ssf.iss.day13class.repo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import ssf.iss.day13class.model.Employee;

@Repository // connects database, currently acts as one 
public class EmployeeRepo {

    final String dirPath ="/Users/CelineNg/Desktop/day13class/data";

    final String fileName ="employee.txt";

    private List<Employee> employees;

    public EmployeeRepo() throws ParseException {
        if (employees == null) {
            employees = new ArrayList<Employee>();
        }

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        Date dt = df.parse("1999-11-22");
        Employee emp = new Employee("Hsien Loong", "Lee", "pap@pap.com", "98765432", 8500, dt, 567890);
        employees.add(emp);

        emp = new Employee("Pritam", "Singh", "wp@wp.com","87654321", 7500, dt, 345678);
        employees.add(emp);
    }


    public List<Employee> findAll(){
        return employees;
    }

    public Boolean delete (Employee employee) {
        Boolean result = false;
        int employeeIndex = employees.indexOf(employee);
        if (employeeIndex >= 0){
            employees.remove(employeeIndex);
            result=true;
        }
        return result;
    }

    public Employee findbyEmail(String email){
        return employees.stream()
            .filter(emp ->emp.getEmail().equals(email)).findFirst().get();
    }

    public Boolean save(Employee employee) throws FileNotFoundException{
        Boolean result =false;
        result = employees.add(employee);

        File f = new File(dirPath + "/" + fileName);
        OutputStream os = new FileOutputStream(f, true); //appends instead of overwrites
        PrintWriter pw = new PrintWriter(os);
        pw.println(employee.toString());
        pw.flush();
        pw.close();

        return result;
    }

    public Boolean updateEmployee (Employee employee){
        Boolean result = false;

        //retrieve object from storage
        Employee empObj = employees.stream()
            .filter(emp -> emp.getEmail().equals(employee.getEmail())).findFirst().get();

        int employeeIndex = employees.indexOf(empObj);

        if (employeeIndex >=0){
            employees.get(employeeIndex).setBirthday(employee.getBirthday());
            employees.get(employeeIndex).setEmail(employee.getEmail());
            employees.get(employeeIndex).setFirstName(employee.getFirstName());
            employees.get(employeeIndex).setLastName(employee.getLastName());
            employees.get(employeeIndex).setPhoneNo(employee.getPhoneNo());
            employees.get(employeeIndex).setPostalCode(employee.getPostalCode());
            employees.get(employeeIndex).setSalary(employee.getSalary());
        }
        return result;
    }
    
    
}
