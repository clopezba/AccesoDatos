package AnexoII;

import java.sql.Date;

public class Empleado {
	private int empno;
	private String nombre;
	private String trabajo;
	private int jefe;
	private Date antiguedad;
	private double salario;
	private double comision;
	private int dept;
	
	public Empleado() {
	}

	public Empleado(int empno, String nombre, String trabajo, int jefe, Date antiguedad, double salario,
			double comision, int dept) {
		this.empno = empno;
		this.nombre = nombre;
		this.trabajo = trabajo;
		this.jefe = jefe;
		this.antiguedad = antiguedad;
		this.salario = salario;
		this.comision = comision;
		this.dept = dept;
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

	public int getDept() {
		return dept;
	}

	public void setDept(int dept) {
		this.dept = dept;
	}

	@Override
	public String toString() {
		return "\nEmpleado [empno=" + empno + ", nombre=" + nombre + ", trabajo=" + trabajo + ", jefe=" + jefe
				+ ", antiguedad=" + antiguedad + ", salario=" + salario + ", comision=" + comision + ", dept=" + dept
				+ "]";
	}
	
	
	
}
