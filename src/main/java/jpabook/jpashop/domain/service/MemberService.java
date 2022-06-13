package jpabook.jpashop.domain.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {


    private final MemberRepository memberRepository;



    /**
     * 회원 가입
     */
    @Transactional
    public Long join(Member member){
        validateDuplicateMeber(member);// 중복 회원
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMeber(Member member) {
        List<Member> byName = memberRepository.findByName(member.getName());
        if(!byName.isEmpty()){
            throw new IllegalStateException("이미 존재 하는 이름 입니다");

        }

    }
    /**
     * 회원 전체 조회
     */

    public List<Member> findMembers(){
        return memberRepository.finAll();
    }

    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }

}
