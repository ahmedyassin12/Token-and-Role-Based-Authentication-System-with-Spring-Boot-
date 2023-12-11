package com.example.demo.services;


//import com.example.demo.configuration.WebSecurityConfiguration;
import com.example.demo.configuration.PasswordConfig;
import com.example.demo.dao.RoleDao;
import com.example.demo.dao.UserDao;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
   private UserDao userdao ;

    @Autowired
  private   RoleDao roleDao ;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    PasswordConfig passwordConfig ;


    public UserDetailsService userDetailsService(){

return new UserDetailsService() {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }

};



    }

    @Autowired
    public UserService(UserDao userDao, RoleDao roleDao, PasswordEncoder passwordEncoder) {
        this.userdao = userDao;
        this.roleDao = roleDao;
        this.passwordEncoder = passwordEncoder;
    }
    public User createNewUser(User user )
    {

        return userdao.save(user) ;



    }


    public void initRolesandUser(){

        Role adminrole =new Role() ;
        adminrole.setRoleName("admin");
        adminrole.setRoleDescription("admin");
        roleDao.save(adminrole);

        Role userRole=new Role();
        userRole.setRoleName("user");
        userRole.setRoleDescription("user");
        roleDao.save(userRole) ;

        User adminUser=new User();
        adminUser.setUsername("admin");
        adminUser.setUserPassword(getEncoderPassword("admin123"));
        adminUser.setUserFirstName("admin");
        adminUser.setUserLastName("admin");
        Set<Role>adminRoles=new HashSet<>() ;
        adminRoles.add(adminrole);

        User user=new User();
        user    .setUsername("omk");
        user.setUserPassword(getEncoderPassword("hhh"));
        user.setUserFirstName("kekw");
        user.setUserLastName("9ahba");
        Set<Role>userRoles=new HashSet<>() ;
        userRoles.add(userRole);


        adminUser.setRole(adminRoles);
        user.setRole(userRoles);
        userdao.save(user) ;
        userdao.save(adminUser) ;


    }


    public String getEncoderPassword(String password){


        return passwordEncoder.encode(password) ;

    }



}
