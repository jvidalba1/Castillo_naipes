import java.util.*;

public class Jugador {

	private String nombre;
	private char color;
	private int puntaje=0;
	private Carta sostenida=null;
	private boolean turno=false;

	public Jugador(String nombre, char color)
	{
		this.nombre=nombre;
		this.color=color;
	}

	public void sostener(Carta j)
	{
		sostenida=j;
	}

	public Carta getSostenida()
	{
		return sostenida;
	}

	/**si tiene un solo elemento sostengo
	 * primer elemento este metodo retorna las coordenadas de la carta donde se debe agregar si es cero sostengo
	 *
	 * segundo elemento 1 si juega con la sostenida y 0 si juega con la ingresada(si es pico co 1 sostenida va a la derecha)
	 *  tercero si es uno pico, si es cero piso
	 */
	public  ArrayList <Integer> mejorJugada(Juego tablero,Carta carta,ArrayList <Carta> baraja,Carta sostenRiv)
	{
		ArrayList <Integer> a=new ArrayList<Integer>();
		//primer elemento 1 si juego con la sostenida, 0 si juego con la arrastrada,
		//el segundo elemento es el puntaje a favor o en contra de esa jugada
		//el tercero el piso
		int [] q=mejorPiso(tablero,carta,baraja,sostenRiv);
		//primer elemento si es 1 en la derecha va la sostenida si es cero en la derecha va la arrastrada
		// el segundo es el valor de la jugada
		//tercero numero del pico
		int [] b= mejorPico(tablero,carta,baraja,sostenRiv);
		a=escogerMejor(q,b);
		return a;
	}

	public ArrayList <Integer> escogerMejor(int[]a,int[]b)
	{
		ArrayList <Integer> mejor=new ArrayList<Integer>();
		if(a[1]<b[1])
		{
			if(b[1]<0)
			{
				mejor.add(0);
			}
			else
			{
				mejor.add(b[2]);
				mejor.add(b[0]);
				mejor.add(1);
			}
		}
		else
		{
			if(a[1]<0)
			{
				mejor.add(0);
			}
			else
			{
				mejor.add(a[2]);
				mejor.add(a[0]);
				mejor.add(0);
			}
		}
		return mejor;
	}

	public int[] mejorPiso(Juego tablero, Carta carta, ArrayList <Carta> baraja, Carta sostenRival)
	{
		int [] mejor = {-100,-100,-100};
		int mej=-100;
		Carta usada=null;
		ArrayList <Coordenada> coordenadas=null;
		ArrayList <Integer> lista=null;
		if(tablero.pueFormarPiso())
		{
			lista=tablero.getListPisos();
			coordenadas=tablero.getPisos();
			int j=0;
			while (j<lista.size())
			{
				int q=lista.get(j);
				int i=definirTriangulo(tablero.getCarta(coordenadas.get(q-1).getI()-1,coordenadas.get(q-1).getJ()-1),tablero.getCarta(coordenadas.get(q-1).getI()-1,coordenadas.get(q-1).getJ()+1),carta);
				int k=-100;
				if(sostenida!=null)
				k=definirTriangulo(tablero.getCarta(coordenadas.get(q-1).getI()-1,coordenadas.get(q-1).getJ()-1),tablero.getCarta(coordenadas.get(q-1).getI()-1,coordenadas.get(q-1).getJ()+1),sostenida);
				if(i<k)
				{
					if(k>mejor[1])
					{
						mej=k;
						usada=sostenida;
					}
				}
				else
				{
					if(i>mejor[1])
					{
						mej=i;
						usada=carta;
					}
				}
				if(sostenRival!=null)
				{
					if((baraja.get(0).getRango()==sostenRival.getRango())||(baraja.get(0).getRango()==usada.getRango())
							||(sostenRival.getRango()==usada.getRango()))
							{
								mej=mej-definirTriangulo(usada,sostenRival,baraja.get(0));
							}
				}
				if(mej>mejor[1])
				{
					mejor[1]=mej;
					if(usada==sostenida)
					{
						mejor[0]=1;
					}
					else
					{
						mejor[0]=0;
					}
					mejor[2]=lista.get(j);
				}
				j++;
			}
		}
		return mejor;
	}

