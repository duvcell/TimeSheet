package Clases;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Home {

	WebDriver driver = null;
	WebElement txtUser;
	WebElement txtPaswd;
	WebElement btLogin;
	WebElement vistaLinkTimeEntry;
	WebElement linkTimeEntry;

	public WebDriver loguearUsuario() {
		try {
			inicializarComponentes();
			BuscarElementosLogueo();
			Logueo();
			Esperar();
            AbrirTimesheet();
			irPagina(this.driver.getCurrentUrl());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return driver;
	}

	public Home() {
	}

	private void BuscarElementosLogueo() {
		// Buscamos el elemento para introducir el usuario
		txtUser = this.driver.findElement(By.xpath("//*[@id=\"USER\"]"));

		// Buscamos el elemento para introducir la contraseña
		txtPaswd = this.driver.findElement(By.xpath("//*[@id=\"PASSWORD\"]"));

		btLogin = this.driver.findElement(By.id("login_button"));
	}

	public void inicializarComponentes() {
		System.setProperty("webdriver.chrome.driver", "Jars y Drivers/chromedriver_2.37.exe");
		this.driver = new ChromeDriver();
		irPagina("https://www.ultimatix.net");
		driver.manage().window().maximize();

	}
	
	private void irPagina(String url) {
		this.driver.get(url);
	}

	private void Esperar() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void Logueo() throws IOException {

		Datos credenciales = new Datos();

		// Introducimos usuario
		txtUser.sendKeys(credenciales.getUserName());

		// Introducimos la contraseña
		txtPaswd.sendKeys(credenciales.getPassword());

		// Pulsamos botón para logearnos
		btLogin.click();
		if (null != driver) {
			System.err.println("Url acual: " + driver.getCurrentUrl());
		}

	}

	public void AbrirTimesheet() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, 10);		
		vistaLinkTimeEntry = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//a[contains(.,'Timesheet Entry') and (@class='trendApps')]")));
		linkTimeEntry = wait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//a[contains(.,'Timesheet Entry') and (@class='trendApps')]")));
		linkTimeEntry.click();

	}

}
