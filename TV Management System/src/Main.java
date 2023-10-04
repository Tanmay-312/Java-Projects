import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.text.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import javax.swing.table.*;


@SuppressWarnings("serial")
public class Main extends JFrame
{
	// Panel1: User Registration
	JPanel subsPanel;

	JTextField subsName;
	JTextField subsLastName;
	JTextField subsPhone;
	JTextField subsCity;

	JLabel nameLabel;
	JLabel lastLabel;
	JLabel mobileLabel;
	JLabel cityLabel;

	// Panel2: Subscription cycle
	JTextField startCycle;
	JTextField endCycle;
	JTextField numberTV;
	JLabel todayLabel;
	JLabel startCycleLabel;
	JLabel endCycleLabel;
	JLabel numberTVLabel;
	JPanel cyclePanel;
	SimpleDateFormat df;
	Date currentDate;

	// Panel3: Channel's packages
	JCheckBox sportsCB;
	JCheckBox moviesCB;
	JCheckBox docCB;
	JPanel packagesPanel;
	
	// Panel4: Package details
	JTextArea CAsports;
	JTextArea CAmovies;
	JTextArea CAdoc;
	JPanel detailsPanel;
	
	// Panel5: Check and Payments
	JLabel installFeeLabel;
	JPanel feePanel;
	JLabel packageFeeLabel;
	JLabel totalFeeLabel;
	
	// Panel6: table (data of subscription)
	JTable table;
	DefaultTableModel tableModel;
	JPanel tablePanel;
	
	// Panel7: Action panel
	JButton saveButton;
	JButton loadButton;
	JButton newButton;
	JPanel actionPanel;
	
	
	// Classes, Objects and Variables
	Subscriber subscriber;
	Subscription subscription;
	int packagesSelectedPrice = 0;
	int totalPrice;
	
