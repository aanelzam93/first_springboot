package com.elzam.springbot.elzam;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;

@SpringBootApplication
public class ElzamApplication {

	public static void main(String[] args) {
		SpringApplication.run(ElzamApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(StudentRepository repository, MongoTemplate mongoTemplate ){
		return  args -> {
			Address address = new Address(
					"indonesia",
					"london",
					"112j"
			);

			String email ="aan.elzam93@gmail.com";
			Student student = new Student(
					"Jamila",
					"elzam",
					"aan.elzam93@gmail.com",
					Gender.FEMALE,
					address,
					List.of("Computer c"),
					BigDecimal.TEN,
					LocalDateTime.now()
			);

			Query query = new Query();
			query.addCriteria(Criteria.where("email").is(email));
			List<Student> students = mongoTemplate.find(query, Student.class);
			if (students.size() > 1){
				throw  new IllegalAccessException("found many student with email"+email);
			}
			if (students.isEmpty()){
				System.out.println("inserting student"+student);
				repository.insert(student);
			}else {
				System.out.println(student + "already exist");
			}

		};
	}
}


