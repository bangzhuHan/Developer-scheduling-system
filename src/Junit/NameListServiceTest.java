package Junit;

import domain.Employee;
import org.testng.annotations.Test;
import service.NameListService;
import service.TeamException;

public class NameListServiceTest {

    @Test
    public void test1(){
        NameListService l1 = new NameListService();
        Employee[] employee = l1.getAllEmployees();
//        for (Employee e :
//                employee) {
//            System.out.println(e);
//        }
        for (int i = 0; i < employee.length; i++) {
            System.out.println(employee[i]);
        }
    }

    @Test
    public void test2(){
        NameListService l2 = new NameListService();
        int id = 7;
        try {
            Employee employee = l2.getEmployee(id);
            System.out.println(employee);
        } catch (TeamException e) {
            e.printStackTrace();
        }
    }
}
