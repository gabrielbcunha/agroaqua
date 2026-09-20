package br.com.agroaqua.api.identity.infrastructure.persistence;

import br.com.agroaqua.api.identity.domain.user.User;
import br.com.agroaqua.api.identity.domain.user.UserRepository;
import br.com.agroaqua.api.identity.infrastructure.mapper.UserMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final UserSpringDataRepository userSpringDataRepository;
    private final UserMapper userMapper;

    public UserRepositoryImpl(UserSpringDataRepository userSpringDataRepository, UserMapper userMapper) {
        this.userSpringDataRepository = userSpringDataRepository;
        this.userMapper = userMapper;
    }

    @Override
    public User save(User user) {
        UserJpaEntity userJpaEntity = userMapper.toJpaEntity(user);
        UserJpaEntity savedJpaEntity = userSpringDataRepository.save(userJpaEntity);
        return userMapper.toDomain(savedJpaEntity);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userSpringDataRepository.findByEmail(email).map(userMapper::toDomain);
    }

}