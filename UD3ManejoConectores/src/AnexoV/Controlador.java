package AnexoV;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class Controlador implements ActionListener, MouseListener{
	private Vista vista;
	AccesoBDatos abd = new AccesoBDatos();
	
	Controlador(Vista vista) throws ClassNotFoundException, SQLException {
		this.vista = vista;
		abd.conectar();
		cargarTabla();
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		String comando = ae.getActionCommand();
		int socioID = Integer.parseInt(this.vista.txtID.getText());
		String nombre = this.vista.txtNombre.getText();
		int estatura = Integer.parseInt(this.vista.txtEstatura.getText());
		int edad = Integer.parseInt(this.vista.txtEdad.getText());
		String localidad = this.vista.txtLocalidad.getText();
		
		
		
		
	}

	private void limpia() {
		vista.txtID.setText("");
		vista.txtNombre.setText("");
		vista.txtEstatura.setText("");
		vista.txtEdad.setText("");
		vista.txtLocalidad.setText("");
	}
	
	protected void cargarTabla() {
		ResultSet resultado;
		Vector<Object> fila;
		
		for (int i = this.vista.dtm.getRowCount(); i > 0; i--) {
			vista.dtm.removeRow(i-1);
		}
		
	}
	
	
	@Override
	public void mouseClicked(MouseEvent me) {
		
		
	}

	@Override
	public void mousePressed(MouseEvent e) {}
	@Override
	public void mouseReleased(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
		
}
