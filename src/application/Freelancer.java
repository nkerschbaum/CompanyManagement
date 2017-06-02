package application;

import java.io.Serializable;

public class Freelancer 
	extends Mitarbeiter implements Serializable
{

	private static final long serialVersionUID = 1L;
	private double preis;
    private boolean fertig;

    public Freelancer()
    {
        super();
        setPreis(50000);
        setFertig(false);
    }

    public Freelancer(double preis, boolean fertig,  int seit, String name, int geburtsjahr)
    {
        super(seit, name, geburtsjahr);
        setPreis(preis);
        setFertig(fertig);
    }
    
    
    public Freelancer(String str) throws FirmaException 
    {
    	super(str);
    	if (str != null) {
    		String[] values = str.split(";");
    		if (values.length == 7) {
    			try {
    				setPreis(Double.parseDouble(values[5]));
    				setFertig(Boolean.parseBoolean(values[6]));
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

    public void setPreis(double preis)
    {
        this.preis = preis;
    }

    public void setFertig(boolean fertig)
    {
        this.fertig = fertig;
    }

    public double getPreis()
    {
        return preis;
    }

    public boolean getFertig()
    {
        return fertig;
    }
    
    public double getGeld()
    {
        double p;
        if (fertig == false)
        {
            return 0;
        }
        else
        {
            p = preis;
            preis = 0;
            fertig = false;
            return p;
        }
    }
    
    public double getKosten()
    {
        return 0;
    }
    
    public String toString()
    {
        return super.toString() + " " + preis + "\t" + fertig;
    }
    
    // csv Darstellung
    public String toFile()
    {
    	return "Freelancer" + ";" + super.toFile() + ";" + preis + ";" + fertig;
    }
}
