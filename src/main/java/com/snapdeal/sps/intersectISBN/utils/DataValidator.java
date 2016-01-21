package com.snapdeal.sps.intersectISBN.utils;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.snapdeal.sps.intersectISBN.dataFactory.Constants;
import com.snapdeal.sps.intersectISBN.dataFactory.DataUtilities;
import com.snapdeal.sps.intersectISBN.dto.DecisionDTO;
import com.snapdeal.sps.intersectISBN.dto.FileFields;

public class DataValidator {


	public static Set<String> emptyAuthorIsbns = new HashSet<String>();

	public static DecisionDTO validateFileFieldData(FileFields ff, Set<String> activeIsbns, Set<String> disabledIsbns){
		DecisionDTO decisionDTO;
		StringBuffer rejectionReason = new StringBuffer();
		Set<String> foundRestrictedWords = new HashSet<String>();

		if(activeIsbns.contains(ff.getIsbn13().toLowerCase().trim()))
		{
			rejectionReason.append("Multivendor ISBN \n");
		}
		else{
			if(disabledIsbns.contains(ff.getIsbn13().toLowerCase().trim()))
				ff.setIsbn13(ff.getIsbn13() + Constants.OLD_SKU_SUFFIX);
			
			if(!isValidBinding(ff.getBinding(),DataUtilities.restrictedBindingSet))
			{
				rejectionReason.append("binding is not valid\n");
			}
			if(!isValidIsbn13(ff.getIsbn13())){
				rejectionReason.append("isbn13 is not valid\n");
			}
			if(ff.getTitle() == null || ff.getTitle().equals("")){
				rejectionReason.append("title is not valid\n");
			}
		}


		//		if((foundRestrictedWords = checkRestrictedWordsInData(ff, DataUtilities.restrictedWordsSet)).size() != 0){
		//			rejectionReason.append("Restricted Words in data: " + foundRestrictedWords.toString().replace("[", "").replace("]", ""));
		//		}

		//		if(ff.getAuthors() == null || ff.getAuthors().equals(""))
		//			emptyAuthorIsbns.add(ff.getIsbn13());

		if(rejectionReason.length() != 0)
			decisionDTO = new DecisionDTO(false, rejectionReason.toString());
		else
			decisionDTO = new DecisionDTO(true,null);

		return decisionDTO;
	}


	private static boolean isValidIsbn13(String isbn13){

		if(isbn13 == null || isbn13.length() != 13 || isbn13.equals(""))
			return false;

		else
			return true;
	}
	private static boolean isValidBinding(String binding, Set<String> restrictedBindingSet){
		if(binding != null && restrictedBindingSet.contains(binding.toLowerCase()))
			return false;
		return true;
	}


	private static Set<String> checkRestrictedWordsInData(FileFields ff,Set<String> restrictedWordsSet){
		Set<String> foundRestrictedWords = new HashSet<String>();
		for( String restrictedWord : restrictedWordsSet){
			if(ff.getTitle() != null && hasRestrictedWord(ff.getTitle().toLowerCase(), restrictedWord.toLowerCase()) 
					|| (ff.getAuthors() != null && hasRestrictedWord(ff.getAuthors().toLowerCase(),restrictedWord.toLowerCase()))
					|| (ff.getBinding() != null && hasRestrictedWord(ff.getBinding().toLowerCase(),restrictedWord.toLowerCase()))
					|| (ff.getLanguage() != null && hasRestrictedWord(ff.getLanguage().toLowerCase(),restrictedWord.toLowerCase()))
					|| (ff.getDescription() != null && hasRestrictedWord(ff.getDescription().toLowerCase(),restrictedWord.toLowerCase()))
					|| (ff.getPublisher() != null && hasRestrictedWord(ff.getPublisher().toLowerCase(),restrictedWord.toLowerCase()))
					|| (ff.getPublicationDate() != null && hasRestrictedWord(ff.getPublicationDate().toLowerCase(),restrictedWord.toLowerCase()))
					|| (ff.getNumberOfPages() != null && hasRestrictedWord(ff.getNumberOfPages().toLowerCase(),restrictedWord.toLowerCase()))
					|| (ff.getIsbn10() != null && hasRestrictedWord(ff.getIsbn10().toLowerCase(),restrictedWord.toLowerCase()))
					|| (ff.getIsbn13() != null && hasRestrictedWord(ff.getIsbn13().toLowerCase(),restrictedWord.toLowerCase())))
				foundRestrictedWords.add(restrictedWord);

		}
		return foundRestrictedWords;

	}

	private static boolean hasRestrictedWord(String source, String subItem){
		String pattern = "\\b"+subItem+"\\b";
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(source);
		return m.find();
	}



}
