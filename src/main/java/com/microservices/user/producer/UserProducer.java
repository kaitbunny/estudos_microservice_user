package com.microservices.user.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.microservices.user.domain.dto.EmailDTO;
import com.microservices.user.domain.model.User;

@Component
public class UserProducer {
	
	final RabbitTemplate rabbitTemplate;
	
	public UserProducer(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}

	@Value(value = "${broker.queue.email.name}")
	private String routingKey;
	
	public void publishMessageEmail(User user) {
		var emailDTO = new EmailDTO();
		
		emailDTO.setUserId(user.getUserId());
		emailDTO.setEmailTo(user.getEmail());
		emailDTO.setSubject("Cadastro realizado com sucesso!");
		emailDTO.setText(String.format("%s, seja bem vindo(a)!\n"
				+ "Agradecemos o seu cadastro, aproveite agora todos os recursos da nossa plataforma!", user.getName()));
		
		rabbitTemplate.convertAndSend("", this.routingKey, emailDTO);
	}
	
}
