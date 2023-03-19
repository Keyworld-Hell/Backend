package com.keyworld.projectboard.service;

import org.apache.commons.net.ftp.FTPClient;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
public class FileService {

    private static final String HOST = "127.0.0.1";
    private static final int PORT = 21;
    private static final String USERNAME = "ystc1247";
    private static final String PASSWORD = "kj10501050";

    public void uploadFile(MultipartFile file, String filePath) throws IOException {
        FTPClient ftpClient = new FTPClient();
        ftpClient.connect(HOST, PORT);
        ftpClient.login(USERNAME, PASSWORD);

        InputStream inputStream = file.getInputStream();
        ftpClient.storeFile(filePath, inputStream);

        inputStream.close();
        ftpClient.logout();
        ftpClient.disconnect();
    }

    public Resource downloadFile(String filePath) throws IOException {
        FTPClient ftpClient = new FTPClient();
        ftpClient.connect(HOST, PORT);
        ftpClient.login(USERNAME, PASSWORD);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ftpClient.retrieveFile(filePath, outputStream);

        byte[] fileData = outputStream.toByteArray();
        outputStream.close();
        ftpClient.logout();
        ftpClient.disconnect();

        return new ByteArrayResource(fileData);
    }
}
