package com.snapdeal.sps.intersectISBN.action;

import java.util.List;

import com.snapdeal.sps.intersectISBN.classes.Initializer;
import com.snapdeal.sps.intersectISBN.dataFactory.Constants;
import com.snapdeal.sps.intersectISBN.dataFactory.DataUtilities;
import com.snapdeal.sps.intersectISBN.dto.FileFields;
import com.snapdeal.sps.intersectISBN.dto.ProcessedDTO;
import com.snapdeal.sps.intersectISBN.enums.AcceptedFileHeaders;
import com.snapdeal.sps.intersectISBN.enums.RejectedFileHeaders;
import com.snapdeal.sps.intersectISBN.utils.FileWriteUtils;

public class Main {
	
	

	public static void main(String args[]) {

		Initializer.initialize();
		
		ProcessEngine.readAndProcessInputText();
		List<FileFields> list = ProcessEngine.readAndProcessIntermediateData();
		System.out.println("Size of list:"+list.size());
		ProcessedDTO processedDTO = ProcessEngine.splitIntermediateData(list);
		FileWriteUtils.writeAcceptedXlsx(processedDTO.getAcceptedRecords(), AcceptedFileHeaders.values(), DataUtilities.subCategoryCodeSubCategoryMap, Constants.WORKING_DIRECTORY + Constants.ACCEPTED_FILES_DIRECTORY, "Accepted_Book_Listing");
		FileWriteUtils.writeRejectedXlsx(processedDTO.getRejectedRecords(), RejectedFileHeaders.values(), DataUtilities.subCategoryCodeSubCategoryMap, Constants.WORKING_DIRECTORY + Constants.REJECTED_FILES_DIRECTORY, "Rejected_Book_Listing");
		
	}
}
