package com.blogApp.services.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.blogApp.services.FileService;

@Service
public class FileSeriviceImpl implements FileService {

	@Override
	public String uploadImage(String path, MultipartFile file) throws IOException {
		
		// file name
		String name = file.getOriginalFilename();

		// rendom name for file(so same images has diff names)
		String randomID = UUID.randomUUID().toString();
		String fileName = randomID.concat(name.substring(name.lastIndexOf(".")));

		// full path
		String filePath = path + "//" + fileName;

		// create folder if not created
		File f = new File(path);
		if (!f.exists()) {

			f.mkdir();
		}

		// file copy

		Files.copy(file.getInputStream(), Paths.get(filePath));
		return fileName;
	}

	@Override
	public InputStream getResource(String path, String filename) throws FileNotFoundException {
		
		String fullPath = path + "//" + filename;

		InputStream is = new FileInputStream(fullPath);

		return is;
	}

}
