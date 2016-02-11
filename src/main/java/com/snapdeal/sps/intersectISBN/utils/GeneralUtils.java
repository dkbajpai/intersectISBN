package com.snapdeal.sps.intersectISBN.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import com.snapdeal.sps.intersectISBN.dataFactory.Constants;
import com.snapdeal.sps.intersectISBN.dataFactory.DataUtilities;
import com.snapdeal.sps.intersectISBN.dto.CatSubcatDTO;
import com.snapdeal.sps.intersectISBN.dto.DimensionsDTO;
import com.snapdeal.sps.intersectISBN.dto.FileFields;
import com.snapdeal.sps.intersectISBN.dto.NavigationCategoryDTO;

public class GeneralUtils {

	public static String getShortProductName(String productName) {
		if (productName.length() > 255) {
			return productName.substring(0, 253);
		}
		return productName;
	}

	public static String getAuthors(String author) {
		if (author == null)
			return "";
		else
			return GeneralUtils.removeSpecialCharacter(author) + ", ";
	}

	public static String getValidLanguage(String language) {
		if (language == null || language.equals(""))
			return "Unknown";

		else
			return language;
	}

	public static String getValidIsbn(String isbn, String oldSkuSuffix) {
		return isbn.replace(oldSkuSuffix, "");
	}

	public static String getValidWeight(String weight) {
		if (weight == null || weight.equals("") || weight.equals("0")) {
			return "400";
		}
		return weight;
	}

	public static String removeSpecialCharacter(String string) {
		// String regEx = Constants.SPECIAL_CHARECTER;
		String regEx = "[½¿ï~`!@#$%^&*+={}/<>:;\'\"]";

		return string.replaceAll(regEx, "").replaceAll("\\\\", "");
	}

	public static String getValidBinding(String binding) {

		if (binding != null
				&& DataUtilities.bindingMap.get(binding.toLowerCase()) != null)
			return DataUtilities.bindingMap.get(binding.toLowerCase());

		else
			return "Unknown";
	}

	public static void zipFile(Set<File> file, String filename) {
		System.out.println("Going to zip files for:"
				+ Constants.VALIDATORBATCHSIZE);
		ArrayList<File> files = new ArrayList<File>();
		System.out.println("..............." + file.size());
		for (File f : file) {
			// System.out.println(f.getName());
			files.add(f);
		}

		File zipfile = new File(filename);
		// Create a buffer for reading the files
		byte[] buf = new byte[1024];
		try {
			// create the ZIP file
			ZipOutputStream out = new ZipOutputStream(new FileOutputStream(
					zipfile));
			// compress the files
			for (int i = 0; i < files.size(); i++) {
				FileInputStream in = new FileInputStream(files.get(i)
						.getCanonicalFile());
				// add ZIP entry to output stream
				out.putNextEntry(new ZipEntry(files.get(i).getName()));
				// transfer bytes from the file to the ZIP file
				int len;
				while ((len = in.read(buf)) > 0) {
					out.write(buf, 0, len);
				}
				// complete the entry
				out.closeEntry();
				in.close();
			}
			// complete the ZIP file
			out.close();
			System.out.println("Successfully zipped files");
			// return zipfile;
		} catch (IOException ex) {
			System.out.println("Error while zipping files");
			ex.printStackTrace();
		}
		// return null;
	}

	public static NavigationCategoryDTO getValidProductType(CatSubcatDTO dto) {
		return DataUtilities.subcategoryNavigationCategoryMap.get(dto);
	}

	public static DimensionsDTO getDimensions(String dimension) {
		DimensionsDTO dimensionsDTO = new DimensionsDTO();
		try {

			String dimensionArray[] = dimension.split("x");

			dimensionsDTO.setLength(String.valueOf((Double
					.parseDouble(dimensionArray[0].trim()) / 10)));
			dimensionsDTO.setBreadth(String.valueOf((Double
					.parseDouble(dimensionArray[1].trim()) / 10)));
			dimensionsDTO.setHeight(String.valueOf((Double
					.parseDouble(dimensionArray[2].trim()) / 10)));
			// System.out.println("Dimension is :"+dimensionsDTO);

		} catch (NullPointerException e) {
			// System.out.println("Dimension is null. Dimension is :"+dimension);
			dimensionsDTO.setLength("20");
			dimensionsDTO.setBreadth("10");
			dimensionsDTO.setHeight("10");
		} catch (Exception e) {
			// System.out.println("exception in get dimension. Dimension is :"+dimension);
			dimensionsDTO.setLength("20");
			dimensionsDTO.setBreadth("10");
			dimensionsDTO.setHeight("10");
		}
		return dimensionsDTO;
	}

