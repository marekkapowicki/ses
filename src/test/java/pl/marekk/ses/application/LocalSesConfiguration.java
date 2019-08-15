package pl.marekk.ses.application;


import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.aws.autoconfigure.context.properties.AwsRegionProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.testcontainers.containers.localstack.LocalStackContainer;
import org.testcontainers.containers.localstack.LocalStackContainer.Service;

@Configuration
@Profile("test")
public class LocalSesConfiguration {

  @Bean
  public LocalStackContainer localStackContainer() {
    return new LocalStackContainer()
        .withServices(Service.SES);
  }

  @MockBean
  AwsRegionProperties awsRegionProperties;

  @Bean
  public AmazonSimpleEmailService amazonSimpleEmailService(LocalStackContainer container) {
    container.start();
    EndpointConfiguration endpointConfiguration = container.getEndpointConfiguration(Service.SES);

    return AmazonSimpleEmailServiceClientBuilder.standard()
        .withCredentials(container.getDefaultCredentialsProvider())
        .withEndpointConfiguration(endpointConfiguration)
        .build();

  }
}
