import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import primero.*;


public class Controlador implements ActionListener{
	private vistaEmp vista;
	
	SessionFactory sesion = SessionFactoryUtil.getSessionFactory();
	Session session = sesion.openSession();
	
	Controlador(vistaEmp vista){
		this.vista = vista;
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
						
					}
				}
				
				
		}
		
	}
	

}
