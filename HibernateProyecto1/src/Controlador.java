import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

import primero.*;


public class Controlador implements ActionListener{
	private vistaEmp vista;
	String fec = ZonedDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE);
		
	SessionFactory sesion = SessionFactoryUtil.getSessionFactory();
	Session session = sesion.openSession();
	
		
	Controlador(vistaEmp vista){
		this.vista = vista;
		rellenarDepartamento();
		rellenarDirector();
		limpiarFormulario();
	}
	
		
	@Override
	public void actionPerformed(ActionEvent arg0) {
		String comando = arg0.getActionCommand();
		
		JTextField numEmp = this.vista.txtEmpNum;
		JTextField apellido = this.vista.txtApellido;
		JTextField oficio =this.vista.txtOficio;
		JTextField salario = this.vista.txtSalario;
		JTextField comision = this.vista.txtComision;
		JTextField fecha = this.vista.txtFecha;
		JComboBox<String> cmbDep = this.vista.cmbDep;
		JComboBox<String> cmbDirec = this.vista.cmbDirec;
		
		switch(comando) {
			case "CONSULTAR":
				
				if(numEmp.getText().isEmpty()) {
					JOptionPane.showMessageDialog(this.vista.frame, "No has introducido ningún número"); 
				}
				else {
					Empleados emp = new Empleados();
					emp = (Empleados) session.get(Empleados.class, Short.valueOf(numEmp.getText()));
					
					if(emp == null) {
						JOptionPane.showMessageDialog(this.vista.frame, "No existe ningún empleado con ese número");
					}
					else {
						apellido.setText(emp.getApellido());
						oficio.setText(emp.getOficio());
						salario.setText(String.valueOf(emp.getSalario()));
						comision.setText(String.valueOf(emp.getComision()));
						fecha.setText(String.valueOf(emp.getFechaAlt()));
						cmbDep.setSelectedItem(emp.getDepartamentos().getDeptNo() + " / " + emp.getDepartamentos().getDnombre());
						Empleados direc = (Empleados) session.get(Empleados.class, Short.valueOf(emp.getDir()));
						cmbDirec.setSelectedItem(emp.getDir() + " / " + direc.getApellido());
					}
				}
				break;
				
			case "INSERTAR":
				
				String numero = numEmp.getText();
				Query consulta = session.createQuery("from Empleados as emp where emp.empNo = :num");
				consulta.setShort("num", Short.valueOf(numero));
			
				
				if (consulta.uniqueResult() != null) {
					JOptionPane.showMessageDialog(this.vista.frame, "Ya existe un empleado con ese número");
				}
				else {
					if(numEmp.getText().isEmpty() || apellido.getText().isEmpty() || oficio.getText().isEmpty() 
							|| salario.getText().isEmpty() || fecha.getText().isEmpty() 
							|| cmbDep.getSelectedIndex()==0 || cmbDirec.getSelectedIndex()==0) {
						JOptionPane.showMessageDialog(this.vista.frame, "Debes rellenar todos los campos");
					}
					else {
						if(comision.getText().isEmpty()) {
							comision.setText("0");
						}
						Transaction tx = session.beginTransaction();
						Empleados emp = new Empleados();
						emp.setEmpNo(Short.valueOf(numEmp.getText()));
						emp.setApellido(apellido.getText());
						emp.setOficio(oficio.getText());
						emp.setSalario(Float.valueOf(salario.getText()));
						emp.setComision(Float.valueOf(comision.getText()));
						String dir = cmbDirec.getSelectedItem().toString().substring(0, 4);
						emp.setDir(Short.valueOf(dir));
						String dep = cmbDep.getSelectedItem().toString().substring(0, 2);
						Departamentos d = new Departamentos();
						d.setDeptNo(Byte.valueOf(dep));
						emp.setDepartamentos(d);
						java.sql.Date fech = Date.valueOf(fec);
						emp.setFechaAlt(fech);
						try{
							session.save(emp);
							try {
								tx.commit();
								JOptionPane.showMessageDialog(this.vista.frame, "Empleado insertado");
								rellenarDirector();
								limpiarFormulario();
								break;
							}
							catch (ConstraintViolationException e) {
								System.out.printf("ERROR SQL: %s%n", e.getSQLException().getMessage());
							}
						}catch (Exception e) {
							System.out.println("ERROR NO CONTROLADO....");
							e.printStackTrace();
						}
					}
				}
				
			case "ELIMINAR":
				
				Short empNo = Short.valueOf(numEmp.getText());
				
				boolean director = false;
					
				Query query = session.createQuery("from Empleados emp where emp.dir= :direc");
				query.setShort("direc", empNo);
				List<Empleados> lista = query.list();
				
				if (!lista.isEmpty() ) {
					JOptionPane.showMessageDialog(this.vista.frame, "No se ha podido borrar. Tiene empleados a su cargo");
					director = true;
				}
				else {
					Transaction trans = session.beginTransaction();
					Empleados empBorrar = (Empleados) session.get(Empleados.class, empNo);
					try {
						if(director == false) {
							session.delete(empBorrar);
							trans.commit();
							JOptionPane.showMessageDialog(this.vista.frame, "Empleado eliminado");
							limpiarFormulario();
							rellenarDirector();
						}
						
						break;
					}catch (Exception e) {
						JOptionPane.showMessageDialog(this.vista.frame, "Ocurrió un error al borrar el empleado");
						e.printStackTrace();
					}
				}
				
			case "MODIFICAR":
				
				Empleados empl = (Empleados) session.get(Empleados.class, Short.valueOf(numEmp.getText()));
				if(numEmp.getText().isEmpty() || apellido.getText().isEmpty() || oficio.getText().isEmpty() 
						|| salario.getText().isEmpty() || fecha.getText().isEmpty() 
						|| cmbDep.getSelectedIndex()==0 || cmbDirec.getSelectedIndex()==0) {
					JOptionPane.showMessageDialog(this.vista.frame, "Debes rellenar todos los campos");
				}
				else {
					if(comision.getText().isEmpty()) {
						comision.setText("0");
					}
					Transaction tx = session.beginTransaction();
					empl.setApellido(apellido.getText());
					empl.setOficio(oficio.getText());
					empl.setSalario(Float.valueOf(salario.getText()));
					empl.setComision(Float.valueOf(comision.getText()));
					String dir = cmbDirec.getSelectedItem().toString().substring(0, 4);
					empl.setDir(Short.valueOf(dir));
					String dep = cmbDep.getSelectedItem().toString().substring(0, 2);
					Departamentos d = new Departamentos();
					d.setDeptNo(Byte.valueOf(dep));
					empl.setDepartamentos(d);
					java.sql.Date fech = Date.valueOf(fec);
					empl.setFechaAlt(fech);
					try{
						session.update(empl);
						try {
							tx.commit();
							JOptionPane.showMessageDialog(this.vista.frame, "Empleado modificado");
							rellenarDirector();
							limpiarFormulario();
							break;
						}
						catch (ConstraintViolationException e) {
							System.out.printf("ERROR SQL: %s%n", e.getSQLException().getMessage());
						}
					}catch (Exception e) {
						System.out.println("ERROR NO CONTROLADO....");
						e.printStackTrace();
					}
				}
							
			case "LIMPIAR":
				limpiarFormulario();
				break;
				
			case "SALIR":
				session.close();
				sesion.close();
				System.exit(0);
		}
		
	}
	protected void rellenarDepartamento() {
		
		JComboBox<String> cmbDep = this.vista.cmbDep;
		Query q = session.createQuery("from Departamentos");
		List<Departamentos> lista = q.list();
		Iterator <Departamentos> iter = lista.iterator();
		while(iter.hasNext()) {
			Departamentos depar = (Departamentos) iter.next();
			String txtCombo = String.valueOf(depar.getDeptNo()) + " / " + depar.getDnombre();
			cmbDep.addItem(txtCombo);
		}
	}
	
	protected void rellenarDirector() {
		
		JComboBox<String> cmbDirec = this.vista.cmbDirec;
		for (int i = 1; i < cmbDirec.getComponentCount(); i++) {
			cmbDirec.remove(i);
		}
		
		Query q = session.createQuery("from Empleados");
		List<Empleados> lista = q.list();
		Iterator <Empleados> iter = lista.iterator();
		while(iter.hasNext()) {
			Empleados emp = (Empleados) iter.next();
			String txtCombo = String.valueOf(emp.getEmpNo()) + " / " + emp.getApellido();
			cmbDirec.addItem(txtCombo);
		}
	}
	protected void limpiarFormulario() {
		JTextField numEmp = this.vista.txtEmpNum;
		JTextField apellido = this.vista.txtApellido;
		JTextField oficio =this.vista.txtOficio;
		JTextField salario = this.vista.txtSalario;
		JTextField comision = this.vista.txtComision;
		JTextField fecha = this.vista.txtFecha;
		JComboBox<String> cmbDep = this.vista.cmbDep;
		JComboBox<String> cmbDirec = this.vista.cmbDirec;
		
		numEmp.setText("");
		apellido.setText("");
		oficio.setText("");
		salario.setText("");
		comision.setText("");
		
		fecha.setText(fec);
		cmbDep.setSelectedIndex(0);
		cmbDirec.setSelectedIndex(0);
	}

}
