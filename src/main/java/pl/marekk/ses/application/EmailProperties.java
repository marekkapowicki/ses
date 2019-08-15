package pl.marekk.ses.application;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "email")
@Data
public class EmailProperties {

  private String fromEmail;

}
