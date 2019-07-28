package frame;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.*;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import saitMLS.exceptions.InvalidLegalDescriptionException;
import saitMLS.exceptions.InvalidNumberOfBathroomsException;
import saitMLS.exceptions.clientale.InvalidPhoneNumberException;
import saitMLS.exceptions.clientale.InvalidPostalCodeException;
import saitMLS.persistance.*;
import saitMLS.persistence.clientale.ClientBroker;
import saitMLS.problemDomain.*;
import saitMLS.problemDomain.clientale.Client;

/**
 * MyFrame.java - Class describing all attributes and operations for a DataManager object.
 * @author Qiqi Wu
 *
 */
public class MyFrame {


	private JButton btnClient;
	private JButton btnResidential;
	private JButton btnCommercial;
	private ArrayList<JPanel> panelList;
	
	private ActionListener actionListener;
	private ActionListener clientSearchActionListener;
	private ActionListener clientRightPanelActionListener;
	private ActionListener rPSearchActionListener;
	private ActionListener RPRightPanelActionListener;
	private ActionListener CPSearchActionListener;
	private ActionListener CPRightPanelActionListener;
	
	private JFrame frame;
	private JButton btnSearchClient;
	private JButton btnClearSearchClient;
	
	private ClientBroker clientBroker;
	private ArrayList<Client> clientSearchResult;
	private ResidentialPropertyBroker RPBroker;
	private ArrayList<ResidentialProperty> RPSearchResult;
	private CommercialPropertyBroker CPBroker;
	private ArrayList<CommercialProperty> CPSearchResult;
	
	private DefaultListModel<String> clientListModel;
	private JList<String> clientLstSearchResult;
	private DefaultListModel<String> RPListModel;
	private JList<String> RPLstSearchResult;
	private DefaultListModel<String> CPListModel;
	private JList<String> CPLstSearchResult;
	
