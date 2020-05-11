package com.deepanshu.publish.util;

import java.math.BigDecimal;

import com.deepanshu.publish.constants.Constants;

public class AmountToDollar {
	
	public double getDollarFromCurrency(String currency, double amount) {
		double amountDollar=0.0;
		
		if(currency.equalsIgnoreCase(Constants.COUNTRY_CODE_IND)) {
			//amountDollar=amount.divide(new Double(66));
			
			amountDollar=(double)Math.round((amount/66.0));
		}
		else if(currency.equalsIgnoreCase(Constants.COUNTRY_CODE_UK)) {
			//amountDollar=amount.divide(new BigDecimal(0.67));
			amountDollar=(double)Math.round((amount/0.67));
		}
		else if(currency.equalsIgnoreCase(Constants.COUNTRY_CODE_SINGAPORE)) {
			//amountDollar=amount.divide(new BigDecimal(1.5));
			amountDollar=(double)Math.round((amount/1.5));
			System.out.println("Amount before return is"+amountDollar);
		}
		else if(currency.equalsIgnoreCase(Constants.COUNTRY_CODE_USD)) {
			amountDollar=amount;
		}
		else if(currency.equalsIgnoreCase(Constants.COUNTRY_CODE_HONGKONG)) {
			amountDollar=(double)Math.round((amount/8.0));
		}
	return amountDollar;
	}
}
