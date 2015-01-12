package hibernate.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * Created by Aliaksei_Cherkas.
 */

@Entity
@Table(name = "PersonalInfo")
public class EmployeePersonalInfo {

	@GenericGenerator(name = "generator", strategy = "foreign", 
			parameters = @Parameter(name = "property", value = "employee"))
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "empl_id")
	private long employeeId;
	
	@OneToOne(cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn
	private Employee employee;
	
	@Column(name = "phone")
	private String phone;
	
	@Column(name = "room")
	private String room;
	
	public EmployeePersonalInfo(){}
	
	public EmployeePersonalInfo(String phone, String room){
		this.phone = phone;
		this.room = room;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setRoom(String room) {
		this.room = room;
	}
	
	public String getRoom() {
		return room;
	}
	
	public Employee getEmployee() {
		return employee;
	}
	
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	public long getEmploueeId() {
		return employeeId;
	}
	
	public void setEmploueeId(long emploueeId) {
		this.employeeId = emploueeId;
	}
}
