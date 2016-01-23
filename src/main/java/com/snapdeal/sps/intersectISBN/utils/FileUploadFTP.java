package com.snapdeal.sps.intersectISBN.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;


public class FileUploadFTP {
	
	public static String getPath(String directoryName)
	{
		String SheetPath = null;
		try
		{
		File mappingSheetDirectory = new File(directoryName);
    	System.out.println(mappingSheetDirectory.getCanonicalPath());
        SheetPath = mappingSheetDirectory.getCanonicalPath();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return SheetPath;
	}
	
	public static void deleteFile(File file,String path)
	{
		try {
			if (file.isDirectory())
			{
				for (String name : file.list())
				{
					Files.delete(new File(path+"/"+name).toPath());
				}
				Files.delete(new File(path).toPath());
			}
			else
			{
				Files.delete(file.toPath());
			}
		} catch (NoSuchFileException x) {
		    System.err.format("%s: no such" + " file or directory%n", path);
		} catch (DirectoryNotEmptyException x) {
		    System.err.format("%s not empty%n", path);
		} catch (IOException x) {
		    // File permission problems are caught here.
		    System.err.println(x);
		}
	}

	public static String getFileExtension(String fileName) {
        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
        return fileName.substring(fileName.lastIndexOf(".")+1);
        else return "";
    }
	
	public static String getFileName(String fileName) {
        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
        return fileName.substring(0,fileName.lastIndexOf("."));
        else return "";
    }
	
	public static void filesToUpload(String path,int flag)
	{
		/*
		 * flag = 1 : accepted folder
		 * flag = 2 : rejected folder
		 * flag = 3 : archive folder
		 */
		
		File file = new File(path);
		FTPUtil ftputil = new FTPUtil();
		for (String name : file.list())
		{
			try{
			if(getFileExtension(name).equals("xls") || getFileExtension(name).equals("xlsx"))
			{
					System.out.println("Uploading file "+path+"/"+name);
					ftputil.uploadFiles(path+"/"+name, new File(path+"/"+name), flag);
					System.out.println("Uploading file "+path+"/"+getFileName(name)+".zip");
					ftputil.uploadFiles(path+"/"+getFileName(name)+".zip", new File(path+"/"+name), flag);
					deleteFile(new File(path+"/"+name), path+"/"+name);
					deleteFile(new File(path+"/"+getFileName(name)+".zip"), path+"/"+getFileName(name)+".zip");
			}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}
