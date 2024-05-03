package com.core.BloggingApplication.ServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.core.BloggingApplication.entity.User;
import com.core.BloggingApplication.payloads.UserDTO;
import com.core.BloggingApplication.repository.UserRepo;
import com.core.BloggingApplication.service.UserService;
import com.core.BloggingApplication.exception.ResourceNotFoundException;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
	private UserRepo userRepo;
    @Autowired
    private ModelMapper modelmapper;
	@Override
	public UserDTO createUser(UserDTO userdto) {///////////(1)
		User user=userDTOtoUser(userdto);
		User saveduser=this.userRepo.save(user);
		return userToUserDTO(saveduser);
	}

	@Override
	public UserDTO updateUser(UserDTO userdto, Integer id) {///////(2)
		//User user=userDTOtoUser(userdto);
		Optional <User> user=this.userRepo.findById(id);//by using get() on optional we get actual user
		if(user.isPresent()) {
			User actualuser=user.get();//now we are getting actual user
			
			actualuser.setName(userdto.getName());
			actualuser.setEmail(userdto.getEmail());
			actualuser.setPassword(userdto.getPassword());
			actualuser.setAbout(userdto.getAbout());
			User updateduser=userRepo.save(actualuser);
			
			
			return userToUserDTO(updateduser);
		}
			throw new ResourceNotFoundException("User", "Id", id);
	}

	@Override
	public UserDTO getUserById(Integer id) {///////(3)
		Optional <User> user=this.userRepo.findById(id);
		if(user.isPresent()) {
			User actualuser=user.get();
			return userToUserDTO(actualuser);
		}
		else {
			throw new ResourceNotFoundException("User","Id",id);
		}
		
	}

	@Override
	public List<UserDTO> getAllUser() {/////////(4)
		List<UserDTO> listusrdto=new ArrayList<>();
		List<User> listUser=this.userRepo.findAll();
		for(User eachUser:listUser) {
			UserDTO eachUserDTO=userToUserDTO(eachUser);
			listusrdto.add(eachUserDTO);
		}
		return listusrdto;
	}
	@Override
	public void deleteById(Integer id) {//////////(5)
		Optional <User> user=this.userRepo.findById(id);
		if(user.isPresent()) {
			User actualuser=user.get();
			userRepo.delete(actualuser);
		}
		else
		{
			throw new ResourceNotFoundException("User", "Id", id);
		}
		
	}
	private User userDTOtoUser(UserDTO userdto) {
		User user =this.modelmapper.map(userdto, User.class);
		
//		user.setName(userdto.getName());
//		user.setEmail(userdto.getEmail());
//		user.setPassword(userdto.getPassword());
//		user.setAbout(userdto.getAbout());
		return user;
	}
	private UserDTO userToUserDTO(User user) {
		UserDTO userdto=this.modelmapper.map(user, UserDTO.class);
		
//		userdto.setEmail(user.getEmail());
//		userdto.setName(user.getName());
//		userdto.setPassword(user.getPassword());
//		userdto.setAbout(user.getAbout());
		return userdto;
		
	}

	

}

