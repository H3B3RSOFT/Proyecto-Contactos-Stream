package Modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Archivo 
{
	private String ruta = "Contacto.dat";
	
	ArrayList<contacto> misContactos = new ArrayList<contacto>();
	ArrayList<String> Dispositivos = new ArrayList<String>();
	ArrayList<String> Direccion = new ArrayList<String>();
	ArrayList<String> Grupos = new ArrayList<String>();
	
	public void Archivo() {}
	
	public void nuevoContacto(String nombre, String telefono, String correo, String tipoContacto, String tipoLugar,String tipoGrupo)
	{
		misContactos.add(new contacto (nombre,telefono,correo,tipoContacto,tipoLugar,tipoGrupo));
		guardarContactos();
	}
	
	public void nuevoDispositivo(String t)
	{
		Dispositivos.add(t);
	}
	
	public void nuevoDireccion(String d)
	{
		Direccion.add(d);
	}
	
	public void nuevoGrupo(String g)
	{
		Grupos.add(g);
	}
	
	public void guardarContactos()
	{
		try 
		{
			File archivo = new File(ruta);
			FileWriter escritura;
			BufferedWriter miBuffer;
			
			escritura = new FileWriter(archivo);
			miBuffer = new BufferedWriter(escritura);

			for(contacto c: misContactos)
			{
				miBuffer.write(c.getNombre() + ";" + c.getTelefono() + ";" + c.getCorreo() + ";" + c.getTipoContacto() + ";" + c.getTipoLugar() + ";" + c.getTipoGrupo());
				miBuffer.newLine();
			}

			miBuffer.close();
			escritura.close();
		} catch (IOException e) 
		{
			System.out.println("Error de escritura de archivo"+e);
		}
	}
	
	public boolean validarArchivoContactos()
	{
		boolean opc = false;
		try 
		{
			File archivo = new File(ruta);
			if(archivo.exists())
			{
				FileReader lectura = new FileReader(archivo);
				int c = lectura.read();
				
				//verifica el archivo si: esta vacio, no tiene formato asignado
				while(c!=-1 && c!=13)
				{
					if(c==59)
						opc = true;
					c = lectura.read();
				}	
				lectura.close();	
			}
			
		} catch (IOException e) 
		{
			JOptionPane.showMessageDialog(null, "Error de lectura, Verifique su dispositivo externo.");
		}
		return opc;
	}
	
	public void cargarContactos()
	{
		try 
		{
			if(validarArchivoContactos())
			{
				File archivo = new File(ruta);
				
				FileReader lectura = new FileReader(archivo);
				BufferedReader miBuffer = new BufferedReader(lectura);
				
				String linea;
				while((linea = miBuffer.readLine())!= null)
				{
					String [] contacto = linea.split(";");
					misContactos.add(new contacto (contacto[0],contacto[1],contacto[2],contacto[3],contacto[4],contacto[5]));
				}
				miBuffer.close();
				lectura.close();
			}	
		} catch (IOException e) 
		{
			JOptionPane.showMessageDialog(null, "Error de lectura, Verifique su dispositivo externo.");	
		}	
	}
	public void cargarDispositivos()
	{
		try 
		{
			String tipoDisp[] = new String [30];
			int i=0, sw=0;
			
			File archivo = new File(ruta);
			
			if(validarArchivoContactos())
			{
				FileReader lectura = new FileReader(archivo);
				BufferedReader miBuffer = new BufferedReader(lectura);
				
				String linea;
				while((linea = miBuffer.readLine())!= null)
				{
					String [] contacto = linea.split(";");
					
					if(i==0)
					{
						tipoDisp[i]=contacto[3];
						i++;
					}

					for(int j=0;j<i;j++)
					{
						if(contacto[3].equals(tipoDisp[j]))
							sw=1;
					}
					if(sw==0)
					{
						tipoDisp[i]=contacto[3];
						i++;
					}
					else
						sw=0;
				}
				
				for(int k = 0;k<i;k++)
				{
					Dispositivos.add(tipoDisp[k]);
				}

				miBuffer.close();
				lectura.close();
			}
			else
				Dispositivos.add("Móvil");
			
		} catch (IOException e) 
		{
			System.out.println("Error de lectura de archivo" +e );	
		}
	}
	
	public void cargarDireccion()
	{
		try 
		{
			String tipoDire[] = new String [30];
			int i=0, sw=0;
			
			File archivo = new File(ruta);
			
			if(validarArchivoContactos())
			{
				FileReader lectura = new FileReader(archivo);
				BufferedReader miBuffer = new BufferedReader(lectura);
				
				String linea;
				while((linea = miBuffer.readLine())!= null)
				{
					String [] contacto = linea.split(";");
					
					if(i==0)
					{
						tipoDire[i]=contacto[4];
						i++;
					}

					for(int j=0;j<i;j++)
					{
						if(contacto[4].equals(tipoDire[j]))
							sw=1;
					}
					if(sw==0)
					{
						tipoDire[i]=contacto[4];
						i++;
					}
					else
						sw=0;
				}
				
				for(int k = 0;k<i;k++)
				{
					Direccion.add(tipoDire[k]);
				}

				miBuffer.close();
				lectura.close();
			}
			else
				Direccion.add("Otros");
			
		} catch (IOException e) 
		{
			System.out.println("Error de lectura de archivo" +e );	
		}
	}
	
	public void cargarGrupo()
	{
		try 
		{
			String tipoGrup[] = new String [30];
			int i=0, sw=0;
			
			File archivo = new File(ruta);
			
			if(validarArchivoContactos())
			{
				FileReader lectura = new FileReader(archivo);
				BufferedReader miBuffer = new BufferedReader(lectura);
				
				String linea;
				while((linea = miBuffer.readLine())!= null)
				{
					String [] contacto = linea.split(";");
					
					if(i==0)
					{
						tipoGrup[i]=contacto[5];
						i++;
					}

					for(int j=0;j<i;j++)
					{
						if(contacto[5].equals(tipoGrup[j]))
							sw=1;
					}
					if(sw==0)
					{
						tipoGrup[i]=contacto[5];
						i++;
					}
					else
						sw=0;
				}
				
				for(int k = 0;k<i;k++)
				{
					Grupos.add(tipoGrup[k]);
				}

				miBuffer.close();
				lectura.close();
			}
			else
				Grupos.add("Otros");
			
		} catch (IOException e) 
		{
			System.out.println("Error de lectura de archivo" +e );	
		}
	}
	
	public DefaultTableModel buscarContactos(String dato)
	{
		DefaultTableModel dtm = new DefaultTableModel()	
		{   //quita la edicion de la tabla
			public boolean isCellEditable(int row, int column)
			{
				return false;
			}
		};
		
		//titulo a la tabla
		dtm.addColumn("Nombre");
		dtm.addColumn("telefono");
		dtm.addColumn("correo");
		dtm.addColumn("Contactos");
		dtm.addColumn("lugar");
		dtm.addColumn("grupo");
		
		//agregar contenido a la tabla
		String Tabla[] = new String[6];
		for(contacto c: misContactos)
		{
			boolean resultado = c.getNombre().toUpperCase().contains(dato.toUpperCase());
			
			if(resultado)
			{
				Tabla[0] = c.getNombre();
				Tabla[1] = c.getTelefono();
				Tabla[2] = c.getCorreo();
				Tabla[3] = c.getTipoContacto();
				Tabla[4] = c.getTipoLugar();
				Tabla[5] = c.getTipoGrupo();

				dtm.addRow(Tabla);
			}
		}
		
		if(dtm.getRowCount()==0)
		{
			JOptionPane.showMessageDialog(null, "No se encontraron resultados");
			return ConsultaContactos();
		}
		else	
			return dtm;	
	}
	
	public void Actualizar(contacto anterior, contacto actual)
	{
		//agregar contenido a la tabla
		for(contacto c: misContactos)
		{
			if(anterior.getNombre().equals(c.getNombre()) && anterior.getTelefono().equals(c.getTelefono()))
			{
				c.setNombre(actual.getNombre());
				c.setTelefono(actual.getTelefono());
				c.setCorreo(actual.getCorreo());
				c.setTipoContacto(actual.getTipoContacto());
				c.setTipoLugar(actual.getTipoLugar());
				c.setTipoGrupo(actual.getTipoLugar());
			}	
		}
	}
		
	public boolean EliminarContacto(contacto elimina)
	{
		int item = 0, eliminarItem = 0;
		boolean bandera = false;
		for(contacto c: misContactos)
		{
			if(elimina.getNombre().equals(c.getNombre()) && elimina.getTelefono().equals(c.getTelefono()))
			{
				eliminarItem = item;
				bandera = true;
			}
			item++;
		}
		
		if(bandera)
		{
			misContactos.remove(eliminarItem);
		}
		
		return bandera;
	}
	
	public void importarContactos()
	{
		String nom = "",tel="";
		int cont = 0;
		try 
		{
			JFileChooser fc = new JFileChooser();
			fc.showOpenDialog(null);
			File archivo = fc.getSelectedFile();
			
			if(archivo!=null)
			{
				//ruta = archivo.getAbsolutePath();
				
				FileReader lectura = new FileReader(archivo);
				BufferedReader miBuffer = new BufferedReader(lectura);
				
				String linea;
				while((linea = miBuffer.readLine())!= null)
				{
					//resultado = linea.contains("FN:");
					String [] contacto = linea.split(":");
					
					if(contacto[0].equals("BEGIN") && contacto[1].equals("VCARD"))
					{
						cont=1;
					}
					if(cont==4)
						nom = contacto[1];
					if(cont==5)
						tel = contacto[1];
					
					if(contacto[0].equals("END") && contacto[1].equals("VCARD"))
					{
						misContactos.add(new contacto (nom,tel));
						cont=0;
					}
					cont++;
				}
				miBuffer.close();
				lectura.close();
			}
			else
				System.out.println("El archivo no existe");
			
		} catch (IOException e) 
		{
			System.out.println("Error de lectura de archivo" +e );	
		}	
	
	}
	
	public DefaultTableModel ConsultaContactos()
	{	
		DefaultTableModel dtm = new DefaultTableModel()	
		{   //quita la edicion de la tabla
			public boolean isCellEditable(int row, int column)
			{
				return false;
			}
		};
		
		//titulo a la tabla
		dtm.addColumn("Nombre");
		dtm.addColumn("telefono");
		dtm.addColumn("correo");
		dtm.addColumn("Contactos");
		dtm.addColumn("lugar");
		dtm.addColumn("grupo");
			
		//agregar contenido a la tabla
		String Tabla[] = new String[6];
		for(contacto c: misContactos)
		{
			Tabla[0] = c.getNombre();
			Tabla[1] = c.getTelefono();
			Tabla[2] = c.getCorreo();
			Tabla[3] = c.getTipoContacto();
			Tabla[4] = c.getTipoLugar();
			Tabla[5] = c.getTipoGrupo();

			dtm.addRow(Tabla);
		}

		return dtm;	
	}
	
	public DefaultComboBoxModel consultaDispositivos()
	{
		DefaultComboBoxModel modelo = new DefaultComboBoxModel();
		for(String x: Dispositivos)
		{			
			modelo.addElement(x);
		}
		
		return modelo;
	}
	
	public DefaultComboBoxModel consultaDirecciones()
	{
		DefaultComboBoxModel modelo = new DefaultComboBoxModel();
		for(String d: Direccion)
		{		
			modelo.addElement(d);
		}
		
		return modelo;
	}
	
	public DefaultComboBoxModel consultaGrupos()
	{
		DefaultComboBoxModel modelo = new DefaultComboBoxModel();
		for(String g: Grupos)
		{
			modelo.addElement(g);
		}
		
		return modelo;
	}
}
