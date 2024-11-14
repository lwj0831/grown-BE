package vision.grown.product;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ProductImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="product_image_id")
    private Long id;
    private int imageOrder;
    private String url;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="product_id")
    private Product product;

    @Builder
    public ProductImage(int imageOrder, String url, Product product) {
        this.imageOrder = imageOrder;
        this.url = url;
        this.product = product;
    }
}
