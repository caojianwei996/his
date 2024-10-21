package learn.caojw.his.authorization.service;

import learn.caojw.his.authorization.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 用户业务层
 *
 * @author 曹健伟
 */
@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.selectByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        } else {
            return user;
        }
    }
}
