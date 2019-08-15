package pl.marekk.ses.application;

import java.util.function.Supplier;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import pl.marekk.ses.domain.Email;
import pl.marekk.ses.domain.EmailFactory;

@AllArgsConstructor(access = AccessLevel.PACKAGE, staticName = "of")
@Log4j2
public class DefaultEmailFactory implements EmailFactory {

  private final Supplier<String> fromSupplier;

  @Override
  public Email create(String to, String subject, String text) {
    log.debug("creating the email to {}: ", to);
    return Email.of(to, fromSupplier.get(), subject, text);
  }
}
