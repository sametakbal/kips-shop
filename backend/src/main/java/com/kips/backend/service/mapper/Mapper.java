package com.kips.backend.service.mapper;

import com.kips.backend.domain.FileEntity;

import java.util.ArrayList;
import java.util.List;

public abstract class Mapper {

    public List<String> getImageUrls(String fileDownloadUrl,List<? extends FileEntity> images) {
        List<String> urls = new ArrayList<>();
        if (images != null) {
            images.forEach(image -> urls.add(fileDownloadUrl + image.getName()));
        }
        return urls;
    }
}
