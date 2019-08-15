package pl.marekk.ses.application;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PACKAGE, staticName = "of")
@Getter
@Builder
class SendEmailCommand {

  private final String to;
  private final String subject;
  private final String text;
}
