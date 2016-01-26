package com.snapdeal.sps.intersectISBN.utils;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import com.snapdeal.sps.intersectISBN.dataFactory.Constants;

public class FTPUtil {

	private String booksSheetPath;
	private FTPClient connectWithFTP(String IP,int PORT,String username,String password)
	{
		//new ftp client
		FTPClient ftp = new FTPClient();
		//try to connect
		try {
			ftp.connect(IP,PORT);
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//login to server
		try {
			if(!ftp.login(username, password))
			{
				System.out.println("logout");
				ftp.logout();
				System.out.println("error");
			}
		} catch (IOException e) {
			System.out.println("Error while log in");
			e.printStackTrace();
		}
		return ftp;
	}

	//	private boolean downloadFileFromFTP(String localDirectory,String fileName,FTPClient ftp)
	//	{
	//		
	//	}
//	public ArrayList<String> getFilesFromFTP(){
//		ArrayList<String> fileNames = new ArrayList<String>();
//		try{
//			//check if the folder with vendor name exists and creates if not
//
//			File booksSheetDirectory = new File(Constants.LOCAL_BOOKS_SHEET_LOCATION);
//			System.out.println(booksSheetDirectory.getCanonicalPath());
//			if(!booksSheetDirectory.exists())
//				booksSheetDirectory.mkdirs();
//			booksSheetPath = booksSheetDirectory.getCanonicalPath();
//			//To write
//
//			String localDirectory = booksSheetPath;
//
//			/*FTPClient ftp = connectWithFTP(Constants.FTP_IP,Constants.FTP_PORT,Constants.FTP_MAPPING_SHEET_USERNAME,Constants.FTP_MAPPING_SHEET_PASSWORD);
//            int reply = ftp.getReplyCode();
//            //FTPReply stores a set of constants for FTP reply codes.
//            if (!FTPReply.isPositiveCompletion(reply))
//            {
//                ftp.disconnect();
//                System.out.println("error");
//            }
//
//            //enter passive mode
//            ftp.enterLocalPassiveMode();
//             ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
//
//            //change current directory
//            ftp.changeWorkingDirectory(Constants.FTP_BOOKS_SHEET_LOCATION);
//            String currentDirectory = ftp.printWorkingDirectory();
//            System.out.println("Current directory is " + ftp.printWorkingDirectory());
//
//            //get list of filenames
//            FTPFile[] ftpFiles = ftp.listFiles(); 
//
//            if (ftpFiles != null && ftpFiles.length > 0) {
//                //loop through files
//            	for (FTPFile file : ftpFiles) {
////                    if (!file.isFile()) {
////                        continue;
////                    }
//                    if(file.isFile())
//                    {
//                    	fileNames.add(file.getName());
//                    }
//                    System.out.println("File is " + file.getName());
//
//
//                    //get output stream
//                    OutputStream output = new BufferedOutputStream(new FileOutputStream(localDirectory + "/" + file.getName()));
////                    output = new FileOutputStream(localDirectory + "/" + file.getName());
//                    //get the file from the remote system
//                    ftp.retrieveFile(currentDirectory+"/"+file.getName(), output);
//                    //close output stream
//                    output.close();
//
//                    //delete the file
////                    ftp.deleteFile(file.getName());
////                    boolean result = downloadFileFromFTP(localDirectory, file.getName(), ftp);
//                    if (result == false)
//                    {
//                    	System.out.println("Error!!!! while downloading "+file.getName()+" from FTP");
//                    }
//                }
//            }
//            else{
//                System.out.println("No file found on FTP Location");
//                System.exit(0);
//            }
//
//            ftp.logout();
//            ftp.disconnect();*/
//
//			for(String name : new File(localDirectory).list())
//			{
//				if(CommonUtils.getFileExtension(name).equals("xls") || CommonUtils.getFileExtension(name).equals("xlsx"))
//				{
//					fileNames.add(name);
//				}
//			}
//		}catch(Exception e){
//			e.printStackTrace();
//			System.exit(0);
//		}
//		return fileNames;
//	}

//	public void getMappingSheet()
//	{
//		System.out.println("Getting Mapping Sheet fro FTP");
//		String currentDirectory = null;
//		String mappingSheetPath = null;
//		try
//		{
//			File mappingSheetDirectory = new File(Constants.LOCAL_MAPPING_SHEET_LOCATION);
//			System.out.println(mappingSheetDirectory.getCanonicalPath());
//			if(!mappingSheetDirectory.exists())
//				mappingSheetDirectory.mkdir();
//			mappingSheetPath = mappingSheetDirectory.getCanonicalPath();
//		}
//		catch(Exception e)
//		{
//			e.printStackTrace();
//		}
//		//To write
//
//		String localDirectory = mappingSheetPath;
//		FTPClient ftp = connectWithFTP(Constants.FTP_IP,Constants.FTP_PORT,Constants.FTP_MAPPING_SHEET_USERNAME,Constants.FTP_MAPPING_SHEET_PASSWORD);
//		int reply = ftp.getReplyCode();
//		try
//		{
//			//FTPReply stores a set of constants for FTP reply codes.
//			if (!FTPReply.isPositiveCompletion(reply))
//			{
//				ftp.disconnect();
//				System.out.println("error");
//			}
//
//			//enter passive mode
//			ftp.enterLocalPassiveMode();
//			ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
//
//			//change current directory
//			ftp.changeWorkingDirectory(Constants.FTP_MAPPING_SHEET_LOCATION);
//			currentDirectory = ftp.printWorkingDirectory();
//			System.out.println("Current directory is " + ftp.printWorkingDirectory());
//		}
//		catch(Exception e)
//		{
//			e.printStackTrace();
//		}
//		if (!mappingSheetPath.equals(null) && !currentDirectory.equals(null))
//		{
//			try
//			{
//				//get output stream
//				OutputStream output = new BufferedOutputStream(new FileOutputStream(localDirectory + "/" + Constants.MAPPING_SHEET_NAME));
//				//get the file from the remote system
//				try {
//					ftp.retrieveFile(currentDirectory+"/"+Constants.MAPPING_SHEET_NAME, output);
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				//close output stream
//				output.close();
//				System.out.println("Successfully downloaded mapping sheet from ftp");
//			}
//			catch(Exception e)
//			{
//				e.printStackTrace();
//			}
//		}
//
//		else
//		{
//			System.out.println("cannot download mapping sheet");
//		}
//	}

	public void uploadFiles(String path, File file,int flag)
	{
		/**
		 * 
		 * flag = 1 : accepted files
		 * flag = 2 : rejected files
		 * flag = 3 : files to archive
		 */
		//		String [] files = file.list();
		FTPClient ftp = null;
		try
		{
			if (flag == 1)
			{
				ftp = connectWithFTP(Constants.FTP_IP,Constants.FTP_PORT,Constants.FTP_UPLOAD_SHEET_USERNAME,
						Constants.FTP_UPLOAD_SHEET_PASSWORD);
			}
			else if(flag == 2)
			{
				ftp = connectWithFTP(Constants.FTP_IP,Constants.FTP_PORT,Constants.FTP_MAPPING_SHEET_USERNAME,
						Constants.FTP_MAPPING_SHEET_PASSWORD);
			}
			else
			{
				ftp = connectWithFTP(Constants.FTP_IP,Constants.FTP_PORT,Constants.FTP_MAPPING_SHEET_USERNAME,
						Constants.FTP_MAPPING_SHEET_PASSWORD);
			}
			int reply = ftp.getReplyCode();
			//FTPReply stores a set of constants for FTP reply codes.
			if (!FTPReply.isPositiveCompletion(reply))
			{
				ftp.disconnect();
				System.out.println("error");
			}

			//enter passive mode
			ftp.enterLocalPassiveMode();
			ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
			String currentDirectory;
			if (flag == 1)
			{
				ftp.changeWorkingDirectory("/"+Constants.FTP_UPLOAD_SHEET_LOCATION);
				currentDirectory = ftp.printWorkingDirectory();
				System.out.println("Current directory is " + ftp.printWorkingDirectory());
			}
			else if (flag == 2)
			{
				ftp.changeWorkingDirectory("/"+Constants.FTP_REJECTED_SHEET_LOCATION);
				currentDirectory = ftp.printWorkingDirectory();
				System.out.println("Current directory is " + ftp.printWorkingDirectory());
			}
			else
			{
				ftp.changeWorkingDirectory("/"+Constants.FTP_ARCHIVE_SHEET_LOCATION);
				currentDirectory = ftp.printWorkingDirectory();
				System.out.println("Current directory is " + ftp.printWorkingDirectory());
			}
			//         for (String name : files)
			//         {
			File fileToUpload = new File(path);
			//        	 System.out.println("Path received is :"+path);
			//             String firstRemoteFile = name;
			InputStream inputStream = new FileInputStream(fileToUpload);

			//             System.out.println("Start uploading file"+fileToUpload.getName());
			boolean done = ftp.storeFile(currentDirectory+"/"+fileToUpload.getName(), inputStream);
			inputStream.close();
			if (done) {
				System.out.println("The file +"+path+"is uploaded successfully.");
				//             }
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Error while uploading files to ftp");
		}
		finally {
			try {
				if (ftp.isConnected()) {
					ftp.logout();
					ftp.disconnect();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	
}
