package br.com.doeme.usuario.upload;

import br.com.doeme.exceptions.FileConversionException;
import br.com.doeme.exceptions.InvalidImageExtensionException;
import br.com.doeme.service.AmazonS3ImageService;
import br.com.doeme.service.ImageDTO;
import br.com.doeme.service.ProductImage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

//https://technicalsand.com/spring-boot-multipart-file-upload-example-postman/

@Slf4j
@RestController
@RequestMapping("/v1/upload")
public class UploadResponse {

    @Value("${file.image.directory}")
    private String UPLOADED_FOLDER = null;

    @Autowired
    private AmazonS3ImageService amazonS3ImageService;

    @PostMapping(value = "/file",
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE} )
    public ResponseEntity<String> uploadSingleFileExample1(ImageDTO file) {
        log.info("Request contains, File: " + file.getFile().getOriginalFilename());

        try {
            //byte[] bytes = file.getBytes();
            //Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            //Files.write(path, bytes);

            ProductImage productImage = amazonS3ImageService.uploadImageToAmazon(file.getFile());
            log.info("File amazon: " + productImage.toString());

            return ResponseEntity.ok("Success");
        } catch (InvalidImageExtensionException | FileConversionException e) {
            e.printStackTrace();
            return ResponseEntity.ok("Error");
        }
    }
}
