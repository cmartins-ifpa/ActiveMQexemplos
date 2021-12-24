package exemplo1Fila.objeto;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("Pessoa")
public class Pessoa {
	private String nome;
	private double valor;
	
	public Pessoa() {};
	
	public Pessoa(String nome, double d) {
		this.nome = nome;
		this.valor=d;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}


}
