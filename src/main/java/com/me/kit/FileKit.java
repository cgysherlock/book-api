package com.me.kit;

import java.io.File;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.me.plugin.SpringContextHolder;

import com.me.model.UploadFile;

public class FileKit {

	/**
	 * 文件上传的路径(\\assets\\upload)
	 */
	public static final String SAVE_DIRECTORY = SpringContextHolder.getRootRealPath() + "\\assets\\upload";

	/**
	 * 照片上传的路径(\\assets\\upload\\photo)
	 */
	public static final String PHOTO_SAVE_DIRECTORY = SpringContextHolder.getRootRealPath() + "\\assets\\upload\\photo";

	/**
	 * 图片上传的路径(\\assets\\upload\\images)
	 */
	public static final String IMAGE_SAVE_DIRECTORY = SpringContextHolder.getRootRealPath()
			+ "\\assets\\upload\\images";

	// /**
	// * 获得重命名的新文件
	// *
	// * @param file
	// * @return
	// */
	// public static File getNewNameFile(UploadFile file) {
	// String fileName = file.getFileName();
	// int index = fileName.lastIndexOf(".");
	// String suffix = fileName.substring(index);
	// File f = new File(file.getSaveDirectory() + "\\" + UUID.randomUUID() +
	// suffix);
	// file.getFile().renameTo(f);
	// return f;
	// }
	//
	// public static void uploadFile(HttpServletRequest request,
	// HttpServletResponse response) {
	// // 创建一个通用的多部分解析器
	// CommonsMultipartResolver multipartResolver = new
	// CommonsMultipartResolver(
	// request.getSession().getServletContext());
	// // 判断 request 是否有文件上传,即多部分请求
	// if (multipartResolver.isMultipart(request)) {
	// // 转换成多部分request
	// MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)
	// request;
	// // 取得request中的所有文件名
	// Iterator<String> iter = multiRequest.getFileNames();
	// while (iter.hasNext()) {
	// // 记录上传过程起始时的时间，用来计算上传时间
	// int pre = (int) System.currentTimeMillis();
	// // 取得上传文件
	// MultipartFile file = multiRequest.getFile(iter.next());
	// if (file != null) {
	// // 取得当前上传文件的文件名称
	// String myFileName = file.getOriginalFilename();
	// // 如果名称不为“”,说明该文件存在，否则说明该文件不存在
	// if (myFileName.trim() != "") {
	// System.out.println(myFileName);
	// // 重命名上传后的文件名
	// String fileName = UUID.randomUUID() + file.getOriginalFilename();
	// // 定义上传路径
	// String path = PHOTO_SAVE_DIRECTORY + "\\" + fileName;
	// File localFile = new File(path);
	// try {
	// file.transferTo(localFile);
	// } catch (IllegalStateException | IOException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }
	// }
	// // 记录上传该文件后的时间
	// int finaltime = (int) System.currentTimeMillis();
	// System.out.println(finaltime - pre);
	// }
	// }
	// }
	//
	// public static List<UploadFile> getFiles(HttpServletRequest request,
	// String saveDir) {
	// List<UploadFile> list = new ArrayList<UploadFile>();
	// // 创建一个通用的多部分解析器
	// CommonsMultipartResolver multipartResolver = new
	// CommonsMultipartResolver(
	// request.getSession().getServletContext());
	// // 判断 request 是否有文件上传,即多部分请求
	// if (multipartResolver.isMultipart(request)) {
	// // 转换成多部分request
	// MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)
	// request;
	// // 取得request中的所有文件名
	// Iterator<String> iter = multiRequest.getFileNames();
	// while (iter.hasNext()) {
	// // 记录上传过程起始时的时间，用来计算上传时间
	// int pre = (int) System.currentTimeMillis();
	// // 取得上传文件
	// MultipartFile file = multiRequest.getFile(iter.next());
	// String originalFileName = file.getOriginalFilename();
	// String paraName = file.getName();
	// if (file != null) {
	// // 取得当前上传文件的文件名称
	// String myFileName = file.getOriginalFilename();
	// String contentType = file.getContentType();
	// // 如果名称不为“”,说明该文件存在，否则说明该文件不存在
	// if (myFileName.trim() != "") {
	// UploadFile uploadFile = new UploadFile(paraName, saveDir,
	// originalFileName, contentType);
	// try {
	// file.transferTo(uploadFile.getFile());
	// } catch (IllegalStateException | IOException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// list.add(uploadFile);
	// }
	// }
	// // 记录上传该文件后的时间
	// int finaltime = (int) System.currentTimeMillis();
	// System.out.println(finaltime - pre);
	// }
	// }
	// return list;
	// }

	/**
	 * 删除文件
	 * 
	 * @param fileName
	 * @param saveDirectory
	 * @return
	 */
	public static boolean deleteFile(String fileName, String saveDirectory) {
		String dir = saveDirectory + "\\" + fileName;
		File oldFile = new File(dir);
		if (oldFile.exists()) {
			return oldFile.delete();
		} else {
			return true;
		}
	}

}
