package hibernate.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * Created by Aliaksei_Cherkas.
 */

@Entity
@Table(name = "project")
public class Project {

	@Id
	@GeneratedValue
	@Column(name = "project_id")
	private int projectId;

	@Column(name = "project_name")
	private String name;

	@ManyToMany(mappedBy = "projects")
	private Set<Employee> employees = new HashSet<Employee>();

	public Project(){}
	
	public Project(String name){
		this.name = name;
	}
	
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public int getProjectId() {
		return projectId;
	}
	
	public Set<Employee> getEmployees() {
		return employees;
	}
	
	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}
	
	public void addEmployee(Employee employee){
		employees.add(employee);
	}
}
