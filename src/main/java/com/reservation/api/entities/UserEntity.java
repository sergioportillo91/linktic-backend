package com.reservation.api.entities;

import java.util.Collection;
import java.util.Collections;

import jakarta.persistence.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="USUARIO")
@SequenceGenerator(name = "SEQ_USER", sequenceName = "SEQ_USER", initialValue = 1, allocationSize = 1)
public class UserEntity implements UserDetails {

	private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_USER")
    @Column(name = "ID", columnDefinition = "NUMBER(10)", nullable = false)
    private Long id;

	
    @Column(name = "USER_NAME",unique = true, length = 30, nullable = false)
    private String username;
    
    @Column(name = "CONTRASEÑA", nullable = false)
    private String password;
    
    @Column(name = "ROL" , length = 10, nullable = false)
    private String role;

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
    
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(role));
    }
}
