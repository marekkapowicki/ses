package pl.marekk.ses.application;

import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import java.util.function.Supplier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.aws.mail.simplemail.SimpleEmailServiceMailSender;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.MailSender;
import pl.marekk.ses.domain.EmailFactory;
import pl.marekk.ses.domain.EmailSender;

@Configuration
@EnableConfigurationProperties(EmailProperties.class)
public class EmailConfiguration {

  @Bean
  public MailSender mailSender(AmazonSimpleEmailService ses) {
    return new SimpleEmailServiceMailSender(ses);
  }

  @Bean
  EmailSender emailSender(MailSender mailSender) {
    return SesEmailSender.of(mailSender);
  }

  @Bean
  EmailFactory emailFactory(EmailProperties emailProperties) {
    Supplier<String> stringSupplier = emailProperties::fromEmail;
    return DefaultEmailFactory.of(stringSupplier);
  }
}
