import java.util.*;

public class Juego {

	private Carta juego [][];
	private ArrayList <Integer> pisos;
	private ArrayList <Integer> picos;
	private ArrayList <Carta> baraja;
	private Jugador a;
	private Jugador b;
	private ArrayList <Coordenada> pisosCoor;
	private ArrayList <Coordenada[]> picosCoor;

	public Juego(Jugador a, Jugador b,ArrayList<Carta> baraja)
	{
		juego = new Carta [7][15];
		Carta m = new Carta (0,'l');
		int i=0;
		int j=0;
		while (i<7)
		{
			j=0;
			while (j<15)
			{
				juego [i][j]= m;
				j++;
			}
			i++;
		}
		this.a=a;
		this.b=b;
		this.baraja=baraja;

		pisosCoor=new ArrayList<Coordenada>();

		Coordenada temp=new Coordenada(1,3);
		pisosCoor.add(temp);
		Coordenada temp1=new Coordenada(1, 7);
		pisosCoor.add(temp1);
		Coordenada temp2=new Coordenada(1, 11);
		pisosCoor.add(temp2);
		Coordenada temp3=new Coordenada(3, 5);
		pisosCoor.add(temp3);
		Coordenada temp4=new Coordenada(3, 9);
		pisosCoor.add(temp4);
		Coordenada temp5=new Coordenada(5, 7);
		pisosCoor.add(temp5);


		picosCoor=new ArrayList<Coordenada[]>();
		Coordenada temp6=new Coordenada(2, 2);
		Coordenada temp7=new Coordenada(2,4);
		Coordenada [] coor={temp6,temp7};
		picosCoor.add(coor);
		Coordenada temp8=new Coordenada(2, 6);
		Coordenada temp9=new Coordenada(2, 8);
		Coordenada [] coor1={temp8,temp9};
		picosCoor.add(coor1);
		Coordenada temp10=new Coordenada(2, 10);
		Coordenada temp11=new Coordenada(2, 12);
		Coordenada [] coor2={temp10,temp11};
		picosCoor.add(coor2);
		Coordenada temp12=new Coordenada(4, 4);
		Coordenada temp13=new Coordenada(4, 6);
		Coordenada [] coor3={temp12,temp13};
		picosCoor.add(coor3);
		Coordenada temp14=new Coordenada(4, 8);
		Coordenada temp15=new Coordenada(4, 10);
		Coordenada [] coor4={temp14,temp15};
		picosCoor.add(coor4);
		Coordenada temp16=new Coordenada(6, 6);
		Coordenada temp17=new Coordenada(6, 8);
		Coordenada [] coor5={temp16,temp17};
		picosCoor.add(coor5);

		picos=new ArrayList<Integer>();
	}

	public void iniciarJuego()
	{
		int i=0;
		while(i<15)
		{
			juego[0][i]=baraja.get(0);
			baraja.remove(0);
			i=i+2;
		}
	}


	public void ingresarCarta(int fila, int columna, Carta ingresada)
	{
		juego[fila][columna]=ingresada;
	}

	public boolean pueFormarPiso()
	{
		pisos=new ArrayList<Integer>();
		int i=0;
		boolean r=false;
		if (juego[1][3].getNumero()==0&&juego[1][3].getRango()=='l')
		{
			r=true;
			pisos.add(i,1);
			i++;
		}
		if (juego[1][7].getNumero()==0&&juego[1][7].getRango()=='l')
		{
			r=true;
			pisos.add(i,2);
			i++;
		}
		if (juego[1][11].getNumero()==0&&juego[1][11].getRango()=='l')
		{
			r=true;
			pisos.add(i,3);
			i++;
		}
		if(((juego[2][4].getNumero()!=0&&juego[2][4].getRango()!='l')&&(juego[2][6].getNumero()!=0&&juego[2][6].getRango()!='l'))
				&&(juego[3][5].getNumero()==0)&&(juego[3][5].getRango()=='l'))
		{
			r=true;
			pisos.add(i,4);
			i++;
		}
		if(((juego[2][8].getNumero()!=0&&juego[2][8].getRango()!='l')&&(juego[2][10].getNumero()!=0&&juego[2][10].getRango()!='l'))
				&&(juego[3][9].getNumero()==0)&&(juego[3][9].getRango()=='l'))
		{
			r=true;
			pisos.add(i,5);
			i++;
		}
		if(((juego[4][6].getNumero()!=0&&juego[4][6].getRango()!='l')&&(juego[4][8].getNumero()!=0&&juego[4][8].getRango()!='l'))
				&&(juego[5][7].getNumero()==0)&&(juego[5][7].getRango()=='l'))
		{
			r=true;
			pisos.add(i,6);
			i++;
		}
		return r;
	}


