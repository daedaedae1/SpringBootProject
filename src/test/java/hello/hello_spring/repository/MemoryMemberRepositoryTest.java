package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

// 테스트는 순서와 관계 없이, 서로 의존 관계가 없이 설계가 되어야 한다.
// 테스트 주도 개발, TDD가 있지만, 지금 한 건 아님.
public class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach      // 어떤 메소드(여기선 Test) 하나가 끝날 때마다 동작한다.
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        // Assertions.assertEquals(member, null);
        assertThat(member).isEqualTo(result);    // 그냥 읽혀서 이걸 많이 사용한다.
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();  // shift + fn+F6 -> 같은 변수명 전부 rename.
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();// option + command + V -> Optional 가져오기.

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }


}
