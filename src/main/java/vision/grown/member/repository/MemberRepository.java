package vision.grown.member.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import vision.grown.member.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,Long> {
    Optional<Member> findByEmail(String email);

    @EntityGraph(attributePaths = {"orderFundingList"})
    Optional<Member> findMemberById(Long memberId);
}
