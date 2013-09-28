package parametrizacao;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import xls.ReadXLS;

@RunWith(Parameterized.class)
public class Agenda {

	private final String	id;
	private final String	nome;
	private final String	numero;
	private final String	idade;
	private final String	estado;

	public Agenda(String id, String nome, String numero, String idade, String estado) {
		super();
		this.id = id;
		this.nome = nome;
		this.numero = numero;
		this.idade = idade;
		this.estado = estado;
	}

	@Parameters(name = "{index}: {0}, {1}, {2}, {3}, {4}")
	public static Collection<Object[]> data() {
		//ReadXML data = new ReadXML("src/xml/file.xml");
		ReadXLS data = new ReadXLS("src/xls/file.xls");

		return Arrays.asList(data.getData());

	}

	@Test
	public void lastnameTest() {
		assertTrue(hasLastname(nome));
	}

	@Test
	public void dddTest() {
		assertTrue(hasDDD(numero));
	}

	public boolean hasLastname(String fullname) {
		if (fullname.contains(" "))
			return true;
		else
			return false;
	}

	public boolean hasDDD(String phoneNumber) {
		if (phoneNumber.length() >= 10)
			return true;
		else
			return false;
	}

}