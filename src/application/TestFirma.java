package application;

public class TestFirma {

	public static void main(String[] args) {
		
		/*
		Firma firma = new Firma();
		System.out.println("Firma: " + firma);
	
		try {
			firma.exportFirma("firma.csv");
		} catch (FirmaException e) {
			System.err.println("Fehler: " + e.getStackTrace());
		}
		*/
		
		Firma deserializedFirma = new Firma();
		try {
			deserializedFirma.importFirma("firma.csv");
		} catch (FirmaException e) {
			System.err.println("Fehler beim Einlesen der Firma: " + e.getStackTrace());
		}
		System.out.println("Eingelesene Firma: " + deserializedFirma);
	}
}
