package CRUD;

import java.sql.Date;

public class Empleado {
	private int empno;
	private String nombre;
	private String trabajo;
	private int jefe;
	private Date antiguedad;
	private double salario;
	private double comision;
	private int deptno;
	
	public Empleado(int empno, String nombre, String trabajo, int jefe, Date antiguedad, double salario,
			double comision, int detno) {
		this.empno = empno;
		this.nombre = nombre;
		this.trabajo = trabajo;
		this.jefe = jefe;
		this.antiguedad = antiguedad;
		this.salario = salario;
		this.comision = comision;
		this.deptno = detno;
	}
	public Empleado() {
	}
	public int getEmpno() {
		return empno;
	}
	public void setEmpno(int empno) {
		this.empno = empno;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getTrabajo() {
		return trabajo;
	}
	public void setTrabajo(String trabajo) {
		this.trabajo = trabajo;
	}
	public int getJefe() {
		return jefe;
	}
	public void setJefe(int jefe) {
		this.jefe = jefe;
	}
	public Date getAntiguedad() {
		return antiguedad;
	}
	public void setAntiguedad(Date antiguedad) {
		this.antiguedad = antiguedad;
	}
	public double getSalario() {
		return salario;
	}
	public void setSalario(double salario) {
		this.salario = salario;
	}
	public double getComision() {
		return comision;
	}
	public void setComision(double comision) {
		this.comision = comision;
	}
	public int getDeptno() {
		return deptno;
	}
	public void setDeptno(int detno) {
		this.deptno = detno;
	}
	@Override
	public String toString() {
		return "Empleado [empno=" + empno + ", nombre=" + nombre + ", trabajo=" + trabajo + ", jefe=" + jefe
				+ ", antiguedad=" + antiguedad + ", salario=" + salario + ", comision=" + comision + ", detno=" + deptno
				+ "]\n";
	}
	
	
}
