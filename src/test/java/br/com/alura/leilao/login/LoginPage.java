package br.com.alura.leilao.login;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import br.com.alura.leilao.PageObject;
import br.com.alura.leilao.leiloes.LeiloesPage;

public class LoginPage extends PageObject {

	private static final String URL_LOGIN = "http://localhost:8080/login";

	public LoginPage() {
		super(null);
		this.browser.navigate().to(URL_LOGIN);
	}	

	public void preencheFormularioLogin(String username, String password) {
		browser.findElement(By.id("username")).sendKeys(username);
		browser.findElement(By.id("password")).sendKeys(password);
	}

	public LeiloesPage efetuaLogin() {
		browser.findElement(By.id("login-form")).submit();
		return new LeiloesPage(browser);
	}

	public boolean isLoginPage() {
		return browser.getCurrentUrl().equals(URL_LOGIN);
	}

	public Object getUserLogin() {
		try {
			return browser.findElement(By.id("usuario-auth")).getText();

		} catch (NoSuchElementException e) {
			return null;
		}
	}

	public void paginaRestrita() {
		browser.navigate().to("http://localhost:8080/leiloes/2");
	}

	public boolean containsText(String text) {
		return browser.getPageSource().contains(text);
	}

	public boolean isLoginPageError() {
		return browser.getCurrentUrl().equals(URL_LOGIN + "?error");
	}
}
