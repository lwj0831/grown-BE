package vision.grown.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vision.grown.member.Member;

public interface MemberRepository extends JpaRepository<Member,Long> {
}
