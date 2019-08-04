package me.lesovoy.kebably.persistence;

import lombok.extern.slf4j.Slf4j;
import me.lesovoy.kebably.model.construction.ItemBuilder;
import me.lesovoy.kebably.model.construction.OrderBuilder;
import me.lesovoy.kebably.model.enumeration.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

@Configuration
@Slf4j
public class LoadDatabase {

    @Bean
    CommandLineRunner initDatabase(OrderRepository repository) {
        return args -> {
//            log.info("Preloading" + repository.save(new OrderBuilder(1)
//                    .withStatus(Status.PENDING)
//                    .withUuid(UUID.randomUUID())
//                    .withItem(new ItemBuilder(ItemType.PITA)
//                            .withContents(Contents.BEEF)
//                            .withSize(Size.LARGE)
//                            .withSpiciness(Spiciness.MEDIUM_SPICY)
//                            .withSauce(Sauce.KETCHUP)
//                            .build())
//                    .withItem(new ItemBuilder(ItemType.DONNER_PLATE)
//                            .withContents(Contents.MIX)
//                            .withSize(Size.MEDIUM)
//                            .withSpiciness(Spiciness.NOT_SPICY)
//                            .withSauce(Sauce.GARLIC)
//                            .build())
//                    .build()));
        };
    }
}
