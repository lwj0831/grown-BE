package vision.grown.center.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vision.grown.center.repository.CenterRepository;

@Service
@RequiredArgsConstructor
public class CenterService {
    private final CenterRepository centerRepository;

}
