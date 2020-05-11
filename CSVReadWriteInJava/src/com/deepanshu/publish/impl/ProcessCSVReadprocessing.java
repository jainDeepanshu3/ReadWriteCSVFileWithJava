package com.deepanshu.publish.impl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.deepanshu.publish.constants.Constants;
import com.deepanshu.publish.interfaces.IReadInputFile;
import com.deepanshu.publish.model.CurrencyTo;

public class ProcessCSVReadprocessing implements IReadInputFile {

	@Override
	public List<CurrencyTo> readInputFileProcessing(String inputFileloc) {
		// TODO Auto-generated method stub
		
		List<CurrencyTo> inputReadList = new ArrayList<CurrencyTo>();
		
		String line="";
		String [] dataFields;
		
		try{
			@SuppressWarnings("resource")
			BufferedReader br = new BufferedReader(new FileReader(inputFileloc));
			
			while((line = br.readLine()) !=null) {
				dataFields= line.split(Constants.CSV_DELIMITER);
				if (dataFields.length < 5 || dataFields[0].equalsIgnoreCase("city") || dataFields[1].equalsIgnoreCase("country") || dataFields[2].equalsIgnoreCase("gender")
						|| dataFields[3].equalsIgnoreCase("currency") || dataFields[4].equalsIgnoreCase("amount"))
				 continue;
				
				CurrencyTo ct= new CurrencyTo();
				String city=dataFields[0];
				System.out.println("City is"+dataFields[0]);
				String country=dataFields[1];
				System.out.println("Country is"+country);
				String gender=dataFields[2];
				String currency=dataFields[3];
				double amount=Double.parseDouble(dataFields[4]);
				
				ct.setCity(city);
				ct.setCountry(country);
				ct.setGender(gender);
				ct.setCurrency(currency);
				ct.setAmount(amount);
				
				inputReadList.add(ct);
				
				
				
			}
			
			/*
			 * Comparator<CurrencyTo> compareByName =
			 * Comparator.comparing(CurrencyTo::getCity).thenComparing(CurrencyTo::
			 * getCountry).thenComparing(CurrencyTo::getGender); //List<CurrencyTo>
			 * newFinalList=inputReadList.stream().sorted(compareByName).collect(Collectors.
			 * toList()); System.out.println("List is"+newFinalList);
			 * 
			 * inputReadList=inputReadList.stream().sorted(compareByName).collect(Collectors
			 * .toList());
			 */
			  
			  
			  
			 
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			line="";
			dataFields=null;
		}
		
		
		return inputReadList;
	}

}
