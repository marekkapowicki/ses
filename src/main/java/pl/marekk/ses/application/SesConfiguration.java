package pl.marekk.ses.application;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@EnableConfigurationProperties(AwsConfig.class)
@Profile("!test")
public class SesConfiguration {

  @Bean
  public AmazonSimpleEmailService amazonSimpleEmailService(
      AwsConfig config) {
    return AmazonSimpleEmailServiceClientBuilder.standard()
        .withCredentials(new AWSStaticCredentialsProvider(config.getAwsCredential()))
        .withRegion(config.getRegion()).build();
  }


}
