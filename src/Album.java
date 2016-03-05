import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics2D;
import java.awt.GridLayout;

import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;



import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.image.*;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.DefaultCaret;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;
import java.awt.Rectangle;
import java.awt.Component;


public class Album {
	JList list;
	JPanel display;
	JLabel pic;
	JLabel annot;
	JLabel title ;
	private JFrame frame;
	ArrayList<String> names= new ArrayList<String>();
	ArrayList<String> annots= new ArrayList<String>();
	ArrayList<ImageIcon> images= new ArrayList<ImageIcon>();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Album window = new Album();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Album() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setSize(new Dimension(1800,800));
		frame.getContentPane().setPreferredSize(new Dimension(1500, 800));
		frame.setBounds(100, 100, 750, 595);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		JPanel display = new JPanel();
		display.setBackground(Color.WHITE);
		display.setBounds(348, 44, 390, 493);
		 pic=new JLabel ();
		 pic.setBounds(new Rectangle(0, 0, 300, 300));
		 display.add(pic);
		frame.getContentPane().add(display);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 12, 262, 217);
		frame.getContentPane().add(scrollPane);
		annot = new JLabel();
		annot.setBackground(new Color(255, 255, 255));
		annot.setBounds(22, 251, 231, 69);
		frame.getContentPane().add(annot);
		title = new JLabel();
		title.setBounds(408, 14, 202, 15);
		frame.getContentPane().add(title);
		list = new JList();
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				next((String)(list.getSelectedValue()));
			}
		});
		
		
		scrollPane.setViewportView(list);
		
		JButton btnNew = new JButton("new");
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ar) {
				
				JFileChooser jFileChooser = new JFileChooser();
				//jFileChooser.setCurrentDirectory(new File("/User/alvinreyes"));
				
				int result = jFileChooser.showOpenDialog(new JFrame());
			
			
				if (result == JFileChooser.APPROVE_OPTION) {
					try{
				    File selectedFile = jFileChooser.getSelectedFile();
				   ImageIcon n = new ImageIcon( (String)selectedFile.getAbsolutePath());
				   /*BufferedImage resizedImage = new BufferedImage(200, 200, BufferedImage.TYPE_INT_ARGB);
		            Graphics2D g = resizedImage.createGraphics();
		            g.drawImage(n.getImage(), 0, 0, 200, 200, null);
		            n.setImage(resizedImage);
				  */
				  
				   
				   
				   JTextField titles = new JTextField(20);
				      JTextArea anno = new JTextArea();
				      anno.setAutoscrolls(true);
				      anno.setBackground(Color.WHITE);
				   

				      JPanel myPanel = new JPanel();
				      myPanel.setBounds(new Rectangle(65, 24, 50, 50));
				  
				      myPanel.setLayout(new GridLayout(0,2,3,10));
				      JLabel t=new JLabel("title:");
				      
				      
				      myPanel.add(t);
				      myPanel.add(titles);
				      //myPanel.add(Box.createVerticalStrut(15)); // a spacer
				      
				      JLabel a=new JLabel("annotations:");
				     //anno.setCaretPosition(anno.getDocument().getLength());
				     anno.setLineWrap(true);
				     anno.setRows(2);
				     anno.setSize(new Dimension(20,20));
				     JScrollPane scrollPan = new JScrollPane();
						scrollPan.setSize(new Dimension(20,20));
						myPanel.add(scrollPan);
						scrollPan.setViewportView(anno);
				      a.setSize(new Dimension(1,1));
				      myPanel.add(a);
				      myPanel.add(scrollPan);

				      int opt = JOptionPane.showConfirmDialog(null, myPanel, "Please Enter Tiltle and Annotation", JOptionPane.OK_CANCEL_OPTION);
				     if((n.getIconWidth()>390)||(n.getIconHeight()>490)){
				    	 BufferedImage resizedImage = new BufferedImage(380, 490, BufferedImage.TYPE_INT_RGB);
				            Graphics2D g = resizedImage.createGraphics();
				            g.drawImage(n.getImage(), 0, 0, 390, 490, null);
				            
				            
				           n.setImage(resizedImage);
				           g.dispose();

				     }
				      if (opt == JOptionPane.OK_OPTION) {
				         names.add(titles.getText());
				         annots.add(anno.getText());
				         change();
				         images.add(n);
				         next(names.get(names.size()-1));
						  
				      }

				
					
				   
					}
					catch(Exception e){
				  
					}
				}
				
			}
		});
		btnNew.setBounds(130, 329, 90, 30);
		frame.getContentPane().add(btnNew);
		
		JButton btnNext = new JButton("Next");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent nex) {
				Icon cur=pic.getIcon();
				int n=images.indexOf(cur);
				if(n<images.size()-1){
					next(n+1);
				}
				else{
					next(0);
					
				}
				
				
			}
		});
		btnNext.setBounds(241, 332, 90, 25);
		frame.getContentPane().add(btnNext);
		
		JButton btnPrevious = new JButton("Previous");
		btnPrevious.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent prev) {
				Icon cur=pic.getIcon();
				int n=images.indexOf(cur);
				if(n>0){
					next(n-1);
					
				
				}
				else{
					next(images.size()-1);
				}
				
			}
		});
		btnPrevious.setBounds(12, 332, 90, 25);
		frame.getContentPane().add(btnPrevious);
		
	//JOptionPane.showMessageDialog(null, "pic: "+pic.getWidth()+" x"+pic.getHeight()+" panel "+display.getWidth()+" X "+display.getHeight()+" pane "+frame.getContentPane().getWidth()+" X "+frame.getContentPane().getHeight());
		
	
	frame.setResizable(false);
		
		
		
		
		
	}
	
	
	private void next(String name){
		int c=names.indexOf(name);
		pic.setIcon(images.get(c));
		title.setText("Title: "+names.get(c));
		//title.setHorizontalAlignment();
		annot.setText("Annotations: "+annots.get(c));
		
        //display.validate();
        //display.repaint(); 
	}
	
	

	private void next(int c){
		
		pic.setIcon(images.get(c));
		title.setText("Title: "+names.get(c));
		//title.setHorizontalAlignment();
		annot.setText("Annotation :"+annots.get(c));
		
        //display.validate();
        //display.repaint(); 
	}
	private void change(){
		
		list.setListData(names.toArray());
	}
}
