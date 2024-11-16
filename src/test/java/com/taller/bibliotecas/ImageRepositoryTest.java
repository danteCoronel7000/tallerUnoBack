package com.taller.bibliotecas;

import com.taller.bibliotecas.entitys.Image;
import com.taller.bibliotecas.repository.ImageRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ImageRepositoryTest {
    @Autowired
    ImageRepository imageRepository;

    @Test
    public void saveImage(){
        Image image = Image.builder()
                .id(6L)
                .imageId("324556342354")
                .name("imageFive")
                .imageUrl("https://res.cloudinary.com/dtvog2pqi/image/upload/v1727185644/samples/two-ladies.jpg")
                .build();
        imageRepository.save(image);
    }
}
