package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;  // 0, 1, 2 ... 키값을 생성해주는 친구.

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));  // null일 수도 있기 때문에 감싸줌.
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))    // 람다식이라고 함.
                .findAny();     // map에서 돌면서 찾아지면 걔를 반환하고, 끝까지 없으면 Optioanl에 null을 포함해서 반환.
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());     // values()는 Member들.
    }

    public void clearStore() {
        store.clear();
    }
}
