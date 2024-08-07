package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberSerivceTest {

    MemberService memberSerivce;
    MemoryMemberRepository memberRepository ;

    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberSerivce = new MemberService(memberRepository);
    }

    @AfterEach      // 어떤 메소드(여기선 Test) 하나가 끝날 때마다 동작한다.
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {   // 테스트는 함수명을 한글로 써도 된다. 빌드될 때 테스트 코드는 포함되지 않음.
        // given
        Member member = new Member();
        member.setName("spring");

        // when
        Long saveId = memberSerivce.join(member);

        // then
        Member findMember = memberSerivce.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외() {
        // given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        // when
        memberSerivce.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberSerivce.join(member2));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

/*      // 범위 선택 후 command + option + / -> 범위 주석 처리
        try {
            memberService.join(member2);
            fail();
        } catch (IllegalStateException e) {
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }
*/


        // then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
