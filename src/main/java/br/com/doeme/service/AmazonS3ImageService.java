package br.com.doeme.service;

import br.com.doeme.exceptions.FileConversionException;
import br.com.doeme.exceptions.InvalidImageExtensionException;
import br.com.doeme.usuario.repositories.ProductImageRepository;
import br.com.doeme.util.FileUtils;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Log4j2
@Service
public class AmazonS3ImageService extends AmazonClientService {


    @Autowired
    private ProductImageRepository productImageRepository;

    // Upload a List of Images to AWS S3.
    public List<ProductImage> insertImages(List<MultipartFile> images) {
        List<ProductImage> amazonImages = new ArrayList<>();
        images.forEach(image -> {
            try {
                amazonImages.add(uploadImageToAmazon(image));
            } catch (InvalidImageExtensionException | FileConversionException e) {
                e.printStackTrace();
            }
        });
        return amazonImages;
    }


    // Upload a List of Images to AWS S3.
    /*public AmazonImage insertImage(MultipartFile image) throws InvalidImageExtensionException {
        AmazonImage amazonImage = uploadImageToAmazon(image);
        return amazonImage;
    }*/

    // Upload image to AWS S3.
    public ProductImage uploadImageToAmazon(MultipartFile multipartFile) throws InvalidImageExtensionException, FileConversionException {

        // Valid extensions array, like jpeg/jpg and png.
        List<String> validExtensions = Arrays.asList("jpeg", "jpg", "png", "svg");

        // Get extension of MultipartFile
        String extension = FilenameUtils.getExtension(multipartFile.getOriginalFilename());
        if (!validExtensions.contains(extension)) {
            // If file have a invalid extension, call an Exception.
            log.warn("invalid.image.extesion");
            throw new InvalidImageExtensionException(validExtensions);
        } else {

            // Upload file to Amazon.
            String url = uploadMultipartFile(multipartFile);

            // Save image information on MongoDB and return them.
            ProductImage productImage = new ProductImage();
            productImage.setImageUrl(url);

            return productImageRepository.save(productImage);
        }

    }

    public void removeImageFromAmazon(ProductImage amazonImage) {
        String fileName = amazonImage.getImageUrl().substring(amazonImage.getImageUrl().lastIndexOf("/") + 1);
        getClient().deleteObject(new DeleteObjectRequest(getBucketName(), fileName));
        // amazonImageRepository.delete(amazonImage);
    }

    // Make upload to Amazon.
    private String uploadMultipartFile(MultipartFile multipartFile) throws FileConversionException {
        String fileUrl;

        try {
            // Get the file from MultipartFile.
            File file = FileUtils.convertMultipartToFile(multipartFile);

            // Extract the file name.
            String fileName = FileUtils.generateFileName(multipartFile);

            // Upload file.
            uploadPublicFile(fileName, file);

            // Delete the file and get the File Url.
            file.delete();
            fileUrl = getUrl().concat(fileName);
        } catch (IOException e) {

            // If IOException on conversion or any file manipulation, call exception.
            log.warn("multipart.to.file.convert.except", e);
            throw new FileConversionException("Erro");
        }

        return fileUrl;
    }

    // Send image to AmazonS3, if have any problems here, the image fragments are removed from amazon.
    // Font: https://docs.aws.amazon.com/AWSJavaSDK/latest/javadoc/com/amazonaws/services/s3/AmazonS3Client.html#putObject%28com.amazonaws.services.s3.model.PutObjectRequest%29
    private void uploadPublicFile(String fileName, File file) {
        getClient().putObject(new PutObjectRequest(getBucketName(), fileName, file)
                .withCannedAcl(CannedAccessControlList.PublicRead));
    }
}
