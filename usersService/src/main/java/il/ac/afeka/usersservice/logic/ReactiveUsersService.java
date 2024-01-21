package il.ac.afeka.usersservice.logic;

import il.ac.afeka.usersservice.boundaries.NewUserBoundary;
import il.ac.afeka.usersservice.boundaries.UserBoundary;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ReactiveUsersService implements UsersService {
    private ReactiveUserCrud userCrud;

    public ReactiveUsersService(ReactiveUserCrud userCrud) {
        this.userCrud = userCrud;
    }

    @Override
    public Flux<UserBoundary> getUsersByDomain(String domain) {
        return this.userCrud
                .findByEmailEndingWith(domain)
                .map(UserBoundary::new);
    }

    @Override
    public Flux<UserBoundary> getUsersByMinimumAge(int minimumAgeInYears) {
        return this.userCrud
                .findAll()
                .filter(user -> user.calculateAge() >= minimumAgeInYears)
                .map(UserBoundary::new);
    }

    @Override
    public Flux<UserBoundary> getUsersByLastname(String lastname) {
        return userCrud
                .findAllByLastname(lastname)
                .map(UserBoundary::new);
    }

    @Override
    public Mono<Void> deleteAll() {
        return this.userCrud.deleteAll();
    }

    @Override
    public Flux<UserBoundary> getAll() {
        return userCrud
                .findAll()
                .map(UserBoundary::new);
    }

    @Override
    public Mono<UserBoundary> GetUserByEmail(String email, String password) {
        return userCrud
                .findByEmailAndPassword(email, password)
                .map(UserBoundary::new);
    }

    @Override
    public Mono<UserBoundary> createUser(NewUserBoundary user) {
        return Mono.just(user)
                .map(NewUserBoundary::toEntity)
                .flatMap(this.userCrud::save)
                .map(UserBoundary::new);
    }
}