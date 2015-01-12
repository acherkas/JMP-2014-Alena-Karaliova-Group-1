package hibernate;

/**
 * Created by Aliaksei_Cherkas.
 */

import hibernate.service.HibernateOperations;
import hibernate.entity.Address;
import hibernate.entity.Employee;
import hibernate.entity.EmployeePersonalInfo;
import hibernate.entity.EmployeeStatus;
import hibernate.entity.Unit;

public class Main {

	public static void main(String[] args) throws Exception {
		Unit unit = new Unit("Unit_1");
		HibernateOperations.createUnit(unit);
		System.out.println("Unit:" +HibernateOperations.getUnitById(1).getName()+ "was created");
		
		Employee empl = new Employee("Fedor", "Pupkin", EmployeeStatus.FullTime);
		EmployeePersonalInfo emplInfo = new EmployeePersonalInfo("123124", "423DA");
		Address adr = new Address("Minsk", "Main");
		HibernateOperations.createEmployee(empl, 1, adr, emplInfo);
		System.out.println("Employee: "+HibernateOperations.getEmployeeById(1).getName()+", "+ HibernateOperations.getEmployeeById(1).getSurName()+" was added");
	}
}
