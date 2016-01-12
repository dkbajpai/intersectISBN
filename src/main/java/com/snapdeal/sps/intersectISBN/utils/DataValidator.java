package com.snapdeal.sps.intersectISBN.utils;

import java.util.ArrayList;
import java.util.List;

import com.snapdeal.sps.intersectISBN.dataFactory.DataUtilities;
import com.snapdeal.sps.intersectISBN.dto.FileFields;
import com.snapdeal.sps.intersectISBN.dto.ProcessedDTO;
import com.snapdeal.sps.intersectISBN.dto.RejectedDTO;

public class DataValidator {



	public static ProcessedDTO processData(List<FileFields> fileFields){
		ProcessedDTO processedDTO = new ProcessedDTO();
		List<FileFields> acceptedRecords = new ArrayList<FileFields>();
		List<RejectedDTO> rejectedRecords = new ArrayList<RejectedDTO>();

		for(FileFields fileField : fileFields){
			if(!isValidBinding(fileField.getBinding()))
			{
				rejectedRecords.add(new RejectedDTO(fileField,"binding is not balid"));
			}
			else if(!isValidIsbn13(fileField.getIsbn13())){
				rejectedRecords.add(new RejectedDTO(fileField,"isbn13 is not valid"));
			}
			else if(fileField.getTitle().equals("")){
				rejectedRecords.add(new RejectedDTO(fileField,"title is missing"));
			}
			else{
				acceptedRecords.add(fileField);
			}

		}


		return processedDTO;
	}


	private static boolean isValidIsbn13(String isbn13){
		if(isbn13.length() != 13 || isbn13.equals(""))
			return false;
		else
			return true;
		
	}
	private static boolean isValidBinding(String binding){
		if(DataUtilities.restrictedBindingSet.contains(binding.toLowerCase()))
			return false;
		return true;
	}

}
