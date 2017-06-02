package application;

import java.io.Serializable;
 
public class Arbeiter 
	extends Mitarbeiter implements Serializable
{

	private static final long serialVersionUID = 1L;
	private double stundenlohn;
    private int stunden;
    private boolean facharbeiter;

    public Arbeiter()
    {
        super();
        setStundenlohn(20);
        setStunden(35);
        setFacharbeiter(true);
    }

    public Arbeiter(double stundenlohn, int stunden, boolean facharbeiter, int seit, String name, int geburtsjahr)
    {
        super(seit, name, geburtsjahr);
        setStundenlohn(stundenlohn);
        setStunden(stunden);
        setFacharbeiter(facharbeiter);
    }
    
    public Arbeiter(String str) throws FirmaException
    {
    	super(str);
    	if (str != null) {
    		String[] values = str.split(";");
    		if (values.length == 8) {
    			try {
    				setStundenlohn(Double.parseDouble(values[5]));
    				setStunden(Integer.parseInt(values[6]));
    				setFacharbeiter(Boolean.parseBoolean(values[7]));
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
    
    

    public void setStundenlohn(double stundenlohn)
    {
        this.stundenlohn = stundenlohn;
    }

    public void setStunden(int stunden)
    {
        this.stunden = stunden;
    }

    public void setFacharbeiter(boolean facharbeiter)
    {
        this.facharbeiter = facharbeiter;
    }

    public double getStundenlohn()
    {
        return stundenlohn;
    }

    public int getStunden()
    {
        return stunden;
    }

    public boolean getFacharbeiter()
    {
        return facharbeiter;
    }
    
    public double getGeld()
    {
        if (facharbeiter == true)
        {
            return stundenlohn * stunden + 200;
        }
        else
        {
            return stundenlohn * stunden;
        }
    }
    
    public double getKosten()
    {
        return getGeld() + 200;
    }

    public String toString()
    {
        if (facharbeiter == true)
        {
            return super.toString() + ", " + stunden + " Stunden" + stundenlohn + " Euro - Facharbeiter ";
        }
        else
        {
            return super.toString() + ", " + stunden + " Stunden" + stundenlohn + " Euro - Hilfsarbeiter ";
        }
    }
    
    // csv Darstellung
    public String toFile()
    {
    	return "Arbeiter" + ";" + super.toFile() + ";" + stundenlohn + ";" + stunden + ";" + facharbeiter;
    }
}
