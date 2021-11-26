import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import primero.*;


public class Controlador implements ActionListener{
	private vistaEmp vista;
		
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
			case "LIMPIAR":
				apellido.setText("");
				oficio.setText("");
				salario.setText("");
				comision.setText("");
				String fec = ZonedDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE);
				fecha.setText(fec);
				cmbDep.setSelectedIndex(0);
				cmbDirec.setSelectedIndex(0);
			
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

}
