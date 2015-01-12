package hibernate.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Created by Aliaksei_Cherkas.
 */

@Entity
@Table(name = "employee")
public class Employee {

	@Id
	@GeneratedValue
	@Column(name = "empl_id")
	private long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "surname")
	private String surName;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "employee_status")
	private EmployeeStatus employeeStatus;

	@Embedded
	private Address address;
	
	@OneToOne(mappedBy = "employee", cascade = CascadeType.ALL)
	private EmployeePersonalInfo personalInfo;
	
	@ManyToOne
	@JoinColumn(name="unit_id", referencedColumnName = "unit_id")
	private Unit unit;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(
			name = "empl_project", 
			joinColumns = {@JoinColumn(name = "empl_id")},
			inverseJoinColumns = {@JoinColumn(name = "project_id")})
	private Set<Project> projects = new HashSet<Project>();;
	
	
	
	
	public Employee() {
	}
	
	public Employee(String name, String surName, EmployeeStatus employeeStatus){
		this.name = name;
		this.surName = surName;
		this.employeeStatus = employeeStatus;
	}
	
	public long getId() {
        return id;
    }
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setSurName(String surName) {
		this.surName = surName;
	}
	
	public String getSurName() {
		return surName;
	}
	
	public EmployeeStatus getEmployeeStatus() {
		return employeeStatus;
	}
	
	public void setEmployeeStatus(EmployeeStatus employeeStatus) {
		this.employeeStatus = employeeStatus;
	}
	
	public Address getAddress() {
		return address;
	}
	
	public void setAddress(Address address) {
		this.address = address;
	}
	
	public EmployeePersonalInfo getPersonalInfo() {
		return personalInfo;
	}
	
	public void setPersonalInfo(EmployeePersonalInfo personalInfo) {
		this.personalInfo = personalInfo;
	}
	
	
	public void setProjects(Set<Project> projects) {
		this.projects = projects;
	}
	
	public Set<Project> getProjects() {
		return projects;
	}
	
	public void setUnit(Unit unit) {
		this.unit = unit;
	}
	
	public Unit getUnit() {
		return unit;
	}
}
