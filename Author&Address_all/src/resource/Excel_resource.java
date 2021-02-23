package resource;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import application.Index;

public class Excel_resource {

	public static ArrayList<String> hold1;
	public static ArrayList<String> hold2;
	
	public static ArrayList<String> country_code1;
	public static ArrayList<String> country_code2;
	
	public static ArrayList<String> country_list;
	
	public static ArrayList<String> Particle_list;
	
	public static void new_resource() throws IOException{
		hold1 = new ArrayList<String>();
		hold2 = new ArrayList<String>();
		country_list = new ArrayList<String>();
		
		country_code1 = new ArrayList<String>();
		country_code2 =	new ArrayList<String>();
		
		Particle_list = new ArrayList<String>();
		
		FileInputStream file = new FileInputStream(new File("C:\\Address_test_all\\Author&Address_all\\DATA\\resource_db.xlsx"));
		XSSFWorkbook wb = new XSSFWorkbook(file);
		XSSFSheet sheet = wb.getSheetAt(0);
		try{
		for(int j=0;j<sheet.getLastRowNum()+1;j++){
		
			Row r = sheet.getRow(j);	
			Cell ce = r.getCell(0);
			Cell c2 = r.getCell(1);
			
			hold1.add(ce.toString());
			hold2.add(c2.toString());
					
		}
		}catch(Exception e){
			System.out.println("Abbreviation list not loaded");
			JOptionPane.showMessageDialog(Index.frmJava, "ABBREVIATION & WORD ENDING NOT LOADED", "", JOptionPane.WARNING_MESSAGE);
			System.exit(0);
		}
		
		if(hold1.size()!=hold2.size()){
			JOptionPane.showMessageDialog(Index.frmJava, "ABBREVIATION & WORD ENDING NOT LOADED", "", JOptionPane.WARNING_MESSAGE);
			System.exit(0);
			}
		
		//TODO========================================= 
		
		XSSFSheet c_sheet = wb.getSheetAt(1);
		try{
		for(int c=0;c<c_sheet.getLastRowNum()+1;c++){
			Row r_country = c_sheet.getRow(c);	
			Cell ce_country = r_country.getCell(0);
			country_list.add(ce_country.toString());
		}
			
		if(country_list.size()!=sheet.getLastRowNum()){
		System.out.println("country_list loaded");
		}else{
			System.out.println("country_list not loaded");
			JOptionPane.showMessageDialog(Index.frmJava, "COUNTRY LIST NOT LOADED", "", JOptionPane.WARNING_MESSAGE);
			System.exit(0);	
		}
		}catch(Exception e){
			System.out.println("country_list not loaded");
			JOptionPane.showMessageDialog(Index.frmJava, "COUNTRY LIST NOT LOADED", "", JOptionPane.WARNING_MESSAGE);
			System.exit(0);	
		}
		
		
		//TODO=========================================
		
		XSSFSheet cc_sheet = wb.getSheetAt(2);
		
		try{
			for(int cc=0;cc<cc_sheet.getLastRowNum()+1;cc++){
				Row r_country_code = cc_sheet.getRow(cc);	
				Cell cell_country_code1 = r_country_code.getCell(0);
				Cell cell_country_code2 = r_country_code.getCell(1);
			
			country_code1.add(cell_country_code1.toString());
			country_code2.add(cell_country_code2.toString());
				
			}
				
			
			}catch(Exception e){
				e.printStackTrace();
				System.out.println("countryCODE_list not loaded");
				JOptionPane.showMessageDialog(Index.frmJava, "COUNTRY CODE LIST NOT LOADED", "", JOptionPane.WARNING_MESSAGE);
				System.exit(0);	
			}
		
		if(country_code1.size()!=country_code2.size()){
			JOptionPane.showMessageDialog(Index.frmJava, "COUNTRY CODE LIST NOT LOADED", "", JOptionPane.WARNING_MESSAGE);
			System.exit(0);
			}
		
		
		//TODO=========================================
		
				XSSFSheet pl_sheet = wb.getSheetAt(3);
				
				try{
					for(int pl=0;pl<pl_sheet.getLastRowNum()+1;pl++){
						Row particle_list_row = pl_sheet.getRow(pl);	
						Cell particle_list1 = particle_list_row.getCell(0);
						Particle_list.add(particle_list1.toString());
					}
				}catch(Exception e){
					e.printStackTrace();
					System.out.println("Particle_list not loaded");
					JOptionPane.showMessageDialog(Index.frmJava, "PARTICLE LIST NOT LOADED", "", JOptionPane.WARNING_MESSAGE);
					System.exit(0);	
				}
		
	wb.close();
	file.close();
	System.out.println("resource load complete.");
	
	}
	}

