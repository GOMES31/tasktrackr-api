package dev.edugomes.springapi.repository;

import dev.edugomes.springapi.domain.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {

    // Retrieves all valid tokens for the specified user (not expired or not revoked)
    @Query(value = """
      select t from Token t inner join User u
      on t.user.id = u.id
      where u.id = :id and (t.expired = false or t.revoked = false)
      """)
    List<Token> findAllValidTokenByUser(@Param("id") Long userId);



    Optional<Token> findByToken(String token);
}
