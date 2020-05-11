package com.deepanshu.publish.main;

import java.util.List;

import com.deepanshu.publish.constants.Constants;
import com.deepanshu.publish.impl.ProcessCSVReadprocessing;
import com.deepanshu.publish.impl.ProcessCSVReportoutput;
import com.deepanshu.publish.interfaces.IReadInputFile;
import com.deepanshu.publish.interfaces.IReportPublish;
import com.deepanshu.publish.model.CurrencyTo;

public class CSVReadWriteMain {
	
	public boolean getReadWriteProcessing() {
		String inputLoc = Constants.INPUT_FILE_LOC;
		System.out.println("Input File Path"+inputLoc);
		
		String outputLoc = Constants.OUTPUT_FILE_LOC;
		
		List<CurrencyTo> inputFinalList=null;
		
		String extension=null;
		
		extension=inputLoc.substring(inputLoc.indexOf('.')+1);
		System.out.println("Extension of file is+"+extension);
		
		try {
		if(inputLoc !=null && extension.equalsIgnoreCase("CSV")) {
			System.out.println("Inside If");
			IReadInputFile inputRead= new ProcessCSVReadprocessing();
			
			 inputFinalList = inputRead.readInputFileProcessing(inputLoc);
		}
		
		IReportPublish reportPub = new ProcessCSVReportoutput();
		
		reportPub.processCSVToOutPutReport( outputLoc, inputFinalList );

	}
	catch(Exception e){
		System.out.println("In catch");
		e.printStackTrace();
	}finally {
		inputFinalList=null;
	}
		return true;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		CSVReadWriteMain obj= new CSVReadWriteMain();
		try {
		obj.getReadWriteProcessing();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		

}}
