package vision.grown.funding.dto;

import lombok.Getter;

@Getter
public class ImageForm {
    private int order;
    private String url;

    public ImageForm(int order, String url) {
        this.order = order;
        this.url = url;
    }
}

