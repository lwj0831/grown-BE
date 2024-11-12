package vision.grown.member;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import vision.grown.orderFunding.OrderFunding;

import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;
    private String password;
    private String name;
    private String phoneNum;
    @Enumerated(EnumType.STRING)
    private MemberType memberType;

    @OneToMany(mappedBy = "member")
    @JsonIgnore
    private List<OrderFunding> orderFundingList;

}
