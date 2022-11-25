package hu.ponte.hr.controller;


import hu.ponte.hr.services.ImageStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Collections;
import java.util.List;

@RestController()
@RequestMapping("api/images")
public class ImagesController {

    @Autowired
    private ImageStore imageStore;

    // Sending list of image metadatas
    @GetMapping("meta")
    public List<ImageMeta> listImages() {
		return imageStore.getList();
    }

    //Sending the bytes of the image based on the id
    @GetMapping(value = "preview/{id}")
    public  byte[] getImage(@PathVariable("id") String id, HttpServletResponse response) throws IOException {
        int imageId = Integer.parseInt(id)-1;
        ByteArrayResource myImage = new ByteArrayResource(imageStore.getIM(imageId).getBytes());
        return imageStore.getIM(imageId).getBytes();


    }
}




