package com.blogApp;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.blogApp.config.AppConstants;
import com.blogApp.entity.Role;
import com.blogApp.repositories.RoleRepo;

@SpringBootApplication
public class BolgAppApisApplication implements CommandLineRunner{

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleRepo roleRepo;
	
	public static void main(String[] args) {
		SpringApplication.run(BolgAppApisApplication.class, args);
	}
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(this.passwordEncoder.encode("atin@123"));
		
		// first time when we run the code the roles(NORMAL & ADMIN) will add
		// and if already exist then no changes
		
		try {
			
			Role role = new Role();
			role.setRoleId(AppConstants.NORMAL_USER); // 502
			role.setName("NORMAL_USER"); 
			
			Role role1 = new Role();
			role1.setRoleId(AppConstants.ADMIN_USER); // 502
			role1.setName("ADMIN_USER"); 
			
			List<Role> roles = List.of(role,role1);
			
			List<Role> result = this.roleRepo.saveAll(roles);
			
//			result.forEach(r->{
//				System.out.println(r.getName());
//			}); 
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}

}
