package fr.insa.ms.occupationTable.service;

import org.springframework.stereotype.Service;
import java.util.Random;

@Service
public class CapteurSimulatorService {

    private final Random random = new Random();

    public int getOccupantsSimule(int maxPlaces) {
        return random.nextInt(maxPlaces + 1); // 0 à maxPlaces
    }
}
