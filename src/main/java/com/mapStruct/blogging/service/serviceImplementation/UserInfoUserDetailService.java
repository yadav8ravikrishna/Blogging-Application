package com.mapStruct.blogging.service.serviceImplementation;

import com.mapStruct.blogging.config.UserInfoUserDetail;
import com.mapStruct.blogging.entity.User;
import com.mapStruct.blogging.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserInfoUserDetailService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;

    public UserInfoUserDetailService() {
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<User> user = userRepo.findUserByUserName(userName);
        return user.map(UserInfoUserDetail::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not Found " + userName));
    }
}
