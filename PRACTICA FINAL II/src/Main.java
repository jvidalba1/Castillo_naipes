import java.util.*;
public class Main
{
    ArrayList <Carta> baraja = new ArrayList <Carta>();
    
	public String aJugar(ArrayList <Carta> baraj, String nombre)
	{
        String res = "";
        int resCom = 0;
        baraja = baraj;
        Jugador a = null;
        Jugador b = null;

        if(nombre.equals("Axel"))
        {
            a=new Jugador("Axel",'R');
            b=new Jugador("Birgit",'B');
        }

        if(nombre.equals("Birgit"))
        {
            a=new Jugador("Birgit",'B');
            b=new Jugador("Axel",'R');
        }

		Juego juego=new Juego(a,b,baraja);
		juego.jugar();

		int puntajeA=a.getPuntaje();
		if(a.getSostenida()!=null)
		{
			if(a.getSostenida().getRango()==a.getColor())
			{
				puntajeA=puntajeA+a.getSostenida().getNumero();
			}
			else
			{
				puntajeA=puntajeA-a.getSostenida().getNumero();
			}
		}
		int puntajeB=b.getPuntaje();
		if(b.getSostenida()!=null)
		{
			if(b.getSostenida().getRango()==b.getColor())
			{
				puntajeB=puntajeB+b.getSostenida().getNumero();
			}
			else
			{
				puntajeB=puntajeB-b.getSostenida().getNumero();
			}
		}

        resCom = puntajeA - puntajeB;

        if(nombre.equals("Axel"))
        {
            if(resCom < 0)
            {
                int resCambio = puntajeB - puntajeA;
                res = "Axel pierde: " + resCambio;
            }

            if(resCom > 0)
            {
                res = "Axel gana: " + resCom;
            }
        }

        else if(nombre.equals("Birgit"))
        {
            if(resCom < 0)
            {
                int resCambio = puntajeB - puntajeA;
                res = "Birgit pierde: " + resCambio;
            }

            if(resCom > 0)
            {
                res = "Birgit gana: " + resCom;
            }
        }

        if (resCom == 0)
        {
            res = "Axel y Birgit empataron";
        }
        resCom = 0;
        return res;
	}

    /**
    * @param args the command line arguments
    */
    public static void main(String args[])
    {
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                new Interfaz().setVisible(true);
            }
        });
    }
}