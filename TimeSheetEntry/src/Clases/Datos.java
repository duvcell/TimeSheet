package Clases;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Datos {
	private String userName;
	private String Password;
	private String Horas;

	public Datos(String userName, String password, String Horas) {
		super();
		this.userName = userName;
		Password = password;
		this.Horas = Horas;
	}

	public Datos() throws IOException {
		super();
		leerArchivo();
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getHoras() {
		return Horas;
	}

	public void setHoras(String horas) {
		Horas = horas;
	}

	public void leerArchivo() throws IOException {
		// ----Lectura de datos desde archivos planos----

		String cadena;
		FileReader f = new FileReader(System.getProperty("user.dir") + "/data.txt");
		BufferedReader b = new BufferedReader(f);
		// Se lee cada linea del Archivo mientras sea diferente de nulo
		while ((cadena = b.readLine()) != null) {
			// Se almacena en la variable vectorDatos los datos separados del archivo por ,
			String[] vectorDatos = cadena.split(",");

			this.userName = vectorDatos[0];
			this.Password = vectorDatos[1];
			this.Horas = vectorDatos[2];
			// loginpage.iniciarSesion(vectorDatos[0],vectorDatos[1]);
		}

		b.close();
		f.close();

		// ----Lectura de datos desde archivos planos----

	}
}
