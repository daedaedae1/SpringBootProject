package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional  // JPA를 쓰려면 항상 이게 있어야 한다. 데이터를 저장하거나 변경할 때 항상 있어야 함.
public class MemberService {        // command + shift + T -> 테스트 클래스 생성

    private final MemberRepository memberRepository;

    // 생성자를 통해서 리포지토리가 들어옴. DI(디펜던시 인젝션) 중 생성자 주입이라고 한다.
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원 가입
     */
    public long join(Member member) {
        // 같은 이름이 있는 중복 회원 X
        validateDuplicateMember(member);    // 중복 회원 검증.
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName()).ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }

}