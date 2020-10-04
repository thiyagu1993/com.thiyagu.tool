package application;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.Desktop;

import process.Start;
import udrive.Author_check_udrive;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import output.Html_output;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.awt.event.ActionEvent;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.ImageIcon;

import java.awt.Window.Type;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Label;

import javax.swing.JTextPane;
import javax.swing.DropMode;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;

import java.awt.Canvas;

import javax.swing.JProgressBar;

public class Index {
	
	public static JFrame frmJava;
	public JProgressBar progressBar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Index window = new Index();
					window.frmJava.setVisible(true);
					window.frmJava.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Index() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmJava = new JFrame();
		frmJava.setAlwaysOnTop(true);
		frmJava.setTitle("Address");
		frmJava.setBounds(100, 100, 803, 513);
		frmJava.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmJava.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 787, 74);
		frmJava.getContentPane().add(panel);
		panel.setLayout(null);
		
		progressBar = new JProgressBar(0,100);
		progressBar.setBounds(56, 395, 215, 28);
		frmJava.getContentPane().add(progressBar);
		progressBar.setValue(0);
		progressBar.setStringPainted(true);
		
		JLabel lblNewLabel = new JLabel("Author & Address All");
		lblNewLabel.setBounds(239, 23, 309, 28);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 23));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBackground(Color.WHITE);
		panel.add(lblNewLabel);
		
		JButton btnOutput = new JButton("Output");
		btnOutput.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
			File ex = new File("C:\\Address_test_all\\Author&Address_all\\Excel\\output.html");
			if(!Desktop.isDesktopSupported()){
				
			}else{
				
				Desktop d = Desktop.getDesktop();
				try {
					d.open(ex);
				
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}			
			}	
			}
		});
		btnOutput.setBounds(395, 132, 102, 88);
		btnOutput.setFont(new Font("Tahoma", Font.BOLD, 11));
		frmJava.getContentPane().add(btnOutput);
			
		JButton btnOutpu = new JButton("Exit");
		btnOutpu.setBounds(655, 397, 102, 46);
		btnOutpu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
			System.exit(0);
			}
		});

	
		JButton btnNewButton = new JButton("Run");
		btnNewButton.setBounds(171, 132, 102, 88);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
//				Author_check_udrive.Start_2();
				Start.Start_1();	
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					JOptionPane.showMessageDialog(frmJava, "Please close stats File...","Alert",JOptionPane.WARNING_MESSAGE);
					
				} catch (NoSuchFieldException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		frmJava.getContentPane().add(btnNewButton);
		btnOutpu.setToolTipText("Close Program");
		btnOutpu.setFont(new Font("Tahoma", Font.BOLD, 11));
		frmJava.getContentPane().add(btnOutpu);
		
		JButton btnInstall = new JButton("Reset");
		btnInstall.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int a=JOptionPane.showConfirmDialog(frmJava, "Do you want to Reset ?");
				
				if(a==JOptionPane.YES_OPTION){
					
					Reset.Reset_void();
					
					JOptionPane.showMessageDialog(frmJava, "Completed");
					
				}
				if(a==JOptionPane.NO_OPTION){
					
				}
				
			}
		});
		btnInstall.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnInstall.setBounds(675, 85, 102, 34);
		frmJava.getContentPane().add(btnInstall);
		
		JButton btnInput = new JButton("Input");
		btnInput.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
			File x = new File("C:\\Address_test_all\\Author&Address_all\\Excel\\input.xlsx");
			
			Desktop desk = Desktop.getDesktop();
			if(!Desktop.isDesktopSupported()){
				
			}else{
				try{
				desk.open(x);
				}catch(IOException e){
					e.printStackTrace();
					JOptionPane.showMessageDialog(frmJava, "Input file not found");
				}
			}
						
			}
		});
		btnInput.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnInput.setBounds(56, 132, 102, 88);
		frmJava.getContentPane().add(btnInput);
		
		JButton btnReport = new JButton("Report");
		btnReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				File x2 = new File("C:\\Address_test_all\\Author&Address_all\\Excel\\stats.xlsx");
				
				Desktop desk = Desktop.getDesktop();
				if(!Desktop.isDesktopSupported()){
					
				}else{
					try{
					desk.open(x2);
					}catch(IOException e){
						e.printStackTrace();
						JOptionPane.showMessageDialog(frmJava, "Stats file not found");
					}
				}
			
			}
		});
		btnReport.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnReport.setBounds(283, 132, 102, 88);
		frmJava.getContentPane().add(btnReport);
		
	}
}
