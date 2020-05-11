package com.deepanshu.publish.TesCases;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.math.BigDecimal;

import org.junit.Test;

import com.deepanshu.publish.main.CSVReadWriteMain;
import com.deepanshu.publish.util.AmountToDollar;

public class CurrencyConversionTest {
	
	AmountToDollar atd = new AmountToDollar();
	
	String currency = "INR";
	double amount=66.00;
	
	double usdAmount=1.0;
	
	//CurrencyConversionTest obj =new CurrencyConversionTest();
	CSVReadWriteMain obj = new CSVReadWriteMain();
	
	/*
	 * @SuppressWarnings("deprecation")
	 * 
	 * @Test public void getTheDollarAmount() throws IOException {
	 * assertEquals(usdAmount, atd.getDollarFromCurrency(currency, amount));
	 * usdAmount= atd.getDollarFromCurrency(currency, amount); //if(usdAmount>new
	 * BigDecimal("s0.0")) System.out.println("Got Value successfully"+usdAmount); }
	 */
	
	@Test
	public void getWholeProcessTest() {
		assertEquals(true, obj.getReadWriteProcessing());
		System.out.println("Complete");
	}
	
}
