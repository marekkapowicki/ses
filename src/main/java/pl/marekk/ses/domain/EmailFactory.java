package pl.marekk.ses.domain;

public interface EmailFactory {

  Email create(
      final String to,
      final String subject,
      final String text);

}
