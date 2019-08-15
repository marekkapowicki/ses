package pl.marekk.ses.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
/**
 * for assertion
 */
public class EmailRepresentation {

  private final String to;
  private final String from;
  private final String subject;
  private final String text;

  public static EmailRepresentation from(Email email) {
    return new EmailRepresentation(email.to(), email.from(), email.subject(), email.text());
  }
}
