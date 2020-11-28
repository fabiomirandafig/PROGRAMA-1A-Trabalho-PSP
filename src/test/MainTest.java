package test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import main.Main;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class MainTest {
  private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
  DecimalFormat d;

	@Before
  	public void setup() {
	  System.setOut(new PrintStream(outContent));
	  d = new DecimalFormat("#,###0.00", new DecimalFormatSymbols(new Locale("en", "US")));
	}

	@After
  	public void tearDown() throws IOException {
      outContent.close();
  	} 

	@Test
	public void testPrincipal(){
    String a = "10 3 1 2 3 4 5 5 4 3 2 1\n";
    String esperado = "4\n0.50\n4.50\n1.41\n3.00\n";
		Scanner leitura = new Scanner(a);
		Main.principal(leitura);
		System.setOut(new PrintStream(outContent));
		assertEquals(esperado,outContent.toString());
	}

	@Test
	public void testNumerodeClassificados(){
		int total = 9;
		int minimo = 4;
		int []pontuacoes = {543,88,123,435,88,4,88,9,23};
		int classificados = Main.NumerodeClassificados(total, minimo, pontuacoes);
		assertEquals(6, classificados);
	}

	@Test
	public void testMediaCompetidores(){
		int total = 14;
		int []pontuacoes = {41,432,55,31,532,112,54,742,88,2,64,7,11,444};
		double mediacompetidores = Main.MediaCompetidores(total,pontuacoes);
		assertEquals(d.format(186.79), d.format(mediacompetidores));
	}

	@Test
	public void testDesvioCompetidores(){
		int total = 6;
		int []pontuacoes = {112,44,1,43,99,44};
		double m = 57.17;
		double desviocompetidores = Main.DesvioCompetidores(total,pontuacoes,m); 
		assertEquals(d.format(37.55), d.format(desviocompetidores));
	}

	@Test
	public void testMediaClassificados(){
		int classificados = 5;
		int []pontuacoes = {778,432,99,88,88};
		double mediaclassificados = Main.MediaClassificados(classificados,pontuacoes); 
		assertEquals(d.format(297.00), d.format(mediaclassificados));
	}

	@Test
	public void testDesvioClassificados(){
		int classificados = 3;
		int []pontuacoes = {432,432,432};
		double m = 432.00;
		double desvioclassificados = Main.DesvioClassificados(classificados,pontuacoes,m); 
		assertEquals(d.format(0.00), d.format(desvioclassificados));
	}

}
