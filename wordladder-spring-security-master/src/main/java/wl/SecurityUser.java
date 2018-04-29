package wl;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class SecurityUser extends SUser implements UserDetails {
    private static final long serialVersionUID = 1L;
    private Collection<SimpleGrantedAuthority> authorities;
    public SecurityUser(SUser suser) {
        if (suser != null)
        {
            this.setId(suser.getId());
            this.setName(suser.getName());
            this.setPassword(suser.getPassword());
            this.setRoles(suser.getRoles());
            System.out.println("SecurityUser:"+suser.getName()+" "+ suser.getPassword());
        }
    }
    public SecurityUser(SUser suser,Collection<SimpleGrantedAuthority> authority) {
        this.setId(suser.getId());
        this.setName(suser.getName());
        this.setPassword(suser.getPassword());
        this.setRoles(suser.getRoles());
        this.authorities = authority;
    }

    @Override
    public Collection<SimpleGrantedAuthority> getAuthorities() {
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
        Set<SRole> userRoles = this.getRoles();
        if (userRoles != null)
        {
            for (SRole role : userRoles) {
                SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.getName());
                authorities.add(authority);
                System.out.println("security auth:"+authority);
            }
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public String getUsername() {
        return super.getName();
    }

    @Override
    public Set<SRole> getRoles() {
        for(SRole r: super.getRoles()) {
            System.out.println("Security get role:"+r.getName());
        }
        return super.getRoles();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}