package com.chansons.chansons.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.chansons.chansons.entities.Role;
import com.chansons.chansons.entities.User;
import com.chansons.chansons.repositories.RoleRepository;
import com.chansons.chansons.repositories.UserRepository;
@Transactional
@Service
public class UserServiceImpl implements UserService{
@Autowired
UserRepository userRep;
@Autowired
RoleRepository roleRep;
@Autowired
BCryptPasswordEncoder bCryptPasswordEncoder;
@Override
public User saveUser(User user) {
user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
return userRep.save(user);
}
@Override
public User addRoleToUser(String username, String rolename) {
User usr = userRep.findByUsername(username);
Role r = roleRep.findByRole(rolename);
usr.getRoles().add(r);
return usr;
}
@Override
public void deleteAllusers() {
userRep.deleteAll();
}
@Override
public void deleteAllRoles() {
roleRep.deleteAll();
}
@Override
public Role addRole(Role role) {
return roleRep.save(role);
}
@Override
public User findUserByUsername(String username) {
return userRep.findByUsername(username);
}
}
