package com.snapdeal.sps.intersectISBN.action;

import com.snapdeal.sps.intersectISBN.dataFactory.Constants;
import com.snapdeal.sps.intersectISBN.utils.FileUploadFTP;
import com.snapdeal.sps.intersectISBN.utils.GeneralUtils;


public class Main {

	public static void main(String args[]) {

		//Initializer.initialize();

		System.out.println(GeneralUtils.getDateTime(2));
		
		
		//uploading files 
		FileUploadFTP.filesToUpload(FileUploadFTP.getPath(Constants.LOCAL_UPLOAD_SHEET_LOCATION),1);
		FileUploadFTP.filesToUpload(FileUploadFTP.getPath(Constants.LOCAL_REJECTED_SHEET_LOCATION),2);
		FileUploadFTP.filesToUpload(FileUploadFTP.getPath(Constants.LOCAL_BOOKS_SHEET_LOCATION),3);
		
		//ProcessEngine.readAndProcessInputText();
		
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
