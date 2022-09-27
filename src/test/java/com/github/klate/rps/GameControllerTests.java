package com.github.klate.rps;

import com.github.klate.rps.models.GameResult;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GameControllerTests {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void testPlayGame() throws Exception {
        ResponseEntity<GameResult> response = testRestTemplate.getForEntity("/api/v1/play", GameResult.class, "name=kai", "c=r");

        assertThat(Objects.requireNonNull(response.getBody()).getUsername()).isEqualTo("kai");
    }

    // TODO: play 100+ games and check if the distribution of the computer decisions are equal within a threshold
    // --> tests the randomizer

}
