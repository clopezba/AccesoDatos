package AnexoI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class ControlAccesoPrep {

	private JFrame frame;
	private JTextField txtUsuario;
	private JButton btnAceptar;
	private JLabel lblCerrado, lblAbierto, lblMensaje;
	private JPasswordField pswCont;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ControlAccesoPrep window = new ControlAccesoPrep();
					window.frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ControlAccesoPrep() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setBounds(45, 29, 81, 27);
		frame.getContentPane().add(lblUsuario);
		
		JLabel lblContrasena = new JLabel("Contrase\u00F1a:");
		lblContrasena.setBounds(45, 63, 81, 27);
		frame.getContentPane().add(lblContrasena);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(129, 32, 61, 20);
		frame.getContentPane().add(txtUsuario);
		txtUsuario.setColumns(10);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AccesoBDatos abd = new AccesoBDatos();
				try {
					abd.conectar();
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
				
				String usuario = txtUsuario.getText().trim();
				char[] contras = pswCont.getPassword();
				String cont = String.valueOf(contras).trim();
				if(usuario.isEmpty() || cont.isEmpty()) {
					lblCerrado.setVisible(true);
					lblAbierto.setVisible(false);
					lblMensaje.setText("");
					JOptionPane.showMessageDialog(frame, "DEBE COMPLETAR LOS DOS CAMPOS", 
							"Mensaje", JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					if(abd.validarP(usuario, cont)) {
						lblMensaje.setText("Bienvenido/a " + abd.dameNombre(usuario));
						lblAbierto.setVisible(true);
						lblCerrado.setVisible(false);
					}
					else {
						lblMensaje.setText("Usuario o contraseña incorrectos");
						lblAbierto.setVisible(false);
						lblCerrado.setVisible(true);
					}
				}
			}
		});
		btnAceptar.setBounds(245, 52, 81, 23);
		frame.getContentPane().add(btnAceptar);
		
		lblMensaje = new JLabel("");
		lblMensaje.setBounds(80, 223, 219, 27);
		frame.getContentPane().add(lblMensaje);
		
		lblAbierto = new JLabel("");
		lblAbierto.setVisible(false);
		lblAbierto.setIcon(new ImageIcon(ControlAccesoPrep.class.getResource("/imagenes/candado_abierto.png")));
		lblAbierto.setBounds(165, 101, 95, 119);
		frame.getContentPane().add(lblAbierto);
		
		lblCerrado = new JLabel("");
		lblCerrado.setIcon(new ImageIcon(ControlAccesoPrep.class.getResource("/imagenes/candado_cerrado.png")));
		lblCerrado.setVisible(false);
		lblCerrado.setBounds(154, 101, 95, 119);
		frame.getContentPane().add(lblCerrado);
		
		pswCont = new JPasswordField();
		pswCont.setBounds(129, 66, 61, 20);
		frame.getContentPane().add(pswCont);
	}
}
