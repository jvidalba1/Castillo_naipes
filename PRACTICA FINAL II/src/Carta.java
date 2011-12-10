
public class Carta {

	private int numero;
	private char rango;

	public Carta (int a, char b)
	{
		numero=a;
		rango=b;
	}

	public int getNumero()
	{
		return numero;
	}

	public char getRango()
	{
		return rango;
	}
	public void setDatos(int a, char b)
	{
		numero=a;
		rango=b;
	}
}
