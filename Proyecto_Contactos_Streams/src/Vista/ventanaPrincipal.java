package Vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import javax.swing.*;
import Modelo.Archivo;
import Modelo.contacto;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.awt.Font;

public class ventanaPrincipal extends JFrame 
{
	protected static final Frame VentanaPrincipal = null;
	private JMenuBar miBarra = new JMenuBar();
	public JTable tablaContactos = new JTable();	
	private JButton botonImprimir;
	
	Archivo misContactos = new Archivo();
	
	private int fila;
	private JMenuBar menuBar;
	private JMenu mnMenu;
	private JMenuItem op_importar;
	private JButton btnBuscar;
	private JButton btnNuevo;
	private JMenuItem op_conocenos;
	private JMenuItem op_salir;
	private JButton btn_cancelar;
	
	public static void main(String[] args) 
	{
		
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					ventanaPrincipal frame = new ventanaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ventanaPrincipal() 
	{
		addWindowListener(new WindowAdapter() 
		{
			@Override
			public void windowClosing(WindowEvent arg0) 
			{
				misContactos.guardarContactos();
			}
		});
		setTitle("Contactos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 933, 707);
		
		//carga los archivos guardados
		misContactos.cargarContactos();
		
		//carga la informacion en la tabla
		consultarContactos();
		tablaContactos.setFont(new Font("Arial", Font.PLAIN, 16));
		tablaContactos.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				//devuleve la posicion del mouse
				fila = tablaContactos.rowAtPoint(e.getPoint());
				//columna = tablaContactos.columnAtPoint(e.getPoint());
				
				//conteo de clicks
				if(e.getClickCount()>1)
				{
					try 
					{
						contacto anterior = new contacto(
								tablaContactos.getValueAt(fila, 0).toString(),
								tablaContactos.getValueAt(fila, 1).toString(),
								tablaContactos.getValueAt(fila, 2).toString(),
								tablaContactos.getValueAt(fila, 3).toString(),
								tablaContactos.getValueAt(fila, 4).toString(),
								tablaContactos.getValueAt(fila, 5).toString());
						
						ventanaEditar editar = new ventanaEditar(VentanaPrincipal,true,anterior);

						editar.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
						editar.setVisible(true);
						
						//Actualizacion de datos
						if(editar.getOpcion()==1)
						{
							misContactos.Actualizar(anterior, editar.cambios);
							editar.setOpcion(0);
							consultarContactos();
						}
						
						//eliminar contacto
						if(editar.getOpcion()==2)
						{
							if(misContactos.EliminarContacto(anterior))
								consultarContactos();	
							else
								JOptionPane.showMessageDialog(null, "No se pudo eliminar el contacto seleccionado");
						}
							
					} catch (Exception f) 
					{
						f.printStackTrace();
					}
				}	
			}
		});
		JScrollPane scrollPane = new JScrollPane(tablaContactos);
		getContentPane().add(scrollPane,BorderLayout.CENTER);
		
		botonImprimir = new JButton("Imprimir");
		
		botonImprimir.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				try
				{
					tablaContactos.print();
					
				}catch(Exception f)
				{
					f.printStackTrace(); 
				}	
			}	
		});
		
		/*
		//agrega una LAMINA SUPERIOR para el boton Imprimir
		JPanel laminaSuperior = new JPanel();
		laminaSuperior.add(mensaje);
		laminaSuperior.add(textField);
		getContentPane().add(laminaSuperior, BorderLayout.NORTH);
		*/
		
		//agrega una LAMINA INFERIOR para el boton Imprimir
		JPanel laminaInferior = new JPanel();
		laminaInferior.add(botonImprimir);
		getContentPane().add(laminaInferior, BorderLayout.SOUTH);		
		
		menuBar = new JMenuBar();
		menuBar.setMaximumSize(new Dimension(2, 0));
		setJMenuBar(menuBar);
		
		mnMenu = new JMenu("Men\u00FA");
		mnMenu.setIcon(new ImageIcon(ventanaPrincipal.class.getResource("/Icons/menu.png")));
		menuBar.add(mnMenu);
		
		op_importar = new JMenuItem("Importar");
		op_importar.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				misContactos.importarContactos();
				consultarContactos();
			}
		});
		mnMenu.add(op_importar);
		
		op_conocenos = new JMenuItem("Con\u00F3cenos");
		op_conocenos.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				JOptionPane.showMessageDialog(null, "Por: Heber Alvarado - heber_mania@hotmail.com");
			}
		});
		mnMenu.add(op_conocenos);
		
		op_salir = new JMenuItem("Salir");
		op_salir.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				misContactos.guardarContactos();
				System.exit(0);
			}
		});
		mnMenu.add(op_salir);
		
		btnBuscar = new JButton("Buscar  ");
		btnBuscar.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				String nuevo = JOptionPane.showInputDialog("Ingrese una referencia");
				if(nuevo.length()!=0)
				{
					tablaContactos.setModel(misContactos.buscarContactos(nuevo));
					btn_cancelar.setVisible(true);
				}
					
			}
		});
		btnBuscar.setBorder(UIManager.getBorder("Menu.border"));
		btnBuscar.setIcon(new ImageIcon(ventanaPrincipal.class.getResource("/Icons/search.png")));
		menuBar.add(btnBuscar);
		
		btnNuevo = new JButton("Nuevo  ");
		btnNuevo.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{
					ventanaNuevo dialog = new ventanaNuevo(VentanaPrincipal,true);
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
					
					if(dialog.isSw())
					{
						misContactos.nuevoContacto(dialog.getNombre(), dialog.getTelefono(), dialog.getCorreo(), dialog.getTipoContacto(), dialog.getTipoLugar(), dialog.getTipoGrupo());
						dialog.setSw(false);
						consultarContactos();
					}
				} 
				catch (Exception f) {
					f.printStackTrace();
				}
			}
		});
		btnNuevo.setBorder(UIManager.getBorder("Menu.border"));
		btnNuevo.setIcon(new ImageIcon(ventanaPrincipal.class.getResource("/Icons/add.png")));
		menuBar.add(btnNuevo);
		
		btn_cancelar = new JButton("Cancelar");
		btn_cancelar.setIcon(new ImageIcon(ventanaPrincipal.class.getResource("/Icons/cancel.png")));
		btn_cancelar.setVisible(false);
		btn_cancelar.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				consultarContactos();
				btn_cancelar.setVisible(false);
				
			}
		});
		btn_cancelar.setBorder(UIManager.getBorder("Menu.border"));
		menuBar.add(btn_cancelar);
	}
	
	public void consultarContactos()
	{
		tablaContactos.setModel(misContactos.ConsultaContactos());
	}		
}
