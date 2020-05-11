package com.deepanshu.publish.interfaces;

import java.util.List;

import com.deepanshu.publish.model.CurrencyTo;

@FunctionalInterface
public interface IReportPublish {
	
	public void processCSVToOutPutReport(String outPutFileLoc, List<CurrencyTo> inputFileList);

}
