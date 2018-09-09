package com.myapp.init;

import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.myapp.dao.UserDAO;
import com.myapp.dao.UserDetailDAO;
import com.myapp.entity.User;
import com.myapp.entity.UserDetail;

@Component
public class DataInit implements ApplicationRunner {

	@Autowired
	private UserDetailDAO userDetailDAO;

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private ObjectMapper objectMapper;
	@Override
	public void run(ApplicationArguments args) throws Exception {

		boolean insertDataOnStartUp = true;
		if (insertDataOnStartUp) {
			User user = new User();
			user.setUsername("USR001");
			user.setPassword("abcd@xyz");

			UserDetail userDetail = new UserDetail();
			userDetail.setFirstName("Michael");
			userDetail.setLastName("Smith");
			userDetail.setEmail("michael.smith@example.com");
			userDetail.setDob(LocalDate.of(1985, Month.APRIL, 1));

			user.setUserDetail(userDetail);
			User dbUser = this.userDAO.save(user);
			System.out.println(this.objectMapper.writeValueAsString(dbUser));
		}

		boolean updateDataOnStartUp = true;
		if (updateDataOnStartUp) {
			UserDetail userDetail = this.userDetailDAO.findById(1l).orElse(null);
			System.out.println(this.objectMapper.writeValueAsString(userDetail));
		}
	}

}