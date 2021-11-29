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
import org.hibernate.exception.DataException;

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
						cmbDep.setSelectedItem(emp.getDepartamentos().getDeptNo() + " / " + emp.getDepartamentos().getDnombre());;
						Empleados direc = (Empleados) session.get(Empleados.class, Short.valueOf(emp.getDir()));
						cmbDirec.setSelectedItem(emp.getDir() + " / " + direc.getApellido());
					}
				}
				break;
			case "INSERTAR":
				String numero = numEmp.getText();
				String consulta = "from Empleados as emp where emp.empNo = " + numero;
				Query q = session.createQuery(consulta);
				if (q.uniqueResult() != null) {
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
							}
							catch (ConstraintViolationException e) {
								System.out.printf("ERROR SQL: %s%n", e.getSQLException().getMessage());
							}
						}catch (DataException e) {
							JOptionPane.showMessageDialog(this.vista.frame, "No se ha podido insertar\n Valor introducido demasiado largo");
						}catch (Exception e) {
							System.out.println("ERROR NO CONTROLADO....");
							e.printStackTrace();
						}
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
