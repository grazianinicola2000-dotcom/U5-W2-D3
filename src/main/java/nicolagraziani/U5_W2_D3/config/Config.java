package nicolagraziani.U5_W2_D3.config;

import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class Config {

    @Bean
    public Cloudinary getImageUploader(@Value("${cloudinary.name}") String cloudName,
                                       @Value("${cloudinary.apikey}") String apiKey,
                                       @Value("${cloudinary.secret}") String apiSecret) {
        System.out.println(cloudName);
        System.out.println(apiKey);
        System.out.println(apiSecret);
        Map<String, String> configuration = new HashMap<>(); // <-- Questa Map<String,String> va passata al costruttore di Cloudinary
        configuration.put("cloud_name", cloudName); //I NOMI DELLE CHIAVI DEVONO ESSERE ESATTAMENTE QUESTI
        configuration.put("api_key", apiKey);
        configuration.put("api_secret", apiSecret);

        return new Cloudinary(configuration);
    }
}
