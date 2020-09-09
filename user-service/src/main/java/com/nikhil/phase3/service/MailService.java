package com.nikhil.phase3.service;

import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.nikhil.phase3.model.NotificationEmail;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MailService {
	private final JavaMailSender mailSender;

	@Async
	void sendMail(NotificationEmail notificationEmail) throws Exception {
		MimeMessagePreparator messagePreparator = mimeMessage -> {
			MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
			messageHelper.setFrom("stockcharting@email.com");
			messageHelper.setTo(notificationEmail.getRecipient());
			messageHelper.setSubject(notificationEmail.getSubject());
			messageHelper.setText(notificationEmail.getBody());
		};
		try {
			mailSender.send(messagePreparator);
		} catch (MailException e) {
			throw new Exception(
					"Exception occurred when sending mail to " + notificationEmail.getRecipient(), e);
		}
	}
}
