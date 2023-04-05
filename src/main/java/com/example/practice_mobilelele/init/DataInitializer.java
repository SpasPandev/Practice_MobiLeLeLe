package com.example.practice_mobilelele.init;

import com.example.practice_mobilelele.model.entity.*;
import com.example.practice_mobilelele.model.enums.CategoryEnum;
import com.example.practice_mobilelele.model.enums.EngineEnum;
import com.example.practice_mobilelele.model.enums.RoleEnum;
import com.example.practice_mobilelele.model.enums.TransmissionEnum;
import com.example.practice_mobilelele.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BrandRepository brandRepository;
    private final ModelRepository modelRepository;
    private final OfferRepository offerRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UserRepository userRepository, RoleRepository roleRepository, BrandRepository brandRepository, ModelRepository modelRepository, OfferRepository offerRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.brandRepository = brandRepository;
        this.modelRepository = modelRepository;
        this.offerRepository = offerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        initializeUser();
    }

    private void initializeUser() {

        if (roleRepository.count() == 0) {

            Arrays.stream(RoleEnum.values())
                    .forEach(roleEnum -> {
                        Role role = new Role();

                        role.setRole(roleEnum);

                        roleRepository.save(role);
                    });
        }

        if (userRepository.count() == 0) {

            User userAdmin = new User();
            userAdmin.setUsername("admin");
            userAdmin.setPassword(passwordEncoder.encode("admin"));
            userAdmin.setFirstName("adminF");
            userAdmin.setLastName("adminL");
            userAdmin.setActive(true);
            userAdmin.setCreated(LocalDate.now());
            userAdmin.setRole(roleRepository.findByName(RoleEnum.Admin));

            User userTest = new User();
            userTest.setUsername("test");
            userTest.setPassword(passwordEncoder.encode("test"));
            userTest.setFirstName("testF");
            userTest.setLastName("testL");
            userTest.setActive(true);
            userTest.setCreated(LocalDate.now());
            userTest.setRole(roleRepository.findByName(RoleEnum.User));

            userRepository.saveAll(List.of(userAdmin, userTest));
        }

        if (brandRepository.count() == 0) {

            Brand brand = new Brand();
            brand.setName("Ford");
            brand.setCreated(LocalDate.now());

            brandRepository.save(brand);
        }

        if (modelRepository.count() == 0) {

            Model fiesta = new Model();
            fiesta.setName("Fiesta");
            fiesta.setCategory(CategoryEnum.Car);
            fiesta.setImageUrl("https://upload.wikimedia.org/wikipedia/commons/thumb/7/7d/2017_Ford_Fiesta_Zetec_Turbo_1.0_Front.jpg/1920px-2017_Ford_Fiesta_Zetec_Turbo_1.0_Front.jpg");
            fiesta.setStartYear(1976);
            fiesta.setBrand(brandRepository.findByName("Ford"));
            fiesta.setCreated(LocalDate.now());

            Model escort = new Model();
            escort.setName("Escort");
            escort.setCategory(CategoryEnum.Car);
            escort.setImageUrl("https://upload.wikimedia.org/wikipedia/commons/thumb/3/39/Ford_Escort_RS2000_MkI.jpg/420px-Ford_Escort_RS2000_MkI.jpg");
            escort.setStartYear(1967);
            escort.setEndYear(2004);
            escort.setBrand(brandRepository.findByName("Ford"));
            escort.setCreated(LocalDate.now());

            modelRepository.saveAll(List.of(fiesta, escort));
        }

        if (offerRepository.count() == 0) {

            Offer offer1 = new Offer();
            offer1.setDescription("Used, but well services and in good condition.");
            offer1.setEngine(EngineEnum.GASOLINE);
            offer1.setImageUrl("https://www.automaistv.com.br/wp-content/uploads/2021/02/ford_fiesta_st-line_5-door_600_edited-990x593.jpg");
            offer1.setMileage(22500);
            offer1.setPrice(BigDecimal.valueOf(14300));
            offer1.setTransmission(TransmissionEnum.MANUAL);
            offer1.setYear(2019);
            offer1.setCreated(LocalDate.now());
            offer1.setModel(modelRepository.findById(1L).orElse(null));
            offer1.setSeller(userRepository.findById(1L).orElse(null));

            Offer offer2 = new Offer();
            offer2.setDescription("After full maintenance, insurance, new tires...");
            offer2.setEngine(EngineEnum.DIESEL);
            offer2.setImageUrl("https://upload.wikimedia.org/wikipedia/commons/thumb/3/39/Ford_Escort_RS2000_MkI.jpg/420px-Ford_Escort_RS2000_MkI.jpg");
            offer2.setMileage(209000);
            offer2.setPrice(BigDecimal.valueOf(5500));
            offer2.setTransmission(TransmissionEnum.AUTOMATIC);
            offer2.setYear(2000);
            offer2.setCreated(LocalDate.now());
            offer2.setModel(modelRepository.findById(2L).orElse(null));
            offer2.setSeller(userRepository.findById(2L).orElse(null));

            offerRepository.saveAll(List.of(offer1, offer2));
        }
    }
}
