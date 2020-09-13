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
	
	public static ArrayList<String> country_list;
	
	public static void new_resource() throws IOException{
		hold1 = new ArrayList<String>();
		hold2 = new ArrayList<String>();
		country_list = new ArrayList<String>();
		
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
		
		
	wb.close();
	file.close();
	System.out.println("resource load complete.");
	
	}
	}

