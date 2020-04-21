package Modelo;

public class contacto 
{
	private String nombre,telefono,correo,tipoContacto,tipoLugar,tipoGrupo;

	public contacto(){}
	
	public contacto(String nombre, String telefono)
	{
		this.nombre = nombre;
		this.telefono = telefono;
		this.correo = "";
		this.tipoContacto = "Móvil";
		this.tipoLugar = "Otros";
		this.tipoGrupo = "Otros";
	}

	public contacto(String nombre, String telefono, String correo, String tipoContacto, String tipoLugar,String tipoGrupo)
	{
		this.nombre = nombre;
		this.telefono = telefono;
		this.correo = correo;
		this.tipoContacto = tipoContacto;
		this.tipoLugar = tipoLugar;
		this.tipoGrupo = tipoGrupo;
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
	

}