	public int[] mejorPico(Juego tablero, Carta carta, ArrayList <Carta> baraja, Carta sostenRival)
	{
		int [] mejor = {-100,-100,-100};
		ArrayList <Integer> lista=null;
		ArrayList<Coordenada[]> coordenadas=null;
		if(tablero.pueFormarPico() && sostenida != null)
		{
			lista=tablero.getListPicos();
			coordenadas=tablero.getPicos();
			int j=0;
			while(j<lista.size())
			{
				Carta piso=tablero.getCarta(coordenadas.get(lista.get(j)-1)[0].getI()-1,coordenadas.get(lista.get(j)-1)[0].getJ()+1);
				int i=picoSolo(piso,sostenida,carta,lista,j);
				// en estos arreglos la primera posicion es el valor de la jugada y la segunda 1 si la sostenida va a la derecha y cero si va a la izquierda
				int k[]=picoDer(piso, sostenRival, carta, lista, j, sostenRival, coordenadas.get(lista.get(j)-1), tablero, baraja);
				int m[]=picoIz(piso, sostenRival, carta, lista, j, sostenRival, coordenadas.get(lista.get(j)-1), tablero, baraja);
				int l[]=mejorPico(tablero, carta, baraja, sostenRival);

				int mej;
				if(i<k[0])
				{
					mej=k[0];
				}
				else
				{
					mej=i;
				}
				int me;
				if(m[0]<l[0])
				{
					me=l[0];
				}
				else
				{
					me=m[0];
				}
				if(me>mej)
				{
					mej=me;
				}

				if(mej>mejor[1])
				{
					mejor[1]=mej;
					if(mej==i)
					{
						mejor[0]=1;
					}
					else
					{
						if(mej==k[0])
						    mejor[0]=k[1];
						if(mej==m[0])
							mejor[0]=m[1];
						if(mej==l[0])
							mejor[0]=l[1];

					}
				}
				j++;
			}

		}
		return mejor;
	}

	public int picoSolo(Carta piso,Carta sostenida,Carta carta, ArrayList <Integer> lista, int j)
	{
		int resultado=-100;
		int r=definirTriangulo(carta,sostenida,piso);
		if(j==0)
		{
			if(lista.get(j)!=lista.get(j+1)-1)
			   resultado=r;
		}
		else if((lista.get(j)!=lista.get(j+1)-1)&&(lista.get(j)!=lista.get(j-1)+1))
		{
			resultado=r;
		}
		return resultado;
	}

	public int [] picoDer(Carta piso,Carta sostenida,Carta carta, ArrayList <Integer> lista, int j,Carta sostenRival,Coordenada[] coordenadas,Juego tablero, ArrayList<Carta> baraja)
	{
		int resultado[]={-100,-100};
		int repar=definirTriangulo(carta,sostenida,piso);
		int resta=0;
		Carta usadaDer=null;
		if((lista.get(j)!=3&&lista.get(j)!=5&&lista.get(j)!=6)&&(lista.get(j)!=lista.get(j+1)-1))
		{
			Carta temp=tablero.getCarta(coordenadas[1].getI(),coordenadas[1].getJ()+2);
			if(temp.getRango()==color)
			{
				if (sostenida.getNumero()>carta.getNumero())
				{
					usadaDer=sostenida;
					resultado[1]=1;
				}
				else
				{
					usadaDer=carta;
					resultado[1]=0;
				}
			}
			else
			{
				if (sostenida.getNumero()<carta.getNumero())
				{
					usadaDer=sostenida;
					resultado[1]=1;
				}
				else
				{
					usadaDer=carta;
					resultado[1]=0;
				}
			}
			resta=definirTriangulo(usadaDer,temp,baraja.get(0));
			if(sostenRival!=null)
			{
				int l=definirTriangulo(usadaDer,temp,sostenRival);
				if(l<resta)
				{
					resta=l;
				}
			}
			resultado[0]=repar+resta;
		}
		return resultado;
	}

