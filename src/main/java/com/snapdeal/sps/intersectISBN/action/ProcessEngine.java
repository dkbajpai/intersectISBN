package com.snapdeal.sps.intersectISBN.action;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.snapdeal.sps.intersectISBN.dataFactory.Constants;
import com.snapdeal.sps.intersectISBN.dto.FileFields;
import com.snapdeal.sps.intersectISBN.dto.ProcessedDTO;
import com.snapdeal.sps.intersectISBN.utils.DataValidator;
import com.snapdeal.sps.intersectISBN.utils.FileReadUtils;

public class ProcessEngine {

	public static boolean readAndProcessInputText() {
		try {
			File dir = new File(Constants.INPUT_TXT_DIRECTORY_PATH);
			File dirList[] = dir.listFiles();
			for (File file : dirList) {
				FileReadUtils.readInputTextAndWriteXlsx(file,
						Constants.WORKING_DIRECTORY
								+ Constants.ALL_FILES_DIRECTORY, "BooksData",
						Constants.BATCHSIZE);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}
	
	public static List<FileFields> readAndProcessIntermediateData() {
		List<FileFields> listOfData = new ArrayList<FileFields>();
		try {
			File dir = new File(Constants.WORKING_DIRECTORY
					+ Constants.ALL_FILES_DIRECTORY);
			File dirList[] = dir.listFiles();
			for (File file : dirList) {
				listOfData.addAll(FileReadUtils.readParsedXlsx(1, file));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return listOfData;
	}
	
	public static ProcessedDTO splitIntermediateData(List<FileFields> listOfData) {
		ProcessedDTO processedDTO;
		try {
			processedDTO = DataValidator.processData(listOfData);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return processedDTO;
	}
}
