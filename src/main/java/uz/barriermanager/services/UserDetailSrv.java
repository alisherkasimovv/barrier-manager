package uz.barriermanager.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;
import uz.barriermanager.models.User;
import uz.barriermanager.models.UserPrincipal;
import uz.barriermanager.repositories.UserRepository;

/**
 * User Details service.
 *
 * @author Alisher Kasimov
 * @version 0.1.0055
 */
@Service
public class UserDetailSrv implements UserDetailsService {
    @Autowired
    @Qualifier("user-repository")
    private UserRepository repository;
    private WebApplicationContext applicationContext;

    public UserDetailSrv() {
        super();
    }

    @Override
    public UserDetails loadUserByUsername(final String s) throws UsernameNotFoundException {
        final User user = repository.findByUsername(s);
        if (user == null) {
            throw new UsernameNotFoundException(s);
        }
        return new UserPrincipal(user);
    }
}
