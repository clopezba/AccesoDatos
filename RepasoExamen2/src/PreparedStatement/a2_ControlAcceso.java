package PreparedStatement;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class a2_ControlAcceso {

	private JFrame frame;
	private JTextField txtUsuario;
	private JPasswordField passContrasenya;
	private JLabel lblTexto;
	a2_AccesoBDatos abd = new a2_AccesoBDatos();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					a2_ControlAcceso window = new a2_ControlAcceso();
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
	public a2_ControlAcceso() {
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
		lblUsuario.setBounds(30, 30, 77, 13);
		frame.getContentPane().add(lblUsuario);
		
		JLabel lblContrasenya = new JLabel("Contrase\u00F1a:");
		lblContrasenya.setBounds(30, 65, 77, 13);
		frame.getContentPane().add(lblContrasenya);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(124, 24, 85, 19);
		frame.getContentPane().add(txtUsuario);
		txtUsuario.setColumns(10);
		
		passContrasenya = new JPasswordField();
		passContrasenya.setBounds(124, 59, 85, 19);
		frame.getContentPane().add(passContrasenya);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abd.conectar();
				String usuario = txtUsuario.getText().trim();
				char [] contras = passContrasenya.getPassword();
				String contrasenya = String.valueOf(contras).trim();
				
				if(usuario.isEmpty() || contrasenya.isEmpty()) {
					JOptionPane.showMessageDialog(frame, "DEBE COMPLETAR LOS DOS CAMPOS", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
					lblTexto.setText("");
				}
				else {
					if(abd.validar(usuario, contrasenya)) {
						lblTexto.setText("Bienvenido/a " + abd.nombreCompleto(usuario));
					}
					else {
						lblTexto.setText("No existe el usuario o la contraseña es incorrecta");
					}
				}
				
			}
		});
		btnAceptar.setBounds(258, 44, 85, 21);
		frame.getContentPane().add(btnAceptar);
		
		lblTexto = new JLabel("");
		lblTexto.setBounds(82, 155, 250, 26);
		frame.getContentPane().add(lblTexto);
	}
}
