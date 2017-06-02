package application;

import java.io.Serializable;

public abstract class Person 
	implements Serializable
{

	private static final long serialVersionUID = 1L;
	private String name;
    private int geburtsjahr;

    public Person() {
        setName("Andreas");
        setGeburtsjahr(1979);
    }

    public Person(String name, int geburtsjahr) {
        setName(name);
        setGeburtsjahr(geburtsjahr);
    }

    public Person(String str) throws FirmaException {
    	if (str != null) {
    		String[] values = str.split(";");
    		if (values.length >= 3) {
    			try {
    				// values[0] enthält Typ des Mitarbeiter
    				setName(values[1]);
    				setGeburtsjahr(Integer.parseInt(values[2]));
    			} catch (NumberFormatException e) {
    				throw new FirmaException("Fehler: ungültige Werte in String für Person");
    			}
    			
    		} else {
    			throw new FirmaException("Fehler: ungültige Anzahl an Werten in String für Person");
    		}
    	} else {
    		throw new FirmaException("Fehler: String ist null");
    	}
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public void setGeburtsjahr(int geburtsjahr) {
        this.geburtsjahr = geburtsjahr;
    }

    public String getName() {
        return name;
    }

    public int getGeburtsjahr() {
        return geburtsjahr;
    }

    public String toString() {
        return name + ", Geb.: " + geburtsjahr;
    }
    
    public String toFile() {
    	return name + ";" + geburtsjahr;
    }
    
}
