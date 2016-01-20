package com.snapdeal.sps.intersectISBN.action;

import com.snapdeal.sps.intersectISBN.classes.Initializer;


public class Main {

	public static void main(String args[]) {

		Initializer.initialize();

		
		ProcessEngine.readAndProcessInputText();
		
		//System.out.println(DataValidator.emptyAuthorIsbns.size());
		
		//FileWriteUtils.writeJugadFucntion(DataValidator.emptyAuthorIsbns, new File("/home/ashish/emptyauthorisbn.xlsx"));

		/*
		 * List<FileFields> list =
		 * ProcessEngine.readAndProcessIntermediateData();
		 * System.out.println("Size of list:"+list.size()); ProcessedDTO
		 * processedDTO = DataValidator.processData(list);
		 * FileWriteUtils.writeAcceptedXlsx(processedDTO.getAcceptedRecords(),
		 * AcceptedFileHeaders.values(),
		 * DataUtilities.subCategoryCodeSubCategoryMap,
		 * Constants.WORKING_DIRECTORY + Constants.ACCEPTED_FILES_DIRECTORY,
		 * "Accepted_Book_Listing.xlsx");
		 * FileWriteUtils.writeRejectedXlsx(processedDTO.getRejectedRecords(),
		 * RejectedFileHeaders.values(),
		 * DataUtilities.subCategoryCodeSubCategoryMap,
		 * Constants.WORKING_DIRECTORY + Constants.REJECTED_FILES_DIRECTORY,
		 * "Rejected_Book_Listing.xlsx");
		 */

	}
}
