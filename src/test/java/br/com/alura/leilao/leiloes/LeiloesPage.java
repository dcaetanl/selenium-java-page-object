package br.com.alura.leilao.leiloes;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import br.com.alura.leilao.PageObject;

public class LeiloesPage extends PageObject{
	
	private static final String URL_NOVO_LEILAO = "http://localhost:8080/leiloes/new";
	private static final String URL_LEILOES = "http://localhost:8080/leiloes";	
	
	public LeiloesPage(WebDriver browser) {
		super(browser);
	}

	public CadastroLeilaoPage cadastroLeilaoPage() {
		this.browser.navigate().to(URL_NOVO_LEILAO);
		return new CadastroLeilaoPage(browser);
	}

	public boolean isLeilaoCadastrado(String nome, String valor, String data) {
		WebElement ultimaLinhaTabelaLeilao = this.browser.findElement(By.cssSelector("#tabela-leiloes tbody tr:last-child"));
		WebElement colunaNomeLeilao = ultimaLinhaTabelaLeilao.findElement(By.cssSelector("td:nth-child(1)"));
		WebElement colunaDataLeilao = ultimaLinhaTabelaLeilao.findElement(By.cssSelector("td:nth-child(2)"));
		WebElement colunaValorLeilao = ultimaLinhaTabelaLeilao.findElement(By.cssSelector("td:nth-child(3)"));
		
		return 
			colunaNomeLeilao.getText().equals(nome) && 
			colunaDataLeilao.getText().equals(data) && 
			colunaValorLeilao.getText().equals(valor);
	}

	public boolean isPaginaAtual() {
		return browser.getCurrentUrl().equals(URL_LEILOES);
	}


}
