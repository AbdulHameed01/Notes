package TextEditor;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.text.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class textEditor extends JFrame implements ActionListener{
	
	JTextArea textArea;
	JScrollPane scrollpane;
	JButton button;
	JSpinner ScaleFontText;
	JLabel label;
	JComboBox fontbox;
	JMenuBar menuBar;
	JMenu filemenu;
	JMenuItem Open;
	JMenuItem Save;
	JMenuItem Exit;
	ImageIcon imgopen;	
	ImageIcon imgsave;	
	ImageIcon imgexit;	
	JRadioButton button2;
	JRadioButton button3;
	Calendar calender;
	JLabel label1;
	String Format;
	SimpleDateFormat time;
	
	textEditor(){
		
		ImageIcon image4 = new ImageIcon("folder.png");
		Image resizedImage5 = image4.getImage().getScaledInstance(10, 10, Image.SCALE_SMOOTH);
		imgopen = new ImageIcon(resizedImage5);
		ImageIcon image5 = new ImageIcon("save.png");
		Image resizedImage6 = image5.getImage().getScaledInstance(10, 10, Image.SCALE_SMOOTH);
		imgsave = new ImageIcon(resizedImage6);
		ImageIcon image6 = new ImageIcon("exit.png");
		Image resizedImage7 = image6.getImage().getScaledInstance(10, 10, Image.SCALE_SMOOTH);
		imgexit = new ImageIcon(resizedImage7);
		ImageIcon boii = new ImageIcon("Boii2.jpg");
		
		time = new SimpleDateFormat();
		
		label1 = new JLabel();		
		
		button2 = new JRadioButton("Dark mode");
		button2.setFocusable(false);
		button2.addActionListener(this);
		
		
		button3 = new JRadioButton("Light mode");
		button3.setFocusable(false);
		button3.addActionListener(this);
		
		ButtonGroup grp = new ButtonGroup();
		grp.add(button3);
		grp.add(button2);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1100, 700);
		this.setTitle("Abd's Notes");
		this.setResizable(false);
		this.setIconImage(boii.getImage());
		this.getContentPane().setBackground(Color.white);
		this.setLocationRelativeTo(null);
		
		textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setForeground(Color.green);
		textArea.setFont(new Font("Arial",Font.PLAIN,20));
		textArea.setBackground(Color.white);
		
		scrollpane = new JScrollPane(textArea);
		scrollpane.setPreferredSize(new Dimension(1050,600));
		scrollpane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		label = new JLabel("Font Size : ");
		label.setForeground(Color.black);
		label.setFont(new Font("Arial",Font.PLAIN,15));
		
		String[] font = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
		
		fontbox = new JComboBox(font);
		fontbox.addActionListener(this);
		fontbox.setSelectedItem("Arial");
		
		ScaleFontText = new JSpinner();
		ScaleFontText.setPreferredSize(new Dimension(50,20));
		ScaleFontText.setValue(20);
		ScaleFontText.addChangeListener(new ChangeListener(){

			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				textArea.setFont(new Font(textArea.getFont().getFamily(),Font.PLAIN,(int) ScaleFontText.getValue()));
				
			}
			
			
		});
		
		//----------------------Menubar----------------------------
		
			menuBar = new JMenuBar();
			filemenu = new JMenu("File");
			Open = new JMenuItem("Open");
			Save = new JMenuItem("Save");
			Exit = new JMenuItem("Exit");
			
			menuBar.add(filemenu);
			filemenu.add(Open);
			filemenu.add(Save);
			filemenu.add(Exit);
			
			Open.addActionListener(this);
			Save.addActionListener(this);
			Exit.addActionListener(this);
			
			Open.setIcon(imgopen);
			Save.setIcon(imgsave);
			Exit.setIcon(imgexit);
			
		
		//---------------------------------------------------------
		
	
		button = new JButton("Choose Color");
		button.setFocusable(false);
		button.setBackground(Color.white);
		button.addActionListener(this);
		
		this.setJMenuBar(menuBar);
		this.add(button);
		this.add(label);
		this.add(ScaleFontText);
		this.add(fontbox);
		this.add(button3);
		this.add(button2);
		this.add(label1);
		this.add(scrollpane);
		this.setLayout(new FlowLayout());
		this.setVisible(true);
		
		Time();
		
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource()==button) {
			
			JColorChooser colorchooser = new JColorChooser();			
			Color color = JColorChooser.showDialog(null, "Choose a color",Color.WHITE);
			textArea.setForeground(color);			
			
		}
		
		if(e.getSource()==fontbox) {
			
			textArea.setFont(new Font((String) fontbox.getSelectedItem(),Font.PLAIN,textArea.getFont().getSize()));
			
		}
		
		if(e.getSource()==Open) {
			
			JFileChooser filechooser = new JFileChooser();
			filechooser.setCurrentDirectory(new File("C:\\Abd's Note"));
			FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files","txt");
			filechooser.setFileFilter(filter);
			
			int response = filechooser.showOpenDialog(null);
			
			if(response == filechooser.APPROVE_OPTION) {
				
				File file = new File(filechooser.getSelectedFile().getAbsolutePath());
				Scanner filein = null;
				
				try {
					filein = new Scanner(file);
					if(file.isFile()) {
						while(filein.hasNextLine()) {
							
							String line = filein.nextLine()+"\n";
							textArea.append(line);
							
						}
					}
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				finally {
					filein.close();
				}
				
				
			}
			
			
		}
		
		if(e.getSource()==Save) {
			
			JFileChooser filechooser = new JFileChooser();
			filechooser.setCurrentDirectory(new File("C:\\Abd's Note"));
			
			int response = filechooser.showSaveDialog(null);
			
			if(response == filechooser.APPROVE_OPTION) {
				
				File file;
				PrintWriter fileout=null;
				
				file = new File(filechooser.getSelectedFile().getAbsolutePath());
				try {
					fileout = new PrintWriter(file);
					fileout.println(textArea.getText());
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}		
				finally {
					fileout.close();
				}
				
			}
			
		
		
		if(e.getSource()==Exit) {
			
			System.exit(0);
			
		}
		
		if(e.getSource()==button2) {
			
			this.getContentPane().setBackground(Color.DARK_GRAY);
			textArea.setBackground(Color.DARK_GRAY);
			label.setForeground(Color.white);
			label1.setForeground(Color.white);
			
		}
		if(e.getSource()==button3) {
			
			this.getContentPane().setBackground(Color.white);
			textArea.setBackground(Color.white);
			label.setForeground(Color.black);
			label1.setForeground(Color.black);
			
		}
	  }
	}
	
	void Time() {
		
		while(true) {
			Format = time.format(Calendar.getInstance().getTime());
			label1.setText(Format);
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	

}
