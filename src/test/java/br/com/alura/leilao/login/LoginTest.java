package br.com.alura.leilao.login;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LoginTest {
	
	private LoginPage loginPage;
	
	@BeforeEach
	public void start() {
		this.loginPage = new LoginPage();
	}
	
	@AfterEach
	public void exitBrowser() {
		this.loginPage.exitBrowser();
	}

	@Test
	public void deveriaEfetuarLoginComDadosValidos() {
				
		loginPage.preencheFormularioLogin("fulano", "pass");
		loginPage.efetuaLogin();

		assertFalse(loginPage.isLoginPage());
		assertEquals("fulano", loginPage.getUserLogin());
		
	}

	@Test
	public void naoDeveriaEfetuarLoginComDadosInvalidos() {
				
		loginPage.preencheFormularioLogin("usuarioInvalido", "senhaInvalida");
		loginPage.efetuaLogin();

		assertTrue(loginPage.isLoginPageError());
		assertNull("usuarioInvalido", loginPage.getUserLogin());
		assertTrue(loginPage.containsText("Usuário e senha inválidos."));
		
	}
	
	@Test
	public void naoDeveriaAcessarPaginaRestritaSemEstarLogado() {
		
		loginPage.paginaRestrita();
		
		assertTrue(loginPage.isLoginPage());
		assertFalse(loginPage.containsText("Dados do Leilão"));
		
	}
}
