package com.deepanshu.publish.impl;

import java.io.FileWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.deepanshu.publish.interfaces.IReportPublish;
import com.deepanshu.publish.model.CurrencyTo;
import com.deepanshu.publish.model.ReportTo;
import com.deepanshu.publish.util.AmountToDollar;



public class ProcessCSVReportoutput implements IReportPublish {

	@Override
	public void processCSVToOutPutReport(String outPutFileLoc, List<CurrencyTo> inputFileList) {
		// TODO Auto-generated method stub
		
		Map<String, List<CurrencyTo>> avgAmountMap = new HashMap<String, List<CurrencyTo>>();
		
		List<CurrencyTo> currencylist =null;
		
		List<ReportTo> avgIncomeFinalList=null;
		
		String [] keyArray=null;
		
		String country=null;
		
		double usdAmount=0.0;
		
		double avgAmount=0.0;
		
		ReportTo avgReportTo=null;
		
		try {
			
			AmountToDollar utility = new AmountToDollar();
			
			for(CurrencyTo currencyTo : inputFileList) {
			
				country=null;
				if(null != currencyTo.getCountry() && ! currencyTo.getCountry().isEmpty() ) {
					country= currencyTo.getCountry();
					System.out.println("If Country is"+country);
				}else {
					country= currencyTo.getCity();
					System.out.println("Else Country is"+country);
				}
				
				if(avgAmountMap.containsKey(country+"^"+currencyTo.getGender())) {
					currencylist = avgAmountMap.get(country+"^"+currencyTo.getGender());
					currencylist.add(currencyTo);
					avgAmountMap.put(country+"^"+currencyTo.getGender(), currencylist);
					
				}else {
					currencylist=new ArrayList<CurrencyTo>();
					
					currencylist.add(currencyTo);
					avgAmountMap.put(country+"^"+currencyTo.getGender(), currencylist);
					
				}
				
				
			}
			
			if(avgAmountMap!= null) {
				avgIncomeFinalList = new ArrayList<ReportTo>();
				
				for(Map.Entry<String, List<CurrencyTo>> e:avgAmountMap.entrySet()) {
					avgAmount = 0.0;
					avgReportTo= new ReportTo();
					
					for(CurrencyTo cr : e.getValue()) {
						usdAmount = 0.0;
						usdAmount= utility.getDollarFromCurrency(cr.getCurrency(), cr.getAmount());
						avgAmount=(double)(avgAmount+usdAmount);
						
					}
					System.out.println("avg amount is before avg"+avgAmount);
					//avgAmount= avgAmount.divide(new BigDecimal(e.getValue().size()));
					System.out.println("e.getValue().size()"+e.getValue().size());
					avgAmount= (double)(avgAmount/e.getValue().size());
					System.out.println("avg amount is"+avgAmount);
					
					keyArray = e.getKey().split("\\^");
					System.out.println("Country"+keyArray[0]);
					System.out.println("Country"+keyArray[1]);
					avgReportTo.setCountry(keyArray[0]);
					avgReportTo.setGender(keyArray[1]);
					avgReportTo.setAvgAmount(avgAmount);
					avgIncomeFinalList.add(avgReportTo);
					
					
				}
				
			}
			Comparator<ReportTo> compareByName = Comparator.comparing(ReportTo::getCountry).thenComparing(ReportTo::getGender);
			  //List<CurrencyTo> newFinalList=inputReadList.stream().sorted(compareByName).collect(Collectors.toList()); System.out.println("List is"+newFinalList);
			  
			avgIncomeFinalList=avgIncomeFinalList.stream().sorted(compareByName).collect(Collectors.toList());
		    csvWriter(avgIncomeFinalList,outPutFileLoc);
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			usdAmount=0.0;
			country=null;
			avgAmount=0.0;
			avgIncomeFinalList=null;
			
		}
	}
	public void csvWriter(List<ReportTo> finalReportList, String outputLoc) {
		
		String reportFilepath=null;
		
		FileWriter fw=null;
		
		try {
			System.out.println("Come to output part");
		reportFilepath=outputLoc+"\\"+"report.csv";
		
		fw = new FileWriter(reportFilepath);
		
		fw.append("City/Country");
		fw.append(",");
		fw.append("gender");
		fw.append(",");
		fw.append("Average Amount In USD");
		fw.append("\n");
		
		for(ReportTo rt :finalReportList ) {
			fw.append(rt.getCountry());
			fw.append(",");
			fw.append(rt.getGender());
			fw.append(",");
			fw.append(rt.getAvgAmount().toString());
			fw.append("\n");
		}
		fw.flush();
		fw.close();
	}catch(Exception e){
		e.printStackTrace();
	}finally {
		
	}
		
		
	}
	

}