	public boolean pueFormarPico()
	{
		picos=new ArrayList<Integer>();
		int i=0;
		boolean r=false;
		if((juego[1][3].getNumero()!=0&&juego[1][3].getRango()!='l')&&((juego[2][2].getNumero()==0&&
				juego[2][2].getRango()=='l')&&(juego[2][4].getNumero()==0 && juego[2][4].getRango()=='l')))
		{
			r=true;
			picos.add(i,1);
			i++;
		}
		if((juego[1][7].getNumero()!=0&&juego[1][7].getRango()!='l')&&((juego[2][6].getNumero()==0&&
				juego[2][6].getRango()=='l')&&(juego[2][8].getNumero()==0 && juego[2][8].getRango()=='l')))
		{
			r=true;
			picos.add(i,2);
			i++;
		}
		if((juego[1][11].getNumero()!=0&&juego[1][11].getRango()!='l')&&((juego[2][10].getNumero()==0&&
				juego[2][10].getRango()=='l')&&(juego[2][12].getNumero()==0 && juego[2][12].getRango()=='l')))
		{
			r=true;
			picos.add(i,3);
			i++;
		}
		if((juego[3][5].getNumero()!=0&&juego[3][5].getRango()!='l')&&((juego[4][4].getNumero()==0&&
				juego[4][4].getRango()=='l')&&(juego[4][6].getNumero()==0 && juego[4][6].getRango()=='l')))
		{
			r=true;
			picos.add(i,4);
			i++;
		}
		if((juego[3][9].getNumero()!=0&&juego[3][9].getRango()!='l')&&((juego[4][8].getNumero()==0&&
				juego[4][8].getRango()=='l')&&(juego[4][10].getNumero()==0 && juego[4][10].getRango()=='l')))
		{
			r=true;
			picos.add(i,5);
			i++;
		}
		if((juego[5][7].getNumero()!=0&&juego[5][7].getRango()!='l')&&((juego[6][6].getNumero()==0&&
				juego[6][6].getRango()=='l')&&(juego[6][8].getNumero()==0 && juego[6][8].getRango()=='l')))
		{
			r=true;
			picos.add(i,6);
			i++;
		}
		return r;
	}

	public ArrayList <Integer> getListPicos()
	{
		return picos;
	}


	public ArrayList <Integer> getListPisos()
	{
		return pisos;
	}

	public void postJugada()
	{
		picos=null;
		pisos=null;
	}

	public Carta[][] getTablero()
	{
		return juego;
	}

	public ArrayList <Coordenada> getPisos()
	{
		return pisosCoor;
	}

	public ArrayList <Coordenada[]> getPicos()
	{
		return picosCoor;
	}

	public Carta getCarta(int i, int j)
	{
		return juego[i][j];
	}


	public void definirPuntaje()
	{
		char uno=a.getColor();
		int puntajeA=0;
		char dos=b.getColor();
		int puntajeB=0;

		int i=0;
		while(i<pisosCoor.size())
		{
			Carta temp=getCarta(pisosCoor.get(i).getI(),pisosCoor.get(i).getJ());
			if(temp.getRango()!='l')
			{
				char temporal;
				Carta s=getCarta(pisosCoor.get(i).getI()-1,pisosCoor.get(i).getJ()-1);
				Carta l=getCarta(pisosCoor.get(i).getI()-1,pisosCoor.get(i).getJ()+1);
				int m= s.getNumero()+l.getNumero()+temp.getNumero();
				if(s.getRango()==l.getRango()||s.getRango()==temp.getRango())
				{
					temporal=s.getRango();
				}
				else
				{
					temporal=l.getRango();
				}

				if(temporal==uno)
				{
					puntajeA=puntajeA+m;
				}
				else
				{
					puntajeB=puntajeB+m;
				}
			}
			i++;
		}

		i=0;
		while(i<picosCoor.size())
		{
			Carta temp=getCarta(picosCoor.get(i)[0].getI(),picosCoor.get(i)[0].getJ());
			if(temp.getRango()!='l')
			{
				char temporal;
				Carta s=getCarta(picosCoor.get(i)[1].getI(),picosCoor.get(i)[1].getJ());
				Carta l=getCarta(picosCoor.get(i)[0].getI()-1,picosCoor.get(i)[0].getJ()+1);
				int m= s.getNumero()+l.getNumero()+temp.getNumero();
				if(s.getRango()==l.getRango()||s.getRango()==temp.getRango())
				{
					temporal=s.getRango();
				}
				else
				{
					temporal=l.getRango();
				}

				if(temporal==uno)
				{
					puntajeA=puntajeA+m;
				}
				else
				{
					puntajeB=puntajeB+m;
				}
			}
			i++;
		}

		a.ingresarPuntaje(puntajeA);
		b.ingresarPuntaje(puntajeB);
	}


