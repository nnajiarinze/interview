package com.paf.exercise.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.paf.exercise.datasource.provider.database.model.DbPlayers;
import com.paf.exercise.datasource.provider.database.repository.PlayersRepository;
import com.paf.exercise.model.Player;
import com.paf.exercise.model.request.PostReqPlayer;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PlayersIntegrationTest {

    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();

    @MockBean
    private PlayersRepository playersRepository;

    ObjectMapper mapper = new ObjectMapper();


    HttpHeaders headers = new HttpHeaders();

    @Test
    public void testCreatePlayer() throws JsonProcessingException {

        headers.add("Content-Type", "application/json");

        PostReqPlayer postReqPlayer = PostReqPlayer.builder()
            .name("player")
            .build();
        DbPlayers dbPlayer = DbPlayers.builder()
            .id(1L)
            .name(postReqPlayer.getName())
            .build();

        when(playersRepository.save(any())).thenReturn(dbPlayer);
        when(playersRepository.findById(any())).thenReturn(Optional.ofNullable(dbPlayer));

        HttpEntity<String> entity = new HttpEntity<String>(mapper.writeValueAsString(postReqPlayer), headers);

        ResponseEntity<Player> response = restTemplate.exchange(
          createURLWithPort("/exercise/v1/players"), HttpMethod.POST, entity, Player.class);

        assertEquals(response.getBody().getName(), postReqPlayer.getName());
        assertNotNull(response.getBody().getId());
    }    

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }

}