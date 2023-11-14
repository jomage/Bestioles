INSERT INTO species (id, common_name, latin_name) VALUES
    (100, 'Chat', 'Felis silvestris catus'),
    (200, 'Chien', 'Canis lupus familiaris'),
    (300, 'Lapin', 'Oryctolagus cuniculus');


INSERT INTO person (id , firstname , lastname , age) VALUES
    (100, 'Henri', 'Lamarque', 22),
    (200, 'Sylvie', 'Lamarque', 24),
    (300, 'Jean', 'Vintroi', 55),
    (400, 'Paul', 'Demaine', 80),
    (500, 'Sophie', 'Nero', 45),
    (600, 'Pierre', 'Sansane', 17),
    (700, 'John', 'Mangolo', 33),
    (800, 'Bill', 'Wagner', 26);

INSERT INTO animal (id, species_id , name, color, sex) VALUES
    (100, 100, 'Max', 'Gris tacheté', 'M'),
    (200, 200, 'Médor', 'Blanc', 'M'),
    (300, 100, 'Loulou', 'Noir', 'F'),
    (400, 200, 'Zia', 'Brun', 'F'),
    (500, 300, 'Lou', 'Blanc', 'F'),
    (600, 100, 'Minouchette', 'Roux', 'M');

INSERT INTO person_animals (person_id , animals_id) VALUES
    (200, 100),
    (200, 500),
    (300, 300),
    (400, 200),
    (500, 400),
    (700, 100),
    (800, 600);
