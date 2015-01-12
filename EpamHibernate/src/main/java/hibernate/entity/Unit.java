package hibernate.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Created by Aliaksei_Cherkas.
 */

@Entity
@Table(name = "unit")
public class Unit {

	@Id
	@GeneratedValue
	@Column(name = "unit_id")
	private long id;
	
	@Column(name = "unit_name")
	private String name;

	@OneToMany(mappedBy = "unit")
	private Set<Employee> employees = new HashSet<Employee>();
	
	public Unit(){}
	
	public Unit(String name){
		this.name = name;
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
	
	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}
	
	public Set<Employee> getEmployees() {
		return employees;
	}
}