	private JRadioButton idRadio;
	private JRadioButton lastnameRadio;
	private JRadioButton clientTypeRadio;
	private JTextField searchClienttext;
	private ButtonGroup group1;
	private JTextField textClientFN;
	private JTextField textClientLN;
	private JTextField textClientAddr;
	private JTextField textClientPOS;
	private JTextField textClientPhone;
	private JLabel labelClientId;
	private JComboBox clientCombox;
	private JButton btnclientSave;
	private JButton btnclientDelete;
	private JButton btnclientClear;
	private JRadioButton RRid;
	private JRadioButton RRDescription;
	private JRadioButton RRcity;
	private JRadioButton RRPrice;
	private JButton btnSearchRP;
	private JButton btnClearRP;
	private JTextField searchTextRP;
	private ButtonGroup group2;
	private JRadioButton CPid;
	private JRadioButton CPDescription;
	private JRadioButton CPcity;
	private JRadioButton CPPrice;
	private JLabel labelRPId;
	private JTextField textRPDes;
	private JTextField textRPAddr;
	private JTextField textRPPrice;
	private JTextField textRPArea;
	private JTextField textRPBath;
	private JTextField textRPBed;
	private JTextField textRPCom;
	private JComboBox RPQuaCombox;
	private JComboBox RPZoonCombox;
	private JComboBox RPGarageCombox;
	private JButton btnRPSave;
	private JButton btnRPDelete;
	private JButton JbtnRPClear;
	private JButton btnSearchCP;
	private JButton btnClearCP;
	private JTextField textSearchCP;
	private ButtonGroup group7;
	private JLabel labelCPId;
	private JTextField textCPDes;
	private JTextField textCPAddr;
	private JTextField textCPPrice;
	private JTextField textCPFloors;
	private JTextField textCPCom;
	private JComboBox CPQuaCombox;
	private JComboBox CPZoonCombox;
	private JComboBox CPTypeCombox;
	private JButton btnCPSave;
	private JButton btnCPDelete;
	private JButton btnCPClear;
	
	
	/**
	 * Constructs a MyFrame object.
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	public MyFrame() throws IOException, ClassNotFoundException {
		
		try {
			initialize();
		} catch (NumberFormatException | InvalidLegalDescriptionException | InvalidNumberOfBathroomsException e) {
			
			e.printStackTrace();
		}
	}
	
	/**
	 * initialize the classes of the Backend database used in the program to call the methods and all the actionListeners
	 * create the basic panel using JFrame
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 * @throws InvalidNumberOfBathroomsException 
	 * @throws InvalidLegalDescriptionException 
	 * @throws NumberFormatException 
	 */
	private void initialize() throws IOException, ClassNotFoundException, NumberFormatException, InvalidLegalDescriptionException, InvalidNumberOfBathroomsException {
		
		clientBroker = ClientBroker.getBroker();
		RPBroker = ResidentialPropertyBroker.getBroker();
		CPBroker = CommercialPropertyBroker.getBroker();
		
		frame = new JFrame();
		frame.setBounds(10, 10, 900, 650);
		
		actionListener = new MyActionListener();
		clientSearchActionListener = new clientSearchActionListener();
		clientRightPanelActionListener = new clientRightPanelActionListener();
		rPSearchActionListener = new RPSearchActionListener();
		RPRightPanelActionListener = new RPRightPanelActionListener();
		CPSearchActionListener = new CPSearchActionListener();
		CPRightPanelActionListener = new CPRightPanelActionListener();
		
		panelList = new ArrayList<JPanel>();
		
		frame.add(getNorthPanel(), BorderLayout.NORTH);
		
		panelList.add(getClientPanel());
		panelList.add(getResidentialPanel());
		panelList.add(getCommercialPanel());
		
		frame.add(panelList.get(0), BorderLayout.CENTER);
		
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e)
			{
				clientBroker.closeBroker();
			}
		});
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
	}
	
	/**
	 * create the menu of the main panel in the north
	 * @return panel
	 */
	private JPanel getNorthPanel() {
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1,3));
		
		Border buttonEdge = BorderFactory.createRaisedBevelBorder();
		
		btnClient = new JButton("Client");
		btnClient.setBorder(buttonEdge);
		btnClient.addActionListener(actionListener);
		
		btnResidential = new JButton("Residential");
		btnResidential.setBorder(buttonEdge);
		btnResidential.addActionListener(actionListener);
		
		btnCommercial = new JButton("Commercial");
		btnCommercial.setBorder(buttonEdge);
		btnCommercial.addActionListener(actionListener);
		
		panel.add(btnClient);
		panel.add(btnResidential);
		panel.add(btnCommercial);
		
		return panel;
	}

	/**
	 * create the panel for client's panel
	 * @return panel
	 */
	private JPanel getClientPanel() {
		
		JPanel panel = new JPanel(new BorderLayout());
		
		panel.add(getClientNorthCenterPanel(), BorderLayout.NORTH);
		panel.add(getCenterCenterPanel());
		return panel;
	}
	
	/**
	 * write the title in the center
	 * @return panel
	 */
	private JPanel getClientNorthCenterPanel() {
		
		JPanel panel = new JPanel();
		JLabel label = new JLabel("Client Management Screen");
		
		Border buttonEdge = BorderFactory.createRaisedBevelBorder();
		panel.setBorder(buttonEdge);
		label.setForeground(Color.BLUE);
		label.setFont(new Font("Calibri",Font.BOLD,28));
		
		panel.add(label);
//		panel.setBackground(Color.RED);
		
		return panel;
	}
	
	/**
	 * create the panel under the title panel
	 * @return panel
	 */
	private JPanel getCenterCenterPanel() {
		
		JPanel panel = new JPanel(new GridLayout(1,2));
		
		panel.add(getLeftCenterPanel());
		panel.add(getRightCenterPanel());
		
		return panel;
	}
	
	/**
	 * create the left panel of the client's panel
	 * @return
	 */
	private JPanel getLeftCenterPanel() {
		
		JPanel panel = new JPanel(new GridLayout(2,1));
		Border buttonEdge = BorderFactory.createRaisedBevelBorder();
		
		panel.setBorder(buttonEdge);
		panel.add(getCenterTopLeftPanel());
		panel.add(getBottomLeftCenterPanel());
		
		return panel;
	}
	
	/**
	 * create the top left panel of the left panel in the client's panel
	 * @return panel
	 */
	private JPanel getCenterTopLeftPanel() {
		
		JPanel panel = new JPanel();
		Border buttonEdge = BorderFactory.createRaisedBevelBorder();
		panel.setLayout(new BorderLayout(9,3));
		JLabel label = new JLabel("Search Clients");
		
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setFont(new Font("Calibri",Font.BOLD,15));
		
		panel.setBorder(buttonEdge);
		panel.add(label, BorderLayout.NORTH);
		panel.add(getSearchRadioPanel());
		panel.add(getSearchBottomLabelPanel(),BorderLayout.SOUTH);
		
		return panel;
	}
	
	/**
	 * lay out the bottom of the top left panel
	 * @return panel
	 */
	private JPanel getSearchRadioPanel() {
		
		JPanel panel = new JPanel(new GridLayout(5, 1));
		JLabel label1 = new JLabel("Select type of search to be performed");
		JLabel label2 = new JLabel("Enter the search parameter below:");
		idRadio = new JRadioButton("Client Id");
		lastnameRadio = new JRadioButton("Last Name");
		clientTypeRadio = new JRadioButton("Client Type");
		
		group1 = new ButtonGroup();
		group1.add(idRadio);
		group1.add(lastnameRadio);
		group1.add(clientTypeRadio);
		
		idRadio.addActionListener(clientSearchActionListener);
		lastnameRadio.addActionListener(clientSearchActionListener);
		clientTypeRadio.addActionListener(clientSearchActionListener);
		
		panel.add(label1);
        panel.add(idRadio);
        panel.add(lastnameRadio);
        panel.add(clientTypeRadio);
        panel.add(label2);
        
		return panel;
	}
	
	/**
	 * lay out the bottom of the left panel in the client's panel
	 * @return panel
	 */
	private JPanel getSearchBottomLabelPanel() {

		JPanel panel = new JPanel();
		searchClienttext = new JTextField(20);
		btnSearchClient = new JButton("Search");
		btnClearSearchClient = new JButton("Clear Search");
	    Border raisedbevel = BorderFactory.createRaisedBevelBorder();
	    Border loweredbevel = BorderFactory.createLoweredBevelBorder();
		
	    searchClienttext.setBorder(BorderFactory.createCompoundBorder(
                          raisedbevel, loweredbevel));
	    
	    searchClienttext.addActionListener(clientSearchActionListener);
	    btnSearchClient.addActionListener(clientSearchActionListener);
	    btnClearSearchClient.addActionListener(clientSearchActionListener);
		
		panel.add(searchClienttext);
		panel.add(btnSearchClient);
		panel.add(btnClearSearchClient);
		
		return panel;
	}

	/**
	 * create the JList pane and add it to the bottom of the left panel in the client's panel
	 * @return panel
	 */
	private JPanel getBottomLeftCenterPanel() {
		
		JPanel panel = new JPanel();
		Border buttonEdge = BorderFactory.createRaisedBevelBorder();
		clientListModel = new DefaultListModel<String>();
		clientLstSearchResult = new JList<String>(clientListModel);
		clientLstSearchResult.addListSelectionListener(new ClientListSelectionListener());
		
		clientLstSearchResult.setFixedCellWidth(400);
		clientLstSearchResult.setVisibleRowCount(15);
		
		JScrollPane scrollPane = new JScrollPane(clientLstSearchResult);
		
		panel.setBorder(buttonEdge);
		panel.add(scrollPane);
//		panel.setBackground(Color.MAGENTA);
		
		return panel;
	}
	
	/**
	 * lay out the right panel under the title in the client's panel
	 * @return panel
	 */
	private JPanel getRightCenterPanel() {
		
		JPanel panel = new JPanel(new BorderLayout());
		Border buttonEdge = BorderFactory.createRaisedBevelBorder();
		JLabel label = new JLabel("Client Information");
		
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setFont(new Font("Calibri",Font.BOLD,15));
		
		panel.setBorder(buttonEdge);
		panel.add(label,BorderLayout.NORTH);
		
		panel.add(getRightCenteCenterrPanel(),BorderLayout.CENTER);
		panel.add(getRightCenteBottomPanel(),BorderLayout.SOUTH);
		
		return panel;
	}

	/**
	 * lay out the text fields and the labels in the right of the client's panel
	 * @return panel
	 */
	private JPanel getRightCenteCenterrPanel() {
		
		JPanel panel = new JPanel(new GridLayout(8, 2));
		
		JLabel label1 = new JLabel("Client Id:",SwingConstants.RIGHT);
		JLabel label2 = new JLabel("First Name:",SwingConstants.RIGHT);
		JLabel label3 = new JLabel("Last Name:",SwingConstants.RIGHT);
		JLabel label4 = new JLabel("Address:",SwingConstants.RIGHT);
		JLabel label5 = new JLabel("Postal Code:",SwingConstants.RIGHT);
		JLabel label6 = new JLabel("Phone Number:",SwingConstants.RIGHT);
		JLabel label7 = new JLabel("Client Type:",SwingConstants.RIGHT);
		labelClientId = new JLabel();

		textClientFN = new JTextField();
		textClientLN = new JTextField();
		textClientAddr = new JTextField();
		textClientPOS = new JTextField();
		textClientPhone = new JTextField();
		
		String[] strings = {"C", "R"};
		clientCombox = new JComboBox(strings);
		clientCombox.setSelectedIndex(0);
		
		panel.add(label1);
		panel.add(labelClientId);
		panel.add(label2);
		panel.add(textClientFN);
		panel.add(label3);
		panel.add(textClientLN);
		panel.add(label4);
		panel.add(textClientAddr);
		panel.add(label5);
		panel.add(textClientPOS);
		panel.add(label6);
		panel.add(textClientPhone);
		panel.add(label7);
		panel.add(clientCombox);
		
		
		
		return panel;
	}
	
	/**
	 * create three buttons for the right client's panel
	 * @return panel
	 */
	private JPanel getRightCenteBottomPanel() {

		JPanel panel = new JPanel();
		
		btnclientSave = new JButton("Save");
		btnclientDelete = new JButton("Delete");
		btnclientClear = new JButton("Clear");
		
		btnclientSave.addActionListener(clientRightPanelActionListener);
		btnclientDelete.addActionListener(clientRightPanelActionListener);
		btnclientClear.addActionListener(clientRightPanelActionListener);
		
		panel.add(btnclientSave);
		panel.add(btnclientDelete);
		panel.add(btnclientClear);
		
		return panel;
	}

	/**
	 * create the panel for residential panel
	 * @return panel
	 */
	private JPanel getResidentialPanel() {
		
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(getRPNorthCenterPanel(), BorderLayout.NORTH);
		panel.add(getRPCenterCenterPanel());
		return panel;
	}
	
	/**
	 * write the title in the center
	 * @return panel
	 */
	private JPanel getRPNorthCenterPanel() {
		
		JPanel panel = new JPanel();
		JLabel label = new JLabel("Residential Property Management Screen");
		
		Border buttonEdge = BorderFactory.createRaisedBevelBorder();
		panel.setBorder(buttonEdge);
		label.setForeground(Color.BLUE);
		label.setFont(new Font("Calibri",Font.BOLD,28));
		
		panel.add(label);
		
		return panel;
	}
	
	/**
	 * lay out the panel under the title
	 * @return panel
	 */
	private JPanel getRPCenterCenterPanel() {

		JPanel panel = new JPanel(new GridLayout(1,2));
		
		panel.add(getRPLeftCenterPanel());
		panel.add(getRPRightCenterPanel());
		
		return panel;
	}
	
	/**
	 * lay out the left panel of the residential panel
	 * @return panel
	 */
	private JPanel getRPLeftCenterPanel() {

		JPanel panel = new JPanel(new GridLayout(2,1));
		Border buttonEdge = BorderFactory.createRaisedBevelBorder();
		
		panel.setBorder(buttonEdge);
		panel.add(getRPCenterTopLeftPanel());
		panel.add(getRPBottomLeftCenterPanel());
		
		return panel;
	}
	
	/**
	 * create the top left panel in the residential panel
	 * @return panel
	 */
	private JPanel getRPCenterTopLeftPanel() {

		JPanel panel = new JPanel();
		Border buttonEdge = BorderFactory.createRaisedBevelBorder();
		panel.setLayout(new BorderLayout(9,3));
		JLabel label = new JLabel("Search Residential Property");
		
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setFont(new Font("Calibri",Font.BOLD,15));
		
		panel.setBorder(buttonEdge);
		panel.add(label, BorderLayout.NORTH);
		panel.add(getRPSearchRadioPanel());
		panel.add(getRPSearchBottomLabelPanel(),BorderLayout.SOUTH);
		
		return panel;
	}

	/**
	 * add radio buttons and labels in the top left panel in the residential panel
	 * @return panel
	 */
	private JPanel getRPSearchRadioPanel() {

		JPanel panel = new JPanel(new GridLayout(6, 1));
		JLabel label1 = new JLabel("Select type of search to be performed");
		JLabel label2 = new JLabel("Enter the search parameter below:");
		RRid = new JRadioButton("Residential Property Id");
		RRDescription = new JRadioButton("Residential Property Legal Description");
		RRcity = new JRadioButton("Quadrant of City");
		RRPrice = new JRadioButton("Residential Property Price");
		
		group2 = new ButtonGroup();
		group2.add(RRid);
		group2.add(RRDescription);
		group2.add(RRcity);
		group2.add(RRPrice);
		
		RRid.addActionListener(rPSearchActionListener);
		RRDescription.addActionListener(rPSearchActionListener);
		RRcity.addActionListener(rPSearchActionListener);
		RRPrice.addActionListener(rPSearchActionListener);
		
		panel.add(label1);
        panel.add(RRid);
        panel.add(RRDescription);
        panel.add(RRcity);
        panel.add(RRPrice);
        panel.add(label2);
        
		return panel;
	}
	
	/**
	 * complete layout of the top bottom panel in the residential panel
	 * @return panel
	 */
	private JPanel getRPSearchBottomLabelPanel() {
		
		JPanel panel = new JPanel();
		searchTextRP = new JTextField(20);
		btnSearchRP = new JButton("Search");
		btnClearRP = new JButton("Clear Search");
	    Border raisedbevel = BorderFactory.createRaisedBevelBorder();
	    Border loweredbevel = BorderFactory.createLoweredBevelBorder();
		
	    searchTextRP.setBorder(BorderFactory.createCompoundBorder(
                          raisedbevel, loweredbevel));
		
	    searchTextRP.addActionListener(rPSearchActionListener);
	    btnSearchRP.addActionListener(rPSearchActionListener);
		btnClearRP.addActionListener(rPSearchActionListener);
		
		panel.add(searchTextRP);
		panel.add(btnSearchRP);
		panel.add(btnClearRP);
		
		return panel;
	}
	
	/**
	 * create the JList panel in the bottom left of the residential panel
	 * @return panel
	 */
	private JPanel getRPBottomLeftCenterPanel() {

		JPanel panel = new JPanel();
		Border buttonEdge = BorderFactory.createRaisedBevelBorder();
		RPListModel = new DefaultListModel<String>();
		RPLstSearchResult = new JList<String>(RPListModel);
		RPLstSearchResult.addListSelectionListener(new RPListSelectionListener());
		
		RPLstSearchResult.setFixedCellWidth(400);
		RPLstSearchResult.setVisibleRowCount(15);
		
		JScrollPane scrollPane = new JScrollPane(RPLstSearchResult);
		
		panel.setBorder(buttonEdge);
		panel.add(scrollPane);
		
		return panel;
	}
	
	/**
	 * create the right panel of the residential panel
	 * @return panel
	 */
	private JPanel getRPRightCenterPanel() {

		JPanel panel = new JPanel(new BorderLayout());
		Border buttonEdge = BorderFactory.createRaisedBevelBorder();
		JLabel label = new JLabel("Residential Property Information");
		
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setFont(new Font("Calibri",Font.BOLD,15));
		
		panel.setBorder(buttonEdge);
		panel.add(label,BorderLayout.NORTH);
		
		panel.add(getRRightCenteCenterrPanel(),BorderLayout.CENTER);
		panel.add(getRRightCenteBottomPanel(),BorderLayout.SOUTH);
		
		return panel;
	}

	/**
	 * add some labels and text fields in the right panel in the residential panel
	 * @return panel
	 */
	private JPanel getRRightCenteCenterrPanel() {

		JPanel panel = new JPanel(new GridLayout(12, 2));
		
		JLabel label1 = new JLabel("Residential Property Id:",SwingConstants.RIGHT);
		JLabel label2 = new JLabel("Property Legal Description:",SwingConstants.RIGHT);
		JLabel label3 = new JLabel("Property Address:",SwingConstants.RIGHT);
		JLabel label4 = new JLabel("City Quadrant:",SwingConstants.RIGHT);
		JLabel label5 = new JLabel("Zoning of Property:",SwingConstants.RIGHT);
		JLabel label6 = new JLabel("Property Asking Price:",SwingConstants.RIGHT);
		JLabel label7 = new JLabel("Building Square Footage:",SwingConstants.RIGHT);
		JLabel label8 = new JLabel("No. of Bathrooms:",SwingConstants.RIGHT);
		JLabel label9 = new JLabel("No. of Bedrooms:",SwingConstants.RIGHT);
		JLabel label10 = new JLabel("Garage Type:",SwingConstants.RIGHT);
		JLabel label11 = new JLabel("Comments about Property:",SwingConstants.RIGHT);
		labelRPId = new JLabel();
		textRPDes = new JTextField();
		textRPAddr = new JTextField();
		textRPPrice = new JTextField();
		textRPArea = new JTextField();
		textRPBath = new JTextField();
		textRPBed = new JTextField();
		textRPCom = new JTextField();
		
		String[] strings = {"NE","NW", "SW","SE"};
		RPQuaCombox = new JComboBox(strings);
		RPQuaCombox.setSelectedIndex(0);
		String[] strings2 = {"R1","R2", "R3","R4"};
		RPZoonCombox = new JComboBox(strings2);
		RPZoonCombox.setSelectedIndex(0);
		String[] strings3 = {"A","D", "N"};
		RPGarageCombox = new JComboBox(strings3);
		RPGarageCombox.setSelectedIndex(0);
		
		panel.add(label1);
		panel.add(labelRPId);
		panel.add(label2);
		panel.add(textRPDes);
		panel.add(label3);
		panel.add(textRPAddr);
		panel.add(label4);
		panel.add(RPQuaCombox);
		panel.add(label5);
		panel.add(RPZoonCombox);
		panel.add(label6);
		panel.add(textRPPrice);
		panel.add(label7);
		panel.add(textRPArea);
		panel.add(label8);
		panel.add(textRPBath);
		panel.add(label9);
		panel.add(textRPBed);
		panel.add(label10);
		panel.add(RPGarageCombox);
		panel.add(label11);
		panel.add(textRPCom);
		
		return panel;
		
	}
	
	/**
	 * create three buttons for the right panel of the residential panel
	 * @return panel
	 */
	private JPanel getRRightCenteBottomPanel() {
		
		JPanel panel = new JPanel();
		
		btnRPSave = new JButton("Save");
		btnRPDelete = new JButton("Delete");
		JbtnRPClear = new JButton("Clear");
		
		btnRPSave.addActionListener(RPRightPanelActionListener);
		btnRPDelete.addActionListener(RPRightPanelActionListener);
		JbtnRPClear.addActionListener(RPRightPanelActionListener);
		
		panel.add(btnRPSave);
		panel.add(btnRPDelete);
		panel.add(JbtnRPClear);
		
		return panel;
		
	}

	/**
	 * create the commercial panel
	 * @return panel
	 */
	private JPanel getCommercialPanel() {
		
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(getCPNorthCenterPanel(), BorderLayout.NORTH);
		panel.add(getCPCenterCenterPanel());
		return panel;
	}
	
	/**
	 * write the title of the panel under the menu in the commercial panel
	 * @return panel
	 */
	private JPanel getCPNorthCenterPanel() {
		
		JPanel panel = new JPanel();
		JLabel label = new JLabel("Commercial Property Management Screen");
		
		Border buttonEdge = BorderFactory.createRaisedBevelBorder();
		panel.setBorder(buttonEdge);
		label.setForeground(Color.BLUE);
		label.setFont(new Font("Calibri",Font.BOLD,28));
		
		panel.add(label);
		
		return panel;
	}
	
	/**
	 * lay out the center panel of the commercial panel
	 * @return panel
	 */
	private JPanel getCPCenterCenterPanel() {
		
		JPanel panel = new JPanel(new GridLayout(1,2));
		
		panel.add(getCPLeftCenterPanel());
		panel.add(getCPRightCenterPanel());
		
		return panel;
	}
	
	/**
	 * lay out the left panel in commercial panel
	 * @return panel
	 */
	private JPanel getCPLeftCenterPanel() {
		
		JPanel panel = new JPanel(new GridLayout(2,1));
		Border buttonEdge = BorderFactory.createRaisedBevelBorder();
		
		panel.setBorder(buttonEdge);
		panel.add(getCPCenterTopLeftPanel());
		panel.add(getCPBottomLeftCenterPanel());
		
		return panel;
	}
	
	/**
	 * lay out the top left panel of the commercial panel
	 * @return panel
	 */
	private JPanel getCPCenterTopLeftPanel() {
		
		JPanel panel = new JPanel();
		Border buttonEdge = BorderFactory.createRaisedBevelBorder();
		panel.setLayout(new BorderLayout(9,3));
		JLabel label = new JLabel("Search Commercial Property");
		
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setFont(new Font("Calibri",Font.BOLD,15));
		
		panel.setBorder(buttonEdge);
		panel.add(label, BorderLayout.NORTH);
		panel.add(getCPSearchRadioPanel());
		panel.add(getCPSearchBottomLabelPanel(),BorderLayout.SOUTH);
		
		return panel;
	}
	
	/**
	 * add radio buttons and labels to the top left panel in the commercial panel
	 * @return panel
	 */
	private JPanel getCPSearchRadioPanel() {
		
		JPanel panel = new JPanel(new GridLayout(6, 1));
		JLabel label1 = new JLabel("Select type of search to be performed");
		JLabel label2 = new JLabel("Enter the search parameter below:");
		CPid = new JRadioButton("Commercial Property Id");
		CPDescription = new JRadioButton("Commercial Property Legal Description");
		CPcity = new JRadioButton("Quadrant of City");
		CPPrice = new JRadioButton("Commercial Property Price");
		
		group7 = new ButtonGroup();
		group7.add(CPid);
		group7.add(CPDescription);
		group7.add(CPcity);
		group7.add(CPPrice);
		
		CPid.addActionListener(CPSearchActionListener);
		CPDescription.addActionListener(CPSearchActionListener);
		CPcity.addActionListener(CPSearchActionListener);
		CPPrice.addActionListener(CPSearchActionListener);
		
		panel.add(label1);
        panel.add(CPid);
        panel.add(CPDescription);
        panel.add(CPcity);
        panel.add(CPPrice);
        panel.add(label2);
        
		return panel;
	}
	
	/**
	 * create buttons and text field for searching function in the commercial panel
	 * @return panel
	 */
	private JPanel getCPSearchBottomLabelPanel() {

		JPanel panel = new JPanel();
		textSearchCP = new JTextField(20);
		btnSearchCP = new JButton("Search");
		btnClearCP = new JButton("Clear Search");
	    Border raisedbevel = BorderFactory.createRaisedBevelBorder();
	    Border loweredbevel = BorderFactory.createLoweredBevelBorder();
		
	    textSearchCP.setBorder(BorderFactory.createCompoundBorder(
                          raisedbevel, loweredbevel));
		
		textSearchCP.addActionListener(CPSearchActionListener);
		btnSearchCP.addActionListener(CPSearchActionListener);
		btnClearCP.addActionListener(CPSearchActionListener);
		
		panel.add(textSearchCP);
		panel.add(btnSearchCP);
		panel.add(btnClearCP);
		
		return panel;
	}

	/**
	 * create the JList panel in the bottom left of the commercial panel
	 * @return panel
	 */
	private JPanel getCPBottomLeftCenterPanel() {
		
		JPanel panel = new JPanel();
		Border buttonEdge = BorderFactory.createRaisedBevelBorder();
		CPListModel = new DefaultListModel<String>();
		CPLstSearchResult = new JList<String>(CPListModel);
		CPLstSearchResult.addListSelectionListener(new CPListSelectionListener());
		
		CPLstSearchResult.setFixedCellWidth(400);
		CPLstSearchResult.setVisibleRowCount(15);
		
		JScrollPane scrollPane = new JScrollPane(CPLstSearchResult);
		
		panel.setBorder(buttonEdge);
		panel.add(scrollPane);
		
		return panel;
	}
	
	/**
	 * lay out the right panel of the commercial panel
	 * @return panel
	 */
	private JPanel getCPRightCenterPanel() {
		
		JPanel panel = new JPanel(new BorderLayout());
		Border buttonEdge = BorderFactory.createRaisedBevelBorder();
		JLabel label = new JLabel("Commercial Property Information");
		
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setFont(new Font("Calibri",Font.BOLD,15));
		
		panel.setBorder(buttonEdge);
		panel.add(label,BorderLayout.NORTH);
		
		panel.add(getCPRightCenteCenterrPanel(),BorderLayout.CENTER);
		panel.add(getCPRightCenteBottomPanel(),BorderLayout.SOUTH);
		
		return panel;
	}

	/**
	 * add labels and textfields in the right panel of the commercial panel
	 * @return panel
	 */
	private Component getCPRightCenteCenterrPanel() {
		
		JPanel panel = new JPanel(new GridLayout(9, 2));
		
		JLabel label1 = new JLabel("Commercial Property Id:",SwingConstants.RIGHT);
		JLabel label2 = new JLabel("Property Legal Description:",SwingConstants.RIGHT);
		JLabel label3 = new JLabel("Property Address:",SwingConstants.RIGHT);
		JLabel label4 = new JLabel("City Quadrant:",SwingConstants.RIGHT);
		JLabel label5 = new JLabel("Zoning of Property:",SwingConstants.RIGHT);
		JLabel label6 = new JLabel("Property Asking Price:",SwingConstants.RIGHT);
		JLabel label7 = new JLabel("Property Type:",SwingConstants.RIGHT);
		JLabel label8 = new JLabel("No. of Floors:",SwingConstants.RIGHT);
		JLabel label9 = new JLabel("Comments about Property:",SwingConstants.RIGHT);
		labelCPId = new JLabel();
		textCPDes = new JTextField();
	    textCPAddr = new JTextField();
		textCPPrice = new JTextField();
		textCPFloors = new JTextField();
		textCPCom = new JTextField();
		String[] strings = {"NE","NW", "SW","SE"};
		CPQuaCombox = new JComboBox(strings);
		CPQuaCombox.setSelectedIndex(0);
		String[] strings2 = {"I1","I2", "I3","I4"};
		CPZoonCombox = new JComboBox(strings2);
		CPZoonCombox.setSelectedIndex(0);
		String[] strings3 = {"M","O"};
		CPTypeCombox = new JComboBox(strings3);
		CPTypeCombox.setSelectedIndex(0);
		
		panel.add(label1);
		panel.add(labelCPId);
		panel.add(label2);
		panel.add(textCPDes);
		panel.add(label3);
		panel.add(textCPAddr);
		panel.add(label4);
		panel.add(CPQuaCombox);
		panel.add(label5);
		panel.add(CPZoonCombox);
		panel.add(label6);
		panel.add(textCPPrice);
		panel.add(label7);
		panel.add(CPTypeCombox);
		panel.add(label8);
		panel.add(textCPFloors);
		panel.add(label9);
		panel.add(textCPCom);
		
		return panel;
	}
	
	/**
	 * create three buttons for the right bottom panel in the commercial panel
	 * @return panel
	 */
	private JPanel getCPRightCenteBottomPanel() {

		JPanel panel = new JPanel();
		
		btnCPSave = new JButton("Save");
		btnCPDelete = new JButton("Delete");
		btnCPClear = new JButton("Clear");
		
		btnCPSave.addActionListener(CPRightPanelActionListener);
		btnCPDelete.addActionListener(CPRightPanelActionListener);
		btnCPClear.addActionListener(CPRightPanelActionListener);
		
		panel.add(btnCPSave);
		panel.add(btnCPDelete);
		panel.add(btnCPClear);
		
		return panel;
	}


	
	/**
	 * how to switch to the other panel
	 * @author Qiqi Wu
	 *
	 */
	private class MyActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JPanel tp;
			
			for(int i = 0; i < panelList.size(); i++)
			{
				tp = panelList.get(i);
				frame.remove(tp);
				tp.setVisible(false);
			}
			
			if(e.getSource() == btnClient)
			{
				tp = panelList.get(0);
				frame.add(tp);
				tp.setVisible(true);
				
			}
			else if(e.getSource() == btnResidential)
			{
				tp = panelList.get(1);
				frame.add(tp);
				tp.setVisible(true);
				
			}
			else if(e.getSource() == btnCommercial)
			{
				tp = panelList.get(2);
				frame.add(tp);
				tp.setVisible(true);
				
			}
		}

	}
	
	/**
	 * how to show the searching information using the JList
	 * @author Qiqi Wu
	 *
	 */
	private class clientSearchActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource() == searchClienttext||e.getSource() == btnSearchClient) {
					
				if(idRadio.isSelected()) {

					clientListModel.clear();
					clientSearchResult = (ArrayList<Client>)clientBroker.search(searchClienttext.getText(), "id");
					
					for(Client c : clientSearchResult) {
						
						clientListModel.addElement(c.getId() + " " + c.getFirstName() + " " + c.getLastName() + 
								" " + c.getClientType());
					}
				}else if(lastnameRadio.isSelected()) {
					clientListModel.clear();
					clientSearchResult = (ArrayList<Client>)clientBroker.search(searchClienttext.getText(), "name");
					
					for(Client c : clientSearchResult) {
							
						clientListModel.addElement(c.getId() + " " + c.getFirstName() + " " + c.getLastName() + 
								" " + c.getClientType());
					}
				}else if(clientTypeRadio.isSelected()) {
					clientListModel.clear();
					clientSearchResult = (ArrayList<Client>)clientBroker.search(searchClienttext.getText(), "type");
					
						for(Client c : clientSearchResult) {
							
							clientListModel.addElement(c.getId() + " " + c.getFirstName() + " " + c.getLastName() + 
									" " + c.getClientType());
						}
				}
			}else if(e.getSource() == btnClearSearchClient) {
				
				clientListModel.clear();
				searchClienttext.setText("");
			}	
		} 
	}
	
	/**
	 * how to select the value of the list and show them in the other fields
	 * @author Qiqi Wu
	 *
	 */
	private class ClientListSelectionListener implements ListSelectionListener{

		@Override
		public void valueChanged(ListSelectionEvent e) {
			
			int index = clientLstSearchResult.getSelectedIndex();
			
			if(index >= 0) {
				
				labelClientId.setText(Long.toString(clientSearchResult.get(index).getId()));
				textClientFN.setText(clientSearchResult.get(index).getFirstName());
				textClientLN.setText(clientSearchResult.get(index).getLastName());
				textClientAddr.setText(clientSearchResult.get(index).getAddress());
				textClientPOS.setText(clientSearchResult.get(index).getPostalCode());
				textClientPhone.setText(clientSearchResult.get(index).getPhoneNumber());
				if(clientSearchResult.get(index).getClientType()=='C') {
					clientCombox.setSelectedIndex(0);
				}else {
					clientCombox.setSelectedIndex(1);
				}
			}
			
		}
	}
	
	/**
	 * how to add,modify,remove the information from the database
	 * @author Qiqi Wu
	 *
	 */
	private class clientRightPanelActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			try {
				
				if(e.getSource() == btnclientSave) {
					
					if(labelClientId.getText().equals("")) {
						boolean flag;
						flag = clientBroker.persist(new Client(0,textClientFN.getText(),textClientLN.getText(),
									textClientAddr.getText(),textClientPOS.getText(),textClientPhone.getText(),
									((String)clientCombox.getSelectedItem()).charAt(0)));
						
						if(flag == true) {
							JOptionPane.showMessageDialog(null, "Adding a new client is successful!");
							}
					}else {
						boolean flag;
				
						flag = clientBroker.persist(new Client(Long.parseLong(labelClientId.getText()),textClientFN.getText(),textClientLN.getText(),
								textClientAddr.getText(),textClientPOS.getText(),textClientPhone.getText(),
								((String)clientCombox.getSelectedItem()).charAt(0)));
						
						if(flag == true) {
							JOptionPane.showMessageDialog(null,"Modifying information is successful!");
						}
					}	
				}else if(e.getSource() == btnclientDelete) {
					
					boolean flag;
					
					flag = clientBroker.remove(new Client(Long.parseLong(labelClientId.getText()),textClientFN.getText(),textClientLN.getText(),
								textClientAddr.getText(),textClientPOS.getText(),textClientPhone.getText(),
								((String)clientCombox.getSelectedItem()).charAt(0)));
					
					if(flag == true) {
						JOptionPane.showMessageDialog(null,"Removing a client is successful!");
					}
				}else if(e.getSource() == btnclientClear) {
					
					labelClientId.setText("");
					textClientFN.setText("");
					textClientLN.setText("");
					textClientAddr.setText("");
					textClientPOS.setText("");
					textClientPhone.setText("");
					clientCombox.setSelectedIndex(0);
				}
			}catch (NumberFormatException | InvalidPostalCodeException | InvalidPhoneNumberException e1) {
				
				e1.printStackTrace();
			}
		}	
	}
	
	/**
	 * how to show the searching information using the JList
	 * @author Qiqi Wu
	 *
	 */
	private class RPSearchActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource() == searchTextRP||e.getSource() == btnSearchRP) {
				
				if(RRid.isSelected()) {

					RPListModel.clear();
					RPSearchResult = (ArrayList<ResidentialProperty>)RPBroker.search(searchTextRP.getText(), "id");
					
					for(ResidentialProperty c : RPSearchResult) {
						
						RPListModel.addElement(c.getId() + " " + c.getLegalDescription() + " " + c.getQuadrant() + 
								" " + c.getAskingPrice());
					}
				}else if(RRDescription.isSelected()) {
					RPListModel.clear();
					RPSearchResult = (ArrayList<ResidentialProperty>)RPBroker.search(searchTextRP.getText(), "legal description");
					
					for(ResidentialProperty c : RPSearchResult) {
						
						RPListModel.addElement(c.getId() + " " + c.getLegalDescription() + " " + c.getQuadrant() + 
								" " + c.getAskingPrice());
					}
				}else if(RRcity.isSelected()) {
					RPListModel.clear();
					RPSearchResult = (ArrayList<ResidentialProperty>)RPBroker.search(searchTextRP.getText(), "quadrant");
					
					for(ResidentialProperty c : RPSearchResult) {
						
						RPListModel.addElement(c.getId() + " " + c.getLegalDescription() + " " + c.getQuadrant() + 
								" " + c.getAskingPrice());
					}
				}else if(RRPrice.isSelected()) {
					RPListModel.clear();
					RPSearchResult = (ArrayList<ResidentialProperty>)RPBroker.search(searchTextRP.getText(), "price");
					
					for(ResidentialProperty c : RPSearchResult) {
						
						RPListModel.addElement(c.getId() + " " + c.getLegalDescription() + " " + c.getQuadrant() + 
								" " + c.getAskingPrice());
					}
				}
			}else if(e.getSource() == btnClearRP) {
				
				RPListModel.clear();
				searchTextRP.setText("");
			}	
		} 
	}
	
	/**
	 * how to select the value of the list and show them in the other fields
	 * @author Qiqi Wu
	 *
	 */
	private class RPListSelectionListener implements ListSelectionListener{

		@Override
		public void valueChanged(ListSelectionEvent e) {
			
			int index = RPLstSearchResult.getSelectedIndex();
			
			if(index >= 0) {
				
				labelRPId.setText(Long.toString(RPSearchResult.get(index).getId()));
				textRPDes.setText(RPSearchResult.get(index).getLegalDescription());
				textRPAddr.setText(RPSearchResult.get(index).getAddress());
				textRPPrice.setText(Double.toString(RPSearchResult.get(index).getAskingPrice()));
				textRPArea.setText(Double.toString(RPSearchResult.get(index).getArea()));
				textRPBath.setText(Double.toString(RPSearchResult.get(index).getBathrooms()));
				textRPBed.setText(Integer.toString(RPSearchResult.get(index).getBedrooms()));
				textRPCom.setText(RPSearchResult.get(index).getComments());
				
				if(RPSearchResult.get(index).getQuadrant().equals("NE")) {
					RPQuaCombox.setSelectedIndex(0);
				}else if(RPSearchResult.get(index).getQuadrant().equals("NW")){
					RPQuaCombox.setSelectedIndex(1);
				}else if(RPSearchResult.get(index).getQuadrant().equals("SW")){
					RPQuaCombox.setSelectedIndex(2);
				}else if(RPSearchResult.get(index).getQuadrant().equals("SE")){
					RPQuaCombox.setSelectedIndex(3);
				}
				
				if(RPSearchResult.get(index).getZone().equals("R1")) {
					RPZoonCombox.setSelectedIndex(0);
				}else if(RPSearchResult.get(index).getZone().equals("R2")){
					RPZoonCombox.setSelectedIndex(1);
				}else if(RPSearchResult.get(index).getZone().equals("R3")){
					RPZoonCombox.setSelectedIndex(2);
				}else if(RPSearchResult.get(index).getZone().equals("R4")){
					RPZoonCombox.setSelectedIndex(3);
				}
				
				if(RPSearchResult.get(index).getGarage()=='A') {
					RPGarageCombox.setSelectedIndex(0);
				}else if(RPSearchResult.get(index).getGarage()=='D'){
					RPGarageCombox.setSelectedIndex(1);
				}else if(RPSearchResult.get(index).getGarage()=='N'){
					RPGarageCombox.setSelectedIndex(2);
				}
			}
			
		}
	}
	
	/**
	 * how to add,modify,remove the information from the database
	 * @author Qiqi Wu
	 *
	 */
	private class RPRightPanelActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			try {
			
				if(e.getSource() == btnRPSave) {
					
					if(labelRPId.getText().equals("")) {
							
						boolean flag = RPBroker.persist(new ResidentialProperty(0,textRPDes.getText(),
									textRPAddr.getText(),(String)RPQuaCombox.getSelectedItem(),(String)RPZoonCombox.getSelectedItem(),
									Double.parseDouble(textRPPrice.getText()),textRPCom.getText(),Double.parseDouble(textRPArea.getText()),
									Double.parseDouble(textRPBath.getText()),Integer.parseInt(textRPBed.getText()),((String)RPGarageCombox.getSelectedItem()).charAt(0)));
						if(flag == true) {
							JOptionPane.showMessageDialog(null, "Adding a new client is successful!");
							}
					}else {
						boolean flag = RPBroker.persist(new ResidentialProperty(Long.parseLong(labelRPId.getText()),textRPDes.getText(),
								textRPAddr.getText(),(String)RPQuaCombox.getSelectedItem(),(String)RPZoonCombox.getSelectedItem(),
								Double.parseDouble(textRPPrice.getText()),textRPCom.getText(),Double.parseDouble(textRPArea.getText()),
								Double.parseDouble(textRPBath.getText()),Integer.parseInt(textRPBed.getText()),((String)RPGarageCombox.getSelectedItem()).charAt(0)));
						
						if(flag == true) {
							JOptionPane.showMessageDialog(null, "Modifying information is successful!");
						}
					}	
				}else if(e.getSource() == btnRPDelete) {
					
					boolean flag = RPBroker.remove(new ResidentialProperty(Long.parseLong(labelRPId.getText()),textRPDes.getText(),
							textRPAddr.getText(),(String)RPQuaCombox.getSelectedItem(),(String)RPZoonCombox.getSelectedItem(),
							Double.parseDouble(textRPPrice.getText()),textRPCom.getText(),Double.parseDouble(textRPArea.getText()),
							Double.parseDouble(textRPBath.getText()),Integer.parseInt(textRPBed.getText()),((String)RPGarageCombox.getSelectedItem()).charAt(0)));		
					
					if(flag == true) {
						JOptionPane.showMessageDialog(null, "Removing a client is successful!");
					}
				}else if(e.getSource() == JbtnRPClear) {
					
					labelRPId.setText("");
					textRPDes.setText("");
					textRPAddr.setText("");
					textRPPrice.setText("");
					textRPCom.setText("");
					textRPArea.setText("");
					textRPBath.setText("");
					textRPBed.setText("");
					RPQuaCombox.setSelectedIndex(0);
					RPZoonCombox.setSelectedIndex(0);
					RPGarageCombox.setSelectedIndex(0);
				}
				RPBroker.closeBroker();
			}catch (NumberFormatException | InvalidLegalDescriptionException | InvalidNumberOfBathroomsException e1) {
				
				e1.printStackTrace();
			}
		}	
	}
	
	/**
	 * how to show the searching information using the JList
	 * @author Qiqi Wu
	 *
	 */
	private class CPSearchActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource() == textSearchCP||e.getSource() == btnSearchCP) {
				
				if(CPid.isSelected()) {
					
					CPListModel.clear();
					CPSearchResult = (ArrayList<CommercialProperty>)CPBroker.search(textSearchCP.getText(), "id");
					
					for(CommercialProperty c : CPSearchResult) {
						
						CPListModel.addElement(c.getId() + " " + c.getLegalDescription() + " " + c.getQuadrant() + 
								" " + c.getAskingPrice());
					}
				}else if(CPDescription.isSelected()) {
					CPListModel.clear();
					CPSearchResult = (ArrayList<CommercialProperty>)CPBroker.search(textSearchCP.getText(), "legal description");
					
					for(CommercialProperty c : CPSearchResult) {
						
						CPListModel.addElement(c.getId() + " " + c.getLegalDescription() + " " + c.getQuadrant() + 
								" " + c.getAskingPrice());
					}
				}else if(CPcity.isSelected()) {
					CPListModel.clear();
					CPSearchResult = (ArrayList<CommercialProperty>)CPBroker.search(textSearchCP.getText(), "quadrant");
					
					for(CommercialProperty c : CPSearchResult) {
						
						CPListModel.addElement(c.getId() + " " + c.getLegalDescription() + " " + c.getQuadrant() + 
								" " + c.getAskingPrice());
					}
				}else if(CPPrice.isSelected()) {
					CPListModel.clear();
					CPSearchResult = (ArrayList<CommercialProperty>)CPBroker.search(textSearchCP.getText(), "price");
					
					for(CommercialProperty c : CPSearchResult) {
						
						CPListModel.addElement(c.getId() + " " + c.getLegalDescription() + " " + c.getQuadrant() + 
								" " + c.getAskingPrice());
					}
				}
			}else if(e.getSource() == btnClearCP) {
				
				CPListModel.clear();
				textSearchCP.setText("");
			}	
		} 
	}
	
	/**
	 * how to select the value of the list and show them in the other fields
	 * @author Qiqi Wu
	 *
	 */
	private class CPListSelectionListener implements ListSelectionListener{

		@Override
		public void valueChanged(ListSelectionEvent e) {
			
			int index = CPLstSearchResult.getSelectedIndex();
			
			if(index >= 0) {
				
				labelCPId.setText(Long.toString(CPSearchResult.get(index).getId()));
				textCPDes.setText(CPSearchResult.get(index).getLegalDescription());
				textCPAddr.setText(CPSearchResult.get(index).getAddress());
				textCPPrice.setText(Double.toString(CPSearchResult.get(index).getAskingPrice()));
				textCPFloors.setText(Double.toString(CPSearchResult.get(index).getNoFloors()));
				textCPCom.setText(CPSearchResult.get(index).getComments());
				
				if(CPSearchResult.get(index).getQuadrant().equals("NE")) {
					CPQuaCombox.setSelectedIndex(0);
				}else if(CPSearchResult.get(index).getQuadrant().equals("NW")){
					CPQuaCombox.setSelectedIndex(1);
				}else if(CPSearchResult.get(index).getQuadrant().equals("SW")){
					CPQuaCombox.setSelectedIndex(2);
				}else if(CPSearchResult.get(index).getQuadrant().equals("SE")){
					CPQuaCombox.setSelectedIndex(3);
				}
				
				if(CPSearchResult.get(index).getZone().equals("I1")) {
					CPZoonCombox.setSelectedIndex(0);
				}else if(CPSearchResult.get(index).getZone().equals("I2")){
					CPZoonCombox.setSelectedIndex(1);
				}else if(CPSearchResult.get(index).getZone().equals("I3")){
					CPZoonCombox.setSelectedIndex(2);
				}else if(CPSearchResult.get(index).getZone().equals("I4")){
					CPZoonCombox.setSelectedIndex(3);
				}
				
				if(CPSearchResult.get(index).getType().equals("M")) {
					CPTypeCombox.setSelectedIndex(0);
				}else if(CPSearchResult.get(index).getType().equals("O")){
					CPTypeCombox.setSelectedIndex(1);
				}
			}
			
		}
	}
	
	/**
	 * how to add,modify,remove the information from the database
	 * @author Qiqi Wu
	 *
	 */
	private class CPRightPanelActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			try {
			
				if(e.getSource() == btnCPSave) {
					
					if(labelCPId.getText().equals("")) {
						boolean flag = CPBroker.persist(new CommercialProperty(0,textCPDes.getText(),
									textCPAddr.getText(),(String)CPQuaCombox.getSelectedItem(),
									(String)CPZoonCombox.getSelectedItem(),
									Double.parseDouble(textCPPrice.getText()),textCPCom.getText(),
									(String)CPTypeCombox.getSelectedItem(),Integer.parseInt(textCPFloors.getText())));
						if(flag == true) {
							JOptionPane.showMessageDialog(null, "Adding a new client is successful!");
						}
					}else {
						boolean flag = CPBroker.persist(new CommercialProperty(Long.parseLong(labelCPId.getText()),textCPDes.getText(),
								textCPAddr.getText(),(String)CPQuaCombox.getSelectedItem(),
								(String)CPZoonCombox.getSelectedItem(),
								Double.parseDouble(textCPPrice.getText()),textCPCom.getText(),
								(String)CPTypeCombox.getSelectedItem(),Integer.parseInt(textCPFloors.getText())));
						if(flag == true) {
							JOptionPane.showMessageDialog(null, "Modifying information is successful!");
						}
					}	
				}else if(e.getSource() == btnCPDelete) {
					
					boolean flag = CPBroker.remove(new CommercialProperty(Long.parseLong(labelCPId.getText()),textCPDes.getText(),
							textCPAddr.getText(),(String)CPQuaCombox.getSelectedItem(),
							(String)CPZoonCombox.getSelectedItem(),
							Double.parseDouble(textCPPrice.getText()),textCPCom.getText(),
							(String)CPTypeCombox.getSelectedItem(),Integer.parseInt(textCPFloors.getText())));
					if(flag == true) {
						JOptionPane.showMessageDialog(null, "Removing a client is successful!");
					}
				}else if(e.getSource() == btnCPClear) {
					
					labelCPId.setText("");
					textCPDes.setText("");
					textCPAddr.setText("");
					textCPPrice.setText("");
					textCPFloors.setText("");
					textCPCom.setText("");
					CPQuaCombox.setSelectedIndex(0);
					CPZoonCombox.setSelectedIndex(0);
					CPTypeCombox.setSelectedIndex(0);
				}
				CPBroker.closeBroker();
			}catch (NumberFormatException | InvalidLegalDescriptionException e1) {
				
				e1.printStackTrace();
			}
		}	
	}
}