	public void jugar()
	{
		iniciarJuego();
		Carta temp=getCarta(0,0);
		if(temp.getRango()==a.getColor())
		{
			a.cambioTurno();
		}
		else
		{
			b.cambioTurno();
		}

		while(baraja.size()!=0)
		{
			if(a.getTurno())
			{
				ArrayList <Integer> temporal=a.mejorJugada(this, baraja.get(0), baraja,b.getSostenida());
				if (temporal.size()==1||temporal.get(0)==0)
				{
					a.sostener(baraja.get(0));
				}
				else if(temporal.get(2)==0)
				{
					if(temporal.get(1)==1)
					{
						ingresarCarta(pisosCoor.get(temporal.get(0)-1).getI(), pisosCoor.get(temporal.get(0)-1).getJ(), a.getSostenida());
						a.setSostenida(baraja.get(0));
					}
					else
					{
						ingresarCarta(pisosCoor.get(temporal.get(0)-1).getI(), pisosCoor.get(temporal.get(0)-1).getJ(), baraja.get(0));
						int j=0;
					}
				}
				else
				{
					if(temporal.get(1)==1)
					{
						ingresarCarta(picosCoor.get(temporal.get(0)-1)[0].getI(),picosCoor.get(temporal.get(0)-1)[0].getJ(), a.getSostenida());
						ingresarCarta(picosCoor.get(temporal.get(0)-1)[1].getI(),picosCoor.get(temporal.get(0)-1)[1].getJ(), baraja.get(0));
					}
					else
					{
						ingresarCarta(picosCoor.get(temporal.get(0)-1)[0].getI(),picosCoor.get(temporal.get(0)-1)[0].getJ(), baraja.get(0));
						ingresarCarta(picosCoor.get(temporal.get(0)-1)[1].getI(),picosCoor.get(temporal.get(0)-1)[1].getJ(), a.getSostenida());
					}
					a.setSostenida(null);
				}
			}
			else
			{
				ArrayList <Integer> temporal=b.mejorJugada(this, baraja.get(0), baraja,a.getSostenida());
				if (temporal.size()==1||temporal.get(0)==0)
				{
					b.sostener(baraja.get(0));
				}
				else if(temporal.get(2)==0)
				{
					if(temporal.get(1)==1)
					{
						ingresarCarta(pisosCoor.get(temporal.get(0)-1).getI(), pisosCoor.get(temporal.get(0)-1).getI(), b.getSostenida());
						b.setSostenida(baraja.get(0));
					}
					else
					{
						ingresarCarta(pisosCoor.get(temporal.get(0)-1).getI(), pisosCoor.get(temporal.get(0)-1).getJ(), baraja.get(0));
					}
				}
				else
				{
					if(temporal.get(1)==1)
					{
						ingresarCarta(picosCoor.get(temporal.get(0)-1)[0].getI(),picosCoor.get(temporal.get(0)-1)[0].getJ(), b.getSostenida());
						ingresarCarta(picosCoor.get(temporal.get(0)-1)[1].getI(),picosCoor.get(temporal.get(0)-1)[1].getJ(), baraja.get(0));
					}
					else
					{
						ingresarCarta(picosCoor.get(temporal.get(0)-1)[0].getI(),picosCoor.get(temporal.get(0)-1)[0].getJ(), baraja.get(0));
						ingresarCarta(picosCoor.get(temporal.get(0)-1)[1].getI(),picosCoor.get(temporal.get(0)-1)[1].getJ(), b.getSostenida());
					}
					b.setSostenida(null);
				}
			}
			postJugada();
			a.cambioTurno();
			b.cambioTurno();
			baraja.remove(0);
		}

		definirPuntaje();
	}


}
