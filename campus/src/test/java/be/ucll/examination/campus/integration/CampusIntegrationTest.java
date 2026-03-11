package be.ucll.examination.campus.integration;

import be.ucll.examination.campus.model.Campus;
import be.ucll.examination.campus.repository.CampusRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webtestclient.autoconfigure.AutoConfigureWebTestClient;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
public class CampusIntegrationTest {

    @Autowired
    private WebTestClient client;

    @Autowired
    private CampusRepository campusRepository;

    @BeforeEach
    public void buildUp() {
        campusRepository.addCampus(new Campus("PROXIMUS", "Geldenaaksebaan 335, 3001 Leuven", 450));
        campusRepository.addCampus(new Campus("HASSELT", "Oude Luikerbaan 79, 3500 Hasselt", 220));
    }

    @AfterEach
    public void cleanUp() {
        this.campusRepository.removeAllCampuses();
    }

    @Test
    public void given2Campuses_whenInvokingGetCampus_then2CampusesAreReturned() {
        client.get()
                .uri("/campus")
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody()
                .json("[" +
                        "{\"name\":\"PROXIMUS\",\"address\":\"Geldenaaksebaan 335, 3001 Leuven\", \"parkingSpots\": 450}," +
                        "{\"name\":\"HASSELT\",\"address\":\"Oude Luikerbaan 79, 3500 Hasselt\", \"parkingSpots\": 220}" +
                        "]");
    }

    @Test
    public void givenNoCampusDiest_whenInvokingPostDiest_thenCampusDiestIsSaved() {
        client.post()
                .uri("/campus")
                .header("Content-Type", "application/json")
                .bodyValue("{\"name\":\"DIEST\",\"address\":\"Weerstandsplein 2, 3290 Diest\",\"parkingSpots\":400}")
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody()
                .json("{\"name\":\"DIEST\",\"address\":\"Weerstandsplein 2, 3290 Diest\",\"parkingSpots\":400}");
    }
}
