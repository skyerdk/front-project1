package wl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired  //数据库服务类
    private UserService suserService;//code7

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        SUser user = suserService.findUserByName(userName); //code8
        if (user == null) {
            throw new UsernameNotFoundException("UserName " + userName + " not found");
        }
        SecurityUser securityUser = new SecurityUser(user);
        List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
        for(SRole role:securityUser.getRoles()){
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return new org.springframework.security.core.userdetails.User(user.getName()
                ,user.getPassword(),authorities);
    }
}