	public int [] picoIz(Carta piso,Carta sostenida,Carta carta, ArrayList <Integer> lista, int j,Carta sostenRival,Coordenada[] coordenadas,Juego tablero, ArrayList<Carta> baraja)
	{
		int resultado[]={-100,-100};
		int repar=definirTriangulo(carta,sostenida,piso);
		int resta=0;
		Carta usadaIz=null;
		if((lista.get(j)!=1&&lista.get(j)!=4&&lista.get(j)!=6)&&(lista.get(j)!=lista.get(j-1)+1))
		{
			Carta temp=tablero.getCarta(coordenadas[0].getI(),coordenadas[0].getJ()-2);
			if(temp.getRango()==color)
			{
				if (sostenida.getNumero()>carta.getNumero())
				{
					usadaIz=sostenida;
					resultado[1]=1;
				}
				else
				{
					usadaIz=carta;
					resultado[1]=0;
				}
			}
			else
			{
				if (sostenida.getNumero()<carta.getNumero())
				{
					usadaIz=sostenida;
					resultado[1]=1;
				}
				else
				{
					usadaIz=carta;
					resultado[1]=0;
				}
			}
			resta=definirTriangulo(usadaIz,temp,baraja.get(0));
			if(sostenRival!=null)
			{
				int l=definirTriangulo(usadaIz,temp,sostenRival);
				if(l<resta)
				{
					resta=l;
				}
			}
			resultado[0]=repar+resta;
		}
		return resultado;
	}

	public int[] picoMed(Carta piso,Carta carta, Carta sostenRival,Juego tablero, ArrayList<Integer> lista, int j,ArrayList<Carta>baraja,Coordenada [] coordenadas)
	{
		int [] resultado={-100,-100};
		if(lista.get(j)==2&&lista.size()==0)
		{
			int repar=definirTriangulo(piso,carta,sostenida);
			Carta temp=tablero.getCarta(coordenadas[0].getI(),coordenadas[0].getJ()-2);
			int []resta=this.escoger(carta, sostenida, sostenRival, baraja.get(1), temp);
			Carta temp1=tablero.getCarta(coordenadas[1].getI(),coordenadas[1].getJ()+2);
			int []resta1=this.escoger(carta, sostenida, sostenRival, baraja.get(1), temp1);
			if(resta[0]<resta1[1])
			{
				resultado[0]=repar-resta[0];
				resultado[1]=resta[1];
			}
			else
			{
				resultado[0]=repar-resta[1];
				if(resta1[1]==1)
				{
					resultado[1]=0;
				}
				else
				{
					resultado[1]=1;
				}
			}

		}
		return resultado;
	}

	// en la primera posicion va el valor de la jugada y en la segunda uno si la sostenida va en la derecha y 0 si va la arrastrada
	public int[] escoger(Carta carta, Carta sos, Carta sostenRival, Carta arrast,Carta temp)
	{
		int resultado[]=new int[2];
		int j=definirTriangulo(carta, temp, arrast);
		int i=definirTriangulo(carta, temp, sostenRival);
		int l=definirTriangulo(sos, temp, sostenRival);
		int m=definirTriangulo(sos, temp, arrast);

		int menor;
		if(j<i)
		{
			menor=j;
		}
		else
		{
			menor=i;
		}
		int men;
		if(l<m)
		{
			men=l;
		}
		else
		{
			men=m;
		}
		if(men>menor)
		{
			menor=men;
		}
		resultado[0]=menor;
		if(menor==j||menor==m)
		{
			resultado[1]=0;
		}
		else if(menor==i||menor==l)
		{
			resultado[1]=1;
		}
		return resultado;
	}

	public int definirTriangulo(Carta a, Carta b, Carta c)
	{
		int puntaje=a.getNumero()+b.getNumero()+c.getNumero();
		char col;
		if((a.getRango()==b.getRango())||(a.getRango()==c.getRango()))
		{
			col=a.getRango();
		}
		else
		{
			col=b.getRango();
		}
		if (col!=color)
		{
			puntaje=-puntaje;
		}
		return puntaje;
	}

	public String getNombre()
	{
		return nombre;
	}

	public char getColor()
	{
		return color;
	}

	public void ingresarPuntaje(int puntaje)
	{
		this.puntaje=puntaje;
	}

	public void cambioTurno()
	{
		turno=!turno;
	}

	public boolean getTurno()
	{
		return turno;
	}

	public void setSostenida(Carta j)
	{
		sostenida=j;
	}

	public int getPuntaje()
	{
		return puntaje;
	}

}