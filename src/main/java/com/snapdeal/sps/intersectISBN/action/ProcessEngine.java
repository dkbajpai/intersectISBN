package com.snapdeal.sps.intersectISBN.action;

import java.io.File;

import com.snapdeal.sps.intersectISBN.dataFactory.Constants;
import com.snapdeal.sps.intersectISBN.utils.FileReadUtils;

public class ProcessEngine {

	public static boolean readAndProcessInputText() {
		try {
			File dir = new File(Constants.INPUT_TXT_DIRECTORY_PATH);
			File dirList[] = dir.listFiles();
			for (File file : dirList) {
				FileReadUtils.readInputTextAndWriteXlsx(file,
						Constants.WORKING_DIRECTORY
								+ Constants.ALL_FILES_DIRECTORY,
						Constants.VALIDATORBATCHSIZE,Constants.REJECTIONBATCHSIZE);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}
	
	/*public static List<FileFields> readAndProcessIntermediateData() {
		List<FileFields> listOfData = new ArrayList<FileFields>();
		try {
			File dir = new File(Constants.WORKING_DIRECTORY
					+ Constants.ALL_FILES_DIRECTORY);
			File dirList[] = dir.listFiles();
			for (File file : dirList) {
				listOfData.addAll(FileReadUtils.readParsedXlsx(1, file));
				
				//listOfData.addAll(FileReadUtils.readParsedXlsx(1, file));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return listOfData;
	}*/
	
}
