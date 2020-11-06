package webfusion.lawyercrm.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webfusion.lawyercrm.models.User;
import webfusion.lawyercrm.repositories.UsersRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UsersService {

    private final UsersRepository usersRepository;

    @Autowired
    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public List<User> findAll() {
        return usersRepository.findAll();
    }

    public Optional<User> findByUsername(String username) {
        return usersRepository.findByUsername(username);
    }

    public void update(User user) {
        usersRepository.save(user);
    }

    public void delete(User user) {
        usersRepository.delete(user);
    }

}
