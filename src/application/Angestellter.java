package application;

import java.io.Serializable;
import java.util.*;

public class Angestellter 
	extends Mitarbeiter implements Serializable
{

	private static final long serialVersionUID = 1L;
	private double gehalt;
    private boolean mannlich;

    public Angestellter()
    {
        super();
        setGehalt(2500);
        setMannlich(true);
    }

    public Angestellter(double gehalt, boolean mannlich, int seit, String name, int geburtsjahr)
    {
        super(seit, name, geburtsjahr);
        setGehalt(gehalt);
        setMannlich(mannlich);
    }
    
    
    public Angestellter(String str) throws FirmaException
    {
    	super(str);
    	if (str != null) {
    		String[] values = str.split(";");
    		if (values.length == 7) {
    			try {
    				setGehalt(Double.parseDouble(values[5]));
    				setMannlich(Boolean.parseBoolean(values[6]));
    			} catch (NumberFormatException e) {
    				throw new FirmaException("Fehler: ungültige Werte in String für Arbeiter");
    			}
    			
    		} else {
    			throw new FirmaException("Fehler: ungültige Anzahl an Werten in String für Arbeiter");
    		}
    	} else {
    		throw new FirmaException("Fehler: String ist null");
    	}
    }

    public void setGehalt(double gehalt)
    {
        this.gehalt = gehalt;
    }

    public void setMannlich(boolean mannlich)
    {
        this.mannlich = mannlich;
    }

    public double getGehalt()
    {
        return gehalt;
    }

    public boolean getMannlich()
    {
        return mannlich;
    }
    
    public double getGeld()
    {
        GregorianCalendar heute;
        
        heute = new GregorianCalendar();
        if (getSeit() <= heute.get(Calendar.YEAR)-10)
        {
            return gehalt + 100;
        }
        else
        {
            return gehalt;
        }
    }
    
    public double getKosten()
    {
        return 2*getGeld();
    }

    public String toString()
    {
        if (mannlich)
        {
            return super.toString() + " " + gehalt + " Euro" + " Mann";
        }
        else
        {
            return super.toString() + " " + gehalt + " Euro" + " Frau";
        }
    }
    
    // csv Darstellung
    public String toFile()
    {
    	return "Angestellter" + ";" + super.toFile() + ";" + gehalt + ";" + mannlich;
    }
}
