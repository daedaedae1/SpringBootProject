package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);

    Optional<Member> findById(Long id); // id가 null일 수도 있기 때문에, Optional이라는 것으로 감싸서 반환한다.

    Optional<Member> findByName(String name);

    List<Member> findAll();
}
