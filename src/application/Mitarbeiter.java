
package application;

import java.io.Serializable;

public abstract class Mitarbeiter 
	extends Person implements Serializable
{

	private static final long serialVersionUID = 1L;
	private int id; 
    private static int nextId = 1000;
    private int seit;

    public Mitarbeiter() {
        super();
        setId();
        setSeit(2000);
    }

    public Mitarbeiter(int seit, String name, int geburtsjahr) {
        super(name, geburtsjahr);
        setId();
        setSeit(seit);
    }
    
    public Mitarbeiter(String str) throws FirmaException {
    	super(str);
    	if (str != null) {
    		String[] values = str.split(";");
    		if (values.length >= 5) {
    			try {
    				setId(Integer.parseInt(values[3]));
    				setSeit(Integer.parseInt(values[4]));
    			} catch (NumberFormatException e) {
    				throw new FirmaException("Fehler: ungültige Werte in String für Mitarbeiter");
    			}
    			
    		} else {
    			throw new FirmaException("Fehler: ungültige Anzahl an Werten in String für Mitarbeiter");
    		}
    	} else {
    		throw new FirmaException("Fehler: String ist null");
    	}

    }    
    
    // Zum deserialisieren von Mitarbeiter Objekten
    private void setId(int id) {
    	this.id = id;
    }
    
    
    public void setId() {
        id = getNextId();
        setNextId(getNextId() + 1);
    }
    
    public int getId() {
    	return id;
    }

    public void setSeit(int seit) {
        this.seit = seit;
    }

    public int getSeit() {
        return seit;
    }

    public String toString() {
        return super.toString() + ", id: " + id + ", seit: " + seit;
    }
    
    public String toFile() {
    	return super.toFile() + ";" + id + ";" + seit;
    }
    
    public abstract double getGeld();
    
    public abstract double getKosten();

	public static int getNextId()
	{
		return nextId;
	}

	public static void setNextId(int nextId)
	{
		Mitarbeiter.nextId = nextId;
	}
}
