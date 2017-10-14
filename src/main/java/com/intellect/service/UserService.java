package com.intellect.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.intellect.model.User;
import com.intellect.repository.UserRepository;
import com.intellect.response.IntelectResponse;
import com.intellect.response.ValidationError;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;


	
	public IntelectResponse createUser(User user){
		IntelectResponse response = new IntelectResponse();
		if(!user.isActive()) {
			response.setResMsg("User Creation Failed");
			response.getValErrors().add(new ValidationError("ERR008", "User Must Be active on Create User", "400"));
			return response;
		}
		
		if(user.getBirthDate() == null) {
			response.setResMsg("User Creation Failed, Date Of Birth is required");
			response.getValErrors().add(new ValidationError("ERR009", "Date Of Birth Must Be Less than Today", "400"));
			return response;
		} else if(chkDOB(user.getBirthDate())) {
			response.setResMsg("User Creation Failed, Date Of Birth Must Be Less than Today Date");
			response.getValErrors().add(new ValidationError("ERR009", "Date Of Birth Must Be Less than Today", "400"));
			return response;
			
		}
		return saveUser(user, response);
	}

	public IntelectResponse updateUser(User user){
		IntelectResponse response = new IntelectResponse();
		if(user.getId() == null) {
			response.setResMsg("Validation Error, Failed to update User");
			response.getValErrors().add(new ValidationError("ERR007", "User Id is Required", "500"));
			return response;
		}
		if(user.getBirthDate() == null) {
			response.setResMsg("Date Of Birth is required, Update User Failed");
			response.getValErrors().add(new ValidationError("ERR009", "Date Of Birth Must Be Less than Today", "400"));
			return response;
		} else if(chkDOB(user.getBirthDate())) {
			response.setResMsg("Date Of Birth Must Be Less than Today Date, Update User Failed");
			response.getValErrors().add(new ValidationError("ERR009", "Date Of Birth Must Be Less than Today", "400"));
			return response;
			
		}
		User updateUser = userRepository.findOne(user.getId());
		updateUser.setBirthDate(user.getBirthDate());
		updateUser.setPinCode(user.getPinCode());
		
		
		return saveUser(updateUser, response);
	}


	
	public IntelectResponse deleteUser(Long userId){
		IntelectResponse response = new IntelectResponse();
		User updateUser = userRepository.findOne(userId);
		if(updateUser.getId() == null) {
			response.setResMsg("Validation Error, Failed to update User");
			response.getValErrors().add(new ValidationError("ERR009", "User Resource cannot Found", "400"));
			return response;
		}
		updateUser.setActive(false);
		userRepository.save(updateUser);
		

		response.setResMsg("User Deleted..");
		response.setUserId(userId);
		return response;
	}

 
	

	public IntelectResponse saveUser(User entity, IntelectResponse response) {
		List<ValidationError> valErrors = new ArrayList<ValidationError>();
		try {
			userRepository.save(entity);
		}catch (DataIntegrityViolationException divEX) {

			System.out.println("Exception :: -- > "+divEX );
			if(divEX.getMostSpecificCause().toString().contains("UNQ_USER_EMAIL")){
				valErrors.add(new ValidationError("ERR001", "Email Is Unique", "email"));
			} else if(divEX.getMostSpecificCause().toString().contains("email")){

				valErrors.add(new ValidationError("ERR002", "Email Is Mandatory", "email"));
			} else if(divEX.getMostSpecificCause().toString().contains("fName")){

				valErrors.add(new ValidationError("ERR003", "fName Is Mandatory", "fName"));
			}  else if(divEX.getMostSpecificCause().toString().contains("lName")){

				valErrors.add(new ValidationError("ERR004", "lName Is Mandatory", "lName"));
			}  else if(divEX.getMostSpecificCause().toString().contains("pincode")){

				valErrors.add(new ValidationError("ERR005", "Pincode Is Mandatory", "pincode"));
			}  else {
				valErrors.add(new ValidationError("DBERR500", "Internal Server Error", "500"));
			}
			
		}catch (Exception ex) { 
			valErrors.add(new ValidationError("DBERR500", "Internal Server Error", "500"));
			
		}
		if(valErrors != null && !valErrors.isEmpty()) {
			response.getValErrors().addAll(valErrors);
		} else {
			response.setResMsg("User Saved.");
			response.setUserId(entity.getId());
		}
		return response;
	}
	


	public boolean chkDOB(Date date) {
		if(date.before(subtractDays(new Date(), -1))) {
			return false;
		}
		return true;
	}
	
	

	public static Date subtractDays(Date date, int days) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.add(Calendar.DATE, -days);
				
		return cal.getTime();
	}
}
