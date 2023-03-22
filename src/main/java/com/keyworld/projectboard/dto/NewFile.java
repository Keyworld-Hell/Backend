package com.keyworld.projectboard.dto;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

public class NewFile implements MultipartFile {
    private final Resource resource;
    private final String name;

    public NewFile(Resource resource, String name) {
        this.resource = resource;
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getOriginalFilename() {
        return resource.getFilename();
    }

    @Override
    public String getContentType() {
        return null; // Implement content type detection if needed
    }

    @Override
    public boolean isEmpty() {
        try {
            return resource.contentLength() == 0;
        } catch (IOException e) {
            return true;
        }
    }

    @Override
    public long getSize() {
        try {
            return resource.contentLength();
        } catch (IOException e) {
            return 0;
        }
    }

    @Override
    public byte[] getBytes() throws IOException {
        return resource.getInputStream().readAllBytes();
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return resource.getInputStream();
    }

    @Override
    public void transferTo(java.io.File destination) throws IOException, IllegalStateException {
        throw new UnsupportedOperationException("Not implemented");
    }
}
