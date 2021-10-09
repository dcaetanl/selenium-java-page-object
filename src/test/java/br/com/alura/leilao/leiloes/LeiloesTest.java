package br.com.alura.leilao.leiloes;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.alura.leilao.login.LoginPage;

public class LeiloesTest {
	
	private LeiloesPage leiloesPage;
	private CadastroLeilaoPage cadastroLeilaoPage;
	
	@BeforeEach
	public void inicializar() {
		LoginPage loginPage = new LoginPage();
		loginPage.preencheFormularioLogin("fulano", "pass");
		this.leiloesPage = loginPage.efetuaLogin();
		this.cadastroLeilaoPage = leiloesPage.cadastroLeilaoPage();
	}
	
	
	@AfterEach
	public void exitBrowser() {
		this.leiloesPage.exitBrowser();
	}

	@Test
	public void deveriaCadastrarLeilao() {
				
		String hoje = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		String nome = "Caixa Misteriosa " + hoje;
		String valor = "490.00";
		
		this.leiloesPage = cadastroLeilaoPage.cadastraLeilao(nome, valor, hoje);
		
		assertTrue(leiloesPage.isLeilaoCadastrado(nome, valor, hoje));
	}
	
	@Test
	public void deveriaValidarCadastroDeLeilao() {
		
		this.leiloesPage = cadastroLeilaoPage.cadastraLeilao("", "", "");
		
		assertFalse(this.cadastroLeilaoPage.isPaginaAtual());
		assertTrue(this.leiloesPage.isPaginaAtual());
		assertTrue(this.cadastroLeilaoPage.isMsgValidacaoVisivel());
	}
	
}