	public static String ISBN10toISBN13(String ISBN10) {
		String ISBN13 = ISBN10;
		ISBN13 = "978" + ISBN13.substring(0, 9);
		int d;

		int sum = 0;
		for (int i = 0; i < ISBN13.length(); i++) {
			d = ((i % 2 == 0) ? 1 : 3);
			sum += ((((int) ISBN13.charAt(i)) - 48) * d);
		}
		sum = 10 - (sum % 10);
		ISBN13 += sum;

		return ISBN13;
	}

	public static String getValidDescriptionText(FileFields ff) {
		if (ff.getDescription() == null || ff.getDescription().equals(""))
			return ff.getTitle();
		else
			return ff.getDescription();

	}

	public static CatSubcatDTO getValidChildCategory(String categoryCode) {
		CatSubcatDTO childCategory;
		if (categoryCode != null
				&& (childCategory = DataUtilities.subCategoryCodeSubCategoryMap
						.get(categoryCode)) != null)
			return childCategory;
		else
			return null;

	}

	public static String getValidLength(String length) {
		if (length == null || length.equals("") || length.equals("0"))
			return "20";
		else
			return length;
	}

	public static String getValidHeight(String height) {
		if (height == null || height.equals("") || height.equals("0"))
			return "10";
		else
			return height;
	}

	public static String getValidBreadth(String breadth) {
		if (breadth == null || breadth.equals("") || breadth.equals("0"))
			return "10";
		else
			return breadth;
	}

	// http://www.mrexcel.com/forum/excel-questions/285670-isbn-13-10-conversion-macro.html
	// for isbn13 to isbn10 conversion

	public static String isbn13ToIsbn10(String isbn13) {
		String isbn10 = null;
		int checkSum = 0;
		int multiplyingFactor = 10;
		int chechSumDigit;

		isbn13 = isbn13.replace("-", "").replace(" ", "");
		if (isbn13.length() != 13)
			return isbn10;

		isbn10 = isbn13.substring(3, 12);

		for (int i = 0; i < isbn10.length(); i++) {
			checkSum = checkSum
					+ (Character.getNumericValue(isbn10.charAt(i)) * multiplyingFactor--);
		}

		chechSumDigit = (11 - (checkSum % 11)) % 11;
		if (chechSumDigit == 10)
			isbn10 = isbn10 + "X";

		else
			isbn10 = isbn10 + chechSumDigit;

		return isbn10;
	}

	public static String getDateTime(int offsetInYears) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				"dd_MM_yyyy_HH_mm_ss");

		Calendar date = Calendar.getInstance();
		date.add(Calendar.YEAR, offsetInYears);

		return simpleDateFormat.format(date.getTime());
	}

	public static String getHighlights(FileFields ff) {

		StringBuffer sb = new StringBuffer();
		// System.out.println("Making highlights");
		// /ISBN13,ISBN10,Language,Author,Publisher,Pages,Binding,Book No.
		sb.append("ISBN13");
		sb.append(" : ");
		sb.append(ff.getIsbn13());
		sb.append("$ ");

		if (ff.getIsbn10() != null && !ff.getIsbn10().equals("")) {
			sb.append("ISBN10");
			sb.append(" : ");
			sb.append(ff.getIsbn10());
			sb.append("$ ");

		}
		if (!(ff.getLanguage() == null || ff.getLanguage().equals("") || ff
				.getLanguage().equals("Unknown"))) {
			sb.append("Language");
			sb.append(" : ");
			sb.append(GeneralUtils.removeSpecialCharacter(ff.getLanguage()));
			sb.append("$ ");

		}
		if (!(ff.getAuthors() == null || ff.getAuthors().equals("") || ff
				.getAuthors().equals("Unknown"))) {
			sb.append("Author");
			sb.append(" : ");
			sb.append(GeneralUtils.removeSpecialCharacter(ff.getAuthors()));
			sb.append("$ ");

		}
		if (!(ff.getPublisher() == null || ff.getPublisher().equals("") || ff
				.getPublisher().equals("Unknown"))) {
			sb.append("Publisher");
			sb.append(" : ");
			sb.append(GeneralUtils.removeSpecialCharacter(ff.getPublisher()));
			sb.append("$ ");

		}
		if (!(ff.getNumberOfPages() == null || ff.getNumberOfPages().equals("") || ff
				.getNumberOfPages().equals("Unknown"))) {
			sb.append("Pages");
			sb.append(" : ");
			sb.append(GeneralUtils.removeSpecialCharacter(ff.getNumberOfPages()));
			sb.append("$ ");

		}
		if (!(ff.getBinding() == null || ff.getBinding().equals("") || ff
				.getBinding().equals("Unknown"))) {
			sb.append("Binding");
			sb.append(" : ");
			sb.append(GeneralUtils.removeSpecialCharacter(GeneralUtils
					.getValidBinding(ff.getBinding())));
			sb.append("$ ");

		}
		return sb.toString();
	}

}
