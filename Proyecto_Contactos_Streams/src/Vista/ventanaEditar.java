package Vista;

import javax.swing.JButton;
import javax.swing.JDialog;
import Modelo.Archivo;
import Modelo.contacto;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.Frame;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ventanaEditar extends JDialog 
{
	private int opcion = 0;
	Archivo misContactos = new Archivo();
	
	contacto cambios = new contacto();
	
	private JTextField tf_nombre;
	private JTextField tf_telefono;
	private JTextField tf_email;
	
	JComboBox cb_dispositivo;
	JComboBox cb_direccion;
	JComboBox cb_grupo;

	public ventanaEditar(Frame ventanaprincipal, boolean modal, contacto dato) 
	{
		super(ventanaprincipal,modal);
		setTitle("Opciones contacto");
		setBounds(100, 100, 405, 280);
		getContentPane().setLayout(null);
		
		//cargar base de datos de Archivo
		misContactos.cargarDispositivos();
		misContactos.cargarDireccion();
		misContactos.cargarGrupo();
		
		JLabel label = new JLabel("Nombre:");
		label.setFont(new Font("Arial", Font.PLAIN, 15));
		label.setBounds(10, 11, 89, 14);
		getContentPane().add(label);
		
		tf_nombre = new JTextField(dato.getNombre());
		tf_nombre.setColumns(10);
		tf_nombre.setBounds(109, 11, 239, 20);
		getContentPane().add(tf_nombre);
		
		tf_telefono = new JTextField(dato.getTelefono());
		tf_telefono.setColumns(10);
		tf_telefono.setBounds(109, 42, 239, 20);
		getContentPane().add(tf_telefono);
		
		JLabel label_1 = new JLabel("Telefono:");
		label_1.setFont(new Font("Arial", Font.PLAIN, 15));
		label_1.setBounds(10, 42, 89, 14);
		getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("Dispositivo:");
		label_2.setFont(new Font("Arial", Font.PLAIN, 15));
		label_2.setBounds(10, 74, 89, 14);
		getContentPane().add(label_2);
		
		cb_dispositivo = new JComboBox();
		consultarTelefono();
		cb_dispositivo.setSelectedItem(dato.getTipoContacto());
		cb_dispositivo.setBounds(109, 73, 239, 20);
		getContentPane().add(cb_dispositivo);
		
		JLabel label_3 = new JLabel("Email:");
		label_3.setFont(new Font("Arial", Font.PLAIN, 15));
		label_3.setBounds(10, 104, 89, 14);
		getContentPane().add(label_3);
		
		tf_email = new JTextField(dato.getCorreo());
		tf_email.setColumns(10);
		tf_email.setBounds(109, 104, 239, 20);
		getContentPane().add(tf_email);
		
		JLabel label_4 = new JLabel("Direcci\u00F3n:");
		label_4.setFont(new Font("Arial", Font.PLAIN, 15));
		label_4.setBounds(10, 136, 89, 14);
		getContentPane().add(label_4);
		
		cb_direccion = new JComboBox();
		consultarDireccion();
		cb_direccion.setSelectedItem(dato.getTipoLugar());
		cb_direccion.setBounds(109, 135, 239, 20);
		getContentPane().add(cb_direccion);
		
		JLabel label_5 = new JLabel("Grupo:");
		label_5.setFont(new Font("Arial", Font.PLAIN, 15));
		label_5.setBounds(10, 167, 89, 14);
		getContentPane().add(label_5);
		
		cb_grupo = new JComboBox();
		consultarGrupos();
		cb_grupo.setSelectedItem(dato.getTipoGrupo());
		cb_grupo.setBounds(109, 166, 239, 20);
		getContentPane().add(cb_grupo);
		
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
		btn_add_grupo.setBounds(354, 164, 25, 22);
		getContentPane().add(btn_add_grupo);
		
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
		btn_add_direccion.setBounds(354, 133, 25, 22);
		getContentPane().add(btn_add_direccion);
		
		Button btn_add_dispositivo = new Button("+");
		btn_add_dispositivo.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
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
		btn_add_dispositivo.setFont(new Font("Arial", Font.BOLD, 15));
		btn_add_dispositivo.setBounds(354, 73, 25, 22);
		getContentPane().add(btn_add_dispositivo);
		
		JButton btn_modificar = new JButton("Guardar cambios");
		btn_modificar.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				if(tf_nombre.getText().equals("")||tf_telefono.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Introduce los datos faltantes");
				}
				else
				{
					//llenado de informacion al objeto nuevo
					cambios.setNombre(tf_nombre.getText());
					cambios.setTelefono(tf_telefono.getText());
					cambios.setCorreo(tf_email.getText());
					cambios.setTipoContacto((String)cb_dispositivo.getSelectedItem());
					cambios.setTipoLugar((String)cb_direccion.getSelectedItem());
					cambios.setTipoGrupo((String)cb_grupo.getSelectedItem());
					
					opcion = 1;
					
					//desaparecer ventana
					dispose();	
				}			
			}
		});
		btn_modificar.setBounds(146, 207, 134, 23);
		getContentPane().add(btn_modificar);
		
		JButton btn_eliminar = new JButton("Eliminar");
		btn_eliminar.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				int opcionEliminar = JOptionPane.showOptionDialog(null, "¿Esta seguro de eliminar el registro permanetenete?", "Eliminar contacto", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[] { "Eliminar", "Cancelar"},"Cancelar");
				
				//opcion 0 = eliminar - opcion 1 = no - opcion 2 = cancelar
				if(opcionEliminar==0)
				{
					opcion = 2;
					//desaparecer ventana
					dispose();	
				}		
			}
		});
		btn_eliminar.setBounds(47, 207, 89, 23);
		getContentPane().add(btn_eliminar);
		
		JButton btn_cancelar = new JButton("Cancelar");
		btn_cancelar.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				//desaparecer ventana
				dispose();
			}
		});
		btn_cancelar.setBounds(290, 207, 89, 23);
		getContentPane().add(btn_cancelar);
	}
	
	public void consultarTelefono()
	{
		cb_dispositivo.setModel(misContactos.consultaDispositivos());
	}
	
	public void consultarDireccion()
	{
		cb_direccion.setModel(misContactos.consultaDirecciones());
	}
	
	public void consultarGrupos()
	{
		cb_grupo.setModel(misContactos.consultaGrupos());
	}
	
	public contacto getCambios() 
	{
		return cambios;
	}

	public int getOpcion() 
	{
		return opcion;
	}

	public void setOpcion(int opcion) 
	{
		this.opcion = opcion;
	}	
}
