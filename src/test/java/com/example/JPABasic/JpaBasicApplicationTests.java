package com.example.JPABasic;

import com.example.JPABasic.entities.User;
import com.example.JPABasic.repository.UserRepository;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JpaBasicApplicationTests {
	@Autowired
	private UserRepository userRepository;

	@Test
	void saveAllUsers() {
		Faker faker = new Faker();
		for (int i = 0; i < 100; i++) {
			User user = new User(
					null,
					faker.name().fullName(),
					faker.internet().emailAddress(),
					faker.phoneNumber().cellPhone(),
					faker.address().cityName(),
					faker.avatar().image(),
					faker.internet().password()
			);
			userRepository.save(user);
		}
	}

}
