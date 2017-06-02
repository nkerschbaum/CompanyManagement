package application;

import java.io.Serializable;

import java.util.*;

import java.io.*;

public class Firma 
	implements Serializable
{

	private static final long serialVersionUID = 1L;
	private ArrayList<Mitarbeiter> mitarbeiter;
    
    public Firma() {
        mitarbeiter = new ArrayList<Mitarbeiter>();
        add(new Arbeiter(20, 30, true,  1990, "Fritz", 1960));
        add(new Angestellter(3000, false, 2000, "Susi", 1970));
        add(new Arbeiter(10, 10, true,  1995, "Hans", 1960));
        add(new Freelancer(35000, false, 2000, "Max", 1980));
        add(new Angestellter(2500, true, 2000, "Alex", 1970));
        add(new Freelancer(25000, true, 1970, "Martin", 1940));
        add(new Arbeiter(20, 20, false, 2000, "Werner", 1960));
    }
    
    public void add(Mitarbeiter neu) {
        mitarbeiter.add(neu);
    }
    
    public Mitarbeiter getFirstMitarbeiter() {
    	if (mitarbeiter != null && mitarbeiter.size() > 0) {
    		return mitarbeiter.get(0);
    	}
    	return null;
    }
    
    public ArrayList<Integer> getAllIDs() {
    	ArrayList<Integer> al = new ArrayList<>();
    	for (Mitarbeiter m : mitarbeiter) {
    		al.add(new Integer(m.getId()));
    	}
    	return al;
    }
    
    public Mitarbeiter getMitarbeiter(int ID) {
    	for (Mitarbeiter m : mitarbeiter) {
    		if (m.getId() == ID) {
    			return m;
    		}
    	}
    	return null;
    }
    
    public String toString() {
        String erg;
        
        erg = "";
        for (Mitarbeiter m: mitarbeiter) {
            //System.out.println(m.toString());
            erg = erg + m.toString() + "\n";
        }
        return erg;
    }

    public double getKosten() {
        double erg;
        
        erg = 0;
        for (Mitarbeiter m: mitarbeiter) {
            System.out.println(m.getKosten());
            erg = erg + m.getKosten();
        }
        return erg;
    }
    
    public void importFirma(String filename) 
    	throws FirmaException
    {
    	
    	mitarbeiter.clear();
    	BufferedReader reader = null;
    	String line = new String("");
    	try {
    		reader = new BufferedReader(new FileReader(filename));
    		line = reader.readLine();
    		while (line != null) {
    			
    			String[] values = line.split(";");
    			Mitarbeiter m = null;
    			if (values.length > 1) {
    				String maType = values[0];
    				if (maType.equals("Arbeiter")) {
    					m = new Arbeiter(line);
    				} else if (maType.equals("Angestellter")) {
    					m = new Angestellter(line);
    				} else if (maType.equals("Freelancer")) {
    					m = new Freelancer(line);
    				} 
    			}
    			if (m != null) {
    				add(m);
    			}

    			line = reader.readLine();
    		}
    	} catch (FileNotFoundException e) {
    		throw new FirmaException("Fehler beim Lesen der Datei " + filename + ".");
    	} catch (IOException e) {
    		String msg = new String("Fehler beim Lesen der Datei " + filename + ". ");
    		if (line != null) {
    			msg += "Fehler in Zeile: " + line;
    		}
    		FirmaException f = new FirmaException(msg);
    		throw f;
    	} finally {
    		if (reader != null) {
    			try {
    				reader.close();
    			} catch (IOException e) {
    				throw new FirmaException("Fehler beim Schliessen der Datei " + filename + ".");
    			}
    		}
    	}
    	
    }
    
    
    public void exportFirma(String filename) 
    		throws FirmaException
    {
    	   	
    	FileWriter fw;
    	BufferedWriter bw = null;
    	try {
	    	fw = new FileWriter(filename);
	    	bw = new BufferedWriter(fw);
	    	String str = new String();
	    	for (Mitarbeiter m: mitarbeiter) {
	    		str += m.toFile() + "\n";
	        }
	    	bw.write(str);
    	} catch (IOException e) {
    		e.printStackTrace();
    		throw new FirmaException("Fehler beim Schreiben in die Datei " + filename + ".");
    	} finally {
    		if (bw != null) {
    			try {
    				bw.close();
    			} catch (IOException e) {
    				throw new FirmaException("Fehler beim Schliessen der Datei " + filename + ".");
    			}
    		}
    	}
    }
    
   
}












