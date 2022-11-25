package hu.ponte.hr.controller.upload;

import hu.ponte.hr.controller.ImageMeta;
import hu.ponte.hr.services.DigitalSign;
import hu.ponte.hr.services.ImageStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;

@Component
@RequestMapping("api/file")
public class UploadController
{
    @Autowired
    private ImageStore imageStore;
    private DigitalSign digitalSign;

    {

        try {
            digitalSign = new DigitalSign("target/classes/config/keys/key.private");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @RequestMapping(value = "post", method = RequestMethod.POST)
    @ResponseBody
    public String handleFormUpload(@RequestParam("file") MultipartFile file) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, InvalidKeyException {
        if(file.getSize() < 2*1024*1024) {

            ImageMeta im = new ImageMeta(String.valueOf(imageStore.getSize() + 1), file.getOriginalFilename(), file.getContentType(), file.getSize(), digitalSign.sign(file.getBytes()), file.getBytes());
            imageStore.add(im);
            return "ok";
        }
        return "File is too heavy.";
    }
}
