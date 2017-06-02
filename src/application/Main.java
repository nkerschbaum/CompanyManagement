package application;
	
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;


public class Main extends Application {
	
	private final int WIDTH = 800;
	private final int HEIGHT = 400;
	
	private Firma firma;
	private Mitarbeiter mitarbeiter;
	private boolean newEmployee;
	
	private static String filename = "firma.csv";
	
	private TextArea taRemarks;
	private TextField tfStatus;
	private ComboBox<Integer> cbId;
	private TextField tfName;
	private TextField tfYearOfBirth;
	private RadioButton rbM;
	private RadioButton rbF;
	private Spinner<Integer> spHourlyWage;
	private Spinner<Integer> spHoursEmployed;
	private CheckBox cbSkilledWorker;
	private TextField tfEmployedSince;
	private BufferedWriter bw;
	private PrintWriter fw;
	
	@Override
	public void start(Stage primaryStage) {
		firma = new Firma();
		// Application.setUserAgentStylesheet(STYLESHEET_MODENA);
		Application.setUserAgentStylesheet(STYLESHEET_CASPIAN);


		try {
			BorderPane root = new BorderPane();

	        // --- bottom (status) ---
	        Label lblStatus = new Label("Status");
			TextField tfStatus = new TextField();
			tfStatus.setPromptText("Status will be displayed here");
			tfStatus.setEditable(false);
			tfStatus.setPrefWidth(0.7*WIDTH);
			
			AnchorPane apBottom = new AnchorPane();
			apBottom.setPadding(new Insets(5, 3, 5, 7)); //oben, rechts, unten, links
			
			AnchorPane.setTopAnchor(lblStatus, 10.0);
			AnchorPane.setLeftAnchor(lblStatus, 10.0);
			AnchorPane.setRightAnchor(tfStatus, 10.0);
			AnchorPane.setLeftAnchor(tfStatus, 100.0);
			AnchorPane.setTopAnchor(tfStatus, 10.0);
			apBottom.getChildren().addAll(lblStatus, tfStatus);
			// HBox hboxBottom = new HBox();
			// hboxBottom.setPadding(new Insets (5, 5, 5, 5));
			// hboxBottom.setSpacing(10);
			// hboxBottom.getChildren().addAll(lblStatus, tfStatus);
			


	        // --- center ---
			SplitPane sp = new SplitPane();
			sp.setOrientation(Orientation.VERTICAL);
			sp.setPadding(new Insets (5, 10, 5, 5));
			
			sp.setDividerPositions(0.3f);
			
			GridPane gp = new GridPane();
			gp.setPadding(new Insets (5, 5, 5, 5));
			gp.setHgap(10);
			gp.setVgap(10);
			gp.setPrefWidth(2*WIDTH);
		    ColumnConstraints column1 = new ColumnConstraints();
		    column1.setPercentWidth(20);
		    ColumnConstraints column2 = new ColumnConstraints();
		    column2.setPercentWidth(40);
		    ColumnConstraints column3 = new ColumnConstraints();
		    column3.setPercentWidth(20);
		    ColumnConstraints column4 = new ColumnConstraints();
		    column4.setPercentWidth(20);
		    gp.getColumnConstraints().addAll(column1, column2, column3, column4);
			
			Label lblId = new Label("ID");
	        ComboBox<Integer> cbId = new ComboBox<Integer>();
	        cbId.getItems().addAll(0, 1, 2, 3);
	        cbId.setPromptText("Select ID");
			gp.add(lblId, 0, 0);
			gp.add(cbId, 1, 0);
			
			Label lblName = new Label("Name");
			TextField tfName = new TextField("Name");
			gp.add(lblName, 0, 1);
			gp.add(tfName, 1, 1);
			
			Label lblGender = new Label("Gender");
			ToggleGroup tg = new ToggleGroup();
			RadioButton rbM = new RadioButton("M  ");
			RadioButton rbF = new RadioButton("F");
			
			rbM.setToggleGroup(tg);
			rbF.setToggleGroup(tg);
			
			rbM.setSelected(true);
			HBox hbGender = new HBox();
			hbGender.getChildren().addAll(rbM, rbF);
			gp.add(lblGender, 0, 2);
			gp.add(hbGender, 1, 2);
			
			Label lblHourlyWage = new Label("Hourly Wage");
			Spinner<Integer> spHourlyWage = new Spinner<Integer>(5, 200, 12);
			spHourlyWage.setPrefWidth(80);
			
			gp.add(lblHourlyWage, 2, 0);
			gp.add(spHourlyWage, 3, 0);
			
			Label lblHoursEmployed = new Label("Hours Employed");
			Spinner<Integer> spHoursEmployed = new Spinner<Integer>(4, 40, 25);
			spHoursEmployed.setPrefWidth(80);
			
			gp.add(lblHoursEmployed, 2, 1);
			gp.add(spHoursEmployed, 3, 1);
			
			Label lblSkilledWorker = new Label("Skilled Worker");
			CheckBox cbSkilledWorker = new CheckBox();
			cbSkilledWorker.setSelected(true);
			gp.add(lblSkilledWorker, 2, 2);
			gp.add(cbSkilledWorker, 3, 2);
			
			Label lblYearOfBirth = new Label("Year of Birth");
			gp.add(lblYearOfBirth, 0, 3);
			lblYearOfBirth.setMinWidth(20);
			lblYearOfBirth.setPrefWidth(100);
			TextField tfYearOfBirth = new TextField();
			gp.add(tfYearOfBirth, 1, 3);
			
			Label lblEmployedSince = new Label("Employed Since");
			gp.add(lblEmployedSince, 2, 3);
			TextField tfEmployedSince = new TextField();
			gp.add(tfEmployedSince, 3, 3);
			
	        VBox vbCenter = new VBox();
	        Label lblRemarks = new Label("Remarks");
	        TextArea taRemarks = new TextArea();
	        taRemarks.setBorder(new Border(new BorderStroke(Color.GREEN, BorderStrokeStyle.SOLID, null, null)));
	        taRemarks.setMinHeight(0.3*HEIGHT);
	        taRemarks.setMaxHeight(3*HEIGHT);
	        taRemarks.setPrefHeight(3*HEIGHT);
	        taRemarks.setWrapText(true);
	        vbCenter.getChildren().addAll(lblRemarks, taRemarks);
	        
	        sp.getItems().addAll(gp, vbCenter);
	        SplitPane.setResizableWithParent(gp, Boolean.FALSE);
	      
		    					       
		    
		    
	        // --- left (buttons) ---
	        VBox vbLeft = new VBox();
	        Button btnLoad = new Button("Load");
	        btnLoad.setOnAction(e -> {
	        	int id = cbId.getValue().intValue();
	        	Mitarbeiter ma = firma.getMitarbeiter(id);
	        	if (ma != null) {
	        		tfName.setText(ma.getName());
	        		
	        		if (ma instanceof Arbeiter) {
	        			Arbeiter arb = (Arbeiter)ma;
	        			int hourlyRateInt = (int)arb.getStundenlohn();
	        			Integer intVal = new Integer(hourlyRateInt);
	        			spHourlyWage.getValueFactory().setValue(intVal);
	        			
	        			int hoursEmployed = arb.getStunden();
	        			intVal = new Integer(hoursEmployed);
	        			spHoursEmployed.getValueFactory().setValue(intVal);
	        			
	        			cbSkilledWorker.setSelected(arb.getFacharbeiter());
	      
	        			tfYearOfBirth.setText(Integer.toString(arb.getGeburtsjahr()));
	        			tfEmployedSince.setText(Integer.toString(arb.getSeit()));
	        			tfStatus.setText("Loaded Mitarbeiter with id " + arb.getId() + " successfully!");
	        		
	        		}
	        		
	        		if (ma instanceof Angestellter) {
	        			Angestellter ang = (Angestellter)ma;
	        			if (ang.getMannlich() == true) {
	        				rbM.setSelected(true);
	        			}
	        			else {
	        				rbF.setSelected(true);
	        			}
	        			
	        			tfYearOfBirth.setText(Integer.toString(ang.getGeburtsjahr()));
	        			tfEmployedSince.setText(Integer.toString(ang.getSeit()));
	        			tfStatus.setText("Loaded Mitarbeiter with id " + ang.getId() + " successfully!");
	        		}
	        			
	        	}
	        });
	        btnLoad.setMinWidth(100);
	        
	        Button btnSave = new Button("Save");
	        btnSave.setOnAction(e -> {
	        	int id = cbId.getValue().intValue();
	        	Mitarbeiter ma = firma.getMitarbeiter(id);
	        	if (ma != null) {
	        		ma.setName(tfName.getText());
	        		
	        		if (ma instanceof Arbeiter) {
	        			Arbeiter arb = (Arbeiter)ma;
	        			
	        			arb.setStundenlohn(spHourlyWage.getValueFactory().getValue());
	        			arb.setStunden(spHoursEmployed.getValueFactory().getValue());
	        			arb.setFacharbeiter(cbSkilledWorker.isSelected());
	        			arb.setGeburtsjahr(Integer.parseInt(tfYearOfBirth.getText()));
	        			arb.setSeit(Integer.parseInt(tfEmployedSince.getText()));
	        			tfStatus.setText("Saved Mitarbeiter with id " + arb.getId() + " successfully!");
	    	        	
	        		}
	        		
	        		if (ma instanceof Angestellter) {
	        			Angestellter ang = (Angestellter)ma;
	        			
	        			if (rbM.isSelected()) {
	        				ang.setMannlich(true);
	        			}
	        			else {
	        				ang.setMannlich(false);
	        			}
	        			
	        			ang.setGeburtsjahr(Integer.parseInt(tfYearOfBirth.getText()));
	        			ang.setSeit(Integer.parseInt(tfEmployedSince.getText()));
	        			tfStatus.setText("Saved Mitarbeiter with id " + ang.getId() + " successfully!");
	        		}
	        			
	        	}
	        });
	        btnSave.setMinWidth(100);
	        
	        Button btnNew = new Button("New");
	        btnNew.setOnAction(e -> {
	    		boolean proceed = true;
	        	if (mitarbeiter != null) {
	    			ButtonType btnOk = new ButtonType("OK", ButtonData.OK_DONE);
	    			ButtonType btnCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
	    			Dialog<ButtonType> dialog = new Dialog<>();
	    			dialog.setContentText("Current data will be purged. Press 'Cancel' to abort.");
	    			dialog.getDialogPane().getButtonTypes().add(btnCancel);
	    			dialog.getDialogPane().getButtonTypes().add(btnOk);

	    			// classical approach
	    			Optional<ButtonType> result = dialog.showAndWait();
	    			if (result.isPresent()) {
	    				if (result.get().getButtonData() == ButtonData.OK_DONE) {
	    					tfStatus.setText("Purging old data and creating new employee.");
	    				} else if (result.get().getButtonData() == ButtonData.CANCEL_CLOSE) {
	    					proceed = false;
	    					tfStatus.setText("Operation cancelled!");
	    				}
	    			}
	    			
	    			
	    			// (semi) lambda approach
	    			/*
	    			dialog.showAndWait().ifPresent(response -> {
	    				if (response == ButtonType.OK) {
	    					tfStatus.setText("Purging old data and creating new employee.");
	    				} else if (response == ButtonType.CANCEL) {
	    					proceed = false;
	    					tfStatus.setText("Operation cancelled!");
	    				}
	    			});
	    			*/
	    			
	        	}
	    		if (proceed) {
	    			tfName.clear();
	    			tfYearOfBirth.clear();
	    			rbM.setSelected(true);
	    			spHourlyWage.getValueFactory().setValue(10);
	    			spHoursEmployed.getValueFactory().setValue(20);
	    			cbSkilledWorker.setSelected(false);
	    			tfEmployedSince.setText(Integer.toString(LocalDate.now().getYear()));
	    			mitarbeiter = new Arbeiter();
	    			newEmployee = true;
	    		}
	        	
	    	});
	        btnNew.setMinWidth(100);
	        
	        vbLeft.setPadding(new Insets (5, 5, 5, 5));
	        vbLeft.setSpacing(10);
	        vbLeft.getChildren().addAll(btnLoad, btnSave, btnNew);

	        
			// --- menu ---
	        MenuBar menuBar = new MenuBar();
	        Menu menuFile = new Menu("File");
	        MenuItem miLoad = new MenuItem("Load Data from File");
	        miLoad.setOnAction(e -> { 
	        							firma = new Firma();
	        							if (firma != null) {
	        								try { firma.importFirma(filename); } 
	        								catch (FirmaException fe) { fe.printStackTrace(); } 
	        							}
	        							taRemarks.setText(firma.toString());
	        							System.out.println(firma.getFirstMitarbeiter());
	        							ArrayList<Integer> al = firma.getAllIDs();
	        							cbId.getItems().clear();
	        							for (Integer i : al) {
	        								cbId.getItems().add(i);
	        							}
	        							tfStatus.setText("Loaded File successfully!");
	        						} );
        	
	        

	        MenuItem miSave = new MenuItem("Save Data to File");
	        miSave.setOnAction(e -> {
	            	if (firma != null) {
	            		try {
	            			firma.exportFirma(filename);
	            		} catch (FirmaException fe) {
	            			fe.printStackTrace();
	            		}
	            	}
	            	tfStatus.setText("Saved File successfully!");
	        });
        	
	        
	        MenuItem miExport = new MenuItem("Export...");
	        miExport.setOnAction(e -> {      
	        	fw = null;
	            if (firma != null) {
	        	try (BufferedWriter bw = new BufferedWriter(new PrintWriter("mitarbeiter.txt"))){
	            	bw.write("Name: " + tfName.getText());
	                bw.newLine();
	                bw.write("Geb.:" + tfYearOfBirth.getText());
	                bw.newLine();
	                int id = cbId.getValue().intValue();
	                bw.write("ID: " + Integer.toString(id));
	                bw.newLine();
	                bw.write("Seit:" + tfEmployedSince.getText());
	                bw.newLine();
	                bw.write("Stundenlohn: " + Integer.toString(spHourlyWage.getValueFactory().getValue()));
	                bw.newLine();
	                bw.write("Stunden: " + Integer.toString(spHoursEmployed.getValueFactory().getValue()));
	                bw.newLine();
	                if (cbSkilledWorker.isSelected()) {
	                	bw.write("Facharbeiter");
	                }
	                else {
	                	bw.write("Hilfsarbeiter");
	                }
	            } catch (IOException e1) {
	                e1.printStackTrace();
	            }
	            finally {
	            if (bw != null) {
	            	fw.close();
	            }
	        }
	            tfStatus.setText("Export successfull!");
	        }
	        
	        else {
	        	tfStatus.setText("No Employee selected!");
	        }
	        });
		    
	        MenuItem miClose = new MenuItem("Close");
	        miClose.setOnAction(e -> {
	        	Stage stage = primaryStage;
			    // do what you have to do
			    stage.close();
	        });
		    
	        
	        menuFile.getItems().addAll(miLoad, miSave, miExport, miClose);
	        
	        Menu menuEdit = new Menu("Edit");
	        
	        Menu menuAbout = new Menu("About");
	        MenuItem miHelp = new MenuItem("Help");
	        miHelp.setOnAction(e -> {
	        	getHostServices().showDocument("help.txt");
	        });
	        MenuItem miURL = new MenuItem("Website");
	        miURL.setOnAction(e -> {
	        	getHostServices().showDocument("http://www.kerschbaum.info");
	        });
	        
	        
	        menuAbout.getItems().addAll(miHelp, miURL);
	        
	        menuBar.getMenus().addAll(menuFile, menuEdit, menuAbout);
	        

	        
	        // set all items to border pane
		    		
		    root.setTop(menuBar);
			root.setLeft(vbLeft);
			root.setCenter(sp);
			// root.setBottom(hboxBottom);
			root.setBottom(apBottom);
			root.setMinSize(0.8*WIDTH, 0.8*HEIGHT);
			root.setMaxSize(2*WIDTH, 1.5*HEIGHT);
			
			Scene scene = new Scene(root, WIDTH, HEIGHT);
			
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Firma");
			primaryStage.getIcons().add(new Image("file:kbit-logo.png"));
			primaryStage.setMinHeight(0.8*HEIGHT);
			primaryStage.setMaxHeight(1.5*HEIGHT);
			primaryStage.setMinWidth(0.8*WIDTH);
			primaryStage.setMaxWidth(2*WIDTH);
			
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	private void loadEmployee() {
	   	int id = cbId.getValue().intValue();
    	mitarbeiter = firma.getMitarbeiter(id);
    	if (mitarbeiter != null) {
    		
    		if (mitarbeiter instanceof Arbeiter) {
    			Arbeiter arb = (Arbeiter)mitarbeiter;
    			
    			tfName.setText(arb.getName());
    			tfYearOfBirth.setText(Integer.toString(arb.getGeburtsjahr()));
    			
    			rbM.setSelected(true);
    			int hourlyRateInt = (int)arb.getStundenlohn();
    			Integer intVal = new Integer(hourlyRateInt);
    			spHourlyWage.getValueFactory().setValue(intVal);
    			
    			int hoursEmployed = arb.getStunden();
    			intVal = new Integer(hoursEmployed);
    			spHoursEmployed.getValueFactory().setValue(intVal);
    			
    			cbSkilledWorker.setSelected(arb.getFacharbeiter());
    			
    			tfEmployedSince.setText(Integer.toString(arb.getSeit()));
    			newEmployee = false;
    		}
    			
    	}
	}
	
	private void saveEmployee() {
		if (mitarbeiter instanceof Arbeiter) {
    		Arbeiter arb = (Arbeiter)mitarbeiter;
    		boolean ok = true;
    		String errMsg = "";
    		Integer iYoB = null;
    		try {
    			iYoB = Integer.parseInt(tfYearOfBirth.getText());
    		} catch (NumberFormatException ex) {
    			ok = false;
    			errMsg += "Could not convert \"" + tfYearOfBirth.getText() 
    				   + "\" to an integer value!\n";
    		}
    		Integer iEmployedSince = null;
    		try {
    			iEmployedSince = Integer.parseInt(tfEmployedSince.getText());
    		} catch (NumberFormatException ex) {
    			ok = false;
    			errMsg += "Could not convert \"" + tfEmployedSince.getText() 
    			       + "\" to an integer value!\n";

    		}
    		if (ok) {
    			arb.setName(tfName.getText());
    			arb.setGeburtsjahr(iYoB.intValue());
    			arb.setStundenlohn(spHourlyWage.getValue());
	        	arb.setStunden(spHoursEmployed.getValue());
	        	arb.setFacharbeiter(cbSkilledWorker.isSelected());
	        	arb.setSeit(iEmployedSince.intValue());
	        	tfStatus.setText("Saved Mitarbeiter with id " + arb.getId() + " successfully!");
	        	if (newEmployee) {
	        		if (firma != null) {
	        			System.out.println("Adding new employee " + arb.toString());
	        			firma.add(arb);
	        			ArrayList<Integer> al = firma.getAllIDs();
	        			cbId.getItems().clear();
	        			for (Integer i : al) {
	        				cbId.getItems().add(i);
	        			}
	        		}
	        		newEmployee = false;
	        	}
    		} else { 
    			ButtonType btnOk = new ButtonType("OK", ButtonData.OK_DONE);
    			Dialog<String> dialog = new Dialog<>();
    			dialog.setContentText(errMsg);
    			dialog.getDialogPane().getButtonTypes().add(btnOk);
    			dialog.showAndWait();
    		}
    	}
	}
	
	private void newEmployee() {
		boolean proceed = true;
    	if (mitarbeiter != null) {
			ButtonType btnOk = new ButtonType("OK", ButtonData.OK_DONE);
			ButtonType btnCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
			Dialog<ButtonType> dialog = new Dialog<>();
			dialog.setContentText("Current data will be purged. Press 'Cancel' to abort.");
			dialog.getDialogPane().getButtonTypes().add(btnCancel);
			dialog.getDialogPane().getButtonTypes().add(btnOk);

			// classical approach
			Optional<ButtonType> result = dialog.showAndWait();
			if (result.isPresent()) {
				if (result.get().getButtonData() == ButtonData.OK_DONE) {
					tfStatus.setText("Purging old data and creating new employee.");
				} else if (result.get().getButtonData() == ButtonData.CANCEL_CLOSE) {
					proceed = false;
					tfStatus.setText("Operation cancelled!");
				}
			}
			
			
			// (semi) lambda approach
			/*
			dialog.showAndWait().ifPresent(response -> {
				if (response == ButtonType.OK) {
					tfStatus.setText("Purging old data and creating new employee.");
				} else if (response == ButtonType.CANCEL) {
					proceed = false;
					tfStatus.setText("Operation cancelled!");
				}
			});
			*/
			
    	}
		if (proceed) {
			tfName.clear();
			tfYearOfBirth.clear();
			rbM.setSelected(true);
			spHourlyWage.getValueFactory().setValue(10);
			spHoursEmployed.getValueFactory().setValue(20);
			cbSkilledWorker.setSelected(false);
			tfEmployedSince.setText(Integer.toString(LocalDate.now().getYear()));
			mitarbeiter = new Arbeiter();
			newEmployee = true;
		}
    	
	}
	
	private void loadCompanyFromFile() {
		int maxId = 0;
		boolean success = true;
		firma = new Firma();
		if (firma != null) {
			try { firma.importFirma(filename); } 
			catch (FirmaException fe) { 
				success = false;
				fe.printStackTrace(); 
			} 
		}
		taRemarks.setText(firma.toString());
		System.out.println(firma.getFirstMitarbeiter());
		ArrayList<Integer> al = firma.getAllIDs();
		cbId.getItems().clear();
		for (Integer i : al) {
			cbId.getItems().add(i);
			if (i.intValue() > maxId) {
				maxId = i.intValue();
			}
		}
		Mitarbeiter.setNextId(++maxId);
		if (success) {
			tfStatus.setText("Company loaded successfully from file.");
		}
	}
	
	private void saveDataToFile() {
    	if (firma != null) {
    		try {
    			firma.exportFirma(filename);
    		} catch (FirmaException fe) {
    			fe.printStackTrace();
    		}
    	}
	}
	
	public static void main(String[] args) {
		launch(args);
	}


	public RadioButton getRbF()
	{
		return rbF;
	}


	public void setRbF(RadioButton rbF)
	{
		this.rbF = rbF;
	}
	
}
