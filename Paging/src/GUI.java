import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class GUI {

	MainControl main;
	String dataSet;
	
	@FXML
	private ChoiceBox<String> kiesBox;
	
	@FXML
    private Button exeAll;

    @FXML
    private Button exeOne;

    @FXML
    private Button restart;
    
    @FXML
    private TableView<TableEntry> pageTable;
    
    @FXML 
    private TableColumn<Page, Integer> pageNumColum;
    @FXML 
    private TableColumn<TableEntry, Boolean> presentBitColum;
    @FXML 
    private TableColumn<TableEntry, Boolean> modifyBitColum;
    @FXML 
    private TableColumn<TableEntry, Integer> latColum;
    @FXML 
    private TableColumn<TableEntry, Integer> frameNumColum;

    @FXML
    private TableView<Page> ram;

    @FXML 
    private TableColumn<Page, Integer> frameNumbColum;
    @FXML 
    private TableColumn<Page, Integer> pidColum;
    @FXML 
    private TableColumn<Page, Integer> pageNumbColum;
    
    @FXML
    private Label algemeenTimer;

    @FXML
    private Label algemeenWriteRam;

    @FXML
    private Label algmeenReadRam;

    @FXML
    private Label vorigeInscrutie;

    @FXML
    private Label vorigeVirtueelAdres;

    @FXML
    private Label vorigeFysiekAdres;

    @FXML
    private Label vorigeProcessID;

    @FXML
    private Label vorigeFrame;

    @FXML
    private Label vorigeOffset;

    @FXML
    private Label huidigeInscrutie;

    @FXML
    private Label huidigeVirtueelAdres;

    @FXML
    private Label huidigeFysiekAdres;

    @FXML
    private Label huidigeProcessID;

    @FXML
    private Label huidigeFrame;

    @FXML
    private Label huidigeOffset;
    
    
   
    
    
    @FXML
    public void initialize() {
    	kiesBox.getItems().removeAll(kiesBox.getItems());
    	kiesBox.getItems().addAll("3 processen","4 processen","20000 instructies 20 processen");
    	kiesBox.getSelectionModel().select("3 processen");
    	
    	
    	
    	main = new MainControl();
    	
    	pageNumColum.setCellValueFactory(new PropertyValueFactory<Page, Integer>("pageNumber"));
    	presentBitColum.setCellValueFactory(new PropertyValueFactory<TableEntry, Boolean>("presentBit"));
    	modifyBitColum.setCellValueFactory(new PropertyValueFactory<TableEntry, Boolean>("modifyBit"));
        latColum.setCellValueFactory(new PropertyValueFactory<TableEntry, Integer>("latTime"));
        frameNumColum.setCellValueFactory(new PropertyValueFactory<TableEntry, Integer>("frameNumber"));
        
        addDataToPageTable();
        
        frameNumbColum.setCellValueFactory(new PropertyValueFactory<Page, Integer>("frameNumber"));
        pidColum.setCellValueFactory(new PropertyValueFactory<Page, Integer>("pId"));
        pageNumbColum.setCellValueFactory(new PropertyValueFactory<Page, Integer>("pageNumber"));

        addDataToRAMTable();
    	
    }
    
    public void addDataToPageTable(){

        for ( int i = 0; i<pageTable.getItems().size(); i++) {
            pageTable.getItems().clear();
        }

        Process temp = main.getCurrentP();

        if(temp!=null){
            for(PageTableEntry p: temp.getLijst()){
                if(p!=null)
                    pageTable.getItems().add(new TableEntry(p.getPage().getPageNumber(), p.getTableEntry().isPresentBit(),
                            p.getTableEntry().isModifyBit(), p.getTableEntry().getLastAccessTime(),
                            p.getTableEntry().getFrameNumber()));
            }
        }
    }
    
    public void addDataToRAMTable(){

        for ( int i = 0; i<ram.getItems().size(); i++) {
            ram.getItems().clear();
        }

        Page[] paginas = main.getFrames();

        for (int i = 0; i < paginas.length ; i++) {
            if(paginas[i]!=null){
                  ram.getItems().add(new Page(i, paginas[i].getpID(), paginas[i].getPageNumber()));
            }
        }
    }
    
    @FXML
    public void executeAll(javafx.event.ActionEvent event) {
    	String value = kiesBox.getValue();
    	main.executeQueue(value);
    	refreshAll();
    	main.initialise();
    }
    
    @FXML
    public void executeOne(javafx.event.ActionEvent event) {
    	String val = kiesBox.getValue();
    	if(dataSet == null) dataSet = val;
    	else if(dataSet.equals(val)) dataSet = val;
    	else {
    		dataSet = val;
    		main.initialise();
    	}
    	main.executeOne(val);
    	refreshAll();
    }
    
    public void refreshAll(){
        algemeenTimer.setText(String.valueOf(main.getTime()));
        addDataToPageTable();
        addDataToRAMTable();
        setVorigeInscrutie();
        setHuidigeInscrutie();
        setWrite();
    }
    
    @FXML
    public void setRestart(){
        main.initialise();

        algemeenTimer.setText(String.valueOf(main.getTime()));
        addDataToPageTable();
        addDataToRAMTable();
        setWrite();

        huidigeInscrutie.setText("Begin");
        huidigeVirtueelAdres.setText("----");
        huidigeProcessID.setText("--");
        huidigeFrame.setText("--");
        huidigeOffset.setText("--");
        huidigeFysiekAdres.setText("----");

        vorigeInscrutie.setText("Begin");
        vorigeVirtueelAdres.setText("----");
        vorigeProcessID.setText("--");
        vorigeFrame.setText("--");
        vorigeOffset.setText("--");
        vorigeFysiekAdres.setText("----");


    }
    
    public void setWrite(){
        List<Process> procesList = main.getProcessesList();

        int write = 0;
        int read = 0;

        for(Process p : procesList){
            write+=p.getToRamCount();
            read+=p.getFromRamCount();
        }

        algemeenWriteRam.setText(String.valueOf(write));
        algmeenReadRam.setText(String.valueOf(read));
    }
    
    
    public void setHuidigeInscrutie(){
        Instruction tmp = main.getCurrentI();

        huidigeInscrutie.setText(tmp.getOperation());
        huidigeVirtueelAdres.setText(String.valueOf(tmp.getAddress()));
        huidigeProcessID.setText(String.valueOf(tmp.getProcessID()));
        List<Integer> vertaling = main.translate(tmp.getAddress());

        huidigeFrame.setText(String.valueOf(vertaling.get(0)));
        huidigeOffset.setText(String.valueOf(vertaling.get(1)));

        huidigeFysiekAdres.setText(String.valueOf(main.convert(vertaling.get(0), vertaling.get(1))));

    }

    public void setVorigeInscrutie(){

        vorigeInscrutie.setText(huidigeInscrutie.getText());
        vorigeVirtueelAdres.setText(huidigeVirtueelAdres.getText());
        vorigeProcessID.setText(huidigeProcessID.getText());
        vorigeFrame.setText(huidigeFrame.getText());
        vorigeOffset.setText(huidigeOffset.getText());
        vorigeFysiekAdres.setText(huidigeFysiekAdres.getText());
    }  
    
}
