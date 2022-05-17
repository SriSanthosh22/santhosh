package EmployeeAssect.hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

	@Entity
	public class EmployeeDetails {
	@Column
	private String empName;
	@Id
	private int empId;
	@Transient
	private String password;
	@Column
	private int assetId;
	
	public EmployeeDetails() {
		
		// TODO Auto-generated constructor stub
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getAssetId() {
		return assetId;
	}
	public void setAssetId(int assetId) {
		this.assetId = assetId;
	}
	@Override
	public String toString() {
		return "EmployeeDetails [empName=" + empName + ", empId=" + empId + ", password=" + password + ", assetId="
				+ assetId + "]";
	}
	
	
	
}
