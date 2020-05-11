package com.deepanshu.publish.interfaces;

import java.util.List;

import com.deepanshu.publish.model.CurrencyTo;

@FunctionalInterface
public interface IReadInputFile {
	
	public List<CurrencyTo> readInputFileProcessing(String inputFileloc);

}
