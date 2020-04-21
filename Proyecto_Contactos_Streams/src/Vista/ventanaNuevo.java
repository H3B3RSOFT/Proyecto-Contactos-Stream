package Vista;

import javax.swing.JButton;
import javax.swing.JDialog;
import Modelo.Archivo;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.Frame;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Button;

public class ventanaNuevo extends JDialog
{
	private String nombre, telefono, correo, tipoContacto, tipoLugar, tipoGrupo;
	boolean sw=false;
	
	Archivo misContactos = new Archivo();
	
	private JTextField tf_nonbre;
	private JTextField tf_telefono;
	private JTextField tf_email;
	private JComboBox cb_dispositivo;
	private JComboBox cb_Direccion;
	private JComboBox cb_Grupo;

	public ventanaNuevo(Frame ventanaprincipal, boolean modal)
	{		
		super(ventanaprincipal,modal);
		setTitle("Nuevo contacto");
		setBounds(100, 100, 415, 293);
		getContentPane().setLayout(null);
		
		//cargar base de datos de Archivo
		misContactos.cargarDispositivos();
		misContactos.cargarDireccion();
		misContactos.cargarGrupo();
		
		JLabel label = new JLabel("Nombre:");
		label.setFont(new Font("Arial", Font.PLAIN, 15));
		label.setBounds(18, 19, 89, 14);
		getContentPane().add(label);
		
		tf_nonbre = new JTextField();
		tf_nonbre.setColumns(10);
		tf_nonbre.setBounds(117, 19, 239, 20);
		getContentPane().add(tf_nonbre);
		
		JLabel label_1 = new JLabel("Telefono:");
		label_1.setFont(new Font("Arial", Font.PLAIN, 15));
		label_1.setBounds(18, 50, 89, 14);
		getContentPane().add(label_1);
		
		tf_telefono = new JTextField();
		tf_telefono.setColumns(10);
		tf_telefono.setBounds(117, 50, 239, 20);
		getContentPane().add(tf_telefono);
		
		JLabel label_2 = new JLabel("Dispositivo:");
		label_2.setFont(new Font("Arial", Font.PLAIN, 15));
		label_2.setBounds(18, 82, 89, 14);
		getContentPane().add(label_2);
		
		cb_dispositivo = new JComboBox();
		consultarTelefono();
		cb_dispositivo.setBounds(117, 81, 239, 20);
		getContentPane().add(cb_dispositivo);
		
		JLabel label_3 = new JLabel("Email:");
		label_3.setFont(new Font("Arial", Font.PLAIN, 15));
		label_3.setBounds(18, 112, 89, 14);
		getContentPane().add(label_3);
		
		tf_email = new JTextField();
		tf_email.setColumns(10);
		tf_email.setBounds(117, 112, 239, 20);
		getContentPane().add(tf_email);
		
		JLabel label_4 = new JLabel("Direcci\u00F3n:");
		label_4.setFont(new Font("Arial", Font.PLAIN, 15));
		label_4.setBounds(18, 144, 89, 14);
		getContentPane().add(label_4);
		
		cb_Direccion = new JComboBox();
		consultarDireccion();
		cb_Direccion.setBounds(117, 143, 239, 20);		
		getContentPane().add(cb_Direccion);
		
		JLabel label_5 = new JLabel("Grupo:");
		label_5.setFont(new Font("Arial", Font.PLAIN, 15));
		label_5.setBounds(18, 175, 89, 14);
		getContentPane().add(label_5);
		
		cb_Grupo = new JComboBox();
		consultarGrupos();
		cb_Grupo.setBounds(117, 174, 239, 20);
		getContentPane().add(cb_Grupo);
		
		JButton btn_agregar = new JButton("Guardar");
		btn_agregar.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				if(tf_nonbre.getText().equals("")||tf_telefono.getText().equals(""))
				{
					JOptionPane.showMessageDialog(ventanaNuevo.this, "Introduce los datos faltantes");
				}
				else
				{
					//llenado de informacion al objeto nuevo
					nombre = tf_nonbre.getText();
					telefono = tf_telefono.getText();
					correo = tf_email.getText();
					tipoContacto = (String)cb_dispositivo.getSelectedItem();
					tipoLugar = (String)cb_Direccion.getSelectedItem();
					tipoGrupo = (String)cb_Grupo.getSelectedItem();
					
					sw = true;//que tiene datos cargados
					
					//desaparecer ventana
					dispose();
				}			
			}
		});
		btn_agregar.setBounds(141, 218, 118, 23);
		getContentPane().add(btn_agregar);
		
		Button btn_add_Dispositivo = new Button("+");
		btn_add_Dispositivo.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				String nuevo = JOptionPane.showInputDialog("Nuevo Dispositivo");
				if(nuevo.length()!=0)
				{
					misContactos.nuevoDispositivo(nuevo);
					//misContactos.guardarDispositivos();
					consultarTelefono();
				}
			}
		});
		btn_add_Dispositivo.setFont(new Font("Arial", Font.BOLD, 15));
		btn_add_Dispositivo.setBounds(362, 81, 25, 22);
		getContentPane().add(btn_add_Dispositivo);
		
		Button btn_add_direccion = new Button("+");
		btn_add_direccion.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				String nuevo = JOptionPane.showInputDialog("Nueva Direccion");
				if(nuevo.length()!=0)
				{
					misContactos.nuevoDireccion(nuevo);
					//misContactos.guardarDireccion();
					consultarDireccion();
				}
				
			}
		});
		btn_add_direccion.setFont(new Font("Arial", Font.BOLD, 15));
		btn_add_direccion.setBounds(362, 141, 25, 22);
		getContentPane().add(btn_add_direccion);
		
		Button btn_add_grupo = new Button("+");
		btn_add_grupo.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				String nuevo = JOptionPane.showInputDialog("Nuevo grupo");
				if(nuevo.length()!=0)
				{
					misContactos.nuevoGrupo(nuevo);
					//misContactos.guardarGrupo();
					consultarGrupos();
				}
			}
		});
		btn_add_grupo.setFont(new Font("Arial", Font.BOLD, 15));
		btn_add_grupo.setBounds(362, 172, 25, 22);
		getContentPane().add(btn_add_grupo);
		
		JButton btn_cancelar = new JButton("Cancelar");
		btn_cancelar.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				//desaparecer ventana
				dispose();
			}
		});
		btn_cancelar.setBounds(269, 218, 118, 23);
		getContentPane().add(btn_cancelar);
	}
	
	public void consultarTelefono()
	{
		cb_dispositivo.setModel(misContactos.consultaDispositivos());
	}
	
	public void consultarDireccion()
	{
		cb_Direccion.setModel(misContactos.consultaDirecciones());
	}
	public void consultarGrupos()
	{
		cb_Grupo.setModel(misContactos.consultaGrupos());
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getTipoContacto() {
		return tipoContacto;
	}

	public void setTipoContacto(String tipoContacto) {
		this.tipoContacto = tipoContacto;
	}

	public String getTipoLugar() {
		return tipoLugar;
	}

	public void setTipoLugar(String tipoLugar) {
		this.tipoLugar = tipoLugar;
	}

	public String getTipoGrupo() {
		return tipoGrupo;
	}

	public void setTipoGrupo(String tipoGrupo) {
		this.tipoGrupo = tipoGrupo;
	}

	public boolean isSw() {
		return sw;
	}

	public void setSw(boolean sw) {
		this.sw = sw;
	}
}
