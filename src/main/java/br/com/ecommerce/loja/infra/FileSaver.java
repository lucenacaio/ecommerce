package br.com.ecommerce.loja.infra;



import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import com.amazonaws.AmazonClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;


@Component
public class FileSaver {

	@Autowired
	private AmazonS3Client s3;

	public String write(String baseFolder, MultipartFile multipartFile) {
		try {
			ObjectMetadata metadata = new ObjectMetadata();
			metadata.setContentType(multipartFile.getContentType());
			metadata.setContentLength(multipartFile.getSize());
			
			s3.putObject("ecommerce", multipartFile.getOriginalFilename(),
					multipartFile.getInputStream(), metadata);
			return "http://localhost:9444/s3/ecommerce/"+multipartFile.getOriginalFilename()+"?noAuth=true";
		} catch (AmazonClientException | IOException e) {
			throw new RuntimeException(e);
		}

	}

}