	// Saving
	ArrayList<Subscription> listToSave = new ArrayList<>();
	File file;
	
	
	
	
	// constructor
	public Main()
	{
		//******** Panel 1 ********//
		//JPanel
		subsPanel = new JPanel();
		Border subsTitle = BorderFactory.createTitledBorder("Subscriber Details");
		subsPanel.setBorder(subsTitle);
		subsPanel.setBounds(15,15,300,200);
		subsPanel.setLayout(new GridLayout(4,2));

		// JLabel
		nameLabel = new JLabel("Name: ");
		lastLabel = new JLabel("Last name: ");
		mobileLabel = new JLabel("Mobile number: ");
		cityLabel = new JLabel("City: ");

		// JTextField
		subsName = new JTextField();
		subsName.setOpaque(false);
		subsLastName = new JTextField();
		subsLastName.setOpaque(false);
		subsPhone = new JTextField();
		subsPhone.setOpaque(false);
		subsCity = new JTextField();
		subsCity.setOpaque(false);

		// Adding components to panel1
		subsPanel.add(nameLabel);
		subsPanel.add(subsName);
		subsPanel.add(lastLabel);
		subsPanel.add(subsLastName);
		subsPanel.add(mobileLabel);
		subsPanel.add(subsPhone);
		subsPanel.add(cityLabel);
		subsPanel.add(subsCity);




		//******** Panel 2 ********//
		cyclePanel = new JPanel();
		cyclePanel.setBounds(15,230,300,500);
		cyclePanel.setLayout(new GridLayout(14,1));

		Border cycleTitle = BorderFactory.createTitledBorder("Cycle Details");
		cyclePanel.setBorder(cycleTitle);

		todayLabel = new JLabel();
		df = new SimpleDateFormat("dd/MM/yyyy");
		currentDate = new Date();
		todayLabel.setText("Today: "+df.format(currentDate));

		// cycle date and TV count
		startCycleLabel = new JLabel("Start cycle date: ");
		startCycle = new JTextField();
		startCycle.setOpaque(false);
		endCycleLabel = new JLabel("End cycle date: ");
		endCycle = new JTextField();
		endCycle.setOpaque(false);
		numberTVLabel = new JLabel("Number of TV: ");
		numberTV = new JTextField();
		numberTV.setOpaque(false);		

		// Adding
		cyclePanel.add(todayLabel);
		cyclePanel.add(startCycleLabel);
		cyclePanel.add(startCycle);
		cyclePanel.add(endCycleLabel);
		cyclePanel.add(endCycle);
		cyclePanel.add(numberTVLabel);
		cyclePanel.add(numberTV);





		//******** Panel 3 ********//
		packagesPanel = new JPanel();
		packagesPanel.setBounds(330,15,300,200);
		packagesPanel.setLayout(new GridLayout(5,1));
		
		Border packTitle = BorderFactory.createTitledBorder("Available Packages");
		packagesPanel.setBorder(packTitle);
		
		JLabel packagesLabel = new JLabel("Please select your package");
		
		sportsCB = new JCheckBox("Sports Package");
		moviesCB = new JCheckBox("Movies Package");
		docCB = new JCheckBox("Documentary Package");
		
		JButton subsButton = new JButton("Subscribe");
		
		packagesPanel.add(packagesLabel);
		packagesPanel.add(sportsCB);
		packagesPanel.add(moviesCB);
		packagesPanel.add(docCB);
		packagesPanel.add(subsButton);
		
		sportsCB.addChangeListener(new ChangeListener()
		{
			@Override
			public void stateChanged(ChangeEvent e)
			{
				if(sportsCB.isSelected())
					DisplaySportsChannel();
				else
				{
					
				}
			}
			
		});
		
		moviesCB.addChangeListener(new ChangeListener()
		{
			@Override
			public void stateChanged(ChangeEvent e)
			{
				if(moviesCB.isSelected())
					DisplayMoviesChannel();
				else
				{
					
				}
			}
			
		});
		
		docCB.addChangeListener(new ChangeListener()
		{
			@Override
			public void stateChanged(ChangeEvent e)
			{
				if(docCB.isSelected())
					DisplayDocChannel();
				else
				{
					
				}
			}
			
		});
		
		subsButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					GetSubsData();
				}
				catch(Exception ee)
				{
					
				}
			}
			
		});
		
		
		
		
		//******** Panel 4 ********//
		detailsPanel = new JPanel();
		detailsPanel.setBounds(330,230,300,500);
		detailsPanel.setLayout(new GridLayout(3,1));
		
		Border detailTitle = BorderFactory.createTitledBorder("Available Channels");
		detailsPanel.setBorder(detailTitle);
		
		CAsports = new JTextArea(5,1);
		CAsports.setEditable(false);
		CAsports.setOpaque(false);
		CAsports.setLineWrap(true);
		
		CAmovies = new JTextArea(5,1);
		CAmovies.setEditable(false);
		CAmovies.setOpaque(false);
		CAmovies.setLineWrap(true);
		
		CAdoc = new JTextArea(5,1);
		CAdoc.setEditable(false);
		CAdoc.setOpaque(false);
		CAdoc.setLineWrap(true);
		
		detailsPanel.add(CAsports);
		detailsPanel.add(CAmovies);
		detailsPanel.add(CAdoc);
		
		
		
		
		//******** Panel 5 ********//
		feePanel = new JPanel();
		feePanel.setBounds(645,15,200,200);
		feePanel.setLayout(new GridLayout(3,1));
		
		Border feeTitle = BorderFactory.createTitledBorder("Fee and Check");
		feePanel.setBorder(feeTitle);
		
		installFeeLabel = new JLabel("Installation fee: ");
		packageFeeLabel = new JLabel("Packages fee: ");
		totalFeeLabel = new JLabel("Total amount to pay: ");
		
		feePanel.add(installFeeLabel);
		feePanel.add(packageFeeLabel);
		feePanel.add(totalFeeLabel);
		
		
		
		
		//******** Panel 6 ********//
		tablePanel = new JPanel();
		tablePanel.setBounds(645,230,515,500);
		tablePanel.setLayout(new GridLayout(3,1));

		Border tableTitle = BorderFactory.createTitledBorder("Customer data");
		tablePanel.setBorder(tableTitle);
		
		// table model
		tableModel = new DefaultTableModel();
		// columns
		table = new JTable(tableModel);
		tableModel.addColumn("First Name");
		tableModel.addColumn("Last Name");
		tableModel.addColumn("Phone Number");
		tableModel.addColumn("Start cycle");
		tableModel.addColumn("End cycle");
		tableModel.addColumn("Total fee");
		// scroll pane
		JScrollPane scrollPane = new JScrollPane(table);
		tablePanel.add(scrollPane);
		
		
		
		
		//******** Panel 7 ********//
		actionPanel = new JPanel();
		actionPanel.setBounds(860,15,300,200);
		actionPanel.setLayout(new GridLayout(4,1));

		Border actionTitle = BorderFactory.createTitledBorder("Action Tab");
		feePanel.setBorder(actionTitle);

		saveButton = new JButton("Save");
		loadButton = new JButton("Load");
		newButton = new JButton("New");

		actionPanel.add(saveButton);
		actionPanel.add(loadButton);
		actionPanel.add(newButton);
		
		
		saveButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					SaveSubscriptionToDisk();
				}
				catch (IOException e1)
				{
					e1.printStackTrace();
				}
			}
			
		});
		
		newButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				NewSubscription();
			}
			
		});
		
		loadButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					LoadDataFromDisk();
				}
				catch (ClassNotFoundException e1)
				{
					e1.printStackTrace();
				}
				catch (IOException e1)
				{
					e1.printStackTrace();
				}
			}
			
		});
		


		
		
		//******** Adding panels to JFrame ********//
		setLayout(null);		// null layout for JFrame
		add(subsPanel);
		add(cyclePanel);
		add(packagesPanel);
		add(detailsPanel);
		add(feePanel);
		add(tablePanel);
		add(actionPanel);
	}

	
	
	
	// Methods
	
	private int DisplaySportsChannel()
	{
		SportsChannel m1 = new SportsChannel("Sony TEN Hindi", "HI", "SPORTS", 3);
		SportsChannel m2 = new SportsChannel("Sony TEN", "EN", "SPORTS", 3);
		SportsChannel m3 = new SportsChannel("Star Sports HD", "HI", "SPORTS", 5);
		SportsChannel m4 = new SportsChannel("Star Sports", "HI", "DOC", 4);
		SportsChannel m5 = new SportsChannel ("ESPN", "HI", "SPORTS", 4);
		SportsChannel m6 = new SportsChannel("DD Sports", "HI", "SPORTS", 2);
		
		ArrayList<TVChannel> sportsChannels = new ArrayList<>();
		sportsChannels.add(m1);
		sportsChannels.add(m2);
		sportsChannels.add(m3);
		sportsChannels.add(m4);
		sportsChannels.add(m5);
		sportsChannels.add(m6);
		
		String sportsChannelString = "";
		int packagePrice = 0;
		
		for(int i=0; i<sportsChannels.size(); i++)
		{
			sportsChannelString +=
					" "+ sportsChannels.get(i).getChannelName()
					+ " 	"+ sportsChannels.get(i).getLanguage()
					+ " 	"+ sportsChannels.get(i).getPrice()
					+ "\n";
			
			packagePrice+= sportsChannels.get(i).getPrice();
		}
		
		CAsports.setText(sportsChannelString);
		
		return packagePrice;

	}
	
	private int DisplayMoviesChannel()
	{
		MoviesChannel m1 = new MoviesChannel("Sony MAX", "HI", "MOVIES", 3);
		MoviesChannel m2 = new MoviesChannel("Sony MAX 2", "EN", "MOVIES", 4);
		MoviesChannel m3 = new MoviesChannel("Star Movies HD", "HI", "MOVIES", 5);
		MoviesChannel m4 = new MoviesChannel("Star Movies", "HI", "DOC", 3);
		MoviesChannel m5 = new MoviesChannel("&pictures", "HI", "MOVIES", 3);
		MoviesChannel m6 = new MoviesChannel("ZEE cinema", "HI", "MOVIES", 3);
		
		ArrayList<TVChannel> moviesChannels = new ArrayList<>();
		moviesChannels.add(m1);
		moviesChannels.add(m2);
		moviesChannels.add(m3);
		moviesChannels.add(m4);
		moviesChannels.add(m5);
		moviesChannels.add(m6);
		
		String moviesChannelString = "";
		int packagePrice = 0;
		
		for(int i=0; i<moviesChannels.size(); i++)
		{
			moviesChannelString +=
					" "+ moviesChannels.get(i).getChannelName()
					+ " 	"+ moviesChannels.get(i).getLanguage()
					+ " 	"+ moviesChannels.get(i).getPrice()
					+ "\n";
			
			packagePrice+= moviesChannels.get(i).getPrice();
		}
		
		CAmovies.setText(moviesChannelString);

		return packagePrice;
	}
	
	private int DisplayDocChannel()
	{
		DocumentaryChannel m1 = new DocumentaryChannel("NAT GEO", "EN", "DOC", 3);
		DocumentaryChannel m2 = new DocumentaryChannel("Discovery", "EN", "DOC", 3);
		DocumentaryChannel m3 = new DocumentaryChannel("Al Jazeera", "EN", "DOC", 5);
		DocumentaryChannel m4 = new DocumentaryChannel("Historica", "EN", "DOC", 4);
		DocumentaryChannel m5 = new DocumentaryChannel("World Documentary", "EN", "DOC", 4);
		DocumentaryChannel m6 = new DocumentaryChannel("Discovery hindi", "HI", "DOC", 2);
		
		ArrayList<TVChannel> documentaryChannels = new ArrayList<>();
		documentaryChannels.add(m1);
		documentaryChannels.add(m2);
		documentaryChannels.add(m3);
		documentaryChannels.add(m4);
		documentaryChannels.add(m5);
		documentaryChannels.add(m6);
		
		String docChannelString = "";
		int packagePrice = 0;
		
		for(int i=0; i<documentaryChannels.size(); i++)
		{
			docChannelString +=
					" "+ documentaryChannels.get(i).getChannelName()
					+ " 	"+ documentaryChannels.get(i).getLanguage()
					+ " 	"+ documentaryChannels.get(i).getPrice()
					+ "\n";
			
			packagePrice+= documentaryChannels.get(i).getPrice();
		}
		
		CAdoc.setText(docChannelString);
		
		return packagePrice;
	}
	
	private void GetSubsData() throws ParseException
	{
		Date currentDate = new Date();
		
		// Subscriber data
		subscriber = new Subscriber(
				subsName.getText(),
				subsLastName.getText(),
				subsCity.getText(),
				Integer.parseInt(subsPhone.getText()));
		
		// cycle
		Date start = df.parse(startCycle.getText());
		Date end = df.parse(endCycle.getText());
		
		SubscriptionCycle cycle = new SubscriptionCycle(
				df.format(start),
				df.format(end));
		
		// subscription
		subscription = new Subscription(
				Integer.parseInt(numberTV.getText()),
				subscriber,
				cycle,
				df.format(currentDate));
		
		installFeeLabel.setText("Installation fee: " + subscription.getTotalFee());
		showPrice();
	}
	
	private void showPrice()
	{
		if(docCB.isSelected())
			packagesSelectedPrice += DisplayDocChannel();
		if(sportsCB.isSelected())
			packagesSelectedPrice += DisplaySportsChannel();
		if(moviesCB.isSelected())
			packagesSelectedPrice += DisplayMoviesChannel();
		
		packageFeeLabel.setText("Packages Fee: " + packagesSelectedPrice);
		totalPrice = subscription.getTotalFee() + packagesSelectedPrice;
		
		totalFeeLabel.setText("Total amount to pay: "+totalPrice);
	}
	
	private void SaveSubscriptionToDisk() throws IOException
	{
		listToSave.add(subscription);
		file = new File("C:/Users/Tanmay Jain/Desktop/Java Lang/TV Management System/src/data.dat");
		
		try
		{
			OutputStream os = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(os);
			
			oos.writeObject(listToSave);
			oos.flush();
			oos.close();
			os.close();
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
	}
	
	private void NewSubscription()
	{
		// All fields are empty
		subsName.setText("");
		subsLastName.setText("");
		subsCity.setText("");
		subsPhone.setText("");
		
		startCycle.setText("");
		endCycle.setText("");
		numberTV.setText("");
		
		installFeeLabel.setText("Installation fee: ");
		packageFeeLabel.setText("Packages fee");
		totalFeeLabel.setText("Total amount to pay: ");
		
		moviesCB.setSelected(false);
		docCB.setSelected(false);
		sportsCB.setSelected(false);
	}
	
	@SuppressWarnings("unchecked")
	private void LoadDataFromDisk() throws IOException, ClassNotFoundException
	{
		ArrayList<Subscription> s = new ArrayList<>();
		file = new File("C:/Users/Tanmay Jain/Desktop/Java Lang/TV Management System/src/data.dat");
		
		try
		{
			InputStream is = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(is);
			
			s = (ArrayList<Subscription>) ois.readObject();
			ois.close();
			is.close();
		}
		
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		
		
		for (Subscription sub: s)
		{
			DisplaySubscriptionInTable(sub);
		}
	}
	
	private void DisplaySubscriptionInTable(Subscription sub)
	{
		tableModel.addRow(new Object[]
		{
			sub.getSubscriber().getfName(),
			sub.getSubscriber().getlName(),
			sub.getSubscriber().getPhone(),
			sub.getCycle().getStartDate(),
			sub.getCycle().getEndDate(),
			sub.getTotalFee()
		});
	}




	// Main method
	public static void main(String[] args)
	{
		Main main = new Main();
		main.setVisible(true);
		main.setBounds(0,0,1200,800);
	}

}
