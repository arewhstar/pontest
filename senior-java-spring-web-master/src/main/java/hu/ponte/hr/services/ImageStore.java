package hu.ponte.hr.services;

import hu.ponte.hr.controller.ImageMeta;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.util.ArrayList;
import java.util.List;

@Service
public class ImageStore {


    List<ImageMeta> metas = new ArrayList<ImageMeta>();

    public int getSize(){
        return metas.size();
    }
    public void add(ImageMeta meta){
        metas.add(meta);

    }
    public List<ImageMeta> getList(){
        return metas;
    }
    public ImageMeta getIM(int i){
        return metas.get(i);
    }

}
