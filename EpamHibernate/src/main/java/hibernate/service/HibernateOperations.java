package hibernate.service;

/**
 * Created by Aliaksei_Cherkas.
 */

import hibernate.dao.HibernateDAO;
import hibernate.entity.Address;
import hibernate.entity.Employee;
import hibernate.entity.EmployeePersonalInfo;
import hibernate.entity.Project;
import hibernate.entity.Unit;

public class HibernateOperations {
	
	private static HibernateDAO<Object> dao = new HibernateDAO<Object>();

	public static Employee createEmployee(Employee employee, int unit_id, Address address, EmployeePersonalInfo personalInfo) 
			throws Exception{
		employee.setAddress(address);
		employee.setPersonalInfo(personalInfo);
		personalInfo.setEmployee(employee);
		

		Unit unit = (Unit) dao.retrieve(Unit.class, unit_id);
		if(unit == null){
			throw new Exception("Unit is not found in DB. Please, create unit first.");
		}
		unit.getEmployees().add(employee);
		employee.setUnit(unit);
		
		dao.save(employee);
		return employee;
	}

	public static Unit createUnit(Unit unit) throws Exception{
		dao.save(unit);
		return unit;
	}
	
	public static Project createProject(Project project) throws Exception{
		dao.save(project);
		return project;
	}
	
	public static void addEmployeeToProject(Employee employee, Project project) throws Exception {
		employee.getProjects().add(project);
		project.getEmployees().add(employee);
		dao.save(project);
	}
	
	public static void addEmployeeToUnit(Employee employee, Unit unit) throws Exception {
		employee.setUnit(unit);
		unit.getEmployees().add(employee);
		
		dao.save(unit);
	}
	
	public static void updateEmployee(Employee employee) throws Exception {
		dao.save(employee);
	}
	
	public static void updateUnit(Unit unit) throws Exception {
		dao.save(unit);
	}
	
	public static void updateProject(Project project) throws Exception {
		dao.save(project);
	}
	
	public static void removeEmployee(Employee employee) throws Exception {
		dao.delete(employee);
	}
	
	public static void removeProject(Project project) throws Exception {
		dao.delete(project);
	}
	
	public static void removeUnit(Unit unit) throws Exception {
		dao.delete(unit);
	}
	
	public static Unit getUnitById(long id) throws Exception {
		return (Unit) dao.retrieve(Unit.class, id);
	}
	
	public static Project getProjectById(long id) throws Exception {
		return (Project) dao.retrieve(Project.class, id);
	}
	
	public static Employee getEmployeeById(long id) throws Exception {
		return (Employee) dao.retrieve(Employee.class, id);
	}
	
}
