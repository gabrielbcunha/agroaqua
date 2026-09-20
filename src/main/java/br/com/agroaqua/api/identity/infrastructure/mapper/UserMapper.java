package br.com.agroaqua.api.identity.infrastructure.mapper;

import br.com.agroaqua.api.identity.domain.user.User;
import br.com.agroaqua.api.identity.infrastructure.persistence.UserJpaEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel="spring")
public interface UserMapper {

    UserJpaEntity toJpaEntity(User user);

    default User toDomain(UserJpaEntity entity) {
        if (entity == null) {
            return null;
        }
        return new User(
                entity.getId(),
                entity.getEmail(),
                entity.getPassword(),
                entity.getRole()
        );
    }
}
